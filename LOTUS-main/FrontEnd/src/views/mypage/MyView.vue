<template>
  <div class="container text-center"></div>
  <div class="row">
    <!-- 신뢰 지수 및 각종 내역 div -->
    <div class="row border rounded-2" style="width: 100%; height: 200px">
      <div class="row p-0">
        <!-- 이름 및 신뢰지수 부분 -->
        <div class="col-4 border text-center pt-2">
          <div class="row text-center pt-2">
            <!-- 이름 및 프로필 사진 부분 -->
            <!-- 프로필 이미지 첨부 부분 -->
            <div class="row">
              <div class="col-7">
                <!-- modal 띄우는 버튼 부분 -->
                <button
                  type="button"
                  class="btn"
                  data-bs-toggle="modal"
                  data-bs-target="#profile"
                >
                  <div
                    class="text-dark text-start"
                    style="width: 80px; height: 80px"
                  >
                    <div v-if="profileFile">
                      <img
                        :src="profileFile"
                        alt="Selected Image"
                        style="width: 80px; height: 80px"
                        class="rounded-circle"
                      />
                    </div>
                    <div v-else>
                      <img
                        :src="imageUrl"
                        alt="Selected Image"
                        style="width: 80px; height: 80px"
                        class="rounded-circle"
                      />
                      <button
                        type="button"
                        class="btn-close position-absolute top-10 start-55 translate-middle p-0 bg-secondary border border-light rounded-circle"
                        @click.prevent="removeFile"
                      ></button>
                    </div>
                  </div>
                </button>
                <!-- modal 띄우는 버튼 부분 종료-->

                <!-- Modal 내부 부분-->
                <div
                  class="modal fade text-start"
                  id="profile"
                  tabindex="-1"
                  aria-labelledby="exampleModalLabel"
                  aria-hidden="true"
                >
                  <div class="modal-dialog">
                    <div class="modal-content">
                      <div class="modal-header">
                        <h1 class="modal-title fs-5" id="exampleModalLabel">
                          프로필 사진 등록
                        </h1>
                        <button
                          type="button"
                          class="btn-close"
                          data-bs-dismiss="modal"
                          aria-label="Close"
                          @click.prevent="removeFile"
                        ></button>
                      </div>
                      <div class="modal-body ms-5">
                        <label for="hiddenInput" style="cursor: pointer">
                          <div
                            class="text-dark text-start me-3"
                            style="width: 80px; height: 80px"
                          >
                            <div v-if="typeof selectedFile == 'string'">
                              <img
                                :src="selectedFile"
                                alt="Selected Image"
                                style="width: 80px; height: 80px"
                                class="rounded-circle"
                              />
                            </div>
                            <div v-else>
                              <img
                                :src="imageUrl"
                                alt="Selected Image"
                                style="width: 80px; height: 80px"
                                class="rounded-circle"
                              />
                              <button
                                type="button"
                                class="btn-close position-absolute top-10 start-55 translate-middle p-0 bg-secondary border border-light rounded-circle"
                                @click.prevent="removeFile"
                              ></button>
                            </div>
                          </div>
                        </label>
                      </div>
                      <div class="modal-footer">
                        <button
                          type="button"
                          class="btn btn-light"
                          data-bs-dismiss="modal"
                          @click="removeFile"
                        >
                          취소
                        </button>
                        <button
                          type="button"
                          class="btn btn-secondary"
                          data-bs-dismiss="modal"
                          @click="saveProfile"
                        >
                          프로필사진 저장
                        </button>
                      </div>
                    </div>
                  </div>
                </div>

                <input
                  type="file"
                  id="hiddenInput"
                  name="images"
                  style="display: none"
                  @change="handleFile"
                />
              </div>

              <!-- Modal 내부 부분 종료 -->

              <!-- 오른쪽 이름 표시 부분 -->
              <div class="col-5 mt-4 p-0 text-start">
                <p v-if="sellingPosts[0]" class="fs-5">
                  {{ sellingPosts[0].user.name }} 님
                </p>
              </div>
            </div>
          </div>

          <!-- 신뢰 지수 및 판매 구매 리뷰 개수 부분 -->
          <div v-if="trustRate" class="row mb-2 mt-4">
            <div class="col-5 text-start ms-3">
              <p class="fs-6 mb-0" style="color: darkgreen">
                신뢰 지수 {{ trustRate }}
              </p>
            </div>
            <div class="col-6 text-end me-2">
              <p class="fs-6 mb-0 me-2">100</p>
            </div>
          </div>

          <div
            class="progress mx-3"
            role="progressbar"
            aria-label="Basic example"
            aria-valuenow="0"
            aria-valuemin="0"
            aria-valuemax="100"
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
              "
              :style="{ width: trustRate + '%' }"
            ></div>
          </div>
        </div>

        <!-- 판매 구매 리뷰 개수 부분 -->
        <div class="col-8">
          <div class="row text-center">
            <div class="col-4 mt-5">
              <p class="fs-5 mt-2 mb-0">판매내역</p>
              <hr class="mt-0 mx-5" />
              <p class="fs-4">{{ sellingPosts.length }}</p>
            </div>
            <div class="col-4 mt-5">
              <p class="fs-5 mt-2 mb-0">구매완료내역</p>
              <hr class="mt-0 mx-5" />
              <p class="fs-4">{{ buyingPosts.length }}</p>
            </div>
            <div class="col-4 mt-5">
              <p class="fs-5 mt-2 mb-0">리뷰내역</p>
              <hr class="mt-0 mx-5" />
              <p class="fs-4">{{ reviews.length }}</p>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- 판매중 예약중 판매완료 헤더 -->
    <div v-if="sellingPosts" class="row my-4">
      <div class="col-3">
        <p class="ms-3 my-0">{{ sellingPosts.length }} 개의 상품</p>
      </div>
      <div class="col-9 text-end">
        <button
          @click="selectedStatus = 'ON_SALE'"
          class="btn btn-outline-secondary rounded-5 mx-1"
          :class="{ active: selectedStatus == 'ON_SALE' }"
        >
          판매중
        </button>
        <button
          @click="selectedStatus = 'UNDER_RESERVATION'"
          class="btn btn-outline-secondary rounded-5 mx-1"
          :class="{ active: selectedStatus == 'UNDER_RESERVATION' }"
        >
          예약중
        </button>
        <button
          @click="selectedStatus = 'COMPLETED'"
          class="btn btn-outline-secondary rounded-5 mx-1"
          :class="{ active: selectedStatus == 'COMPLETED' }"
        >
          판매완료
        </button>
      </div>
    </div>

    <!-- 선택한 조건 게시물 목록 -->
    <div v-if="sellingPosts" class="row">
      <div
        v-for="post in sellingPosts.filter(
          post => post.transactionStatus == selectedStatus,
        )"
        :key="post"
        class="col-2 mb-4"
      >
        <div
          class="card"
          style="width: 100%"
          @mouseover="setHover(post.productIdx, true)"
          @mouseleave="setHover(post.productIdx, false)"
        >
          <img
            :src="`/images/${post.images.split(' ')[0]}`"
            class="card-img-top"
            style="width: 100%; height: 150px; object-fit: cover"
          />
          <div class="card-body pb-0">
            <p class="card-title text-secondary fs-6 mb-0 text-truncate">
              {{ post.title }}
            </p>
            <div class="row">
              <div class="col-12 mb-1">
                <p class="card-text" style="font-weight: 800; font-size: 14px">
                  {{ post.price }} 원
                </p>
              </div>
            </div>
            <div class="row text-start mt-2">
              <p class="text-secondary mb-3" style="font-size: 12px">
                {{ post.postingDate }}
              </p>
            </div>

            <div v-if="post.transactionStatus == 'ON_SALE'">
              <div class="row justify-content-center">
                <span class="badge text-bg-light border mb-2 w-85">판매중</span>
                <button
                  @click="
                    goDetailPage(post.productIdx, post.price, post.postingDate)
                  "
                  class="badge text-bg-success border mb-2 w-85"
                >
                  판매 페이지
                </button>
              </div>
            </div>
            <div v-if="post.transactionStatus == 'UNDER_RESERVATION'">
              <div class="row justify-content-center">
                <span class="badge text-bg-light border mb-2 w-85">예약중</span>
                <button
                  @click="soldMethod(post.productIdx)"
                  class="badge text-bg-success border mb-2 w-85"
                >
                  판매 완료 하기
                </button>
              </div>
            </div>
            <div v-if="post.transactionStatus == 'COMPLETED'">
              <div class="row justify-content-center">
                <span class="badge text-bg-light border mb-2 w-85"
                  >판매완료</span
                >
                <button
                  class="badge text-bg-success border mb-2 w-85"
                  data-bs-toggle="modal"
                  data-bs-target="#review"
                  data-bs-whatever="@mdo"
                >
                  리뷰 작성하기
                </button>
                <!-- 모달 부분 ===================================================================== -->
                <div
                  class="modal fade"
                  id="review"
                  tabindex="-1"
                  aria-labelledby="exampleModalLabel"
                  aria-hidden="true"
                >
                  <div class="modal-dialog">
                    <div class="modal-content">
                      <div class="modal-header">
                        <h1 class="modal-title fs-5" id="exampleModalLabel">
                          구매자에 대한 리뷰 작성하기
                        </h1>
                        <button
                          type="button"
                          class="btn-close"
                          data-bs-dismiss="modal"
                          aria-label="Close"
                        ></button>
                      </div>
                      <div class="modal-body">
                        <form>
                          <div class="mb-1">
                            <input
                              v-model="reviewForm.title"
                              type="text"
                              class="form-control"
                              placeholder="제목"
                            />
                          </div>
                          <div class="mb-3">
                            <label for="recipient-name" class="col-form-label"
                              >별점 선택</label
                            >
                            <select
                              v-model="reviewForm.evaluation"
                              class="form-select"
                              aria-label="Default select example"
                            >
                              <option selected>별점 선택</option>
                              <option value="VERY_POOR">1</option>
                              <option value="POOR">2</option>
                              <option value="FAIR">3</option>
                              <option value="GOOD">4</option>
                              <option value="EXCELLENT">5</option>
                            </select>
                          </div>
                          <div class="mb-3">
                            <textarea
                              v-model="reviewForm.content"
                              class="form-control"
                              style="height: 100px"
                              placeholder="후기 작성"
                            ></textarea>
                          </div>
                        </form>
                      </div>
                      <div class="modal-footer">
                        <button
                          type="button"
                          class="btn btn-secondary"
                          data-bs-dismiss="modal"
                        >
                          취소
                        </button>
                        <button
                          type="button"
                          class="btn btn-primary"
                          data-bs-dismiss="modal"
                          @click="writeReview(post.productIdx)"
                        >
                          리뷰 작성하기
                        </button>
                      </div>
                    </div>
                  </div>
                </div>
                <!-- 모달 부분 종료 ================================================================ -->
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { instance, formInstance } from '@/common/axios/axiosInstance';
import {
  changeTimeFormat,
  addComma,
  trustRateCal,
  getEmail,
} from '@/common/utils/utils';

