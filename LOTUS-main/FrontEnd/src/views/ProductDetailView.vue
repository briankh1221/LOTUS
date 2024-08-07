<template>
  <div v-if="post">
    <br /><br />

    <div class="row">
      <!-- 사진 슬라이드 부분 -->
      <div class="col-6">
        <div id="carouselExample" class="carousel slide">
          <div class="carousel-inner">
            <div
              v-for="(image, idx) in post.images.split(' ')"
              :key="idx"
              class="carousel-item"
              :class="{ active: idx === 0 }"
            >
              <img
                :src="'/images/' + image"
                class="d-block w-100 rounded-3"
                style="height: 400px; object-fit: cover"
              />
            </div>
          </div>
          <div v-if="post.images.split(' ').length > 1">
            <button
              class="carousel-control-prev"
              type="button"
              data-bs-target="#carouselExample"
              data-bs-slide="prev"
            >
              <span
                class="carousel-control-prev-icon"
                aria-hidden="true"
              ></span>
              <span class="visually-hidden">Previous</span>
            </button>
            <button
              class="carousel-control-next"
              type="button"
              data-bs-target="#carouselExample"
              data-bs-slide="next"
            >
              <span
                class="carousel-control-next-icon"
                aria-hidden="true"
              ></span>
              <span class="visually-hidden">Next</span>
            </button>
          </div>
        </div>
      </div>

      <!-- 사진 오른쪽 정보 부분 -->
      <div class="col-6 mt-0">
        <p class="text-secondary ms-2">카테고리 : {{ category }}</p>
        <h4 class="mt-0 ms-2" style="font-weight: 400">{{ post.title }}</h4>
        <h2 class="my-2 ms-2" style="font-weight: 600">{{ price }} 원</h2>
        <div class="row mt-3">
          <div class="col-7 p-0">
            <p class="ms-3 mb-0 text-secondary">
              제품일련번호 &middot {{ post.productName }}
            </p>
          </div>
          <div class="col-5 text-end p-0">
            <RouterLink class="text-decoration-none" to="/price"
              ><p class="text-dark me-2 mb-1">
                이 상품 시세 조회하러 가기
              </p></RouterLink
            >
          </div>
        </div>

        <hr class="p-0 mt-0" />

        <div class="row mb-3">
          <div v-if="postingDate" class="col-5 ms-2">
            {{ postingDate }} &middot 조회수 20 &middot 찜 10
          </div>
          <div class="col-6 text-end ms-4">
            <RouterLink class="text-dark text-decoration-none" to="/fraud"
              ><img
                class="me-2"
                src="/images/fraudImg.jpg"
                style="width: 18px"
              />사기 조회</RouterLink
            >
          </div>
        </div>
        <div class="row">
          <div class="col-4 text-center">
            <p class="text-secondary mb-2">물품 상태</p>
            <h5 v-if="productStatus">{{ productStatus }}</h5>
          </div>
          <div class="col-4 text-center">
            <p class="text-secondary mb-2">거래 방법</p>
            <h5 v-if="deliveryMethod">{{ deliveryMethod }}</h5>
          </div>
          <div class="col-4 text-center">
            <p class="text-secondary mb-2">배송비</p>
            <h5 v-if="deliveryFee">{{ deliveryFee }}</h5>
          </div>
        </div>

        <hr class="mb-0" />

        <!-- 찜하기 예약하기 채팅하기 버튼 -->
        <!-- 본인의 판매상품인 경우 수정하기 삭제하기 버튼 -->
        <div v-if="accessToken" class="container mt-2">
          <!-- 본인의 상품이 아닌 경우 -->
          <div v-if="post && post.user.email != getEmail(accessToken)">
            <div class="row">
              <div class="col-md-6 mb-2">
                <button
                  @click="addFavorite"
                  class="btn btn-outline-danger w-100"
                  :class="{
                    active: isFavorite,
                  }"
                  type="button"
                >
                  찜하기
                </button>
              </div>
              <!-- 판매 완료 상품의 경우 -->
              <div
                v-if="post.transactionStatus == 'COMPLETED'"
                class="col-md-6"
              >
                <button
                  class="btn text-bg-warning border bg-opacity-50 w-100"
                  type="button"
                  disabled
                >
                  판매 완료 상품
                </button>
              </div>
              <!-- 내가 예약한 상품의 경우 -->
              <div
                v-else-if="
                  post.transactionStatus == 'UNDER_RESERVATION' &&
                  post.buyer.email == getEmail()
                "
                class="col-md-6"
              >
                <button
                  class="btn text-bg-secondary bg-opacity-75 w-100"
                  type="button"
                  data-bs-toggle="modal"
                  data-bs-target="#cancleReservation"
                >
                  예약 취소 하기
                </button>
              </div>

              <!-- 다른 사람이 예약한 상품의 경우 -->
              <div
                v-else-if="
                  post.transactionStatus == 'UNDER_RESERVATION' &&
                  post.buyer.email != getEmail()
                "
                class="col-md-6"
              >
                <button class="btn btn-success w-100" type="button" disabled>
                  예약 중인 상품 입니다
                </button>
              </div>

              <!-- 판매중인 상품의 경우 -->
              <div
                v-else-if="post.transactionStatus == 'ON_SALE'"
                class="col-md-6"
              >
                <button
                  class="btn btn-success w-100"
                  type="button"
                  data-bs-toggle="modal"
                  data-bs-target="#makeReservation"
                >
                  예약하기
                </button>
              </div>

              <!-- 예약 취소 하기 버튼 클릭시 나오는 화면 =============================================================-->
              <div
                class="modal fade"
                id="cancleReservation"
                tabindex="-1"
                aria-labelledby="exampleModalLabel"
                aria-hidden="true"
              >
                <div class="modal-dialog">
                  <div class="modal-content">
                    <div class="modal-header">
                      <h1 class="modal-title fs-5" id="exampleModalLabel">
                        예약 취소 확인
                      </h1>
                      <button
                        type="button"
                        class="btn-close"
                        data-bs-dismiss="modal"
                        aria-label="Close"
                      ></button>
                    </div>
                    <div class="modal-body">
                      해당 상품을 예약 취소 하시겠습니까?
                    </div>
                    <div class="modal-footer">
                      <button
                        type="button"
                        class="btn btn-secondary"
                        data-bs-dismiss="modal"
                      >
                        닫기
                      </button>
                      <button
                        @click="cancleReservation"
                        type="button"
                        class="btn btn-success"
                        data-bs-dismiss="modal"
                      >
                        예약 취소 하기
                      </button>
                    </div>
                  </div>
                </div>
              </div>
              <!-- 예약 취소 하기 버튼 클릭시 나오는 화면 종료=============================================================-->

              <!-- 예약하기 버튼 클릭시 나오는 화면 =============================================================-->
              <div
                class="modal fade"
                id="makeReservation"
                tabindex="-1"
                aria-labelledby="exampleModalLabel"
                aria-hidden="true"
              >
                <div class="modal-dialog">
                  <div class="modal-content">
                    <div class="modal-header">
                      <h1 class="modal-title fs-5" id="exampleModalLabel">
                        예약 확인
                      </h1>
                      <button
                        type="button"
                        class="btn-close"
                        data-bs-dismiss="modal"
                        aria-label="Close"
                      ></button>
                    </div>
                    <div class="modal-body">해당 상품을 예약하시겠습니까?</div>
                    <div class="modal-footer">
                      <button
                        type="button"
                        class="btn btn-secondary"
                        data-bs-dismiss="modal"
                      >
                        취소
                      </button>
                      <button
                        @click="makeReservation"
                        type="button"
                        class="btn btn-success"
                        data-bs-dismiss="modal"
                      >
                        예약 하기
                      </button>
                    </div>
                  </div>
                </div>
              </div>
              <!-- 예약하기 버튼 클릭시 나오는 화면 종료=============================================================-->

              <!-- 채팅하기 버튼 및 오른쪽 채팅창 부분 *24.02.05 고훈 ================================================================= -->

              <div class="row pe-0" v-if="post">
                <div class="col-md-12 pe-0">
                  <button
                    @click="startChat"
                    class="btn btn-dark w-100"
                    type="button"
                    data-bs-toggle="offcanvas"
                    data-bs-target="#offcanvasScrolling"
                    aria-controls="offcanvasScrolling"
                  >
                    채팅하기
                  </button>
                </div>
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
                        <h5
                          class="offcanvas-title ms-2"
                          id="offcanvasScrollingLabel"
                        >
                          {{ post.user.email }}
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
                        :src="`/images/${post.images.split(' ')[0]}`"
                        class="rounded-1"
                        style="width: 60px; height: 60px"
                      />
                    </div>
                    <div class="col-5 ps-0 mt-1">
                      <p class="mb-1">{{ post.title }}</p>
                      <p class="mb-0" style="font-weight: 600">
                        {{ price }} 원
                      </p>
                    </div>
                    <div class="col-5 p-0 mt-4">
                      <button
                        class="btn btn-success me-2"
                        type="button"
                        data-bs-toggle="modal"
                        data-bs-target="#exampleModal"
                      >
                        예약하기
                      </button>
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
                          <div v-if="chat.writer == 'Seller'" class="row mb-3">
                            <div class="col-1 px-0">
                              <img
                                :src="`/images/${post.user.image}`"
                                class="rounded-circle"
                                style="width: 35px; height: 35px"
                              />
                            </div>
                            <div class="col-10 ps-0 mt-0">
                              <div
                                class="d-inline-flex p-2 ms-2 bg-white rounded"
                              >
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
                          <div v-if="chat.writer == 'Buyer'" class="row mb-3">
                            <div class="col-12 d-flex justify-content-end">
                              <div class="d-inline-flex ms-2">
                                <p
                                  class="mb-0 mt-4 me-2"
                                  style="font-size: 10px"
                                >
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
                      <button
                        @click="sendMessageBtn"
                        class="btn btn-secondary text-end"
                      >
                        작성하기
                      </button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <!-- 채팅하기 버튼 및 오른쪽 채팅창 부분 종료 ================================================================= -->
          </div>
          <!-- 본인의 상품인 경우 -->
          <div
            v-if="post && post.user.email == getEmail(accessToken)"
            class="row mt-4"
          >
            <div class="col-md-6">
              <button
                @click="modifyPost(post.productIdx)"
                class="btn btn-secondary w-100"
                type="button"
              >
                수정하기
              </button>
            </div>
            <div class="col-md-6">
              <button
                @click="deletePost"
                class="btn btn-danger w-100"
                type="button"
              >
                삭제하기
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>

    <hr class="mt-5" />

    <div class="row">
      <!-- 상세 물품 정보 -->
      <div class="col-7 my-0">
        <div class="p-3 bg-light text-dark text-center">
          <h5>거래 전 주의 사항</h5>
          <p class="my-0" style="font-size: 12px">
            판매자가 별도의 메신저로 결제링크를 보내거나 직거래(직접송금)을
            유도하는 경우 사기일 가능성이 높으니 거래를 자제해 주세요 거래 전
            사기 조회도 권장합니다
          </p>
        </div>
        <p v-if="post" class="my-5" style="white-space: pre-line">
          {{ post.description }}
        </p>
      </div>
      <!-- 판매자 정보 -->
      <div class="col-5">
        <div class="p-2 bg-light text-dark text-center">
          <h5>판매자 정보</h5>
        </div>
        <hr class="mt-0" />
        <!-- 이메일 및 프로필 사진 표시 부분 -->
        <div class="row">
          <div class="col-6">
            <p class="ms-4 mt-1 fs-5">
              {{ post.user.name }}
            </p>
          </div>
          <div class="col-6 text-end">
            <img
              :src="`/images/${post.user.image}`"
              class="rounded-circle me-5"
              style="width: 50px; height: 50px"
            />
          </div>
        </div>

        <!-- 신뢰 지수 바 표시 부분 
        <div class="row">
          <div class="row mb-2 mt-3 mb-0">
            <div class="col-5 text-start ms-3">
              <p class="fs-6 mb-0" style="color: darkgreen">신뢰 지수 47</p>
            </div>
            <div class="col-6 text-end me-2">
              <p class="fs-6 mb-0 me-2">100</p>
            </div>
          </div>
          <div class="row">
            <div class="col-12 p-1">
              <div
                class="progress ms-3 me-4 p-0"
                role="progressbar"
                aria-label="Basic example"
                aria-valuenow="0"
                aria-valuemin="0"
                aria-valuemax="80"
                style="height: 10px"
              >
                <div
                  class="progress-bar"
                  style="
                    background: linear-gradient(
                      to right,
                      rgb(105, 236, 164),
                      rgb(4, 119, 61)
                    );
                    width: 47%;
                  "
                ></div>
              </div>
            </div>
          </div>
        </div> -->

        <div class="row">
          <div class="row text-center">
            <div class="col-4 mt-3">
              <p class="fs-5 mt-2 mb-0">판매내역</p>
              <hr class="mt-0 mx-4" />
              <p class="fs-4">3</p>
            </div>
            <div class="col-4 mt-3">
              <p class="fs-5 mt-2 mb-0">구매완료내역</p>
              <hr class="mt-0 mx-2" />
              <p class="fs-4">5</p>
            </div>
            <div class="col-4 mt-3">
              <p class="fs-5 mt-2 mb-0">리뷰내역</p>
              <hr class="mt-0 mx-4" />
              <p class="fs-4">6</p>
            </div>
          </div>
        </div>
        <div class="p-2 bg-light text-dark text-center">
          <h5>타 사이트 시세</h5>
        </div>
        <hr class="mt-0" />
        <div class="row text-center">
          <div v-if="isSpinnerShown">
            <div
              class="spinner-border mt-3"
              style="width: 3rem; height: 3rem"
              role="status"
            >
              <span class="visually-hidden">Loading...</span>
            </div>
          </div>
          <div v-if="bunJangposts" class="col-12 d-flex border-0">
            <div
              v-for="post in bunJangposts"
              :key="post.title"
              class="col-4 border-0"
            >
              <div class="card border-0">
                <img
                  :src="post.image"
                  class="img-fluidd-block rounded-3 px-1"
                  style="height: 100px; width: 100%; object-fit: cover"
                />
                <div class="card-body">
                  <p
                    class="card-title text-truncate"
                    style="font-size: smaller"
                  >
                    {{ post.title }}
                  </p>
                  <p class="card-text" style="font-weight: 600">
                    {{ post.price }} 원
                  </p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { instance, formInstance } from '@/common/axios/axiosInstance';
