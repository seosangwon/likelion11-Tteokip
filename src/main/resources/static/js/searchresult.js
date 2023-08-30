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




let detaildata;


const baseUrl = "http://ec2-3-34-90-9.ap-northeast-2.compute.amazonaws.com:8080";

//콘서트 제목으로 모든 아이템 가져오기
const getdetailinfo = () => {
    axios.get(baseUrl + '/api/items/search',{
        params: {
            itemName: getSearchTermFromURL()
        }
    }).then(response =>{
        detaildata = response.data;

        placeinfo.innerText = detaildata.venue;
        dateinfo.innerText = detaildata.dateTime;
        infotext2.innerText = detaildata.runningTime;
        infotext3.innerText = detaildata.artist;
        infotext1.innerText = detaildata.ageRequirement;
        posterimg.src=detaildata.post;

        let updatedate = detaildata.uploadTime;
        let itemid = detaildata.id;

        afterDetailDataLoaded();

        const date1String = '2023-08-19';
        const date2String = updatedate;

        const date1 = new Date(date1String);
        const date2 = new Date(date2String);

        const timeDifference = date1 - date2;
        const daysDifference = timeDifference / (1000 * 60 * 60 * 24);

        realdday = 5 - (daysDifference);

        if(realdday == 0){
            dDay.innerText = "[D-day]";
        }else if(realdday<0){
            dDay.innerText = "[마감]";
            document.querySelector('.sideBtnWrap').style.display="none";
        }else{
            dDay.innerText = "[D-"+ realdday+"]";
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


        for (let i = 0; i <= 6; i++) {
            let seat = document.getElementById(`Seat${i}`);
            seat.innerText = seatdata[i].sectionName;

            let price = document.getElementById(`Price${i}`);
            price.innerText = seatdata[i].price+ '원';
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
