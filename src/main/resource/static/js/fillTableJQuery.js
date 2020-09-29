

const requestUrl = ' http://localhost:8088/admin/users'

$(document).ready(function () {
//
        //call the ajax here
        // console.log(JSON.parse(JSON.stringify(data)));
        // var data=JSON.parse(JSON.stringify(data));
        function sendRequest(method, url, body = null) {
            return fetch(url,).then(response=>{
                return response.json()
            })
        }

        sendRequest('GET', requestUrl)
            .then(data =>{
                console.log(data);
                data.forEach(function (user){
                    $("#tData").append("<tr>"+
                        "<td>"+user.id+"</td>"+
                        "<td>"+getValueWithoutNull(user.firstName)+"</td>"+
                        "<td>"+getValueWithoutNull(user.lastName)+"</td>"+
                        "<td>"+user.age+"</td>"+
                        "<td>"+user.email+"</td>"+
                        "<td type='password'>"+user.password+"</td>"+
                        "<td>"+getRolesUserString(user)+"</td>"+
                        '<td><button type="button" class="btn btn-info btn-primary label" data-toggle="modal" data-target="#edit-modal" value="edit" data-id="' + user.id + '">Edit</button></td>'+
                        '<td><div th:unless="${#authentication.getName() == ' + user.email + '"><button type="button" class="btn btn-danger btn-primary label" data-toggle="modal" data-target="#deleteModal" value="delete" data-id="' + user.id + '">Delete</button></div></td></tr>'
                        +"</tr>");
                });
            })
            .catch(err => console.log(err))
});



let getValueWithoutNull= function (val){
      if(val){
          return val
      } else {
          return ""
      }
}

let getRolesUserString = function (user) {
    let rolesUser='';
    user.roles.forEach(function (role){
        let roleName=role.roleName
        rolesUser=rolesUser+roleName.substring(5,roleName.length)+', '
    });
    return rolesUser.substring(0,rolesUser.length-2)
}


let button =function (){
    var $input = $('<input type="button" value="new button" />');
    $input.appendTo($("body"));
}











//VAR1
//Ajax using  withoutFetch


// $(document).ready(function () {
// //
//     $("#btn").click(function () {
//         //call the ajax here
//         $.get("http://localhost:8088/admin/users", function (data, status) {
//             console.log(JSON.parse(JSON.stringify(data)));
//             var data=JSON.parse(JSON.stringify(data));
//             data.forEach(function (dt){
//                 $("#tData").append("<tr>"+
//                     "<td>"+dt.id+"</td>"+
//                     "<td>"+dt.firstName+"</td>"+
//                     "<td>"+dt.lastName+"</td>"+
//                     "<td>"+dt.age+"</td>"+
//                     "<td>"+dt.email+"</td>"+
//                     "<td>"+dt.password+"</td>"
//
//                     +"</tr>"
//                 );
//             });
//         });
//     });
// });
