var pageIndex;
var btnPre;
var btnNext;
var btnLogout;

$(function () {
    pageIndex = $("#pageIndex");
    btnPre = $("#btnPre");
    btnNext = $("#btnNext");
    btnLogout = $("#btnLogout");

    //获取登录的用户名
    getUserName();

    btnPre.on("click", function(){
        changePageIndex(pageIndex.val() - 1);
    })

    btnNext.on("click", function(){
        changePageIndex(parseInt(pageIndex.val()) + 1);
    })

    btnLogout.on("click", function(){
        logout();
    })

    $(".choose").on("click", function(){
        refund($(this));
    })

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
    $("#hotelBillForm").submit();
}

function refund(obj){
    if(confirm("确定取消此机票的预订吗")){
        $("#hotelBillId").val(obj.attr("hotelBillId"));
         $("#hotelBillForm").submit();
    }
}