import { onMounted, ref, watch } from 'vue';
import { useRouter } from 'vue-router';
import {
  getEmail,
  createRoom,
  connect,
  sendMessage,
  loadChat,
  handleChatRaw,
  addComma,
} from '@/common/utils/utils';
import { newMessageEvent } from '@/common/utils/utils';

const router = useRouter();
const { productIdx, price, postingDate } = history.state;
const post = ref(null);
const productStatus = ref('');
const deliveryMethod = ref('');
const deliveryFee = ref('');
const category = ref('');
const isFavorite = ref(false);
const message = ref('');
const roomIdx = ref(null);
const chatList = ref([]);

// 타 사이트 시세 조회 관련
const searchResult = ref([]);
const bunJangposts = ref([]);
const isSpinnerShown = ref(false);

const accessToken = ref(sessionStorage.getItem('accessToken'));

const postForm = ref({
  title: null,
  price: null,
  image: null,
});

// 게시글의 정보 db 에서 받아오는 매서드
const fetchDetail = async () => {
  try {
    const response = await instance.get('/product/' + productIdx);
    return response.data;
  } catch (error) {
    console.error(error);
  }
};

const fetchPriceList = async () => {
  try {
    const response = await formInstance.get('/python/price-list', {
      params: { keyword: post.value.title },
    });
    return response.data;
  } catch (error) {
    console.error(error);
  }
};

