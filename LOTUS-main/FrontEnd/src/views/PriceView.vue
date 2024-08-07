<template>
  <div
    class="container text-center justify-content-center d-flex flex-column align-items-center"
  >
    <h2 class="">타 사이트 시세 조회</h2>
    <h6 class="text-secondary mb-5">
      구매를 원하는 상품의 시세를 검색해 보세요
    </h6>
    <div class="form-control d-flex justify-content-center border-0">
      <input
        class="form-control me-3"
        type="text"
        placeholder="상품 이름, 제목, 내용으로 검색"
        style="width: 500px"
        v-model="keyword"
        @keyup.enter="searchPrice(keyword)"
      />
      <button
        class="btn btn-sm btn-success opacity-50"
        @click="searchPrice(keyword)"
      >
        <i class="bi bi-search px-2"></i>
      </button>
    </div>
  </div>

  <div class="col-12 mt-5">
    <h3 class="ms-4" style="font-weight: 600">번개장터</h3>
    <hr />

    <!-- spinner 부분 -->
    <div v-if="isSpinnerShown" class="text-center mt-5">
      <br /><br /><br />
      <div
        class="spinner-border"
        style="width: 5rem; height: 5rem"
        role="status"
      >
        <span class="visually-hidden">Loading...</span>
      </div>
    </div>
    <!-- spinner 부분 -->
  </div>

  <div v-if="bunJangposts.length > 10" class="row">
    <div v-for="post in bunJangposts" :key="post.title" class="col-4">
      <div class="card mb-3 border-0" style="max-width: 540px">
        <div class="row g-0">
          <div class="col-md-4">
            <img
              :src="post.image"
              class="img-fluidd-block rounded-3"
              style="height: 100px; width: 100px; object-fit: cover"
            />
          </div>
          <div class="col-md-8">
            <div class="card-body">
              <p class="card-text text-truncate">{{ post.title }}</p>
              <div class="row">
                <div class="col-6">
                  <p
                    class="card-text"
                    style="font-weight: 600; font-size: larger"
                  >
                    {{ post.price }} 원
                  </p>
                </div>
                <div class="col-6 text-end">
                  <p
                    class="mb-0 mt-2"
                    style="color: darkgrey; font-size: small"
                  >
                    {{ post.postingDate }}
                  </p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { formInstance } from '@/common/axios/axiosInstance';
import { addComma } from '@/common/utils/utils';

const keyword = ref('');
const searchResult = ref([]);
const bunJangposts = ref([]);
const isSpinnerShown = ref(false);

const postForm = ref({
  title: null,
  price: null,
  postingDate: null,
  image: null,
  status: null,
});

const fetchPriceList = async keyword => {
  try {
    const response = await formInstance.get('/python/price-list', {
      params: { keyword: keyword },
    });
    return response.data;
  } catch (error) {
    console.error(error);
  }
};

const searchPrice = async keyword => {
  bunJangposts.value = [];
  isSpinnerShown.value = true;
  searchResult.value = await fetchPriceList(keyword);

  for (const result of searchResult.value) {
    if (result != '[]') {
      postForm.value.title = result.split("'")[1];
      postForm.value.price = result.split("'")[2];
      postForm.value.price = postForm.value.price.replace(/[,\s]/g, '');
      postForm.value.postingDate = result.split("'")[3];
      postForm.value.image = result.split("'")[5];
      postForm.value.status = result.split("'")[7];

      const postValue = { ...postForm.value };

      bunJangposts.value.push(postValue);
      console.log(bunJangposts.value);
    }
  }

  // 가격에 , 추가하기
  bunJangposts.value.price = addComma(bunJangposts.value);
  isSpinnerShown.value = false;
};
</script>

<style lang="scss" scoped></style>
