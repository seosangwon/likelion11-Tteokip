let iddata = "김멋사";
let maildata = "ek******@n****.com";

let name = document.getElementById('nameid');
let nameinfo = document.getElementById('nameinfo');
let mailinfo = document.getElementById('mailinfo')

name.innerText = iddata;
nameinfo.innerText = iddata;
mailinfo.innerText = maildata;
/*공연정보 페이지네이션*/

/**
 * 필요한 페이지 번호 개수 구하기
 * @returns {number} - 필요한 페이지 번호 개수
 */
/*필요한 페이지 번호 개수*/
/*
const getTotalPageCount = async () => {
    try {
        const data = await getData();
        console.log('mypage.js');
        const arrayLength = data.length;

        return Math.ceil(arrayLength / count_per_page);

    } catch (error) {
        throw error;
    }
};
*/

/*하이퍼링크*/

function moveMain() {
    location.href = "/main.html";
}
function moveMypage() {
    location.href = "/mypage.html";
}

function moveResult() {
    location.href = "/resultPopup.html"
}
