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
    if(confirm("确定删除此项用户吗？")){
        $.ajax({
            url:"/AirSystem/user/sys/delete.do",
            type:"post",
            contentType:"application/json;charset=utf-8",
            dataType:"text",
            data:JSON.stringify({userId:obj.attr("userId")}),
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
    if(confirm("确定更新此项用户吗？")){
        var userId = obj.attr("userId");
        var name = null;
        var password = null;
        var phone = null;
        var email = null;
        var money = null;
        if(obj.attr("userName") != obj.parents("tr").prev("tr").find("input").eq(0).val()){
            name = obj.parents("tr").prev("tr").find("input").eq(0).val();
        }
        if(obj.attr("userPassword") != obj.parents("tr").prev("tr").find("input").eq(1).val()){
            password = obj.parents("tr").prev("tr").find("input").eq(1).val();
        }
        if(obj.attr("userPhone") != obj.parents("tr").prev("tr").find("input").eq(2).val()){
            phone = obj.parents("tr").prev("tr").find("input").eq(2).val();
        }
        if(obj.attr("userEmail") != obj.parents("tr").find("input").eq(0).val()){
            email = obj.parents("tr").find("input").eq(0).val();
        }
        if(obj.attr("userMoney") != obj.parents("tr").find("input").eq(1).val()){
            money = obj.parents("tr").find("input").eq(1).val();
        }
        if(name != null || password != null || phone != null || email != null || money != null) {
            $.ajax({
                url: "/AirSystem/user/sys/update.do",
                type: "post",
                contentType: "application/json;charset=utf-8",
                dataType: "text",
                data: JSON.stringify({
                    userId:userId,
                    name:name,
                    password:password,
                    phone:phone,
                    email:email,
                    money:money,
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