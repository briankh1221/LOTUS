<template>
  <br /><br />

  <!-- 광고 표시 부분 -->
  <div id="carouselExample" class="carousel slide">
    <div class="carousel-inner">
      <div class="carousel-item active">
        <img
          src="/images/tj_advertise_01.jpg"
          class="d-block mb-5"
          style="width: 100%; height: 400px"
        />
      </div>
      <div class="carousel-item">
        <img
          src="/images/tj_advertise_02.jpg"
          class="d-block w-100 mb-5"
          style="width: 100%; height: 400px"
        />
      </div>
      <div class="carousel-item">
        <img
          src="/images/tj_advertise_03.jpg"
          class="d-block w-100 mb-5"
          style="width: 100%; height: 400px"
        />
      </div>
    </div>
    <button
      class="carousel-control-prev"
      type="button"
      data-bs-target="#carouselExample"
      data-bs-slide="prev"
    >
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Previous</span>
    </button>
    <button
      class="carousel-control-next"
      type="button"
      data-bs-target="#carouselExample"
      data-bs-slide="next"
    >
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Next</span>
    </button>
  </div>

  <!-- 최고 인기 상품 표시 부분 -->
  <div>
    <h2 class="mb-4" style="font-weight: 600">최고 인기 상품</h2>

    <div v-if="bestPosts.length > 0" class="row">
      <div
        v-for="post in bestPosts"
        :key="post.productIdx"
        class="col-3 mb-4"
        @click="goDetailPage(post.productIdx, post.price, post.postingDate)"
      >
        <div
          class="card"
          :class="{
            'border-1': isHover[post.productIdx],
            'border-0': !isHover[post.productIdx],
            'mt-0': isHover[post.productIdx],
            'mt-1': !isHover[post.productIdx],
          }"
          style="width: 100%"
          @mouseover="setHover(post.productIdx, true)"
          @mouseleave="setHover(post.productIdx, false)"
        >
          <img
            :src="`/images/${post.images.split(' ')[0]}`"
            class="card-img-top"
            style="width: 100%; height: 250px; object-fit: cover"
          />
          <div class="card-body">
            <p class="card-title text-secondary text-truncate">
              {{ post.title }}
            </p>

            <!-- 판매중인 상품 가격 시간 표시 -->
            <div v-if="post.transactionStatus == 'ON_SALE'" class="row">
              <div class="col-8">
                <p class="card-text" style="font-weight: 800">
                  {{ post.price }} 원
                </p>
              </div>
              <div class="col-4 text-end">
                <p class="text-secondary mt-1" style="font-size: 12px">
                  {{ post.postingDate }}
                </p>
              </div>
            </div>

            <!-- 예약중인 상품 가격 시간 표시 -->
            <div
              v-if="post.transactionStatus == 'UNDER_RESERVATION'"
              class="row"
            >
              <div class="col-8 d-flex">
                <p class="card-text" style="font-weight: 800">
                  {{ post.price }} 원
                </p>
                <button
                  class="badge btn-sm text-bg-success bg-opacity-50 border py-0 px-2 w-85 ms-2"
                  style="height: 25px"
                >
                  예약중
                </button>
              </div>
              <div class="col-4 text-end">
                <p class="text-secondary mt-1" style="font-size: 12px">
                  {{ post.postingDate }}
                </p>
              </div>
            </div>

            <!-- 판매완료 상품 가격 시간 표시 -->
            <div v-if="post.transactionStatus == 'COMPLETED'" class="row">
              <div class="col-8 d-flex">
                <p class="card-text" style="font-weight: 800">
                  {{ post.price }} 원
                </p>
                <button
                  class="badge btn-sm text-bg-warning bg-opacity-25 border py-0 px-2 w-85 ms-2"
                  style="height: 25px"
                >
                  판매완료
                </button>
              </div>
              <div class="col-4 text-end">
                <p class="text-secondary mt-1" style="font-size: 12px">
                  {{ post.postingDate }}
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <br /><br /><br /><br />
  </div>

  <!-- 최신 상품 표시 부분 -->
  <div>
    <h2 class="mb-4" style="font-weight: 600">최신 상품</h2>

    <div v-if="recentPosts.length > 0" class="row">
      <div
        v-for="post in recentPosts"
        :key="post.productIdx"
        class="col-3 mb-4"
        @click="goDetailPage(post.productIdx, post.price, post.postingDate)"
      >
        <div
          class="card"
          :class="{
            'border-1': isHover[post.productIdx],
            'border-0': !isHover[post.productIdx],
            'mt-0': isHover[post.productIdx],
            'mt-1': !isHover[post.productIdx],
          }"
          style="width: 100%"
          @mouseover="setHover(post.productIdx, true)"
          @mouseleave="setHover(post.productIdx, false)"
        >
          <img
            :src="`/images/${post.images.split(' ')[0]}`"
            class="card-img-top"
            style="width: 100%; height: 250px; object-fit: cover"
          />
          <div class="card-body">
            <p class="card-title text-secondary text-truncate">
              {{ post.title }}
            </p>
            <!-- 판매중인 상품 가격 시간 표시 -->
            <div v-if="post.transactionStatus == 'ON_SALE'" class="row">
              <div class="col-8">
                <p class="card-text" style="font-weight: 800">
                  {{ post.price }} 원
                </p>
              </div>
              <div class="col-4 text-end">
                <p class="text-secondary mt-1" style="font-size: 12px">
                  {{ post.postingDate }}
                </p>
              </div>
            </div>

            <!-- 예약중인 상품 가격 시간 표시 -->
            <div
              v-if="post.transactionStatus == 'UNDER_RESERVATION'"
              class="row"
            >
              <div class="col-8 d-flex">
                <p class="card-text" style="font-weight: 800">
                  {{ post.price }} 원
                </p>
                <button
                  class="badge btn-sm text-bg-success bg-opacity-50 border py-0 px-2 w-85 ms-2"
                  style="height: 25px"
                >
                  예약중
                </button>
              </div>
              <div class="col-4 text-end">
                <p class="text-secondary mt-1" style="font-size: 12px">
                  {{ post.postingDate }}
                </p>
              </div>
            </div>

            <!-- 판매완료 상품 가격 시간 표시 -->
            <div v-if="post.transactionStatus == 'COMPLETED'" class="row">
              <div class="col-8 d-flex">
                <p class="card-text" style="font-weight: 800">
                  {{ post.price }} 원
                </p>
                <button
                  class="badge btn-sm text-bg-warning bg-opacity-25 border py-0 px-2 w-85 ms-2"
                  style="height: 25px"
                >
                  판매완료
                </button>
              </div>
              <div class="col-4 text-end">
                <p class="text-secondary mt-1" style="font-size: 12px">
                  {{ post.postingDate }}
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <br /><br /><br /><br />
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import { instance } from '@/common/axios/axiosInstance';
import { useRouter } from 'vue-router';
import { changeTimeFormat, addComma } from '@/common/utils/utils';

