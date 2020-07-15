//首页
var selStartCity;
var selArriveCity;
var startCity;
var arriveCity;
var btnSearch;

$(function () {
    selStartCity = $("#selStartCity");
    selArriveCity = $("#selLastCity");
    startCity = $("#startCity");
    arriveCity = $("#arriveCity");
    btnSearch = $("#btnSearch");

    btnSearch.on("click", function(){
        startCity.val(selStartCity.val());
        arriveCity.val(selArriveCity.val());
        $("#searchForm").submit();
    })

    //获取登录的用户名
    getUserName();

    //初始化时间控件
    initTimePicker();
})


//初始化时间控件
function initTimePicker(){
    $("#dateTime").datetimepicker({
        format: 'Y-m-d',        //设置时间显示格式
        autoclose: true,        //设置选择时间后自动消失为true(这个没用)
        minView: 2,             //设置显示方式为2（这个没用）
        language: 'zh-CN',      //设置字符串方式为中文解码
        minDate: new Date(),    //设置可选日期为当前日期之后的日期
    });
}