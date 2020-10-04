
const requestUrl = 'http://localhost:8085/api'
const requestUrlUsers = requestUrl.concat("/users")
const requestUrlRoles = requestUrl.concat("/roles")



$(document).ready(function () {
    let requestOptions = {
        method: 'GET',
        body: null,
        redirect: 'follow'
    };

    fillupNavBar(sendFetchRequest(requestUrl.concat("/userApi"),requestOptions));
    fillUsersTable(sendFetchRequest(requestUrlUsers,requestOptions));
    leftNavBarAdmin();

});

// $(document).on('click', '#btnAdminPage', function (event) {
//             let requestOptions = {
//                 method: 'GET',
//                 body: null,
//                 redirect: 'follow'
//             };
//     document.getElementById('#contentPage').onclick = function() {
//         this.innerHTML = '';
//     }
//
//     fillPersonalData(sendFetchRequest(requestUrl.concat("/userApi"),requestOptions));
//     $("#tData tr").remove();
//     fillUsersTable(sendFetchRequest(requestUrlUsers,requestOptions));
// })
//
// $(document).on('click', '#btnUserPage', function (event) {
//     let requestOptions = {
//         method: 'GET',
//         body: null,
//         redirect: 'follow'
//     };
//
//     $('#adminFragment').remove();
//     fillPersonalData(sendFetchRequest(requestUrl.concat("/userApi"),requestOptions))
// })






let fillUsersTable=function(listAllUsers) {
    listAllUsers.then(function (data) {
        $("#tData").append('<thead>' +
            '  <th th:text="id">ID</th>\n' +
            ' <th th:text="#{user.firstName}">First Name</th>' +
            '<th th:text="#{user.lastName}">Last Name</th>' +
            ' <th th:text="#{user.age}">Age</th>' +
            ' <th th:text="#{user.email}"> E-mail</th>' +
            ' <th th:text="#{user.password}"> Password</th>' +
            '<th th:text="#{user.role}"> Roles</th>' +
            '<th th:text="#{user.edit}">Edit</th>' +
            '<th th:text="#{user.delete}">Delete</th>' +
            '</tr>\n' +
            '</thead>'
        );


            data.forEach(function (user) {
            $("#tData").append(
              '  <tbody>'+
                '<tr id="' + user.id + '">' +
                "<td>" + user.id + "</td>" +
                "<td>" + getValueWithoutNull(user.firstName) + "</td>" +
                "<td>" + getValueWithoutNull(user.lastName) + "</td>" +
                "<td>" + user.age + "</td>" +
                "<td>" + user.email + "</td>" +
                "<td type='password'>" + user.password + "</td>" +
                "<td>" + getRolesUserString(user) + "</td>" +
                '<td><button type="button" class="btn btn-info" id="btnMainPage" value="edit" data-id="' + user.id + '">Edit</button></td>' +
                '<td><div th:unless="${#authentication.getName() == ' + user.email + '"><button type="button"  class="btn btn-danger "  id="btnMainPage" value="delete" data-id="' + user.id + '">Delete</button></div></td>'
                + "</tr>"+
       ' </tbody>'
            );

        })
    })
}



let leftNavBarAdmin = function () {
    $("#leftNavBar").append(
        '  <div class="row" >'+
        '   <div class="nav-col  btn-group-vertical btn-group-toggle btn-block">'+
        ' <button type="button" class="nav-item btn btn-white-50 active" id="btnAdminPage">'+
        '      <a class="nav-link active nav-fill"  >Admin</a>'+
        ' </button>'+
        '     <button type="button" class="nav-item btn btn-white-50 " id="btnUserPage"  >'+
        '    <a class="nav-link  nav-fill ">User</a>'+
        '   </button>'+
        '   </div>'+
        ' </div>'
    )
}



