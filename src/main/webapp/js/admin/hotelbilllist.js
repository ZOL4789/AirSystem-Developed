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
        deleteHotelBillList($(this));
    })

    $(".update").on("click", function(){
        updateHotelBillList($(this));
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

function deleteHotelBillList(obj){
    if(confirm("确定删除此项酒店订单吗？")){
        $.ajax({
            url:"/AirSystem/hotel/bill/sys/delete.do",
            type:"post",
            contentType:"application/json;charset=utf-8",
            dataType:"text",
            data:JSON.stringify({hotelBillId:obj.attr("hotelBillId")}),
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

function updateHotelBillList(obj){
    if(confirm("确定更新此项酒店订单吗？")){
        var hotelBillId = obj.attr("hotelBillId");
        var checkInDate = null;
        var checkOutDate = null;
        if(obj.attr("checkInDate") != obj.parents("tr").prev("tr").find("input").eq(0).val()){
            checkInDate = obj.parents("tr").prev("tr").find("input").eq(0).val();
        }
        if(obj.attr("checkOutDate") != obj.parents("tr").find("input").eq(0).val()){
            checkOutDate = obj.parents("tr").find("input").eq(0).val();
        }
        if(checkInDate != null || checkOutDate != null)
        {
            $.ajax({
                url: "/AirSystem/hotel/bill/sys/update.do",
                type: "post",
                contentType: "application/json;charset=utf-8",
                dataType: "text",
                data: JSON.stringify({
                    hotelBillId:hotelBillId,
                    checkInDate:checkInDate,
                    checkOutDate:checkOutDate,
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
