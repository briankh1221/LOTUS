# final_project
k-digital final project 권지현 고훈 프로젝트 레포지토리 입니다


# 프로젝트 소개

> 당근, 중고나라, 번개장터.\
> 여러 사이트의 정보를 한눈에 볼 수 있음.\
> 기존 사이트들의 장점을 모았음.
> 
> SpringBoot와 Spring Data JPA를 사용해 기본적인 REST API를 구현하고,\
> AWS, S3 등을 이용해 서버를 배포할 예정입니다.

> ### 개발 기간 및 인원
> 24.01.16 ~ 24.03.09 (8주) \
> 백엔드 2명

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


# ERD

![erd](https://github.com/briankh1221/final_project/assets/129491967/d7205d77-e2b7-4103-bb33-6a2c28276479)




