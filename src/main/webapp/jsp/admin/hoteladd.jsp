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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
</head>
<body>
<form id="hotelForm" method="post" action="/AirSystem/hotel/sys/doAdd.html">
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
                    <input type="button" id="btnListHotelBill" value="查看酒店订单" class="btn btn-primary btn-block" onclick="javascript:location='${pageContext.request.contextPath}/hotel//bill/sys/listAll.html'"/>
                    <br />
                    <p>可查看和更新所有用户的酒店订单。</p>
                </div>
                <div>
                    <input type="button" id="btnListTicketBill" value="查看机票订单" class="btn btn-primary btn-block" onclick="javascript:location='${pageContext.request.contextPath}/ticket/bill/sys/listAll.html'"/>
                    <br />
                    <p>可查看和更新所有用户的机票订单。</p>
                </div>
                <div>
                    <input type="button" id="btnListHotel" value="查看酒店" class="btn btn-primary btn-block" onclick="javascript:location='${pageContext.request.contextPath}/hotel/sys/listAll.html'"/>
                    <br />
                    <p>可查看和更新所有酒店信息。</p>
                </div>
                <div>
                    <input type="button" id="btnAddHotel" value="添加酒店" class="btn btn-primary btn-block" onclick="javascript:location='${pageContext.request.contextPath}/hotel/sys/add.html'"/>
                    <br />
                    <p>可添加酒店信息。</p>
                </div>
                <div>
                    <input type="button" id="btnListTicket" value="查看机票" class="btn btn-primary btn-block"  onclick="javascript:location='${pageContext.request.contextPath}/ticket/sys/listAll.html'"/>
                    <br />
                    <p>可查看和更新所有机票信息。</p>
                </div>
                <div>
                    <input type="button" id="btnListPassenger" value="查看乘客" class="btn btn-primary btn-block" onclick="javascript:location='${pageContext.request.contextPath}/passenger/sys/listAll.html'"/>
                    <br />
                    <p>可查看和更新所有乘客信息。</p>
                </div>
                <div>
                    <input type="button" id="btnListAndUpdateUser" value="查看用户" class="btn btn-primary btn-block" onclick="javascript:location='${pageContext.request.contextPath}/user/sys/listAll.html'"/>
                    <br />
                    <p>可查看和更新所有用户信息。</p>
                </div>
            </div>
            <div class="col-lg-10">
                <div class="panel panel-primary">
                    <div class="panel-title" style="font-size:24px">
                        <p style="text-align: center;">添加酒店</p>
                    </div>
                    <div class="panel-body" style="height: 100%">
                        <div class="row" style="margin-top: 100px;">
                            <div class="input-group col-lg-6 col-lg-offset-3" style="text-align: center">
                                <span id="lblName" class="input-group-addon" Style="width: 150px" >名字：</span>
                                <input type="text" name="name" id="name" class="form-control" placeholder="HotelName" aria-describedby="basic-addon1"></input>
                            </div>
                        </div>
                        <br />
                        <div class="row">
                            <div class="input-group col-lg-6 col-lg-offset-3" style="text-align: center">
                                <span id="lblAddress" Style="width: 150px" class="input-group-addon input-group-lg" >城市：</span>
                                <input type="text" name="address" id="address"  class="form-control" placeholder="Hotel's City" aria-describedby="basic-addon1"></input>
                            </div>
                        </div>
                        <br />
                        <div class="row">
                            <div class="input-group col-lg-6 col-lg-offset-3" style="text-align: center">
                                <span id="lblFullAddress" Style="width: 150px" class="input-group-addon input-group-lg" >详细地址：</span>
                                <input type="text" name="fullAddress" id="fullAddress" class="form-control" placeholder="Hotel's FullAddress" aria-describedby="basic-addon1"></input>
                            </div>
                        </div>
                        <br />
                        <div class="row">
                            <div class="input-group col-lg-6 col-lg-offset-3" style="text-align: center">
                                <span id="lblMark" Style="width: 150px" class="input-group-addon input-group-lg" >评分：</span>
                                <input type="text" name="mark" id="mark" class="form-control" placeholder="Hotel's Mark" aria-describedby="basic-addon1"></input>
                            </div>
                        </div>
                        <br />
                        <div class="row">
                            <div class="input-group col-lg-6 col-lg-offset-3" style="text-align: center">
                                <span id="lblSingleRoomNum" Style="width: 150px" class="input-group-addon input-group-lg">单人房间数：</span>
                                <input type="text" name="singleRoomNum" id="singleRoomNum"  class="form-control" placeholder="singleRoomLeft" aria-describedby="basic-addonl"></input>
                            </div>
                        </div>
                        <br />
                        <div class="row">
                            <div class="input-group col-lg-6 col-lg-offset-3" style="text-align: center">
                                <span id="lblSingleRoomPrice" Style="width: 150px" class="input-group-addon input-group-lg">单人房间价格：</span>
                                <input type="text" name="singleRoomPrice" id="singleRoomPrice"  class="form-control" placeholder="singleRoomPrice" aria-describedby="basic-addon1"></input>
                            </div>
                        </div>
                        <br />
                        <div class="row">
                            <div class="input-group col-lg-6 col-lg-offset-3" style="text-align: center">
                                <span id="lblDoubleRoomNum" Style="width: 150px" class="input-group-addon input-group-lg">双人房间数：</span>
                                <input type="text" name="doubleRoomNum" id="doubleRoomNum"  class="form-control" placeholder="doubleRoomNum" aria-describedby="basic-addonl"></input>
                            </div>
                        </div>
                        <br />
                        <div class="row">
                            <div class="input-group col-lg-6 col-lg-offset-3" style="text-align: center">
                                <span id="lblDoubleRoomPrice" Style="width: 150px" class="input-group-addon input-group-lg">双人房间价格：</span>
                                <input type="text" name="doubleRoomPrice" id="doubleRoomPrice"  class="form-control" placeholder="doubleRoomPrice" aria-describedby="basic-addon1"></input>
                            </div>
                        </div>
                        <br />
                        <div class="row">
                            <div class="col-lg-6 col-lg-offset-3">
                                <input type="submit" id="btnSubmit" value="添加" class="btn btn-primary col-lg-12" />
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
<script src="${pageContext.request.contextPath}/js/common.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/admin/hoteladd.js" type="text/javascript"></script>
</body>
</html>
