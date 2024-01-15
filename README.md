# final_project
k-digital final project 권지현 고훈 프로젝트 레포지토리 입니다

# api 명세
도메인: user, admin, product, favorite, review, qna, message, common

모든 도메인 - controller, service/impl - imple, dto/form, entity, repository

common - config, exception, enum, BasteTimeEntity, (S3UploaderService)

| Domain       | URL                                                                        | Http Method                 | description       | 접근 권한 |
|:-------------|:---------------------------------------------------------------------------|:----------------------------|:------------------|:------|
| **Auth**     | /signup                                                                    | `POST`                      | 사용자 회원가입          | -     |
|              | /signup/admin                                                              | `POST`                      | 관리자 회원가입          | -     |
|              | /signin                                                                    | `POST`                      | 사용자/관리자 로그인       | -     |
| **Prouduct** | /product/list/{categoryId}                                                 | `GET`                       | 카테고리 별 상품 목록 조회   | -     |
|              | /product/best-list                                                         | `GET`                       | 베스트 상품 목록 조회      | -     |
|              | /product/{productId}                                                       | `GET`                       | 상품 상세 조회          | -     |
|              | /admin/product                                                             | `POST`                      | 상품 등록             | ADMIN |
|              | /admin/product/{productId}                                                 | `GET` `PUT` `DELETE`        | 상품 조회, 수정, 삭제     | ADMIN |
|              | /admin/product?productId={productId}&soldout={soldOutStatus}               | `PUT`                       | 상품 품절 여부 수정       | ADMIN |
|              | /admin/option/{optionId}                                                   | `GET` `PUT` `POST` `DELETE` | 상품 옵션 CRUD        | ADMIN |
|              | /admin/category/{categoryId}                                               | `GET` `PUT` `POST` `DELETE` | 상품 카테고리 CRUD      | ADMIN |
|              | /admin/option-category                                                     | `GET` `PUT` `POST` `DELETE` | 옵션 카테고리 CRUD      | ADMIN |
| **Orders**   | /auth/pay/list?viewType={viewType}&startDate={startDate}&endDate={endDate} | `GET`                       | 구매 내역 조회          | USER  |
|              | /auth/orders/elapsed-time/{ordersId}                                       | `GET`                       | 주문 경과 시간 조회       | USER  |
|              | /auth/orders/cancel/{ordersId}                                             | `PATCH`                     | 주문 취소             | USER  |
|              | /admin/orders/status/{ordersId}                                            | `PATCH`                     | 주문 상태 변경          | ADMIN |
|              | /admin/orders/cooking-time/{ordersId}                                      | `PATCH`                     | 예상 조리 시간 선택       | ADMIN |
|              | /admin/orders/receipt-status/{ordersId}                                    | `PATCH`                     | 주문 수락 또는 거절       | ADMIN |
| **Cart**     | /auth/cart                                                                 | `GET`                       | 장바구니 상품 목록 조회     | USER  |
|              | /auth/cart                                                                 | `POST` `DELETE`             | 장바구니 상품 수량 변경, 삭제 | USER  |
|              | /auth/cart/save                                                            | `POST`                      | 장바구니 상품 추가        | USER  |
|              | /auth/pay                                                                  | `PUT`                       | 장바구니 전체 결제        | USER  |
| **Review**   | /auth/review                                                               | `POST`                      | 리뷰 등록             | USER  |

|/signin|POST|사용자|관리자 로그인|
|signup|POST|사용자 회원가입|

/signup/admin, POST, 관리자 회원 가입 

/user/product/{productid}, GET, PUT, DELETE 중고 상품 상세 조회, 수정, 삭제
/user/product, POST, 중고 상품 등록
/user/product/{productid}, GET, PUT, DELETE 중고 상품 상세 조회, 수정, 삭제

/user/favorite, GET, 찜 목록 조회
/user/favorite, POST, 찜 목록 추가 
/user/favorite, DELETE, 찜 목록 삭제

/user/qna, GET, QnA 조회
/user/qna, POST, 리뷰 등록
/user/qna, DELETE, 리뷰 삭제 
/user/qna, POST, 리뷰 답변 

/user/review, GET, 리뷰 조회
/user/review, POST, 리뷰 등록 
/user/review, DELETE, 리뷰 삭제 
*/user/message, GET, 채팅 메세지 조회
*/user/message, POST, 채팅하기 

/admin/product/{productId}, GET, PUT, DELETE, 관리자 상품 조회, 수정, 삭제
/admin/category-list, GET, PUT, DELETE 관리자 카테고리 항목 관리 
/admin/qna, GET, PUT, DELETE 관리자 1대1 질문 게시판 관리
/admin/review, GET, PUT, DELETE 관리자 리뷰 관리   

/product/category-list/{CategoryId}, GET, 카테고리 별 중고 상품 목록 조회
/product/best-list, GET, 베스트 중고 상품 목록 조회
/product/recent-list, GET, 최신 중고 상품 목록 조회  
