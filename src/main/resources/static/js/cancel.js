
function showPopup() {
    const popup = document.querySelector('#cancelpopup');

    popup.classList.add('filter');
    popup.classList.remove('hide');
}

function movetoPopup(hided,newpop) {
    const hidedpopup = document.querySelector(hided);
    hidedpopup.classList.add('hide');
    hidedpopup.classList.remove('filter');

    const newpopup = document.querySelector(newpop);
    newpopup.classList.add('filter');
    newpopup.classList.remove('hide');
}

function closePopup(closepopup) {
    const popup = document.querySelector(closepopup);
    popup.classList.add('hide');
    popup.classList.remove('filter');

}
/*

function alertmention() {
    const situation = document.qw;
}
*/

const CancelFunc = (id) => {
    console.log('마지막',id)
    axios.delete(baseUrl + '/api/raffles',{
        params : {
            raffleId : id
        }
    }).then(response => {
        movetoPopup('#cancelpopup2','#cancelpopup3');

    }).catch(function (error) {
        console.log(error);
    })
}

/*

const getcancelinfo = () => {
    axios.get(baseUrl + '/api/items/',{
        params: {
            itemName: getTitleFromURL()
        }
    }).then(response =>{
        //console.log(response.data);
        let canceldata = response.data;

        document.getElementById('venue').innerText = itemdata.venue;
        document.getElementById('dateTime').innerText = itemdata.dateTime;
        document.getElementById('postimg').src=itemdata.post;

        let itemid = itemdata.id;

        getdateinfo(date);


    }).catch(function (error) {
        console.log(error);
    })
}

const getdateinfo = (date) => {
    axios.get(baseUrl + '/api/sections/item',{
        params: {
            itemId: date
        }
    }).then(response =>{
        //console.log(response.data);
        let datedata = response.data;

/!*

        for (let i = 0; i <= 6; i++) {
            let seat = document.getElementById(`seat${i}`);
            seat.innerText = seatdata[i].sectionName;

            let price = document.getElementById(`price${i}`);
            price.innerText = seatdata[i].price+ ' 원';

            let seatQuantity = document.getElementById(`seatQuantity${i}`);
            seatQuantity.innerText = seatdata[i].seatQuantity+ '석';
        }

*!/


    }).catch(function (error) {
        console.log(error);
    })
}*/