//
// ====== 찜하기 버튼 처리 ==========================================================================================
// 페이지 접속한 직후 찜 했는지 여부 확인
const fetchIsFavorite = async () => {
  try {
    const response = await instance.get('user/product/favorite');
    console.log('response', response);
    response.data.map(x => {
      if (x.productIdx == productIdx) {
        isFavorite.value = true;
        return;
      }
    });
  } catch (error) {
    console.error(error);
  }
};
if (sessionStorage.getItem('accessToken')) {
  fetchIsFavorite();
}

// 찜하기 버튼을 누른 상황 처리
// 찜 추가 하는 매서드 선언
const fetchFavoriteAdd = async () => {
  try {
    await instance.post('/user/product/' + productIdx + '/favorite');
    isFavorite.value = true;
  } catch (error) {
    console.error(error);
  }
};

// 찜 삭제 하는 매서드 선언
const fetchFavoriteDelete = async () => {
  try {
    await instance.delete('/user/product/' + productIdx + '/favorite');
    isFavorite.value = false;
  } catch (error) {
    console.error(error);
  }
};

// 찜 버튼 클릭시 작동되는 매서드
const addFavorite = () => {
  // accessToken의 존재 여부로 회원인지 아닌지 판별
  if (sessionStorage.getItem('accessToken')) {
    // 회원이지만 찜버튼을 누르지 않은 상태
    if (!isFavorite.value) {
      fetchFavoriteAdd();
    } else if (isFavorite.value) {
      fetchFavoriteDelete();
    }
  } else {
    alert('회원만 찜 가능');
    router.push({ name: 'Signin' });
  }
};
// ====== 찜하기 버튼 처리 종료 ==========================================================================================
//
//

