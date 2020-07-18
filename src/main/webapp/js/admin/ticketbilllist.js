var pageIndex;
var pageNum;
var btnPre;
var btnNext;

$(function (){
    pageIndex = $("#pageIndex");
    pageNum = $("#pageNum");
    btnPre = $("#btnPre");
    btnNext = $("#btnNext");

    btnPre.on("click", function(){
        changePageIndex(pageIndex.val() - 1);
    })

    btnNext.on("click", function(){
        changePageIndex(parseInt(pageIndex.val()) + 1);
    })

    $(".delete").on("click", function(){
        deleteTicketBillList($(this));
    })

    $(".update").on("click", function(){
        updateTicketBillList($(this));
    })


    getUserName();
    tableRowMove();
})

function changePageIndex(index) {
    if(index == 0){
        index = 1;
    }
    if(index > pageNum.val()){
        index = pageNum.val();
    }
    pageIndex.val(index);
    $("#billForm").submit();
}

function deleteTicketBillList(obj){
    if(confirm("确定删除此项机票订单吗？")){
        $.ajax({
            url:"/AirSystem/ticket/bill/sys/delete.do",
            type:"post",
            contentType:"application/json;charset=utf-8",
            dataType:"text",
            data:JSON.stringify({ticketBillId:obj.attr("ticketBillId")}),
            success:function(sym){
                if(sym){
                    obj.parents("tr").prev("tr").remove();
                    obj.parents("tr").remove();
                }else {
                    alert("删除失败！");
                }
            },
            error:function(){
                alert("删除失败！");
            }
        })
    }
}

function updateTicketBillList(obj){
    if(confirm("确定更新此项机票订单吗？")){
        var ticketBillId = obj.attr("ticketBillId");
        var passengerName = null;
        if(obj.attr("passengerName") != obj.parents("tr").find("select").eq(0).val()){
            passengerName = obj.parents("tr").find("select").eq(0).val();
        }
        if(passengerName != null)
        {
            $.ajax({
                url: "/AirSystem/ticket/bill/sys/update.do",
                type: "post",
                contentType: "application/json;charset=utf-8",
                dataType: "text",
                data: JSON.stringify({
                    ticketBillId:ticketBillId,
                    passengerName:passengerName
                }),
                success: function (sym) {
                    if (sym) {

                    } else {
                        alert("更新失败！");
                    }
                },
                error: function () {
                    alert("更新失败！");
                }
            });
        }else {
            alert("无需更改！");
        }
    }
}
