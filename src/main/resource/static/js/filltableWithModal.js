const requestUrl = ' http://localhost:8088/admin/users'
let allUsers;


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
                $("#tData").append("<tr>" +
                    "<td>" + user.id + "</td>" +
                    "<td>" + getValueWithoutNull(user.firstName) + "</td>" +
                    "<td>" + getValueWithoutNull(user.lastName) + "</td>" +
                    "<td>" + user.age + "</td>" +
                    "<td>" + user.email + "</td>" +
                    "<td type='password'>" + user.password + "</td>" +
                    "<td>" + getRolesUserString(user) + "</td>" +
                    '<td><button type="button" class="btn btn-info" value="edit" data-id="' + user.id + '">Edit</button></td>' +
                    '<td>   <button type="button" class="btn btn-info" data-toggle="modal" data-target="#edit-modal">Edit\n' +
                    '                                </button></td>' +
                    '<td><div th:unless="${#authentication.getName() == ' + user.email + '"><button type="button" class="btn btn-danger " data-toggle="modal" data-target="#deleteModal" value="delete" data-id="' + user.id + '">Delete</button></div></td></tr>'
                    + "</tr>");
            });
        })
        .catch(err => console.log(err))


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
    $(document).on('click', '.btn', function (event) {
        console.log($(this)); // это и есть наш элемент с классом butt
        $('[name = "editModal"]').modal('show');

        $('#edit-modal').modal();


        $('.modal').modal();

    });



});


