<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List"%>

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
                        <c:if test="${type == 'paid'}">
                        <h4>Paid Payments List</h4>
                            <a href="paidPayments?gender=all"><button type="button" class="btn btn-info" id="myBtn">All</button></a>&nbsp;&nbsp;
                            <a href="paidPayments?gender=male"><button type="button" class="btn btn-info" id="myBtn">Male</button></a>&nbsp;&nbsp;
                            <a href="paidPayments?gender=female"><button type="button" class="btn btn-info" id="myBtn">Female</button></a></br></br>
                        </c:if>
                        
                        <c:if test="${type == 'notpaid'}">
                        <h4>Pending Payments List</h4>
                            <a href="pendingPayments?gender=all"><button type="button" class="btn btn-info" id="myBtn">All</button></a>&nbsp;&nbsp;
                            <a href="pendingPayments?gender=male"><button type="button" class="btn btn-info" id="myBtn">Male</button></a>&nbsp;&nbsp;
                            <a href="pendingPayments?gender=female"><button type="button" class="btn btn-info" id="myBtn">Female</button></a></br></br>
                        </c:if>
                            
                            <div class="asset-inner">
                                <table>
                                    <tr>
                                        <th>Image</th>
                                        <th>Name</th>
                                        <th>Gender</th>
                                        <th>Package</th>
                                        <th>Fees</th>
                                        <th>Start Date</th>
                                        <th>End Date</th>
                                        <th>Payment Date</th>
                                        <th>Payment Status</th>                                                                                
                                    </tr>
                                    
                                    <c:forEach items="${paymentDataPVOList}" var="paymentData" varStatus="status">
                                    <tr>
                                        <td><img src="img/courses/pkgIcon.png" alt="" /></td>
                                        <td><c:out value="${paymentData.clientName}"/></td>
                                        <td><c:out value="${paymentData.gender}"/></td>                                        
                                        <td><c:out value="${paymentData.packageName}"/></td>
                                        <td><c:out value="${paymentData.feesPaid}"/></td>
                                        <td><c:out value="${paymentData.packageStartDate}"/></td>
                                        <td><c:out value="${paymentData.packageEndDate}"/></td>
                                        <td><c:out value="${paymentData.packagePaymentEndDate}"/></td>
                                        <td><c:out value="${paymentData.clientPackageStatus}"/></td>                                        
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