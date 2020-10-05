const requestUrl = 'http://localhost:8085/api'
const requestUrlUserApi = 'http://localhost:8085/userApi'
const requestUrlUsers = requestUrl.concat("/users")
const requestUrlRoles = requestUrl.concat("/roles")


$(document).ready(function () {
    let requestOptions = {
        method: 'GET',
        body: null,
        redirect: 'follow'
    };

    fillupNavBar(sendFetchRequest(requestUrlUserApi, requestOptions));
    fillUsersTable(sendFetchRequest(requestUrlUsers, requestOptions));
});




let fillUsersTable = function (listAllUsers) {
    listAllUsers.then(function (data) {
        $("#tData").append('<thead>' +
            '<th th:text="id">ID</th>\n' +
            '<th th:text="#{user.firstName}">First Name</th>' +
            '<th th:text="#{user.lastName}">Last Name</th>' +
            '<th th:text="#{user.age}">Age</th>' +
            '<th th:text="#{user.email}"> E-mail</th>' +
            '<th th:text="#{user.password}"> Password</th>' +
            '<th th:text="#{user.role}"> Roles</th>' +
            '<th th:text="#{user.edit}">Edit</th>' +
            '<th th:text="#{user.delete}">Delete</th>' +
            '</tr>\n' +
            '</thead>'
        );


        data.forEach(function (user) {
            $("#tData").append(
                '<tbody>' +
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
                + "</tr>" +
                ' </tbody>'
            );

        })
    })
}



