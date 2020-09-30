$(document).on('click', '#btnEditUser', function (event) {
    console.log($(this)); // это и есть наш элемент с классом butt


    let dropdown = document.getElementById('selectInModal');
    dropdown.length = 0;

    let defaultOption = document.createElement('option');
    defaultOption.text = 'Choose role/roles';

    dropdown.add(defaultOption);
    dropdown.selectedIndex = 0;

    fetch(requestUrlRoles)
        .then(
            function(response) {
                if (response.status !== 200) {
                    console.warn('Looks like there was a problem. Status Code: ' +
                        response.status);
                    return;
                }

                // Examine the text in the response
                response.json().then(function(data) {
                    let option;

                    for (let i = 0; i < data.length; i++) {
                        option = document.createElement('option');
                        option.text = data[i].roleName;
                        option.value = data[i].roleName;
                        dropdown.add(option);
                    }
                });
            }
        )
        .catch(function(err) {
            console.error('Fetch Error -', err);
        });

    // $('[name = "editModal"]').modal('show');
    //

    console.log("ID выбранного юзера(и ID строки)"+$(this).attr('data-id'))
    // $('.modal').modal(); //открываем все классы модаль
    let requestUrlgetOne=requestUrl.concat("/").concat($(this).attr('data-id'))
    console.log("Запрос по адресу"+requestUrlgetOne)
    $(document).ready(function () {
        sendRequest('GET',requestUrlgetOne)
            .then(data => {
                console.log(data);
                $('#idEdit').val(data.id);
                $('#firstNameEdit').val(data.firstName);
                $('#lastNameEdit').val(data.lastName);
                $('#ageEdit').val(data.age);
                $('#emailEdit').val(data.email);
                $('#selectInModal').selectpicker();




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
//  .catch(err => console.log(err))





