<template>
  <div>
    <h2 class="text-center">관리자 QnA 관리</h2>
    <hr class="mb-2 mt-3" />
    <br />
    <div v-if="qnaList">
      <table class="table table-hover text-center">
        <thead>
          <tr>
            <th style="width: 50px">IDX</th>
            <th style="width: 200px">제목</th>
            <th style="width: 500px">내용</th>
            <th style="width: 150px">작성자ID</th>
            <th style="width: 150px">답변</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="qna in qnaList" :key="qna">
            <th style="width: 50px">{{ qna.qnaIdx }}</th>
            <td style="width: 200px">{{ qna.shortTitle }}</td>
            <td style="width: 500px">{{ qna.shortContent }}</td>
            <td style="width: 150px">{{ qna.user.id }}</td>
            <td v-if="qna.qnaReply" style="width: 150px">
              <button
                type="button"
                class="btn btn-sm text-bg-warning bg-opacity-50 text-bold"
                data-bs-toggle="modal"
                data-bs-target="#modifyReply"
                @click="answerBtn(qna)"
              >
                <p class="mb-0 text-black">답변 확인</p>
              </button>
            </td>
            <td v-else style="width: 150px">
              <button
                type="button"
                class="btn btn-sm text-bg-success bg-opacity-75 text-bold"
                data-bs-toggle="modal"
                data-bs-target="#writeReply"
                @click="answerBtn(qna)"
              >
                답변 작성
              </button>
            </td>
          </tr>
        </tbody>
      </table>

      <!-- 답변 작성 클릭시 등장하는 모달 부분 =================================-->
      <div
        class="modal fade"
        id="writeReply"
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
                답변 작성
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
                  v-if="selectedQnA"
                  class="fs-5 ms-1"
                  style="font-weight: 700"
                  >{{ selectedQnA.title }}</span
                >
                <br />
                <hr />
                <span v-if="selectedQnA" class="ms-1">{{
                  selectedQnA.content
                }}</span>
                <br /><br />
                <textarea
                  class="form-control"
                  cols="30"
                  rows="10"
                  placeholder="미답변 상태입니다 답변을 작성해 주세요"
                  v-model="replyForm.reply"
                >
                </textarea>
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
                class="btn btn-primary"
                data-bs-dismiss="modal"
                @click="writeReply(selectedQnA.qnaIdx)"
              >
                작성 완료
              </button>
            </div>
          </div>
        </div>
      </div>
      <!-- 답변 작성 클리시 등장하는 모달 부분 종료=================================-->

      <!-- 답변 확인 클릭시 등장하는 모달 부분 =================================-->
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
                답변 확인 및 수정
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
                  v-if="selectedQnA"
                  class="fs-5 ms-1"
                  style="font-weight: 700"
                  >{{ selectedQnA.title }}</span
                >
                <br />
                <hr />
                <span v-if="selectedQnA" class="ms-1">{{
                  selectedQnA.content
                }}</span>
                <br /><br />
                <textarea
                  v-if="selectedQnA"
                  class="form-control"
                  cols="30"
                  rows="10"
                  placeholder="답변 작성"
                  v-model="replyForm.reply"
                >
                </textarea>
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
                class="btn btn-primary"
                data-bs-dismiss="modal"
                @click="writeReply(selectedQnA.qnaIdx)"
              >
                답변 수정
              </button>
            </div>
          </div>
        </div>
      </div>
      <!-- 답변 확인 클리시 등장하는 모달 부분 종료=================================-->

      <!-- 페이징 처리 부분 -->

      <div class="btn-toolbar justify-content-center">
        <div class="btn-group me-2" role="group" aria-label="First group">
          <button
            v-if="pagingData.isPrevious"
            type="button"
            class="btn"
            @click="goPrevious"
          >
            &laquo;
          </button>
          <button
            v-for="n in pagingData.endPage - pagingData.startPage"
            :key="n"
            type="button"
            class="btn"
            :class="{ active: pagingData.selectedPage == n }"
            @click="goToPage(n)"
          >
            {{ n }}
          </button>
          <button
            v-if="pagingData.isNext"
            type="button"
            class="btn"
            @click="goNext"
          >
            &raquo;
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import { instance, formInstance } from '@/common/axios/axiosInstance';
import { titleLength, contentLength } from '@/common/utils/utils';

const qnaList = ref([]);

const selectedQnA = ref();

const replyForm = ref({ reply: null });

const pagingData = ref({
  selectedPage: 1,
  endPage: null,
  startPage: null,
  isNext: false,
  isPrevious: false,
});

const answerBtn = qna => {
  selectedQnA.value = qna;
  if (qna.qnaReply) {
    replyForm.value.reply = qna.qnaReply.reply;
  } else {
    replyForm.value.reply = '';
  }
};

const writeReply = async qnaIdx => {
  if (confirm('답변을 작성하시겠습니까?')) {
    try {
      await formInstance.post('/admin/qna/' + qnaIdx + '/qnareply', {
        ...replyForm.value,
      });
      replyForm.value.reply = '';
      console.log('답변 저장 완료');
    } catch (error) {
      console.error(error);
    }
  }
};

const fetchQnAs = async page => {
  try {
    const response = await instance.get('/admin/qna-list', {
      params: {
        page: page,
      },
    });
    return response.data;
  } catch (error) {
    console.error(error);
  }
};

onMounted(async () => {
  // 1페이지의 qna 리스트를 불러옴
  const { qnaPageDto, qnaDtoList } = await fetchQnAs(0);

  qnaList.value = qnaDtoList;
  pagingData.value.endPage = qnaPageDto.endPage;
  pagingData.value.startPage = qnaPageDto.startPage;
  if (pagingData.value.endPage >= 2) {
    pagingData.value.isNext = true;
  }

  // 제목이 긴 경우 짧게 줄이기
  qnaList.value.shortTitle = titleLength(qnaList.value, 8);
  qnaList.value.shortContent = contentLength(qnaList.value, 30);

  console.log(qnaList.value);
});
</script>

<style lang="scss" scoped></style>
