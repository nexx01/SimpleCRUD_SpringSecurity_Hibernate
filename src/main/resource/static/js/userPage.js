
$(document).ready(function () {
    const requestUrl = 'http://localhost:8085/api'

    let requestOptions = {
        method: 'GET',
        body: null,
        redirect: 'follow'
    };

    let userPromise=sendFetchRequest(requestUrl.concat("/userApi"),requestOptions);
    fillupNavBar(userPromise);
    fillPersonalData(userPromise)
})
