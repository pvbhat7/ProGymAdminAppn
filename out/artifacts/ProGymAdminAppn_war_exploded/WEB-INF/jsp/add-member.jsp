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
<script>
window.onload = function() {
    var filter = '${gen}';
    var dobb = '${dobb}';
        if(filter == 'male'){
            document.getElementById('gender').value = 'male';
        }
        else if(filter == 'female'){
            document.getElementById('gender').value = 'female'
        }

        if(dobb != '' && dobb != null){
            var arr =  dobb.split('/');
            var newdate = arr[2]+'-'+arr[1]+'-'+arr[0];
            document.getElementById('birthDate').value = newdate;
        }



	};
</script>  
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
                                                                <form:label path="gender">Name</form:label>
                                                                    <form:input id="name" path="name" name="name" type="text" class="form-control" placeholder="Full Name" required="true"/>
                                                                </div>
                                                                <div class="form-group">
                                                                <form:label path="mobile">Mobile</form:label>
                                                                    <form:input id="mobile" path="mobile" name="mobile" type="number" class="form-control" placeholder="Mobile no." required="true"/>
                                                                    
                                                                </div>
                                                                <div class="form-group">
                                                                <form:label path="gender">Gender</form:label>
                                                                    <form:select path="gender" class="form-control" required="true">
																		<form:option value="NONE" label="--- Select Gender ---"/>
																		<form:options items="${gendersList}"/>
																	</form:select>
                                                                </div>
                                                                <div class="form-group">
                                                                <form:label path="height">Height (In cm)</form:label>
                                                                    <form:input id="height" path="height" name="height" type="number" class="form-control" placeholder="Enter Height" />
                                                                    
                                                                </div>
                                                                <div class="form-group">
                                                                <form:label path="weight">Weight (In kg)</form:label>
                                                                    <form:input id="weight" path="weight" name="weight" type="number" class="form-control" placeholder="Enter Weight" />
                                                                </div>
                                                                <div class="form-group">
                                                                <form:label path="address">Occupation</form:label>
                                                                    <form:input id="occupation" path="occupation" name="occupation" value="none" type="text" class="form-control" placeholder="Occupation"/>
                                                                </div>                                                                                                                                
                                                            </div>
                                                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                                                            <div class="form-group">
                                                                <div class="input-mark-inner mg-b-22">
                                                                <form:label path="birthDate">BirthDate</form:label>
                                                                    <input name="birthDate" path="birthDate" id="birthDate" type="date" class="form-control" placeholder="Date of Birth"  placeholder="Enter DOB">
                                                                </div>
                                                                </div>
                                                                <div class="form-group">
                                                                <form:label path="email">Email</form:label>
                                                                    <form:input  id="email" path="email" name="email" type="email" class="form-control" placeholder="Email Id" />
                                                                </div>
                                                                <div class="form-group">
                                                                <form:label path="address">Address</form:label>
                                                                    <form:input id="address" path="address" name="address" type="text" class="form-control" placeholder="Address" required="true"/>
                                                                </div>
                                                                <div class="form-group">
                                                                <form:label path="bloodGroup">Blood Group</form:label>
                                                                    <form:select path="bloodGroup" class="form-control">
																		<form:option value="NONE" label="--- Select Blood Group ---"/>
																		<form:options items="${bloodGroupsList}"/>
																	</form:select>
                                                                </div>
                                                                <div class="form-group">
                                                                <form:label path="previousGym">Previous Gym</form:label>
                                                                    <form:input id="previousGym" path="previousGym" name="previousGym" value="no" type="text" class="form-control" placeholder="Previous Gym Name" />
                                                                    
                                                                </div>
                                                                <div class="form-group">
                                                                <form:label path="reference">Reference</form:label>
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
                                                    <form action="fetchUserDetailsFromServer" id="fetchUserDetailsFromServer" method="post">
                                                        <label path="mobile">Mobile</label>
                                                        <input id="enteredMobile" path="enteredMobile" name="enteredMobile" type="number" placeholder="Enter Mobile" required="true"/>
                                                        <button id="basicInfo1" type="submit" >Fetch user details</button>
                                                    </form>

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