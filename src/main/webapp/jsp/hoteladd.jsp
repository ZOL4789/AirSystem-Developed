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
    <title>AirSystem-酒店添加</title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<form id="hotelForm" method="post" action="/AirSystem/user/doHotelAdd.html">
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
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="/AirSystem/init/home.html">首页</a></li>
                        <li><a href="/AirSystem/user/login.html">登录</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <div class="container">
            <div class="col-lg-2">
                <div>
                    <input type="button" id="btnInfo" value="查看订单" class="btn btn-primary btn-block" onclick="javascript:location='/AirSystem/user/personalInfo.html'"/>
                    <br />
                    <p>可查看所有用户的订单。</p>
                </div>
                <div>
                    <input type="button" id="btnChangePwd" value="查看酒店" class="btn btn-primary btn-block" onclick="javascript:location='/AirSystem/user/changePwd.html'"/>
                    <br />
                    <p>可查看所有酒店信息。</p>
                </div>
                <div>
                    <input type="button" id="btnBill" value="添加酒店" class="btn btn-primary btn-block" onclick="javascript:location='/AirSystem/bill/list.html'"/>
                    <br />
                    <p>可添加酒店信息。</p>
                </div>
                <div>
                    <input type="button" id="btnLogout" value="查看机票" class="btn btn-danger btn-block" />
                    <br />
                    <p>可查看所有机票信息。</p>
                </div>
                <div>
                    <input type="button" id="" value="更新机票" class="btn btn-danger btn-block" />
                    <br />
                    <p>可更新机票信息。</p>
                </div>
            </div>
            <div class="col-lg-10">
                <div class="panel panel-primary">
                    <div class="panel-title" style="font-size:24px">
                        <p style="text-align: center;">添加酒店</p>
                    </div>
                    <div class="panel-body" style="height: 100%">
                        <div class="row" style="margin-top: 200px;">
                            <div class="input-group col-lg-6 col-lg-offset-3" style="text-align: center">
                                <span id="lblUserName" class="input-group-addon" Style="width: 150px" >用户名：</span>
                                <input type="text" name="userName" id="txtUserName" class="form-control" placeholder="Username" aria-describedby="basic-addon1"></input>
                            </div>
                        </div>
                        <br />
                        <div class="row">
                            <div class="input-group col-lg-6 col-lg-offset-3" style="text-align: center">
                                <span id="lblPassword" Style="width: 150px" class="input-group-addon input-group-lg" >请输入密码：</span>
                                <input type="text" name="password" id="txtPassword"  class="form-control" placeholder="Password" aria-describedby="basic-addon1"></input>
                            </div>
                        </div>
                        <br />
                        <div class="row">
                            <div class="input-group col-lg-6 col-lg-offset-3" style="text-align: center">
                                <span id="lblPasswordAgain" Style="width: 150px" class="input-group-addon input-group-lg" >请再次输入密码：</span>
                                <input type="text" name="passwordAgain" id="txtPasswordAgain" class="form-control" placeholder="Password Again" aria-describedby="basic-addon1"></input>
                            </div>
                        </div>
                        <br />
                        <div class="row">
                            <div class="input-group col-lg-6 col-lg-offset-3" style="text-align: center">
                                <span id="lblPhone" Style="width: 150px" class="input-group-addon input-group-lg">手机号码</span>
                                <input type="text" name="phone" id="txtPhone"  class="form-control" placeholder="PhoneNumber" aria-describedby="basic-addonl"></input>
                            </div>
                        </div>
                        <br />
                        <div class="row">
                            <div class="input-group col-lg-6 col-lg-offset-3" style="text-align: center">
                                <span id="lblEmail" Style="width: 150px" class="input-group-addon input-group-lg">邮箱：</span>
                                <input type="text" name="email" id="txtEmail"  class="form-control" placeholder="Email" aria-describedby="basic-addon1"></input>
                            </div>
                        </div>
                        <br />
                        <div class="row">
                            <div class="col-lg-6 col-lg-offset-3" style="margin-bottom:250px">
                                <input type="button" id="btnSubmit" value="注册" class="btn btn-primary col-lg-12" />
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
<script src="../js/register.js" type="text/javascript"></script>
</body>
</html>
