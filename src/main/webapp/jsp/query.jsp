<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%--
  Created by IntelliJ IDEA.
  User: ZOL
  Date: 2020/7/1
  Time: 20:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>AirSystem-查询</title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<form id="ticketForm" method="post" action="/AirSystem/ticket/list.html">
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
                <input type="button" id="btnBuyInQueryPage" value="机票购买" class="btn btn-primary btn-block" disabled="true"/>
                <br />
                <p>使用该功能需要登录。</p>
                <p>登录后请点击需要购买的机票进行购买。</p>
            </div>
            <div>
                <input type="button" id="btnNotice" value="公告" class="btn btn-primary btn-block" disabled="true"/>
                <br />
                <p>本站 WEB 服务来源于：http://www.webxml.com.cn/</p>
            </div>
        </div>
        <div class="col-lg-10">
            <div class="panel panel-primary">
                <div class="panel-title">
                    <div style="text-align: center; font-size: 24px">查询</div>
                </div>
                <div class="panel-body" style="text-align: center; margin-top: 100px; margin-bottom: 100px">
                    <div class="row">
                        <table id="tabTickets" class="table table-bordered">
                            <input type="hidden" id="startCity" name="startCity" value="${startCity}"/>
                            <input type="hidden" id="arriveCity" name="arriveCity" value="${arriveCity}"/>
                            <input type="hidden" id="theDate" name="theDate" value="${theDate}"/>
                            <input type="hidden" id="pageNum" name="pageNum" value="${pageNum}"/>
                            <tr class="active">
                                <td>航空公司</td>
                                <td>航班号</td>
                                <td>出发机场</td>
                                <td>到达机场</td>
                                <td>出发时间</td>
                                <td>到达时间</td>
                                <td>机型</td>
                                <td>经停</td>
                                <td>飞行周期（星期）</td>
                            </tr>
                            <c:forEach var="ticket" items="${ticketList}" varStatus="i">
                                <tr onclick="toBuy(${i.index})">
                                    <td>${ticket.company}</td>
                                    <td>${ticket.airCode}</td>
                                    <td>${ticket.startDrome}</td>
                                    <td>${ticket.arriveDrome}</td>
                                    <td>${ticket.startTime}</td>
                                    <td>${ticket.arriveTime}</td>
                                    <td>${ticket.mode}</td>
                                    <td>${ticket.airStop}</td>
                                    <td>${ticket.week}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                    <div class="row">
                        <input type="hidden" name="pageIndex" id="pageIndex" value="${pageIndex}"/>
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
                <div class="panel-footer">
                    <span>点击订单可进行购买</span>
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
<form id="buyForm" method="post" action="/AirSystem/ticket/toBuy.html">
    <input type="hidden" id="airCode" name="airCode"/>
    <input type="hidden" id="startTime" name="startTime"/>
    <input type="hidden" id="arriveTime" name="arriveTime"/>
    <input type="hidden" id="theDateToBuy" name="theDateToBuy"/>
</form>
<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="../js/common.js" type="text/javascript"></script>
<script src="../js/query.js" type="text/javascript"></script>
</body>
</html>
