<template>
  <div>
    <form>
      <div class="container text-center">
        <h2 class="justify-items-center mb-5">상품 등록</h2>
        <div class="row">
          <div class="col-2"></div>
          <div class="col-8 d-flex flex-column">
            <!-- 상품 사진 등록 영역 -->
            <div class="container text-start pb-2 mx-5 px-0 d-flex be">
              <label for="hiddenInput" style="cursor: pointer">
                <div
                  class="bg-light text-dark text-center me-3"
                  style="width: 80px; height: 80px"
                >
                  <img
                    src="/images/imageBtn.jpg"
                    style="width: 80px; height: 80px"
                  />
                </div>
              </label>
              <input
                type="file"
                multiple
                id="hiddenInput"
                name="images"
                style="display: none"
                @change="handleFileChange"
              />
              <div v-if="selectedFiles.length > 0" class="">
                <div class="row">
                  <div
                    v-for="file in selectedFiles"
                    :key="file.name"
                    class="d-block col-auto position-relative mb-3 px-2"
                  >
                    <img
                      style="width: 80px; height: 80px"
                      :src="createObjectURL(file)"
                      class="img-fluid"
                    />
                    <button
                      type="button"
                      class="btn-close position-absolute top-10 start-55 translate-middle p-0 bg-secondary border border-light rounded-circle"
                      @click.prevent="removeFile(file)"
                    ></button>
                  </div>
                </div>
              </div>
            </div>
            <p class="text-start text-secondary">
              사진 1장 필수 첨부 &middot 최대 5장
            </p>
            <!-- 제목 입력 영역 -->
            <input
              v-model="form.title"
              name="title"
              type="text"
              class="form-control my-3"
              placeholder="  제목 "
            />
            <input
              v-model="form.productName"
              type="text"
              class="form-control mb-5"
              placeholder="  정확한 제품 일련번호"
            />
            <div class="list-group mb-5">
              <select
                v-model="form.categoryName"
                class="form-select"
                aria-label="Default select example"
              >
                <option selected>카테고리 선택</option>
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
            </div>

            <div class="input-group">
              <input
                v-model="form.price"
                type="text"
                class="form-control"
                aria-label="Text input with radio button"
                placeholder="  ₩ 판매 가격"
              />
              <div class="input-group-text p-1">
                <input
                  v-model="form.deliveryFee"
                  type="radio"
                  class="btn-check"
                  name="deliveryFee"
                  id="deliveryFee"
                  value="INCLUDING_DELIVERY_FEE"
                />
                <label class="btn btn-outline-success mx-1" for="deliveryFee"
                  >배송비 포함</label
                >
                <input
                  v-model="form.deliveryFee"
                  type="radio"
                  class="btn-check"
                  name="deliveryFee"
                  id="deliveryFee2"
                  value="NOT_INCLUDING_DELIVERY_FEE"
                />
                <label class="btn btn-outline-secondary mx-1" for="deliveryFee2"
                  >미포함</label
                >
              </div>
            </div>

            <hr />

            <div class="input-group">
              <input
                type="text"
                @focus.prevent
                class="form-control"
                aria-label="Text input with radio button"
                placeholder="  물품 상태 선택"
                disabled
              />
              <div class="input-group-text p-1">
                <input
                  v-model="form.productStatus"
                  type="radio"
                  class="btn-check"
                  name="productStatus"
                  id="productStatus1"
                  value="EXCELLENT"
                />
                <label class="btn btn-outline-success mx-1" for="productStatus1"
                  >최상</label
                >
                <input
                  v-model="form.productStatus"
                  type="radio"
                  class="btn-check"
                  name="productStatus"
                  id="productStatus2"
                  value="GOOD"
                />
                <label class="btn btn-outline-success mx-1" for="productStatus2"
                  >상</label
                >
                <input
                  v-model="form.productStatus"
                  type="radio"
                  class="btn-check"
                  name="productStatus"
                  id="productStatus3"
                  value="FAIR"
                />
                <label class="btn btn-outline-success mx-1" for="productStatus3"
                  >중</label
                >
                <input
                  v-model="form.productStatus"
                  type="radio"
                  class="btn-check"
                  name="productStatus"
                  id="productStatus4"
                  value="POOR"
                />
                <label class="btn btn-outline-success mx-1" for="productStatus4"
                  >하</label
                >
                <input
                  v-model="form.productStatus"
                  type="radio"
                  class="btn-check"
                  name="productStatus"
                  id="productStatus5"
                  value="VERY_POOR"
                />
                <label class="btn btn-outline-success mx-1" for="productStatus5"
                  >최하</label
                >
              </div>
            </div>

            <hr />

            <div class="input-group mb-3">
              <input
                type="text"
                class="form-control"
                aria-label="Text input with radio button"
                placeholder="  거래 방식 선택"
                disabled
              />
              <div class="input-group-text p-1">
                <input
                  v-model="form.deliveryMethod"
                  type="radio"
                  class="btn-check"
                  name="deliveryMethod"
                  id="deliveryMethod1"
                  value="DIRECT_DEAL"
                />
                <label
                  class="btn btn-outline-success mx-1"
                  for="deliveryMethod1"
                  >직거래</label
                >
                <input
                  v-model="form.deliveryMethod"
                  type="radio"
                  class="btn-check"
                  name="deliveryMethod"
                  id="deliveryMethod2"
                  value="SHIPPING_SERVICE"
                />
                <label
                  class="btn btn-outline-secondary mx-1"
                  for="deliveryMethod2"
                  >택배거래</label
                >
              </div>
            </div>

            <textarea
              v-model="form.description"
              @input="handleTextareaInput"
              class="form-control my-3"
              name=""
              cols="30"
              rows="10"
              placeholder=" - 상품명(브랜드) &#10; - 구매 시기- 사용 기간	&#10; - 하자 여부	&#10;&#10; * 실제 촬영한 사진과 함께 상세 정보를 입력해주세요."
            ></textarea>

            <input
              v-model="form.address"
              type="text"
              class="form-control mb-1"
              placeholder="  희망 거래 주소 입력 "
            />
            <button
              class="btn btn-secondary my-5"
              @click.prevent="productCreate"
            >
              상품 등록 하기
            </button>
          </div>
          <div class="col-2"></div>
        </div>
      </div>
      <div class="d-flex gap-2 mt-4">
        <slot name="actions"> </slot>
      </div>
    </form>
  </div>
