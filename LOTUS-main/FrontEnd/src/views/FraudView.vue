<template>
  <div class="container text-center">
    <h2 class="justify-items-center mb-5">사기 조회</h2>
  </div>
  <hr />
  <form>
    <div class="row">
      <!-- 안내 문구 헤더 -->
      <div class="row">
        <div class="col-4">
          <div class="container">
            <p>
              판매자의 휴대폰, 계좌번호, 메신저 ID, 이메일로 피해 사례 조회를
              이용해 보세요!
            </p>
          </div>
        </div>
        <div class="col-8"></div>
      </div>
      <div class="col-12">
        <select class="form-select mb-2" aria-label="Default select example">
          <option selected>사기 조회 기준</option>
          <option value="1" selected>휴대폰 번호</option>
          <option value="2">계좌번호</option>
          <option value="3">이름</option>
        </select>
        <div class="row">
          <div class="col-10">
            <input
              type="text"
              class="form-control"
              placeholder="하이픈 없이 숫자만 입력"
            />
          </div>
          <div class="col-2 justify-content-end">
            <button class="btn btn-secondary w-100">검색</button>
          </div>
        </div>
      </div>
    </div>
  </form>
</template>

<script setup></script>

<style lang="scss" scoped></style>
