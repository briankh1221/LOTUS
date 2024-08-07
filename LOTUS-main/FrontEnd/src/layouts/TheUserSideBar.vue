<template>
  <p class="justify-items-center fs-4">마이 페이지</p>
  <p class="justify-items-center fs-5 mb-1">쇼핑 정보</p>
  <hr class="w-75 mt-0" />
  <p class="btn justify-items-center fs-6 my-0 me-5 p-1" @click="goSaleList">
    판매 내역
  </p>
  <p
    class="btn justify-items-center fs-6 my-0 me-5 p-1"
    @click="goPurchaseList"
  >
    구매 내역
  </p>
  <p
    class="btn justify-items-center fs-6 my-0 me-5 p-1"
    @click="goFavoriteList"
  >
    찜한 상품
  </p>

  <p class="justify-items-center fs-5 mb-1 mt-3">개인 정보</p>
  <hr class="w-75 mt-0" />
  <p class="btn justify-items-center fs-6 my-0 me-5 p-1" @click="goUserModify">
    정보 수정
  </p>
  <p class="btn justify-items-center fs-6 my-0 me-5 p-1" @click="goReviewList">
    후기 관리
  </p>
  <p class="btn justify-items-center fs-6 my-0 me-5 p-1" @click="goQnAList">
    질문 관리
  </p>
</template>

<script setup>
import { useRouter } from 'vue-router';

const router = useRouter();
// 클릭시 페이지 이동 메서드 부분 ===================================================================================
const goSaleList = () => {
  router.push({ name: 'SaleListView' });
};
const goPurchaseList = () => {
  router.push({ name: 'PurchaseListView' });
};
const goFavoriteList = () => {
  router.push({ name: 'FavoriteListView' });
};
const goUserModify = () => {
  router.push({ name: 'UserModifyView' });
};
const goReviewList = () => {
  router.push({ name: 'ReviewView' });
};
const goQnAList = () => {
  router.push({ name: 'QnAView' });
};
const goDetailPage = () => {};

// 클릭시 페이지 이동 메서드 부분 종료 ==============================================================================
</script>

<style lang="scss" scoped></style>
