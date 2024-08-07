<template>
  <div class="container text-center"></div>
  <div class="row">
    <!-- 판매중 예약중 판매완료 헤더 -->
    <div class="container text-center mt-3 mb-5">
      <h2>구매 내역</h2>
      <hr />
    </div>
    <!-- 판매중 예약중 판매완료 헤더 -->
    <div v-if="sellingPosts" class="row my-4">
      <div class="col-3">
        <p class="ms-3 my-0">{{ sellingPosts.length }} 개의 상품</p>
      </div>
      <div class="col-9 text-end">
        <button
          @click="statusMethod('UNDER_RESERVATION')"
          class="btn btn-outline-secondary rounded-5 mx-1"
          :class="{ active: selectedStatus == 'UNDER_RESERVATION' }"
        >
          예약중
        </button>
        <button
          @click="statusMethod('COMPLETED')"
          class="btn btn-outline-secondary rounded-5 mx-1"
          :class="{ active: selectedStatus == 'COMPLETED' }"
        >
          구매완료
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
                  class="badge text-bg-success bg-opacity-75 border mb-2 w-85"
                >
                  판매 페이지
                </button>
              </div>
            </div>
            <div v-if="post.transactionStatus == 'UNDER_RESERVATION'">
              <div class="row justify-content-center">
                <span class="badge text-bg-light border mb-2 w-85">예약중</span>
                <button
                  @click="cancleMethod(post.productIdx)"
                  class="badge text-bg-success bg-opacity-75 border mb-2 w-85"
                >
                  예약 취소 하기
                </button>
              </div>
            </div>
            <div v-if="post.transactionStatus == 'COMPLETED'">
              <div class="row justify-content-center">
                <span class="badge text-bg-light border mb-2 w-85"
                  >판매완료</span
                >
                <button
                  class="badge text-bg-success bg-opacity-75 border mb-2 w-85"
                  data-bs-toggle="modal"
                  data-bs-target="#review"
                  data-bs-whatever="@mdo"
                  @click="reviewBtn(post.productIdx)"
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
                          판매자에 대한 리뷰 작성하기
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
                          @click="writeReview(selectedProductIdx)"
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
import { addComma, changeTimeFormat } from '@/common/utils/utils';

const router = useRouter();
const isHover = ref({});
const sellingPosts = ref([]);
const selectedStatus = ref('UNDER_RESERVATION');
const selectedProductIdx = ref();

const reviewForm = ref({
  title: null,
  evaluation: 'FAIR',
  content: null,
  images: new File([], 'images'),
});

const setHover = (idx, value) => {
  isHover.value[idx] = value;
};
// 버튼 클릭시 메서드 부분 ===================================================================================

const goDetailPage = (productIdx, price, postingDate) => {
  router.push({
    name: 'ProductDetail',
    state: { productIdx, price, postingDate },
  });
};

//리뷰 작성하기 버튼을 누르면 선택된 product의 idx 저장
const reviewBtn = productIdx => {
  selectedProductIdx.value = productIdx;
};

// 모달 내부에서 리뷰 작성하기 버튼 누르면 db에 작성한 리뷰 저장
const writeReview = async productIdx => {
  console.log(productIdx);
  try {
    console.log({ ...reviewForm.value });
    await formInstance.post('/user/product/' + productIdx + '/review', {
      ...reviewForm.value,
    });
  } catch (error) {
    console.error(error);
  }
};

const statusMethod = async status => {
  selectedStatus.value = status;
  sellingPosts.value = await fetchMyData(status);
  // 경과 시간 값 처리하기
  sellingPosts.value.postingDate = changeTimeFormat(sellingPosts.value);

  // 가격에 , 추가하기
  sellingPosts.value.price = addComma(sellingPosts.value);
};

const cancleMethod = async productIdx => {
  await instance.patch('/user/product/' + productIdx + '/cancelstatus');

  // 초기 화면 전체 판매 내역 불러오기
  sellingPosts.value = await fetchMyData('UNDER_RESERVATION');

  // 경과 시간 값 처리하기
  sellingPosts.value.postingDate = changeTimeFormat(sellingPosts.value);

  // 가격에 , 추가하기
  sellingPosts.value.price = addComma(sellingPosts.value);

  alert('예약이 취소 되었습니다');
};

// 클릭시 페이지 이동 메서드 부분 종료 ==============================================================================

const fetchMyData = async selectedStatus => {
  try {
    const response = await instance.get('/user/buying-list/' + selectedStatus);
    return response.data;
  } catch (error) {
    console.error(error);
  }
};

// 페이지 onload 시 실행 매서드
onMounted(async () => {
  // 초기 화면 전체 판매 내역 불러오기
  sellingPosts.value = await fetchMyData('UNDER_RESERVATION');

  // 경과 시간 값 처리하기
  sellingPosts.value.postingDate = changeTimeFormat(sellingPosts.value);

  // 가격에 , 추가하기
  sellingPosts.value.price = addComma(sellingPosts.value);
});
</script>

<style lang="scss" scoped></style>
