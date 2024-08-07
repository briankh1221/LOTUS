<template>
  <div class="container text-center">
    <h2 class="justify-items-center mb-5">채팅 목록</h2>
  </div>
  <br /><br /><br />

  <!-- 채팅 목록 첫번째 버튼 -->
  <div v-for="room in roomList" :key="room.roomIdx">
    <div class="row mb-3">
      <div class="col-2"></div>
      <div class="col-8">
        <button
          @click="startChat(room)"
          class="btn btn-white p-0"
          type="button"
          data-bs-toggle="offcanvas"
          data-bs-target="#offcanvasScrolling"
          aria-controls="offcanvasScrolling"
          style="width: 100%"
        >
          <div class="row p-0 m-0">
            <div class="col-2 p-0">
              <img
                :src="'/images/' + room.buyer.image"
                class="rounded-circle mt-1"
                style="width: 40px; height: 40px"
              />
            </div>
            <div class="col-10 text-start p-0">
              <div v-if="myEmail">
                <h6 class="d-flex">
                  {{
                    room.buyer.email == myEmail
                      ? room.product.user.email
                      : room.buyer.email
                  }}
                  <div v-if="room.buyer.email == myEmail">
                    <span class="badge bg-secondary ms-3"> 구매상품 </span>
                  </div>
                  <div v-else>
                    <span class="badge bg-warning ms-3">판매상품</span>
                  </div>
                </h6>
              </div>
              <p class="mb-0">마지막 채팅 내용 표시</p>
            </div>
          </div>
        </button>
      </div>
      <div class="col-2"></div>
    </div>
  </div>

  <!-- 특정 채팅방을 클릭 했을 때만 나타나는 부분 ============================================================== -->
  <div
    class="offcanvas offcanvas-end"
    data-bs-scroll="true"
    data-bs-backdrop="false"
    tabindex="-1"
    id="offcanvasScrolling"
    aria-labelledby="offcanvasScrollingLabel"
    style="width: 500px"
  >
    <!-- 판매자 이름 및 x 버튼 헤더 -->
    <div class="offcanvas-header pt-3 pb-0">
      <div class="row w-100">
        <div class="col-4"></div>
        <div class="col-4">
          <h5 class="offcanvas-title" id="offcanvasScrollingLabel">
            판매자 이름
          </h5>
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
      <div class="col-2 ps-2 ps-0">
        <img
          src="/images/13.jpg"
          class="rounded-1"
          style="width: 60px; height: 60px"
        />
      </div>
      <div v-if="selectedRoom" class="col-5 ps-0 mt-1">
        <p class="mb-1">{{ selectedRoom.product.title }}</p>
        <p class="mb-0">{{ selectedRoom.product.price }} 원</p>
      </div>
      <div class="col-5 p-0 mt-4">
        <button class="btn btn-secondary me-2">예약하기</button>
        <button class="btn btn-danger">신고하기</button>
      </div>
    </div>

    <hr class="mt-1 mb-1" />

    <!-- 채팅 내용 부분 -->
    <div class="offcanvas-body p-3">
      <div
        class="container bg-dark-subtle pt-3 px-4"
        style="height: 500px; overflow-y: scroll"
      >
        <div v-if="chatList">
          <!-- 상대방 프로필과 상대방 채팅 내용 부분 -->
          <div v-for="chat in chatList" :key="chat">
            <div v-if="chat.writer != myStatus" class="row mb-3">
              <div v-if="myStatus == 'Seller'" class="col-1 px-0">
                <img
                  :src="`/images/${selectedRoom.buyer.image}`"
                  class="rounded-circle"
                  style="width: 35px; height: 35px"
                />
              </div>
              <div v-if="myStatus == 'Buyer'" class="col-1 px-0">
                <img
                  :src="`/images/${selectedRoom.product.user.image}`"
                  class="rounded-circle"
                  style="width: 35px; height: 35px"
                />
              </div>
              <div class="col-10 ps-0 mt-0">
                <div class="d-inline-flex p-2 ms-2 bg-white rounded">
                  {{ chat.content }}
                </div>
                <div class="d-inline-flex ms-2">
                  <p class="mb-0" style="font-size: 10px">
                    {{ chat.time }}
                  </p>
                </div>
              </div>
            </div>

            <!-- 내 채팅 내용 부분 -->
            <div v-if="chat.writer == myStatus" class="row mb-3">
              <div class="col-12 d-flex justify-content-end">
                <div class="d-inline-flex ms-2">
                  <p class="mb-0 mt-4 me-2" style="font-size: 10px">
                    {{ chat.time }}
                  </p>
                </div>
                <div class="d-inline-flex p-2 bg-white rounded">
                  {{ chat.content }}
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- 채팅 입력 창 -->
      <div class="row">
        <textarea
          @keyup.enter="sendMessageBtn"
          v-model="message"
          type="text"
          class="border rounded bg-light mt-2 ms-3"
          style="height: 100px; width: 93%"
        ></textarea>
      </div>
      <div class="d-flex justify-content-end mt-2 me-2">
        <button @click="sendMessageBtn" class="btn btn-secondary text-end">
          작성하기
        </button>
      </div>
    </div>
  </div>
  <!-- 특정 채팅방을 클릭 했을 때만 나타나는 부분 ============================================================== -->
</template>

<script setup>
import { ref, onMounted, watch } from 'vue';
import {
  getRoomList,
  getEmail,
  connect,
  handleChatRaw,
  sendMessage,
  loadChat,
} from '@/common/utils/utils';

import { newMessageEvent } from '@/common/utils/utils';

const roomList = ref([]);
const myEmail = ref();
const roomInfo = ref(null);
const chatList = ref([]);
const message = ref('');
const myStatus = ref('');
const selectedRoom = ref();

onMounted(async () => {
  myEmail.value = getEmail();
  await fetchRoom();
});

// 내가 연관된 모든 채팅방 목록 불러와서 셋팅

const fetchRoom = async () => {
  roomList.value = await getRoomList();
};

// ====== 채팅 관련 처리 ==============================================================================================

// 처음 채팅 버튼 눌렀을 때 방 생성 후 해당 방으로 웹소켓 연결

const startChat = async room => {
  // 채팅 선택한 방 정보 셋팅
  selectedRoom.value = room;
  if (selectedRoom.value.buyer.image.startsWith('org.spring')) {
    selectedRoom.value.buyer.image = 'noImage.jpg';
  }
  if (selectedRoom.value.product.user.image.startsWith('org.spring')) {
    selectedRoom.value.product.user.image = 'noImage.jpg';
  }

  const chatRaw = await connect(room.roomIdx);
  chatList.value = handleChatRaw(chatRaw);
  roomInfo.value = room;

  if (roomInfo.value.buyer.email == getEmail()) {
    myStatus.value = 'Buyer';
  } else {
    myStatus.value = 'Seller';
  }
};

// 채팅 창에서 엔터 or 전송 버튼 클릭시 메시지 전송
const sendMessageBtn = async () => {
  sendMessage(
    message.value,
    roomInfo.value.buyer.email,
    roomInfo.value.product.productIdx,
    roomInfo.value.roomIdx,
    roomInfo.value.buyer.email == getEmail(),
  );
  const chatRaw = await loadChat(roomInfo.value.roomIdx);
  message.value = '';
  chatList.value = handleChatRaw(chatRaw);
};

watch(newMessageEvent, async () => {
  const chatRaw = await loadChat(roomInfo.value.roomIdx);
  chatList.value = handleChatRaw(chatRaw);
});

// ====== 채팅 관련 처리 종료 ==========================================================================================
</script>

<style lang="scss" scoped></style>
