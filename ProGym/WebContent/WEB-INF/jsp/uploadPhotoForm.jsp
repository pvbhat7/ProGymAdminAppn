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
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <div class="product-status-wrap">
                            
                            </br></br>                            
                            <div class="asset-inner">
                            <form:form action="fileUploadPage" method = "POST" modelAttribute = "fileUpload"
										         enctype = "multipart/form-data">
	                            <table>
	                                    <tr align="center">
	                                        <td align="center">
	                                        
	                                        <form:select path="reference" class="form-control">
												<form:option value="NONE" label="--- Select Referer Name ---"/>
												<form:options items="${referencesList}"/>
												</form:select>
											</td>                                                                                                                      
	                                    </tr>
	                                    <tr align="center">
	                                        <td align="center">
	                                        <!-- // upload photo form -->
				                            
										         <input class="btn btn-primary" type = "file" name = "file" />
										         
										      
											</td>                                                                                                                      
	                                    </tr>
	                                    <tr>
	                                        <td class="btn btn-primary" align="center">
 	                                        <input type = "submit" value = "upload" />
	                                        
	                                        </td>                                                                                                                      
	                                    </tr>
	                                    
	                                   
	                            </table>
                             </form:form>
                            
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