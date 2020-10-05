let fillupNavBar = function (user) {
    let aboutUser;
    user.then(function (data) {
        aboutUser = data.email + " with roles: " + getRolesUserString(data);
        $("#upNavBar").append(
            '<a>' + aboutUser + '</a>');
    })
}
