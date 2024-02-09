# LOTUS: 중고 거래 웹사이트

K-Digital Training 
(더조은 아카데미 - 빅데이터 분석 (with 파이썬)과 엘라스틱 서치를 활용한 자바(Java) 웹 개발자 양성 과정)

권지현, 고훈 파이널 프로젝트 레포지토리 입니다

# 프로젝트 소개

> 이용자가 손쉽게 중고 물품을 구매하고 판매할 수 있는 플랫폼니다.\
> 다른 중고 사이트의 정보를 한눈에 볼 수 있어 이용자는 합리적인 가격으로 원하는 물품을 구매할 수 있습니다.\
> 이용자는 간편한 절차를 통해 자신의 중고 물품을 손쉽게 판매할 수 있습니다.
> 
> SpringBoot와 Spring Data JPA를 사용해 기본적인 REST API를 구현하였습니다.\
> AWS, S3 등을 이용해 서버를 배포할 예정입니다.

> ### 개발 기간 및 인원
> 24.01.16 ~ 24.03.09 (8주) \
> 프론트, 백엔드 2명(권지현, 고훈)
>
> ### 목차

>
> 1. [ERD](#ERD)
> 2. [아키텍처](#아키텍처)
> 3. [기술 스택](#기술-스택)
> 4. [주요 기능](#주요-기능)
>   + 공통 : [회원가입, 로그인](#회원가입-로그인) | [상품 목록 및 상품 상세 조회](#상품-목록-및-상품-상세-조회)
>   + 사용자 : [장바구니 관리](#장바구니-관리) | [주문 내역 관리](#주문-내역-관리) | [리뷰 관리](#리뷰-관리)
>   + 관리자 : [상품 및 상품 카테고리 관리](#상품-및-상품-카테고리-관리) | [옵션 카테고리 및 옵션 관리](#옵션-카테고리-및-옵션-관리) | [주문 수락 및 주문 상태 관리](#주문-수락-및-주문-상태-관리)
> 5. [api 명세](#api-명세)
> 6. [형상 관리 - Notion, Jira](#형상-관리)
> 7. [팀 문화](#팀-문화)

# API 명세

| Domain       | URL                                                                        | Http Method                 | description       | 접근 권한 |
|:-------------|:---------------------------------------------------------------------------|:----------------------------|:------------------|:------|
| **auth**     | /auth/signup                                                                    | `POST`                 | 사용자 회원가입          | USER |
|              | /auth/signup/admin                                                              | `POST`                 | 관리자 회원가입          | ADMIN |
|              | /auth/signin                                                                    | `POST`                 | 사용자/관리자 로그인       | -     |
| **admin**    | /admin/product/{productId}                                                 | `GET` `PUT` `DELETE`        | 관리자 상품 조회, 수정, 삭제 | ADMIN  |
|              | /admin/category/{categorytId}                                              | `GET` `PUT` `DELETE`        | 관리자 카테고리 항목 관리 | ADMIN  |
|              | /admin/qna/{productId}                                                     | `GET` `PUT` `DELETE`        | 관리자 질문 게시판 관리 | ADMIN |
|              | /admin/review/{productId}                                                  | `GET` `PUT` `DELETE`        | 관리자 리뷰 관리 | ADMIN  |
| **Prouduct** | /product/category-list/{categoryId}                                        | `GET`                       | 카테고리 별 상품 목록 조회   | USER |
|              | /product/best-list                                                         | `GET`                       | 베스트 상품 목록 조회      | USER |
|              | /product/recent-list                                                       | `GET`                       | 최신 상품 목록 조회          | USER |
|              | /user/product                                                              | `POST`                      | 상품 등록             | USER |
|              | /product/{productId}                                                       | `GET` `PUT` `DELETE`        | 상품 조회, 수정, 삭제     | USER |
|              | /user/product-list                                                         | `GET`                       | 사용자 등록 상품 목록     | USER |
| **favorite** | /user/favorite                                                             | `POST`                      | 찜 등록      | USER |
|              | /user/favorite                                                             | `GET` `PUT` `DELETE`        | 찜 상품 조회, 수정, 삭제     | USER |
| **qna**      | /user/qna                                                                  | `POST`                      | qna 작성     | USER |
|              | /user/qna                                                                  | `GET` `PUT` `DELETE`        | qna 조회, 등록, 삭제     | USER |
| **review**   | /user/review                                                               | `POST`                      | 리뷰 작성    | USER |
|              | /user/review                                                               | `GET` `PUT` `DELETE`        | 리뷰 조회, 수정, 삭제     | USER |
| **message**  | /user/message                                                              | `GET` `POST`                | 채팅 조회, 작성     | USER |

# 임시 아키텍쳐

![전체 아키텍쳐 임시](https://github.com/briankh1221/final_project/assets/129491967/6d48dd94-d156-4e88-9702-ab1fa3efaabe
)

# ERD
[👉 ERD Cloud에서 직접 보기](https://www.erdcloud.com/d/pTHTfhADbwpA9mggo)

![ERD](https://github.com/briankh1221/final_project/assets/145642491/bf9f35f2-41ed-428a-abfd-be44aba59cdb)