</template>

<script setup>
import { formInstance } from '@/common/axios/axiosInstance';
import { ref } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter();
const activeLink = ref(null);
const selectedFiles = ref([]);

const form = ref({
  title: null,
  productName: null,
  categoryName: 'LUXURY_GOODS',
  description: null,
  price: null,
  deliveryFee: null,
  productStatus: null,
  deliveryMethod: null,
  transactionStatus: 'ON_SALE',
  address: null,
  images: [],
});

const handleLinkClick = index => {
  activeLink.value = index;
  form.value.categoryIdx = index;
};

const handleFileChange = event => {
  const files = Array.from(event.target.files);

  // 이미지 파일을 File 객체로 변환하여 배열에 추가
  const fileObjects = files.map(file => new File([file], file.name));

  form.value.images = [...selectedFiles.value, ...fileObjects];
  selectedFiles.value = [...form.value.images];

  if (form.value.images.length > 5) {
    alert('사진은 5개까지만 추가 가능합니다');
    while (form.value.images.length > 5) {
      form.value.images.pop();
    }
  }
  selectedFiles.value = [...form.value.images];
};

const removeFile = fileToRemove => {
  selectedFiles.value = selectedFiles.value.filter(
    file => file !== fileToRemove,
  );
  form.value.images = [...selectedFiles.value];
};

// textarea에 입력된 내용을 줄바꿈을 포함하여 저장
const handleTextareaInput = event => {
  form.value.description = event.target.value;
};

const productCreate = async () => {
  try {
    console.log(form.value);
    const formData = new FormData();

    formData.append('transactionStatus', form.value.transactionStatus);

    if (form.value.title) formData.append('title', form.value.title);
    if (form.value.productName)
      formData.append('productName', form.value.productName);
    if (form.value.categoryIdx)
      formData.append('categoryIdx', form.value.categoryIdx);
    if (form.value.description)
      formData.append('description', form.value.description);
    if (form.value.price) formData.append('price', form.value.price);
    if (form.value.deliveryFee)
      formData.append('deliveryFee', form.value.deliveryFee);
    if (form.value.productStatus)
      formData.append('productStatus', form.value.productStatus);
    if (form.value.deliveryMethod)
      formData.append('deliveryMethod', form.value.deliveryMethod);
    if (form.value.address) formData.append('address', form.value.address);

    if (form.value.categoryName)
      formData.append('categoryName', form.value.categoryName);

    // 이미지 파일들을 formData에 추가
    form.value.images.forEach(file => {
      formData.append('images', file);
    });

    await formInstance.post('/user/product', formData);
    alert('상품 등록 완료');
    router.push({ name: 'Home' });
  } catch (error) {
    console.error(error);
    alert(error.response.data.errorMessage);
  }
};

const createObjectURL = file => {
  return URL.createObjectURL(file);
};
</script>

<style lang="scss" scoped></style>
