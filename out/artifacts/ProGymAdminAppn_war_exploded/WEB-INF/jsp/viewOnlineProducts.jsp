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
                    <h4>${objTypename}</h4>
                    <!-- <a href="paidPayments?gender=all"><button type="button" class="btn btn-info" id="myBtn">Male Trainer</button></a>&nbsp;&nbsp;
                    <a href="paidPayments?gender=male"><button type="button" class="btn btn-info" id="myBtn">Female Trainer</button></a>&nbsp;&nbsp; -->
                    <div class="asset-inner">
                        <table>
                            <tr>
                                <th>Image</th>
                                <th>Name</th>
                                <th>Old Price</th>
                                <th>New Price</th>
                                <th>Update</th>
                                <th>Update Photo</th>
                            </tr>
                            <c:forEach items="${listOfProducts}" var="object" varStatus="status">
                                <tr>
                                    <td><img data-src="${object.productPhoto}" src="${object.productPhoto}" width="100" height="100" alt="${object.productPhoto}"/></td>
                                    <form:form action="updateProductToServer" method="POST">
                                    <td>
                                            <input type="hidden" name="category" id="category" value="${objTypename}">
                                            <input type="hidden" name="productId" id="productId" value="${object.id}">
                                             <input type="hidden" name="productPhoto" id="productPhoto" value="${object.productPhoto}">
                                            <input type="text" name="productName" id="productName" value="${object.productName}">
                                    </td>

                                    <td><input type="number" name="oldPrice" id="oldPrice" value="${object.oldPrice}"></td>
                                    <td><input type="number" name="newPrice" id="newPrice" value="${object.newPrice}"></td>
                                        <td><input class="btn btn-primary btn-xs" type="submit" value="Update"/></td>
                                    </form:form>
                                    <td>
                                        <form:form action="uploadProductPhotoToServer" method="POST" modelAttribute="fileUpload" enctype="multipart/form-data">
                                        <input type="hidden" name="productPhoto" id="productPhoto" value="${object.productPhoto}">
                                            <input type="hidden" name="category" id="category" value="${objTypename}">
                                        <input type="hidden" name="productId" id="productId" value="${object.id}">
                                        <input class="btn btn-primary btn-xs" type="file" name="file" accept="image/jpeg"/>
                                        <input class="btn btn-primary btn-sm" type="submit" value="upload"/>
                                        </form:form>
                                    </td>
                                   <%-- <td>
                                        <a href="<c:url value='deleteProduct?productId=${object.id}&category=${objTypename}'/>">
                                            <input class="btn btn-danger btn-xs" type="button" value="Delete" />
                                        </a>
                                    </td>--%>
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