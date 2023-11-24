# KOUN 🍀
[ 항공대 멋쟁이사자처럼 11기 - KOUN 프로젝트 BE ]  
 </br>
 </br>
 </br>

## 👋소개
### 티켓팅 추첨 서비스
코운은 치열한 피켓팅으로 인해 예매를 하고싶어도 할 수 없는 사람들을 위해 추첨제를 도입할 수 있습니다.</br>
외국인과 중장년층등 선착순제에서 불리한 위치에 있는 사람들에게도 </br>
티켓팅을 할 수 있는 기회를 주어 디지털 격차를 해결하고자 하는 KOUN입니다.

 </br>
 </br>

## 🕑개발기간
약 2개월</br>
2023.07.18 ~ 2023.08.18</br>
2023.10.17 ~ 2023.11.17

 </br>
 </br>
 
## 📌배포주소
http://ec2-13-124-88-252.ap-northeast-2.compute.amazonaws.com:8080
 </br>
 </br>
 
## 🛠️기술스택
### FrontEnd </br>
<img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white">
<img src="https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white">
<img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=JavaScript&logoColor=white">
</br>

### BackEnd </br>
<img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white">
<img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
<img src="https://img.shields.io/badge/java-E34F26?style=for-the-badge&logo=java&logoColor=white">

</br>
</br>

## 🧩아키텍쳐
koun</br>
├── java</br>
│   └── com.example.koun</br>
│       ├── config - CORS,SpringSecurityFilter</br>
│       ├── controller - 웹 요청 (API) 처리 컨트롤러</br>
│       ├── domain - 엔티티 모델 정의 </br>
│       ├── dto - 데이터 전송 객체</br>
│       ├── repository - 데이터베이스 연동을 위한 JPA 리포지토리</br>
│       ├── service - 비즈니스 로직 처리</br>
│       ├── login - Oauth2.0 로직 처리</br>
│       └── handler - 인증 실행 후 처리  </br>  
└── resources</br>
    ├── application.yml - 애플리케이션 설정 파일</br>
    ├── static - 정적 웹 자원 (CSS, JS, 이미지 등)</br>
    └── templates - HTML 템플릿 파일</br>
</br>
</br>

## ❤️떡잎마을방범대

<table>
  <tbody>
    <tr>
      <td align="center"><a href="https://github.com/kyr4601"><img src="https://github.com/kyr4601.png"width="100px;" alt=""/><br /><sub><b>FE 팀장 : 김예린</b></sub></a><br /></td>
      <td align="center"><a href="https://github.com/ekmonet1"><img src="https://github.com/ekmonet1.png" width="100px;" alt=""/><br /><sub><b>FE 팀원 : 이솔</b></sub></a><br /></td>
     <tr/>
      <td align="center"><a href="https://github.com/seosangwon"><img src="https://github.com/seosangwon.png" width="100px;" alt=""/><br /><sub><b>BE 팀장 : 서상원</b></sub></a><br /></td>
      <td align="center"><a href="https://github.com/minp2o"><img src="https://github.com/minp2o.png" width="100px;" alt=""/><br /><sub><b>BE 팀원 : 김민표</b></sub></a><br /></td>
      <td align="center"><a href="https://github.com/qkralstj0808"><img src="https://github.com/qkralstj0808.png" width="100px;" alt=""/><br /><sub><b>BE 팀원 : 박민서</b></sub></a><br /></td>
    </tr>
  </tbody>
</table>

</br>
</br>

## ✨주요 기능
### **1. 인기 Top 1-10 조회**

- **메소드 설명**: **`getTopLikes(Pageable pageable)`**
    - 이 메소드는 데이터베이스에서 가장 인기 있는 상품들(좋아요를 많이 받은 상품들)을 조회하는 기능을 제공합니다.
    - **`Pageable`** 인터페이스를 매개변수로 사용하여 요청된 페이지와 페이지 크기에 따라 상품들을 반환합니다.
- **작동 원리**:
    - **`itemRepository.findTopLikedItems(pageable):`**  데이터베이스에서 좋아요 수가 가장 많은 상품들을 찾는 쿼리를 실행합니다.
    - 조회된 상품들(**`Item`** 객체들)은 **`convertToDto`** 메소드를 통해 **`ItemResponseDto`** 객체로 변환됩니다.

### **2. 신규 Top 1-10 조회**

