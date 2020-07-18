<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>AirSystem-首页</title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <link href="../content/jquery.datetimepicker.css" rel="stylesheet" />
</head>
<body>
<form id="searchForm" method="post" action="/AirSystem/ticket/queryList.html">
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
                <input type="button" id="btnQuery" value="航班查询" class="btn btn-primary btn-block" disabled="true"/>
                <br />
                <p>选择出发地、目的地和出发时间以进行航班班次查询。</p>
            </div>
            <div>
                <input type="button" id="btnBuy" value="机票预订" class="btn btn-primary btn-block" onclick="javascript:location='/AirSystem/ticket/toBook.html'"/>
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
                    <div style="text-align: center; font-size: 24px">首页</div>
                </div>
                <div class="panel-body" style="margin-top: 100px; margin-bottom: 250px">
                    <div class="col-lg-3">
                        <div class="input-group">
                            <span id="lblStartCity"  class="input-group-addon">出发地：</span>
                            <input type="hidden" name="startCity" id="startCity">
                            <select id="selStartCity" class="form-control">
                                <c:forEach var="item" items="${cityList}">
                                    <c:choose>
                                        <c:when test="${startCity == item.cnCityName}">
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
                    <div class="col-lg-3">
                        <div class="input-group">
                            <span id="lblLastCity" class="input-group-addon" >目的地：</span>
                            <input type="hidden" name="arriveCity" id="arriveCity">
                            <select id="selLastCity" class="form-control">
                                <c:forEach var="item" items="${cityList}">
                                    <c:choose>
                                        <c:when test="${arriveCity == item.cnCityName}">
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
                    <div class="col-lg-3">
                        <div class="input-group">
                            <span id="lblStartDate" class="input-group-addon">出发时间：</span>
                            <input type="text" id="dateTime" name="theDate" class="form-control" placeholder="请选择时间" aria-describedby="basic-addon1" <c:if test="${date != ''}">value="${date}"</c:if>></input>
                        </div>
                    </div>
                    <div class="col-lg-3">
                        <input type="button" id="btnSearch" value="搜索" class="btn btn-primary btn-block"/>
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
<script src="${pageContext.request.contextPath}/js/home.js" type="text/javascript"></script>
</body>
</html>
