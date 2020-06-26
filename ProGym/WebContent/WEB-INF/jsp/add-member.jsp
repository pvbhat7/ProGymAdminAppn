<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
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
   
        <!-- Single pro tab review Start-->
        <div class="single-pro-review-area mt-t-30 mg-b-15">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <div class="product-payment-inner-st">
                            <ul id="myTabedu1" class="tab-review-design">
                                <li class="active"><a href="#description">Enter Client Information</a></li>                                
                            </ul>
                            <div id="myTabContent" class="tab-content custom-product-edit">
                                <div class="product-tab-list tab-pane fade active in" id="description">
                                    <div class="row">
                                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                            <div class="review-content-section">
                                                <div id="dropzone1" class="pro-ad">
                                                    <form:form action="addMember" class="dropzone dropzone-custom needsclick add-professors" id="addmember" modelAttribute="addmemberobject" method="post">
                                                        <div class="row">
                                                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                                                                <div class="form-group">
                                                                    <form:input id="name" path="name" name="name" type="text" class="form-control" placeholder="Full Name" required="true"/>
                                                                </div>
                                                                <div class="form-group">
                                                                    <form:input id="mobile" path="mobile" name="mobile" type="number" class="form-control" placeholder="Mobile no." required="true"/>
                                                                    
                                                                </div>
                                                                <div class="form-group">
                                                                    <form:select path="gender" class="form-control">
																		<form:option value="NONE" label="--- Select Gender ---"/>
																		<form:options items="${gendersList}"/>
																	</form:select>
                                                                </div>
                                                                <div class="form-group">
                                                                    <form:input id="previousGym" path="previousGym" name="previousGym" type="text" class="form-control" placeholder="Previous Gym Name" required="true"/>
                                                                    
                                                                </div>
                                                                                                                                
                                                            </div>
                                                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                                                            <div class="form-group">
                                                                <div class="input-mark-inner mg-b-22">
                                                                    <input name="birthDate" path="birthDate" id="birthDate" type="date" class="form-control" placeholder="Date of Birth" required="true" placeholder="Enter DOB">
                                                                </div>
                                                                </div>
                                                                <div class="form-group">
                                                                    <form:input id="email" path="email" name="email" type="email" class="form-control" placeholder="Email Id" required="true"/>
                                                                </div>
                                                                <div class="form-group">
                                                                    <form:select path="bloodGroup" class="form-control">
																		<form:option value="NONE" label="--- Select Blood Group ---"/>
																		<form:options items="${bloodGroupsList}"/>
																	</form:select>
                                                                </div>
                                                                <div class="form-group">
                                                                    <form:select path="reference" class="form-control">
																		<form:option value="NONE" label="--- Select Referer Name ---"/>
																		<form:options items="${referencesList}"/>
																	</form:select>
                                                                </div>
                                                                
                                                            </div>
                                                        </div>
                                                        
                                                        <div class="row">
                                                            <div class="col-lg-12">
                                                                <div class="payment-adress"></br>
                                                                    <form:button id="basicInfo" type="submit" class="btn btn-primary">Add User</form:button>
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