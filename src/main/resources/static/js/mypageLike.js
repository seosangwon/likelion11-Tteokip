// const numberButWrapper2 = document.querySelector('.number-but-wrapper2');
// const prevButton2 = document.querySelector('.prev-button2');
// const nextButton2 = document.querySelector('.next-button2');
// let likedPage = 1;
//
//
//
// const setPageButtons2 = () => {
//     numberButWrapper2.innerHTML = '';
//     for (let j = 1; j <= getTotalPageCount(); j++) {
//         numberButWrapper2.innerHTML += `<span class="number-button2"> ${j} </span>`;
//     }
//     numberButWrapper2.firstChild.classList.add('selected');
//     pageNumberBut2 = document.querySelectorAll('.number-button2');
// };
// /**
//  * 페이지에 해당하는 게시물 ul에 넣어주기
//  * @param {number} pageNumber - 이동할 페이지 번호
//  */
//
// /*찜목록 페이지내이션 & 동적할당*/
// const setPageOf_liked = (pageNumber) => {
//     const list2 = document.getElementById("list2");
//     list2.innerText = '';
//     for (
//         let i = count_per_page * (pageNumber - 1) + 1;
//         i <= count_per_page * (pageNumber - 1) + 3 && i <= data.length;
//         i++
//     ) {
//         const parentElement2 = document.createElement('li');
//         // 부모 요소 선택
//         //var parentElement = document.querySelector('#orderList');
//         // 전체 div 생성
//         var itemBox = document.createElement("div");
//         itemBox.className = "itemBox";
//         // reserveDate_itemBox 요소 생성
//         var postNumberBox = document.createElement("div");
//         postNumberBox.className = "post_number";
//         var postNumberText = document.createElement("p");
//         postNumberText.className = "postedText";
//         postNumberText.textContent = data[i - 1].postNumber;
//         postNumberBox.appendChild(postNumberText);
//         itemBox.appendChild(postNumberBox);
//         // info_itemBox 요소 생성
//         var infoItemBox = document.createElement("div");
//         infoItemBox.className = "info_itemBox";
//
//
//         var img = document.createElement("img");
//         img.src = "../img/Poster_Lauv.png";
//         img.alt = "posterImg";
//         img.className = "posterImg";
//         var infoItemText = document.createElement("div");
//         infoItemText.className = "info_itemText";
//         var infoTitle = document.createElement("h3");
//         infoTitle.className = "info_title";
//         infoTitle.id = "concertname";
//         infoTitle.textContent = data[i - 1].title;
//
//         infoItemBox.addEventListener('click', function (event) {
//             boxinfo = event.target.closest('h3').textContent;
//             console.log(boxinfo);
//             window.location.href = 'detail.html?inbox=' + encodeURIComponent(boxinfo);
//         });
//
//
//         let infoDetail = document.createElement("div");
//         infoDetail.className = "info_detail";
//         let infoTime = document.createElement("p");
//         infoTime.className = "info_time";
//         infoTime.id = "concertinform";
//         infoTime.textContent = data[i - 1].concertdate;
//         let infoBar = document.createElement("p");
//         infoBar.className = "info_bar";
//         infoBar.textContent = "|";
//         let infoCount = document.createElement("p");
//         infoCount.className = "info_count";
//         infoCount.id = "concertcount";
//         infoCount.textContent = data[i - 1].concertcount;
//         //각 부모요소에 요소 추가
//         infoDetail.appendChild(infoTime);
//         infoDetail.appendChild(infoBar);
//         infoDetail.appendChild(infoCount);
//
//         infoItemText.appendChild(infoTitle);
//         infoItemText.appendChild(infoDetail);
//         infoItemBox.appendChild(img);
//         infoItemBox.appendChild(infoItemText);
//         //info_itemBox를 itemBox에 추가
//         itemBox.appendChild(infoItemBox);
//         // current_itemBox 요소 생성
//         let currentItemBox = document.createElement("div");
//         currentItemBox.className = "current_itemBox";
//         let currentInform = document.createElement("p");
//         currentInform.className = "current";
//         currentInform.id = "currentInform";
//         let resultBtn = document.createElement("button");
//         resultBtn.className = "result_button";
//         resultBtn.classList.add('result_button')
//
//         let resultBtnText = document.createTextNode('결과');
//         resultBtn.appendChild(resultBtnText)
//         let dDay = document.createElement("p");
//         dDay.className = "dday";
//         dDay.id = "d_day";
//         dDay.textContent = data[i - 1].dDay;
//         if (data[i - 1].dDay == '[D-Day]') {
//             currentInform.textContent = "응모종료";
//             currentItemBox.appendChild(currentInform);
//         } else {
//             currentInform.textContent = "응모중";
//             currentItemBox.appendChild(currentInform);
//             currentItemBox.appendChild(dDay);
//
//         }
//         //current_itemBox를 itemBox에 추가
//         itemBox.appendChild(currentItemBox);
//
//         // cancel_itemBox 요소 생성
//         let applyItemBox = document.createElement("div");
//         applyItemBox.className = "apply_itemBox";
//         let applyButton = document.createElement("button");
//         applyButton.className = "applybtn";
//         applyButton.textContent = "응모";
//
//
//         if (data[i - 1].dDay == "[D-Day]") {
//             applyButton.classList.add('end_button');
// /*
//             applyButton.addEventListener('click', confirm('해당 응모는 마감되었습니다.'));
// */
//
//         } else {
//             applyButton.classList.add('result_button');
//
//         }
//
//
//
//
//
//         applyItemBox.appendChild(applyButton);
//         itemBox.appendChild(applyItemBox);
//         //다 완성된 itemBox 요소를 ul에 추가
//         parentElement2.appendChild(itemBox);
//         list2.append(parentElement2);
//
//         applyButton.addEventListener('click', function (event) {
//             let closestBox = event.target.closest('.itemBox');
//             let title = closestBox.querySelector('h3').textContent;
//             console.log(title)
//             window.location.href = "raffle.html?raffle=" + encodeURIComponent(title);
//         });
//     }}
//
//
//
// /*페이지 번호 버튼 클릭 이벤트*/
//
//
// let pageNumberBut2 = document.querySelectorAll('.number-button2')
// pageNumberBut2.forEach((numberButton) => {
//     numberButton.addEventListener('click', (e) => {
//         setPageOf_liked(+e.target.innerHTML);
//     });
// });
//
//
//
// const moveSelectedPageHighlight2 = () => {
//     const pageNumberButtons2 = document.querySelectorAll('.number-button2');
//     pageNumberButtons2.forEach((numberButton) => {
//         if (numberButton.classList.contains('selected')) {
//             numberButton.classList.remove('selected');
//         }
//     });
//     pageNumberButtons2[likedPage - 1].classList.add('selected');
// };
//
//
//
// setPageButtons2();
//
// setPageOf_liked(likedPage);
//
// prevButton2.addEventListener('click', () => {
//     if (likedPage > 1) {
//         likedPage-= 1;
//         setPageOf_liked(likedPage);
//         moveSelectedPageHighlight2();
//     }
// });
//
//
// nextButton2.addEventListener('click', () => {
//     if (likedPage < getTotalPageCount()) {
//         likedPage += 1;
//         setPageOf_liked(likedPage);
//         moveSelectedPageHighlight2();
//     }
// });
// /*페이지 번호 버튼 클릭 리스너*/
//
// pageNumberBut2.forEach((numberButton) => {
//     numberButton.addEventListener('click', (e) => {
//         likedPage = +e.target.innerHTML;
//         setPageOf_liked(likedPage);
//         moveSelectedPageHighlight2();
//     })
// });