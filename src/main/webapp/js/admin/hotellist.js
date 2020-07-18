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
        deleteHotel($(this));
    })

    $(".update").on("click", function(){
        updateHotel($(this));
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
    $("#hotelForm").submit();
}


function deleteHotel(obj){
    if(confirm("确定删除此项酒店信息吗？")){
        $.ajax({
            url:"/AirSystem/hotel/sys/delete.do",
            type:"post",
            contentType:"application/json;charset=utf-8",
            dataType:"text",
            data:JSON.stringify({hotelId:obj.attr("hotelId")}),
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

function updateHotel(obj){
    if(confirm("确定更新此项酒店信息吗？")){
        var hotelId = obj.attr("hotelId");
        var name = null;
        var address = null;
        var fullAddress = null;
        var singleRoomLeft = null;
        var singleRoomPrice = null;
        var doubleRoomLeft = null;
        var doubleRoomPrice = null;
        if(obj.attr("hotelName") != obj.parents("tr").prev("tr").find("input").eq(0).val()){
            name = obj.parents("tr").prev("tr").find("input").eq(0).val();
        }
        if(obj.attr("hotelAddress") != obj.parents("tr").prev("tr").find("input").eq(1).val()){
            address = obj.parents("tr").prev("tr").find("input").eq(1).val();
        }
        if(obj.attr("hotelFullAddress") != obj.parents("tr").prev("tr").find("input").eq(2).val()){
            fullAddress = obj.parents("tr").prev("tr").find("input").eq(2).val();
        }
        if(obj.attr("hotelSingleRoomLeft") != obj.parents("tr").prev("tr").find("input").eq(3).val()){
            singleRoomLeft = obj.parents("tr").prev("tr").find("input").eq(3).val();
        }
        if(obj.attr("hotelSingleRoomPrice") != obj.parents("tr").find("input").eq(0).val()){
            singleRoomPrice = obj.parents("tr").find("input").eq(0).val();
        }
        if(obj.attr("hotelDoubleRoomLeft") != obj.parents("tr").find("input").eq(1).val()){
            doubleRoomLeft = obj.parents("tr").find("input").eq(1).val();
        }
        if(obj.attr("hotelDoubleRoomPrice") != obj.parents("tr").find("input").eq(2).val()){
            doubleRoomPrice = obj.parents("tr").find("input").eq(2).val();
        }
        if(name != null || address != null || fullAddress != null || singleRoomLeft != null || singleRoomPrice != null
            || doubleRoomLeft != null || doubleRoomPrice != null)
        {
            $.ajax({
                url: "/AirSystem/hotel/sys/update.do",
                type: "post",
                contentType: "application/json;charset=utf-8",
                dataType: "text",
                data: JSON.stringify({
                    hotelId:hotelId,
                    name:name,
                    address:address,
                    fullAddress:fullAddress,
                    singleRoomLeft:singleRoomLeft,
                    singleRoomPrice:singleRoomPrice,
                    doubleRoomLeft:doubleRoomLeft,
                    doubleRoomPrice:doubleRoomPrice
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