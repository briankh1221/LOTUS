# LOTUS: 중고 거래 웹사이트
>
> K-Digital Training 
> (더조은 아카데미 - 빅데이터 분석 (with 파이썬)과 엘라스틱 서치를 활용한 자바(Java) 웹 개발자 양성 과정)
>
> 고훈, 권지현 파이널 프로젝트 레포지토리 입니다
>
> // 메인 페이지 및 상품 등록 후 채팅하는 사진 첨부 예정 //

# 목차
Q&A 게시판 등록 및 조회
> 1. [프로젝트 소개](#프로젝트-소개)
> 2. [ERD](#ERD)
> 3. [아키텍처](#아키텍처)
> 4. [기술 스택](#기술-스택)
> 5. [주요 기능](#주요-기능)
>   + 공통 : [회원가입, 로그인, 나의 정보 관리](#회원가입-로그인-나의-정보-관리) | [채팅](#채팅) | [물품 상세 조회](#물품-상세-조회)
>   + 이용자 : [물품 판매 등록 및 관리](#물품-판매-등록-및-관리) | [물품 찜 등록 및 예약](#물품-찜-등록-및-예약) | [리뷰 등록 및 관리](#리뷰-등록-및-관리) | [Q&A 게시판 등록 및 조회](#QA-게시판-등록-및-조회)
>   + 관리자 : [Q&A 게시판 답변 및 관리](#QA-게시판-답변-및-관리) 
> 6. [api 명세](#api-명세)
> 7. [형상 관리 - Notion, Jira](#형상-관리)
> 8. [팀 문화](#팀-문화)

			
# 프로젝트 소개

> - **이용자가 손쉽게 중고 물품을 구매 및 판매할 수 있는 플랫폼입니다.**
> - **타 중고 사이트의 정보를 한 눈에 볼 수 있어 이용자는 합리적인 가격으로 원하는 물품을 구매할 수 있습니다.**
> - **이용자는 간편한 절차를 통해 자신의 중고 물품을 손쉽게 판매할 수 있습니다.**
>
> - **SpringBoot와 Spring Data JPA를 사용해 기본적인 REST API를 구현하였고 AWS, S3 등을 이용해 서버를 배포할 예정입니다.**
>
> #### 개발 기간 및 인원
> 24.01.16 ~ 24.03.09 (8주) / 프론트, 백엔드 2명 (고훈, 권지현)



# ERD
> ![ERD](https://github.com/briankh1221/final_project/assets/145642491/42af701f-5828-45b6-8b01-b77c07f66151)
>
> [ERD Cloud에서 보기](https://www.erdcloud.com/d/pTHTfhADbwpA9mggo) 

# 아키텍쳐

![전체 아키텍쳐 임시](https://github.com/briankh1221/final_project/assets/129491967/6d48dd94-d156-4e88-9702-ab1fa3efaabe
)


# 주요 기능

### 요약

<table align="center"><!-- 팀원 표 -->
<tr>
   <th>공통</th>
   <th>이용자</th>
   <th>관리자</th>
</tr>
<tr>
   <td align="left" width="350px" class="공통">
    - 회원가입, 로그인, 나의 정보 관리<br/>
    - 채팅<br/>
    - 물품 상세 조회</td>
   <td align="left" width="350px" class="이용자">
     - 물품 판매 등록 및 관리 <br/>
     - 물품 찜 등록 및 예약 <br/>
     - 리뷰 등록 및 관리 <br/>
     - Q&A 게시판 등록 및 조회
   </td>
   <td align="left" width="350px" class="관리자">
    - Q&A 게시판 답변 및 관리</td>
</tr>
</table>

## [ 공통 기능 ]

### 회원가입, 로그인, 나의 정보 관리
- 이용자는 아이디, 비밀번호, 이름, 휴대전화 번호, 이메일을 이용해 회원가입할 수 있다.
- 관리자는 이메일, 비밀번호, 이름, 휴대전화 번호, 이메일, 관리자 인증코드를 이용해 회원가입할 수 있다.
- 회원가입 시 사용한 아이디와 비밀번호를 이용해 로그인할 수 있다.
- 비밀번호, 이름, 휴대전화 번호 등 개인 정보를 변경할 수 있다.
- 프로필 이미지를 추가할 수 있다.

### 채팅
- 채팅 목록을 통해 지난 채팅 내용을 확인할 수 있다.
- 채팅을 통해 구매자와 판매자 간 실시간으로 채팅할 수 있다.

### 물품 상세 조회
- 키워드 검색을 통해 물품을 상세 조회할 수 있다.
- 카테고리 별, 최신 물품, 베스트 찜 상품 등을 조회할 수 있다.

## [ 이용자 기능 ]

### 물품 판매 등록 및 관리 
- 카테고리, 물품명, 제목, 내용, 택배비 포함 여부, 가격, 물품 이미지 등을 통해 판매 물품을 등록할 수 있다.
- 등록한 판매 물품을 수정 및 삭제할 수 있다.
- 해당 물품 거래가 완료되면 판매 종료로 거래 상태를 변경 시킬 수 있다.
- 전체 판매 물품 조회 및 거래 상태 (판매중, 예약중, 거래 완료)에 따라 물품 조회할 수 있다. 

### 물품 찜 등록 및 예약 
- 찜 등록 및 삭제할 수 있다.
- 찜 물품 목록을 조회할 수 있다.
- 구매하고 싶은 물품을 예약할 수 있다.
- 예약한 물품 및 거래 완료한 물품을 조회할 수 있다.

### 리뷰 등록 및 관리  
- 중고 거래한 상품에 대하여 이미지 및 평가 등을 통해 리뷰를 등록할 수 있다.
- 등록한 리뷰를 수정 및 삭제할 수 있다.

### Q&A 게시판 등록 및 조회
- Q&A 게시판을 통해 관리자와 1대1 질문을 등록할 수 있다.
- 등록한 질문을 수정 및 삭제할 수 있다.
- 작성한 모든 질문을 조회할 수 있다.


## [ 관리자 기능 ]

### Q&A 게시판 답변 및 관리

- Q&A 게시판을 통해 답변을 등록할 수 있다.
- 등록한 질문을 수정 및 삭제할 수 있다.


# API 명세

| Domain       | URL                                                                        | Http Method                 | description       | 접근 권한 |
|:-------------|:---------------------------------------------------------------------------|:----------------------------|:------------------|:------|
| **auth**     | /auth/signup                                                               | `POST`                      | 이용자 회원가입 | - |
|              | /auth/signup/admin                                                         | `POST`                      | 관리자 회원가입 | ADMIN |
|              | /auth/signin                                                               | `POST`                      | 사용자/관리자 로그인 | BOTH |
| **user**     | /user/details                                                              | `GET` `POST`                | 나의 정보 관리 조회, 수정 | USER |
|              | /user/product                                                              | `GET` `POST`                | 판매 물품 조회, 등록 | USER |
|              | /user/product/{productIdx}                                                 | `PUT` `DELETE`              | 판매 물품 수정, 삭제 | USER |
|              | /user/product/{productIdx}/favorite                                        | `POST` `DELETE`             | 찜 등록, 삭제 | USER |
|              | /user/favorite                                                             | `GET`                       | 찜 목록 조회 | USER |
|              | /user/product/{productIdx}/bookingStatus                                   | `PATCH`                     | 물품 예약 | USER |
|              | /user/product/{productIdx}/soldStatus                                      | `PATCH`                     | 물품 거래 종 | USER |
|              | /user/product/{productIdx}/bookingStatus                                   | `PATCH`                     | 물품 예약 | USER |
|              | /user/product/{transactionStatus}                                          | `GET`                       | 거래 상태에 따른 판매 물품 조회 | USER |
|              | /user/buying-list/{transactionStatus}                                      | `GET`                       | 거래 상태에 따른 구매 물품 조회 | USER |
|              | /user/product/{productIdx}/review                                          | `POST`                      | 리뷰 등록 | USER |
|              | /user/product/{productIdx}/review/{reviewIdx}                              | `PUT` `DELETE`              | 리뷰 수정, 삭제 | USER |
|              | /user/product/{productIdx}/review                                          | `GET`                       | 한 물품에 대한 모든 리뷰 조회 | USER |
|              | /user/qna                                                                  | `POST`                      | Q&A 등록 | USER |
|              | /user/qna/{qnaIdx}                                                         | `PUT` `DELETE`              | Q&A 수정, 삭제 | USER |
|              | /user/qna-list                                                             | `GET`                       | Q&A 전체 게시판 조회 | USER |
|              | /user/qna                                                                  | `GET`                       | 이용자 작성한 Q&A 게시판 조회 | USER |
| **admin**    | /admin/details                                                             | `GET` `POST`                | 관리자 정보 관리 조회, 수정 | ADMIN |
|              | /admin/qna/{qnaIdx}/qnareply                                               | `POST` `PUT` `DELETE`       | Q&A 답변 등록, 수정, 삭제 | ADMIN |
| **room**     | /room                                                                      | `POST`                      | 채팅하기 | BOTH |
|              | /room-list                                                                 | `GET`                       | 채팅 목록 조회 | BOTH |
|              | /room/{roomIdx}                                                            | `GET`                       | 채팅 메시지 내용 조회 | BOTH |
| **product**  | /product/keyword/{keyword}                                                 | `GET`                       | 키워드 검색 | - |
|              | /product/{productIdx}                                                      | `GET`                       | 물품 상세 조회 | - |
|              | /product/category-list/{categoryName}                                      | `GET`                       | 카테고리별 물품 조회 | - |
|              | /product/recent-list/                                                      | `GET`                       | 최신 상품 조회 | - |
|              | /product/best-list                                                         | `GET`                       | 베스트 찜 상품 조회 | - |

