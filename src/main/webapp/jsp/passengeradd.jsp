<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dzx
  Date: 2020/7/2
  Time: 19:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>AirSystem-添加乘客</title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<form id="passengerForm" method="post" action="/AirSystem/passenger/doPassengerAdd.html">
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
                    <input type="button" id="Button1" value="乘客协议" class="btn btn-primary btn-block" disabled="true"/><br />
                    <p>此乘客信息仅用于当前AirSystem用户使用。</p>
                    <p>点击确定即表示同意AirSystem访问隐私，并且AirSystem保证不泄露用户隐私。</p>
                    <p>此乘客信息由当前用户所拥有，拥有者应受法律监管，切勿用于违法犯罪。</p>
                </div>
                <div>
                    <input type="button" id="Button2" value="乘客帮助" class="btn btn-primary btn-block" disabled="true"/><br />
                    <p>真实姓名即姓名。</p>
                    <p>身份证号即所填真实姓名所对应的身份证号。</p>
                </div>
                <div>
                    <input type="button" id="Button3" value="关于乘客" class="btn btn-primary btn-block" disabled="true"/><br />
                    <p>购买机票需要选择当前登录用户所添加的乘客中的任意一个。</p>
                    <p>乘客信息最终解释权归AirSystem所有。</p>
                </div>
            </div>
            <div class="col-lg-10">
                <div class="panel panel-primary">
                    <div class="panel-title" style="font-size:24px">
                        <p style="text-align: center;">添加乘客</p>
                    </div>
                    <div class="panel-body" style="height: 100%">
                        <div class="row" style="margin-top: 200px;">
                            <div class="input-group col-lg-6 col-lg-offset-3" style="text-align: center">
                                <span id="lblUserName" class="input-group-addon" Style="width: 150px" >真实姓名：</span>
                                <input type="text" name="name" id="name" class="form-control" placeholder="Username" aria-describedby="basic-addon1"></input>
                            </div>
                        </div>
                        <br />
                        <div class="row">
                            <div class="input-group col-lg-6 col-lg-offset-3" style="text-align: center">
                                <span id="lblPassword" Style="width: 150px" class="input-group-addon input-group-lg" >身份证号：</span>
                                <input type="text" name="IDNum" id="IDNum"  class="form-control" placeholder="Password" aria-describedby="basic-addon1"></input>
                            </div>
                        </div>
                        <br />
                        <div class="row">
                            <div class="input-group col-lg-6 col-lg-offset-3" style="text-align: center">
                                <span id="lblPasswordAgain" Style="width: 150px" class="input-group-addon input-group-lg" >类别：</span>
                                <input type="hidden" id="roleType" name="roleType">
                                <select id="selRoleType" class="form-control">
                                    <c:forEach var="item" items="${roles}" varStatus="i">
                                        <c:choose>
                                            <c:when test="${i.index == 0}">
                                                <option selected="selected">${item}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option>${item}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <br />
                        <div class="row">
                            <div class="col-lg-6 col-lg-offset-3" style="margin-bottom:100px">
                                <input type="button" id="btnSubmit" value="确定" class="btn btn-primary col-lg-12" />
                            </div>
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
<script src="../js/common.js" type="text/javascript"></script>
<script src="../js/passengeradd.js" type="text/javascript"></script>
</body>
</html>
