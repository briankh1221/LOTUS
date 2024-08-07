import { ref } from 'vue';
import VueJwtDecode from 'vue-jwt-decode';
import { instance, formInstance } from '../axios/axiosInstance';
import { Stomp } from '@stomp/stompjs';
import { format } from 'date-fns';
import numeral from 'numeral';

const websocket = ref(null);
const stompClient = ref(null);
const newMessageEvent = ref(null);

//
//
//
//

const isExpired = accessToken => {
  const expiration = VueJwtDecode.decode(accessToken).exp;

  return Math.floor(Date.now() / 1000) > expiration ? true : false;
};

const getRole = accessToken => {
  const role = VueJwtDecode.decode(accessToken).role;

  return role;
};

// sessionStorage의 accessToken 에서 email 추출해서 리턴
const getEmail = () => {
  const accessToken = sessionStorage.getItem('accessToken');
  if (accessToken) {
    const email = VueJwtDecode.decode(accessToken).sub;
    return email;
  }
  return '';
};

// 문자열로 된 시간 형식을 현재 시간과 비교해서 몇 초 전 형식으로 변환하는 매서드
const changeTimeFormat = posts => {
  //배열이 아닌 경우 배열의 형태로 변경
  if (!posts.length) {
    let post = [posts];
    posts = post;
  }
  for (let i = 0; i < posts.length; i++) {
    // 게시물 정보에서 날짜 추출
    const postingDate = ref();
    postingDate.value = posts[i].postingDate;

    // 문자열을 Date 객체로 변환
    postingDate.value = new Date(postingDate.value);

    // 현재 시간을 나타내는 Date 객체
    const currentDate = new Date();

    // 두 시간의 차이를 계산하여 초로 변환
    const differenceInSeconds = Math.floor(
      (currentDate - postingDate.value) / 1000,
    );

    // 초 단위로 된 시간 차이 => 표현 형식 변경
    if (differenceInSeconds < 60) {
      postingDate.value = differenceInSeconds + '초 전';
    } else if (differenceInSeconds < 60 * 60) {
      postingDate.value = Math.floor(differenceInSeconds / 60) + '분 전';
    } else if (differenceInSeconds < 60 * 60 * 24) {
      postingDate.value =
        Math.floor(differenceInSeconds / (60 * 60)) + '시간 전';
    } else {
      postingDate.value =
        Math.floor(differenceInSeconds / (60 * 60 * 24)) + '일 전';
    }
    posts[i].postingDate = postingDate.value;
  }

  return posts;
};

// 가격에 , 추가하는 매서드
const addComma = posts => {
  for (let i = 0; i < posts.length; i++) {
    // numeral 라이브러리를 통해 , 추가
    let noCommaPrice = posts[i].price;

    noCommaPrice = noCommaPrice.replace(/[,\s]/g, '');

    const commaPrice = ref(numeral(parseInt(noCommaPrice)).format(0, 0));
    posts[i].price = commaPrice.value;
  }
  return posts;
};

// 제목이 긴 경우 뒷부분을 자르는 매서드
const titleLength = (posts, length) => {
  for (let i = 0; i < posts.length; i++) {
    let title = posts[i].title;
    if (title.length > length) {
      title = title.substring(0, length);
      title = title + '...';
    }
    posts[i].shortTitle = title;
  }
  return posts;
};

// 제목이 긴 경우 뒷부분을 자르는 매서드
const contentLength = (posts, length) => {
  for (let i = 0; i < posts.length; i++) {
    let content = posts[i].content;
    if (content.length > length) {
      content = content.substring(0, length);
      content = content + '...';
    }
    posts[i].shortContent = content;
  }
  return posts;
};

// // 채팅의 room이 존재하는 지 확인 하는 매서드
const createRoom = async (buyerEmail, productIdx) => {
  const data = ref([]);

  data.value = { buyerEmail, productIdx };
  try {
    const response = await formInstance.post('/room', { ...data.value });
    const roomIdx = response.data.roomIdx;
    console.log('room 생성 매서드 - roomIdx', roomIdx);
    return roomIdx;
  } catch (error) {
    console.error(error);
  }
};

// 웹소켓 연결 매서드
const connect = roomIdx => {
  return new Promise(resolve => {
    websocket.value = new WebSocket('ws://localhost:8081/chat');
    stompClient.value = Stomp.over(websocket.value);

    stompClient.value.connect({}, async () => {
      console.log('Stomp websocket가 연결되었습니다');

      // 방에 구독
      stompClient.value.subscribe('/room/' + roomIdx, async message => {
        // 구독한 상대방이 새로운 message를 입력한 경우 이부분에서 처리
        console.log('새로운 메세지 도착');

        newMessageEvent.value = message;
      });
      let response = '';
      try {
        response = await loadChat(roomIdx);
      } catch (error) {}
      resolve(response);
    });
  });
};

// 저장된 채팅 불러오기 로직
const loadChat = async roomIdx => {
  try {
    const response = await instance.get(`/room/${roomIdx}`);
    return response.data;
  } catch (error) {
    console.error(error);
  }
};

// 메시지 전송 로직
const sendMessage = (
  message,
  buyerEmail,
  productIdx,
  roomIdx,
  isBuyerEmail,
) => {
  const messageToSend = ref({
    message,
    buyerEmail,
    productIdx,
    isBuyerEmail,
  });

  // JSON 형태로 변환하여 서버에 전송
  stompClient.value.send(
    '/send/' + roomIdx,
    {},
    JSON.stringify(messageToSend.value),
  );
};

//존재하는 room list 가져오기
const getRoomList = async () => {
  try {
    const response = await instance.get('/room-list', {
      params: {
        email: getEmail(),
      },
    });
    return response.data;
  } catch (error) {
    console.error(error);
  }
};

// 채팅 전체 목록을 정제
const handleChatRaw = chatRaw => {
  const sentences = chatRaw.split('##');
  sentences.pop();
  const after = [];
  for (const sentence of sentences) {
    const time = format(new Date(sentence.split('//')[0]), 'a h:mm');
    const writer = sentence.split('//')[1].split('::')[0];
    const content = sentence.split('//')[1].split('::')[1];
    let chatObject = { time, writer, content };
    after.push(chatObject);
  }
  return { ...after };
};

// 신뢰 지수 계산 부분
const trustRateCal = (buyingLength, sellingLength, evaluations) => {
  let avg = 0;
  evaluations.map(x => {
    if (x == 'EXCELLENT') {
      avg += 5;
    } else if (x == 'GOOD') {
      avg += 4;
    } else if (x == 'FAIR') {
      avg += 3;
    } else if (x == 'POOR') {
      avg += 2;
    } else if (x == 'VERY_POOR') {
      avg += 1;
    }
  });

  let score =
    30 + (avg / evaluations.length) * 10 + buyingLength + sellingLength;

  if (score >= 100) {
    score == 100;
  }

  return score;
};

export {
  isExpired,
  getRole,
  connect,
  createRoom,
  getEmail,
  sendMessage,
  loadChat,
  getRoomList,
  handleChatRaw,
  newMessageEvent,
  changeTimeFormat,
  addComma,
  titleLength,
  contentLength,
  trustRateCal,
};
