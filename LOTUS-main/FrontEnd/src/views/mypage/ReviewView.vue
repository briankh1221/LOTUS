<template>
  <div class="container text-center"></div>
  <div class="row">
    <!-- 판매중 예약중 판매완료 헤더 -->
    <div class="container text-center mt-3 mb-5">
      <h2>후기 관리</h2>

      <hr />
    </div>
    <!-- <div class="row my-3">
      <div class="col-3">
        <p class="ms-3 my-0">{{ reviews.length }} 개의 후기</p>
      </div>
      <div class="col-9 text-end">
        <button class="btn btn-outline-secondary rounded-5 mx-1 active">
          전체
        </button>
        <button class="btn btn-outline-secondary rounded-5 mx-1">
          받은후기
        </button>
        <button class="btn btn-outline-secondary rounded-5 mx-1">쓴후기</button>
      </div>
    </div> -->

    <!-- 선택한 조건 게시물 목록 -->
    <div v-if="reviews.length > 0" class="row mx-2 mb-3">
      <div
        v-for="review in reviews"
        :key="review"
        class="col-12 bg-light rounded-1 mt-2"
      >
        <div class="row">
          <div class="col-1 p-1">
            <img
              :src="`/images/${review.product.images.split(' ')[0]}`"
              class="rounded"
              style="width: 60px; height: 60px"
            />
          </div>
          <div class="col-2 pe-0">
            <span
              v-if="review.product.user.email == getEmail()"
              class="badge text-bg-warn bg-opacity-50 mt-2"
              >판매한 물건</span
            >
            <span v-else class="badge bg-success bg-opacity-50 mt-2"
              >구매한 물건</span
            >
            <p class="my-1 text-truncate">{{ review.product.title }}</p>
          </div>

          <div class="vr p-0 mt-2" style="width: 1px; height: 55px"></div>

          <div class="col-8">
            <span
              v-if="review.user.email == getEmail()"
              class="badge text-bg-info bg-opacity-25 mt-2"
              >쓴 후기</span
            >
            <span v-else class="badge text-bg-secondary bg-opacity-50 mt-2"
              >받은 후기</span
            >
            <p class="my-1">
              {{ review.content }}
            </p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';

import { instance } from '@/common/axios/axiosInstance';

import { getEmail } from '@/common/utils/utils';

const reviews = ref([]);

// 클릭시 페이지 이동 메서드 부분 ===================================================================================

// 클릭시 페이지 이동 메서드 부분 종료 ==============================================================================

// 모든 리뷰 일단 불러오는 매서드
const fetchReview = async () => {
  try {
    const response = await instance.get('/user/buyer-review-list');
    return response.data;
  } catch (error) {
    console.error(error);
  }
};

// 페이지 onload 시 실행 매서드
onMounted(async () => {
  // 초기 화면 전체 판매 내역 불러오기
  reviews.value = await fetchReview();

  console.log(reviews.value);
});
</script>

<style lang="scss" scoped></style>
