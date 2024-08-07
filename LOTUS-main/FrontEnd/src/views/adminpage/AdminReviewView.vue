<template>
  <div>
    <h2 class="text-center">관리자 리뷰 관리</h2>
    <hr class="mb-2 mt-3" />
    <br />
    <div v-if="reviewList">
      <table class="table table-hover text-center">
        <thead>
          <tr>
            <th style="width: 50px">IDX</th>
            <th style="width: 200px">상품IDX</th>
            <th style="width: 500px">내용</th>
            <th style="width: 200px" class="text-center">작성자ID</th>
            <th style="width: 200px" class="text-center">판매/구매</th>
            <th style="width: 200px" class="text-center">별점</th>
            <th style="width: 200px" class="text-center">삭제</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="review in reviewList" :key="review">
            <th style="width: 50px">{{ review.reviewIdx }}</th>
            <td style="width: 200px">{{ review.product.productIdx }}</td>
            <td style="width: 700px">{{ review.content }}</td>
            <td style="width: 200px">{{ review.user.idx }}</td>
            <td
              v-if="review.product.user.idx == review.user.idx"
              style="width: 200px"
            >
              판매자
            </td>
            <td v-else style="width: 200px">구매자</td>
            <td style="width: 200px">{{ review.evaluation }}</td>
            <td style="width: 200px" class="py-1">
              <button
                type="button"
                class="btn btn-sm text-bg-danger text-bold p-1"
                data-bs-toggle="modal"
                data-bs-target="#modifyReply"
                @click="deleteBtn(review)"
              >
                삭제
              </button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <!-- 삭제 버튼 클릭시 등장하는 모달 부분 =================================-->
    <div
      class="modal fade"
      id="modifyReply"
      data-bs-backdrop="static"
      data-bs-keyboard="false"
      tabindex="-1"
      aria-labelledby="staticBackdropLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h1 class="modal-title fs-5" id="staticBackdropLabel">
              리뷰 삭제하기
            </h1>
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close"
            ></button>
          </div>
          <div class="modal-body">
            <form class="justify-content-center">
              <span
                v-if="selectedReview"
                class="fs-5 ms-1"
                style="font-weight: 700"
                >{{ selectedReview.title }}</span
              >
              <br />
              <hr />
              <span v-if="selectedReview" class="ms-1">{{
                selectedReview.content
              }}</span>
              <br /><br />
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
              class="btn btn-danger"
              data-bs-dismiss="modal"
              @click="deleteReply"
            >
              삭제하기
            </button>
          </div>
        </div>
      </div>
    </div>
    <!-- 삭제 버튼 클리시 등장하는 모달 부분 종료=================================-->
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import { instance } from '@/common/axios/axiosInstance';

const reviewList = ref([]);
const selectedReview = ref();

const deleteBtn = review => {
  selectedReview.value = review;
};

const deleteReply = async () => {
  if (confirm('리뷰를 삭제하시겠습니까?')) {
    try {
      await instance.delete('/admin/review/' + selectedReview.value.reviewIdx);
      alert('리뷰 삭제 완료');
      reviewList.value = await fetchReviews();
    } catch (error) {
      console.error(error);
    }
  }
};

const fetchReviews = async () => {
  try {
    const response = await instance.get('/admin/review-list');
    return response.data.reviewDtoList;
  } catch (error) {
    console.error(error);
  }
};

onMounted(async () => {
  reviewList.value = await fetchReviews();
  console.log(reviewList.value);
});
</script>

<style lang="scss" scoped></style>
