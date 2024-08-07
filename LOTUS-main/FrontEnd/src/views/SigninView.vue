<template>
  <br /><br /><br /><br />
  <div class="row justify-content-center">
    <div class="card p-0 border-0" style="width: 400px; height: 400px">
      <div class="card-body bg-body-tertiary">
        <div class="d-flex flex-column">
          <h5 class="text-center mb-5">LOTUS 로그인</h5>
          <input
            v-model="form.id"
            type="text"
            class="mt-3 mb-0 ms-3 bg-body-tertiary"
            style="
              outline: none;
              border-top: none;
              border-left: none;
              border-right: none;
              border-bottom: 20rem;
            "
            placeholder="아이디"
          />
          <hr class="mt-1 ms-3 me-3" />
          <input
            v-model="form.password"
            type="password"
            class="bg-body-tertiary border-0 mt-3 ms-3"
            style="outline: none"
            placeholder="비밀번호"
          />
          <hr class="mt-1 ms-3 me-3 mb-5" />
          <button
            class="btn btn-secondary mt-3 mb-1"
            @click.prevent="signinMethod"
          >
            로그인
          </button>
        </div>
        <hr />
        <RouterLink to="/signup">
          <div class="d-flex flex-column">
            <button class="btn btn-secondary mt-1">회원가입</button>
          </div>
        </RouterLink>
      </div>
    </div>
  </div>
</template>

<script setup>
import { instance } from '@/common/axios/axiosInstance';
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import useRoleStore from '@/common/store/roleStore';
import { getRole } from '@/common/utils/utils';

const router = useRouter();
const roleStore = useRoleStore();

const form = ref({
  id: null,
  password: null,
});

const signinMethod = async () => {
  try {
    if (sessionStorage.getItem('accesToken')) {
      sessionStorage.removeItem('accessToken');
    }
    const response = await instance.post('/auth/signin', { ...form.value });

    roleStore.role = getRole(response.data.tokenDto.accessToken);

    alert('로그인 성공!!');
    if (roleStore.role == 'ROLE_ADMIN') {
      router.push({ name: 'AdminMain' });
    } else {
      router.push({ name: 'Home' });
    }
  } catch (error) {
    console.error(error);
    alert('로그인 실패!!');
  }
};
</script>

<style lang="scss" scoped></style>
