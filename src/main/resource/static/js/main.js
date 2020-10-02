
const requestUrl = 'http://localhost:8088/api/v1'
const requestUrlUsers = requestUrl.concat("/users")
const requestUrlRoles = requestUrl.concat("/roles")



$(document).ready(function () {

    let myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    myHeaders.append("Cookie", "__cfduid=d28a1e326be283e5126a739d27f3f1ccf1601280227");

    let requestOptions = {
        method: 'GET',
        headers: myHeaders,
        body: null,
        redirect: 'follow'
    };

    fillUsersTable(sendFetchRequest(requestUrlUsers,requestOptions));

});




let fillUsersTable=function(listAllUsers) {
    listAllUsers.then(function (data) {
        console.log(data)
        data.forEach(function (user) {
            $("#tData").append('<tr id="' + user.id + '">' +
                "<td>" + user.id + "</td>" +
                "<td>" + getValueWithoutNull(user.firstName) + "</td>" +
                "<td>" + getValueWithoutNull(user.lastName) + "</td>" +
                "<td>" + user.age + "</td>" +
                "<td>" + user.email + "</td>" +
                "<td type='password'>" + user.password + "</td>" +
                "<td>" + getRolesUserString(user) + "</td>" +
                '<td><button type="button" class="btn btn-info" id="btnMainPage" value="edit" data-id="' + user.id + '">Edit</button></td>' +
                '<td><div th:unless="${#authentication.getName() == ' + user.email + '"><button type="button"  class="btn btn-danger "  id="btnMainPage" value="delete" data-id="' + user.id + '">Delete</button></div></td>'
                + "</tr>");

        })
    })
}
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

