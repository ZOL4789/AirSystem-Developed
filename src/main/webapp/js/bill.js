var airCode;
var billDate;
var ticketDate;

var btnLogout;

$(function(){
    btnLogout = $("#btnLogout");

    airCode = $("#airCode");
    billDate = $("#billDate");
    ticketDate = $("#ticketDate")

    //获取用户名
    getUserName();

    btnLogout.on("click", function(){
        logout();
    })
})


function refund(i) {
    var colArr = new Array();
    $("#tabBill tr").eq(i + 1).find("td").each(function () {
        colArr.push($(this).text());
    });
    if (confirm("确定取消订单吗？")) {
        airCode.val(colArr[1]);
        ticketDate.val(colArr[9]);
        billDate.val(colArr[10]);
        $("#billForm").submit();
    }
}