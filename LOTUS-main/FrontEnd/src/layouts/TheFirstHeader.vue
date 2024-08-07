<template>
  <nav class="navbar navbar-expand-lg bg-white">
    <div class="container-fluid ps-0">
      <RouterLink class="nav-link active" to="/">
        <h3>
          <img
            class="mt-2 mb-0"
            src="/images/logoImg.jpg"
            style="width: 150px; height: 70px"
          />
        </h3>
      </RouterLink>
      <button
        class="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="d-flex" id="navbarSupportedContent">
        <input
          class="form-control me-3"
          type="text"
          placeholder="상품 이름, 제목, 내용으로 검색"
          style="width: 500px"
          v-model="pageForm.keyword"
          @keyup.enter="searchMethod"
        />
      </div>
      <ul class="navbar-nav justify-content-end">
        <li class="nav-item">
          <RouterLink class="nav-link active" to="/chating"
            ><i class="bi bi-chat-dots"></i> 채팅</RouterLink
          >
        </li>
        <li class="nav-item">
          <RouterLink class="nav-link active" to="/product/create"
            ><i class="bi bi-bag me-1"></i>상품 등록</RouterLink
          >
        </li>
        <li v-if="roleStore.getRole.role === 'ROLE_ADMIN'" class="nav-item">
          <RouterLink class="nav-link active" to="/admin/main"
            ><i class="bi bi-person-fill-exclamation me-1"></i
            >관리자</RouterLink
          >
        </li>
        <li v-if="roleStore.getRole.role === 'NONE'" class="nav-item">
          <RouterLink class="nav-link active" to="/signin"
            ><i class="bi bi-door-open me-1"></i>로그인/회원가입</RouterLink
          >
        </li>
        <li v-if="roleStore.getRole.role != 'NONE'" class="nav-item">
          <RouterLink class="nav-link active" to="/" @click="logout"
            ><i class="bi bi-door-closed me-1"></i>로그아웃</RouterLink
          >
        </li>
        <li v-if="roleStore.getRole.role === 'ROLE_USER'" class="nav-item">
          <RouterLink class="nav-link active" to="/user"
            ><i class="bi bi-person me-1"></i>My</RouterLink
          >
        </li>
      </ul>
    </div>
  </nav>
</template>

<script setup>
import { storeToRefs } from 'pinia';
import { useRouter } from 'vue-router';
import useRoleStore from '@/common/store/roleStore';
import useSearchStore from '@/common/store/searchStore';
import { ref } from 'vue';

const router = useRouter();
const roleStore = useRoleStore();
const searchStore = useSearchStore();

const { role } = storeToRefs(roleStore);

const pageForm = ref({
  keyword: null,
  page: 0,
});

const searchMethod = () => {
  searchStore.setSearchWord(pageForm.value.keyword);
  router.push({
    name: 'ProductSearch',
    state: { keyword: pageForm.value.keyword, page: pageForm.value.page },
  });

  pageForm.value.keyword = '';
};

const logout = () => {
  sessionStorage.removeItem('accessToken');
  sessionStorage.removeItem('refreshToken');
  role.value = 'NONE';

  alert('로그아웃 완료');
  router.push({ name: 'Home' });
};
</script>

<style lang="scss" scoped></style>
