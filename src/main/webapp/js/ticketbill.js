
var btnLogout;

$(function(){
    btnLogout = $("#btnLogout");

    //获取用户名
    getUserName();

    btnLogout.on("click", function(){
        logout();
    })

    $(".choose").on("click", function(){
        refund($(this));
    })

    tableRowMove();
})


function refund(obj) {
    if (confirm("确定取消订单吗？")) {
        $("#ticketBillId").val(obj.attr("ticketBillId"));
        $("#ticketBillForm").submit();
    }
}