// =====수정하기 삭젝하기, 예약하기 버튼 처리=========================================================================================
const modifyPost = productIdx => {
  console.log(productIdx);
  router.push({
    name: 'ProductModify',
    state: { productIdx },
  });
};
const deletePost = async () => {
  try {
    if (confirm('정말로 게시물을 삭제하시겠습니다?')) {
      await instance.delete('/user/product/' + productIdx);
      router.push({ name: 'Home' });
    }
  } catch (error) {
    console.error(error);
  }
};

const cancleReservation = async () => {
  try {
    await instance.patch('/user/product/' + productIdx + '/cancelstatus');
    router.push({ name: 'Home' });
  } catch (error) {
    console.error(error);
  }
};

const makeReservation = async () => {
  try {
    await instance.patch('/user/product/' + productIdx + '/bookingstatus');
    router.push({ name: 'Home' });
  } catch (error) {
    console.error(error);
  }
};

// =====수정하기 삭젝하기, 예약하기 버튼 처리 종료======================================================================================
//
//
// ====== 채팅 관련 처리 ==============================================================================================

// 처음 채팅 버튼 눌렀을 때 방 생성 후 해당 방으로 웹소켓 연결

const startChat = async () => {
  console.log('startChat 실행');
  roomIdx.value = await createRoom(getEmail(), productIdx);
  const chatRaw = await connect(roomIdx.value);
  chatList.value = handleChatRaw(chatRaw);
};

