
/*하이퍼링크*/


const goraffle = document.getElementById('goRaffle');
const timetable = document.getElementById('timeTableBtn');

timetable.addEventListener("click",function(){
    timetable.style.backgroundColor = '#26DDB1';
    timetable.style.color = '#FFFFFF';
    goraffle.style.backgroundColor = '#26DDB1';
    goraffle.id = 'goraffle';
/*    else if(timetable.style.backgroundColor == '#FFFFFF') {
        goraffle.style.backgroundColor = '#8C9091';
    }*/
})

goraffle.addEventListener("click", function (){
    if(goraffle.id == 'goraffle') {
        let applybtn = searchResult.innerText;
        window.location.href = 'raffle.html?raffle=' + encodeURIComponent(applybtn);
    }
    else {
        alert('회차를 선택해주세요.')
    }
})



function moveMypage() {
    location.href = "../html/mypage.html";
}

function moveMain() {
    location.href = "../html/main.html"
}

/*calendar*/
function afterDetailDataLoaded() {

    const dateString = detaildata.dateTime;

// 문자열을 공백을 기준으로 분할
    const [datePart, timePart] = dateString.split(' ');

// 날짜 부분을 연도, 월, 일로 분할
    const [year, month, day] = datePart.split('-').map(Number);

    let today = new Date(year, month-1, day);
    let date = new Date(year, month, day);

    buildCalendar(today,date);

    let timeTableBtn = document.querySelector('.timeTableBtn');
    let prevBtn = document.querySelector('.premonth');
    let nextBtn = document.querySelector('.nextmonth');
    //prevBtn.addEventListener('click', () => prevCalendar(today,date));
    //nextBtn.addEventListener('click', () => nextCalendar(today,date));
    timeTableBtn.innerText = "1회 " + timePart;

}


function prevCalendar(today,date) {
    today = new Date(today.getFullYear(), today.getMonth() - 1, today.getDate());
    buildCalendar(today,date);
}

function nextCalendar(today,date) {
    today = new Date(today.getFullYear(), today.getMonth() + 1, today.getDate());
    buildCalendar(today,date);
}

/*현재 달 달력 만들기*/

function buildCalendar(today,date){

    let doMonth = new Date(today.getFullYear(),today.getMonth(),1);
    //이번 달의 첫째 날,
    let lastDate = new Date(today.getFullYear(),today.getMonth()+1,0);
    //이번 달의 마지막 날
    let tbCalendar = document.getElementById("calendar");
    let tbCalendarYM = document.getElementById("tbCalendarYM");
    tbCalendarYM.innerHTML = today.getFullYear() + ". " + (today.getMonth() + 1) ;


    while (tbCalendar.rows.length > 2) {
        //열을 지워줌
        //기본 열 크기는 body 부분에서 2로 고정되어 있다.
        tbCalendar.deleteRow(tbCalendar.rows.length-1);
    }

    let row = null;
    row = tbCalendar.insertRow();
    row.className = "dateList";
    //테이블에 새로운 열 삽입
    let cnt = 0;// count, 셀의 갯수를 세어주는 역할
    // 1일이 시작되는 칸을 맞추어 줌


    for (i=0; i<doMonth.getDay(); i++) {
        cell = row.insertCell();//열 한칸한칸 계속 만들어주는 역할
        cell.id = "disabled";
        cnt = cnt + 1;
    }
    /*달력 출력*/
    for (i=1; i<=lastDate.getDate(); i++) {
        cell = row.insertCell();//열 한칸한칸 계속 만들어주는 역할
        cell.innerHTML = i;
        cell.className = "ableDate";
        cnt = cnt + 1;


        /*일요일 구하기*/
        if (cnt % 7 == 1) {
            cell.innerHTML = "<font color=#F79DC2>" + i
            //1번째의 cell에만 색칠
        }

        /*토요일 구하기*/
        if (cnt%7 == 0){
            cell.innerHTML = "<font color=skyblue>" + i
            row = calendar.insertRow();
        }
        /*지정된 날짜에 색 칠하기*/

        if (today.getFullYear() == date.getFullYear()
            && today.getMonth() == date.getMonth()-1
            && i == today.getDate()) {


            cell.bgColor = "#26ddb1";//셀의 배경색
            cell.style.border = "1px solid #26ddb1";
            cell.style.borderRadius = "60px" ;
            cell.innerHTML = "<font color='white'>" + i
        }
    }
}



/*찜버튼-메인css에서 가져옴*/


const likeBtn = document.getElementById('likeBtn');
const empty = document.getElementById('emptyHeart');
const fill = document.getElementById('fillHeart');



likeBtn.addEventListener("click", function(){
    //좋아요 삭제할때
    if(fill.style.display == "flex"){
        fill.style.display = "none";
        empty.style.display = "flex";
        deleteLike(likeIdBox.id);
        console.log('좋아요삭제')


    }else{ //좋아요 생성할때
        fill.style.display = "flex";
        empty.style.display = "none";
        console.log('받아온 itemid: ',posterimg.id)
        makeLike(posterimg.id);

    }

})

const makeLike = (id) => {
    axios.post(baseUrl + "/api/likes/create", {
        "userId": localStorage.getItem('user-id'),
        "itemId": id,
    }).then(function (response) {
        console.log('좋아요 생성');
        console.log(response.data)
        likeIdBox.id = response.data.likeId;
    }).catch(function (error) {
        console.log("error")
    })
}

const deleteLike = (id) => {
    axios.delete(baseUrl + "/api/likes/delete", {
        params: {
            "likeId": id
        }
    }).then(function (response) {
        console.log('좋아요 삭제');
    }).catch(function (error) {
        console.log("error")
    })
}


const logOut = document.getElementById('out');

logOut.addEventListener('click', function(){
        localStorage.removeItem('login-token');
        localStorage.removeItem('user-id');
        window.location.href= '../html/login.html';

})