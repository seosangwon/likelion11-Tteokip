const baseUrl = "http://13.124.88.252:8080";

const logo = document.getElementById('logo');
logo.onclick = moveMain;
function moveMain() {
    location.href = "../html/main.html";
}

const signupBtn = document.getElementById('signupBtn');
signupBtn.onclick = signUp;

function signUp(){

    const name = document.getElementById('name').value;
    const pw = document.getElementById('pw').value;
    const mail = document.getElementById('mail').value;

    axios.post(baseUrl + `/api/user/signup`, {
        "userName": name,
        "userEmail" : mail,
        "password": pw
    }).then(function (response) {
        alert('회원가입이 완료되었습니다.');
        window.location.href = "../html/main.html";
    }).catch(function (error) {
        console.log("error",error)
    })
}


