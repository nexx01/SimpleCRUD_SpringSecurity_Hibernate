const requestUrl = ' http://localhost:8088/admin/users'
// const requestUrl = 'https://jsonplaceholder.typicode.com/users'
console.log("Script is work!!!!!")
let allUsers;


function sendRequest(method, url, body = null) {
    return fetch(url,).then(response=>{
        return response.json()
    })
}


sendRequest('GET', requestUrl)
    .then(data => {
        var table_obj = $('table');
        $.each(data, function(index, result){
            var table_row = $('<tr>', {});
            var table_cell1 = $('<td>', {html: result.firstName});//result.yourDataAttributes
            var table_cell2 = $('<td>', {html: result.lastName});
            var table_cell3 = $('<td>', {html: result.age});
            table_row.append(table_cell1,table_cell2,table_cell3);
            table_obj.append(table_row);
        })

    })
    .catch(err => console.log(err))



// sendRequest('GET', requestUrl)
//     .then(data => {
//         var table_obj = $('table');
//         $.each(data, function(index, result){
//             var table_row = $('<tr>', {});
//             var table_cell1 = $('<td>', {html: result.firstName});//result.yourDataAttributes
//             var table_cell2 = $('<td>', {html: result.lastName});
//             var table_cell3 = $('<td>', {html: result.age});
//             table_row.append(table_cell1,table_cell2,table_cell3);
//             table_obj.append(table_row);
//         })
//
//     })
//     .catch(err => console.log(err))
