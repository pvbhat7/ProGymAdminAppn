<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List"%>
<%
    if(session == null)
        response.sendRedirect("login");
    else if(session.getAttribute("loggedInUser") == null)
        response.sendRedirect("login");
%>

<!doctype html>
<html class="no-js" lang="en">

<head>
    <jsp:include page="header.jsp" />
</head>

<body>

<!-- Start Left menu area -->
<jsp:include page="topPage.jsp" />

<div class="product-status mg-b-15">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="product-status-wrap">

                    <a href="orders?filter=Confirmed"><button type="button" class="btn btn-primary" >Confirmed Orders</button></a>&nbsp;&nbsp;
                    <a href="orders?filter=Shipped"><button type="button" class="btn btn-warning" >Shipped Orders</button></a>&nbsp;&nbsp;
                    <a href="orders?filter=Delivered"><button type="button" class="btn btn-success">Delivered Orders</button></a>&nbsp;&nbsp;
                    <a href="orders?filter=Cancelled"><button type="button" class="btn btn-danger" >Cancelled Orders</button></a>&nbsp;&nbsp;

                    <div class="asset-inner">
                        <table>
                            <tr>
                                <th align="center">Img</th>
                                <th align="center">Order id</th>
                                <th align="center">Product Name</th>
                                <th align="center">Amount</th>
                                <th align="center">Date</th>
                                <th align="center">Photo</th>
                                <th align="center">Client name</th>
                                <th align="center">Mobile</th>
                                <th align="center">Email</th>
                                <th align="center">Tracking Details</th>
                                <th align="center">Payment status</th>
                                <th align="center">Order Status</th>
                                <th align="center">Action</th>
                            </tr>
                            <c:forEach items="${listOfOrders}" var="object" varStatus="status">
                                <tr>
                                    <td align="center"><img data-src="${object.productImg}" src="${object.productImg}" width="100" height="100" alt="${object.productImg}"/></td>
                                    <td align="center"><c:out value="${object.order_id}"/></td>
                                    <td align="center"><c:out value="${object.productName}" /></td>
                                    <td align="center"><c:out value="${object.amount}"/></td>
                                    <td align="center"><c:out value="${object.orderDate}"/></td>
                                    <td align="center"><img data-src="${object.clientImg}" src="${object.clientImg}" width="100" height="100" alt="${object.clientImg}"/></td>
                                    <td align="center"><c:out value="${object.clientName}"/></td>
                                    <td align="center"><c:out value="${object.clientMobile}"/></td>
                                    <td align="center"><c:out value="${object.clientEmail}"/></td>
                                    <td align="center"><c:out value="${object.trackingDetails}"/></td>
                                    <td align="center"><c:out value="${object.orderPaymentStatus}" /></td>
                                    <c:if test="${object.orderStatus == 'Confirmed'}">
                                        <td bgcolor="#4261FF" align="center"><c:out value="${object.orderStatus}"/></td>
                                    </c:if>
                                    <c:if test="${object.orderStatus == 'Shipped'}">
                                        <td bgcolor="#FFF642" align="center"><c:out value="${object.orderStatus}"/></td>
                                    </c:if>
                                    <c:if test="${object.orderStatus == 'Delivered'}">
                                        <td bgcolor="#57FF42" align="center"><c:out value="${object.orderStatus}"/></td>
                                    </c:if>
                                    <c:if test="${object.orderStatus == 'Cancelled'}">
                                        <td bgcolor="#FF3349" align="center"><c:out value="${object.orderStatus}"/></td>
                                    </c:if>


                                    <%--<c:url value='deleteProduct?productId=${object.id}&category=${objTypename}'/>--%>
                                    <td align="center">
                                        <c:if test="${object.orderStatus == 'Confirmed'}">
                                            <a href="<c:url value='/updateOrderStatus?filter=${filter}&order_id=${object.order_id}&status=Shipped'/>"><input class="btn btn-warning btn-xl" type="button" value="S" /></a>
                                            <a href="<c:url value='/updateOrderStatus?filter=${filter}&order_id=${object.order_id}&status=Delivered'/>"><input class="btn btn-success btn-xl" type="button" value="D" /></a>
                                            <a href="<c:url value='/updateOrderStatus?filter=${filter}&order_id=${object.order_id}&status=Cancelled'/>"><input class="btn btn-danger btn-xl" type="button" value="C" /></a>
                                        </c:if>
                                        <c:if test="${object.orderStatus == 'Pending'}">
                                            <a href="<c:url value='/updateOrderStatus?filter=${filter}&order_id=${object.order_id}&status=Shipped'/>"><input class="btn btn-warning btn-xl" type="button" value="S" /></a>
                                            <a href="<c:url value='/updateOrderStatus?filter=${filter}&order_id=${object.order_id}&status=Delivered'/>"><input class="btn btn-success btn-xl" type="button" value="D" /></a>
                                            <a href="<c:url value='/updateOrderStatus?filter=${filter}&order_id=${object.order_id}&status=Cancelled'/>"><input class="btn btn-danger btn-xl" type="button" value="C" /></a>
                                        </c:if>
                                        <c:if test="${object.orderStatus == 'Shipped'}">
                                            <a href="<c:url value='/updateOrderStatus?filter=${filter}&order_id=${object.order_id}&status=Delivered'/>"><input class="btn btn-success btn-xl" type="button" value="D" /></a>
                                            <a href="<c:url value='/updateOrderStatus?filter=${filter}&order_id=${object.order_id}&status=Cancelled'/>"><input class="btn btn-danger btn-xl" type="button" value="C" /></a>
                                        </c:if>
                                        <c:if test="${object.orderStatus == 'Delivered'}">
                                        </c:if>

                                    </td>
                                </tr>
                            </c:forEach>

                        </table>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="copyright.jsp" />
</div>

<jsp:include page="bottom_script.jsp" />
</body>
</html>