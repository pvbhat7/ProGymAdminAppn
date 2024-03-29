<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
                                <li class="active"><a href="#description">Enter SubType Workout</a></li>
                            </ul>
                            <div id="myTabContent" class="tab-content custom-product-edit">
                                <div class="product-tab-list tab-pane fade active in" id="description">
                                    <div class="row">
                                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                            <div class="review-content-section">
                                                <div id="dropzone1" class="pro-ad">
                                                    <form action="addSubWorkout" class="dropzone dropzone-custom needsclick add-professors" id="addSubWorkout" method="post">
                                                        <div class="row">
                                                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                                                                <div class="form-group">
                                                                    <select name="mainWorkoutId" id="mainWorkoutId" class="form-control" >
                                                                        <option value="-1" label="--- Select Main Workout ---"/>
                                                                        <c:forEach items="${workoutMainTypeList}" var="list" varStatus="status">
                                                                            <option value="${list.id}">${list.name}</option>
                                                                        </c:forEach>
                                                                    </select>
                                                                </div>
                                                                <div class="form-group">
                                                                    <input id="subWorkoutName" name="subWorkoutName" type="text" class="form-control" placeholder="Enter Sub Workout Name" required="true"/>
                                                                </div>
                                                                <div class="form-group">
                                                                    <input id="sets" name="sets" type="number" class="form-control" placeholder="Enter Sets" required="true"/>
                                                                </div>
                                                                <div class="form-group">
                                                                    <input id="reps" name="reps" type="number" class="form-control" placeholder="Enter Reps" required="true"/>
                                                                </div>
                                                                <div class="form-group">
                                                                    <button id="basicInfo" type="submit" class="btn btn-primary">Submit</button>
                                                                </div>
                                                            </div>
                                                        </div>
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