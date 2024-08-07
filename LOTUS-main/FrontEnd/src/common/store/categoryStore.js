import { defineStore } from 'pinia';
import { ref } from 'vue';

export const useCategoryStore = defineStore('category', {
  state: () => {
    const category = ref('');

    return category;
  },
  actions: {
    setCategory(category) {
      this.category = category;
    },
  },
  getters: {
    getCategory: state => {
      return state;
    },
  },
});

export default useCategoryStore;
