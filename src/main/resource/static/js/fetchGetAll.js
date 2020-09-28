const requestUrl = ' http://localhost:8088/admin/users'
// const requestUrl = 'https://jsonplaceholder.typicode.com/users'
console.log("Script is work!!!!!")


function sendRequest(method, url, body = null) {
    return fetch(url,).then(response=>{
        return response.json()
    })
}


sendRequest('GET', requestUrl)
    .then(data => console.log(data))
    .catch(err => console.log(err))




// var 2

// var myHeaders = new Headers();
// myHeaders.append("Cookie", "__cfduid=d28a1e326be283e5126a739d27f3f1ccf1601280227");
//
// var requestOptions = {
//     method: 'GET',
//     headers: myHeaders,
//     redirect: 'follow'
// };
//
// fetch("http://localhost:8088/admin/users", requestOptions)
//     .then(response => response.json())
//     .then(result => console.log(result))
//     .catch(error => console.log('error', error));