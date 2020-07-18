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
        deletePassenger($(this));
    })
    $(".update").on("click",function(){
        updatePassenger($(this));
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
    $("#passengerForm").submit();
}

function deletePassenger(obj){
    if(confirm("确定删除此项用户吗？")){
        $.ajax({
            url:"/AirSystem/passenger/sys/delete.do",
            type:"post",
            contentType:"application/json;charset=utf-8",
            dataType:"text",
            data:JSON.stringify({passengerId:obj.attr("passengerId")}),
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

function updatePassenger(obj){
    if(confirm("确定更新此项用户吗？")){
        var passengerId = obj.attr("passengerId");
        var name = null;
        var IDNum = null;
        var roleName = null;
        var userName = null;
        if(obj.attr("passengerName") != obj.parents("tr").find("input").eq(0).val()){
            name = obj.parents("tr").find("input").eq(0).val();
        }
        if(obj.attr("passengerIDNum") != obj.parents("tr").find("input").eq(1).val()){
            IDNum = obj.parents("tr").prev("tr").find("input").eq(1).val();
        }
        if(obj.attr("passengerRoleName") != obj.parents("tr").find("#selRoleName").eq(0).val()){
            roleName = obj.parents("tr").find("#selRoleName").eq(0).val();
        }
        if(obj.attr("passengerUserName") != obj.parents("tr").find("#selUserName").eq(0).val()){
            userName = obj.parents("tr").find("#selUserName").eq(0).val();
        }
        if(name != null || IDNum != null || roleName != null || userName != null ) {
            $.ajax({
                url: "/AirSystem/passenger/sys/update.do",
                type: "post",
                contentType: "application/json;charset=utf-8",
                dataType: "text",
                data: JSON.stringify({
                    passengerId:passengerId,
                    name:name,
                    IDNum:IDNum,
                    roleName:roleName,
                    userName:userName
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