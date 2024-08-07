import { defineStore } from 'pinia';
import { ref } from 'vue';
import { getRole } from '../utils/utils';

export const useRoleStore = defineStore('role', {
  state: () => {
    const accessToken = sessionStorage.getItem('accessToken');
    const role = ref('');
    if (accessToken) {
      role.value = getRole(accessToken);
    } else {
      role.value = 'NONE';
    }

    return { role };
  },
  actions: {
    setRole(role) {
      this.role.value = role;
    },
  },
  getters: {
    getRole: state => {
      return state;
    },
  },
});

export default useRoleStore;
