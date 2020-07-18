<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: ZOL
  Date: 2020/7/1
  Time: 20:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>AirSystem-个人信息</title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<form id="personalInfoForm" method="post" action="/AirSystem/passenger/add.html">
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
                <input type="button" id="btnInfo" value="账号信息" class="btn btn-primary btn-block" onclick="javascript:location='${pageContext.request.contextPath}/user/personalInfo.html'"/>
                <br />
                <p>可查看本账号的个人信息。</p>
            </div>
            <div>
                <input type="button" id="btnChangePwd" value="修改密码" class="btn btn-primary btn-block" onclick="javascript:location='${pageContext.request.contextPath}/user/changePwd.html'"/>
                <br />
                <p>可修改本账号的密码。</p>
            </div>
            <div>
                <input type="button" id="btnTicketBill" value="机票订单" class="btn btn-primary btn-block" onclick="javascript:location='${pageContext.request.contextPath}/ticket/bill/list.html'"/>
                <br />
                <p>可查看本账号以往的机票预订记录。</p>
            </div>
            <div>
                <input type="button" id="btnHotelBill" value="酒店订单" class="btn btn-primary btn-block" onclick="javascript:location='${pageContext.request.contextPath}/hotel/bill/list.html'"/>
                <br />
                <p>可查看本账号以往的酒店预订记录。</p>
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
                    <div style="text-align: center; font-size: 24px">个人首页</div>
                </div>
                <div class="panel-body" style="text-align: center; margin-top: 100px; margin-bottom: 100px">
                    <div class="row">
                        <table id="tabInfo" class="table table-hover table-bordered">
                            <tr class="active">
                                <td class="col-lg-3">用户名:</td>
                                <td>${user.name}</td>
                            </tr>
                            <tr>
                                <td>绑定邮箱:</td>
                                <td>${user.email}</td>
                            </tr>
                            <tr>
                                <td>注册时间:</td>
                                <td>${user.date}</td>
                            </tr>
                            <tr class="thead active">
                                <td colspan="2"></td>
                            </tr>
                            <tr>
                                <td>乘客信息</td>
                                <td><input type="submit" id="btnAdd" value="添加乘客"/></td>
                            </tr>
                            <c:forEach var="passenger" items="${passengerList}" varStatus="i">
                                <tr>
                                    <td>姓名：</td>
                                    <td>${passenger.name}</td>
                                </tr>
                                <tr>
                                    <td>身份证号：</td>
                                    <td>${passenger.IDNum}</td>
                                </tr>
                                <tr>
                                    <td>身份：</td>
                                    <td>${passenger.role.name}</td>
                                </tr>
                                <tr class="thead active">
                                    <td colspan="2"></td>
                                </tr>
                            </c:forEach>
                        </table>
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
<script src="${pageContext.request.contextPath}/js/personalInfo.js" type="text/javascript"></script>
</body>
</html>
