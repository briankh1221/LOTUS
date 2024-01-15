# final_project
k-digital final project 권지현 고훈 프로젝트 레포지토리 입니다

# api 명세
도메인: user, admin, product, favorite, review, qna, message, common

모든 도메인 - controller, service/impl - imple, dto/form, entity, repository

|Domain|URL|Http Method|description|

common - config, exception, enum, BasteTimeEntity, (S3UploaderService)

/signin, POST, 사용자, 관리자 로그인
/signup, POST, 사용자 회원가입
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
