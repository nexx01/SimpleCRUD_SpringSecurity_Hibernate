
$('#formEditUser').submit(function(  ) {

    let rolesSelected = [];
    let idUser=$('#idEdit').val();



    $('#selectInModal option:selected').each(function(i, selected){
        let roleSelected ={
            id:$(selected).val(),
            roleName:$(selected).text()

        };
        rolesSelected.push(roleSelected)
    });

    console.log(rolesSelected)


    let user = {
        id:idUser,
        firstName: $('#firstNameEdit').val(),
        lastName: $('#lastNameEdit').val(),
        age: $('#ageEdit').val(),
        email: $('#emailEdit').val(),
        password: $('#passwordEdit').val(),
        roles: rolesSelected
    };

    let userJSON=JSON.stringify(user)

    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    myHeaders.append("Cookie", "__cfduid=d28a1e326be283e5126a739d27f3f1ccf1601280227");

    var requestOptions = {
        method: 'PUT',
        headers: myHeaders,
        body: userJSON,
        redirect: 'follow'
    };

    console.log(userJSON)
    fetch(requestUrl.concat("/").concat(idEditUser), requestOptions)
        .then(response => {return response.json()})
        .then(result => console.log(result))
        .catch(error => console.log('error', error));


    let html = "";
    html = html + '<tr><td>' + user.id + '</td>\n' +
        '        <td>' + user.firstName + '</td>\n' +
        '        <td>' + user.lastName + '</td>\n' +
        '        <td>' + user.age + '</td>' +
        '        <td>' + user.email + '</td>' +
        '        <td>' + getRolesUserString(user) + '</td>' +
        '        <td><button type="button" class="btn btn-info btn-primary label" data-toggle="modal" data-target="#editModal" value="edit" data-id="' + user.id + '">Edit</button></td>' +
        '        <td><div th:unless="${#authentication.getName() == ' + user.email + '"><button type="button" class="btn btn-danger btn-primary label" data-toggle="modal" data-target="#deleteModal" value="delete" data-id="' + user.id + '">Delete</button></div></td></tr>';
    $("tr#".concat(idUser)).replaceWith(html);
    $('[name = "#modalUser"]').modal('hide');
});