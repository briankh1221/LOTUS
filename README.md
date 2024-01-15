# final_project
k-digital final project 권지현 고훈 프로젝트 레포지토리 입니다

# api 명세

| Domain       | URL                                                                        | Http Method                 | description       | 접근 권한 |
|:-------------|:---------------------------------------------------------------------------|:----------------------------|:------------------|:------|
| **Auth**     | /signup                                                                    | `POST`                      | 사용자 회원가입          | -     |
|              | /signup/admin                                                              | `POST`                      | 관리자 회원가입          | -     |
|              | /signin                                                                    | `POST`                      | 사용자/관리자 로그인       | -     |
| **admin**    | /admin/product/{productId}                                                 | `GET` `PUT` `DELETE`        | 관리자 상품 조회, 수정, 삭제 | ADMIN  |
|              | /admin/category/{categorytId}                                              | `GET` `PUT` `DELETE`        | 관리자 카테고리 항목 관리 | ADMIN  |
|              | /admin/qna/{productId}                                                     | `GET` `PUT` `DELETE`        | 관리자 질문 게시판 관리 | ADMIN  |
|              | /admin/review/{productId}                                                  | `GET` `PUT` `DELETE`        | 관리자 리뷰 관리 | ADMIN  |
| **Prouduct** | /product/category-list/{categoryId}                                        | `GET`                       | 카테고리 별 상품 목록 조회   | -     |
|              | /product/best-list                                                         | `GET`                       | 베스트 상품 목록 조회      | -     |
|              | /product/recent-list                                                       | `GET`                       | 최신 상품 목록 조회          | -     |
|              | /user/product                                                              | `POST`                      | 상품 등록             | -   |
|              | /user/product/{productId}                                                  | `GET` `PUT` `DELETE`        | 상품 조회, 수정, 삭제     | - |
| **favorite** | /user/favorite                                                             | `POST`                      | 찜 등록      | USER  |
|              | /user/favorite                                                             | `GET` `PUT` `DELETE`        | 찜 상품 조회, 수정, 삭제     | USER  |
| **qna**      | /user/qna                                                                  | `POST`                      | qna 작성     | USER  |
|              | /user/qna                                                                  | `GET` `PUT` `DELETE`        | qna 조회, 등록, 삭제     | USER  |
| **review**   | /user/review                                                               | `POST`                      | 리뷰 작성    | USER  |
|              | /user/review                                                               | `GET` `PUT` `DELETE`        | 리뷰 조회, 수정, 삭제     | USER  |
| **message**  | /user/message                                                              | `GET` `POST`                | 채팅 조회, 작성     | USER  |

