<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
   
        <div class="product-status mg-b-15">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                        <div class="product-status-wrap">
                            
                            </br></br>                            
                            <div class="asset-inner">
	                            <table>
                                    <tr>
                                        <form:form action="fileUploadPage" method="POST" modelAttribute="fileUpload" enctype="multipart/form-data">
                                            <td>owner_1</td>
                                            <td><img src="${imageObject.owner_1}" width="100" height="100" alt=""/></td>
                                            <input type="hidden" name="imgServerPath" id="imgServerPath" value="owner_1.jpg">
                                            <td><input class="btn btn-primary" type="file" name="file" accept="image/jpeg"/></td>
                                            <td><input class="btn btn-primary" type="submit" value="upload"/></td>
                                        </form:form>
                                    </tr>
                                    <tr>
                                        <form:form action="fileUploadPage" method="POST" modelAttribute="fileUpload" enctype="multipart/form-data">
                                            <td>owner_2</td>
                                            <td><img src="${imageObject.owner_2}" width="100" height="100" alt=""/></td>
                                            <input type="hidden" name="imgServerPath" id="imgServerPath" value="owner_2.jpg">
                                            <td><input class="btn btn-primary" type="file" name="file" accept="image/jpeg"/></td>
                                            <td><input class="btn btn-primary" type="submit" value="upload"/></td>
                                        </form:form>
                                    </tr>
                                    <tr>
                                        <form:form action="fileUploadPage" method="POST" modelAttribute="fileUpload" enctype="multipart/form-data">
                                            <td>trainer_1</td>
                                            <td><img src="${imageObject.trainer_1}" width="100" height="100" alt=""/></td>
                                            <input type="hidden" name="imgServerPath" id="imgServerPath" value="trainer_1.jpg">
                                            <td><input class="btn btn-primary" type="file" name="file" accept="image/jpeg"/></td>
                                            <td><input class="btn btn-primary" type="submit" value="upload"/></td>
                                        </form:form>
                                    </tr>
                                    <tr>
                                        <form:form action="fileUploadPage" method="POST" modelAttribute="fileUpload" enctype="multipart/form-data">
                                            <td>trainer_2</td>
                                            <td><img src="${imageObject.trainer_2}" width="100" height="100" alt=""/></td>
                                            <input type="hidden" name="imgServerPath" id="imgServerPath" value="trainer_2.jpg">
                                            <td><input class="btn btn-primary" type="file" name="file" accept="image/jpeg"/></td>
                                            <td><input class="btn btn-primary" type="submit" value="upload"/></td>
                                        </form:form>
                                    </tr>
                                    <tr>
                                        <form:form action="fileUploadPage" method="POST" modelAttribute="fileUpload" enctype="multipart/form-data">
                                            <td>banner_1</td>
                                            <td><img src="${imageObject.banner_1}" width="100" height="100" alt=""/></td>
                                            <input type="hidden" name="imgServerPath" id="imgServerPath" value="banner_1.jpg">
                                            <td><input class="btn btn-primary" type="file" name="file" accept="image/jpeg"/></td>
                                            <td><input class="btn btn-primary" type="submit" value="upload"/></td>
                                        </form:form>
                                    </tr>
                                    <tr>
                                        <form:form action="fileUploadPage" method="POST" modelAttribute="fileUpload" enctype="multipart/form-data">
                                            <td>login_brand_logo</td>
                                            <td><img src="${imageObject.login_brand_logo}" width="100" height="100" alt=""/></td>
                                            <input type="hidden" name="imgServerPath" id="imgServerPath" value="login_brand_logo.jpg">
                                            <td><input class="btn btn-primary" type="file" name="file" accept="image/jpeg"/></td>
                                            <td><input class="btn btn-primary" type="submit" value="upload"/></td>
                                        </form:form>
                                    </tr>
                                    <tr>
                                        <form:form action="fileUploadPage" method="POST" modelAttribute="fileUpload" enctype="multipart/form-data">
                                            <td>login_screen_banner</td>
                                            <td><img src="${imageObject.login_screen_banner}" width="100" height="100" alt=""/></td>
                                            <input type="hidden" name="imgServerPath" id="imgServerPath" value="login_screen_banner.png">
                                            <td><input class="btn btn-primary" type="file" name="file" accept="image/jpeg"/></td>
                                            <%--<td><input class="btn btn-primary" type="submit" value="upload"/></td>--%>
                                        </form:form>
                                    </tr>
	                            </table>

                            
                                <!-- table removed from here -->
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