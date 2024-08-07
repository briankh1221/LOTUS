# LOTUS: 중고 거래 웹사이트
>
> K-Digital Training (빅데이터 분석 (with 파이썬)과 엘라스틱 서치를 활용한 자바(Java) 웹 개발자 양성 과정)
>
> 고훈, 권지현 파이널 프로젝트 레포지토리 입니다


# 목차

> 1. [프로젝트 소개](#프로젝트-소개)
> 2. [ERD](#ERD)
> 3. [시스템 아키텍처](#아키텍처)
> 4. [기술 스택](#기술-스택)
> 5. [주요 기능](#주요-기능)
>   + 공통 : [회원가입, 로그인, 마이 페이지](#회원가입-로그인-마이-페이지) | [채팅](#채팅) | [물품 상세 조회](#물품-상세-조회)
>   + 이용자 : [물품 판매 등록 및 관리](#물품-판매-등록-및-관리) | [물품 찜 등록 및 예약](#물품-찜-등록-및-예약) | [리뷰 등록 및 관리](#리뷰-등록-및-관리) | [Q&A 게시판 등록 및 조회](#QA-게시판-등록-및-조회)
>   + 관리자 : [Q&A 게시판 답변 및 관리](#QA-게시판-답변-및-관리) 
> 6. [API 명세](#API-명세)
> 7. [팀 문화](#팀-문화)


			
# 프로젝트 소개
>
> ![스크린샷 2024-02-23 115613](https://github.com/briankh1221/LOTUS/assets/145642491/98728141-9f2c-4a42-ae21-0d36a5772649)
>
> - **중고 물품을 구매 및 판매할 수 있는 플랫폼입니다.**
> - **프론트엔드는 Vue, SpringBoot와 Spring Data JPA를 사용해 기본적인 REST API로 백엔드를 구성했습니다.**
> - **JWT(JSON Web Token)을 이용한 로그인 서비스 개발하였습니다.**
> - **STOMP 활용하여 채팅 서비스 개발하였습니다.**
> - **파이썬 크롤링을 활용해 타 중고사이트와 가격 비교가 가능하도록 했습니다.**
>
> #### 개발 기간 및 인원
> 24.01.16 ~ 24.03.04 / 프론트, 백엔드 2명 (고훈, 권지현)



# ERD
> ![ERD](https://github.com/briankh1221/final_project/assets/145642491/42af701f-5828-45b6-8b01-b77c07f66151)
>
> [ERD Cloud에서 보기](https://www.erdcloud.com/d/pTHTfhADbwpA9mggo) 

# 시스템 아키텍쳐
> <img alt="스크린샷5" src="https://github.com/briankh1221/LOTUS/assets/145642491/03babef1-2726-4e63-8b1d-25e372d8e74e">

# 기술 스택
> [![stackticon](https://firebasestorage.googleapis.com/v0/b/stackticon-81399.appspot.com/o/images%2F1708045228177?alt=media&token=e5c624f1-8b5e-4945-b49f-5aaf3ce03603)](https://github.com/msdio/stackticon)
> [![stackticon](https://firebasestorage.googleapis.com/v0/b/stackticon-81399.appspot.com/o/images%2F1707963722075?alt=media&token=de54b6af-bb60-458f-916d-1012fe92bc25)](https://github.com/msdio/stackticon)
> [![stackticon](https://firebasestorage.googleapis.com/v0/b/stackticon-81399.appspot.com/o/images%2F1707967744666?alt=media&token=3102f790-1a9b-4b26-85db-4528dcf0ad4c)](https://github.com/msdio/stackticon)
# 주요 기능

### 요약

> <table align="center"><!-- 팀원 표 -->
> <tr>
>  <th>공통</th>
>  <th>이용자</th>
>  <th>관리자</th>
> </tr>
> <tr>
> <td align="left" width="350px" class="공통">
>   - 회원가입, 로그인, 마이 페이지<br/>
>   - 채팅<br/>
>   - 물품 상세 조회</td>
> <td align="left" width="350px" class="이용자">
>   - 물품 판매 등록 및 관리 <br/>
>   - 물품 찜 등록 및 예약 <br/>
>   - 리뷰 등록 및 관리 <br/>
>   - Q&A 게시판 등록 및 조회</td>
>  <td align="left" width="350px" class="관리자">
>   - 물품, 리뷰, Q&A 게시판 조회 <br/>
>   - Q&A 게시판 답변 및 관리 <br/>
>   - 중고 물품 수정 및 삭제 <br/>
>   - 리뷰 삭제 </td>
> </tr>
> </table>

> ## [ 공통 기능 ]
>
> ### 회원가입, 로그인, 마이 페이지
>
> <p width="100%"><img width="50%" src="https://github.com/briankh1221/LOTUS/assets/145642491/0a3fc2e9-d027-4c0c-a2f5-f54dcdc18455" style="margin-right: 10px;"><img width="50%" src="https://github.com/briankh1221/LOTUS/assets/145642491/7668ed77-1f3f-4603-863d-f2371eb64bf9"></p>
>
> - 이용자는 아이디, 비밀번호, 이름, 휴대전화 번호, 이메일을 이용해 회원가입할 수 있다.
> - 관리자는 이메일, 비밀번호, 이름, 휴대전화 번호, 이메일, 관리자 인증코드를 이용해 회원가입할 수 있다.
> - 회원가입 시 사용한 아이디와 비밀번호를 이용해 로그인할 수 있다.
> - 비밀번호, 이름, 휴대전화 번호 등 개인 정보를 변경할 수 있다.
> - 프로필 이미지를 추가할 수 있다.
>
> ### 채팅
>
> ![12](https://github.com/briankh1221/LOTUS/assets/145642491/11259388-5084-4bce-b778-8e2dee1fa026)
>
> - 채팅 목록을 통해 지난 채팅 내용을 확인할 수 있다.
> - 채팅을 통해 구매자와 판매자 간 실시간으로 채팅할 수 있다.
>
> ### 물품 상세 조회
>
> ![스크린샷 2024-02-22 174727](https://github.com/briankh1221/LOTUS/assets/145642491/526e77c6-c10f-4efa-b69c-0b4ed1d7cc02)
>
> - 키워드 검색을 통해 물품을 상세 조회할 수 있다.
> - 카테고리 별, 최신 물품, 베스트 찜 상품 등을 조회할 수 있다.
>
> ## [ 이용자 기능 ]
>
> ### 물품 판매 등록 및 관리 
>
>  ![3](https://github.com/briankh1221/LOTUS/assets/145642491/e61a299e-ef43-4d42-98c6-f7d6bcf2bdd2)
>
> - 카테고리, 물품명, 제목, 내용, 택배비 포함 여부, 가격, 물품 이미지 등을 통해 판매 물품을 등록할 수 있다.
> - 등록한 판매 물품을 수정 및 삭제할 수 있다.
> - 해당 물품 거래가 완료되면 판매 종료로 거래 상태를 변경 시킬 수 있다.
> - 판매 종료된 상품을 다시 판매 상태로 변경 시킬 수 있다.
> - 전체 판매 물품 조회 및 거래 상태 (판매중, 예약중, 거래 완료)에 따라 물품 조회할 수 있다.
> - 판매자의 모든 리뷰를 조회할 수 있다.
>
> ### 물품 찜 등록 및 예약 
> 
> <img src="https://github.com/briankh1221/LOTUS/assets/145642491/a7e65b49-fe45-415d-9cdb-a129c7dc0690">
> <img src="https://github.com/briankh1221/LOTUS/assets/145642491/e1cd6ae0-8a67-4950-bc9d-5d7c6a0ff8f4">
> 
> - 찜 등록 및 삭제할 수 있다.
> - 찜 물품 목록을 조회할 수 있다.
> - 구매하고 싶은 물품을 예약 및 취소할 수 있다.
> - 예약한 물품 및 거래 완료한 물품을 조회할 수 있다.
>
> ### 리뷰 등록 및 관리
>
>  ![11](https://github.com/briankh1221/LOTUS/assets/145642491/26bd1cc2-d275-415d-b275-96143ae942e2)
>
> - 중고 거래한 상품에 대하여 이미지 및 평가 등을 통해 리뷰를 등록할 수 있다.
> - 등록한 리뷰를 수정 및 삭제할 수 있다.
>   
> ### Q&A 게시판 등록 및 조회
>
>  ![13](https://github.com/briankh1221/LOTUS/assets/145642491/043612cc-2fa5-450d-9473-42277f73b8a7)
>
> - Q&A 게시판을 통해 관리자와 1대1 질문을 등록할 수 있다.
> - 등록한 질문을 수정 및 삭제할 수 있다.
> - 작성한 모든 질문을 조회할 수 있다.
>   
> ## [ 관리 기능 ]
> 
> ### Q&A 게시판 답변 및 관리
>
> ![14](https://github.com/briankh1221/LOTUS/assets/145642491/ad4e3261-c2ed-4d91-ad89-e8ad746df3f5)
>
> -  물품, 리뷰, Q&A 게시판, Q&A 답변을 조회할 수 있다.
> - Q&A 게시판을 통해 답변을 등록할 수 있다.
> - 등록한 질문을 수정 및 삭제할 수 있다.
> - 중고 물품 (카테고리, 거래 상태) 수정 및 삭제할 수 있다.
> - 리뷰를 삭제할 수 있다.

# API 명세

>
> ![MergedImages](https://github.com/briankh1221/LOTUS/assets/145642491/e5c9e7bc-ad49-4e7e-a7ea-366bb148d1c8)
>
> ![MergedImages (1)](https://github.com/briankh1221/LOTUS/assets/145642491/ede1cf7e-19a9-4de7-86c3-3a4bf78c2237)
>
> | Domain       | URL                                                                        | HTTP Method                 | Description         | 접근 권한 |
> |:-------------|:---------------------------------------------------------------------------|:----------------------------|:--------------------|:------|
> | **auth**     | /auth/signup                                                               | `POST`                      | 이용자 회원가입 | USER |  
> |              | /auth/signup/admin                                                         | `POST`                      | 관리자 회원가입 | ADMIN |
> |              | /auth/signin                                                               | `POST`                      | 로그인 | BOTH |	
> | **user**     | /user/details                                                              | `GET` `PUT`                 | 마이 페이지 조회, 등록 | USER |  
> |              | /user/product                                                              | `GET` `POST`                | 판매 물품 조회, 등록 | USER |
> |              | /user/product/{productIdx}                                                 | `PUT` `DELETE`              | 판매 물품 수정, 삭제 | USER |
> |              | /user/product/{productIdx}/favorite                                        | `POST` `DELETE`             | 찜 등록, 삭제 | USER |
> |              | /user/product/favorite                                                     | `GET`                       | 찜 목록 조회 | USER |
> |              | /user/product/{productIdx}/bookingstatus                                   | `PATCH`                     | 물품 예약 | USER |
> |              | /user/product/{productIdx}/cancelstatus                                    | `PATCH`                     | 물품 예약 취소 | USER |
> |              | /user/product/{productIdx}/soldstatus                                      | `PATCH`                     | 물품 판매 종료 | USER |
> |              | /user/product/{productIdx}/salestatus                                      | `PATCH`                     | 물품 판매 종료 취소 | USER |
> |              | /user/product-list                                                         | `GET`                       | 판매자 판매 물품 조회 | USER |
> |              | /user/seller-review-list                                                   | `GET`                       | 판매자 리뷰 조회 | USER |
> |              | /user/buyer-review-list                                                    | `GET`                       | 구매자 리뷰 조회 | USER |
> |              | /user/product/{transactionStatus}                                          | `GET`                       | 거래 상태에 따른 판매 물품 조회 | USER |
> |              | /user/buying-list/{transactionStatus}                                      | `GET`                       | 거래 상태에 따른 구매 물품 조회 | USER |
> |              | /user/product/{productIdx}/review                                          | `POST`                      | 리뷰 등록 | USER |
> |              | /user/product/{productIdx}/review/{reviewIdx}                              | `PUT` `DELETE`              | 리뷰 수정, 삭제 | USER |
> |              | /user/product/{productIdx}/review                                          | `GET`                       | 한 물품에 대한 모든 리뷰 조회 | USER |
> |              | /user/qna                                                                  | `POST`                      | Q&A 등록 | USER |
> |              | /user/qna/{qnaIdx}                                                         | `PUT` `DELETE`              | Q&A 수정, 삭제 | USER |
> |              | /user/qna-list                                                             | `GET`                       | Q&A 전체 게시판 조회 | USER |
> |              | /user/qna                                                                  | `GET`                       | Q&A 작성한 게시판 조회 | USER |
> |  **admin**   | /admin/product-list                                                        | `GET`                       | 관리자 중고 물품 조회 | ADMIN |
> |              | /admin/review-list                                                         | `GET`                       | 관리자 리뷰 조회 | ADMIN |
> |              | /admin/qna-list                                                            | `GET`                       | 관리자 Q&A 조회 | ADMIN |
> |              | /admin/qnareply-list                                                       | `GET`                       | 관리자 Q&A 답변 조회 | ADMIN |
> |              | /admin/details                                                             | `GET` `POST`                | 관리자 마이 페이지 조회, 수정 | ADMIN |
> |              | /admin/qna/{qnaIdx}/qnareply                                               | `POST`                      | Q&A 답변 등록 | ADMIN |
> |              | /admin/qna/{qnaIdx}/qnareply{qnaReplyIdx}                                  | `PUT` `DELETE`              | Q&A 답변 수정, 삭제 | ADMIN |
> |              | /admin/product/{productIdx}                                                | `PATCH` `DELETE`            | 관리자 물품 수정, 삭제 | ADMIN |
> |              | /admin/review/{reviewIdx}                                                  | `DELETE`                    | 관리자 리뷰 삭제 | ADMIN |
> | **room**     | /room                                                                      | `POST`                      | 채팅하기 | BOTH |
> |              | /room-list                                                                 | `GET`                       | 채팅 목록 조회 | BOTH |
> |              | /room/{roomIdx}                                                            | `GET`                       | 채팅 메시지 조회 | BOTH |
> | **product**  | /product/keyword/{keyword}                                                 | `GET`                       | 키워드 검색 | - |
> |              | /product/{productIdx}                                                      | `GET`                       | 물품 상세 조회 | - |
> |              | /product/category-list/{categoryName}                                      | `GET`                       | 카테고리 별 물품 조회 | - |
> |              | /product/recent-list/                                                      | `GET`                       | 최신 상품 조회 | - |
> |              | /product/best-list                                                         | `GET`                       | 최다 찜 상품 조회 | - |

# 팀 문화

1. :ok_woman: 경청과 화합
    - 질문, 리뷰 및 미팅할 때나 늘 경청한 후 의견을 냅니다. 그리고 항상 협업이 될 수 있도록 노력합니다.
      
2. :page_facing_up: 하루를 시작 및 끝나는 미팅
    - 업무 시작 10분 전 무엇을 하고 있었는지, 무엇을 새로 시작했는지, 앞으로 무엇을 할 것인지 그 날의 to do list를 공유합니다.
    - 업무 종료 10분 전  to do list 달성 여부 및 할 일, 피드백 등 간단히 정리 합니다.
      
3. :clipboard: 한 주를 시작하는 미팅
    - 일주일을 시작하는 월요일에 한 주 동안 어떤 일을 할지 계획을 세웁니다.
    - 지난 Sprint Plan에서 세웠던 계획을 얼마나 이행했는지에 대해 돌아봅니다.
    - 업무적으로 좋았던 부분/아쉬웠던 부분, 개인적으로 좋았던 부분/아쉬웠던 부분이 있으면 공유합니다.
   
5. :muscle: 즐거운 개발 시간
    - 협업 효율을 올리기 위해 주중에 다 같이 개발하는 시간인 코어 타임을 가집니다.
    - 주중 오전 9시 30분 ~ 오후 11시 15분까지 필수, 주말은 자유입니다.
    - 질문이 있을 경우 눈치 보지 않고 바로 질문하고 상대방은 바쁘더라도 하던 일을 잠시 멈추고 대답합니다.


