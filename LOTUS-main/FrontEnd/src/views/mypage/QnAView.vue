<template>
  <div class="container text-center"></div>
  <div class="row">
    <!-- 판매중 예약중 판매완료 헤더 -->
    <div class="container text-center mt-3 mb-2">
      <h2>질문 관리</h2>
      <hr />
    </div>
    <div class="row my-3">
      <div class="col-12 text-end pe-0">
        <!-- Button trigger modal -->
        <button
          type="button"
          class="btn btn-outline-secondary rounded-5 mx-1"
          data-bs-toggle="modal"
          data-bs-target="#staticBackdrop"
        >
          새로운 질문 작성
        </button>

        <!-- 작성 버튼 클릭시 발생하는 모달 부분 ============================== -->
        <div
          class="modal fade"
          id="staticBackdrop"
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
                  질문 작성
                </h1>
                <button
                  type="button"
                  class="btn-close"
                  data-bs-dismiss="modal"
                  aria-label="Close"
                ></button>
              </div>
              <div class="modal-body">
                <div class="row">
                  <div class="col-12">
                    <form>
                      <input
                        v-model="qnaForm.title"
                        class="form-control mb-3"
                        type="text"
                        placeholder="제목"
                      />
                      <textarea
                        v-model="qnaForm.content"
                        class="form-control"
                        style="height: 200px"
                        placeholder="내용 입력"
                      ></textarea>
                    </form>
                  </div>
                </div>
              </div>
              <div class="modal-footer">
                <button
                  type="button"
                  class="btn btn-secondary"
                  data-bs-dismiss="modal"
                >
                  닫기
                </button>
                <button
                  type="button"
                  class="btn btn-primary"
                  data-bs-dismiss="modal"
                  @click="writeQnAMethod"
                >
                  작성 완료
                </button>
              </div>
            </div>
          </div>
        </div>
        <!-- 작성 버튼 클릭시 발생하는 모달 부분 종료 ============================== -->
      </div>
    </div>

    <!-- 선택한 조건 게시물 목록 -->
    <div v-if="qnaList.length > 0">
      <table class="table table-hover text-center">
        <thead>
          <tr>
            <th style="width: 200px">제목</th>
            <th style="width: 700px">내용</th>
            <th style="width: 150px">작성일</th>
            <th style="width: 150px">답변학인</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="qna in qnaList" :key="qna">
            <td scope="row">{{ qna.shortTitle }}</td>
            <td scope="row">{{ qna.shortContent }}</td>
            <td scope="row">{{ qna.postingDate }}</td>
            <td v-if="qna.qnaReply" scope="row">
              <button
                type="button"
                class="btn btn-sm text-bg-success text-bold p-1"
                data-bs-toggle="modal"
                data-bs-target="#Reply"
                @click="replyBtn(qna)"
              >
                답변 확인
              </button>
            </td>
            <td v-else scope="row">
              <button
                type="button"
                class="btn btn-sm text-bg-warning text-bold p-1 bg-opacity-50"
                data-bs-toggle="modal"
                data-bs-target="#noAnswerReply"
                @click="replyBtn(qna)"
              >
                미답변
              </button>
            </td>
          </tr>
        </tbody>
      </table>

      <!-- 답변 확인 클릭시 등장하는 모달 부분 =================================-->
      <div
        class="modal fade"
        id="Reply"
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
                답변 확인
              </h1>
              <button
                type="button"
                class="btn-close"
                data-bs-dismiss="modal"
                aria-label="Close"
              ></button>
            </div>
            <div class="modal-body">
              <form v-if="selectedQna" class="justify-content-center">
                <span class="fs-5 ms-1" style="font-weight: 700">{{
                  selectedQna.title
                }}</span>
                <br />
                <hr />
                <span class="ms-1">{{ selectedQna.content }}</span>
                <br />
                <hr />
                <br /><br />
                <span
                  v-if="selectedQna.qnaReply"
                  class="fs-5 ms-1"
                  style="font-weight: 700"
                  >관리자({{ selectedQna.qnaReply.user.name }})의 답변</span
                >
                <hr />
                <span v-if="selectedQna.qnaReply" class="ms-1">{{
                  selectedQna.qnaReply.reply
                }}</span>
                <hr />
              </form>
            </div>
            <div class="modal-footer">
              <button
                type="button"
                class="btn btn-secondary"
                data-bs-dismiss="modal"
              >
                확인
              </button>
            </div>
          </div>
        </div>
      </div>
      <!-- 답변 확인 클릭시 등장하는 모달 부분 종료=================================-->

      <!-- 미답변 클릭시 등장하는 모달 부분 =================================-->
      <div
        class="modal fade"
        id="noAnswerReply"
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
                답변 확인
              </h1>
              <button
                type="button"
                class="btn-close"
                data-bs-dismiss="modal"
                aria-label="Close"
              ></button>
            </div>
            <div class="modal-body">
              <form v-if="selectedQna" class="justify-content-center">
                <span class="fs-5 ms-1" style="font-weight: 700">{{
                  selectedQna.title
                }}</span>
                <br />
                <hr />
                <span class="ms-1">{{ selectedQna.content }}</span>
                <br />
                <hr />
                <br /><br />
                <span class="fs-5 ms-1" style="font-weight: 700"
                  >미답변 상태 입니다</span
                >
              </form>
            </div>
            <div class="modal-footer">
              <button
                type="button"
                class="btn btn-secondary"
                data-bs-dismiss="modal"
              >
                확인
              </button>
            </div>
          </div>
        </div>
      </div>
      <!-- 미답변 클릭시 등장하는 모달 부분 종료=================================-->
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { formInstance } from '@/common/axios/axiosInstance';

import { titleLength, contentLength } from '@/common/utils/utils';

const qnaList = ref([]);
const selectedQna = ref();

const qnaForm = ref({
  title: null,
  content: null,
  images: new File([], 'images'),
});

const replyBtn = async qna => {
  selectedQna.value = qna;
  console.log(selectedQna.value);
};

const writeQnAMethod = async () => {
  try {
    await formInstance.post('/user/qna', { ...qnaForm.value });
    alert('질문 등록 완료');
  } catch (error) {
    console.error(error);
  }

  // 질문 작성 후 다시 질문 내역 가져오기
  qnaList.value = await fetchQna();

  for (let i = 0; i < qnaList.value.length; i++) {
    qnaList.value[i].postingDate = qnaList.value[i].postingDate.substring(
      0,
      10,
    );
  }
  // form 초기화
  qnaForm.value.title = null;
  qnaForm.value.content = null;
};

const fetchQna = async () => {
  try {
    const response = await formInstance.get('/user/qna');
    return response.data;
  } catch (error) {
    console.error(error);
  }
};

// 클릭시 페이지 이동 메서드 부분 ===================================================================================

// 클릭시 페이지 이동 메서드 부분 종료 ==============================================================================

onMounted(async () => {
  qnaList.value = await fetchQna();

  for (let i = 0; i < qnaList.value.length; i++) {
    qnaList.value[i].postingDate = qnaList.value[i].postingDate.substring(
      0,
      10,
    );
  }

  // 제목이 긴 경우 짧게 줄이기
  qnaList.value.shortTitle = titleLength(qnaList.value, 8);
  qnaList.value.shortContent = contentLength(qnaList.value, 30);
});
</script>

<style lang="scss" scoped></style>
