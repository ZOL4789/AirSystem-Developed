<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: ZOL
  Date: 2020/7/1
  Time: 20:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>AirSystem-预订机票</title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<form id="buyForm" method="post" action="/AirSystem/ticket/bill/book.html">
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
                <input type="button" id="btnBook" value="机票预订" class="btn btn-primary btn-block" disabled="true"/>
                <br />
                <p>使用该功能需要登录。</p>
            </div>
            <div>
                <input type="button" id="btnBookHotel" value="酒店预订" class="btn btn-primary btn-block" onclick="javascript:location='/AirSystem/hotel/queryList.html'" />
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
                    <div style="text-align: center; font-size: 24px">预订机票</div>
                </div>
                <div class="panel-body" style="text-align: center; margin-top: 100px; margin-bottom: 100px">
                    <div class="row" style="margin-bottom:20px">
                        <table id="tabTickets" class="table table-bordered">
                            <input type="hidden" id="ticketId" name="ticketId" value="${ticket.id}"/>
                            <input type="hidden" id="passengerName" name="passengerName"/>
                            <tr class="active" id="thead">
                                <td>航空公司</td>
                                <td>航班号</td>
                                <td>出发机场</td>
                                <td>到达机场</td>
                                <td>出发时间</td>
                                <td>到达时间</td>
                                <td>机型</td>
                                <td>乘客</td>
                            </tr>
                            <tr>
                                <td>${ticket.company}</td>
                                <td>${ticket.airCode}</td>
                                <td>${ticket.startDrome}</td>
                                <td>${ticket.arriveDrome}</td>
                                <td>${ticket.startTime}</td>
                                <td>${ticket.arriveTime}</td>
                                <td>${ticket.mode}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${fn:length(passengerList) == 0}">
                                            <input type="button" id="btnAdd" value="添加" class="form-control" onclick="javascript:location='${pageContext.request.contextPath}/passenger/add.html'"/>
                                        </c:when>
                                        <c:otherwise>
                                            <select id="selPassenger" class="form-control">
                                            <c:forEach var="passenger" items="${passengerList}" varStatus="i">
                                                <option>${passenger.name}</option>
                                            </c:forEach>
                                            </select>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <div class="row">
                        <input type="button" id="btnBuy" value="预订" class="btn btn-primary col-lg-4 col-lg-offset-4"/>
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
<script src="${pageContext.request.contextPath}/js/common.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/bookticket.js" type="text/javascript"></script>
</body>
</html>
