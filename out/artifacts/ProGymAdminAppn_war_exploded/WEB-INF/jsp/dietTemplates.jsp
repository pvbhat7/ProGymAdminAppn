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
<script>

    function setDisplayValuesFromTemplate (e) {

        <c:forEach items="${allDietTemplatesList}" var="obj" varStatus="status">
        console.log(${obj.id});
        if(e == ${obj.id}){
            document.getElementById('id').value = '${obj.id}';
            document.getElementById('name').value = '${obj.name}';
            document.getElementById('templateDisplayTime_1').value = '${obj.time_1}';
            document.getElementById('templateDisplayActivity_1').value = '${obj.activity_1}';
            document.getElementById('templateDisplayTime_2').value = '${obj.time_2}';
            document.getElementById('templateDisplayActivity_2').value = '${obj.activity_2}';
            document.getElementById('templateDisplayTime_3').value = '${obj.time_3}';
            document.getElementById('templateDisplayActivity_3').value = '${obj.activity_3}';
            document.getElementById('templateDisplayTime_4').value = '${obj.time_4}';
            document.getElementById('templateDisplayActivity_4').value = '${obj.activity_4}';
            document.getElementById('templateDisplayTime_5').value = '${obj.time_5}';
            document.getElementById('templateDisplayActivity_5').value = '${obj.activity_5}';
            document.getElementById('templateDisplayTime_6').value = '${obj.time_6}';
            document.getElementById('templateDisplayActivity_6').value = '${obj.activity_6}';
            document.getElementById('templateDisplayTime_7').value = '${obj.time_7}';
            document.getElementById('templateDisplayActivity_7').value = '${obj.activity_7}';
            document.getElementById('templateDisplayTime_8').value = '${obj.time_8}';
            document.getElementById('templateDisplayActivity_8').value = '${obj.activity_8}';
            document.getElementById('templateDisplayTime_9').value = '${obj.time_9}';
            document.getElementById('templateDisplayActivity_9').value = '${obj.activity_9}';
            document.getElementById('templateDisplayTime_10').value = '${obj.time_10}';
            document.getElementById('templateDisplayActivity_10').value = '${obj.activity_10}';
            document.getElementById('templateDisplayTime_11').value = '${obj.time_11}';
            document.getElementById('templateDisplayActivity_11').value = '${obj.activity_11}';
            document.getElementById('templateDisplayTime_12').value = '${obj.time_12}';
            document.getElementById('templateDisplayActivity_12').value = '${obj.activity_12}';
            document.getElementById('templateDisplayTime_13').value = '${obj.time_13}';
            document.getElementById('templateDisplayActivity_13').value = '${obj.activity_13}';
            document.getElementById('templateDisplayTime_14').value = '${obj.time_14}';
            document.getElementById('templateDisplayActivity_14').value = '${obj.activity_14}';
            document.getElementById('templateDisplayTime_15').value = '${obj.time_15}';
            document.getElementById('templateDisplayActivity_15').value = '${obj.activity_15}';
            document.getElementById('templateDisplayTime_16').value = '${obj.time_16}';
            document.getElementById('templateDisplayActivity_16').value = '${obj.activity_16}';
            document.getElementById('templateDisplayTime_17').value = '${obj.time_17}';
            document.getElementById('templateDisplayActivity_17').value = '${obj.activity_17}';
            $("#editTemplateDietPlanModal").modal();

        }
        </c:forEach>
    }

    function showAddDietTemplateModal(){
        $("#addTemplateDietPlanModal").modal();
    }

</script>

<body>

<!-- Start Left menu area -->
<jsp:include page="topPage.jsp" />

