var pageIndex;
var pageNum;
var btnPre;
var btnNext;

$(function (){
    pageIndex = $("#pageIndex");
    pageNum = $("#pageNum");
    btnPre = $("#btnPre");
    btnNext = $("#btnNext")

    btnPre.on("click", function(){
        changePageIndex(pageIndex.val() - 1);
    })

    btnNext.on("click", function(){
        changePageIndex(parseInt(pageIndex.val()) + 1);
    })

    $(".delete").on("click", function(){
        deleteTicket($(this));
    })
    $(".update").on("click",function(){
        updateTicket($(this));
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
    $("#ticketForm").submit();
}

function deleteTicket(obj){
    if(confirm("确定删除此项机票吗？")){
        $.ajax({
            url:"/AirSystem/ticket/sys/delete.do",
            type:"post",
            contentType:"application/json;charset=utf-8",
            dataType:"text",
            data:JSON.stringify({ticketId:obj.attr("ticketId")}),
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

function updateTicket(obj){
    if(confirm("确定更新此项机票吗？")){
        var ticketId = obj.attr("ticketId");
        var company = null;
        var airCode = null;
        var startTime = null;
        var arriveTime = null;
        var startDrome = null;
        var arriveDrome = null;
        var mode = null;
        var airStop = null;
        var week = null;
        if(obj.attr("ticketCompany") != obj.parents("tr").prev("tr").find("input").eq(0).val()){
            company = obj.parents("tr").prev("tr").find("input").eq(0).val();
        }
        if(obj.attr("ticketAirCode") != obj.parents("tr").prev("tr").find("input").eq(1).val()){
            airCode = obj.parents("tr").prev("tr").find("input").eq(1).val();
        }
        if(obj.attr("ticketStartDrome") != obj.parents("tr").prev("tr").find("input").eq(2).val()){
            startDrome = obj.parents("tr").prev("tr").find("input").eq(2).val();
        }
        if(obj.attr("ticketArriveDrome") != obj.parents("tr").prev("tr").find("input").eq(3).val()){
            arriveDrome = obj.parents("tr").prev("tr").find("input").eq(3).val();
        }
        if(obj.attr("ticketStartTime") != obj.parents("tr").prev("tr").find("input").eq(4).val()){
            startTime = obj.parents("tr").prev("tr").find("input").eq(4).val();
        }
        if(obj.attr("ticketArriveTime") != obj.parents("tr").find("input").eq(0).val()){
            arriveTime = obj.parents("tr").find("input").eq(0).val();
        }
        if(obj.attr("ticketMode") != obj.parents("tr").find("input").eq(1).val()){
            mode = obj.parents("tr").find("input").eq(1).val();
        }
        if(obj.attr("ticketAirStop") != obj.parents("tr").find("input").eq(2).val()){
            airStop = obj.parents("tr").find("input").eq(2).val();
        }
        if(obj.attr("ticketWeek") != obj.parents("tr").find("input").eq(3).val()){
            week = obj.parents("tr").find("input").eq(3).val();
        }
        if(company != null || airCode != null || startDrome != null || arriveDrome != null ||
            startTime != null || arriveTime != null || mode != null || airStop != null || week != null)
        {
            $.ajax({
                url: "/AirSystem/ticket/sys/update.do",
                type: "post",
                contentType: "application/json;charset=utf-8",
                dataType: "text",
                data: JSON.stringify({
                    ticketId:ticketId,
                    company:company,
                    airCode:airCode,
                    startDrome:startDrome,
                    arriveDrome:arriveDrome,
                    startTime:startTime,
                    arriveTime:arriveTime,
                    mode:mode,
                    airStop:airStop,
                    week:week
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