const router = useRouter();
const isHover = ref({});
const sellingPosts = ref([]);
const buyingPosts = ref([]);
const reviews = ref([]);
const selectedStatus = ref('ON_SALE');
const profileFile = ref('/images/imageBtn.jpg');
const selectedFile = ref('/images/imageBtn.jpg');
const imageUrl = ref();
const trustRate = ref();

const form = ref({
  id: null,
  password: null,
  name: null,
  email: null,
  phone: null,
  image: null,
});

const reviewForm = ref({
  title: null,
  evaluation: 'FAIR',
  content: null,
  images: new File([], 'images'),
});

const setHover = (idx, value) => {
  isHover.value[idx] = value;
};

// 프로필 사진 관련 부분 ==================================================================================
const handleFile = event => {
  const file = event.target.files[0];
  selectedFile.value = file;
  imageUrl.value = URL.createObjectURL(file);
  form.value.image = new File([file], 'image');
};

const removeFile = () => {
  selectedFile.value = '/images/imageBtn.jpg';
  imageUrl.value = '';
  form.value.image = new File([], 'image');
};

const saveProfile = async () => {
  if (imageUrl.value) {
    profileFile.value = imageUrl.value;
  } else {
    profileFile.value = '/images/imageBtn.jpg';
  }
  console.log(form.value);
  try {
    await formInstance.put('/user/details', {
      ...form.value,
    });

    alert('프로필 사진 수정이 완료 되었습니다.');
  } catch (error) {
    console.error(error);
  }
};

