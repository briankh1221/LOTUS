<template>
  <br /><br />

  <!-- 카테고리별 상품 표시 부분 -->
  <div>
    <div class="row">
      <div class="col-3">
        <h2>카테고리별 상품</h2>
      </div>
      <div class="col-4">
        <select
          v-model="selectedCateg"
          class="form-select"
          aria-label="Default select example"
          @change="categChangeMethod"
        >
          <option selected>카테고리 선택</option>
          <option value="LUXURY_GOODS">럭셔리/명품</option>
          <option value="FASHION_CLOTHES">패션/의류</option>
          <option value="FASHION_GOODS">패션/잡화</option>
          <option value="BEAUTY">뷰티</option>
          <option value="INFANTRY_GOODS">유아 상품</option>
          <option value="MOBILE_TABLET">모바일 태블릿</option>
          <option value="HOME_APPLIANCE">가전 제품</option>
          <option value="NOTEBOOK_PC">노트북, PC</option>
          <option value="CAMERA">카메라, 캠코더</option>
          <option value="FURNITURE">가구, 인테리어</option>
          <option value="HOUSEHOLD_GOODS">리빙, 생활</option>
          <option value="GAME">게임</option>
          <option value="PETS_HOBBIES">반려생활, 취미</option>
          <option value="BOOKS_RECORD_WRITING_GOODS">도서, 음반, 문구</option>
          <option value="TICKET_COUPON">티켓, 쿠폰</option>
          <option value="SPORTS_GOODS">스포츠</option>
          <option value="LEISURE_TRAVEL_ITEMS">레저, 여행</option>
          <option value="INDUSTRIAL_GOODS">공구, 산업 용품</option>
          <option value="SHARING">무료 나눔</option>
          <option value="NFT">NFT</option>
          <option value="USED_CARS">중고차</option>
        </select>
      </div>
    </div>
    <hr class="my-4" />

    <div v-if="categoryPosts" class="row">
      <div
        v-for="post in categoryPosts"
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
import { onMounted, ref, watch } from 'vue';
import { instance } from '@/common/axios/axiosInstance';
import { useRouter } from 'vue-router';
import { changeTimeFormat, addComma } from '@/common/utils/utils';
// import { storeToRefs } from 'pinia';
import useCategoryStore from '@/common/store/categoryStore';

const categoryStore = useCategoryStore();

const selectedCateg = ref('');
selectedCateg.value = categoryStore.category;

const router = useRouter();

const categoryPosts = ref([]);
const isHover = ref({});

const setHover = (idx, value) => {
  isHover.value[idx] = value;
};

const categChangeMethod = () => {
  categoryStore.setCategory(selectedCateg.value);
};

const fetchCategory = async category => {
  try {
    const response = await instance.get('/product/category-list/' + category);

    return response.data;
  } catch (error) {
    console.error(error);
  }
};

const goDetailPage = (productIdx, price, postingDate) => {
  router.push({
    name: 'ProductDetail',
    state: { productIdx, price, postingDate },
  });
};

onMounted(async () => {
  const categoryStore = useCategoryStore();

  // 카테고리별 상품 목록 가져오기
  categoryPosts.value = await fetchCategory(categoryStore.category);

  // 경과 시간 값 처리하기
  categoryPosts.value.postingDate = changeTimeFormat(categoryPosts.value);

  // 가격에 , 추가하기
  categoryPosts.value.price = addComma(categoryPosts.value);
});

// 카테고리가 변경될 때마다 호출되는 watch 함수
watch(
  () => categoryStore.category,
  async newValue => {
    // 새로운 카테고리 값으로 상품 목록을 다시 가져옴
    categoryPosts.value = await fetchCategory(newValue);

    // 경과 시간 값 처리하기
    categoryPosts.value.postingDate = changeTimeFormat(categoryPosts.value);

    // 가격에 , 추가하기
    categoryPosts.value.price = addComma(categoryPosts.value);

    // select 선택 값 변경하기
    selectedCateg.value = categoryStore.category;
  },
);
</script>

<style lang="scss" scoped></style>
