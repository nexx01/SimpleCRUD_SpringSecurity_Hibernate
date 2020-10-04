
let fillPersonalData = function (user) {
    // $("#tDataUserPage").remove();
    user.then(function (data) {
        $("#tData").append('<thead>' +
            '<th th:text="id">ID</th>\n' +
            '<th th:text="#{user.firstName}">First Name</th>\n' +
            ' <th th:text="#{user.lastName}">Last Name</th>\n' +
            '<th th:text="#{user.age}">Age</th>\n' +
            '<th th:text="#{user.email}"> E-mail</th>\n' +
            '<th th:text="#{user.password}"> Password</th>\n' +
            '<th th:text="#{user.role}"> Roles</th>\n' +
            '</tr>\n' +
            '</thead>' +

            '<tbody>' +
            '<tr id="' + data.id + '">' +
            "<td>" + data.id + "</td>" +
            "<td>" + getValueWithoutNull(data.firstName) + "</td>" +
            "<td>" + getValueWithoutNull(data.lastName) + "</td>" +
            "<td>" + data.age + "</td>" +
            "<td>" + data.email + "</td>" +
            "<td >" + data.password + "</td>" +
            "<td>" + getRolesUserString(data) + "</td>" +
            +"</tr>" +
            ' </tbody>'
        );
    })
}
