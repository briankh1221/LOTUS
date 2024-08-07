<template>
  <div class="container text-center"></div>
  <div class="row">
    <!-- 판매중 예약중 판매완료 헤더 -->
    <div class="container text-center mt-3 mb-5">
      <h2>찜한 상품</h2>
      <hr />
    </div>

    <!-- 선택한 조건 게시물 목록 -->

    <div v-if="favoritePosts.length > 0" class="row">
      <div v-for="post in favoritePosts" :key="post" class="col-2 mb-4">
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
                <span
                  class="badge text-bg-warning bg-opacity-50 border mb-2 w-85"
                  >판매중</span
                >
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
                  @click="
                    goDetailPage(post.productIdx, post.price, post.postingDate)
                  "
                  class="badge text-bg-success bg-opacity-75 border mb-2 w-85"
                >
                  판매 페이지
                </button>
              </div>
            </div>
            <div v-if="post.transactionStatus == 'COMPLETED'">
              <div class="row justify-content-center">
                <span
                  class="badge text-bg-danger bg-opacity-75 border mb-2 w-85"
                  >판매완료</span
                >
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
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { instance } from '@/common/axios/axiosInstance';
import { addComma, changeTimeFormat } from '@/common/utils/utils';

const router = useRouter();
const isHover = ref({});
const favoritePosts = ref([]);

const setHover = (idx, value) => {
  isHover.value[idx] = value;
};
// 클릭시 페이지 이동 메서드 부분 ===================================================================================

const goDetailPage = (productIdx, price, postingDate) => {
  router.push({
    name: 'ProductDetail',
    state: { productIdx, price, postingDate },
  });
};

// 클릭시 페이지 이동 메서드 부분 종료 ==============================================================================

const fetchFavoriteList = async () => {
  try {
    const response = await instance.get('/user/product/favorite');
    return response.data;
  } catch (error) {
    console.error(error);
  }
};

// 페이지 onload 시 실행 매서드
onMounted(async () => {
  // 초기 화면 전체 판매 내역 불러오기
  favoritePosts.value = await fetchFavoriteList();

  // response data에서 product 만 추출하는 작업
  let posts = [];
  for (let i = 0; i < favoritePosts.value.length; i++) {
    posts[i] = favoritePosts.value[i].product;
  }
  favoritePosts.value = posts;

  // 경과 시간 값 처리하기
  favoritePosts.value.postingDate = changeTimeFormat(favoritePosts.value);

  // 가격에 , 추가하기
  favoritePosts.value.price = addComma(favoritePosts.value);

  console.log(favoritePosts.value);
});
</script>

<style lang="scss" scoped></style>
