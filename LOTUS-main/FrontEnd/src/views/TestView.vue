<template>
  <button
    class="btn btn-primary"
    type="button"
    data-bs-toggle="offcanvas"
    data-bs-target="#viewChat"
  >
    채팅하기
  </button>

  <div
    class="offcanvas offcanvas-end"
    data-bs-scroll="true"
    data-bs-backdrop="false"
    tabindex="-1"
    id="viewChat"
    style="width: 500px"
  >
    <!-- 판매자 이름 및 x 버튼 헤더 -->
    <div class="offcanvas-header pt-3 pb-0">
      <div class="row w-100">
        <div class="col-4"></div>
        <div class="col-4">
          <h5 class="offcanvas-title">판매자 이름</h5>
        </div>
        <div class="col-4 text-end mt-1">
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="offcanvas"
            aria-label="Close"
          ></button>
        </div>
      </div>
    </div>

    <hr class="mt-2 mb-2" />

    <!-- 사진 및 물품 가격  표시 부분 -->
    <div class="row m-1">
      <div class="col-4 ps-2 ps-0">
        <img
          src="/images/11111111111.jpg"
          class="rounded-1"
          style="width: 60px; height: 60px"
        />
      </div>
      <div class="col-8">2222</div>
    </div>

    <hr class="mt-2 mb-1" />

    <!-- 채팅 내용 부분 -->
    <div class="offcanvas-body p-3">
      <div
        class="container bg-dark-subtle pt-3 px-4"
        style="height: 500px; overflow-y: scroll"
      >
        <!-- 상대방 프로필과 상대방 채팅 내용 부분 -->
        <div class="row mb-3">
          <div class="col-1 px-0">
            <img
              src="/images/11111111111.jpg"
              class="rounded-circle"
              style="width: 35px; height: 35px"
            />
          </div>
          <div class="col-10 ps-0 mt-0">
            <div class="d-inline-flex p-2 ms-2 bg-white rounded">
              안녕하세요
            </div>
            <div class="d-inline-flex ms-2">
              <p class="mb-0" style="font-size: 10px">오후 5:32</p>
            </div>
          </div>
        </div>

        <!-- 상대방 프로필과 상대방 채팅 내용 부분 -->
        <div class="row mb-3">
          <div class="col-1 px-0">
            <img
              src="/images/11111111111.jpg"
              class="rounded-circle"
              style="width: 35px; height: 35px"
            />
          </div>
          <div class="col-10 mt-0 ps-0">
            <div class="d-inline-flex p-2 ms-2 bg-white rounded">
              물건 아직 있나요??
            </div>
            <div class="d-inline-flex ms-2">
              <p class="mb-0" style="font-size: 10px">오후 5:32</p>
            </div>
          </div>
        </div>

        <!-- 상대방 프로필과 상대방 채팅 내용 부분 -->
        <!-- <div class="row mb-3">
          <div class="col-2 ps-0">
            <img
              src="/images/11111111111.jpg"
              class="rounded-circle"
              style="width: 42px; height: 42px"
            />
          </div>
          <div class="col-10 ps-0 mt-0">
            <div class="d-inline-flex p-2 bg-white rounded">
              안녕하세요 안녕하세요 안녕하세요 안녕하세요 안녕하세요 안녕하세요
              안녕하세요
            </div>
            <div class="d-inline-flex ms-2">
              <p class="mb-0" style="font-size: 10px">오후 5:32</p>
            </div>
          </div>
        </div> -->

        <!-- 내 채팅 내용 부분 -->
        <div class="row mb-3">
          <div class="col-12 d-flex justify-content-end">
            <div class="d-inline-flex ms-2">
              <p class="mb-0 mt-4 me-2" style="font-size: 10px">오후 5:32</p>
            </div>
            <div class="d-inline-flex p-2 bg-white rounded">아직 있습니다</div>
          </div>
        </div>

        <!-- 내 채팅 내용 부분 -->
        <div class="row mb-3">
          <div class="col-12 d-flex justify-content-end">
            <div class="d-inline-flex ms-2">
              <p class="mb-0 mt-4 me-2" style="font-size: 10px">오후 5:32</p>
            </div>
            <div class="d-inline-flex p-2 bg-white rounded">
              구매하실 건가요??
            </div>
          </div>
        </div>

        <!-- 내 채팅 내용 부분 -->
        <div class="row mb-3">
          <div class="col-12 d-flex justify-content-end">
            <div class="d-inline-flex ms-2">
              <p class="mb-0 mt-4 me-2" style="font-size: 10px">오후 5:32</p>
            </div>
            <div class="d-inline-flex p-2 bg-white rounded">
              구매 결정 하신 경우 예약 잡아주시면 됩니다. 직거래 택배거래 모두
              가능합니다
            </div>
          </div>
        </div>
      </div>
      <!-- 채팅 입력 창 -->
      <div class="row">
        <textarea
          @keyup.enter="sendMessage"
          v-model="message"
          type="text"
          class="border rounded bg-light mt-2 ms-3"
          style="height: 100px; width: 93%"
        ></textarea>
      </div>
      <div class="d-flex justify-content-end mt-2 me-2">
        <button @click="sendMessage" class="btn btn-secondary text-end">
          작성하기
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { Stomp } from '@stomp/stompjs';
import { ref, onMounted } from 'vue';
import { instance } from '@/common/axios/axiosInstance';

const roomIdx = ref(); // 방 ID 설정
const websocket = ref(null);
const stompClient = ref(null);
const message = ref('메세지 입니다');
const data = ref([]);
const email = ref('1');

const messageToSend = ref({
  message: null,
  myEmail: null,
  productIdx: null,
});

const getList = async () => {
  //존재하는 room list 가져오기
  try {
    const response = await instance.get('/room-list', {
      params: {
        email: email.value,
      },
    });
    roomIdx.value = response.data.roomIdx;
    console.log(response.data);
  } catch (error) {
    console.error(error);
  }
};

const connect = async () => {
  //

  // // 채팅의 room이 존재하는 지 확인
  data.value = { buyerEmail: '2', productIdx: 1 };
  try {
    const response = await instance.post('/room', { ...data.value });
    roomIdx.value = response.data.roomIdx;
    console.log(response.data);
  } catch (error) {
    console.error(error);
  }

  // 소켓 연결
  websocket.value = new WebSocket('ws://localhost:8081/chat');
  stompClient.value = Stomp.over(websocket.value);

  stompClient.value.connect({}, () => {
    console.log('Stomp websocket가 연결되었습니다');

    // 방에 구독
    stompClient.value.subscribe('/room/' + roomIdx.value, message => {
      console.log('매서드안 진입');
      const chatMessage = JSON.parse(message.body);
      showChat(chatMessage);
      loadChat();
      console.log('구독 완료');
    });
  });
  loadChat();
};

const loadChat = chatList => {
  console.log('loadChat');
  // 저장된 채팅 불러오기 로직
};

const showChat = chatMessage => {
  // 채팅 보여주기 로직
};

const sendMessage = () => {
  // 메시지 전송 로직
  messageToSend.value = {
    message: message.value,
    myEmail: 'myEmail', // buyer의 email로 넣어줘야 함
    productIdx: 2,
  };

  // JSON 형태로 변환하여 서버에 전송
  stompClient.value.send(
    '/send/' + roomIdx.value,
    {},
    JSON.stringify(messageToSend.value),
  );

  // 전송 후 메시지 입력창 초기화
  message.value = '';
};

onMounted(() => {
  connect(); // 컴포넌트가 마운트된 후에 소켓 연결 시도
  getList();
});
</script>

<style lang="scss" scoped></style>
