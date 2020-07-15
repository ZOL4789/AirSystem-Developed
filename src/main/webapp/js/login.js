
$(function(){
    //检查是否重新登录
    checkIsNotLogin();

    myFunction();
})

function myFunction() {
    var x="${sessionScope.result}";
    if (x!=null&&x!=""){
        alert(x);
    }
}