<div class="product-status mg-b-15">
    <div class="container-fluid">
        <!-- Edit Template Diet Plan Modal starts -->
        <div class="modal fade" id="editTemplateDietPlanModal" role="dialog">
            <div class="modal-dialog" style="width:1000px;">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header" style="padding:20px 30px;">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4>Edit Template</h4>
                    </div>
                    <form action="updateTemplateDietPlanData" method="post">
                        <div align="center">
                        <input type="text" id="name" name="name"/>
                        </div>
                        <input type="hidden" id="id" name="id"/>
                        <input type="hidden" id="clientId" name="clientId" value="${clientObject.id}"/>
                        <input type="hidden" id="gender" name="gender" value="${clientObject.gender}"/>
                        <div class="modal-body" style="padding:40px 50px;">
                            <table style="border-spacing: 20px">
                                <thead>
                                <tr>
                                    <td style="font-size:22px;font-weight: bold;border: 1px solid black;" align="center">Time</td>
                                    <td style="font-size:22px;font-weight: bold;border: 1px solid black;width:100%;" align="center">Activity</td>
                                </tr>
                                </thead>
                                <tbody>

                                <tr>
                                    <td style="font-size:18px;">
                                        <input type="time" name="templateDisplayTime_1" id="templateDisplayTime_1">
                                    </td>
                                    <td style="width:100%;font-size:18px;">
                                        <input type="text" name="templateDisplayActivity_1" id="templateDisplayActivity_1" style="width:100%;">
                                    </td>
                                </tr>
                                <tr>
                                    <td style="font-size:18px;">
                                        <input type="time" name="templateDisplayTime_2" id="templateDisplayTime_2" >
                                    </td>
                                    <td style="width:100%;font-size:18px;">
                                        <input type="text" name="templateDisplayActivity_2" id="templateDisplayActivity_2" style="width:100%;">
                                    </td>
                                </tr>
                                <tr>
                                    <td style="font-size:18px;">
                                        <input type="time" name="templateDisplayTime_3" id="templateDisplayTime_3" >
                                    </td>
                                    <td style="width:100%;font-size:18px;">
                                        <input type="text" name="templateDisplayActivity_3" id="templateDisplayActivity_3" style="width:100%;">
                                    </td>
                                </tr>
                                <tr>
                                    <td style="font-size:18px;">
                                        <input type="time" name="templateDisplayTime_4" id="templateDisplayTime_4" >
                                    </td>
                                    <td style="width:100%;font-size:18px;">
                                        <input type="text" name="templateDisplayActivity_4" id="templateDisplayActivity_4" style="width:100%;">
                                    </td>
                                </tr>
                                <tr>
                                    <td style="font-size:18px;">
                                        <input type="time" name="templateDisplayTime_5" id="templateDisplayTime_5" >
                                    </td>
                                    <td style="width:100%;font-size:18px;">
                                        <input type="text" name="templateDisplayActivity_5" id="templateDisplayActivity_5" style="width:100%;">
                                    </td>
                                </tr>
                                <tr>
                                    <td style="font-size:18px;">
                                        <input type="time" name="templateDisplayTime_6" id="templateDisplayTime_6" >
                                    </td>
                                    <td style="width:100%;font-size:18px;">
                                        <input type="text" name="templateDisplayActivity_6" id="templateDisplayActivity_6" style="width:100%;">
                                    </td>
                                </tr>
                                <tr>
                                    <td style="font-size:18px;">
                                        <input type="time" name="templateDisplayTime_7" id="templateDisplayTime_7" >
                                    </td>
                                    <td style="width:100%;font-size:18px;">
                                        <input type="text" name="templateDisplayActivity_7" id="templateDisplayActivity_7" style="width:100%;">
                                    </td>
                                </tr>
                                <tr>
                                    <td style="font-size:18px;">
                                        <input type="time" name="templateDisplayTime_8" id="templateDisplayTime_8" 8>
                                    </td>
                                    <td style="width:100%;font-size:18px;">
                                        <input type="text" name="templateDisplayActivity_8" id="templateDisplayActivity_8" style="width:100%;">
                                    </td>
                                </tr>
                                <tr>
                                    <td style="font-size:18px;">
                                        <input type="time" name="templateDisplayTime_9" id="templateDisplayTime_9" 9>
                                    </td>
                                    <td style="width:100%;font-size:18px;">
                                        <input type="text" name="templateDisplayActivity_9" id="templateDisplayActivity_9" style="width:100%;">
                                    </td>
                                </tr>
                                <tr>
                                    <td style="font-size:18px;">
                                        <input type="time" name="templateDisplayTime_10" id="templateDisplayTime_10" >
                                    </td>
                                    <td style="width:100%;font-size:18px;">
                                        <input type="text" name="templateDisplayActivity_10" id="templateDisplayActivity_10" style="width:100%;">
                                    </td>
                                </tr>
                                <tr>
                                    <td style="font-size:18px;">
                                        <input type="time" name="templateDisplayTime_11" id="templateDisplayTime_11" 1>
                                    </td>
                                    <td style="width:100%;font-size:18px;">
                                        <input type="text" name="templateDisplayActivity_11" id="templateDisplayActivity_11" style="width:100%;">
                                    </td>
                                </tr>
                                <tr>
                                    <td style="font-size:18px;">
                                        <input type="time" name="templateDisplayTime_12" id="templateDisplayTime_12" >
                                    </td>
                                    <td style="width:100%;font-size:18px;">
                                        <input type="text" name="templateDisplayActivity_12" id="templateDisplayActivity_12" style="width:100%;">
                                    </td>
                                </tr>
                                <tr>
                                    <td style="font-size:18px;">
                                        <input type="time" name="templateDisplayTime_13" id="templateDisplayTime_13" >
                                    </td>
                                    <td style="width:100%;font-size:18px;">
                                        <input type="text" name="templateDisplayActivity_13" id="templateDisplayActivity_13" style="width:100%;">
                                    </td>
                                </tr>
                                <tr>
                                    <td style="font-size:18px;">
                                        <input type="time" name="templateDisplayTime_14" id="templateDisplayTime_14" >
                                    </td>
                                    <td style="width:100%;font-size:18px;">
                                        <input type="text" name="templateDisplayActivity_14" id="templateDisplayActivity_14" style="width:100%;">
                                    </td>
                                </tr>
                                <tr>
                                    <td style="font-size:18px;">
                                        <input type="time" name="templateDisplayTime_15" id="templateDisplayTime_15" >
                                    </td>
                                    <td style="width:100%;font-size:18px;">
                                        <input type="text" name="templateDisplayActivity_15" id="templateDisplayActivity_15" style="width:100%;">
                                    </td>
                                </tr>
                                <tr>
                                    <td style="font-size:18px;">
                                        <input type="time" name="templateDisplayTime_16" id="templateDisplayTime_16" >
                                    </td>
                                    <td style="width:100%;font-size:18px;">
                                        <input type="text" name="templateDisplayActivity_16" id="templateDisplayActivity_16" style="width:100%;">
                                    </td>
                                </tr>
                                <tr>
                                    <td style="font-size:18px;">
                                        <input type="time" name="templateDisplayTime_17" id="templateDisplayTime_17" >
                                    </td>
                                    <td style="width:100%;font-size:18px;">
                                        <input type="text" name="templateDisplayActivity_17" id="templateDisplayActivity_17" style="width:100%;">
                                    </td>
                                </tr>


                                </tbody>
                            </table>
                        </div>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-success" >UPDATE</button>
                            <button type="button" class="btn btn-danger" data-dismiss="modal">CLOSE</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- Edit Template Diet Plan Modal ends -->

        <!-- Add Template Diet Plan Modal starts -->
        <div class="modal fade" id="addTemplateDietPlanModal" role="dialog">
            <div class="modal-dialog" style="width:1000px;">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header" style="padding:20px 30px;">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4>Add Template</h4>
                    </div>
                    <form action="submitDietPlanTemplateData" method="post">
                        </br></br>
                        <div align="center">
                            <input type="text" id="name" name="name" placeholder="Enter Diet Template Name"/>
                        </div>
                        <div class="modal-body" style="padding:40px 50px;">
                            <table style="border-spacing: 20px">
                                <thead>
                                <tr>
                                    <td style="font-size:22px;font-weight: bold;border: 1px solid black;" align="center">Time</td>
                                    <td style="font-size:22px;font-weight: bold;border: 1px solid black;width:100%;" align="center">Activity</td>
                                </tr>
                                </thead>
                                <tbody>

                                <tr>
                                    <td style="font-size:18px;">
                                        <input type="time" name="time_1" id="time_1" value="${dietTimeSlotObject.time_1}">
                                    </td>
                                    <td style="width:100%;font-size:18px;">
                                        <input type="text" name="activity_1" id="activity_1" style="width:100%;">
                                    </td>
                                </tr>
                                <tr>
                                    <td style="font-size:18px;">
                                        <input type="time" name="time_2" id="time_2" value="${dietTimeSlotObject.time_2}">
                                    </td>
                                    <td style="width:100%;font-size:18px;">
                                        <input type="text" name="activity_2" id="activity_2" style="width:100%;">
                                    </td>
                                </tr>
                                <tr>
                                    <td style="font-size:18px;">
                                        <input type="time" name="time_3" id="time_3" value="${dietTimeSlotObject.time_3}">
                                    </td>
                                    <td style="width:100%;font-size:18px;">
                                        <input type="text" name="activity_3" id="activity_3" style="width:100%;">
                                    </td>
                                </tr>
                                <tr>
                                    <td style="font-size:18px;">
                                        <input type="time" name="time_4" id="time_4" value="${dietTimeSlotObject.time_4}">
                                    </td>
                                    <td style="width:100%;font-size:18px;">
                                        <input type="text" name="activity_4" id="activity_4" style="width:100%;">
                                    </td>
                                </tr>
                                <tr>
                                    <td style="font-size:18px;">
                                        <input type="time" name="time_5" id="time_5" value="${dietTimeSlotObject.time_5}">
                                    </td>
                                    <td style="width:100%;font-size:18px;">
                                        <input type="text" name="activity_5" id="activity_5" style="width:100%;">
                                    </td>
                                </tr>
                                <tr>
                                    <td style="font-size:18px;">
                                        <input type="time" name="time_6" id="time_6" value="${dietTimeSlotObject.time_6}">
                                    </td>
                                    <td style="width:100%;font-size:18px;">
                                        <input type="text" name="activity_6" id="activity_6" style="width:100%;">
                                    </td>
                                </tr>
                                <tr>
                                    <td style="font-size:18px;">
                                        <input type="time" name="time_7" id="time_7" value="${dietTimeSlotObject.time_7}">
                                    </td>
                                    <td style="width:100%;font-size:18px;">
                                        <input type="text" name="activity_7" id="activity_7" style="width:100%;">
                                    </td>
                                </tr>
                                <tr>
                                    <td style="font-size:18px;">
                                        <input type="time" name="time_8" id="time_8" value="${dietTimeSlotObject.time_8}">
                                    </td>
                                    <td style="width:100%;font-size:18px;">
                                        <input type="text" name="activity_8" id="activity_8" style="width:100%;">
                                    </td>
                                </tr>
                                <tr>
                                    <td style="font-size:18px;">
                                        <input type="time" name="time_9" id="time_9" value="${dietTimeSlotObject.time_9}">
                                    </td>
                                    <td style="width:100%;font-size:18px;">
                                        <input type="text" name="activity_9" id="activity_9" style="width:100%;">
                                    </td>
                                </tr>
                                <tr>
                                    <td style="font-size:18px;">
                                        <input type="time" name="time_10" id="time_10" value="${dietTimeSlotObject.time_10}">
                                    </td>
                                    <td style="width:100%;font-size:18px;">
                                        <input type="text" name="activity_10" id="activity_10" style="width:100%;">
                                    </td>
                                </tr>
                                <tr>
                                    <td style="font-size:18px;">
                                        <input type="time" name="time_11" id="time_11" value="${dietTimeSlotObject.time_11}">
                                    </td>
                                    <td style="width:100%;font-size:18px;">
                                        <input type="text" name="activity_11" id="activity_11" style="width:100%;">
                                    </td>
                                </tr>
                                <tr>
                                    <td style="font-size:18px;">
                                        <input type="time" name="time_12" id="time_12" value="${dietTimeSlotObject.time_12}">
                                    </td>
                                    <td style="width:100%;font-size:18px;">
                                        <input type="text" name="activity_12" id="activity_12" style="width:100%;">
                                    </td>
                                </tr>
                                <tr>
                                    <td style="font-size:18px;">
                                        <input type="time" name="time_13" id="time_13" value="${dietTimeSlotObject.time_13}">
                                    </td>
                                    <td style="width:100%;font-size:18px;">
                                        <input type="text" name="activity_13" id="activity_13" style="width:100%;">
                                    </td>
                                </tr>
                                <tr>
                                    <td style="font-size:18px;">
                                        <input type="time" name="time_14" id="time_14" value="${dietTimeSlotObject.time_14}">
                                    </td>
                                    <td style="width:100%;font-size:18px;">
                                        <input type="text" name="activity_14" id="activity_14" style="width:100%;">
                                    </td>
                                </tr>
                                <tr>
                                    <td style="font-size:18px;">
                                        <input type="time" name="time_15" id="time_15" value="${dietTimeSlotObject.time_15}">
                                    </td>
                                    <td style="width:100%;font-size:18px;">
                                        <input type="text" name="activity_15" id="activity_15" style="width:100%;">
                                    </td>
                                </tr>
                                <tr>
                                    <td style="font-size:18px;">
                                        <input type="time" name="time_16" id="time_16" value="${dietTimeSlotObject.time_16}">
                                    </td>
                                    <td style="width:100%;font-size:18px;">
                                        <input type="text" name="activity_16" id="activity_16" style="width:100%;">
                                    </td>
                                </tr>
                                <tr>
                                    <td style="font-size:18px;">
                                        <input type="time" name="time_17" id="time_17" value="${dietTimeSlotObject.time_17}">
                                    </td>
                                    <td style="width:100%;font-size:18px;">
                                        <input type="text" name="activity_17" id="activity_17" style="width:100%;">
                                    </td>
                                </tr>


                                </tbody>
                            </table>
                        </div>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-success" >Submit</button>
                            <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- Add Template Diet Plan Modal ends -->


        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="product-status-wrap" align="center">
                    <button type="button" class="close" data-dismiss="modal" onclick="showAddDietTemplateModal()">ADD DIET TEMPLATE</button>
                </br></br>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="product-status-wrap">
                    <h4>Diet Templates</h4>
                    <!-- <a href="paidPayments?gender=all"><button type="button" class="btn btn-info" id="myBtn">Male Trainer</button></a>&nbsp;&nbsp;
                    <a href="paidPayments?gender=male"><button type="button" class="btn btn-info" id="myBtn">Female Trainer</button></a>&nbsp;&nbsp; -->
                    <div class="asset-inner">
                        <table>
                            <tr>
                                <th style="text-align: center">Action</th>
                                <th style="text-align: center">Diet Name</th>
                                <th style="text-align: center">Time / Activity</th>
                                <th style="text-align: center">Time / Activity</th>
                                <th style="text-align: center">Time / Activity</th>
                                <th style="text-align: center">Time / Activity</th>
                                <th style="text-align: center">Time / Activity</th>
                                <th style="text-align: center">Time / Activity</th>
                                <th style="text-align: center">Time / Activity</th>
                                <th style="text-align: center">Time / Activity</th>
                                <th style="text-align: center">Time / Activity</th>
                                <th style="text-align: center">Time / Activity</th>
                                <th style="text-align: center">Time / Activity</th>
                                <th style="text-align: center">Time / Activity</th>
                                <th style="text-align: center">Time / Activity</th>
                                <th style="text-align: center">Time / Activity</th>
                                <th style="text-align: center">Time / Activity</th>
                                <th style="text-align: center">Time / Activity</th>
                                <th style="text-align: center">Time / Activity</th>
                            </tr>
                            <c:forEach items="${allDietTemplatesList}" var="object" varStatus="status">
                                <tr>
                                    <td>
                                        <button style="text-align:center;background-color:#000000" type="button" class="btn btn-info btn-lg" id="addTemplateDietPlanBtn" onclick="setDisplayValuesFromTemplate(${object.id})">Edit</button>
                                    </td>
                                    <td style="text-align: center"><c:out value="${object.name}"/></td>
                                    <td style="text-align: center"><c:out value="${object.time_1}"/><br/><c:out value="${object.activity_1}"/></td>
                                    <td style="text-align: center"><c:out value="${object.time_2}"/><br/><c:out value="${object.activity_2}"/></td>
                                    <td style="text-align: center"><c:out value="${object.time_3}"/><br/><c:out value="${object.activity_3}"/></td>
                                    <td style="text-align: center"><c:out value="${object.time_4}"/><br/><c:out value="${object.activity_4}"/></td>
                                    <td style="text-align: center"><c:out value="${object.time_5}"/><br/><c:out value="${object.activity_5}"/></td>
                                    <td style="text-align: center"><c:out value="${object.time_6}"/><br/><c:out value="${object.activity_6}"/></td>
                                    <td style="text-align: center"><c:out value="${object.time_7}"/><br/><c:out value="${object.activity_7}"/></td>
                                    <td style="text-align: center"><c:out value="${object.time_8}"/><br/><c:out value="${object.activity_8}"/></td>
                                    <td style="text-align: center"><c:out value="${object.time_9}"/><br/><c:out value="${object.activity_9}"/></td>
                                    <td style="text-align: center"><c:out value="${object.time_10}"/><br/><c:out value="${object.activity_10}"/></td>
                                    <td style="text-align: center"><c:out value="${object.time_11}"/><br/><c:out value="${object.activity_11}"/></td>
                                    <td style="text-align: center"><c:out value="${object.time_12}"/><br/><c:out value="${object.activity_12}"/></td>
                                    <td style="text-align: center"><c:out value="${object.time_13}"/><br/><c:out value="${object.activity_13}"/></td>
                                    <td style="text-align: center"><c:out value="${object.time_14}"/><br/><c:out value="${object.activity_14}"/></td>
                                    <td style="text-align: center"><c:out value="${object.time_15}"/><br/><c:out value="${object.activity_15}"/></td>
                                    <td style="text-align: center"><c:out value="${object.time_16}"/><br/><c:out value="${object.activity_16}"/></td>
                                    <td style="text-align: center"><c:out value="${object.time_17}"/><br/><c:out value="${object.activity_17}"/></td>
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