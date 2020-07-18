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
    <title>AirSystem-查看用户酒店订单</title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
</head>
<body>
<form id="hotelForm" method="post" action="/AirSystem/hotel/sys/listAll.html">
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
                    <div class="panel-title">
                        <div style="text-align: center; font-size: 24px">所有酒店</div>
                    </div>
                    <div class="panel-body" style="text-align: center; margin-top: 50px; margin-bottom: 50px">
                        <div class="row">
                            <table id="tabHotel" class="table table-bordered">
                                <input type="hidden" id="pageNum" name="pageNum" value="${pageNum}"/>
                                <input type="hidden" name="pageIndex" id="pageIndex" value="${pageIndex}"/>
                                <c:forEach var="hotel" items="${hotelList}" varStatus="i">
                                    <tr>
                                        <td>名字：<input type="text"  value="${hotel.name}"/></td>
                                        <td>地址：<input type="text" value="${hotel.address}"/></td>
                                        <td>详细地址：<input type="text"  value="${hotel.fullAddress}"/></td>
                                        <td>单人间剩余：<input type="text"  value="${hotel.singleRoomLeft}"/></td>

                                    </tr>
                                    <tr>
                                        <td>单人间价格：<input type="text"  value="${hotel.singleRoomPrice}"/></td>
                                        <td>双人间剩余：<input type="text"  value="${hotel.doubleRoomLeft}"/></td>
                                        <td>双人间价格：<input type="text"  value="${hotel.doubleRoomPrice}"/></td>
                                        <td>
                                            <input hotelId="${hotel.id}"
                                                   hotelName="${hotel.name}"
                                                    hotelAddress="${hotel.address}"
                                                   hotelFullAddress="${hotel.fullAddress}"
                                                   hotelSingleRoomLeft="${hotel.singleRoomLeft}"
                                                   hotelSingleRoomPrice="${hotel.singleRoomPrice}"
                                                   hotelDoubleRoomLeft="${hotel.doubleRoomLeft}"
                                                   hotelDoubleRoomPrice="${hotel.doubleRoomPrice}"
                                                   class="update" type="button"  value="更新" >
                                            <input class="delete" hotelId="${hotel.id}" type="button"  value="删除" >
                                        </td>
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
<script src="${pageContext.request.contextPath}/js/common.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/admin/hotellist.js" type="text/javascript"></script>
</body>
</html>
