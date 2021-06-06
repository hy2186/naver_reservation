# 클론 네이버 예약 서비스

## 소개

- 예약서비스는 네이버웹서비스에서 사용했던 서비스입니다.
- 실제 네이버 서비스의 동작과는 다르며, 부스트코스 웹 프로그래밍과정의 기획서 내용을 토대로 개발하였습니다.
- 모바일웹과 PC웹을 하나의 UI로 구성하였고 크게 5가지 서비스 기능을 개발 하였습니다.
<hr>

## 주요기술
- HTML, CSS, JavaScript
- Java
- SpringBoot
- Spring Data JPA
- QueryDSL

## 주요기능
### 1. 메인페이지

<p>
<img width="250" alt="메인화면" src="https://user-images.githubusercontent.com/26062056/93191596-acc94400-f77f-11ea-8283-dae45f319ba7.png">
</p>

- 처음 접속하면 나타나는 메인화면으로 카테고리별로 상품 정보가 4개씩 보여진다.
- 더보기를 클릭하여 4개씩 추가로 상품정보를 AJAX요청으로 가져온다.
- 상품을 클릭하면 상품 상세정보 페이지로 이동한다.
<hr>

### 2. 각 항목의 상세페이지

<p float="left">
<img width="250" height="763" alt="상세페이지1" src="https://user-images.githubusercontent.com/26062056/93197437-983c7a00-f786-11ea-9fd7-5896efb8772c.png">
<img width="250" heigth="763" alt="상세페이지2" src="https://user-images.githubusercontent.com/26062056/93197447-9c689780-f786-11ea-9106-138ee1e39efc.png">
</p>

- 상품을 클릭한 후 상품상세정보 페이지로 예매자의 리뷰와 평점을 볼 수 있고 상품 상세 정보와 위치를 볼 수 있다.
- 예매하기 버튼을 클릭하면 상품을 예매하기 위한 페이지로 이동한다.
<hr>

### 3. 예약하기

<p float="left">
<img width="250" height="763" alt="예매하기1" src="https://user-images.githubusercontent.com/26062056/93197454-9d99c480-f786-11ea-8aec-083743a980d1.png">
<img width="250" height="763" alt="예매하기2" src="https://user-images.githubusercontent.com/26062056/93197457-9ecaf180-f786-11ea-835c-e70599fdd678.png">
</p>

- 상품 상세정보 페이지에서 예매하기 버튼을 클릭하면 이동하는 페이지다.
- 티켓 개수를 선택하고 예매자의 정보를 입력하여 상품을 예매한다.
<hr>

### 4. 나의 예매 내역 확인

<p>
<img width="250" alt="예매내역확인" src="https://user-images.githubusercontent.com/26062056/93197451-9d99c480-f786-11ea-992d-e3acca5edc74.png">
</p>

- 이메일에 따른 예매내역을 나타내는 페이지다.
- 예약확정, 이용완료, 취소된 예약으로 구분된다.
<hr>

### 5. 한줄평 등록

<p>
<img width="250" alt="한줄평 등록" src="https://user-images.githubusercontent.com/26062056/93197697-eb163180-f786-11ea-8f24-3cff3c566ae4.png">
</p>

- 이용완료 후 상품 리뷰를 등록할 수 있는 페이지다.
- 별을 이용해서 평점을 매길 수 있으며 사진 1장을 썸네일로 띄워 리뷰 등록시 이미지를 등록할 수 있다.
<hr>


## 프로젝트를 진행하면서 느낀점
부스트코스 에이스과정을 하면서 주어진 프로젝트 미션입니다. 
이 프로젝트를 한단계씩 진행하면서 현업 개발자분들에게 코드리뷰를 받으면서 단순히 실행가능한 코드를 작성하는 것이 아닌 협업과 유지보수를 위해 리팩토링의 중요성을 알았습니다.