// 채팅 창에서 엔터 or 전송 버튼 클릭시 메시지 전송
const sendMessageBtn = async () => {
  // 여기서 채팅 버튼을 누르는 사람은 항상 구매자 이므로 true 일괄 전달
  if (message.value != '') {
    sendMessage(message.value, getEmail(), productIdx, roomIdx.value, true);
    const chatRaw = await loadChat(roomIdx.value);
    chatList.value = handleChatRaw(chatRaw);
    message.value = '';
  }
};

// 채팅 스크롤 가장 아래로 기본 설정
const handelScroll = () => {};

watch(newMessageEvent, async () => {
  const chatRaw = await loadChat(roomIdx.value);
  chatList.value = handleChatRaw(chatRaw);
});

// ====== 채팅 관련 처리 종료 ==========================================================================================

// onMounted 시작 =============================================================================================================================
onMounted(async () => {
  // DB에서 정보 추출 ==========================================================================================
  post.value = await fetchDetail();

  if (post.value.user.image.startsWith('org.spring')) {
    post.value.user.image = 'noImage.jpg';
  }

  // DB에서 정보 추출 종료 =====================================================================================

  //
  //
  // ====== enum 선택 옵션 값들 처리 ===========================================================================
  // 물품 상태
  if (post.value.productStatus == 'EXCELLENT') {
    productStatus.value = '최상';
  } else if (post.value.productStatus == 'GOOD') {
    productStatus.value = '상';
  } else if (post.value.productStatus == 'FAIR') {
    productStatus.value = '중';
  } else if (post.value.productStatus == 'POOR') {
    productStatus.value = '하';
  } else if (post.value.productStatus == 'VERY_POOR') {
    productStatus.value = '최하';
  }

  // 배송방법
  if (post.value.deliveryMethod == 'SHIPPING_SERVICE') {
    deliveryMethod.value = '택배거래';
  } else if (post.value.deliveryMethod == 'DIRECT_DEAL') {
    deliveryMethod.value = '직거래';
  }

  // 배송비 포함 여부
  if (post.value.deliveryFee == 'INCLUDING_DELIVERY_FEE') {
    deliveryFee.value = '포함';
  } else if (post.value.deliveryFee == 'NOT_INCLUDING_DELIVERY_FEE') {
    deliveryFee.value = '미포함';
  }

  // 카테고리 값
  if (post.value.categoryName == 'LUXURY_GOODS') {
    category.value = '럭셔리/명품';
  } else if (post.value.categoryName == 'FASHION_CLOTHES') {
    category.value = '패션/의류';
  } else if (post.value.categoryName == 'FASHION_GOODS') {
    category.value = '패션/잡화';
  } else if (post.value.categoryName == 'BEAUTY') {
    category.value = '뷰티';
  } else if (post.value.categoryName == 'INFANTRY_GOODS') {
    category.value = '유아 상품';
  } else if (post.value.categoryName == 'MOBILE_TABLET') {
    category.value = '모바일 태블릿';
  } else if (post.value.categoryName == 'HOME_APPLIANCE') {
    category.value = '가전 제품';
  } else if (post.value.categoryName == 'NOTEBOOK_PC') {
    category.value = '노트북, PC';
  } else if (post.value.categoryName == 'CAMERA') {
    category.value = '카메라, 캠코더';
  } else if (post.value.categoryName == 'FURNITURE') {
    category.value = '가구, 인테리어';
  } else if (post.value.categoryName == 'HOUSEHOLD_GOODS') {
    category.value = '리빙, 생활';
  } else if (post.value.categoryName == 'PETS_HOBBIES') {
    category.value = '반려생활, 취미';
  } else if (post.value.categoryName == 'BOOKS_RECORD_WRITING_GOODS') {
    category.value = '도서, 음반, 문구';
  } else if (post.value.categoryName == 'TICKET_COUPON') {
    category.value = '티켓, 쿠폰';
  } else if (post.value.categoryName == 'SPORTS_GOODS') {
    category.value = '스포츠';
  } else if (post.value.categoryName == 'LEISURE_TRAVEL_ITEMS') {
    category.value = '레저, 여행';
  } else if (post.value.categoryName == 'MOTOR_CYCLE') {
    category.value = '오토바이';
  } else if (post.value.categoryName == 'INDUSTRIAL_GOODS') {
    category.value = '공구, 산업 용품';
  } else if (post.value.categoryName == 'SHARING') {
    category.value = '무료 나눔';
  } else if (post.value.categoryName == 'NFT') {
    category.value = 'NFT';
  } else if (post.value.categoryName == 'USED_CARS') {
    category.value = '중고차';
  }

  // ====== enum 선택 옵션 값들 처리 종료 ===========================================================================

  console.log(post.value);

  bunJangposts.value = [];
  isSpinnerShown.value = true;
  searchResult.value = await fetchPriceList();

  for (const result of searchResult.value) {
    if (result != '[]') {
      postForm.value.title = result.split("'")[1];
      postForm.value.price = result.split("'")[2];
      postForm.value.price = postForm.value.price.replace(/[,\s]/g, '');
      postForm.value.image = result.split("'")[5];

      const postValue = { ...postForm.value };

      bunJangposts.value.push(postValue);
      console.log(bunJangposts.value);
      if (bunJangposts.value.length == 3) {
        break;
      }
    }
  }

  // 가격에 , 추가하기
  bunJangposts.value.price = addComma(bunJangposts.value);
  isSpinnerShown.value = false;
});
// onMounted 종료 =============================================================================================================================
</script>

<style lang="scss" scoped></style>
