<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
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
   
        <!-- Single pro tab review Start-->
        <div class="single-pro-review-area mt-t-30 mg-b-15">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <div class="product-payment-inner-st">
                            <ul id="myTabedu1" class="tab-review-design">
                                <li class="active"><a href="#description">Enter Package Information</a></li>                                
                            </ul>
                            <div id="myTabContent" class="tab-content custom-product-edit">
                                <div class="product-tab-list tab-pane fade active in" id="description">
                                    <div class="row">
                                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                            <div class="review-content-section">
                                                <div id="dropzone1" class="pro-ad">
                                                    <form:form action="addPackage" class="dropzone dropzone-custom needsclick add-professors" id="addPackage" modelAttribute="addPackageObject" method="post">
                                                        <div class="row">
                                                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                                                                <div class="form-group">
                                                                    <form:input id="packageName" path="packageName" name="packageName" type="text" class="form-control" placeholder="Package Name" required="true"/>
                                                                </div>
                                                                <div class="form-group">
                                                                    <form:select path="days" class="form-control" >
																		<form:option value="NONE" label="--- Select Period ---"/>
																		<form:options items="${packagePeriodsList}"/>
																	</form:select>
                                                                </div>
                                                                                                                                
                                                            </div>
                                                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                                                           		<div class="form-group">
                                                                    <form:select path="gender" class="form-control">
																		<form:option value="NONE" label="--- Select Gender ---"/>
																		<form:options items="${gendersList}"/>
																	</form:select>
                                                                </div>
                                                                <div class="form-group">
                                                                    <form:input id="fees" path="fees" name="fees" type="number" class="form-control" placeholder="Fees" required="true"/>
                                                                </div>
                                                                
                                                            </div>
                                                        </div>
                                                        
                                                        <div class="row">
                                                            <div class="col-lg-12">
                                                                <div class="payment-adress"></br>
                                                                    <form:button id="basicInfo" type="submit" class="btn btn-primary">Add Package</form:button>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </form:form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>                                                
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