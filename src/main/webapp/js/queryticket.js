var pageIndex;
var pageNum;
var btnPre;
var btnNext;
var theDate;

$(function (){
    pageIndex = $("#pageIndex");
    pageNum = $("#pageNum");
    btnPre = $("#btnPre");
    btnNext = $("#btnNext");
    theDate = $("#theDate");


    btnPre.on("click", function(){
        changePageIndex(pageIndex.val() - 1);
    })

    btnNext.on("click", function(){
        changePageIndex(parseInt(pageIndex.val()) + 1);
    })

    $(".choose").on("click", function(){
        toBook($(this));
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

function toBook(obj){
    $("#ticketId").val(obj.attr("ticketId"));
    alert(obj.attr("ticketId") + "/" +$("#ticketId").val());
    $("#ticketForm").attr("action","/AirSystem/ticket/toBook.html");
    $("#ticketForm").submit();
}

