import { defineStore } from 'pinia';
import { ref } from 'vue';

export const useSearchStore = defineStore('searchWord', {
  state: () => {
    const searchWord = ref('');

    return searchWord;
  },
  actions: {
    setSearchWord(searchWord) {
      this.searchWord = searchWord;
    },
  },
  getters: {
    getSearchWord: state => {
      return state;
    },
  },
});

export default useSearchStore;
