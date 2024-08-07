<script setup>
import TheFirstHeader from '@/layouts/TheFirstHeader.vue';
import TheSecondHeader from '@/layouts/TheSecondHeader.vue';
import TheView from '@/layouts/TheView.vue';
</script>

<template>
  <div class="row">
    <div class="col-2"></div>
    <div class="col-8">
      <TheFirstHeader></TheFirstHeader>
      <TheSecondHeader></TheSecondHeader>

      <TheView></TheView>
    </div>
    <div class="col-2"></div>
  </div>
</template>
