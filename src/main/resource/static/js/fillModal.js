$(document).on('click', '#btnEditUser', function (event) {
    console.log($(this)); // это и есть наш элемент с классом butt


    let dropdown = document.getElementById('selectInModal');
    dropdown.length = 0;
    //
    let defaultOption = document.createElement('option');
    // defaultOption.text = 'Choose role/roles';
    //
    // dropdown.add(defaultOption);
    // dropdown.selectedIndex = 0;
    let requestUrlgetOne = requestUrl.concat("/").concat($(this).attr('data-id'))


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
                        option.value = data[i].roleName;
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
                    console.log(data);
                    $('#idEdit').val(data.id);
                    $('#firstNameEdit').val(data.firstName);
                    $('#lastNameEdit').val(data.lastName);
                    $('#ageEdit').val(data.age);
                    $('#emailEdit').val(data.email);
                    $('#selectInModal').selectpicker();


                   $.each(allRoles, function (indexRole, nameRole){
                       $.each(data.roles, function (indexRoleUser, roleUser){
                           console.log("nameRole "+nameRole+ " roleUser "+ roleUser.roleName)
                         if(nameRole===roleUser.roleName){


                         }
                       })
                   })


                    // response.json().then(function (data) {
                    //     console.log("hbjkh" + data)
                    // })

                    // sendRequest('GET',requestUrlRoles)
                    // .then(roles=>{
                    //     console.log(roles)
                    //     roles.forEach(function (role){
                    //             $("#selectInModal").append(
                    //                 $('<option value="1">New option</option>')
                    //             );
                    //         });
                    //
                    //     });
                });

            $('#edit-modal').modal();
        })
    })


})


// $('[name = "editModal"]').modal('show');
//


//  .catch(err => console.log(err))





