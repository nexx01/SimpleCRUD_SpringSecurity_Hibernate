let idEditUser;

$(document).on('click', '#btnMainPage', function (event) {
    let valueButton=$(this).attr('value')
    idEditUser=$(this).attr('data-id')


    switch (valueButton) {
        case "delete":
            setPropertiesButtonDelete();
            fillModalDataUser();
            break;
        case "edit":
            setPropertiesButtonSave();
            fillModalDataUser();
            break;
        case "newUser":
            resetForm();
            setPropertiesButtonSave();
            break;
        default:
            alert("Что то пошло не так!")
    }



    $('#modalUser').modal();
})




let setPropertiesButtonSave =function () {
    $($('#btnModalUser')).prop({
        'textContent': 'Save changed',
        'class': 'btn btn-primary',
        'type':"submit"
    })
}


let setPropertiesButtonDelete =function () {
    $($('#btnModalUser')).prop({
        'textContent': 'DELETE',
        'class': 'btn btn-danger',
        'type':"submit"
    })
}


let resetForm=function (){
    $( '#formEditUser' ).each(function(){
        this.reset();
    });

}


let fillModalDataUser = function () {
    $('#selectInModal').selectpicker('destroy')

    let dropdown = document.getElementById('selectInModal');
    dropdown.length = 0;
    //
    //  let defaultOption = document.createElement('option');


    let requestUrlgetOne = requestUrl.concat("/").concat(idEditUser)
    fetch(requestUrlRoles)
        .then(
            function (response) {
                if (response.status !== 200) {
                    console.warn('Looks like there was a problem. Status Code: ' +
                        response.status);
                    return;
                }

                let allRoles =[];

                // Examine the text in the response
                response.json().then(function (data) {
                    let option;
                    for (let i = 0; i < data.length; i++) {
                        option = document.createElement('option');
                        option.text = data[i].roleName;
                        option.id="idRole"+data[i].roleName;
                        option.selected=false
                        option.value = data[i].id;
                        allRoles.push(data[i].roleName)
                        dropdown.add(option);
                    }
                });

                return allRoles;
            }
        ).then(function (allRoles) {
        $(document).ready(function () {
            sendRequest('GET', requestUrlgetOne)
                .then(data => {
                    let roles=[]
                    $('#idEdit').val(data.id);
                    $('#firstNameEdit').val(data.firstName);
                    $('#lastNameEdit').val(data.lastName);
                    $('#ageEdit').val(data.age);
                    $('#emailEdit').val(data.email);
                    $('#selectInModal').selectpicker();
                    let option;


                    $.each(data.roles ,function (index,roleUser){
                        let id="#"+"idRole"+roleUser.roleName
                        $(id).prop('selected', true);
                        $('#selectInModal').selectpicker('refresh');
                })
                })
        });
    })
}


