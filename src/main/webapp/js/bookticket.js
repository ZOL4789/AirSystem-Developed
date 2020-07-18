var btnBuy;

var selPassenger;
var passengerName;

$(function(){
    btnBuy = $("#btnBuy");

    selPassenger = $("#selPassenger");
    passengerName = $("#passengerName");

    btnBuy.on("click", function(){
        bookTicket();
    })

    //获取用户名
    getUserName();
    tableRowMove();
})


//购买事件，发送请求保存订单到数据库
function bookTicket() {
    if($("#btnAdd").length > 0) {
        alert("未添加任何乘客！");
    }else {
        if(confirm("确定购买？")) {
            passengerName.val(selPassenger.val());
            $("#buyForm").submit();
        }
    }
}