- **메소드 설명**: **`getNewTopLikes(Pageable pageable)`**
    - 이 메소드는 최근에 인기를 얻고 있는 상품들을 조회하는 기능을 제공합니다.
    - 이 또한 **`Pageable`** 인터페이스를 사용하여 페이징 처리된 결과를 반환합니다.
- **작동 원리**:
    - **`itemRepository.findNewTopLikedItems(pageable)`**를 호출하여 아이템 업로드 기준으로 정렬합니다.
    - 조회된 **`Item`** 객체들은 **`convertToDto2`** 메소드를 통해 **`ItemResponseDto`** 객체로 변환됩니다.

### 3. **랜덤 응모 추첨 (구역별)**:

- **`drawRaffleForSection(Long sectionId)`** 함수는 특정 콘서트 구역에 대한 응모 추첨을 수행합니다.
- 구역에 해당하는 응모 목록을 무작위로 섞은 후, 구역의 좌석 수에 따라 응모자들을 당첨시킵니다. 각 응모는 **`winRaffleStatus("true")`** 메소드를 호출하여 당첨 상태로 변경됩니다.

### 4. **랜덤 응모 추첨 (아이템별)**:

- **`drawRaffleForItem(Long itemId)`** 함수는 특정 콘서트 아이템(item)에 연결된 모든 구역에 대해 응모 추첨을 수행합니다.
- 각 구역에 대해 **`drawRaffleForSection`** 함수를 호출하여 추첨을 진행합니다.

### 5. **응모 결과 조회**:

- **`findRaffleResult(Long id)`** 함수는 특정 응모의 결과를 조회합니다.
- 응모, 구역, 아이템에 대한 정보를 조회한 후, 구역 및 아이템별 응모 수, 당첨 확률, 가격 등의 정보를 **`RaffleResultResponseDto`** 객체에 담아 반환합니다.


</br>
</br>

