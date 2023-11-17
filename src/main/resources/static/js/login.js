const logo = document.getElementById('logo');
logo.onclick = moveMain;
function moveMain() {
    location.href = "../html/main.html";
}

const baseUrl = "http://13.124.88.252:8080";

const loginBtn = document.getElementById('loginBtn');
loginBtn.onclick = login;

function login() {

    const loginPw = document.getElementById('loginPw').value;
    const loginMail = document.getElementById('loginMail').value;

    axios.post(baseUrl + "/api/user/form_login", {
        "userEmail" : loginMail,
        "password": loginPw
    }).then(function (response) {
        console.log(response.data);
        if (response.data.accessToken) {

            localStorage.setItem('login-token', response.data.accessToken);
            localStorage.setItem('user-id', response.data.userId);
            const expiresAt = new Date().getTime() + 3600000; // 1시간 = 3600000밀리초
            localStorage.setItem('expires_at', expiresAt);
            alert('로그인이 완료되었습니다.');
            window.location.href = "../html/main.html";

        }



    }).catch(function (error) {
        console.log("error")
        if(error.response.status == '500'){
            alert('로그인에 실패하였습니다.')
            document.getElementById('loginPw').value = '';
            document.getElementById('loginMail').value ='';
        }

    })
}

