<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

<div class="product-status mg-b-15">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="product-status-wrap">
                    <h4>Main Workout</h4>
                    <!-- <a href="paidPayments?gender=all"><button type="button" class="btn btn-info" id="myBtn">Male Trainer</button></a>&nbsp;&nbsp;
                    <a href="paidPayments?gender=male"><button type="button" class="btn btn-info" id="myBtn">Female Trainer</button></a>&nbsp;&nbsp; -->
                    <div class="asset-inner">
                        <table>
                            <tr>
                                <th>Main Workout</th>
                                <th>Action</th>
                            </tr>
                            <c:forEach items="${mList}" var="object" varStatus="status">
                                <tr>

                                    <td>
                                        <a href="<c:url value='viewWorkouts?mid=${object.id}'/>">
                                            <c:out value="${object.name}"/>
                                        </a>

                                    </td>
                                    <td>
                                        <a href="<c:url value='deleteMainWorkoutType?mainTypeId=${object.id}'/>">
                                            <input class="btn btn-danger btn-xs" type="button" value="Delete" />
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>

                        </table>
                    </div>

                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="product-status-wrap">
                    <h4>Sub Workout</h4>
                    <!-- <a href="paidPayments?gender=all"><button type="button" class="btn btn-info" id="myBtn">Male Trainer</button></a>&nbsp;&nbsp;
                    <a href="paidPayments?gender=male"><button type="button" class="btn btn-info" id="myBtn">Female Trainer</button></a>&nbsp;&nbsp; -->
                    <div class="asset-inner">
                        <table>
                            <tr>
                                <th>Image</th>
                                <th>Main Workout</th>
                                <th>Sub Type Workout</th>
                                <th>Sets</th>
                                <th>Reps</th>
                                <th>Gif File</th>
                                <th>Action</th>
                            </tr>
                            <c:forEach items="${sList}" var="object" varStatus="status">
                                <tr>
                                    <td><img data-src="${object.gifFilePath}" src="${object.gifFilePath}" width="100" height="100" alt="${object.gifFilePath}"/></td>
                                    <td><c:out value="${object.mainType.name}"/></td>
                                    <form:form action="updateTSubWorkoutName" method="POST">
                                    <td>

                                            <input type="hidden" name="subWorkoutId" id="subWorkoutId" value="${object.id}">
                                            <input type="hidden" name="mid" id="mid" value="${object.mainType.id}">
                                            <input type="text" name="name" id="name" value="${object.name}">
                                            <input class="btn btn-primary btn-xs" type="submit" value="Update"/>

                                    </td>

                                    <td><input type="number" name="sets" id="sets" value="${object.sets}"></td>
                                        <td><input type="number" name="reps" id="reps" value="${object.reps}"></td>
                                    </form:form>
                                    <td>
                                        <form:form action="uploadSubWorkoutGifAction" method="POST" modelAttribute="fileUpload" enctype="multipart/form-data">
                                        <input type="hidden" name="imgServerPath" id="imgServerPath" value="${object.gifImageName}">
                                            <input type="hidden" name="subWorkoutId" id="subWorkoutId" value="${object.id}">
                                            <input type="hidden" name="mid" id="mid" value="${object.mainType.id}">
                                        <input class="btn btn-primary btn-xs" type="file" name="file" accept="image/jpeg"/>
                                        <input class="btn btn-primary btn-sm" type="submit" value="upload"/>
                                        </form:form>
                                    </td>
                                    <td>
                                        <a href="<c:url value='deleteSubWorkoutType?mid=${object.mainType.id}&subTypeId=${object.id}'/>">
                                            <input class="btn btn-danger btn-xs" type="button" value="Delete" />
                                        </a>
                                    </td>
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