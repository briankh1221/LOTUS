<template>
  <div class="container py-4">
    <div v-if="showSidebar == 'user'">
      <div class="row">
        <div class="col-2"><TheUserSideBar></TheUserSideBar></div>
        <div class="col-10">
          <RouterView></RouterView>
        </div>
      </div>
    </div>
    <div v-else-if="showSidebar == 'admin'">
      <div class="row">
        <div class="col-2"><TheAdminSideBar></TheAdminSideBar></div>
        <div class="col-10">
          <RouterView></RouterView>
        </div>
      </div>
    </div>
    <div v-else>
      <RouterView></RouterView>
    </div>
  </div>
</template>

<script setup>
import TheUserSideBar from '@/layouts/TheUserSideBar.vue';
import TheAdminSideBar from '@/layouts/TheAdminSideBar.vue';

import { ref, watch } from 'vue';
import { useRoute } from 'vue-router';

const showSidebar = ref(false);
const route = useRoute();

watch(
  () => route.fullPath,
  newPath => {
    if (newPath.split('/')[1] === 'user') {
      showSidebar.value = 'user';
    } else if (newPath.split('/')[1] === 'admin') {
      showSidebar.value = 'admin';
    } else {
      showSidebar.value = 'none';
    }
  },
);
</script>

<style lang="scss" scoped></style>
