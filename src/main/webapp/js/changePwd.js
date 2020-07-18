var btnSubmit;
var btnLogout;

$(function(){
    btnSubmit = $("#btnSubmit");
    btnLogout = $("#btnLogout");

    //获取用户名
    getUserName();

    //检查是否已经登录
    checkIsLogin();

    btnSubmit.on("click", function(){
        if(pwdIsSame()){
            updatePwd();
        }
    })

    btnLogout.on("click", function () {
        logout();
    });
})


function updatePwd() {
    var oldPassword = $("#txtOldPassword").val().trim();
    var newPassword = $("#txtNewPassword").val().trim();
    if (oldPassword != "" && newPassword != "") {
        if (confirm("确定修改密码吗？")) {
            $("#userForm").submit();
        }
    } else {
        alert("密码不能为空!");
    }
}

//判断两次密码输入是否一致
function pwdIsSame() {
    var pwd1=$("#txtNewPassword").val();
    var pwd2=$("#txtNewPasswordAgain").val();
    if(pwd1 == pwd2){
        return true;
    }
    else{
        alert("两次密码输入不一致！");
        return false;
    }
}
