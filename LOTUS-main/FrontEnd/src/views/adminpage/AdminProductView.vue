<template>
  <div>
    <h2 class="text-center">관리자 상품 관리</h2>
    <hr class="mb-2 mt-3" />
    <br />
    <div v-if="posts">
      <table class="table table-hover text-center">
        <thead>
          <tr>
            <th style="width: 50px">IDX</th>
            <th style="width: 700px">제목</th>
            <th style="width: 200px">작성자</th>
            <th style="width: 150px">거래상태</th>
            <th style="width: 200px">카테고리</th>
            <th style="width: 100px">삭제</th>
            <th style="width: 100px">변경</th>
          </tr>
        </thead>

        <tbody>
          <tr v-for="post in posts" :key="post">
            <th>{{ post.productIdx }}</th>
            <td
              @click="
                goDetailPage(post.productIdx, post.price, post.postingDate)
              "
            >
              <button class="btn">
                {{ post.shortTitle }}
              </button>
            </td>
            <td>{{ post.user.email }}</td>
            <td>
              <select
                class="form-select-sm text-center"
                v-model="post.transactionStatus"
              >
                <option value="" selected>거래상태</option>
                <option value="ON_SALE">판매중</option>
                <option value="UNDER_RESERVATION">예약중</option>
                <option value="COMPLETED">거래완료</option>
              </select>
            </td>
            <td>
              <select
                class="form-select-sm text-center"
                v-model="post.categoryName"
              >
                <option value="" selected>카테고리 선택</option>
                <option value="LUXURY_GOODS">럭셔리/명품</option>
                <option value="FASHION_CLOTHES">패션/의류</option>
                <option value="FASHION_GOODS">패션/잡화</option>
                <option value="BEAUTY">뷰티</option>
                <option value="INFANTRY_GOODS">유아 상품</option>
                <option value="MOBILE_TABLET">모바일 태블릿</option>
                <option value="HOME_APPLIANCE">가전 제품</option>
                <option value="NOTEBOOK_PC">노트북, PC</option>
                <option value="CAMERA">카메라, 캠코더</option>
                <option value="FURNITURE">가구, 인테리어</option>
                <option value="HOUSEHOLD_GOODS">리빙, 생활</option>
                <option value="GAME">게임</option>
                <option value="PETS_HOBBIES">반려생활, 취미</option>
                <option value="BOOKS_RECORD_WRITING_GOODS">
                  도서, 음반, 문구
                </option>
                <option value="TICKET_COUPON">티켓, 쿠폰</option>
                <option value="SPORTS_GOODS">스포츠</option>
                <option value="LEISURE_TRAVEL_ITEMS">레저, 여행</option>
                <option value="INDUSTRIAL_GOODS">공구, 산업 용품</option>
                <option value="SHARING">무료 나눔</option>
                <option value="NFT">NFT</option>
                <option value="USED_CARS">중고차</option>
              </select>
            </td>
            <td>
              <button
                class="btn btn-outline-danger btn-sm"
                @click="deleteMethdod(post.productIdx)"
              >
                삭제
              </button>
            </td>
            <td>
              <button
                class="btn btn-outline-secondary btn-sm"
                @click="
                  changeMethod(
                    post.categoryName,
                    post.transactionStatus,
                    post.productIdx,
                  )
                "
              >
                변경
              </button>
            </td>
          </tr>
        </tbody>
      </table>

      <!-- 페이징 처리 부분 -->

      <div class="btn-toolbar justify-content-center">
        <div class="btn-group me-2" role="group" aria-label="First group">
          <button
            v-if="pagingData.isPrevious"
            type="button"
            class="btn"
            @click="goPrevious"
          >
            &laquo;
          </button>
          <button
            v-for="n in pagingData.endPage - pagingData.startPage"
            :key="n"
            type="button"
            class="btn"
            :class="{ active: pagingData.selectedPage == n }"
            @click="goToPage(n)"
          >
            {{ n }}
          </button>
          <button
            v-if="pagingData.isNext"
            type="button"
            class="btn"
            @click="goNext"
          >
            &raquo;
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import { instance, formInstance } from '@/common/axios/axiosInstance';
import { titleLength } from '@/common/utils/utils';
import { useRouter } from 'vue-router';

const router = useRouter();

const posts = ref([]);
const pagingData = ref({
  selectedPage: 1,
  endPage: null,
  startPage: null,
  isNext: false,
  isPrevious: false,
});

// 제목 클릭 시 상세 페이지 이동 매서드
const goDetailPage = (productIdx, price, postingDate) => {
  router.push({
    name: 'ProductDetail',
    state: { productIdx, price, postingDate },
  });
};

