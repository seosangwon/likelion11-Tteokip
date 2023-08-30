const ul = document.querySelector(".rel-list");
const searchInput = document.querySelector("#search");
const relContainer = document.querySelector(".list");

const baseUrl = "http://ec2-3-34-90-9.ap-northeast-2.compute.amazonaws.com:8080";


const checkInput = () => {
    const beforeInput = searchInput.value;
    timer(beforeInput);
}

const timer = (beforeInput) => {
    setTimeout(() => {

        if(searchInput.value === beforeInput) {
            loadData(searchInput.value);
            checkInput();

        } else {
            checkInput();
        }

        if(searchInput.value === "") {
            relContainer.classList.add("hide");
        } else {
            relContainer.classList.remove("hide");
        }
    }, 1000);
}


const loadData = (input) => {
    axios.get(baseUrl + '/api/items/all')
        .then((response) => {
            const data = response.data.map(item => item.itemName);
            const filteredData = data.filter(item => item.includes(input));
            fillSearch(filteredData);
        })
        .catch((error) => {
            console.log(error);
        })
}

const fillSearch = (suggestArr) => {
    ul.innerHTML = "";
    suggestArr.forEach((el, idx) => {
        const li = document.createElement("li");
        li.innerHTML = el;
        ul.appendChild(li);
        li.addEventListener("click", function (event){
            //searchInput.value = event.target.innerText;
            let searchTerm = event.target.innerText;
            window.location.href = 'detail.html?search=' + encodeURIComponent(searchTerm);
        })
    })
}


checkInput();

/*차트 클릭시 페이지 전환*/
const cards = document.querySelectorAll('.card');

cards.forEach(function(card) {
    card.addEventListener("click", function (event) {
        let title = card.querySelector('.card-title h3').textContent;
        window.location.href = 'detail.html?search=' + encodeURIComponent(title);
    });
});

