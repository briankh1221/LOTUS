<template>
  <br /><br /><br /><br />
  <div>
    <div class="row justify-content-center">
      <div class="card p-0 border-0" style="width: 400px; height: 400px">
        <div class="card-body bg-body-tertiary">
          <div class="d-flex flex-column">
            <h5 class="text-center mb-5">LOTUS 회원가입</h5>

            <!-- 회원 정보 작성 부분 -->
            <input
              v-model="form.id"
              type="text"
              class="bg-body-tertiary border-0 mt-3 mb-0 ms-3"
              style="outline: none"
              placeholder="아이디"
            />
            <hr class="mt-1 ms-3 me-3" />
            <input
              v-model="form.password"
              type="password"
              class="bg-body-tertiary border-0 mt-1 ms-3"
              style="outline: none"
              placeholder="비밀번호"
            />
            <hr class="mt-1 ms-3 me-3" />
            <input
              v-model="form.name"
              type="text"
              class="bg-body-tertiary border-0 mt-1 ms-3"
              style="outline: none"
              placeholder="이름"
            />
            <hr class="mt-1 ms-3 me-3" />
            <input
              v-model="form.email"
              type="text"
              class="bg-body-tertiary border-0 mt-1 ms-3"
              style="outline: none"
              placeholder="이메일"
            />
            <hr class="mt-1 ms-3 me-3" />
            <input
              v-model="form.phone"
              type="text"
              class="bg-body-tertiary border-0 mt-1 ms-3"
              style="outline: none"
              placeholder="전화번호"
            />
            <hr class="mt-1 ms-3 me-3" />

            <button
              class="btn btn-secondary my-5 mb-1"
              @click.prevent="signupMethod"
            >
              회원가입
            </button>
          </div>
        </div>
      </div>
    </div>

    <div class="d-flex gap-2 mt-4">
      <slot name="actions"> </slot>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { formInstance } from '@/common/axios/axiosInstance';
import router from '@/router';

const form = ref({
  id: null,
  password: null,
  name: null,
  email: null,
  phone: null,
  image: new File([], 'image'),
});

const signupMethod = async () => {
  try {
    console.log(form.value);
    await formInstance.post('/auth/signup', {
      ...form.value,
    });

    alert('회원가입이 완료되었습니다');

    router.push({ name: 'Home' });
  } catch (error) {
    console.error(error);
  }
};
</script>

<style lang="scss" scoped></style>
\
