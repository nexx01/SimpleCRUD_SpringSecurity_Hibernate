const requestUrl = 'http://localhost:8088/admin/users'
const requestUrlRoles = 'http://localhost:8088/admin/roles'
let allUsers;



function sendRequest(method, url, body = null) {
    return fetch(url,).then(response => {
        return response.json()
    })
}




$(document).ready(function () {
//
    //call the ajax here
    // console.log(JSON.parse(JSON.stringify(data)));
    // var data=JSON.parse(JSON.stringify(data));
    function sendRequest(method, url, body = null) {
        return fetch(url,).then(response => {
            return response.json()
        })
    }


    sendRequest('GET', requestUrl)
        .then(data => {
            console.log(data);
            data.forEach(function (user) {
                $("#tData").append('<tr id="' + user.id +'">' +
                    "<td>" + user.id + "</td>" +
                    "<td>" + getValueWithoutNull(user.firstName) + "</td>" +
                    "<td>" + getValueWithoutNull(user.lastName) + "</td>" +
                    "<td>" + user.age + "</td>" +
                    "<td>" + user.email + "</td>" +
                    "<td type='password'>" + user.password + "</td>" +
                    "<td>" + getRolesUserString(user) + "</td>" +
                    '<td><button type="button" class="btn btn-info" id="btnMainPage" value="edit" data-id="' + user.id + '">Edit</button></td>' +
                    '<td><div th:unless="${#authentication.getName() == ' + user.email + '"><button type="button"  class="btn btn-danger "  id="btnMainPage" value="delete" data-id="' + user.id + '">Delete</button></div></td>'
                    + "</tr>");
            });
        })
        .catch(err => console.log(err))

});







let getValueWithoutNull = function (val) {
    if (val) {
        return val
    } else {
        return ""
    }
}

let getRolesUserString = function (user) {
    let rolesUser = '';
    user.roles.forEach(function (role) {
        let roleName = role.roleName
        rolesUser = rolesUser + roleName.substring(5, roleName.length) + ', '
    });
    return rolesUser.substring(0, rolesUser.length - 2)
}


/*Работайте, по возможности, с всплывающим событием.
 Так вы застрахуетесь от проблем при динамическом добавлении элементов
*Если кнопки созданы не динамически(без javascript),
 ищутся все элементы по селектору в момент выполнения инструкции,
 а затем им устанавливается слушатель на событие.
 Всё, что добавлено после этого, не имеет такого слушателя.
* */
// $(document).on('click', '#btnEditUser', function (event) {
//     console.log($(this)); // это и есть наш элемент с классом butt
//
//
//     let dropdown = document.getElementById('selectInModal');
//     dropdown.length = 0;
//
//     let defaultOption = document.createElement('option');
//     defaultOption.text = 'Choose role/roles';
//
//     dropdown.add(defaultOption);
//     dropdown.selectedIndex = 0;
//
//     fetch(requestUrlRoles)
//         .then(
//             function(response) {
//                 if (response.status !== 200) {
//                     console.warn('Looks like there was a problem. Status Code: ' +
//                         response.status);
//                     return;
//                 }
//
//                 // Examine the text in the response
//                 response.json().then(function(data) {
//                     let option;
//
//                     for (let i = 0; i < data.length; i++) {
//                         option = document.createElement('option');
//                         option.text = data[i].roleName;
//                         option.value = data[i].roleName;
//                         dropdown.add(option);
//                     }
//                 });
//             }
//         )
//         .catch(function(err) {
//             console.error('Fetch Error -', err);
//         });
//
//     // $('[name = "editModal"]').modal('show');
//     //
//
//     console.log("ID выбранного юзера(и ID строки)"+$(this).attr('data-id'))
//     // $('.modal').modal(); //открываем все классы модаль
//     let requestUrlgetOne=requestUrl.concat("/").concat($(this).attr('data-id'))
//     console.log("Запрос по адресу"+requestUrlgetOne)
//     $(document).ready(function () {
//     sendRequest('GET',requestUrlgetOne)
//         .then(data => {
//             console.log(data);
//                 $('#idEdit').val(data.id);
//                 $('#firstNameEdit').val(data.firstName);
//                 $('#lastNameEdit').val(data.lastName);
//                 $('#ageEdit').val(data.age);
//                 $('#emailEdit').val(data.email);
//                 $('#selectInModal').selectpicker();
//
//
//
//
//            // sendRequest('GET',requestUrlRoles)
//                 // .then(roles=>{
//                 //     console.log(roles)
//                 //     roles.forEach(function (role){
//                 //             $("#selectInModal").append(
//                 //                 $('<option value="1">New option</option>')
//                 //             );
//                 //         });
//                 //
//                 //     });
//                 });
//
//     $('#edit-modal').modal();
//         })
// })
//       //  .catch(err => console.log(err))
//
//
//
//
//

