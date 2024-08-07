<template>
  <br /><br />

  <!-- 검색 상품 표시 부분 -->
  <div>
    <h2 class="mb-4" style="font-weight: 600">검색 상품</h2>

    <div v-if="searchedPosts.length > 0" class="row">
      <div
        v-for="post in searchedPosts"
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
    <div v-else><p>검색 내역에 해당하는 상품이 존재하지 않습니다</p></div>
    <br /><br /><br /><br />
  </div>
</template>

<script setup>
import { onMounted, ref, watch } from 'vue';
import { instance } from '@/common/axios/axiosInstance';
import { useRouter } from 'vue-router';
import { changeTimeFormat, addComma } from '@/common/utils/utils';
import useSearchStore from '@/common/store/searchStore';

const router = useRouter();

const searchStore = useSearchStore();

const pageForm = ref({
  keyword: null,
  page: 1,
});
const searchedPosts = ref([]);

const isHover = ref({});

const getKeywordPage = async () => {
  const { keyword, page } = history.state;
  pageForm.value.keyword = keyword;
  pageForm.value.page = page;
};

const fetchSearch = async pageForm => {
  try {
    console.log(pageForm.keyword, pageForm.page);
    const response = await instance.get('/product/product-list', {
      params: {
        keyword: pageForm.keyword,
        page: pageForm.page,
      },
    });
    return response.data.productDtoList;
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
  await getKeywordPage();
  // 검색 상품 목록 가져오기
  searchedPosts.value = await fetchSearch(pageForm.value);
  console.log(searchedPosts.value);

  // 경과 시간 값 처리하기
  searchedPosts.value.postingDate = changeTimeFormat(searchedPosts.value);

  // 가격에 , 추가하기
  searchedPosts.value.price = addComma(searchedPosts.value);
});

watch(
  () => searchStore.searchWord,
  async newValue => {
    if (newValue != '') {
      // 새로운 검색어로 상품 목록을 다시 가져옴
      pageForm.value.keyword = newValue;
      searchedPosts.value = await fetchSearch(pageForm.value);

      // 경과 시간 값 처리하기
      searchedPosts.value.postingDate = changeTimeFormat(searchedPosts.value);

      // 가격에 , 추가하기
      searchedPosts.value.price = addComma(searchedPosts.value);
    }
  },
);
</script>

<style lang="scss" scoped></style>
