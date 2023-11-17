/*
const data = [
    { reservdate: '2023.08.09' ,title: '라우브 내한공연(Lauv in Seoul)', concertdate: '2023.08.29(토) 오후 6시', concertcount: '2매', dDay: '[D-2]', postNumber: 1},
    { reservdate: '2023.08.09' ,title: '라우브 내한공연2', concertdate: '2023.08.29(토) 오후 6시', concertcount: '2매', dDay: '[D-Day]',postNumber: 2},
    { reservdate: '2023.08.09' ,title: '라우브 내한공연3', concertdate: '2023.08.29(토) 오후 6시', concertcount: '2매', dDay: '[D-2]',postNumber: 3},
    { reservdate: '2023.08.09' ,title: '라우브 내한공연4', concertdate: '2023.08.29(토) 오후 6시', concertcount: '2매', dDay: '[D-2]',postNumber: 4},
    { reservdate: '2023.08.09' ,title: '라우브 내한공연', concertdate: '2023.08.29(토) 오후 6시', concertcount: '2매', dDay: '[D-2]',postNumber: 5},
    { reservdate: '2023.08.09' ,title: '라우브 내한공연', concertdate: '2023.08.29(토) 오후 6시', concertcount: '2매', dDay: '[D-2]',postNumber: 6},
    { reservdate: '2023.08.09' ,title: '라우브 내한공연', concertdate: '2023.08.29(토) 오후 6시', concertcount: '2매', dDay: '[D-2]',postNumber: 7},
];
*/



const baseUrl = "http://13.124.88.252:8080";

/*
http://ec2-3-38-100-226.ap-northeast-2.compute.amazonaws.com:8080
*/

const getData = async () => {
    try {
        let token =  localStorage.getItem('login-token')
        const response = await axios.get(baseUrl + '/api/raffles/users', {
            params: {
                userId: localStorage.getItem('user-id')
            },headers: {Authorization: `Bearer ${token}`},
        });
        const responseData = response.data;
        console.log(responseData,'data')
        return responseData; // 전체 데이터 반환
    } catch (error) {
        console.log(error);
        throw error; // 에러를 다시 던져서 호출자가 처리하도록 함
    }
};

const likeData = async () => {
    try {
        let token =  localStorage.getItem('login-token')
        let config = {
            headers: {
                Authorization: `Bearer ${token}`
            }
        }
        let id = localStorage.getItem('user-id')
        const response = await axios.get(baseUrl + `/api/items/${id}`, config);

        const responseData = response.data; // response에서 데이터 추출
        console.log(responseData)

        return responseData; // 전체 데이터 반환
    } catch (error) {
        console.log(error);
        throw error; // 에러를 다시 던져서 호출자가 처리하도록 함
    }
};

