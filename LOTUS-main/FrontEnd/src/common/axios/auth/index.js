import axiosInstance from '@/common/axios/axiosInstance';

function create(baseURL, options) {
  const instance = axiosInstance.create(Object.assign({ baseURL }, options));

  return instance;
}

export const auths = create('http://localhost:8081/auth');
