<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: ZOL
  Date: 2020/7/1
  Time: 18:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>AirSystem-酒店预订</title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <link href="../content/jquery.datetimepicker.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
</head>
<body>
<form id="hotelForm" method="post" action="/AirSystem/hotel/queryList.html">
    <div class="container-fluid">
        <nav class="navbar navbar-inverse" role="navigation">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button id="switchNav" class="navbar-toggle" data-toggle="collapse"
                            data-target="#example-navbar-collapse">
                        <span class="sr-only">切换导航</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">AirSystem</a>
                </div>
                <div class="collapse navbar-collapse" id="example-navbar-collapse">
                    <ul class="nav navbar-nav navbar-right" id="navshow">
                    </ul>
                </div>
            </div>
        </nav>
        <div class="container">
            <div class="col-lg-2">
                <div>
                    <input type="button" id="btnQuery" value="航班查询" class="btn btn-primary btn-block" onclick="javascript:location='/AirSystem/init/home.html'"/>
                    <br />
                    <p>选择出发地、目的地和出发时间以进行航班班次查询。</p>
                </div>
                <div>
                    <input type="button" id="btnBuy" value="机票预订" class="btn btn-primary btn-block" onclick="javascript:location='/AirSystem/ticket/toBook.html'"/>
                    <br />
                    <p>使用该功能需要登录。</p>
                </div>
                <div>
                    <input type="button" id="btnBookHotel" value="酒店预订" class="btn btn-primary btn-block" disabled="true" />
                    <br />
                    <p>可以预订酒店。</p>
                </div>
                <div>
                    <input type="button" id="btnNotice" value="公告" class="btn btn-primary btn-block" disabled="true" />
                    <br />
                    <p>本站 WEB 服务来源于：http://www.webxml.com.cn/</p>
                </div>
            </div>
            <div class="col-lg-10">
                <div class="panel panel-primary">
                    <div class="panel-title">
                        <div style="text-align: center; font-size: 24px">酒店预订</div>
                    </div>
                    <div class="panel-body" style="margin-top: 100px;">
                        <div class="row">
                            <div class="col-lg-4">
                                <div class="input-group">
                                    <span id="lblCity"  class="input-group-addon">目的地：</span>
                                    <input type="hidden" name="city" id="city" value="${city}">
                                    <select id="selCity" class="form-control">
                                        <c:forEach var="item" items="${cityList}">
                                            <c:choose>
                                                <c:when test="${city == item.cnCityName}">
                                                    <option selected="selected">${item.cnCityName}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option>${item.cnCityName}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="col-lg-4">
                                <div class="input-group">
                                    <span id="lblCheckInDate" class="input-group-addon" >入住时间：</span>
                                    <input type="text" id="checkInDate" name="checkInDate" class="form-control" placeholder="请选择时间" aria-describedby="basic-addon1" <c:if test="${checkInDate != ''}">value="${checkInDate}"</c:if>></input>
                                </div>
                            </div>
                            <div class="col-lg-4">
                                <div class="input-group">
                                    <span id="lblCheckOutDate" class="input-group-addon">退房时间：</span>
                                    <input type="text" id="checkOutDate" name="checkOutDate" class="form-control" placeholder="请选择时间" aria-describedby="basic-addon1" <c:if test="${checkOutDate != ''}">value="${checkOutDate}"</c:if>></input>
                                </div>
                            </div>
                        </div>
                        <div class="row" style="margin-top:20px">
                            <div class="col-lg-4">
                                <div class="input-group">
                                    <span id="lblRoomType" class="input-group-addon" >房间类型：</span>
                                    <input type="hidden" name="roomType" id="roomType">
                                    <select id="selRoomType" class="form-control">
                                        <c:forEach var="item" items="${rooms}" varStatus="i">
                                            <option>${item}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="col-lg-4">
                                <div class="input-group">
                                    <span id="lblRoomNum" class="input-group-addon">房间数：</span>
                                    <select id="selRoomNum" class="form-control">
                                        <c:forEach var="item" begin="1" end="10" varStatus="i">
                                            <option>${item}间</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="col-lg-4">
                                <input type="button" id="btnSearch" value="搜索" class="btn btn-primary btn-block"/>
                            </div>
                        </div>
                        <div class="row" style="margin-top:20px">
                            <table id="tabHotel" class="table table-bordered">
                                <input type="hidden" id="pageIndex" name="pageIndex" value="${pageIndex}"/>
                                <input type="hidden" id="hotelId" name="hotelId"/>
                                <tr id="thead" class="active">
                                    <td>名字</td>
                                    <td>城市</td>
                                    <td>详细地址</td>
                                    <td>评分</td>
                                    <td>单人房剩余</td>
                                    <td>单人房价格</td>
                                    <td>双人房剩余</td>
                                    <td>双人房价格</td>
                                </tr>
                                <c:forEach var="hotel" items="${hotelList}" varStatus="i">
                                    <tr class="choose" hotelId="${hotel.id}">
                                        <td>${hotel.name}</td>
                                        <td>${hotel.address}</td>
                                        <td>${hotel.fullAddress}</td>
                                        <td>${hotel.mark}</td>
                                        <td>${hotel.singleRoomLeft}</td>
                                        <td>${hotel.singleRoomPrice}</td>
                                        <td>${hotel.doubleRoomLeft}</td>
                                        <td>${hotel.doubleRoomPrice}</td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                        <div class="row">
                            <nav aria-label="Page navigation">
                                <ul class="pager" id="pageBar">
                                    <li id='btnPre'><a aria-label='Previous'><span aria-hidden='true'>&laquo;</span>Previous</a></li>
                                    <c:forEach var="item" begin="1" end="${pageNum}" varStatus="ind">
                                        <li><a onclick="changePageIndex(${ind.index})">${ind.index}</a></li>
                                    </c:forEach>
                                    <li id='btnNext'><a aria-label='Next'>Next<span aria-hidden='true'>&raquo;</span></a></li>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="footer" class="container">
            <nav class="navbar navbar-default navbar-fixed-bottom">
                <div class="navbar-inner navbar-content-center">
                    <p class="text-muted credit" style="padding: 20px; text-align: center">
                        &#169;2020&nbsp;AirSystem&nbsp;
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        (无)-经营性-2015-0023
                    </p>
                </div>
            </nav>
        </div>
    </div>
</form>
<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/scripts/jquery.datetimepicker.js"></script>
<script src="${pageContext.request.contextPath}/js/common.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/queryhotel.js" type="text/javascript"></script>
</body>
</html>
