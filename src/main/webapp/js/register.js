var btnSubmit;


$(function(){
    btnSubmit = $("#btnSubmit");

    btnSubmit.on("click", function(){
        if(pwdIsSame()){
            $("#registerForm").submit();
        }
    })

    myFunction();
})


function myFunction() {
    var x="${sessionScope.result2}";
    if (x!=null&&x!=""){
        alert(x);
    }
}

//判断两次密码输入是否一致
function pwdIsSame() {
    var pwd1=$("#txtPassword").val();
    var pwd2=$("#txtPasswordAgain").val();
    if(pwd1==pwd2){
        return true;
    }
    else{
        alert("两次密码输入不一致！");
        return false;
    }
}