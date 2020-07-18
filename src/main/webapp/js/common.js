var userName;
function getUserName(){
    $.ajax({
        url:"/AirSystem/user/getUserName",
        type:"post",
        async:false,
        //contentType:"application/json;charset=utf-8",
        dataType:"text",
        success:function (data) {
            userName = data;
            //检查是否已经登录了账号
            if (userName == null || userName == "") {
                $("#btnBuy").attr("disabled", true);
                $("#navshow").append("<li><a href='/AirSystem/init/home.html'>首页</a></li>");
                $("#navshow").append("<li><a href='/AirSystem/user/register.html'>注册</a></li>");
                $("#navshow").append("<li><a href='/AirSystem/user/login.html'>登录</a></li>");
            } else {
                $("#btnBuy").attr("disabled", false);
                $("#navshow").append("<li><a href='/AirSystem/init/home.html'>首页</a></li>");
                $("#navshow").append("<li id='UserList'><a href='/AirSystem/user/personalInfo.html'>" + userName + "</a></li>");
                $("#UserList").append("<ul class='dropdown-menu' style='text-align:center' id='menu'>" +
                    "<li><a href='/AirSystem/user/personalInfo.html'>个人信息</a></li>" +
                    "<li><a href='/AirSystem/user/changePwd.html'>修改密码</a></li>" +
                    "<li><a href='/AirSystem/ticket/bill/list.html'>我的机票订单</a></li>" +
                    "<li><a href='/AirSystem/hotel/bill/list.html'>我的酒店订单</a></li>" +
                    "</ul>");
                if(userName == "admin"){
                    $("#menu").append(
                        "<li><a href='/AirSystem/hotel/bill/sys/listAll.html'>所有酒店订单</a></li>" +
                        "<li><a href='/AirSystem/ticket/bill/sys/listAll.html'>所有机票订单</a></li>" +
                        "<li><a href='/AirSystem/hotel/sys/listAll.html'>查看所有酒店</a></li>" +
                        "<li><a href='/AirSystem/hotel/sys/add.html'>添加酒店</a></li>" +
                        "<li><a href='/AirSystem/ticket/sys/listAll.html'>查看所有机票</a></li>" +
                        "<li><a href='/AirSystem/passenger/sys/listAll.html'>查看所有乘客</a></li>" +
                        "<li><a href='/AirSystem/user/sys/listAll.html'>查看所有用户</a></li>"
                    )
                }
                $("#menu").append("<li><a href='#' onclick='logout()'>退出登录</a></li>");
                $("#UserList").hover(function () {
                    $("#menu").slideDown(200);
                }, function () {
                    $("#menu").slideUp(100);
                });
            }
        },
        error:function () {
            alert("获取用户信息失败！");
        }
    });
}

//检查用户是否登录
function checkIsLogin(){
    if(userName == null || userName == ""){
        alert("当前未登录任何用户，是否登录？");
        location = "/AirSystem/user/login.html";
    }
}

function checkIsNotLogin(){
    if(userName != null && userName != ""){
        alert("不可重复登录！");
        location = "/AirSystem/init/home.html";
    }
}

//退出登录
function logout() {
    if (confirm("确定退出登录吗？")) {
        location = "/AirSystem/user/logout.html";
    }
}


function tableRowMove() {
    //表格行的hover效果
    $("tr").not("#thead").mousemove(function () {
        $(this).attr("class", "active");
    });
    $("tr").not("#thead").mouseout(function () {
        $(this).attr("class", "");
    });
}
