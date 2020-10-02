


$('#formEditUser').submit(function () {
    let valueButton = $('#btnModalUser').val()
    let idUser = $('#idEdit').val();

    switch (valueButton) {
        case "delete":
            deleteUser(idUser)
            break;
        case "save":
            updateUser(getUser(idUser));
            break;
        case "add":
            addUser(getUser(idUser));
            break;
        default:
            alert("Что то пошло не так!")
    }
});


let deleteUser = function (idUser) {
    let requestOptions = {
        method: 'DELETE',
        body: null,
    };
    sendFetchRequest(requestUrlUsers.concat("/").concat(idUser), requestOptions)
        .then(function (data){
        console.log("successful delete")})
}



let updateUser = function (user) {

    let userJSON = JSON.stringify(user)

    console.log("updater userJSON" + userJSON)
    let myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    myHeaders.append("Cookie", "__cfduid=d28a1e326be283e5126a739d27f3f1ccf1601280227");

    let requestOptions = {
        method: 'PUT',
        headers: myHeaders,
        body: userJSON,
        redirect: 'follow'
    };

    sendFetchRequest(requestUrlUsers.concat("/").concat(user.id), requestOptions)
        .then(function (data){
            console.log("successful updater")})
    refreshDataTable(userJSON)
}



let addUser = function (user) {
    let userJSON = JSON.stringify(user)

    let myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    myHeaders.append("Cookie", "__cfduid=d28a1e326be283e5126a739d27f3f1ccf1601280227");

    let requestOptions = {
        method: 'POST',
        headers: myHeaders,
        body: userJSON,
        redirect: 'follow'
    };

    sendFetchRequest(requestUrlUsers, requestOptions).then(function (data){
        console.log("successful add")})

    refreshDataTable(userJSON)
}





let refreshDataTable = function (user) {
    let html = "";

    html = html + '<tr><td>' + user.id + '</td>\n' +
        '        <td>' + user.firstName + '</td>\n' +
        '        <td>' + user.lastName + '</td>\n' +
        '        <td>' + user.age + '</td>' +
        '        <td>' + user.email + '</td>' +
        '        <td>' + getRolesUserString(user) + '</td>' +
        '        <td><button type="button" class="btn btn-info btn-primary label" data-toggle="modal" data-target="#editModal" value="edit" data-id="' + user.id + '">Edit</button></td>' +
        '        <td><div th:unless="${#authentication.getName() == ' + user.email + '"><button type="button" class="btn btn-danger btn-primary label" data-toggle="modal" data-target="#deleteModal" value="delete" data-id="' + user.id + '">Delete</button></div></td></tr>';

    $("tr#".concat(user.id)).replaceWith(html);

    $('[name = "#modalUser"]').modal('hide');
}



let getUser = function (idUser) {

    let rolesSelected = [];
    console.log("idSelectedUser" + idUser)
    $('#selectInModal option:selected').each(function (i, selected) {
        let roleSelected = {
            id: $(selected).val(),
            roleName: $(selected).text()
        };
        rolesSelected.push(roleSelected)
    });

    let user = {
        id: idUser,
        firstName: $('#firstNameEdit').val(),
        lastName: $('#lastNameEdit').val(),
        age: $('#ageEdit').val(),
        email: $('#emailEdit').val(),
        password: $('#passwordEdit').val(),
        roles: rolesSelected
    };

    return user;
}

