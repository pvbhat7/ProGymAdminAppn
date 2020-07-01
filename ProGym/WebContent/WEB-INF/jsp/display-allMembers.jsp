<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List"%>
<%
if(session.getAttribute("loggedInUser") == null)
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
   		<!-- // search bar start -->
		<div class="breadcome-area">
                <div class="container-fluid">
                    <div class="row">
                        <div align="center">
                            <div class="breadcome-list">
                                <div class="row">
                                    <div >
                                        <div class="breadcome-heading">
                                            <form role="search" class="sr-input-func">
                                                <input type="text" placeholder="Search..." class="search-int form-control">
                                                <a href="#"><i class="fa fa-search"></i></a>
                                            </form>
                                        </div>
                                    </div>                                    
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- // search bar end -->
   
        <div class="product-status mg-b-15">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <div class="product-status-wrap">
                        <a href="allMembers?gender=all&zone=none"><button type="button" class="btn btn-primary" id="myBtn">All</button></a>&nbsp;&nbsp;
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            
                            
                            <a href="allMembers?gender=male&zone=none"><button type="button" class="btn btn-primary" id="myBtn">Male</button></a>
                            &nbsp;&nbsp;&nbsp;                            
                            <a href="allMembers?gender=male&zone=green"><button type="button" class="btn btn-success" id="myBtn">GREEN ZONE</button></a>
                            &nbsp;&nbsp;&nbsp;
                            <a href="allMembers?gender=male&zone=red"><button type="button" class="btn btn-danger" id="myBtn">RED ZONE</button></a>
                            &nbsp;&nbsp;&nbsp;
                            <a href="allMembers?gender=male&zone=yellow"><button type="button" class="btn btn-warning" id="myBtn">YELLOW ZONE</button></a>
                            &nbsp;&nbsp;&nbsp;
                            
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            
                            <a href="allMembers?gender=female&zone=none"><button type="button" class="btn btn-primary" id="myBtn">Female</button></a>
                            &nbsp;&nbsp;&nbsp;
                            
                            <a href="allMembers?gender=female&zone=green"><button type="button" class="btn btn-success" id="myBtn">GREEN ZONE</button></a>
                            &nbsp;&nbsp;&nbsp;
                            <a href="allMembers?gender=female&zone=red"><button type="button" class="btn btn-danger" id="myBtn">RED ZONE</button></a>
                            &nbsp;&nbsp;&nbsp;
                            <a href="allMembers?gender=female&zone=yellow"><button type="button" class="btn btn-warning" id="myBtn">YELLOW ZONE</button></a>
                            
                            </br></br>                            
                            <div class="asset-inner">
                                <table>
                                    <tr>
                                        <!-- <th>Image</th> -->
                                        <th align="center">Name</th>  
                                        <th>Refer Points</th> 
                                        <th>Days Remaining</th>
                                        <!-- <th>Payment Status</th> -->                                                                                                      
                                    </tr>
                                    
                                    <c:forEach items="${membersList}" var="client" varStatus="status">
	                                    <tr style="background-color:<c:out value="${client.color}"/>">
	                                        <%-- <td align="right"><img src="img/memberPhotos/${client.id}.jpg" alt="" /></td> --%>
	                                        <td align="center"><h3 >
	                                        <a style="color: black;" href="<c:url value='clientProfile?cliendId=${client.id}&gender=${client.gender}'/>">
	                                        <c:out value="${client.name}"/>
	                                        </a></h3>
	                                        </td>
	                                        <td><h3><c:out value="${client.referPoints}"/></h3></td>
	                                        <td><h3><c:out value="${client.daysRemaining}"/></h3></td>
	                                        <%-- <td><h3 style="color: white;"><c:out  value="${client.paymentStatus}"/></h3></td> --%>                                                                                                                       
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