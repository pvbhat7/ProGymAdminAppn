<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
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
                                <li class="active"><a href="#description">Basic Information</a></li>                                
                            </ul>
                            <div id="myTabContent" class="tab-content custom-product-edit">
                                <div class="product-tab-list tab-pane fade active in" id="description">
                                    <div class="row">
                                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                            <div class="review-content-section">
                                                <div id="dropzone1" class="pro-ad">
                                                    <form:form action="addmember" class="dropzone dropzone-custom needsclick add-professors" id="addmember" modelAttribute="addmemberobject" method="post">
                                                        <div class="row">
                                                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                                                                <div class="form-group">
                                                                    <form:input id="name" path="name" name="name" type="text" class="form-control" placeholder="Full Name"/>
                                                                </div>
                                                                <div class="form-group">
                                                                    <form:input id="mobile" path="mobile" name="mobile" type="number" class="form-control" placeholder="Mobile no."/>
                                                                </div>
                                                                <div class="form-group">
                                                                    <form:select path="gender" class="form-control">
																		<form:option value="NONE" label="--- Select Gender ---"/>
																		<form:options items="${countryList}"/>
																	</form:select>
                                                                </div>
                                                                <div class="form-group">
                                                                <div class="input-mark-inner mg-b-22">
                                                                    <input name="dob" path="dob" id="dob" type="date" class="form-control" placeholder="Date of Birth">
                                                                </div></div>
                                                                
                                                            </div>
                                                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                                                            <div class="form-group">
                                                                    <form:select path="packageCategory" class="form-control">
																		<form:option value="NONE" label="--- Select Category ---"/>
																		<form:options items="${categoryList}"/>
																	</form:select>
                                                                </div>
                                                            <div class="form-group">
                                                                    <form:select path="packageDuration" class="form-control">
																		<form:option value="NONE" label="--- Select Duration ---"/>
																		<form:options items="${durationList}"/>
																	</form:select>
                                                                </div>
                                                                <div class="form-group">
                                                                    <form:select path="cpackage" class="form-control">
																		<form:option value="NONE" label="--- Select Package ---"/>
																		<form:options items="${packageList}"/>
																	</form:select>
                                                                </div>
                                                                <div class="form-group">
                                                                    <form:input id="remarks" path="remarks" name="remarks" type="text" class="form-control" placeholder="Remarks"/>
                                                                </div>
                                                                
                                                            </div>
                                                        </div>
                                                        
                                                        <div class="row">
                                                            <div class="col-lg-12">
                                                                <div class="payment-adress"></br>
                                                                    <form:button type="submit" class="btn btn-primary waves-effect waves-light">Add User</form:button>
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