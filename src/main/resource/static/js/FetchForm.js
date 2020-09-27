
const requestUrl = ' http://localhost:8088/admin/users'





newUserForm.onsubmit = async (e) => {
    e.preventDefault();

    let response = await fetch('requestUrl', {
    method: 'POST',
    body: new FormData(newUserForm)
});

    let result = await response.json();

    alert(result.message);
};



