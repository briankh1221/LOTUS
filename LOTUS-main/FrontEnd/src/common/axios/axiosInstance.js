import axios from 'axios';
import { isExpired } from '../utils/utils';
import router from '@/router';
import useRoleStore from '@/common/store/roleStore';

const instance = axios.create({
  baseURL: 'http://localhost:8081/',
  headers: {
    'Content-Type': 'application/json',
    // 'Content-Type': 'application/x-www-form-urlencoded',
    //'Content-Type': 'multipart/form-data',
  },
});

const formInstance = axios.create({
  baseURL: 'http://localhost:8081/',
  headers: {
    'Content-Type': 'multipart/form-data',
  },
});

// ==================================================================
// instance 에 관련된 인터셉터 설정 ===================================
// ==================================================================
instance.interceptors.request.use(
  config => {
    const url = config.url;

    // url이 /refreshToken인 경우는 refreshToken만 가져감
    if (url == '/refreshToken') {
      const refreshToken = sessionStorage.getItem('refreshToken');
      config.headers['refreshToken'] = refreshToken;
      return config;
    }

    const accessToken = sessionStorage.getItem('accessToken');

    // 그 이외의 모든 경우에는 accessToken이 저장된 경우 accessToken을 가져감
    if (accessToken) {
      if (!isExpired(accessToken)) {
        config.headers['Authorization'] = accessToken;
      }
    }
    return config;
  },
  error => {
    return Promise.reject(error);
  },
);
instance.interceptors.response.use(
  response => {
    // 정상적으로 로그인한 경우 tokenDto에 2개의 토큰이 담겨서 response 됨
    // 2개의 토큰을 각각의 이름으로 sessionStorage에 저장

    if (response.data.tokenDto) {
      const { accessToken, refreshToken } = response.data.tokenDto;
      if (accessToken) {
        sessionStorage.setItem('accessToken', accessToken);
      }
      if (refreshToken) {
        sessionStorage.setItem('refreshToken', refreshToken);
      }
    }

    return response;
  },
  error => {
    console.log('response  전에 error');
    console.error(error);
    if (error.response.status == 401) {
      if (error.response.data.startsWith('access')) {
        try {
          const newAccessToken = refreshToken();
          console.log('interceptor 에서 refresh로 access 토큰 재발급');
          // 새로운 Access 토큰으로 재시도
          error.config.headers['Authorization'] = newAccessToken;
          return instance(error.config);
        } catch (refreshError) {
          // 재시도도 실패하면 에러 반환
          return Promise.reject(error);
        }
      }
    } else if (error.response.status == 400) {
      if (error.response.data.errorMessage.startsWith('refresh')) {
        console.log('interceptor 에서 refresh 만료로 재 로그인 요청');
        const roleStore = useRoleStore();
        roleStore.role = 'NONE';
        alert('재 로그인이 필요합니다');
        router.push({ name: 'Signin' });
      }
    }
    return Promise.reject(error);
  },
);

// =====================================================================
// formInstance 에 관련된 인터셉터 설정 ==================================
// =====================================================================

formInstance.interceptors.request.use(
  config => {
    const url = config.url;

    // url이 /refreshToken인 경우는 refreshToken만 가져감
    if (url == '/refreshToken') {
      const refreshToken = sessionStorage.getItem('refreshToken');
      config.headers['refreshToken'] = refreshToken;
      return config;
    }

    const accessToken = sessionStorage.getItem('accessToken');

    // 그 이외의 모든 경우에는 accessToken이 저장된 경우 accessToken을 가져감
    if (accessToken) {
      config.headers['Authorization'] = accessToken;
    }
    return config;
  },
  error => {
    return Promise.reject(error);
  },
);
formInstance.interceptors.response.use(
  response => {
    // 정상적으로 로그인한 경우 tokenDto에 2개의 토큰이 담겨서 response 됨
    // 2개의 토큰을 각각의 이름으로 sessionStorage에 저장
    if (response.data.tokenDto) {
      const { accessToken, refreshToken } = response.data.tokenDto;
      if (accessToken) {
        sessionStorage.setItem('accessToken', accessToken);
      }
      if (refreshToken) {
        sessionStorage.setItem('refreshToken', refreshToken);
      }
    }

    return response;
  },
  error => {
    if (error.response.status == 401) {
      if (error.response.data.startsWith('access')) {
        try {
          const newAccessToken = refreshToken();

          // 새로운 Access 토큰으로 재시도
          error.config.headers['Authorization'] = newAccessToken;
          return instance(error.config);
        } catch (refreshError) {
          // 재시도도 실패하면 에러 반환
          return Promise.reject(error);
        }
      }
    } else if (error.response.status == 400) {
      if (error.response.data.errorMessage.startsWith('refresh')) {
        router.push({ name: 'Signin' });
      }
    }
    return Promise.reject(error);
  },
);

// refresh 토큰으로 access 토큰 재발급 받아오는 매서드
const refreshToken = async () => {
  const refreshToken = sessionStorage.getItem('refreshToken');
  try {
    const response = await instance.post('/refreshToken', { refreshToken });
    const { accessToken } = response.data;
    sessionStorage.setItem('accessToken', accessToken);
    return response.config;
  } catch (error) {
    console.error('refreshToken 요청 실패:', error);
  }
};

export { instance, formInstance };
