<%@page import="com.progym.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
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
		
		<%
		User u = (User)session.getAttribute("loggedInUser");
		if(u.getAuthorizedToApprovePayment().equalsIgnoreCase("YES")){
		%>
		

		<!--Main page content -->
        <div class="analytics-sparkle-area">
            <div class="container-fluid">
                
            </div>
        </div>
        <%} %>
        
        <div class="product-sales-area mg-tb-30">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
                        <div class="product-sales-chart">
                            <img src="img/owner.jpg">
                        </div>
                        </br></br>
                        <div class="product-sales-chart">
                            <img src="img/ladiesTrainer.jpg">
                        </div>
                        
                    </div>
                    
                    <!-- GYM STAT START -->
                    <div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
                        <%-- <div class="white-box analytics-info-cs mg-b-10 res-mg-b-30 res-mg-t-30 table-mg-t-pro-n tb-sm-res-d-n dk-res-t-d-n">
                            <h3 class="box-title">Male Members</h3>
                            <ul class="list-inline two-part-sp">
                                <li>
                                    <div id="sparklinedash"></div>
                                </li>
                                <li class="text-right sp-cn-r"><i class="fa fa-level-up" aria-hidden="true"></i> <span class="counter text-success">${maletotal}</span></li>
                            </ul>
                        </div>
                        <div class="white-box analytics-info-cs mg-b-10 res-mg-b-30 tb-sm-res-d-n dk-res-t-d-n">
                            <h3 class="box-title">Female Members</h3>
                            <ul class="list-inline two-part-sp">
                                <li>
                                    <div id="sparklinedash2"></div>
                                </li>
                                <li class="text-right graph-two-ctn"><i class="fa fa-level-up" aria-hidden="true"></i> <span class="counter text-purple">${femaletotal}</span></li>
                            </ul>
                        </div> --%>
                        <div class="product-sales-chart">
                            <img src="img/banner1.jpg">
                        </div>
                        <div class="white-box analytics-info-cs mg-b-10 res-mg-b-30 tb-sm-res-d-n dk-res-t-d-n">
                            <h3 class="box-title">Total Members</h3>
                            <ul class="list-inline two-part-sp">
                                <li>
                                    <div id="sparklinedash3"></div>
                                </li>
                                <li class="text-right graph-three-ctn"><i class="fa fa-level-up" aria-hidden="true"></i> <span class="counter text-info">${clienttotal}</span></li>
                            </ul>
                        </div> 
                        <div class="white-box analytics-info-cs mg-b-10 res-mg-b-30 tb-sm-res-d-n dk-res-t-d-n">
                           					 <table>
                                              <tr><td><h4>Email Flag : </h4></td>
                                              <td><h4><c:out value="${emailInvoiceFlag}"/></h4></td>
                                              <td>&nbsp;&nbsp;
                                              <c:if test="${emailInvoiceFlag == 'ON'}">
	                                            <a href="toggleInvoiceFlag?flag=false"><button type="submit" class="btn btn-danger btn-md">Turn Off</button></a>
									            </c:if>
									            <c:if test="${emailInvoiceFlag == 'OFF'}">
	                                            <a href="toggleInvoiceFlag?flag=true"><button type="submit" class="btn btn-success btn-md">Turn On</button></a>
									            </c:if>
                                              </td>
                                              </tr>
                                              </table>
                        </div>  
                                              
                    </div>
                    <!-- GYM STAT END -->
                    
                    <!-- MALE MEMBERS STAT STARTS -->
                    <div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
                        <div class="white-box analytics-info-cs mg-b-10 res-mg-b-30 res-mg-t-30 table-mg-t-pro-n tb-sm-res-d-n dk-res-t-d-n">
                            <h3 class="box-title">Male Members</h3>
                            <ul class="list-inline two-part-sp">
                                <li>
                                    <div id="sparklinedash"></div>
                                </li>
                                <li class="text-right sp-cn-r"><i class="fa fa-level-up" aria-hidden="true"></i> <span class="counter text-success">${maletotal}</span></li>
                            </ul>
                        </div>
                        <div class="white-box analytics-info-cs mg-b-10 res-mg-b-30 tb-sm-res-d-n dk-res-t-d-n">
                            <h3 class="box-title">Full Paid</h3>
                            <ul class="list-inline two-part-sp">
                                <li>
                                    <div id=""></div>
                                </li>
                                <li class="text-right graph-two-ctn"><i class="fa fa-level-up" aria-hidden="true"></i> <span class="counter text-purple">${maleFullPaid}</span></li>
                            </ul>
                        </div>
                        <div class="white-box analytics-info-cs mg-b-10 res-mg-b-30 tb-sm-res-d-n dk-res-t-d-n">
                            <h3 class="box-title">Partial Paid</h3>
                            <ul class="list-inline two-part-sp">
                                <li>
                                    <div id="sparklinedash3"></div>
                                </li>
                                <li class="text-right graph-three-ctn"><i class="fa fa-level-up" aria-hidden="true"></i> <span class="counter text-info">${malePartialPaid}</span></li>
                            </ul>
                        </div> 
                        <div class="white-box analytics-info-cs mg-b-10 res-mg-b-30 tb-sm-res-d-n dk-res-t-d-n">
                            <h3 class="box-title">Not Paid</h3>
                            <ul class="list-inline two-part-sp">
                                <li>
                                    <div id="sparklinedash3"></div>
                                </li>
                                <li class="text-right graph-three-ctn"><i class="fa fa-level-up" aria-hidden="true"></i> <span class="counter text-info">${maleNotPaid}</span></li>
                            </ul>
                        </div>                        
                    </div>
                    <!-- MALE MEMBERS STAT ENDS -->
                    <!-- FEMALE MEMBERS STAT STARTS -->
                    <div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
                        <div class="white-box analytics-info-cs mg-b-10 res-mg-b-30 res-mg-t-30 table-mg-t-pro-n tb-sm-res-d-n dk-res-t-d-n">
                            <h3 class="box-title">Female Members</h3>
                            <ul class="list-inline two-part-sp">
                                <li>
                                    <div id="sparklinedash2"></div>
                                </li>
                                <li class="text-right sp-cn-r"><i class="fa fa-level-up" aria-hidden="true"></i> <span class="counter text-success">${femaletotal}</span></li>
                            </ul>
                        </div>
                        <div class="white-box analytics-info-cs mg-b-10 res-mg-b-30 tb-sm-res-d-n dk-res-t-d-n">
                            <h3 class="box-title">Full Paid</h3>
                            <ul class="list-inline two-part-sp">
                                <li>
                                    <div id=""></div>
                                </li>
                                <li class="text-right graph-two-ctn"><i class="fa fa-level-up" aria-hidden="true"></i> <span class="counter text-purple">${femaleFullPaid}</span></li>
                            </ul>
                        </div>
                        <div class="white-box analytics-info-cs mg-b-10 res-mg-b-30 tb-sm-res-d-n dk-res-t-d-n">
                            <h3 class="box-title">Partial Paid</h3>
                            <ul class="list-inline two-part-sp">
                                <li>
                                    <div id="sparklinedash3"></div>
                                </li>
                                <li class="text-right graph-three-ctn"><i class="fa fa-level-up" aria-hidden="true"></i> <span class="counter text-info">${femalePartialPaid}</span></li>
                            </ul>
                        </div> 
                        <div class="white-box analytics-info-cs mg-b-10 res-mg-b-30 tb-sm-res-d-n dk-res-t-d-n">
                            <h3 class="box-title">Not Paid</h3>
                            <ul class="list-inline two-part-sp">
                                <li>
                                    <div id="sparklinedash3"></div>
                                </li>
                                <li class="text-right graph-three-ctn"><i class="fa fa-level-up" aria-hidden="true"></i> <span class="counter text-info">${femaleNotPaid}</span></li>
                            </ul>
                        </div>                        
                    </div>
                    <!-- FEMALE MEMBERS STAT ENDS -->
                    
                </div>
            </div>
        </div>
		
        <jsp:include page="copyright.jsp" />
       
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