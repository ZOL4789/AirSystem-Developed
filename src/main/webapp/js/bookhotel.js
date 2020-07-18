var passengerName;

var btnSearch;
var btnPre;
var btnNext;
var btnBuy;

$(function () {
    passengerName = $("#passengerName");

    btnSearch = $("#btnSearch");
    btnPre = $("#btnPre");
    btnNext = $("#btnNext");
    btnBuy = $("#btnBuy");
    //获取登录的用户名
    getUserName();

    btnSearch.on("click", function(){
        search();
    })

    btnPre.on("click", function(){
        changePageIndex(pageIndex.val() - 1);
    })


    btnNext.on("click", function(){
        changePageIndex(parseInt(pageIndex.val()) + 1);
    })

    btnBuy.on("click", function(){
        bookHotel();
    })

    tableRowMove();
})


function search(){
    city.val(selCity.val());
    $("#hotelForm").submit();
}

function bookHotel(){
    if($("#btnAdd").length > 0) {
        alert("未添加任何乘客！");
    }else {
        if (confirm("确定预订此酒店吗？")) {
            passengerName.val($("#selPassenger").val());
            $("#hotelForm").submit();
        }
    }
}

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
