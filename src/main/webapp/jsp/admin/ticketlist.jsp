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
    <title>AirSystem-所有机票信息</title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css"/>
</head>
<body>
<form id="ticketForm" method="post" action="/AirSystem/ticket/sys/listAll.html">
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
                        <div style="text-align: center; font-size: 24px">所有机票</div>
                    </div>
                    <div class="panel-body" style="text-align: center; margin-top: 50px; margin-bottom: 50px">
                        <div class="row">
                            <table id="tabHotel" class="table table-bordered">
                                <input type="hidden" name="pageIndex" id="pageIndex" value="${pageIndex}"/>
                                <input type="hidden" name="ticketId" id="ticketId"/>
                                <c:forEach var="ticket" items="${ticketList}" varStatus="i">
                                    <tr>
                                        <td>航空公司：<input style="width:100px" type="text" value="${ticket.company}"/></td>
                                        <td>航班号：<input type="text" value="${ticket.airCode}"/></td>
                                        <td>出发机场：<input type="text" value="${ticket.startDrome}"/></td>
                                        <td>到达机场：<input type="text" value="${ticket.arriveDrome}"/></td>
                                        <td>出发时间：<input style="width:100px" type="text" value="${ticket.startTime}"/></td>
                                    </tr>
                                    <tr>
                                        <td>到达时间：<input style="width:100px" type="text" value="${ticket.arriveTime}"/></td>
                                        <td>机型：<input type="text" value="${ticket.mode}"/></td>
                                        <td>经停：<input type="text" value="${ticket.airStop}"/></td>
                                        <td>飞行周期（星期）<input type="text" value="${ticket.week}"/></td>
                                        <td>
                                            <input ticketId="${ticket.id}"
                                                   ticketCompany="${ticket.company}"
                                                   ticketAirCode="${ticket.airCode}"
                                                   ticketStartDrome="${ticket.startDrome}"
                                                   ticketArriveDrome="${ticket.arriveDrome}"
                                                   ticketStartTime="${ticket.startTime}"
                                                   ticketArriveTime="${ticket.arriveTime}"
                                                   ticketMode="${ticket.mode}"
                                                   ticketAirStop="${ticket.airStop}"
                                                   ticketWeek="${ticket.week}"
                                                   class="update" type="button" id="btnUpdate" value="更新"/>
                                            <input class="delete" ticketId="${ticket.id}" type="button" id="btnDelete" value="删除"/>
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
<script src="${pageContext.request.contextPath}/js/admin/ticketlist.js" type="text/javascript"></script>
</body>
</html>
