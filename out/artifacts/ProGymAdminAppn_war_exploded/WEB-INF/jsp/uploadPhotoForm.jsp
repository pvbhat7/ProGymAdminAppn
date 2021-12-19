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
                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                        <div class="product-status-wrap">
                            
                            </br></br>                            
                            <div class="asset-inner">
	                            <table>
                                    <tr>
                                        <form:form action="fileUploadPage" method="POST" modelAttribute="fileUpload" enctype="multipart/form-data">
                                            <td>owner_1</td>
                                            <td><img src="${imageObject.owner_1}" width="100" height="100" alt=""/></td>
                                            <input type="hidden" name="imgServerPath" id="imgServerPath" value="owner_1">
                                            <td><input class="btn btn-primary" type="file" name="file" accept="image/jpeg"/></td>
                                            <td hidden="true"><input type="number" name="mobile" value="" id="mobile" ></td>
                                            <td><input class="btn btn-primary" type="submit" value="upload"/></td>
                                        </form:form>
                                    </tr>
                                    <tr>
                                        <form:form action="fileUploadPage" method="POST" modelAttribute="fileUpload" enctype="multipart/form-data">
                                            <td>owner_2</td>
                                            <td><img src="${imageObject.owner_2}" width="100" height="100" alt=""/></td>
                                            <input type="hidden" name="imgServerPath" id="imgServerPath" value="owner_2">
                                            <td><input class="btn btn-primary" type="file" name="file" accept="image/jpeg"/></td>
                                            <td hidden="true"><input type="number" name="mobile" value="" id="mobile" ></td>
                                            <td><input class="btn btn-primary" type="submit" value="upload"/></td>
                                        </form:form>
                                    </tr>
                                    <tr>
                                        <form:form action="fileUploadPage" method="POST" modelAttribute="fileUpload" enctype="multipart/form-data">
                                            <td>trainer_1</td>
                                            <td><img src="${imageObject.trainer_1}" width="100" height="100" alt=""/></td>
                                            <input type="hidden" name="imgServerPath" id="imgServerPath" value="trainer_1">
                                            <td><input class="btn btn-primary" type="file" name="file" accept="image/jpeg"/></td>
                                            <td hidden="true"><input type="number" name="mobile" value="" id="mobile" ></td>
                                            <td><input class="btn btn-primary" type="submit" value="upload"/></td>
                                        </form:form>
                                    </tr>
                                    <tr>
                                        <form:form action="fileUploadPage" method="POST" modelAttribute="fileUpload" enctype="multipart/form-data">
                                            <td>trainer_2</td>
                                            <td><img src="${imageObject.trainer_2}" width="100" height="100" alt=""/></td>
                                            <input type="hidden" name="imgServerPath" id="imgServerPath" value="trainer_2">
                                            <td><input class="btn btn-primary" type="file" name="file" accept="image/jpeg"/></td>
                                            <td hidden="true"><input type="number" name="mobile" value="" id="mobile" ></td>
                                            <td><input class="btn btn-primary" type="submit" value="upload"/></td>
                                        </form:form>
                                    </tr>
                                    <tr>
                                        <form:form action="fileUploadPage" method="POST" modelAttribute="fileUpload" enctype="multipart/form-data">
                                            <td>banner_1</td>
                                            <td><img src="${imageObject.banner_1}" width="100" height="100" alt=""/></td>
                                            <input type="hidden" name="imgServerPath" id="imgServerPath" value="banner_1">
                                            <td><input class="btn btn-primary" type="file" name="file" accept="image/jpeg"/></td>
                                            <td hidden="true"><input type="number" name="mobile" value="" id="mobile" ></td>
                                            <td><input class="btn btn-primary" type="submit" value="upload"/></td>
                                        </form:form>
                                    </tr>
                                    <tr>
                                        <form:form action="fileUploadPage" method="POST" modelAttribute="fileUpload" enctype="multipart/form-data">
                                            <td>login_brand_logo</td>
                                            <td><img src="${imageObject.login_brand_logo}" width="100" height="100" alt=""/></td>
                                            <input type="hidden" name="imgServerPath" id="imgServerPath" value="login_brand_logo">
                                            <td><input class="btn btn-primary" type="file" name="file" accept="image/jpeg"/></td>
                                            <td hidden="true"><input type="number" name="mobile" value="" id="mobile" ></td>
                                            <td><input class="btn btn-primary" type="submit" value="upload"/></td>
                                        </form:form>
                                    </tr>
                                    <tr>
                                        <form:form action="fileUploadPage" method="POST" modelAttribute="fileUpload" enctype="multipart/form-data">
                                            <td>login_screen_banner</td>
                                            <td><img src="${imageObject.login_screen_banner}" width="100" height="100" alt=""/></td>
                                            <input type="hidden" name="imgServerPath" id="imgServerPath" value="login_screen_banner">
                                            <td><input class="btn btn-primary" type="file" name="file" accept="image/jpeg"/></td>
                                            <%--<td><input class="btn btn-primary" type="submit" value="upload"/></td>--%>
                                        </form:form>
                                    </tr>

                                    <%--App Banners--%>
                                    <tr>
                                        <form:form action="fileUploadPage" method="POST" modelAttribute="fileUpload" enctype="multipart/form-data">
                                            <td>App Banner 1</td>
                                            <td><img src="${imageObject.appBanner_1}" width="100" height="100" alt=""/></td>
                                            <input type="hidden" name="imgServerPath" id="imgServerPath" value="appBanner_1">
                                            <td><input class="btn btn-primary" type="file" name="file" accept="image/jpeg"/></td>
                                            <td><input type="number" name="mobile" id="mobile" value="${imageObject.appBanner_1Contact}" placeholder="Enter Contact Number"></td>
                                            <td><input class="btn btn-primary" type="submit" value="upload"/></td>
                                        </form:form>
                                    </tr>
                                    <tr>
                                        <form:form action="fileUploadPage" method="POST" modelAttribute="fileUpload" enctype="multipart/form-data">
                                            <td>App Banner 2</td>
                                            <td><img src="${imageObject.appBanner_2}" width="100" height="100" alt=""/></td>
                                            <input type="hidden" name="imgServerPath" id="imgServerPath" value="appBanner_2">
                                            <td><input class="btn btn-primary" type="file" name="file" accept="image/jpeg"/></td>
                                            <td><input type="number" name="mobile" id="mobile" value="${imageObject.appBanner_2Contact}" placeholder="Enter Contact Number"></td>
                                            <td><input class="btn btn-primary" type="submit" value="upload"/></td>
                                        </form:form>
                                    </tr>
                                    <tr>
                                        <form:form action="fileUploadPage" method="POST" modelAttribute="fileUpload" enctype="multipart/form-data">
                                            <td>App Banner 3</td>
                                            <td><img src="${imageObject.appBanner_3}" width="100" height="100" alt=""/></td>
                                            <input type="hidden" name="imgServerPath" id="imgServerPath" value="appBanner_3">
                                            <td><input class="btn btn-primary" type="file" name="file" accept="image/jpeg"/></td>
                                            <td><input type="number" name="mobile" id="mobile" value="${imageObject.appBanner_3Contact}" placeholder="Enter Contact Number"></td>
                                            <td><input class="btn btn-primary" type="submit" value="upload"/></td>
                                        </form:form>
                                    </tr>
                                    <tr>
                                        <form:form action="fileUploadPage" method="POST" modelAttribute="fileUpload" enctype="multipart/form-data">
                                            <td>App Banner 4</td>
                                            <td><img src="${imageObject.appBanner_4}" width="100" height="100" alt=""/></td>
                                            <input type="hidden" name="imgServerPath" id="imgServerPath" value="appBanner_4">
                                            <td><input class="btn btn-primary" type="file" name="file" accept="image/jpeg"/></td>
                                            <td><input type="number" name="mobile" id="mobile" value="${imageObject.appBanner_4Contact}" placeholder="Enter Contact Number"></td>
                                            <td><input class="btn btn-primary" type="submit" value="upload"/></td>
                                        </form:form>
                                    </tr>

                                    <%--App Advertise--%>
                                    <tr>
                                        <form:form action="fileUploadPage" method="POST" modelAttribute="fileUpload" enctype="multipart/form-data">
                                            <td>App Advertise 1</td>
                                            <td><img src="${imageObject.appAdvertise_1}" width="100" height="100" alt=""/></td>
                                            <input type="hidden" name="imgServerPath" id="imgServerPath" value="appAdvertise_1">
                                            <td><input class="btn btn-primary" type="file" name="file" accept="image/jpeg"/></td>
                                            <td><input type="number" name="mobile" id="mobile" value="${imageObject.appAdvertise_1Contact}" placeholder="Enter Contact Number"></td>
                                            <td><input class="btn btn-primary" type="submit" value="upload"/></td>
                                        </form:form>
                                    </tr>
                                    <tr>
                                        <form:form action="fileUploadPage" method="POST" modelAttribute="fileUpload" enctype="multipart/form-data">
                                            <td>App Advertise 2</td>
                                            <td><img src="${imageObject.appAdvertise_2}" width="100" height="100" alt=""/></td>
                                            <input type="hidden" name="imgServerPath" id="imgServerPath" value="appAdvertise_2">
                                            <td><input class="btn btn-primary" type="file" name="file" accept="image/jpeg"/></td>
                                            <td><input type="number" name="mobile" id="mobile" value="${imageObject.appAdvertise_2Contact}" placeholder="Enter Contact Number"></td>
                                            <td><input class="btn btn-primary" type="submit" value="upload"/></td>
                                        </form:form>
                                    </tr>
                                    <tr>
                                        <form:form action="fileUploadPage" method="POST" modelAttribute="fileUpload" enctype="multipart/form-data">
                                            <td>App Advertise 3</td>
                                            <td><img src="${imageObject.appAdvertise_3}" width="100" height="100" alt=""/></td>
                                            <input type="hidden" name="imgServerPath" id="imgServerPath" value="appAdvertise_3">
                                            <td><input class="btn btn-primary" type="file" name="file" accept="image/jpeg"/></td>
                                            <td><input type="number" name="mobile" id="mobile" value="${imageObject.appAdvertise_3Contact}" placeholder="Enter Contact Number"></td>
                                            <td><input class="btn btn-primary" type="submit" value="upload"/></td>
                                        </form:form>
                                    </tr>
                                    <tr>
                                        <form:form action="fileUploadPage" method="POST" modelAttribute="fileUpload" enctype="multipart/form-data">
                                            <td>App Advertise 4</td>
                                            <td><img src="${imageObject.appAdvertise_4}" width="100" height="100" alt=""/></td>
                                            <input type="hidden" name="imgServerPath" id="imgServerPath" value="appAdvertise_4">
                                            <td><input class="btn btn-primary" type="file" name="file" accept="image/jpeg"/></td>
                                            <td><input type="number" name="mobile" id="mobile" value="${imageObject.appAdvertise_4Contact}" placeholder="Enter Contact Number"></td>
                                            <td><input class="btn btn-primary" type="submit" value="upload"/></td>
                                        </form:form>
                                    </tr>

                                    <tr>
                                        <form:form action="fileUploadPage" method="POST" modelAttribute="fileUpload" enctype="multipart/form-data">
                                            <td>Highlights 1</td>
                                            <td><img src="${imageObject.h1}" width="100" height="100" alt=""/></td>
                                            <input type="hidden" name="imgServerPath" id="imgServerPath" value="h1">
                                            <td><input class="btn btn-primary" type="file" name="file" accept="image/jpeg"/></td>
                                            <td hidden="true"><input type="number" name="mobile" value="" id="mobile" ></td>
                                            <td><input class="btn btn-primary" type="submit" value="upload"/></td>
                                        </form:form>
                                    </tr>
                                    <tr>
                                        <form:form action="fileUploadPage" method="POST" modelAttribute="fileUpload" enctype="multipart/form-data">
                                            <td>Highlights 2</td>
                                            <td><img src="${imageObject.h2}" width="100" height="100" alt=""/></td>
                                            <input type="hidden" name="imgServerPath" id="imgServerPath" value="h2">
                                            <td><input class="btn btn-primary" type="file" name="file" accept="image/jpeg"/></td>
                                            <td hidden="true"><input type="number" name="mobile" value="" id="mobile" ></td>
                                            <td><input class="btn btn-primary" type="submit" value="upload"/></td>
                                        </form:form>
                                    </tr>
                                    <tr>
                                        <form:form action="fileUploadPage" method="POST" modelAttribute="fileUpload" enctype="multipart/form-data">
                                            <td>Highlights 3</td>
                                            <td><img src="${imageObject.h3}" width="100" height="100" alt=""/></td>
                                            <input type="hidden" name="imgServerPath" id="imgServerPath" value="h3">
                                            <td><input class="btn btn-primary" type="file" name="file" accept="image/jpeg"/></td>
                                            <td hidden="true"><input type="number" name="mobile" value="" id="mobile" ></td>
                                            <td><input class="btn btn-primary" type="submit" value="upload"/></td>
                                        </form:form>
                                    </tr>
                                    <tr>
                                        <form:form action="fileUploadPage" method="POST" modelAttribute="fileUpload" enctype="multipart/form-data">
                                            <td>Highlights 4</td>
                                            <td><img src="${imageObject.h4}" width="100" height="100" alt=""/></td>
                                            <input type="hidden" name="imgServerPath" id="imgServerPath" value="h4">
                                            <td><input class="btn btn-primary" type="file" name="file" accept="image/jpeg"/></td>
                                            <td hidden="true"><input type="number" name="mobile" value="" id="mobile" ></td>
                                            <td><input class="btn btn-primary" type="submit" value="upload"/></td>
                                        </form:form>
                                    </tr>
                                    <tr>
                                        <form:form action="fileUploadPage" method="POST" modelAttribute="fileUpload" enctype="multipart/form-data">
                                            <td>Highlights 5</td>
                                            <td><img src="${imageObject.h5}" width="100" height="100" alt=""/></td>
                                            <input type="hidden" name="imgServerPath" id="imgServerPath" value="h5">
                                            <td><input class="btn btn-primary" type="file" name="file" accept="image/jpeg"/></td>
                                            <td hidden="true"><input type="number" name="mobile" value="" id="mobile" ></td>
                                            <td><input class="btn btn-primary" type="submit" value="upload"/></td>
                                        </form:form>
                                    </tr>

                                    <tr>
                                        <form:form action="fileUploadPage" method="POST" modelAttribute="fileUpload" enctype="multipart/form-data">
                                            <td>login_brand_logo</td>
                                            <td><img src="${imageObject.upgradePlan1_img}" width="100" height="100" alt=""/></td>
                                            <input type="hidden" name="imgServerPath" id="imgServerPath" value="upgradePlan1_img">
                                            <td><input class="btn btn-primary" type="file" name="file" accept="image/jpeg"/></td>
                                            <td hidden="true"><input type="number" name="mobile" value="" id="mobile" ></td>
                                            <td><input class="btn btn-primary" type="submit" value="upload"/></td>
                                        </form:form>
                                    </tr>

                                    <tr>
                                        <form:form action="fileUploadPage" method="POST" modelAttribute="fileUpload" enctype="multipart/form-data">
                                            <td>login_brand_logo</td>
                                            <td><img src="${imageObject.upgradePlan2_img}" width="100" height="100" alt=""/></td>
                                            <input type="hidden" name="imgServerPath" id="imgServerPath" value="upgradePlan2_img">
                                            <td><input class="btn btn-primary" type="file" name="file" accept="image/jpeg"/></td>
                                            <td hidden="true"><input type="number" name="mobile" value="" id="mobile" ></td>
                                            <td><input class="btn btn-primary" type="submit" value="upload"/></td>
                                        </form:form>
                                    </tr>

                                    <tr>
                                        <form:form action="fileUploadPage" method="POST" modelAttribute="fileUpload" enctype="multipart/form-data">
                                            <td>login_brand_logo</td>
                                            <td><img src="${imageObject.upgradePlan3_img}" width="100" height="100" alt=""/></td>
                                            <input type="hidden" name="imgServerPath" id="imgServerPath" value="upgradePlan3_img">
                                            <td><input class="btn btn-primary" type="file" name="file" accept="image/jpeg"/></td>
                                            <td hidden="true"><input type="number" name="mobile" value="" id="mobile" ></td>
                                            <td><input class="btn btn-primary" type="submit" value="upload"/></td>
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