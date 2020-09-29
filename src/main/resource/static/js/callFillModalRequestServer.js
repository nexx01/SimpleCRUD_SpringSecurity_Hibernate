
$('document').ready(function (){
    $('.table .btn').on('click', function (event) {
        event.preventDefault();


        $('#edit-modal').modal('show');
    });






    $('#btnEditUser').on('click', function (event) {
        event.preventDefault();


        $('#edit-modal').modal('show');
    });




    $('#edit').on('click', function (event) {
        event.preventDefault();
        alert("Work1")


        $('#edit-modal').modal('show');
    });

});





// $('document').ready(function (){
//     $('.table .btn').on('click', function (event) {
//         event.preventDefault();
//
//         var href = $(this).attr('href');
//
//         $.get(href, function (user, status) {
//             $('#idEdit').val(user.id);
//             $('#firstNameEdit').val(user.firstName);
//             $('#lastNameEdit').val(user.lastName);
//             $('#emailEdit').val(user.email);
//         })
//         $('#edit-modal').modal();
//     });
// });