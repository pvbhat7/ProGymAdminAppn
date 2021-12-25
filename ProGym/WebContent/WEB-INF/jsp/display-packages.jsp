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

        <div class="courses-area">
            <div class="container-fluid">
                <c:forEach items="${pkgList}" var="pkg">
                <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
                       
                        <div class="courses-inner res-mg-b-30">
                        	
	                            <div class="courses-title" align="center">
	                                <img src="img/courses/pkgIcon.png" alt="">
	                                <h2><c:out value="${pkg.packageName}"/></h2>
	                            </div>
	                            <div class="course-des" align="center">
	                                <p><span><i class="fa fa-clock"></i></span> <b>Duration :</b><c:out value="${pkg.days}"/>days</p>
	                                <p><span><i class="fa fa-clock"></i></span> <b>Fees:</b> Rs.<c:out value="${pkg.fees}"/></p>
	                                
	                            </div>
	                            <div class="product-buttons" align="center">
	                                <button type="button" class="button-default cart-btn" disabled="true">Edit</button>
	                                <a href="<c:url value='deletePackage?gender=${pkg.gender}&pkgid=${pkg.id}'/>">
	                                <button type="button" class="button-default cart-btn">Delete</button>
	                                </a>
	                            </div>
                            
                        </div></br>
                       
                    </div> </c:forEach>
                    
            </div>
        </div>
        <div class="footer-copyright-area">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <div class="footer-copy-right">
                            <p>Copyright © 2018. All rights reserved. Template by <a href="https://colorlib.com/wp/templates/">Colorlib</a></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

   	<script src="js1/vendor/jquery-1.12.4.min.js"></script>
    <script src="js1/bootstrap.min.js"></script>
    <script src="js1/wow.min.js"></script>
    <script src="js1/jquery-price-slider.js"></script>
    <script src="js1/jquery.meanmenu.js"></script>
    <script src="js1/owl.carousel.min.js"></script>
    <script src="js1/jquery.sticky.js"></script>
    <script src="js1/jquery.scrollUp.min.js"></script>
    <script src="js1/counterup/jquery.counterup.min.js"></script>
    <script src="js1/counterup/waypoints.min.js"></script>
    <script src="js1/counterup/counterup-active.js"></script>
    <script src="js1/scrollbar/jquery.mCustomScrollbar.concat.min.js"></script>
    <script src="js1/scrollbar/mCustomScrollbar-active.js"></script>
    <script src="js1/metisMenu/metisMenu.min.js"></script>
    <script src="js1/metisMenu/metisMenu-active.js"></script>
    <script src="js1/morrisjs/raphael-min.js"></script>
    <script src="js1/morrisjs/morris.js"></script>
    <script src="js1/morrisjs/morris-active.js"></script>
    <script src="js1/sparkline/jquery.sparkline.min.js"></script>
    <script src="js1/sparkline/jquery.charts-sparkline.js"></script>
    <script src="js1/sparkline/sparkline-active.js"></script>
    <script src="js1/calendar/moment.min.js"></script>
    <script src="js1/calendar/fullcalendar.min.js"></script>
    <script src="js1/calendar/fullcalendar-active.js"></script>
    <script src="js1/plugins.js"></script>
    <script src="js1/main.js"></script>
    <script src="js1/tawk-chat.js"></script>
</body>

</html>