const router = useRouter();
const recentPosts = ref([]);
const bestPosts = ref([]);
const isHover = ref({});

const fetchRecent = async () => {
  try {
    const response = await instance.get('/product/recent-list');
    return response.data;
  } catch (error) {
    console.error(error);
  }
};

const fetchBest = async () => {
  try {
    const response = await instance.get('/product/best-list');
    return response.data;
  } catch (error) {
    console.error(error);
  }
};

const setHover = (idx, value) => {
  isHover.value[idx] = value;
};

const goDetailPage = (productIdx, price, postingDate) => {
  router.push({
    name: 'ProductDetail',
    state: { productIdx, price, postingDate },
  });
};

onMounted(async () => {
  // 최신 상품 목록 가져오기
  recentPosts.value = await fetchRecent();

  // 최신 상품 목록 가져오기
  bestPosts.value = await fetchBest();

  // 경과 시간 값 처리하기
  recentPosts.value.postingDate = changeTimeFormat(recentPosts.value);
  bestPosts.value.postingDate = changeTimeFormat(bestPosts.value);

  // 가격에 , 추가하기
  recentPosts.value.price = addComma(recentPosts.value);
  bestPosts.value.price = addComma(bestPosts.value);

  console.log(bestPosts.value);
  console.log(recentPosts.value);
});
</script>

<style lang="scss" scoped></style>