// 프로필 사진 관련 부분 종료 ==============================================================================

//  버튼들 메서드 부분 ===================================================================================

const goDetailPage = (productIdx, price, postingDate) => {
  router.push({
    name: 'ProductDetail',
    state: { productIdx, price, postingDate },
  });
};

const soldMethod = async productIdx => {
  if (confirm('판매 완료 하시겠습니까?')) {
    try {
      await instance.patch('/user/product/' + productIdx + '/soldstatus');
      alert('판매 완료');
      sellingPosts.value = await fetchSelling();
    } catch (error) {
      console.error(error);
    }
  }
};

const writeReview = async productIdx => {
  try {
    console.log({ ...reviewForm.value });
    await formInstance.post('/user/product/' + productIdx + '/review', {
      ...reviewForm.value,
    });
  } catch (error) {
    console.error(error);
  }
};

// 클릭시 페이지 이동 메서드 부분 종료 ==============================================================================

const fetchSelling = async () => {
  try {
    const response = await instance.get('/user/product');
    return response.data;
  } catch (error) {
    console.error(error);
  }
};

const fetchBuying = async () => {
  try {
    const response = await instance.get('/user/buying-list/COMPLETED');
    return response.data;
  } catch (error) {
    console.error(error);
  }
};

const fetchReview = async () => {
  try {
    const response = await instance.get('/user/buyer-review-list');
    return response.data;
  } catch (error) {
    console.error(error);
  }
};

