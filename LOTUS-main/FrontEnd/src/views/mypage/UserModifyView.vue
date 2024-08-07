<template>
  <div class="container text-center"></div>
  <div class="row">
    <br /><br /><br />
    <div class="row justify-content-center">
      <div class="card p-0 border-0" style="width: 500px; height: 400px">
        <div class="card-body bg-body-tertiary">
          <div class="d-flex flex-column">
            <h5 class="text-center mb-5">회원 정보 수정</h5>
            <div v-if="form" class="row">
              <div class="col-3 h-50">
                <p class="mb-0 ms-4">ID</p>
              </div>
              <div class="col-9 h-50">
                <input
                  type="text"
                  class="bg-body-tertiary border-0 mb-0 ms-0"
                  style="outline: none"
                  :placeholder="`아이디 변경 불가(${form.id})`"
                  disabled
                />
                <hr class="mt-1 me-3" />
              </div>
            </div>
            <div class="row">
              <div class="col-3 h-50">
                <p class="mb-0 ms-4">비밀번호</p>
              </div>
              <div class="col-9 h-50">
                <input
                  v-model="newPassword"
                  type="text"
                  class="bg-body-tertiary border-0 mb-0 ms-0"
                  style="outline: none"
                  placeholder="변경할 비밀번호 입력"
                />
                <hr class="mt-1 me-3" />
              </div>
            </div>
            <div class="row">
              <div class="col-3 h-50">
                <p class="mb-0 ms-4">이름</p>
              </div>
              <div class="col-9 h-50">
                <input
                  v-model="form.name"
                  type="text"
                  class="bg-body-tertiary border-0 mb-0 ms-0"
                  style="outline: none"
                  placeholder="이름"
                />
                <hr class="mt-1 me-3" />
              </div>
            </div>
            <div class="row">
              <div class="col-3 h-50">
                <p class="mb-0 ms-4">이메일</p>
              </div>
              <div class="col-9 h-50">
                <input
                  v-model="form.email"
                  type="text"
                  class="bg-body-tertiary border-0 mb-0 ms-0"
                  style="outline: none; width: 300px"
                  :placeholder="`이메일 변경 불가(${form.email})`"
                  disabled
                />
                <hr class="mt-1 me-3" />
              </div>
            </div>
            <div class="row">
              <div class="col-3 h-50">
                <p class="mb-0 ms-4">전화번호</p>
              </div>
              <div class="col-9 h-50">
                <input
                  v-model="form.phone"
                  type="text"
                  class="bg-body-tertiary border-0 mb-0 ms-0"
                  style="outline: none"
                  placeholder="전화번호"
                />
                <hr class="mt-1 me-3" />
              </div>
            </div>

            <button class="btn btn-secondary my-5 mb-1" @click="modifyMethod">
              정보 수정
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { formInstance, instance } from '@/common/axios/axiosInstance';

const router = useRouter();

const form = ref({
  id: null,
  password: null,
  name: null,
  email: null,
  phone: null,
  image: new File([], 'image'),
});
const newPassword = ref();

const fetchMyData = async () => {
  try {
    const response = await instance.get('/user/details');
    return response.data;
  } catch (error) {
    console.error(error);
  }
};

const modifyMethod = async () => {
  try {
    form.value.image = new File([], 'image');
    if (newPassword.value) {
      form.value.password = newPassword.value;
    }
    console.log(form.value);
    await formInstance.put('/user/details', {
      ...form.value,
    });

    alert('정보 수정이 완료 되었습니다');
    router.push({ name: 'MyView' });
  } catch (error) {
    console.error(error);
  }
};

// 페이지 onload 시 실행 매서드
onMounted(async () => {
  // 초기 화면 전체 판매 내역 불러오기
  form.value = await fetchMyData();
  console.log(form.value);
});
</script>

<style lang="scss" scoped></style>
