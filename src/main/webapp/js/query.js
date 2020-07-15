var pageIndex;
var pageNum;
var btnPre;
var btnNext;
var theDate;

var airCode;
var startTime;
var arriveTime;
var theDateToBuy;

$(function (){
    pageIndex = $("#pageIndex");
    pageNum = $("#pageNum");
    btnPre = $("#btnPre");
    btnNext = $("#btnNext");
    theDate = $("#theDate");

    airCode = $("#airCode");
    startTime = $("#startTime");
    arriveTime = $("#arriveTime");
    theDateToBuy = $("#theDateToBuy");

    btnPre.on("click", function(){
        changePageIndex(pageIndex.val() - 1);
    })

    btnNext.on("click", function(){
        changePageIndex(parseInt(pageIndex.val()) + 1);
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
    $("#ticketForm").submit();
}

function toBuy(i){
    var colArr = new Array();
    $("#tabTickets tr").eq(i + 1).find("td").each(function () {
        colArr.push($(this).text());
    });

    airCode.val(colArr[1]);
    startTime.val(colArr[4]);
    arriveTime.val(colArr[5]);
    theDateToBuy.val(theDate.val());
    $("#buyForm").submit();
}