const fetchMyData = async () => {
  try {
    const response = await instance.get('/user/details');
    return response.data;
  } catch (error) {
    console.error(error);
  }
};

// 페이지 onload 시 실행 매서드
onMounted(async () => {
  // 초기 화면 전체 판매 내역 불러오기
  sellingPosts.value = await fetchSelling();

  // 초기 나의 정보 불러 오기
  form.value = await fetchMyData();

  // 현재 profile 사진 설정 부분
  if (!form.value.image.startsWith('org.spring')) {
    profileFile.value = 'images/' + form.value.image;
  }

  // 전체 구매 내역 불러오기
  buyingPosts.value = await fetchBuying();

  // 경과 시간 값 처리하기
  sellingPosts.value.postingDate = changeTimeFormat(sellingPosts.value);

  // 가격에 , 추가하기
  sellingPosts.value.price = addComma(sellingPosts.value);

  // 모든 리뷰 불러오기
  reviews.value = await fetchReview();

  // 받은 평가만 추출 후 신뢰 지수 계산
  let evaluations = [
    ...reviews.value.map(x => {
      if (x.user.email != getEmail()) {
        return x.evaluation;
      }
    }),
  ];

  // 신뢰 지수 계산
  trustRate.value = trustRateCal(
    buyingPosts.value.length,
    sellingPosts.value.length,
    evaluations,
  );

  console.log(trustRate.value);
});
</script>

<style lang="scss" scoped></style>
