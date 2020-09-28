const requestUrl = ' http://localhost:8088/admin/users'
alert("dsdded")


function sendRequest(method, url, body=null) {
    const  headers ={
        'Content-Type': 'application/json'
    }
    return fetch(url, {
        method:method,
        body:JSON.stringify(body),
        headers:headers
    }).then(response=>{
        if(response.ok) {
            return response.json()
        }
        return response.json().then(error=>{
            const  e= new Error('Что то пошло не так')
            e.data=error
            throw e
        })
    })
}


const body = {"firstname": "Peter",
    "lastname": "Peter",
    "email": "Peteer9@sss.com",
    "password": "Peter"
}

sendRequest('POST', requestUrl, body)
    .then(data=>console.log(data))
    .catch(err => console.log())


// var 2
// var myHeaders = new Headers();
// myHeaders.append("Content-Type", "text/plain");
// myHeaders.append("Cookie", "__cfduid=d28a1e326be283e5126a739d27f3f1ccf1601280227");
//
// const user = {"firstname": "Peter",
//     "lastname": "Peter",
//     "email": "Peteer10@sss.com",
//     "password": "Peter"
// }
//
//
// var requestOptions = {
//     method: 'POST',
//     headers: myHeaders,
//     body: user,
//     redirect: 'follow'
// };
//
// fetch("http://localhost:8088/admin/users", requestOptions)
//     .then(response => response.json())
//     .then(result => console.log(result))
//     .catch(error => console.log('error', error));