<template>
  <nav class="nav">
    <div class="btn-group">
      <button
        type="button"
        class="btn btn-secondary dropdown-toggle"
        data-bs-toggle="dropdown"
        data-bs-display="static"
        aria-expanded="false"
      >
        카테고리
      </button>
      <ul class="dropdown-menu dropdown-menu-end dropdown-menu-lg-start">
        <li>
          <button
            class="dropdown-item"
            type="button"
            @click="goCategPage('LUXURY_GOODS')"
          >
            럭셔리/명품
          </button>
        </li>
        <li>
          <button
            class="dropdown-item"
            type="button"
            @click="goCategPage('FASHION_CLOTHES')"
          >
            패션/의류
          </button>
        </li>
        <li>
          <button
            class="dropdown-item"
            type="button"
            @click="goCategPage('FASHION_GOODS')"
          >
            패션/잡화
          </button>
        </li>
        <li>
          <button
            class="dropdown-item"
            type="button"
            @click="goCategPage('BEAUTY')"
          >
            뷰티
          </button>
        </li>
        <li>
          <button
            class="dropdown-item"
            type="button"
            @click="goCategPage('INFANTRY_GOODS')"
          >
            유아 상품
          </button>
        </li>
        <li>
          <button
            class="dropdown-item"
            type="button"
            @click="goCategPage('MOBILE_TABLET')"
          >
            모바일 태블릿
          </button>
        </li>
        <li>
          <button
            class="dropdown-item"
            type="button"
            @click="goCategPage('HOME_APPLIANCE')"
          >
            가전 제품
          </button>
        </li>
        <li>
          <button
            class="dropdown-item"
            type="button"
            @click="goCategPage('NOTEBOOK_PC')"
          >
            노트북, PC
          </button>
        </li>
        <li>
          <button
            class="dropdown-item"
            type="button"
            @click="goCategPage('CAMERA')"
          >
            카메라, 캠코더
          </button>
        </li>
        <li>
          <button
            class="dropdown-item"
            type="button"
            @click="goCategPage('FURNITURE')"
          >
            가구, 인테리어
          </button>
        </li>
        <li>
          <button
            class="dropdown-item"
            type="button"
            @click="goCategPage('HOUSEHOLD_GOODS')"
          >
            리빙, 생활
          </button>
        </li>
        <li>
          <button
            class="dropdown-item"
            type="button"
            @click="goCategPage('GAME')"
          >
            게임
          </button>
        </li>
        <li>
          <button
            class="dropdown-item"
            type="button"
            @click="goCategPage('PETS_HOBBIES')"
          >
            반려생활, 취미
          </button>
        </li>
        <li>
          <button
            class="dropdown-item"
            type="button"
            @click="goCategPage('BOOKS_RECORD_WRITING_GOODS')"
          >
            도서, 음반, 문구
          </button>
        </li>
        <li>
          <button
            class="dropdown-item"
            type="button"
            @click="goCategPage('TICKET_COUPON')"
          >
            티켓, 쿠폰
          </button>
        </li>
        <li>
          <button
            class="dropdown-item"
            type="button"
            @click="goCategPage('SPORTS_GOODS')"
          >
            스포츠
          </button>
        </li>
        <li>
          <button
            class="dropdown-item"
            type="button"
            @click="goCategPage('LEISURE_TRAVEL_ITEMS')"
          >
            레저, 여행
          </button>
        </li>
        <li>
          <button
            class="dropdown-item"
            type="button"
            @click="goCategPage('INDUSTRIAL_GOODS')"
          >
            공구, 산업 용품
          </button>
        </li>
        <li>
          <button
            class="dropdown-item"
            type="button"
            @click="goCategPage('SHARING')"
          >
            무료 나눔
          </button>
        </li>
        <li>
          <button
            class="dropdown-item"
            type="button"
            @click="goCategPage('NFT')"
          >
            NFT
          </button>
        </li>
        <li>
          <button
            class="dropdown-item"
            type="button"
            @click="goCategPage('USED_CARS')"
          >
            중고차
          </button>
        </li>
      </ul>
    </div>

    <RouterLink class="nav-link text-secondary" to="/fraud"
      ><p class="mb-0" style="font-weight: 600">사기조회</p></RouterLink
    >
    <RouterLink class="nav-link text-secondary" to="/price"
      ><p class="mb-0" style="font-weight: 600">시세조회</p></RouterLink
    >
  </nav>
</template>

<script setup>
import { useRouter } from 'vue-router';
import useCategoryStore from '@/common/store/categoryStore';

const categoryStore = useCategoryStore();
const router = useRouter();

const goCategPage = category => {
  categoryStore.setCategory(category);

  router.push({
    name: 'CategoryView',
  });
};
</script>

<style lang="scss" scoped></style>
