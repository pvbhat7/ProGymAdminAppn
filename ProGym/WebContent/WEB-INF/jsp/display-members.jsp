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
   
        <div class="contacts-area mg-b-15">
            <div class="container-fluid">
                    <c:forEach items="${membersList}" var="client">
					<tr>
					    <%-- <td>Name: <c:out value="${client.name}"/></td> --%>
					<div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
                        <a href="<c:out value="clientProfile"/>"><div class="student-inner-std res-mg-b-30">
                            <div class="student-img">
                                <img src="img/student/1.jpg" alt="" />
                            </div>
                            <div class="student-dtl">
                                <h2><c:out value="${client.name}"/></h2>
                                <p class="dp"><c:out value="${client.mobile}"/></p>
<%--                                 <p class="dp-ag"><b>Age:</b> <c:out value="${client.birthDate}"/></p>
 --%>                            </div>
                        </div></br> </a>
                    </div>         
					</tr>
					
					</c:forEach>
                    
                    
                
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