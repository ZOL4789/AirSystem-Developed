<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ZOL
  Date: 2020/7/1
  Time: 20:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>AirSystem-查看订单</title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<form id="billForm" method="post" action="/AirSystem/bill/refund.html">
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
                <input type="button" id="btnInfo" value="账号信息" class="btn btn-primary btn-block" onclick="javascript:location='/AirSystem/user/personalInfo.html'"/>
                <br />
                <p>可查看本账号的个人信息。</p>
            </div>
            <div>
                <input type="button" id="btnChangePwd" value="修改密码" class="btn btn-primary btn-block" onclick="javascript:location='/AirSystem/user/changePwd.html'"/>
                <br />
                <p>可修改本账号的密码。</p>
            </div>
            <div>
                <input type="button" id="btnBill" value="我的订单" class="btn btn-primary btn-block" onclick="javascript:location='/AirSystem/bill/list.html'"/>
                <br />
                <p>可查看本账号以往的购买记录。</p>
            </div>
            <div>
                <input type="button" id="btnLogout" value="退出登录" class="btn btn-danger btn-block" />
                <br />
                <p>注销本账号的登录。</p>
            </div>
        </div>
        <div class="col-lg-10">
            <div class="panel panel-primary">
                <div class="panel-title">
                    <div style="text-align: center; font-size: 24px">订单</div>
                </div>
                <div class="panel-body" style="text-align: center; margin-top: 50px; margin-bottom: 50px">
                    <div class="row">
                        <table id="tabBill" class="table table-bordered">
                            <input type="hidden" id="airCode" name="airCode"/>
                            <input type="hidden" id="billDate" name="billDate"/>
                            <input type="hidden" id="ticketDate" name="ticketDate"/>
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
                                <td>出发日期</td>
                                <td>下单日期</td>
                            </tr>
                            <c:forEach var="bill" items="${billList}" varStatus="i">
                                <tr onclick="refund(${i.index})">
                                    <td>${bill.ticket.company}</td>
                                    <td>${bill.ticket.airCode}</td>
                                    <td>${bill.ticket.startDrome}</td>
                                    <td>${bill.ticket.arriveDrome}</td>
                                    <td>${bill.ticket.startTime}</td>
                                    <td>${bill.ticket.arriveTime}</td>
                                    <td>${bill.ticket.mode}</td>
                                    <td>${bill.ticket.airStop}</td>
                                    <td>${bill.ticket.week}</td>
                                    <td>${bill.ticket.date}</td>
                                    <td>${bill.date}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                    <div class="row">
                        <nav aria-label="Page navigation">
                            <ul class="pager" id="pageBar">
                            </ul>
                        </nav>
                    </div>
                </div>
                <div class="panel-footer">
                    <span>点击订单可进行退票</span>
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
<script src="../js/common.js" type="text/javascript"></script>
<script src="../js/bill.js" type="text/javascript"></script>
</body>
</html>
