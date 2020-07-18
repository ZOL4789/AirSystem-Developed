//首页
var roomType;
var roomNum;
var checkInDate;
var checkOutDate;
var city;
var selCity;
var hotelId;
var passengerName;
var selPassenger;

var btnSearch;
var btnPre;
var btnNext;

$(function () {
    roomType = $("#roomType");
    roomNum = $("#roomNum");
    city = $("#city");
    selCity = $("#selCity");
    checkInDate = $("#checkInDate");
    checkOutDate = $("#checkOutDate");
    hotelId = $("#hotelId");
    passengerName = $("#passengerName");
    selPassenger = $("#selPassenger");

    btnSearch = $("#btnSearch");
    btnPre = $("#btnPre");
    btnNext = $("#btnNext");

    //获取登录的用户名
    getUserName();

    //初始化时间控件
    initTimePicker(checkInDate);
    initTimePicker(checkOutDate);

    btnSearch.on("click", function(){
        search();
    })

    btnPre.on("click", function(){
        changePageIndex(pageIndex.val() - 1);
    })

    btnNext.on("click", function(){
        changePageIndex(parseInt(pageIndex.val()) + 1);
    })

    $(".choose").on("click", function(){
        toBook($(this));
    })

    tableRowMove();
})


//初始化时间控件
function initTimePicker(obj){
    obj.datetimepicker({
        format: 'Y-m-d h:m',        //设置时间显示格式
        autoclose: true,        //设置选择时间后自动消失为true(这个没用)
        minView: 1,             //设置显示方式为2（这个没用）
        language: 'zh-CN',      //设置字符串方式为中文解码
        minDate: new Date(),    //设置可选日期为当前日期之后的日期
    });
}

function search(){
    city.val(selCity.val());
    $("#hotelForm").submit();
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

function toBook(obj){
    $("#hotelId").val(obj.attr("hotelId"));
    $("#hotelForm").attr("action","/AirSystem/hotel/toBook.html");
    $("#hotelForm").submit();
}

