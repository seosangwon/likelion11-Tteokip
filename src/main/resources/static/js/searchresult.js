//main->detail
// URL에서 main에서 검색한 단어를 가져오기
function getSearchTermFromURL() {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get('search');
}


let dateinfo = document.querySelector('.dateinfo');
let placeinfo = document.querySelector('.placeinfo');

let infotext1 = document.getElementById('veiwing_age');

let dDay = document.querySelector('.d-day');
let infotext2 = document.getElementById('veiwing_time');
let infotext3 = document.getElementById('concertPerformer');
let posterimg = document.querySelector('.posterBoxImage');
const likeIdBox = document.querySelector('.posterBox');

let detaildata;


const baseUrl = "http://13.124.88.252:8080";

//콘서트 제목으로 모든 아이템 가져오기
const getdetailinfo = () => {
    axios.get(baseUrl + '/api/items/search',{
        params: {
            itemName: getSearchTermFromURL(),
            userId: localStorage.getItem('user-id')
        }
    }).then(response =>{
        detaildata = response.data;
        console.log(detaildata)
        placeinfo.innerText = detaildata.venue;
        dateinfo.innerText = detaildata.dateTime;
        infotext2.innerText = detaildata.runningTime;
        infotext3.innerText = detaildata.artist;
        infotext1.innerText = detaildata.ageRequirement;
        posterimg.src=detaildata.post;
        //이미 좋아요를 한 상태라면
        if(detaildata.userLike){
            fill.style.display = "flex";
            empty.style.display = "none";
        }else{ // 좋아요를 하지 않은 상태라면
            fill.style.display = "none";
            empty.style.display = "flex";
        }

        let itemid = detaildata.id;
        posterimg.id = detaildata.id;
        likeIdBox.id = detaildata.likeId;

        afterDetailDataLoaded();

        let now = new Date();
        let today_year = now.getFullYear();
        let today_month = now.getMonth() + 1;
        let today_date = now.getDate();

        let uploadTime = detaildata.uploadTime;
        let update_year = uploadTime.split('-')[0];
        let update_month = uploadTime.split('-')[1];
        let update_date = uploadTime.split('-')[2];

        if (today_year < update_year) {
            today_month += 12;
            let day30 = [2, 4, 6, 9, 11];
            let day31 = [1, 3, 5, 7, 8, 10, 12];
            if ((today_month < update_month) && (day30.includes(update_month))) {
                if (today_month != 2) {
                    today_date += 30;
                } else {
                    if ((today_year % 4 == 0 && today_year % 100 != 0) || today_year % 400 == 0) {
                        today_date += 29;
                    } else {
                        today_date += 28;
                    }
                }
            } else if (today_month < update_month && day31.includes(update_month)) {
                today_date += 31;
            }
        }



        let realDday = 5 - (today_date - update_date);

        if(realDday == 0){
            dDay.innerText = "[D-day]";
        }else if(realDday<0 || realDday >5 ||today_year > update_year || today_month > update_month){
            dDay.innerText = "[마감]";
            document.querySelector('.sideBtnWrap').style.display="none";
        }else{
            dDay.innerText = "[D-"+ realDday +"]";
        }


        getseatinfo(itemid);


    }).catch(function (error) {
        console.log(error);
    })
}

const getseatinfo = (id) => {
    axios.get(baseUrl + '/api/sections/item',{
        params: {
            itemId: id
        }
    }).then(response =>{
        let seatdata = response.data;
        console.log(seatdata)


        for (let i = 0; i <= 6; i++) {
            let seat = document.getElementById(`Seat${i}`);
            seat.innerText = seatdata[i].sectionName;

            let price = document.getElementById(`Price${i}`);
            price.innerText = seatdata[i].price.toLocaleString()+ ' 원';
        }



    }).catch(function (error) {
        console.log(error);
    })
}

// 검색어로 detail페이지로 이동해 결과를 렌더링하는 함수
function renderSearchResult() {
    const searchTerm = getSearchTermFromURL();
    const searchResult = document.getElementById('searchResult');
    searchResult.innerText = searchTerm;

}
window.addEventListener('DOMContentLoaded', () => {
    renderSearchResult();
    getdetailinfo();

});
