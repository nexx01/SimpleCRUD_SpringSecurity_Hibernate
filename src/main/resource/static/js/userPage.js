$(document).ready(function () {
    const requestUrlUserApi = 'http://localhost:8085/userApi'

    let requestOptions = {
        method: 'GET',
        body: null,
        redirect: 'follow'
    };

    let userPromise = sendFetchRequest(requestUrlUserApi, requestOptions);
    fillupNavBar(userPromise);
    fillPersonalData(userPromise)
})
