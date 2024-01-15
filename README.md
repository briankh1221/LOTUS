# final_project
k-digital final project 권지현 고훈 프로젝트 레포지토리 입니다


# 프로젝트 소개

> 하루 한 번 가지는 음료 한잔의 소소한 행복을 더욱 편리하게 해주는 온라인 카페 주문 시스템입니다.\
> 사용자에게는 간편한 메뉴확인과 빠른 주문이라는 편의성, 포인트 적립이라는 이득을 제공합니다.\
> 관리자에게는 시스템 사용의 이득을 통한 사용자 모집 효과, 간편한 주문관리를 제공합니다.
> 
> SpringBoot와 Spring Data JPA를 사용해 기본적인 REST API를 구현하고,\
> Docker, AWS, S3 등을 이용해 서버를 배포했습니다.

> ### 개발 기간 및 인원
> 23.08.07 ~ 23.09.14 (5주) \
> 백엔드 5명

# api 명세

| Domain       | URL                                                                        | Http Method                 | description       | 접근 권한 |
|:-------------|:---------------------------------------------------------------------------|:----------------------------|:------------------|:------|
| **Auth**     | /signup                                                                    | `POST`                      | 사용자 회원가입          | USER |
|              | /signup/admin                                                              | `POST`                      | 관리자 회원가입          | ADMIN |
|              | /signin                                                                    | `POST`                      | 사용자/관리자 로그인       | -     |
| **admin**    | /admin/product/{productId}                                                 | `GET` `PUT` `DELETE`        | 관리자 상품 조회, 수정, 삭제 | ADMIN  |
|              | /admin/category/{categorytId}                                              | `GET` `PUT` `DELETE`        | 관리자 카테고리 항목 관리 | ADMIN  |
|              | /admin/qna/{productId}                                                     | `GET` `PUT` `DELETE`        | 관리자 질문 게시판 관리 | ADMIN |
|              | /admin/review/{productId}                                                  | `GET` `PUT` `DELETE`        | 관리자 리뷰 관리 | ADMIN  |
| **Prouduct** | /product/category-list/{categoryId}                                        | `GET`                       | 카테고리 별 상품 목록 조회   | USER |
|              | /product/best-list                                                         | `GET`                       | 베스트 상품 목록 조회      | USER |
|              | /product/recent-list                                                       | `GET`                       | 최신 상품 목록 조회          | USER |
|              | /user/product                                                              | `POST`                      | 상품 등록             | USER |
|              | /user/product/{productId}                                                  | `GET` `PUT` `DELETE`        | 상품 조회, 수정, 삭제     | USER |
| **favorite** | /user/favorite                                                             | `POST`                      | 찜 등록      | USER |
|              | /user/favorite                                                             | `GET` `PUT` `DELETE`        | 찜 상품 조회, 수정, 삭제     | USER |
| **qna**      | /user/qna                                                                  | `POST`                      | qna 작성     | USER |
|              | /user/qna                                                                  | `GET` `PUT` `DELETE`        | qna 조회, 등록, 삭제     | USER |
| **review**   | /user/review                                                               | `POST`                      | 리뷰 작성    | USER |
|              | /user/review                                                               | `GET` `PUT` `DELETE`        | 리뷰 조회, 수정, 삭제     | USER |
| **message**  | /user/message                                                              | `GET` `POST`                | 채팅 조회, 작성     | USER |

# 임시 아키텍쳐

![전체 아키텍쳐 임시](https://github.com/briankh1221/final_project/assets/129491967/2787277b-e6b0-4d23-a9a3-f50445779786)

