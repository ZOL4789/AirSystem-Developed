var btnBuy;
var airCodeShow;
var startTimeShow;
var arriveTimeShow;
var selPassenger;
var passengerName;
var airCode;
var startTime;
var arriveTime;

$(function(){
    btnBuy = $("#btnBuy");
    airCode = $("#airCode");
    startTime = $("#startTime");
    arriveTime = $("#arriveTime");
    airCodeShow = $("#airCodeShow");
    startTimeShow = $("#startTimeShow");
    arriveTimeShow = $("#arriveTimeShow");
    selPassenger = $("#selPassenger");
    passengerName = $("#passengerName");

    //获取用户名
    getUserName();

    btnBuy.on("click", function(){
        bookTicket();
    })
})


//购买事件，发送请求保存订单到数据库
function bookTicket() {
    if (userName == null || userName == "") {
        if (confirm("当前未登录任何用户！是否登录？")) {
            location = "/AirSystem/user/login.html";
        }
    } else {
        if(confirm("确定购买？")) {
            airCode.val(airCodeShow.text());
            startTime.val(startTimeShow.text());
            arriveTime.val(arriveTimeShow.text());
            passengerName.val(selPassenger.val());
            $("#buyForm").submit();
        }
    }
}