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
    location.href = "../html/main.html";
}
function moveMypage() {
    location.href = "../html/mypage.html";
}

function moveResult() {
    location.href = "../html/resultPopup.html";
}
const getuserinfo = () => {
    let token =  localStorage.getItem('login-token')
    let config = {
        headers: {
            Authorization: `Bearer ${token}`
        }
    }
    let id = localStorage.getItem('user-id')
    axios.get(baseUrl + `/api/user/${id}`,config
    ).then(response =>{
        //console.log(response.data);
        let username = document.getElementById('nameid');
        username.innerText = response.data.name;

        let username2 = document.getElementById('nameinfo');
        username2.innerText = response.data.name;

        let mailinfo = document.getElementById('mailinfo');
        mailinfo.innerText = response.data.email;

    }).catch(function (error) {
        console.log(error);
    })
}
window.addEventListener('DOMContentLoaded', () => {
    getuserinfo();
});


const logOut = document.getElementById('out');

logOut.addEventListener('click', function(){
    localStorage.removeItem('login-token');
    localStorage.removeItem('user-id');
    window.location.href= '../html/login.html';

})