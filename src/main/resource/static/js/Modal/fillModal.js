let idEditUser;

$(document).on('click', '#btnMainPage', function (event) {
    let valueButton = $(this).attr('value')
    idEditUser = $(this).attr('data-id')


    switch (valueButton) {
        case "delete":
            setPropertiesButtonDelete();
            fillModalData();
            break;
        case "edit":
            setPropertiesButtonSave();
            fillModalData();
            break;
        case "newUser":
            resetForm();
            fillModalData()
            setPropertiesButtonNewUser();
            break;

        default:
            alert("Что то пошло не так!")
    }
    $('#modalUser').modal();
})


let setPropertiesButtonSave = function () {
    $($('#btnModalUser')).prop({
        'textContent': 'Save changed',
        'class': 'btn btn-primary',
        'type': "submit",
        'value': "save"
    })
}


let setPropertiesButtonDelete = function () {
    $($('#btnModalUser')).prop({
        'textContent': 'DELETE',
        'class': 'btn btn-danger',
        'type': "submit",
        'value': "delete"
    })
}


let setPropertiesButtonNewUser = function () {
    $($('#btnModalUser')).prop({
        'textContent': 'New User',
        'class': 'btn btn-primary',
        'type': "submit",
        'value': "add"
    })
}


let resetForm = function () {
    $('#formEditUser').each(function () {
        this.reset();
    });

}


let fillModalData = function () {
    $('#selectInModal').selectpicker('destroy')

    let dropdown = document.getElementById('selectInModal');
    dropdown.length = 0;
    let requestOptions = {
        method: 'GET',
        body: null,
        redirect: 'follow'
    };


    sendFetchRequest(requestUrlRoles, requestOptions)
        .then(data => {
            let allRoles = [];
            let option;
            for (let i = 0; i < data.length; i++) {
                option = document.createElement('option');
                option.text = data[i].roleName;
                option.id = "idRole" + data[i].roleName;
                option.selected = false
                option.value = data[i].id;
                allRoles.push(data[i].roleName)
                dropdown.add(option);
            }
            $('#selectInModal').selectpicker();
            //  $('#selectInModal').selectpicker('refresh');

            return allRoles;
        })
        .then(function (allRoles) {
                if (idEditUser) {
                    $(document).ready(function () {

                        let requestOptions = {
                            method: 'GET',
                            body: null,
                            redirect: 'follow'
                        };


                        sendFetchRequest(requestUrlUsers.concat("/").concat(idEditUser), requestOptions)
                            .then(data => {
                                let roles = []
                                $('#idEdit').val(data.id);
                                $('#firstNameEdit').val(data.firstName);
                                $('#lastNameEdit').val(data.lastName);
                                $('#ageEdit').val(data.age);
                                $('#emailEdit').val(data.email);

                                let option;


                                $.each(data.roles, function (index, roleUser) {
                                    let id = "#" + "idRole" + roleUser.roleName
                                    $(id).prop('selected', true);
                                    $('#selectInModal').selectpicker('refresh');
                                })
                            })
                    });
                }
            }
        )
}


