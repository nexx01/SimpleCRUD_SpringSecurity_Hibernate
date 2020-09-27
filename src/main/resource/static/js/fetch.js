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


// sendRequest('GET', requestUrl)
//     .then(data=>console.log(data))
//     .catch(err => console.log())

// const body ={
//     name:'Vladilen',
//     age:'26'
// }



const body = {"firstname": "Peter",
    "lastname": "Peter",
    "email": "Peteer4@sss.com",
    "password": "Peter"
}

sendRequest('POST', requestUrl, body)
    .then(data=>console.log(data))
    .catch(err => console.log())