## 💻화면 구성
<table>
  <tbody>
    <tr>
      <td align="center"><img src="https://abaft-digestion-37a.notion.site/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2F8bd00c95-22c2-442a-80c9-09fcaf280f1f%2F6a01282e-d03a-4951-a60a-d2e302a1ace3%2FUntitled.png?table=block&id=c9e2afbb-ebdb-451c-9bb0-56a3d0e42ada&spaceId=8bd00c95-22c2-442a-80c9-09fcaf280f1f&width=2000&userId=&cache=v2 "width="200px;" alt=""/><br /><sub><b>로그인</b></sub></a><br /></td>
      <td align="center"><img src="https://abaft-digestion-37a.notion.site/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2F8bd00c95-22c2-442a-80c9-09fcaf280f1f%2F39c574cd-19b2-4af6-9061-6417c42c62d2%2FUntitled.png?table=block&id=b133e78c-9520-406d-b646-458931524f3b&spaceId=8bd00c95-22c2-442a-80c9-09fcaf280f1f&width=2000&userId=&cache=v2" width="200px;" alt=""/><br /><sub><b>회원가입</b></sub></a><br /></td>
     <tr/>
      <td align="center"><img src="https://abaft-digestion-37a.notion.site/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2F8bd00c95-22c2-442a-80c9-09fcaf280f1f%2Fba62aa35-a1bc-453f-ad83-0bfafdb83b7a%2FUntitled.png?table=block&id=7ff612dd-6fb4-430e-ba5a-f4e7b9787877&spaceId=8bd00c95-22c2-442a-80c9-09fcaf280f1f&width=2000&userId=&cache=v2" width="200px;" alt=""/><br /><sub><b>메인페이지</b></sub></a><br /></td>
      <td align="center"><img src="https://abaft-digestion-37a.notion.site/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2F8bd00c95-22c2-442a-80c9-09fcaf280f1f%2F7ca6d919-2916-4534-8e37-e1ab63f941d9%2FUntitled.png?table=block&id=344c44d0-ed1a-4509-9964-b0337a6db969&spaceId=8bd00c95-22c2-442a-80c9-09fcaf280f1f&width=2000&userId=&cache=v2" width="200px;" alt=""/><br /><sub><b>메인페이지 검색</b></sub></a><br /></td>
    </tr>
      <td align="center"><img src="https://abaft-digestion-37a.notion.site/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2F8bd00c95-22c2-442a-80c9-09fcaf280f1f%2F9fb1a13a-dae0-4df2-94c1-2466dda009c4%2FUntitled.png?table=block&id=f1483af3-5a47-47c6-bafb-a59e8b8ed895&spaceId=8bd00c95-22c2-442a-80c9-09fcaf280f1f&width=2000&userId=&cache=v2"width="200px;" alt=""/><br /><sub><b>상세페이지</b></sub></a><br /></td>
      <td align="center"><img src="https://abaft-digestion-37a.notion.site/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2F8bd00c95-22c2-442a-80c9-09fcaf280f1f%2F76f08e3c-f019-45b5-bd0e-9b3a7d80b8cd%2FUntitled.png?table=block&id=b48c0a28-c92e-43a8-9eab-a8e2c3d13386&spaceId=8bd00c95-22c2-442a-80c9-09fcaf280f1f&width=2000&userId=&cache=v2" width="200px;" alt=""/><br /><sub><b>응모페이지</b></sub></a><br /></td>
      <td align="center"><img src="https://abaft-digestion-37a.notion.site/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2F8bd00c95-22c2-442a-80c9-09fcaf280f1f%2Ffd501f23-e84b-4d23-8fdc-0d1a42cdf5a8%2FUntitled.png?table=block&id=02c2cebf-fcc6-45c8-a5dc-8ce1daf0949c&spaceId=8bd00c95-22c2-442a-80c9-09fcaf280f1f&width=2000&userId=&cache=v2" width="200px;" alt=""/><br /><sub><b>결제페이지</b></sub></a><br /></td>
      <td align="center"><img src="https://abaft-digestion-37a.notion.site/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2F8bd00c95-22c2-442a-80c9-09fcaf280f1f%2Fe277d12b-84f8-4374-a739-50a867de6e7e%2FUntitled.png?table=block&id=5f3eaf3e-f720-4f9a-8a68-c49381f5d11e&spaceId=8bd00c95-22c2-442a-80c9-09fcaf280f1f&width=2000&userId=&cache=v2" width="200px;" alt=""/><br /><sub><b>결제창</b></sub></a><br /></td>
    <tr/>
      <td align="center"><img src="https://abaft-digestion-37a.notion.site/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2F8bd00c95-22c2-442a-80c9-09fcaf280f1f%2F55bdbf86-69dc-4ae3-bd7c-511b4b203e39%2FUntitled.png?table=block&id=9641ccf8-d75d-4870-9a12-76eaec227c18&spaceId=8bd00c95-22c2-442a-80c9-09fcaf280f1f&width=2000&userId=&cache=v2" width="200px;" alt=""/><br /><sub><b>마이페이지</b></sub></a><br /></td>
      <td align="center"><img src="https://abaft-digestion-37a.notion.site/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2F8bd00c95-22c2-442a-80c9-09fcaf280f1f%2Fd34f1488-a7b2-4419-940f-60ecec0b6999%2FUntitled.png?table=block&id=f392708b-a969-4630-80a4-17954bc0992e&spaceId=8bd00c95-22c2-442a-80c9-09fcaf280f1f&width=1400&userId=&cache=v2" width="200px;" alt=""/><br /><sub><b>응모결과페이지</b></sub></a><br /></td>
    </tr>
      <td align="center"><img src="https://abaft-digestion-37a.notion.site/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2F8bd00c95-22c2-442a-80c9-09fcaf280f1f%2F953c8b32-1213-408c-b150-6561432755ba%2FUntitled.png?table=block&id=588ab898-b1e5-43c0-9aae-014468c1219f&spaceId=8bd00c95-22c2-442a-80c9-09fcaf280f1f&width=2000&userId=&cache=v2" width="200px;" alt=""/><br /><sub><b>취소페이지</b></sub></a><br /></td>
      <td align="center"><img src="https://abaft-digestion-37a.notion.site/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2F8bd00c95-22c2-442a-80c9-09fcaf280f1f%2F4db26e1e-b754-43e5-b7c9-c472886b5b5d%2FUntitled.png?table=block&id=20fca7aa-f9d2-4d6f-b68c-b78ec0be9c5e&spaceId=8bd00c95-22c2-442a-80c9-09fcaf280f1f&width=2000&userId=&cache=v2" width="200px;" alt=""/><br /><sub><b>취소페이지</b></sub></a><br /></td>
    </tr>
  
  </tbody>
</table>
