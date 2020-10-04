
let getRolesUserString = function (user) {
    let rolesUser = '';
    user.roles.forEach(function (role) {
        let roleName = role.roleName
        rolesUser = rolesUser + roleName.substring(5, roleName.length) + ', '
    });
    return rolesUser.substring(0, rolesUser.length - 2)
}


let getValueWithoutNull = function (val) {
    if (val) {
        return val
    } else {
        return ""
    }
}
