import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '../views/HomeView.vue';
import CategoryView from '../views/CategoryView.vue';
import SigninView from '../views/SigninView.vue';
import SignupView from '../views/SignupView.vue';
import ProductCreateView from '../views/ProductCreateView.vue';
import ProductDetailView from '../views/ProductDetailView.vue';
import ProductModifyView from '../views/ProductModifyView.vue';
import ProductSearchView from '../views/ProductSearchView.vue';
import TestView from '../views/TestView.vue';
import FraudView from '../views/FraudView.vue';
import PriceView from '../views/PriceView.vue';
import ChatingView from '../views/ChatingView.vue';
import MyView from '../views/mypage/MyView.vue';
import UserModifyView from '../views/mypage/UserModifyView.vue';
import SaleListView from '../views/mypage/SaleListView.vue';
import PurchaseListView from '../views/mypage/PurchaseListView.vue';
import FavoriteListView from '../views/mypage/FavoriteListView.vue';
import ReviewView from '../views/mypage/ReviewView.vue';
import QnAView from '../views/mypage/QnAView.vue';
import AdminMainView from '../views/adminpage/AdminMainView.vue';
import AdminProductView from '../views/adminpage/AdminProductView.vue';
import AdminQnAView from '../views/adminpage/AdminQnAView.vue';
import AdminReviewView from '../views/adminpage/AdminReviewView.vue';

const routes = [
  // 전체 허용 목록
  {
    path: '/',
    name: 'Home',
    component: HomeView,
    meta: { requiredRole: 'All' },
  },
  {
    path: '/category',
    name: 'CategoryView',
    component: CategoryView,
    meta: { requiredRole: 'All' },
  },
  {
    path: '/test',
    name: 'TestView',
    component: TestView,
    meta: { requiredRole: 'All' },
  },
  {
    path: '/signin',
    name: 'Signin',
    component: SigninView,
    meta: { requiredRole: 'All' },
  },
  {
    path: '/signup',
    name: 'Signup',
    component: SignupView,
    meta: { requiredRole: 'All' },
  },
  {
    path: '/fraud',
    name: 'FraudView',
    component: FraudView,
    meta: { requiredRole: 'All' },
  },
  {
    path: '/price',
    name: 'PriceView',
    component: PriceView,
    meta: { requiredRole: 'All' },
  },
  {
    path: '/product/detail',
    name: 'ProductDetail',
    component: ProductDetailView,
    meta: { requiredRole: 'All' },
  },
  {
    path: '/product/search',
    name: 'ProductSearch',
    component: ProductSearchView,
    meta: { requiredRole: 'All' },
  },

  // USER 허용 목록
  {
    path: '/chating',
    name: 'ChatingView',
    component: ChatingView,
    meta: { requiredRole: 'USER' },
  },
  {
    path: '/product/create',
    name: 'ProductCreate',
    component: ProductCreateView,
    meta: { requiredRole: 'USER' },
  },
  {
    path: '/product/modify',
    name: 'ProductModify',
    component: ProductModifyView,
    meta: { requiredRole: 'USER' },
  },
  {
    path: '/user/qna',
    name: 'QnAView',
    component: QnAView,
    meta: { requiredRole: 'USER' },
  },
  {
    path: '/user',
    name: 'MyView',
    component: MyView,
    meta: { requiredRole: 'USER' },
  },
  {
    path: '/user/sale-list',
    name: 'SaleListView',
    component: SaleListView,
    meta: { requiredRole: 'USER' },
  },
  {
    path: '/user/purchase-list',
    name: 'PurchaseListView',
    component: PurchaseListView,
    meta: { requiredRole: 'USER' },
  },
  {
    path: '/user/favorite-list',
    name: 'FavoriteListView',
    component: FavoriteListView,
    meta: { requiredRole: 'USER' },
  },
  {
    path: '/user/review-list',
    name: 'ReviewView',
    component: ReviewView,
    meta: { requiredRole: 'USER' },
  },
  {
    path: '/user/modify',
    name: 'UserModifyView',
    component: UserModifyView,
    meta: { requiredRole: 'USER' },
  },

  // ADMIN 허용 목록
  {
    path: '/admin/main',
    name: 'AdminMain',
    component: AdminMainView,
    meta: { requiredRole: 'ADMIN' },
  },
  {
    path: '/admin/product',
    name: 'AdminProduct',
    component: AdminProductView,
    meta: { requiredRole: 'ADMIN' },
  },
  {
    path: '/admin/qna',
    name: 'AdminQnA',
    component: AdminQnAView,
    meta: { requiredRole: 'ADMIN' },
  },
  {
    path: '/admin/review',
    name: 'AdminReview',
    component: AdminReviewView,
    meta: { requiredRole: 'ADMIN' },
  },
];

const router = createRouter({
  history: createWebHistory('/'),
  routes: routes,
});

import { instance } from '@/common/axios/axiosInstance';
import { isExpired, getRole } from '@/common/utils/utils';

// beforeEach 가드를 메서드로 분리
// 이동하기 전에 수행할 작업
function handleBeforeEach(to, from, next) {
  console.log('네비게이션 가드 입장');
  //
  // permitAll에 해당하는 사이트는 무조건 접속 가능
  if (to.meta.requiredRole == 'All') {
    next();

    // 이외의 사이트는 인증이 필요함
  } else {
    let accessToken = sessionStorage.getItem('accessToken');

    // sessionStorage에 accessToken이 없으면 로그인 페이지로
    if (!accessToken) {
      alert('로그인이 필요합니다');
      next('/signin');

      // accessToken이 있지만 만료된 경우
    } else if (isExpired(accessToken)) {
      // accessToken 을 재발급하여 sessionStorage에 저장
      const recreateAccess = async () => {
        try {
          const response = await instance.post('/refreshToken');
          sessionStorage.setItem('accessToken', response.data.accessToken);
          console.log('router 에서 refresh를 통한 access 재발급');
        } catch (e) {
          console.error(e);

          // refresh 토큰이 만료된 경우에는 로그인 페이지로 이동
          if (
            e.response.data.status == 400 &&
            e.response.data.errorMessage.startsWith('refresh')
          ) {
            console.log('router에서 refresh 만료로 재 로그인 요청');
            next('/signin');
            return;
          }
        }
      };
      recreateAccess();
    }

    // 여기까지 도착 => 현재 유효한 accessToken을 가지고 있음
    const refreshToken = sessionStorage.getItem('refreshToken');
    accessToken = sessionStorage.getItem('accessToken');
    if (!isExpired(refreshToken)) {
      let role = getRole(accessToken);
      // USER의 경우
      if (role == 'ROLE_USER') {
        // ADMIN만 들어갈 수 있는 페이지는 못들어가고 메인 페이지로 이동
        if (to.meta.requiredRole == 'ADMIN') {
          alert('관리자 페이지에 접근할 수 없습니다');
          next('/');
          // USER만 들어갈 수 있는 경우 해당 페이지로 이동
        } else if (to.meta.requiredRole == 'USER') {
          next();
        }
      }
      if (role == 'ROLE_ADMIN') {
        next();
      }
    }
  }
}

// beforeEach 가드를 등록
router.beforeEach(handleBeforeEach);

export default router;
