
const moveMain = () => {
    location.href = "../html/main.html"
}
const moveMypage = () => {
    location.href = "../html/mypage.html";
}

const getInfoFromURL = () => {
    const urlParams = new URLSearchParams(window.location.search);

    const name = urlParams.get('name');
    const time = urlParams.get('time');
    const count = urlParams.get('count');
    const price = urlParams.get('price');

    return {
        name: name,
        time: time,
        count: count,
        price: price
    };
}

const info = getInfoFromURL();

const name = document.getElementById('name');
const time = document.getElementById('time');
const count = document.getElementById('count');
const price = document.getElementById('price');
const total = document.getElementById('total');

name.innerText = info.name;
time.innerText = info.time;
count.innerText = '총 ' + info.count+'매';
price.innerText = info.price;


let res = info.price.split(" ");
let first = res[0].split(',');
let str = first[0]+first[1];
total.innerText = (Number(str) + 3000).toLocaleString() + ' 원';


const getPay = () => {

    //가맹점 식별코드
    const IMP = window.IMP;
    IMP.init("imp58565627");
    IMP.request_pay({
        pg : 'html5_inicis',
        pay_method : 'card',
        merchant_uid : 'merchant_' + new Date().getTime(), //주문번호
        name : name.innerText,
        amount : Number(str) + 3000, //결제 가격
        buyer_email : 'jane@naver.com', //구매자 정보
        buyer_name : '김예린',
        buyer_tel : '010',
        buyer_addr : '파주',
        buyer_postcode : '11111',
        m_redirect_url : 'http://localhost:63342/koun_frontend/html/main.html'
    }, function(rsp) {
        if ( !rsp.success ) {
            //결제 시작 페이지로 리디렉션되기 전에 오류가 난 경우
            //개발 단계에서 실제 거래를 생성시키지 않기 위해 결제가 완료된 것처럼 처리
            var msg = '결제가 완료되었습니다.'

            alert(msg);
            moveMain();
        }
    });

}
