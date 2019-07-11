function login() {
    $.post(contextPath + "/admin/login",
        $("#admin-login").serialize(),
        function (data) {
            alert(data.message);
        },
        "json"
    );
}