<template>
  <p class="justify-items-center fs-4 mb-0">관리자 메뉴</p>
  <hr class="w-75 mt-0 mb-3" />
  <p class="btn justify-items-center fs-6 my-0 me-5 p-1" @click="goAdminMain">
    관리자 메인
  </p>
  <p
    class="btn justify-items-center fs-6 my-0 me-5 p-1"
    @click="goAdminProduct"
  >
    상품 관리
  </p>
  <p class="btn justify-items-center fs-6 my-0 me-5 p-1" @click="goAdminQna">
    QnA 관리
  </p>
  <p class="btn justify-items-center fs-6 my-0 me-5 p-1" @click="goAdminReview">
    리뷰 관리
  </p>
</template>

<script setup>
import { useRouter } from 'vue-router';

const router = useRouter();
// 클릭시 페이지 이동 메서드 부분 ===================================================================================
const goAdminMain = () => {
  router.push({ name: 'AdminMain' });
};
const goAdminProduct = () => {
  router.push({ name: 'AdminProduct' });
};
const goAdminQna = () => {
  router.push({ name: 'AdminQnA' });
};
const goAdminReview = () => {
  router.push({ name: 'AdminReview' });
};

// 클릭시 페이지 이동 메서드 부분 종료 ==============================================================================
</script>

<style lang="scss" scoped></style>
