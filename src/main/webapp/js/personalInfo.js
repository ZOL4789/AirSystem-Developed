
var btnLogout;

$(function(){
    btnLogout = $("#btnLogout");
    btnAdd = $("#btnAdd");

    //获取用户名
    getUserName();
    //检查是否已经登录用户
    checkIsLogin();

    btnLogout.on("click", function(){
        logout();
    })
})