// 삭제 버튼 매서드
const deleteMethdod = async productIdx => {
  if (confirm('해당 상품을 삭제하시겠습니까?')) {
    try {
      await instance.delete('/admin/product/' + productIdx);
      alert('삭제 완료');

      posts.value = await fetchPosts();
      posts.value.shortTitle = titleLength(posts.value);
    } catch (error) {
      console.error(error);
    }
  }
};

// 변경 확정 매서드
const changeMethod = async (categoryName, transactionStatus, productIdx) => {
  if (confirm('변경 사항을 저장하시겠습니까?')) {
    try {
      await formInstance.patch('/admin/product/' + productIdx, {
        categoryName,
        transactionStatus,
      });
      alert('변경 저장 완료');

      posts.value = await fetchPosts();
      posts.value.shortTitle = titleLength(posts.value);
    } catch (error) {
      console.error(error);
    }
  }
};

// 페이징 관련 처리 =========================================================================================
// 특정 페이지의 목록을 가져오는 매서드
const fetchPosts = async page => {
  try {
    const response = await instance.get('/admin/product-list', {
      params: {
        page: page,
      },
    });
    return response.data;
  } catch (error) {
    console.error(error);
  }
};

// 특정 페이지를 클릭 한 경우 처리 매서드
const goToPage = async page => {
  const { pageDto, productDtoList } = await fetchPosts(page - 1);

  posts.value = productDtoList;
  pagingData.value.endPage = pageDto.endPage;
  pagingData.value.startPage = pageDto.startPage;

  // 제목이 긴 경우 짧게 줄이기
  posts.value.shortTitle = titleLength(posts.value);

  // 선택된 페이지 정보 수정
  pagingData.value.selectedPage = page;

  // 이전페이지 다음페이지 버튼 설정
  // 이전 페이지
  if (page >= 2) {
    pagingData.value.isPrevious = true;
  } else if (page == 1) {
    pagingData.value.isPrevious = false;
    if (pagingData.value.endPage >= 2) {
      pagingData.value.isNext = true;
    }
  }

  // 다음 페이지
  if (page == pagingData.value.endPage) {
    pagingData.value.isNext = false;
  }
};

// 이전 페이지 버튼 클릭 처리 매서드
const goPrevious = async () => {
  const page = pagingData.value.selectedPage - 1;
  const { pageDto, productDtoList } = await fetchPosts(page - 1);

  posts.value = productDtoList;
  pagingData.value.endPage = pageDto.endPage;
  pagingData.value.startPage = pageDto.startPage;

  // 제목이 긴 경우 짧게 줄이기
  posts.value.shortTitle = titleLength(posts.value);

  // 선택된 페이지 정보 수정
  pagingData.value.selectedPage = page;

  // 이전페이지 다음페이지 버튼 설정
  // 이전 페이지
  if (page >= 2) {
    pagingData.value.isPrevious = true;
  } else if (page == 1) {
    pagingData.value.isPrevious = false;
    if (pagingData.value.endPage >= 2) {
      pagingData.value.isNext = true;
    }
  }

  // 다음 페이지
  if (page == pagingData.value.endPage) {
    pagingData.value.isNext = false;
  }
};

// 다음 페이지 버튼 클릭 처리 매서드
const goNext = async () => {
  const page = pagingData.value.selectedPage + 1;
  const { pageDto, productDtoList } = await fetchPosts(page - 1);

  posts.value = productDtoList;
  pagingData.value.endPage = pageDto.endPage;
  pagingData.value.startPage = pageDto.startPage;

  // 제목이 긴 경우 짧게 줄이기
  posts.value.shortTitle = titleLength(posts.value);

  // 선택된 페이지 정보 수정
  pagingData.value.selectedPage = page;

  // 이전페이지 다음페이지 버튼 설정
  // 이전 페이지
  if (page >= 2) {
    pagingData.value.isPrevious = true;
  } else if (page == 1) {
    pagingData.value.isPrevious = false;
  }

  // 다음 페이지
  if (page == pagingData.value.endPage) {
    pagingData.value.isNext = false;
  }
};

// 페이징 관련 처리 종료 =========================================================================================

onMounted(async () => {
  // 1페이지에 해당하는 데이터와 paging 관련 정보
  const { pageDto, productDtoList } = await fetchPosts(0);

  posts.value = productDtoList;
  pagingData.value.endPage = pageDto.endPage;
  pagingData.value.startPage = pageDto.startPage;
  if (pagingData.value.endPage >= 2) {
    pagingData.value.isNext = true;
  }

  // 제목이 긴 경우 짧게 줄이기
  posts.value.shortTitle = titleLength(posts.value, 20);
});
</script>

<style scoped></style>
