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
   		<!-- // search bar start -->
		<div class="breadcome-area">
                <div class="container-fluid">
                    <div class="row">
                        <div align="center">
                            <div class="breadcome-list">
                                        <div class="breadcome-heading">
                                            <form action="searchMember" role="search" class="sr-input-func">
                                                <input style="color:black;" name="searchCriteria" type="text" placeholder="Search Member..." class="search-int form-control">
                                                <button id="basicInfo" type="submit" class="fa fa-search"></button>
                                            </form>
                                        </div>
                                                                                
							</div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- // search bar end -->
   
        <div class="product-status mg-b-15">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <div class="product-status-wrap">
                        <a href="allMembers?gender=all&zone=none&enableDisable=enable"><button type="button" class="btn btn-primary" id="myBtn">All</button></a>&nbsp;&nbsp;
                        <a href="allMembers?gender=male&zone=none&enableDisable=disable"><button style="text-align:center;background-color:#FF0000" type="button" class="btn btn-danger" id="myBtn">Disabled Male</button></a>&nbsp;&nbsp;
                        <a href="allMembers?gender=female&zone=none&enableDisable=disable"><button style="text-align:center;background-color:#FF0000" type="button" class="btn btn-danger" id="myBtn">Disabled Female</button></a>&nbsp;&nbsp;
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                            
                            
                            <a href="allMembers?gender=male&zone=none&enableDisable=enable"><button type="button" class="btn btn-primary" id="myBtn">Male</button></a>
                            &nbsp;&nbsp;&nbsp;                            
                            <a href="allMembers?gender=male&zone=green&enableDisable=enable"><button style="text-align:center;background-color:#09951E" type="button" class="btn btn-success" id="myBtn">GREEN ZONE</button></a>
                            &nbsp;&nbsp;&nbsp;
                            <a href="allMembers?gender=male&zone=red&enableDisable=enable"><button style="text-align:center;background-color:#FF0000" type="button" class="btn btn-danger" id="myBtn">RED ZONE</button></a>
                            &nbsp;&nbsp;&nbsp;
                            <a href="allMembers?gender=male&zone=yellow&enableDisable=enable"><button type="button" class="btn btn-warning" id="myBtn">YELLOW ZONE</button></a>
                            &nbsp;&nbsp;&nbsp;
                            
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            
                            <a href="allMembers?gender=female&zone=none&enableDisable=enable"><button type="button" class="btn btn-primary" id="myBtn">Female</button></a>
                            &nbsp;&nbsp;&nbsp;
                            
                            <a href="allMembers?gender=female&zone=green&enableDisable=enable"><button style="text-align:center;background-color:#09951E" type="button" class="btn btn-success" id="myBtn">GREEN ZONE</button></a>
                            &nbsp;&nbsp;&nbsp;
                            <a href="allMembers?gender=female&zone=red&enableDisable=enable"><button style="text-align:center;background-color:#FF0000" type="button" class="btn btn-danger" id="myBtn">RED ZONE</button></a>
                            &nbsp;&nbsp;&nbsp;
                            <a href="allMembers?gender=female&zone=yellow&enableDisable=enable"><button type="button" class="btn btn-warning" id="myBtn">YELLOW ZONE</button></a>
                            
                            </br></br>                            
                            <div class="asset-inner">
                                <table style="border: solid 2px">
                                    <tr style="background-color:#E5E5E5">
                                        <!-- <th>Image</th> -->
                                        <th style="text-align:center;"><h4>Name</h4></th>  
										<th style="text-align:center;"><h4>Payment Status</h4></th>
										<th style="text-align:center;"><h4>Days Remaining</h4></th>
                                        <th style="text-align:center;"><h4>Refer Points</h4></th>
                                        <th style="text-align:center;"><h4>Reminder</h4></th>
                                        <th style="text-align:center;"><h4>Enable/Disable</h4></th>
                                        <!-- <th>Payment Status</th> -->                                                                                                      
                                    </tr>
                                    
                                    <c:forEach items="${membersList}" var="client" varStatus="status">
	                                    <tr style="background-color:<c:out value="${client.color}"/>">
	                                        <%-- <td align="right"><img src="img/memberPhotos/${client.id}.jpg" alt="" /></td> --%>
	                                        <td style="text-align:center;"><h3 >
	                                        <a style="color: black;" href="<c:url value='clientProfile?cliendId=${client.id}&gender=${client.gender}'/>">
	                                        <c:out value="${client.name}"/>
	                                        </a></h3>
	                                        </td>
	                                        <td style="text-align:center;"><h4><c:out value="${client.paymentStatus}"/></h4></td>
	                                        <td style="text-align:center;"><h3><c:out value="${client.daysRemaining}"/></h3></td>
	                                        <td style="text-align:center;background-color:white"><h3><c:out value="${client.referPoints}"/></h3></td>
                                            <td style="text-align:center;background-color:white">
	                                        <a href="sendReminderToSingleClient?clientname=${client.name}&clientid=${client.id}&daysLeft=${client.daysRemaining}&packageName=${client.packageName}&packageDuration=${client.packageDuration}&pendingFees=${client.pendingFees}&feesPaid=${client.feesPaid}&packageTotalFees=${client.packageTotalFees}"><button style="text-align:center;background-color:black" type="button" class="btn btn-danger" id="myBtn">Send Reminder</button></a>
	                                        </td>
	                                        <c:if test="${client.profileActiveFlag == 'enable'}">
	                                        <td style="text-align:center;background-color:white">
	                                        <a href="enableDisableMember?selectflag=disable&clientid=${client.id}"><button style="text-align:center;background-color:#FF0000" type="button" class="btn btn-danger" id="myBtn">Disable</button></a>
	                                        </td>
	                                        </c:if>
	                                        
	                                        <c:if test="${client.profileActiveFlag == 'disable'}">
	                                        <td style="text-align:center;background-color:white">
	                                        <a href="enableDisableMember?selectflag=enable&clientid=${client.id}"><button style="text-align:center;background-color:#09951E" type="button" class="btn btn-primary" id="myBtn">Enable</button></a>
	                                        </td>
	                                        </c:if>
	                                        
	                                        <%-- <td><h3 style="color: white;"><c:out  value="${client.paymentStatus}"/></h3></td> --%>                                                                                                                       
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