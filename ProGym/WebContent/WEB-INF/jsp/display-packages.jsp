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
	                                <button type="button" class="button-default cart-btn">Edit</button>
	                                <button type="button" class="button-default cart-btn">Delete</button>
	                            </div>
                            
                        </div></br>
                       
                    </div> </c:forEach>
	
	<%-- <!-- Edit Client assigned Modal -->
    <div class="modal fade" id="editPackageModal" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Update Package</h4>
                </div>
                <form action="updateClientAssignedPackage" class="dropzone dropzone-custom needsclick add-professors" method="post">
                <div class="modal-body">
                	<label for="psw"><span class="glyphicon"></span> Package</label>
                    <input id="u_package" name="u_package" class="form-control" type="text" readonly/>
                    </br>
                    <label for="psw"><span class="glyphicon"></span> Package StartDate</label>
                    <input id="u_startdate" name="u_startdate" class="form-control" type="date"/>
                    
                    <label for="psw"><span class="glyphicon"></span> Fees </label>
                    <input id="u_fees" name="u_fees" class="form-control" type="number" />
                    
                    <input id="u_pkgId" class="form-control" type="hidden" name="u_pkgId" />
                    <input id="u_clientid" class="form-control" type="hidden" value="<%out.print(((Client)request.getAttribute("clientObject")).getId());%>" name="u_clientid" />
                    <input id="u_gender" class="form-control" type="hidden" name="u_gender" value="<%out.print(((Client)request.getAttribute("clientObject")).getGender());%>"/>
                                        
                </div>
                <div class="modal-footer">
                    <button id="basicInfo" type="submit" class="btn btn-primary">Update Package</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
                </form>
            </div>

        </div>
    </div>
    <script>
    $(function () {
    	
    	/* on click - pay */
        $(".btnedit").click(function () {
            //get data from table row
            var packageId = $(this).parent().prev().prev().prev().prev().prev().prev().prev().text();
            var packageName = $(this).parent().prev().prev().prev().prev().prev().prev().text();
            var paidPayment = $(this).parent().prev().text();
            var total = $(this).parent().prev().prev().text();
            var remaining = total - paidPayment;


            //assign to value for input box inside modal
            $("#txt_item").val(packageName);
            $("#txt_Price1").val(paidPayment);
            $("#feesPaid").val(remaining);
            $("#packageDetailsId").val(packageId);

            //open modal
            $("#payModel").modal();

            $("#btnsave").click(function () {
                //make ajax request to update data

                //and in ajax success callback function 
                //hide modal
                //$("#myModal").modal("hide")              
            })
        })
        
        /* on click - edit client assigned package */
        $(".btnEditClientPackage").click(function () {
            //get data from table row
            var packageId = $(this).parent().prev().prev().prev().prev().prev().prev().prev().text();
            var packageName = $(this).parent().prev().prev().prev().prev().prev().prev().text();
            var startdate = $(this).parent().prev().prev().prev().prev().prev().text();
            var fees = $(this).parent().prev().prev().text();
            var cliendId = <%out.print(((Client)request.getAttribute("clientObject")).getId());%>
            var gender = <%out.print(((Client)request.getAttribute("clientObject")).getGender());%>
            
            var initial = startdate.split(/\//);
            var formattedDate = [ initial[2], initial[1], initial[0] ].join('-');
            //assign to value for input box inside modal
            $("#u_pkgId").val(packageId);
            $("#u_package").val(packageName);
            $("#u_fees").val(fees);
            $("#u_startdate").val(formattedDate);
            /* $("#u_clientid").val(cliendId);
            $("#u_gender").val(gender); */
            
            
            

            //open modal
            $("#editPackageModal").modal();

            $("#btnsave").click(function () {
                //make ajax request to update data

                //and in ajax success callback function 
                //hide modal
                //$("#myModal").modal("hide")              
            })
        })
    })
</script>
			 --%>
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