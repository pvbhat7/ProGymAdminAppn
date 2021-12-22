<%@page import="com.progym.common.model.User"%>
<%@page import="com.progym.common.model.Client"%>
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
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
      <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
      <script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
      <style>
         .modal-header, h4, .close {
         background-color: #5cb85c;
         color:white !important;
         text-align: center;
         font-size: 30px;
         }
         .modal-footer {
         background-color: #f9f9f9;
         }
      </style>
      <jsp:include page="header.jsp" />
      <script>
         window.onload = function() {
            nullifyAdditionalDataFormValues();
            //setDefaultsForDietPlanForm();
         	};

         function setDefaultsForDietPlanForm(){
            time_1.value = "06:30";
         }

         function nullifyAdditionalDataFormValues(){
            if(document.getElementById('neck') != null)
               neck.value = "";
            if(document.getElementById('chest') != null)
               chest.value = "";
            if(document.getElementById('weist') != null)
               weist.value = "";
            if(document.getElementById('arm') != null)
               arm.value = "";
            if(document.getElementById('thigh') != null)
               thigh.value = "";
            if(document.getElementById('upperHips') != null)
               upperHips.value = "";
            if(document.getElementById('hips') != null)
               hips.value = "";
            if(document.getElementById('Calf') != null)
               Calf.value = "";
            if(document.getElementById('ankle') != null)
               ankle.value = "";
         }

      </script>
   </head>
   <body>
      <!-- Start Left menu area -->
      <jsp:include page="topPage.jsp" />
      <!-- Single pro tab review Start-->
      <div class="single-pro-review-area mt-t-30 mg-b-15">
         <div class="container-fluid">
            <div class="row">
               <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">

                  <%--PROFILE BOX STARTS--%>
                     <jsp:include page="profileBox.jsp" />
                  <%--PROFILE BOX ENDS--%>

               </div>
               <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10">
                  <div class="product-payment-inner-st res-mg-t-30 analysis-progrebar-ctn">

                     <%--ALL MODULE TABS HEAD START--%>
                     <ul id="myTabedu1" class="tab-review-design">
                        <li class="active"><a href="#description">Packages</a></li>

                        <c:if test="${isEnabledMonthlyDataModule}">
                        <li><a href="#view_monthly_data">Monthly Data</a></li>
                        <li><a href="#add_monthly_data_female">Add Monthly Data</a></li>
                        </c:if>

                        <li><a href="#edit_profile">Edit Profile</a></li>

                        <c:if test="${isEnabledWorkoutModule}"> <li><a href="#workout_schedule">Workout Schedule</a></li> </c:if>
                        <c:if test="${isEnabledDietModule}"> <li><a href="#diet_plan">Diet Plan</a></li> </c:if>
                     </ul>
                     <%--ALL MODULE TABS HEAD ENDS--%>

                     <%--ALL MODULE TABS CONTENT START--%>
                     <div id="myTabContent" class="tab-content custom-product-edit st-prf-pro">

                        <!-- PACKAGES MODULE START -->
                        <div class="product-tab-list tab-pane fade active in" id="description">
                           <div class="row">
                              <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                 <div class="review-content-section">
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <button style="text-align:center;background-color:#000000" type="button" class="btn btn-info btn-lg" id="myBtn">Add Package</button>
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    &nbsp;&nbsp;&nbsp;&nbsp;
                                    <a href="<c:url value='renewPackage?gender=${clientObject.gender}&clientid=${clientObject.id}'/>">
                                       <button style="text-align:center;background-color:#000000" type="button" class="btn btn-info btn-lg">Renew Package</button>
                                    </a>
                                    <!-- Refer points show modal -->
                                    <div class="modal fade bd-example-modal-sm" id="myModalww" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
                                       <div class="modal-dialog modal-sm">
                                          <div class="modal-content">
                                             <!-- Modal Header -->
                                             <div class="modal-header">
                                                <h4 class="modal-title">Refer System</h4>
                                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                             </div>
                                             <!-- Modal body -->
                                             <div class="modal-body" align="center">
                                                <table class="table table-striped" align="center">
                                                   <thead>
                                                   <tr align="center">
                                                      <th>Points</th>
                                                      <th>Referral</th>
                                                   </tr>
                                                   </thead>
                                                   <tbody>
                                                   <tr>
                                                      <td>${clientObject.referPoints}</td>
                                                      <td>
                                                         <textarea style="color:red;" id="refField" disabled="disabled"></textarea>
                                                      </td>
                                                   </tr>
                                                   </tbody>
                                                </table>
                                             </div>
                                             <!-- Modal footer -->
                                             <div class="modal-footer">
                                                <c:if test="${clientObject.referPoints == '10'}">
                                                   <a href="<c:url value='redeemReferPoints?gender=${clientObject.gender}&clientid=${clientObject.id}'/>">
                                                      <button type="button" class="btn btn-success">Redeem Points</button>
                                                   </a>
                                                </c:if>
                                                <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                                             </div>
                                          </div>
                                       </div>
                                    </div>
                                    <script>
                                       $(document).ready(function(){
                                          $("#btnreferral").click(function(){


                                             //get data from table row

                                             var reffrralName = ${refferalName};
                                             console.log(reffrralName.trim());
                                             $("#refField").val(reffrralName.trim());

                                             //open modal
                                             $("#myModalww").modal();

                                             $("#btnsave").click(function () {
                                             })


                                          });
                                       });
                                    </script>
                                    <!-- end Refer points show modal -->

                                    <!-- show/hide mobile show modal -->
                                    <div class="modal fade bd-example-modal-sm" id="mobileModal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
                                       <div class="modal-dialog modal-sm">
                                          <div class="modal-content">
                                             <!-- Modal Header -->
                                             <div class="modal-header">
                                                <h4 class="modal-title">Security check</h4>
                                             </div>
                                             <form action="validatePassword" method="post">
                                                <!-- Modal body -->
                                                <div class="modal-body" align="center">
                                                   <label for="psw"><span class="glyphicon"></span> Enter Password</label>
                                                   <input type="password" name="password" id="password" required>
                                                   <input type="hidden" id="clientId" name="clientId" value="${clientObject.id}"/>
                                                   <input type="hidden" id="gender" name="gender" value="${clientObject.gender}"/>
                                                </div>
                                                <!-- Modal footer -->
                                                <div class="modal-footer">
                                                   <button type="submit" class="btn btn-success" >Verify</button>
                                                   <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                                                </div>
                                             </form>
                                          </div>
                                       </div>
                                    </div>
                                    <script>
                                       $(document).ready(function(){
                                          $("#btnViewMobile").click(function(){

                                             //open modal
                                             $("#mobileModal").modal();

                                             $("#btnsave").click(function () {
                                             })

                                          });
                                       });
                                    </script>
                                    <!-- end show/hide mobile show modal -->

                                    <!-- hide delete client profile modal -->
                                    <div class="modal fade bd-example-modal-sm" id="deleteClientProfileModal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
                                       <div class="modal-dialog modal-sm">
                                          <div class="modal-content">
                                             <!-- Modal Header -->
                                             <div class="modal-header">
                                                <h4 class="modal-title">Security check</h4>
                                             </div>
                                             <form action="deleteClientProfile" method="post">
                                                <!-- Modal body -->
                                                <div class="modal-body" align="center">
                                                   <label for="psw"><span class="glyphicon"></span> Enter Password</label>
                                                   <input type="password" name="password" id="password" required>
                                                   <input type="hidden" id="clientId" name="clientId" value="${clientObject.id}"/>
                                                   <input type="hidden" id="gender" name="gender" value="${clientObject.gender}"/>
                                                </div>
                                                <!-- Modal footer -->
                                                <div class="modal-footer">
                                                   <button type="submit" class="btn btn-success" >Delete Profile</button>
                                                   <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                                                </div>
                                             </form>
                                          </div>
                                       </div>
                                    </div>
                                    <script>
                                       $(document).ready(function(){
                                          $("#btnDeleteClientProfile").click(function(){

                                             //open modal
                                             $("#deleteClientProfileModal").modal();

                                             $("#btnsave").click(function () {
                                             })

                                          });
                                       });
                                    </script>
                                    <!-- end delete client profile show modal -->
                                    <!-- upload photo modal -->
                                    <div class="modal fade bd-example-modal-sm" id="uploadPhotoModal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
                                       <div class="modal-dialog modal-lg">
                                          <div class="modal-content">
                                             <!-- Modal Header -->
                                             <div class="modal-header">
                                                <h4 class="modal-title">Upload Photo</h4>
                                             </div>
                                             <!-- Modal body -->
                                             <div class="modal-body" align="center">
                                                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
                                                <script src="https://canvasjs.com/assets/script/canvasjs.min.js"> </script>
                                                <table class="table table-striped" align="center">
                                                   <thead>
                                                   <tr align="center">
                                                      <th>Video</th>
                                                      <th>Captured Photo</th>
                                                   </tr>
                                                   </thead>
                                                   <tbody>
                                                   <tr>
                                                      <td><video id="videoID" width="300" height="300" autoplay style="border: 1px solid black;"></video></td>
                                                      <td><canvas id="canvas" width="300" height="300" style="border: 1px solid black;"></canvas></td>
                                                   </tr>
                                                   </tbody>
                                                </table>
                                                <script type="text/javascript">
                                                   const video = document.getElementById('videoID');
                                                   const canvas = document.getElementById('canvas');
                                                   const ctx = canvas.getContext('2d');
                                                   var str = null;
                                                   function capture() {
                                                      ctx.drawImage(video, 0, 0, canvas.width, canvas.height);
                                                   };

                                                   function send() {
                                                      $('#uploadPhotoModal').modal('hide');
                                                      var imageData = canvas.toDataURL();
                                                      var xmlhttp = new XMLHttpRequest();
                                                      xmlhttp.open("POST", "/ProGym_war_exploded/saveCanvasImage", true);
                                                      //xmlhttp.open("POST", "/ProGymKop/saveCanvasImage", true);
                                                      xmlhttp.send(imageData);
                                                      /*$.ajax({
                                                          url:'/ProGym_war_exploded/saveCanvasImage',
                                                          data:{imageBase64: imageData},
                                                          type: 'post',
                                                          dataType: 'json',
                                                          timeout: 10000,
                                                          async: false,
                                                          error: function(){
                                                              console.log("WOOPS");
                                                          },
                                                          success: function(res){
                                                              if(res.ret==0){
                                                                  console.log("SUCCESS");
                                                              }else{
                                                                  console.log("FAIL : " + res.msg);
                                                              }
                                                          }
                                                      });*/
                                                      setTimeout(function(){
                                                         window.location.reload(1);
                                                      }, 4000);
                                                   };
                                                </script>
                                                <script type="text/javascript">
                                                   function showVideo() {
                                                      window.URL = window.URL || window.webkitURL;
                                                      navigator.getUserMedia = navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.msGetUserMedia;
                                                      navigator.getUserMedia({video: true}, function (stream) {
                                                         video.srcObject = stream;
                                                         str = stream;
                                                      }, function (e) {
                                                      });
                                                   }
                                                </script>
                                             </div>
                                             <!-- Modal footer -->
                                             <div class="modal-footer">
                                                <input type="button" class="btn btn-primary" value="Take photo" onclick="capture()" style="width: 200px; height: 30px;"/>
                                                <input type="button" class="btn btn-primary" value="Send" onclick="send()" style="width: 200px; height: 30px;"/>
                                                <button id="btnClose" type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                                             </div>
                                          </div>
                                       </div>
                                    </div>
                                    <script>
                                       $(document).ready(function(){
                                          $("#uploadPhotoLink").click(function(){
                                             showVideo();
                                             //open modal
                                             $("#uploadPhotoModal").modal();

                                             $("#uploadPhotoModal").on("hidden.bs.modal", function () {
                                                str.getTracks().forEach(function(track) { track.stop(); })
                                                ctx.clearRect(0, 0, canvas.width, canvas.height);
                                             });

                                          });
                                       });


                                    </script>
                                    <!-- end upload photo modal -->

                                    <!-- Add package modal -->
                                    <div class="modal fade" id="myModal" role="dialog">
                                       <div class="modal-dialog">
                                          <!-- Modal content-->
                                          <div class="modal-content">
                                             <div class="modal-header" style="padding:20px 30px;">
                                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                <h4>Add Package</h4>
                                             </div>
                                             <div class="modal-body" style="padding:40px 50px;">
                                                <form:form role="form" modelAttribute="clientAddPackageObject" method="post" action="addPackageForClient" id="addPackageForClient">
                                                   <div class="form-group">
                                                      <form:select path="cpackageId" class="form-control">
                                                         <form:option value="NONE" label="--- Select Package ---"/>
                                                         <form:options items="${packagesList}"/>
                                                      </form:select>
                                                   </div>
                                                   </br>
                                                   <div class="form-group">
                                                      <label for="psw"><span class="glyphicon"></span> Package Start Date</label>
                                                      <form:input path="startDate" type="date" class="form-control" id="psw" />
                                                   </div>
                                                   </br>
                                                   <div class="form-group">
                                                      <form:select path="discountPercentage" class="form-control">
                                                         <form:option value="NONE" label="--- Select Discount Amount ---"/>
                                                         <form:options items="${discountPercentageList}"/>
                                                      </form:select>
                                                   </div>
                                                   </br>
                                                   <form:input type="hidden" path="clientId" value="${clientObject.id}"/>
                                                   <form:input type="hidden" path="gender" value="${clientObject.gender}"/>
                                                   <div align="center">
                                                      <form:button type="submit" class="btn btn-danger" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Cancel</form:button>
                                                      <form:button type="submit" class="btn btn-success"><span class="glyphicon glyphicon-off"></span> Submit</form:button>
                                                   </div>
                                                </form:form>
                                             </div>
                                          </div>
                                       </div>
                                    </div>
                                    <script>
                                       $(document).ready(function(){
                                          $("#myBtn").click(function(){
                                             $("#myModal").modal();
                                          });
                                       });
                                    </script>

                                    <div class="row mg-b-15">
                                       <div class="col-lg-12">
                                          <div class="sparkline8-list">
                                             <div class="sparkline8-graph">
                                                <div class="static-table-list">
                                                   <table class="table">
                                                      <thead>
                                                      <tr>
                                                         <th hidden="true">Id</th>
                                                         <th>Package</th>
                                                         <th>Start Date</th>
                                                         <th>End Date</th>
                                                         <th>Status</th>
                                                         <th>Fees</th>
                                                         <th>Collection</th>
                                                         <th>Action</th>
                                                         <th>Transactions</th>
                                                      </tr>
                                                      </thead>
                                                      <tbody>
                                                      <c:forEach items="${clientPackagesList}" var="c_pkg" varStatus="status">
                                                         <tr>
                                                            <td hidden="true">
                                                               <c:out value="${c_pkg.id}"/>
                                                            </td>
                                                            <td>
                                                               <c:out value="${c_pkg.packageName}"/>
                                                            </td>
                                                            <td>
                                                               <c:out value="${c_pkg.packageStartDate}"/>
                                                            </td>
                                                            <td>
                                                               <c:out value="${c_pkg.packageEndDate}"/>
                                                            </td>
                                                            <td>
                                                               <c:out value="${c_pkg.clientPackageStatus}"/>
                                                            </td>
                                                            <td>
                                                               <c:out value="${c_pkg.packageFees}"/>
                                                            </td>
                                                            <td>
                                                               <c:out value="${c_pkg.amountPaid}"/>
                                                            </td>
                                                            <td>
                                                               <c:if test="${c_pkg.packageFees != c_pkg.amountPaid}">
                                                                  <input class="btnedit btn btn-success btn-xs" type="button" value="Pay" />
                                                               </c:if>
                                                               <input class="btnEditClientPackage btn-primary btn-xs" type="button" value="Edit" />
                                                               <input class="btnDeleteClientAssignedPackage btn btn-danger btn-xs" type="button" value="Delete"/>
                                                               </a>
                                                            </td>
                                                            <td>
                                                               <div>
                                                                  <table id="tbldata" border="1">
                                                                     <tr>
                                                                        <th style="text-align:center;">Amount</th>
                                                                        <th style="text-align:center;">Date</th>
                                                                        <th style="text-align:center;">Approved ?</th>
                                                                        <th style="text-align:center;">Paid By</th>
                                                                        <th style="text-align:center;">Invoice</th>
                                                                     </tr>
                                                                     <c:forEach var = "trans" items = "${c_pkg.paymentTransactions}" varStatus="status1">
                                                                        <tr>
                                                                           <td style="text-align:center;">
                                                                              &nbsp;&nbsp;&nbsp;&nbsp;
                                                                              <c:out value="${trans.feesPaid}"/>
                                                                              &nbsp;&nbsp;&nbsp;&nbsp;
                                                                           </td>
                                                                           <td style="text-align:center;">
                                                                              &nbsp;&nbsp;
                                                                              <c:out value="${trans.paymentDate}"/>
                                                                              &nbsp;&nbsp;
                                                                           </td>
                                                                           <c:if test="${trans.isApproved == 'NO'}">
                                                                              <%
                                                                                 if(session == null)
                                                                                    response.sendRedirect("login");
                                                                                 else if(session.getAttribute("loggedInUser") == null)
                                                                                    response.sendRedirect("login");

                                                                                 if(((User)session.getAttribute("loggedInUser")).getAuthorizedToApprovePayment().equals("YES")){
                                                                              %>
                                                                              <td height="40">&nbsp;&nbsp;
                                                                                       <%
                                                                                       }
                                                                                       else{
                                                                                       %>
                                                                              <td height="40" bgcolor="#FF3333">
                                                                                 &nbsp;&nbsp;
                                                                                 <%} %>
                                                                                 <%
                                                                                    if(session == null)
                                                                                       response.sendRedirect("login");
                                                                                    else if(session.getAttribute("loggedInUser") == null)
                                                                                       response.sendRedirect("login");
                                                                                    if(((User)session.getAttribute("loggedInUser")).getAuthorizedToApprovePayment().equals("YES")){
                                                                                 %>
                                                                                 <a href="<c:url value='approveTransaction?txnId=${c_pkg.paymentTransactions[status1.index].id}&cID=${clientPackagesList[status.index].client.id}&gender=${clientPackagesList[status.index].client.gender}'/>">
                                                                                    <input class="btnedit btn btn-danger btn-xs" type="button" value="Approve" />
                                                                                 </a>
                                                                                 <%}
                                                                                 else{%>
                                                                                 <c:out value="${trans.isApproved}"/>
                                                                                 <%
                                                                                    }%>
                                                                                 &nbsp;&nbsp;
                                                                              </td>
                                                                           </c:if>
                                                                           <c:if test="${trans.isApproved == 'YES'}">
                                                                              <td bgcolor="#00FF00">
                                                                                 &nbsp;&nbsp;
                                                                                 <c:out value="${trans.isApproved}"/>
                                                                                 &nbsp;&nbsp;
                                                                              </td>
                                                                           </c:if>
                                                                           <td style="text-align:center;">
                                                                              <c:out value="${trans.paymentMode}"/>
                                                                           </td>
                                                                           <td style="border: solid 2px #ddd; padding:10px 10px;"><a href="<c:url value='sendInvoice?txnId=${c_pkg.paymentTransactions[status1.index].id}&cID=${clientPackagesList[status.index].client.id}&email=${clientObject.email}&gender=${clientPackagesList[status.index].client.gender}'/>">
                                                                              <input class="btn btn-primary btn-xs" type="button" value="&nbsp;&nbsp;Send Invoice&nbsp;&nbsp;" />
                                                                           </a>
                                                                           </td>
                                                                        </tr>
                                                                     </c:forEach>
                                                                  </table>
                                                               </div>
                                                            </td>
                                                            <!-- Add Transaction Modal -->
                                                            <div class="modal fade" id="payModel" role="dialog">
                                                               <div class="modal-dialog">
                                                                  <!-- Modal content-->
                                                                  <div class="modal-content">
                                                                     <div class="modal-header">
                                                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                                        <h4 class="modal-title">Make Payment</h4>
                                                                     </div>
                                                                     <form:form action="addTransaction" class="dropzone dropzone-custom needsclick add-professors" id="addTransaction" modelAttribute="transactionObject" method="post">
                                                                        <div class="modal-body">
                                                                           <form:input path="clientId" value="${clientObject.id}" title="" name="clientId" type="hidden" class="form-control" ></form:input>
                                                                           <form:input path="clientGender" value="${clientObject.gender}" title="" name="clientGender" type="hidden" class="form-control" ></form:input>
                                                                           <label for="psw"><span class="glyphicon"></span> Package</label>
                                                                           <input id="txt_item" class="form-control" type="text" readonly/>
                                                                           </br>
                                                                           <label for="psw"><span class="glyphicon"></span> Paid Payment</label>
                                                                           <input id="txt_Price1" class="form-control" type="text" readonly/>
                                                                           </br>
                                                                           <label for="psw"><span class="glyphicon"></span> Remaining Payment</label>
                                                                           <form:input id="feesPaid" name="feesPaid" path="feesPaid" class="form-control" type="number" />
                                                                           </br>
                                                                           <label for="psw"><span class="glyphicon"></span> Payment Mode</label>
                                                                           <form:select path="paymentMode" class="form-control">
                                                                              <form:options items="${paymentModes}"/>
                                                                           </form:select>
                                                                           </br>
                                                                           <form:input id="packageDetailsId" class="form-control" type="hidden" name="packageDetailsId" path="packageDetailsId"/>
                                                                        </div>
                                                                        <div class="modal-footer">
                                                                           <form:button id="basicInfo" type="submit" class="btn btn-primary" onclick="this.disabled=true;this.value='Submitting...'; this.form.submit();">Pay</form:button>
                                                                           <script>
                                                                              //submit search, display loading message
                                                                              $('input[type = submit]').click(function(){
                                                                                 $.blockUI({ css: {
                                                                                       border: 'none',
                                                                                       padding: '15px',
                                                                                       backgroundColor: '#000',
                                                                                       opacity: .6
                                                                                    } });
                                                                              });
                                                                           </script>
                                                                           <form:button type="button" class="btn btn-default" data-dismiss="modal">Close</form:button>
                                                                        </div>
                                                                     </form:form>
                                                                  </div>
                                                               </div>
                                                            </div>
                                                            <!-- Edit Client assigned Modal -->
                                                            <div class="modal fade" id="editPackageModal" role="dialog">
                                                               <div class="modal-dialog">
                                                                  <!-- Modal content-->
                                                                  <div class="modal-content">
                                                                     <div class="modal-header">
                                                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                                        <h4 class="modal-title">Update Package</h4>
                                                                     </div>
                                                                     <form action="updateClientAssignedPackage" class="dropzone dropzone-custom needsclick add-professors" method="post">
                                                                        <div class="modal-body">
                                                                           <label for="psw"><span class="glyphicon"></span> Package Name</label>
                                                                           <input id="u_package" name="u_package" class="form-control" type="text" readonly/>

                                                                           <label for="psw"><span class="glyphicon"></span> Package StartDate</label>
                                                                           <input id="u_startdate" name="u_startdate" class="form-control" type="date" readonly/>
                                                                           <label for="psw"><span class="glyphicon"></span> Package EndDate</label>
                                                                           <input id="u_enddate" name="u_enddate" class="form-control" type="date" required="true"/>
                                                                           <label for="psw"><span class="glyphicon"></span> Fees </label>
                                                                           <input id="u_fees" name="u_fees" class="form-control" type="number" required="true"/>
                                                                           <input id="u_pkgId" class="form-control" type="hidden" name="u_pkgId" />
                                                                           <input id="u_clientid" class="form-control" type="hidden" value="<%out.print(((Client)request.getAttribute("clientObject")).getId());%>" name="u_clientid" />
                                                                           <input id="u_gender" class="form-control" type="hidden" name="u_gender" value="<%out.print(((Client)request.getAttribute("clientObject")).getGender());%>"/>
                                                                        </div>
                                                                        <div class="modal-footer">
                                                                           <button id="basicInfo" type="submit" class="btn btn-primary">Update Package</button>
                                                                           <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                                        </div>
                                                                     </form>
                                                                  </div>
                                                               </div>
                                                            </div>

                                                            <div class="modal fade" id="deletePackageModal" role="dialog">
                                                               <div class="modal-dialog modal-sm">
                                                                  <!-- Modal content-->
                                                                  <div class="modal-content">
                                                                     <div class="modal-header">
                                                                        <h4 class="modal-title">Security check</h4>
                                                                     </div>
                                                                     <form action="deleteClientAssignedPackage" method="post">
                                                                        <!-- Modal body -->
                                                                        <div class="modal-body" align="center">
                                                                           <label for="psw"><span class="glyphicon"></span> Enter Password</label>
                                                                           <input type="password" name="password" id="password" required>
                                                                           <input type="hidden" id="clientId" name="clientId" value="${clientObject.id}"/>
                                                                           <input type="hidden" id="gender" name="gender" value="${clientObject.gender}"/>
                                                                           <input type="hidden" id="pkg_id" name="pkg_id"/>
                                                                        </div>
                                                                        <!-- Modal footer -->
                                                                        <div class="modal-footer">
                                                                           <button type="submit" class="btn btn-success" >Delete Package</button>
                                                                           <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                                                                        </div>
                                                                     </form>

                                                                  </div>
                                                               </div>
                                                            </div>


                                                            <script>
                                                               $(function () {

                                                                  /* on click - pay */
                                                                  $(".btnedit").click(function () {
                                                                     //get data from table row
                                                                     var packageId = $(this).parent().prev().prev().prev().prev().prev().prev().prev().text();
                                                                     var packageName = $(this).parent().prev().prev().prev().prev().prev().prev().text();
                                                                     var paidPayment = $(this).parent().prev().text();
                                                                     var total = $(this).parent().prev().prev().text();
                                                                     var remaining = total - paidPayment;

                                                                     //assign to value for input box inside modal
                                                                     $("#txt_item").val(packageName);
                                                                     $("#txt_Price1").val(paidPayment);
                                                                     $("#feesPaid").val(remaining);
                                                                     $("#packageDetailsId").val(packageId);

                                                                     //open modal
                                                                     $("#payModel").modal();

                                                                     $("#btnsave").click(function () {
                                                                     })
                                                                  })

                                                                  /* on click - edit client assigned package */
                                                                  $(".btnEditClientPackage").click(function () {
                                                                     //get data from table row
                                                                     var packageId = $(this).parent().prev().prev().prev().prev().prev().prev().prev().text();
                                                                     var packageName = $(this).parent().prev().prev().prev().prev().prev().prev().text();
                                                                     var startdate = $(this).parent().prev().prev().prev().prev().prev().text();
                                                                     var enddate = $(this).parent().prev().prev().prev().prev().text();
                                                                     var fees = $(this).parent().prev().prev().text().replace(/\s+/g, '');
                                                                     var initial = startdate.split(/\//);
                                                                     var formattedDate = [initial[2],initial[1],initial[0]].join('-').replace(/\s+/g, '');

                                                                     var initialEndDate = enddate.split(/\//);
                                                                     var formattedEndDate = [ initialEndDate[2], initialEndDate[1], initialEndDate[0] ].join('-').replace(/\s+/g, '');

                                                                     //assign to value for input box inside modal
                                                                     $("#u_pkgId").val(packageId);
                                                                     $("#u_package").val(packageName);
                                                                     $("#u_fees").val(fees);
                                                                     $("#u_startdate").val(formattedDate);
                                                                     $("#u_enddate").val(formattedEndDate);
                                                                     /* $("#u_clientid").val(cliendId);
                                                                     $("#u_gender").val(gender); */

                                                                     //open modal
                                                                     $("#editPackageModal").modal();

                                                                     $("#btnsave").click(function () {
                                                                        //make ajax request to update data

                                                                        //and in ajax success callback function
                                                                        //hide modal
                                                                        //$("#myModal").modal("hide")
                                                                     })
                                                                  })

                                                                  $(".btnDeleteClientAssignedPackage").click(function () {
                                                                     //get data from table row
                                                                     var packageId = $(this).parent().prev().prev().prev().prev().prev().prev().prev().text();
                                                                     console.log(packageId);
                                                                     //assign to value for input box inside modal
                                                                     $("#pkg_id").val(packageId);

                                                                     //open modal
                                                                     $("#deletePackageModal").modal();

                                                                     $("#btnsave").click(function () {

                                                                     })
                                                                  })

                                                               })
                                                            </script>
                                                         </tr>
                                                      </c:forEach>
                                                      </tbody>
                                                   </table>
                                                </div>
                                             </div>
                                          </div>
                                       </div>
                                    </div>
                                 </div>
                              </div>
                           </div>
                        </div>
                        <!-- PACKAGES MODULE ENDS -->

                        <!-- MONTHLY DATA MODULE STARTS -->
                        <c:if test="${isEnabledMonthlyDataModule}">
                           <!--MONTHLY DATA TAB 2 START -->
                           <div class="product-tab-list tab-pane fade" id="view_monthly_data">
                              <div class="row">
                                 <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                    <div class="review-content-section">
                                       <!-- table -->
                                       <div class="row mg-b-15">
                                          <div class="col-lg-12">
                                             <div class="sparkline8-list">
                                                <div class="sparkline8-graph">
                                                   <div class="static-table-list">
                                                      <table class="table">
                                                         <thead>
                                                         <tr>
                                                            <th hidden="true">Id</th>
                                                            <th>Date</th>
                                                            <th>Month</th>
                                                            <th>Weight</th>
                                                            <th>Height</th>
                                                            <th>Neck</th>
                                                            <th>Chest</th>
                                                            <th>Weist</th>
                                                            <th>Arm</th>
                                                            <th>Thigh</th>
                                                            <th>Upper Hips</th>
                                                            <th>Hips</th>
                                                            <th>Calf</th>
                                                            <th>Ankle</th>
                                                            <th>Delete</th>
                                                         </tr>
                                                         </thead>
                                                         <tbody>
                                                         <c:forEach items="${femaleAditionalDataList}" var="obj" varStatus="status">
                                                            <tr>
                                                               <td>
                                                                  <c:out value="${obj.date}"/>
                                                               </td>
                                                               <td>
                                                                  <c:out value="${obj.month}"/>
                                                               </td>
                                                               <td>
                                                                  <c:out value="${obj.weight}"/>
                                                               </td>
                                                               <td>
                                                                  <c:out value="${obj.height}"/>
                                                               </td>
                                                               <td>
                                                                  <c:out value="${obj.neck}"/>
                                                               </td>
                                                               <td>
                                                                  <c:out value="${obj.chest}"/>
                                                               </td>
                                                               <td>
                                                                  <c:out value="${obj.weist}"/>
                                                               </td>
                                                               <td>
                                                                  <c:out value="${obj.arm}"/>
                                                               </td>
                                                               <td>
                                                                  <c:out value="${obj.thigh}"/>
                                                               </td>
                                                               <td>
                                                                  <c:out value="${obj.upperHips}"/>
                                                               </td>
                                                               <td>
                                                                  <c:out value="${obj.hips}"/>
                                                               </td>
                                                               <td>
                                                                  <c:out value="${obj.calf}"/>
                                                               </td>
                                                               <td>
                                                                  <c:out value="${obj.ankle}"/>
                                                               </td>
                                                               <td>
                                                                  <a href="<c:url value='deleteFemaleClientAdditionalDetails?id=${obj.id}&gender=${clientObject.gender}&clientid=${clientObject.id}'/>">
                                                                     <input class="btn btn-danger btn-xs" type="button" value="Delete" />
                                                                  </a>
                                                               </td>
                                                            </tr>
                                                         </c:forEach>
                                                         </tbody>
                                                      </table>
                                                   </div>
                                                </div>
                                             </div>
                                          </div>
                                       </div>
                                       <!-- table end -->
                                    </div>
                                 </div>
                              </div>
                           </div>
                           <!--MONTHLY DATA TAB 2 END -->

                           <!--ADD MONTHLY DATA TAB 3 START -->
                           <div class="product-tab-list tab-pane fade" id="add_monthly_data_female">
                              <div class="row">
                                 <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                    <form:form action="submitFemaleAditionalDataForm" class="dropzone dropzone-custom needsclick add-professors" id="addTransaction" modelAttribute="femaleAditionalDataFormObject" method="post">
                                       <div class="review-content-section">
                                          <div class="row">
                                             <div class="col-lg-3">
                                                <div class="form-group">
                                                   <form:label path="day">Select Day</form:label>
                                                   <form:select path="day" class="form-control" required="required">
                                                      <form:option value="NONE" label="--- Select Day ---"/>
                                                      <form:options items="${daysList}"/>
                                                   </form:select>
                                                </div>
                                             </div>
                                             <div class="col-lg-3">
                                                <div class="form-group">
                                                   <form:label path="month">Select Month</form:label>
                                                   <form:select path="month" class="form-control" required="required">
                                                      <form:option value="NONE" label="--- Select Month ---"/>
                                                      <form:options items="${monthsList}"/>
                                                   </form:select>
                                                </div>
                                             </div>
                                             <div class="col-lg-3">
                                                <div class="form-group">
                                                   <form:label path="year">Select Year</form:label>
                                                   <form:select path="year" class="form-control" required="required">
                                                      <form:option value="NONE" label="--- Select Year ---"/>
                                                      <form:options items="${yearsList}"/>
                                                   </form:select>
                                                </div>
                                             </div>
                                          </div>
                                          <div class="row">
                                             <div class="col-lg-3">
                                                <div class="form-group">
                                                   <form:input path="clientId" title="" name="clientId" type="hidden" class="form-control" placeholder="Enter Weight"></form:input>
                                                </div>
                                                <div class="form-group">
                                                   <form:input path="gender" title="" name="gender" type="hidden" class="form-control" placeholder="Enter Weight"></form:input>
                                                </div>
                                                <div class="form-group">
                                                   <form:label path="weight">Weight</form:label>
                                                   <form:input path="weight" title="" name="weight" type="number" class="form-control" placeholder="Enter Weight"></form:input>
                                                </div>
                                                <div class="form-group">
                                                   <form:label path="height">Height</form:label>
                                                   <form:input path="height" name="height" type="number" class="form-control" placeholder="Enter Height"></form:input>
                                                </div>
                                                <div class="form-group">
                                                   <form:label path="neck">Neck</form:label>
                                                   <form:input path="neck" name="neck" type="number" class="form-control" placeholder="Enter Neck"></form:input>
                                                </div>
                                                <div class="form-group">
                                                   <form:label path="chest">Chest</form:label>
                                                   <form:input path="chest" name="chest" type="number" class="form-control" placeholder="Enter Chest"></form:input>
                                                </div>
                                                <div class="form-group">
                                                   <form:label path="weist">Weist</form:label>
                                                   <form:input path="weist" name="weist" type="number" class="form-control" placeholder="Enter Weist"></form:input>
                                                </div>
                                                <div class="form-group">
                                                   <form:label path="arm">Arm</form:label>
                                                   <form:input path="arm" name="arm" type="number" class="form-control" placeholder="Enter Arm"></form:input>
                                                </div>
                                             </div>
                                             <div class="col-lg-3">
                                                <div class="form-group">
                                                   <form:label path="thigh">Thigh</form:label>
                                                   <form:input path="thigh" name="thigh" type="number" class="form-control" placeholder="Enter Theigh"></form:input>
                                                </div>
                                                <div class="form-group">
                                                   <form:label path="upperHips">Upper Hips</form:label>
                                                   <form:input path="upperHips" name="upperHips" type="number" class="form-control" placeholder="Enter Upper Hips"></form:input>
                                                </div>
                                                <div class="form-group">
                                                   <form:label path="hips">Hips</form:label>
                                                   <form:input path="hips" name="hips" type="number" class="form-control" placeholder="Enter Hips"></form:input>
                                                </div>
                                                <div class="form-group">
                                                   <form:label path="Calf">Calf</form:label>
                                                   <form:input path="calf" name="calf" type="number" class="form-control" placeholder="Enter Calf"></form:input>
                                                </div>
                                                <div class="form-group">
                                                   <form:label path="ankle">Ankle</form:label>
                                                   <form:input path="ankle" name="ankle" type="number" class="form-control" placeholder="Enter Ankle"></form:input>
                                                </div>
                                             </div>
                                          </div>
                                          </br>
                                          <div class="row">
                                             <div class="col-lg-12">
                                                <div class="payment-adress mg-t-15">
                                                   <button type="submit" class="btn btn-primary waves-effect waves-light mg-b-15">Submit</button>
                                                </div>
                                             </div>
                                          </div>
                                       </div>
                                    </form:form>
                                 </div>
                              </div>
                           </div>
                           <!--ADD MONTHLY DATA TAB 3 END -->
                        </c:if>
                        <!-- MONTHLY DATA MODULE ENDS -->

                        <!-- EDIT PROFILE MODULE STARTS -->
                        <div class="product-tab-list tab-pane fade" id="edit_profile">
                           <div class="row">
                              <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                 <form:form action="editClientProfile" class="dropzone dropzone-custom needsclick add-professors" id="editClientProfileFormObjectid" modelAttribute="editClientProfileFormObject" method="post">
                                    <div class="review-content-section">
                                       <div class="row">
                                          <div class="col-lg-6">
                                             <div class="form-group">
                                                <form:input path="id" title="" name="id" type="hidden" class="form-control" placeholder="Enter Weight"></form:input>
                                             </div>
                                             <div class="form-group">
                                                <form:input path="gender" title="" name="gender" type="hidden" class="form-control" placeholder="Enter Weight"></form:input>
                                             </div>
                                             <div class="form-group">
                                                <form:label path="name">Name</form:label>
                                                <form:input path="name" name="name" type="text" class="form-control" ></form:input>
                                             </div>
                                             <div class="form-group">
                                                <form:label path="mobile">Mobile</form:label>
                                                <form:input path="mobile" name="mobile" type="text" class="form-control" ></form:input>
                                             </div>
                                             <%-- <div class="form-group">
                                                <form:label path="gender">Gender</form:label>
                                                    <form:input path="gender" name="gender" type="text" class="form-control" ></form:input>
                                                </div> --%>
                                             <div class="form-group">
                                                <form:label path="birthDate">Birth Date</form:label>
                                                <form:input disabled="true" path="birthDate" name="birthDate" type="text" class="form-control" ></form:input>
                                             </div>
                                             <div class="form-group">
                                                <form:label path="height">Height</form:label>
                                                <form:input path="height" name="height" type="number" class="form-control" ></form:input>
                                             </div>
                                             <div class="form-group">
                                                <form:label path="weight">Weight</form:label>
                                                <form:input path="weight" name="weight" type="number" class="form-control" ></form:input>
                                             </div>
                                          </div>
                                          <div class="col-lg-6">
                                             <div class="form-group">
                                                <form:label path="email">Email</form:label>
                                                <form:input path="email" name="email" type="text" class="form-control" ></form:input>
                                             </div>
                                             <div class="form-group">
                                                <form:label path="address">Address</form:label>
                                                <form:input path="address" name="address" type="text" class="form-control" ></form:input>
                                             </div>
                                             <div class="form-group">
                                                <form:label path="bloodGroup">Blood Group</form:label>
                                                <form:input path="bloodGroup" name="bloodGroup" type="text" class="form-control" ></form:input>
                                             </div>
                                             <div class="form-group">
                                                <form:label path="previousGym">Previous Gym</form:label>
                                                <form:input path="previousGym" name="previousGym" type="text" class="form-control" ></form:input>
                                             </div>
                                          </div>
                                       </div>
                                       </br>
                                       <div class="row">
                                          <div class="col-lg-12">
                                             <div class="payment-adress mg-t-15">
                                                <button type="submit" class="btn btn-primary waves-effect waves-light mg-b-15">Submit</button>
                                             </div>
                                          </div>
                                       </div>
                                    </div>
                                 </form:form>
                              </div>
                           </div>
                        </div>
                        <!-- EDIT PROFILE MODULE ENDS -->

                        <!-- WORKOUT SCHEDULE TAB 5 STARTS-->
                        <c:if test="${isEnabledWorkoutModule}">
                           <div class="product-tab-list tab-pane fade" id="workout_schedule">
                              <div class="row">
                                 <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                    <h4>Current Workout Plan: ${defaultWorkoutPlanName}</h4>

                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    &nbsp;&nbsp;&nbsp;&nbsp;
                                    <button style="text-align:center;background-color:#000000" type="button" class="btn btn-info btn-lg" id="addNewWorkoutBtn">Add New Workout</button>
                                    <button style="text-align:center;background-color:#000000" type="button" class="btn btn-info btn-lg" id="defaultWorkoutBtn">Default Workout Plan</button>
                                    <!-- Add new workout Modal starts -->
                                    <div class="modal fade" id="addNewWorkoutModal" role="dialog">
                                       <div class="modal-dialog">
                                          <!-- Modal content-->
                                          <div class="modal-content">
                                             <div class="modal-header" style="padding:20px 30px;">
                                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                <h4>Add New Workout</h4>
                                             </div>
                                             <form action="submitWorkoutMainData" method="post">
                                                <div class="modal-body" style="padding:40px 50px;">
                                                   </br></br>
                                                   <div align="center">
                                                      <label style="font-size:18px;" for="dietDate"><span class="glyphicon"></span>Select Date</label>
                                                      <input style="font-size:18px;" type="date" id="workoutDate" name="workoutDate"/>
                                                   </div>
                                                   <div align="center">
                                                      </br>
                                                      <label style="font-size:18px;" for="mainWorkoutType">Choose Main Workout:</label></br>
                                                      <select style="font-size:18px;" name="mainWorkoutType" id="mainWorkoutType" >
                                                         <c:forEach items="${workoutMainTypeList}" var="list" varStatus="status">
                                                            <option style="font-size:18px;" value="${list.id}">${list.name}</option>
                                                         </c:forEach>
                                                      </select>
                                                      </br>
                                                   </div>
                                                   <input type="hidden" id="clientId" name="clientId" value="${clientObject.id}"/>
                                                   <input type="hidden" id="gender" name="gender" value="${clientObject.gender}"/>


                                                </div>
                                                <div class="modal-footer">
                                                   <button type="submit" class="btn btn-success">Submit</button>
                                                   <button type="button" class="btn btn-danger" data-dismiss="modal">Close
                                                   </button>
                                                </div>
                                             </form>

                                          </div>
                                       </div>
                                    </div>
                                    <script>
                                       $(document).ready(function(){
                                          $("#addNewWorkoutBtn").click(function(){
                                             $("#addNewWorkoutModal").modal();
                                          });
                                       });
                                    </script>
                                    <!-- Add new workout Modal ends -->
                                    <!-- default workout Modal starts -->
                                    <div class="modal fade" id="defaultWorkoutModal" role="dialog">
                                       <div class="modal-dialog">
                                          <!-- Modal content-->
                                          <div class="modal-content">
                                             <div class="modal-header" style="padding:20px 30px;">
                                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                <h4>Set Default Workout Plan</h4>
                                             </div>
                                             <form action="submitDefaultWorkoutMainData" method="post">
                                                <div class="modal-body" style="padding:40px 50px;">
                                                   <div align="center">
                                                      </br>
                                                      <label style="font-size:18px;" for="mainWorkoutType">Choose Default Workout Plan:</label></br>
                                                      <select style="font-size:18px;" name="workoutPlan" id="workoutPlan" >
                                                         <c:forEach items="${activeWorkoutPlansList}" var="list" varStatus="status">
                                                            <option style="font-size:18px;" value="${list.id}">${list.name}</option>
                                                         </c:forEach>
                                                      </select>
                                                      </br>
                                                   </div>
                                                   <input type="hidden" id="clientId" name="clientId" value="${clientObject.id}"/>
                                                   <input type="hidden" id="gender" name="gender" value="${clientObject.gender}"/>


                                                </div>
                                                <div class="modal-footer">
                                                   <button type="submit" class="btn btn-success">Submit</button>
                                                   <button type="button" class="btn btn-danger" data-dismiss="modal">Close
                                                   </button>
                                                </div>
                                             </form>

                                          </div>
                                       </div>
                                    </div>
                                    <script>
                                       $(document).ready(function(){
                                          $("#defaultWorkoutBtn").click(function(){
                                             $("#defaultWorkoutModal").modal();
                                          });
                                       });
                                    </script>
                                    <!-- default workout Modal ends -->
                                    </br></br>

                                       <%--workout box starts--%>
                                    <div class="row mg-b-15">
                                       <div class="col-lg-12">
                                          <div class="sparkline8-list">
                                             <div class="sparkline8-graph">
                                                <div class="static-table-list">
                                                   <table class="table">
                                                      <thead>
                                                      <tr>
                                                         <th hidden="true">Id</th>
                                                         <th style="text-align: center;font-size: 16px">Date</th>
                                                         <th style="text-align: center;font-size: 16px">WorkoutType</th>
                                                         <th></th>
                                                      </tr>
                                                      </thead>
                                                      <tbody>
                                                      <c:forEach items="${clientWorkoutList}" var="workout" varStatus="status">
                                                         <tr>
                                                            <td hidden="true">
                                                               <c:out value="${workout.cid}"/>
                                                            </td>
                                                            <td style="font-weight: bold;font-size: 17px;text-align: center">
                                                               <c:out value="${workout.date}"/>
                                                            </td>
                                                            <td style="font-weight: bold;font-size: 17px;text-align: center">
                                                               <c:out value="${workout.mainWorkoutType.name}"/>
                                                            </td>

                                                            <td>
                                                               <div>
                                                                  <table id="tbldata">
                                                                     <thead>
                                                                     <tr>
                                                                        <th style="text-align:center;" style="width:50%;font-weight: bold;">Sub-Type</th>
                                                                        <th style="text-align:center;" style="width:30px;font-weight: bold;">Sets</th>
                                                                        <th style="text-align:center;" style="width:30px;font-weight: bold;">Reps</th>
                                                                        <th style="text-align:center;" style="width:30px;font-weight: bold;">Action</th>
                                                                        <th style="text-align:center;" style="width:30px;font-weight: bold;">Client Performance</th>
                                                                     </tr>
                                                                     </thead>
                                                                     <tbody>
                                                                     <c:forEach var = "subType" items = "${workout.workoutSubTypeList}" varStatus="status1">
                                                                        <tr>
                                                                           <td style="text-align:center;color: blue;width:50%;padding-right: 10px">
                                                                              &nbsp;&nbsp;
                                                                              <c:out value="${subType.twsid}"/>
                                                                              &nbsp;&nbsp;
                                                                           </td>
                                                                           <td style="text-align:center;color: blue;width:30px">
                                                                              &nbsp;&nbsp;&nbsp;&nbsp;
                                                                              <c:out value="${subType.sets}"/>
                                                                              &nbsp;&nbsp;&nbsp;&nbsp;
                                                                           </td>
                                                                           <td style="text-align:center;color: blue">
                                                                              &nbsp;&nbsp;
                                                                              <c:out value="${subType.maxReps}"/>
                                                                              &nbsp;&nbsp;
                                                                           </td>
                                                                           <td style="width:30px;padding-bottom: 5px">
                                                                                 <%--<a href="<c:url value='deleteSubType?subTypeId=${subType.id}&clientId=${clientObject.id}&gender=${clientObject.gender}'/>">
                                                                                    <input class="btn btn-danger btn-xs" type="button" value="&nbsp;&nbsp;Delete&nbsp;&nbsp;" />--%>
                                                                              </a>
                                                                           </td>
                                                                           <td align="center">
                                                                              <c:if test="${subType.clientPerformance == true}">
                                                                                 <img style="max-width: 18px" src="https://tavrostechinfo.com/PROGYM/img/green_tck.png">
                                                                              </c:if>
                                                                              <c:if test="${subType.clientPerformance == false}">
                                                                                 <img style="max-width: 20px" src="https://tavrostechinfo.com/PROGYM/img/cross_mark.jpg">
                                                                              </c:if>
                                                                           </td>
                                                                        </tr>
                                                                     </c:forEach>
                                                                     <tr >
                                                                        <form action="submitWorkoutSubTypeData" method="post">

                                                                           <td align="center" style="width:50%;padding-top: 5px;padding-right: 10px">
                                                                              <select name="workoutSubType" id="workoutSubType" >
                                                                                 <option value="-1">______________Select______________</option>
                                                                                 <c:forEach items="${workout.subTypesStaticList}" var="list" varStatus="status">
                                                                                    <%--<c:if test="${workout.mainWorkoutType.id == list.id}">
                                                                                       <option value="${list.id}">${list.name}</option>
                                                                                    </c:if>--%>
                                                                                    <option value="${list.id}">${list.name}</option>
                                                                                 </c:forEach>
                                                                              </select>
                                                                           </td>
                                                                           <td align="center" style="width:30px;padding-top: 5px;padding-right: 10px">
                                                                              <select name="sets" id="sets">
                                                                                 <option value="-1">Select</option>
                                                                                 <option value="1">1</option>
                                                                                 <option value="2">2</option>
                                                                                 <option value="3">3</option>
                                                                                 <option value="4">4</option>
                                                                                 <option value="5">5</option>
                                                                                 <option value="6">6</option>
                                                                                 <option value="7">7</option>
                                                                                 <option value="8">8</option>
                                                                                 <option value="9">9</option>
                                                                                 <option value="10">10</option>
                                                                              </select>
                                                                           </td>

                                                                           <td align="center" style="width:30px;padding-top: 5px;padding-right: 10px">
                                                                              <select name="maxReps" id="maxReps" >
                                                                                 <option value="-1">Select</option>
                                                                                 <option value="1">1</option>
                                                                                 <option value="2">2</option>
                                                                                 <option value="3">3</option>
                                                                                 <option value="4">4</option>
                                                                                 <option value="5">5</option>
                                                                                 <option value="6">6</option>
                                                                                 <option value="7">7</option>
                                                                                 <option value="8">8</option>
                                                                                 <option value="9">9</option>
                                                                                 <option value="10">10</option>
                                                                                 <option value="11">11</option>
                                                                                 <option value="12">12</option>
                                                                                 <option value="13">13</option>
                                                                                 <option value="14">14</option>
                                                                                 <option value="15">15</option>
                                                                              </select>
                                                                           </td>
                                                                           <td align="center" style="width:30px;padding-top: 5px;padding-right: 15px">
                                                                              <input align="center" class="btn btn-success btn-xs" type="submit" value="&nbsp;&nbsp;Add&nbsp;&nbsp;" />
                                                                           </td>


                                                                           <input type="hidden" id="clientId" name="clientId" value="${clientObject.id}"/>
                                                                           <input type="hidden" id="gender" name="gender" value="${clientObject.gender}"/>
                                                                           <input type="hidden" name="workoutObjectId" id="workoutObjectId" value="${workout.id}" />
                                                                        </form>
                                                                     </tr>
                                                                     </tbody>
                                                                  </table>
                                                               </div>
                                                            </td>
                                                            <!-- Add Transaction Modal -->
                                                            <div class="modal fade" id="payModel" role="dialog">
                                                               <div class="modal-dialog">
                                                                  <!-- Modal content-->
                                                                  <div class="modal-content">
                                                                     <div class="modal-header">
                                                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                                        <h4 class="modal-title">Make Payment</h4>
                                                                     </div>
                                                                     <form:form action="addTransaction" class="dropzone dropzone-custom needsclick add-professors" id="addTransaction" modelAttribute="transactionObject" method="post">
                                                                        <div class="modal-body">
                                                                           <form:input path="clientId" value="${clientObject.id}" title="" name="clientId" type="hidden" class="form-control" ></form:input>
                                                                           <form:input path="clientGender" value="${clientObject.gender}" title="" name="clientGender" type="hidden" class="form-control" ></form:input>
                                                                           <label for="psw"><span class="glyphicon"></span> Package</label>
                                                                           <input id="txt_item" class="form-control" type="text" readonly/>
                                                                           </br>
                                                                           <label for="psw"><span class="glyphicon"></span> Paid Payment</label>
                                                                           <input id="txt_Price1" class="form-control" type="text" readonly/>
                                                                           </br>
                                                                           <label for="psw"><span class="glyphicon"></span> Remaining Payment</label>
                                                                           <form:input id="feesPaid" name="feesPaid" path="feesPaid" class="form-control" type="number" />
                                                                           </br>
                                                                           <label for="psw"><span class="glyphicon"></span> Payment Mode</label>
                                                                           <form:select path="paymentMode" class="form-control">
                                                                              <form:options items="${paymentModes}"/>
                                                                           </form:select>
                                                                           </br>
                                                                           <form:input id="packageDetailsId" class="form-control" type="hidden" name="packageDetailsId" path="packageDetailsId"/>
                                                                        </div>
                                                                        <div class="modal-footer">
                                                                           <form:button id="basicInfo" type="submit" class="btn btn-primary" onclick="this.disabled=true;this.value='Submitting...'; this.form.submit();">Pay</form:button>
                                                                           <script>
                                                                              //submit search, display loading message
                                                                              $('input[type = submit]').click(function(){
                                                                                 $.blockUI({ css: {
                                                                                       border: 'none',
                                                                                       padding: '15px',
                                                                                       backgroundColor: '#000',
                                                                                       opacity: .6
                                                                                    } });
                                                                              });
                                                                           </script>
                                                                           <form:button type="button" class="btn btn-default" data-dismiss="modal">Close</form:button>
                                                                        </div>
                                                                     </form:form>
                                                                  </div>
                                                               </div>
                                                            </div>
                                                            <!-- Edit Client assigned Modal -->
                                                            <div class="modal fade" id="editPackageModal" role="dialog">
                                                               <div class="modal-dialog">
                                                                  <!-- Modal content-->
                                                                  <div class="modal-content">
                                                                     <div class="modal-header">
                                                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                                        <h4 class="modal-title">Update Package</h4>
                                                                     </div>
                                                                     <form action="updateClientAssignedPackage" class="dropzone dropzone-custom needsclick add-professors" method="post">
                                                                        <div class="modal-body">
                                                                           <label for="psw"><span class="glyphicon"></span> Package Name</label>
                                                                           <input id="u_package" name="u_package" class="form-control" type="text" readonly/>

                                                                           <label for="psw"><span class="glyphicon"></span> Package StartDate</label>
                                                                           <input id="u_startdate" name="u_startdate" class="form-control" type="date" readonly/>
                                                                           <label for="psw"><span class="glyphicon"></span> Package EndDate</label>
                                                                           <input id="u_enddate" name="u_enddate" class="form-control" type="date" required="true"/>
                                                                           <label for="psw"><span class="glyphicon"></span> Fees </label>
                                                                           <input id="u_fees" name="u_fees" class="form-control" type="number" required="true"/>
                                                                           <input id="u_pkgId" class="form-control" type="hidden" name="u_pkgId" />
                                                                           <input id="u_clientid" class="form-control" type="hidden" value="<%out.print(((Client)request.getAttribute("clientObject")).getId());%>" name="u_clientid" />
                                                                           <input id="u_gender" class="form-control" type="hidden" name="u_gender" value="<%out.print(((Client)request.getAttribute("clientObject")).getGender());%>"/>
                                                                        </div>
                                                                        <div class="modal-footer">
                                                                           <button id="basicInfo" type="submit" class="btn btn-primary">Update Package</button>
                                                                           <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                                        </div>
                                                                     </form>
                                                                  </div>
                                                               </div>
                                                            </div>

                                                            <div class="modal fade" id="deletePackageModal" role="dialog">
                                                               <div class="modal-dialog modal-sm">
                                                                  <!-- Modal content-->
                                                                  <div class="modal-content">
                                                                     <div class="modal-header">
                                                                        <h4 class="modal-title">Security check</h4>
                                                                     </div>
                                                                     <form action="deleteClientAssignedPackage" method="post">
                                                                        <!-- Modal body -->
                                                                        <div class="modal-body" align="center">
                                                                           <label for="psw"><span class="glyphicon"></span> Enter Password</label>
                                                                           <input type="password" name="password" id="password" required>
                                                                           <input type="hidden" id="clientId" name="clientId" value="${clientObject.id}"/>
                                                                           <input type="hidden" id="gender" name="gender" value="${clientObject.gender}"/>
                                                                           <input type="hidden" id="pkg_id" name="pkg_id"/>
                                                                        </div>
                                                                        <!-- Modal footer -->
                                                                        <div class="modal-footer">
                                                                           <button type="submit" class="btn btn-success" >Delete Package</button>
                                                                           <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                                                                        </div>
                                                                     </form>

                                                                  </div>
                                                               </div>
                                                            </div>


                                                            <script>
                                                               $(function () {

                                                                  /* on click - pay */
                                                                  $(".btnedit").click(function () {
                                                                     //get data from table row
                                                                     var packageId = $(this).parent().prev().prev().prev().prev().prev().prev().prev().text();
                                                                     var packageName = $(this).parent().prev().prev().prev().prev().prev().prev().text();
                                                                     var paidPayment = $(this).parent().prev().text();
                                                                     var total = $(this).parent().prev().prev().text();
                                                                     var remaining = total - paidPayment;

                                                                     //assign to value for input box inside modal
                                                                     $("#txt_item").val(packageName);
                                                                     $("#txt_Price1").val(paidPayment);
                                                                     $("#feesPaid").val(remaining);
                                                                     $("#packageDetailsId").val(packageId);

                                                                     //open modal
                                                                     $("#payModel").modal();

                                                                     $("#btnsave").click(function () {
                                                                     })
                                                                  })

                                                                  /* on click - edit client assigned package */
                                                                  $(".btnEditClientPackage").click(function () {
                                                                     //get data from table row
                                                                     var packageId = $(this).parent().prev().prev().prev().prev().prev().prev().prev().text();
                                                                     var packageName = $(this).parent().prev().prev().prev().prev().prev().prev().text();
                                                                     var startdate = $(this).parent().prev().prev().prev().prev().prev().text();
                                                                     var enddate = $(this).parent().prev().prev().prev().prev().text();
                                                                     var fees = $(this).parent().prev().prev().text().replace(/\s+/g, '');
                                                                     var initial = startdate.split(/\//);
                                                                     var formattedDate = [initial[2],initial[1],initial[0]].join('-').replace(/\s+/g, '');

                                                                     var initialEndDate = enddate.split(/\//);
                                                                     var formattedEndDate = [ initialEndDate[2], initialEndDate[1], initialEndDate[0] ].join('-').replace(/\s+/g, '');

                                                                     //assign to value for input box inside modal
                                                                     $("#u_pkgId").val(packageId);
                                                                     $("#u_package").val(packageName);
                                                                     $("#u_fees").val(fees);
                                                                     $("#u_startdate").val(formattedDate);
                                                                     $("#u_enddate").val(formattedEndDate);
                                                                     /* $("#u_clientid").val(cliendId);
                                                                     $("#u_gender").val(gender); */

                                                                     //open modal
                                                                     $("#editPackageModal").modal();

                                                                     $("#btnsave").click(function () {
                                                                        //make ajax request to update data

                                                                        //and in ajax success callback function
                                                                        //hide modal
                                                                        //$("#myModal").modal("hide")
                                                                     })
                                                                  })

                                                                  $(".btnDeleteClientAssignedPackage").click(function () {
                                                                     //get data from table row
                                                                     var packageId = $(this).parent().prev().prev().prev().prev().prev().prev().prev().text();
                                                                     console.log(packageId);
                                                                     //assign to value for input box inside modal
                                                                     $("#pkg_id").val(packageId);

                                                                     //open modal
                                                                     $("#deletePackageModal").modal();

                                                                     $("#btnsave").click(function () {

                                                                     })
                                                                  })

                                                               })
                                                            </script>
                                                         </tr>
                                                      </c:forEach>
                                                      </tbody>
                                                   </table>
                                                </div>
                                             </div>
                                          </div>
                                       </div>
                                    </div>
                                       <%--workout box ends--%>

                                 </div>
                              </div>
                           </div>
                        </c:if>
                        <!-- WORKOUT SCHEDULE TAB 5 ENDS-->

                        <!-- DIET TAB 6 STARTS-->
                        <c:if test="${isEnabledDietModule}">
                           <div class="product-tab-list tab-pane fade" id="diet_plan">
                              <!-- Add Diet Plan Modal starts -->
                              <div class="modal fade" id="addDietPlanModal" role="dialog">
                                 <div class="modal-dialog" style="width:1000px;">
                                    <!-- Modal content-->
                                    <div class="modal-content">
                                       <div class="modal-header" style="padding:20px 30px;">
                                          <button type="button" class="close" data-dismiss="modal">&times;</button>
                                          <h4>Add Diet Plan Modal</h4>
                                       </div>
                                       <form action="submitDietPlanData" method="post">
                                          </br></br>
                                          <div align="center">
                                             <input type="text" id="name" name="name" placeholder="Enter Diet Plan Name"/>
                                          </div>
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
                              <script>
                                 $(document).ready(function(){
                                    $("#addDietPlanBtn").click(function(){
                                       $("#addDietPlanModal").modal();
                                    });
                                 });
                              </script>
                              <!-- Add Diet Plan Modal ends -->
                              <!-- Add Template Diet Plan Modal starts -->
                              <div class="modal fade" id="addTemplateDietPlanModal" role="dialog">
                                 <div class="modal-dialog" style="width:1000px;">
                                    <!-- Modal content-->
                                    <div class="modal-content">
                                       <div class="modal-header" style="padding:20px 30px;">
                                          <button type="button" class="close" data-dismiss="modal">&times;</button>
                                          <h4>Add Template Diet Plan Modal</h4>
                                       </div>
                                       <form action="submitTemplateDietPlanData" method="post">
                                          <input type="hidden" id="clientId" name="clientId" value="${clientObject.id}"/>
                                          <input type="hidden" id="gender" name="gender" value="${clientObject.gender}"/>
                                          <div class="modal-body" style="padding:40px 50px;">
                                             <div align="center">
                                                <label style="font-size:18px;" for="selectedTemplateId">Choose Template:</label></br>
                                                <select style="font-size:18px;" name="selectedTemplateId" id="selectedTemplateId" onchange="setDisplayValuesFromTemplate('selectedTemplateId')">
                                                   <option style="font-size:18px;" value="-1">Select</option>
                                                   <c:forEach items="${defaultDietPlanTemplatesList}" var="list" varStatus="status">
                                                      <option style="font-size:18px;" value="${list.id}">${list.name}</option>
                                                   </c:forEach>
                                                </select>
                                             </div>
                                             </br>
                                             <div align="center">
                                                <label style="font-size:18px;" for="dietname">Template name</label></br>
                                                <input style="text-align: center" type="text" id="dietname" name="name"/>
                                             </div>
                                             </br>
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
                                             <button type="submit" class="btn btn-success" >Assign</button>
                                             <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                                          </div>
                                       </form>
                                    </div>
                                 </div>
                              </div>
                              <script>
                                 $(document).ready(function(){
                                    $("#addTemplateDietPlanBtn").click(function(){
                                       $("#addTemplateDietPlanModal").modal();
                                    });
                                 });

                                 function setDisplayValuesFromTemplate (e) {
                                    var e = document.getElementById(e);
                                    /*if (e.value == '1') {
                                    }*/
                                    console.log(e.value);

                                    <c:forEach items="${defaultDietPlanTemplatesList}" var="obj" varStatus="status">
                                    console.log(${obj.id});
                                    if(e.value == ${obj.id}){
                                       document.getElementById('dietname').value = '${obj.name}';
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


                                    }
                                    </c:forEach>
                                 }
                              </script>
                              <!-- Add Template Diet Plan Modal ends -->
                              <div class="row">
                                 <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    &nbsp;&nbsp;&nbsp;&nbsp;
                                    <button style="text-align:center;background-color:#000000" type="button" class="btn btn-info btn-lg" id="addDietPlanBtn">New Plan</button>
                                    <button style="text-align:center;background-color:#000000" type="button" class="btn btn-info btn-lg" id="addTemplateDietPlanBtn">Plan Templates</button>
                                    <a href="<c:url value='resetActiveDietPlan?clientId=${clientObject.id}&gender=${clientObject.gender}'/>">
                                       <button style="text-align:center;" type="button" class="btn btn-danger btn-xs" id="addTemplateDietPlanBtn">Reset Active Plan</button>
                                    </a>

                                    <div class="review-content-section">
                                       <a href="<c:url value='viewActiveDietPlanByClientId?clientId=${clientObject.id}&gender=${clientObject.gender}'/>">
                                          <input class="btn btn-primary" type="button" value="&nbsp;&nbsp;View Active Plan&nbsp;&nbsp;" />
                                       </a>
                                       <c:forEach items="${oldDietPlanTemplatesList}" var="obj" varStatus="status">
                                          <a href="<c:url value='viewOldDietPlanTemplate?templateId=${obj.id}&clientId=${clientObject.id}&gender=${clientObject.gender}'/>">
                                             <input class="btn btn-secondary" type="button" value="&nbsp;&nbsp;Diet - ${obj.name}&nbsp;&nbsp;" />
                                          </a>
                                       </c:forEach>
                                       </br></br>
                                       <c:forEach items="${DietPlanObjectDetailsList}" var="obj" varStatus="status">
                                          <a href="<c:url value='viewDietPlanObjectDetails?dietPlanOjectId=${obj.id}&templateId=${obj.dietPlanTemplate.id}&clientId=${clientObject.id}&gender=${clientObject.gender}'/>">
                                             <c:if test="${obj.dietCompletionPercentageColor == 'red'}"><input class="btn btn-danger" type="button" value="&nbsp;&nbsp;${obj.dietDate}&nbsp;&nbsp;" /></c:if>
                                             <c:if test="${obj.dietCompletionPercentageColor == 'yellow'}"><input class="btn btn-warning" type="button" value="&nbsp;&nbsp;${obj.dietDate}&nbsp;&nbsp;" /></c:if>
                                             <c:if test="${obj.dietCompletionPercentageColor == 'green'}"><input class="btn btn-success" type="button" value="&nbsp;&nbsp;${obj.dietDate}&nbsp;&nbsp;" /></c:if>
                                          </a>
                                       </c:forEach>
                                       <c:if test="${viewDetailedDietObjectClicked == 1}">
                                          <b>Selected Date : ${selectedDietPlanObject.dietDate}</b>
                                       </c:if>
                                       </br></br>
                                       <table style="border-spacing: 20px">
                                          <thead>
                                          <tr>
                                             <c:if test="${selectedDietObjectTemplate != null}">
                                                <td style="font-size:18px;font-weight: bold;border: 1px solid black;" align="center">&nbsp;&nbsp;Time&nbsp;&nbsp;</td>
                                                <td style="font-size:18px;font-weight: bold;border: 1px solid black;" align="center">&nbsp;&nbsp;Activity&nbsp;&nbsp;</td>
                                                <td></td>
                                             </c:if>
                                             <c:if test="${selectedDietPlanObject != null}">
                                                <td style="font-size:18px;font-weight: bold;border: 1px solid black;" align="center">&nbsp;&nbsp;Time&nbsp;&nbsp;</td>
                                                <td style="font-size:18px;font-weight: bold;border: 1px solid black;" align="center">&nbsp;&nbsp;Activity&nbsp;&nbsp;</td>
                                                <td style="font-size:18px;font-weight: bold;border: 1px solid black;" align="center">&nbsp;&nbsp;Completion status&nbsp;&nbsp;</td>
                                             </c:if>
                                          </tr>
                                          </thead>
                                          <tbody>
                                          <c:if test="${selectedDietObjectTemplate != null}">
                                             <tr>
                                                <td style="font-size:16px;text-align:center" >
                                                      ${selectedDietObjectTemplate.time_1}
                                                </td>
                                                <td style="font-size:16px;text-align:center;width:70%;" >
                                                      ${selectedDietObjectTemplate.activity_1}
                                                </td>
                                             </tr>
                                             <tr>
                                                <td style="font-size:16px;text-align:center" >
                                                      ${selectedDietObjectTemplate.time_2}
                                                </td>
                                                <td style="font-size:16px;text-align:center;width:70%;" >
                                                      ${selectedDietObjectTemplate.activity_2}
                                                </td>
                                             </tr>
                                             <tr>
                                                <td style="font-size:16px;text-align:center" >
                                                      ${selectedDietObjectTemplate.time_3}
                                                </td>
                                                <td style="font-size:16px;text-align:center;width:70%;" >
                                                      ${selectedDietObjectTemplate.activity_3}
                                                </td>
                                             </tr>
                                             <tr>
                                                <td style="font-size:16px;text-align:center" >
                                                      ${selectedDietObjectTemplate.time_4}
                                                </td>
                                                <td style="font-size:16px;text-align:center;width:70%;" >
                                                      ${selectedDietObjectTemplate.activity_4}
                                                </td>
                                             </tr>
                                             <tr>
                                                <td style="font-size:16px;text-align:center" >
                                                      ${selectedDietObjectTemplate.time_5}
                                                </td>
                                                <td style="font-size:16px;text-align:center;width:70%;" >
                                                      ${selectedDietObjectTemplate.activity_5}
                                                </td>
                                             </tr>
                                             <tr>
                                                <td style="font-size:16px;text-align:center" >
                                                      ${selectedDietObjectTemplate.time_6}
                                                </td>
                                                <td style="font-size:16px;text-align:center;width:70%;" >
                                                      ${selectedDietObjectTemplate.activity_6}
                                                </td>
                                             </tr>
                                             <tr>
                                                <td style="font-size:16px;text-align:center" >
                                                      ${selectedDietObjectTemplate.time_7}
                                                </td>
                                                <td style="font-size:16px;text-align:center;width:70%;" >
                                                      ${selectedDietObjectTemplate.activity_7}
                                                </td>
                                             </tr>
                                             <tr>
                                                <td style="font-size:16px;text-align:center" >
                                                      ${selectedDietObjectTemplate.time_8}
                                                </td>
                                                <td style="font-size:16px;text-align:center;width:70%;" >
                                                      ${selectedDietObjectTemplate.activity_8}
                                                </td>
                                             </tr>
                                             <tr>
                                                <td style="font-size:16px;text-align:center" >
                                                      ${selectedDietObjectTemplate.time_9}
                                                </td>
                                                <td style="font-size:16px;text-align:center;width:70%;" >
                                                      ${selectedDietObjectTemplate.activity_9}
                                                </td>
                                             </tr>
                                             <tr>
                                                <td style="font-size:16px;text-align:center" >
                                                      ${selectedDietObjectTemplate.time_10}
                                                </td>
                                                <td style="font-size:16px;text-align:center;width:70%;" >
                                                      ${selectedDietObjectTemplate.activity_10}
                                                </td>
                                             </tr>
                                             <tr>
                                                <td style="font-size:16px;text-align:center" >
                                                      ${selectedDietObjectTemplate.time_11}
                                                </td>
                                                <td style="font-size:16px;text-align:center;width:70%;" >
                                                      ${selectedDietObjectTemplate.activity_11}
                                                </td>
                                             </tr>
                                             <tr>
                                                <td style="font-size:16px;text-align:center" >
                                                      ${selectedDietObjectTemplate.time_12}
                                                </td>
                                                <td style="font-size:16px;text-align:center;width:70%;" >
                                                      ${selectedDietObjectTemplate.activity_12}
                                                </td>
                                             </tr>
                                             <tr>
                                                <td style="font-size:16px;text-align:center" >
                                                      ${selectedDietObjectTemplate.time_13}
                                                </td>
                                                <td style="font-size:16px;text-align:center;width:70%;" >
                                                      ${selectedDietObjectTemplate.activity_13}
                                                </td>
                                             </tr>
                                             <tr>
                                                <td style="font-size:16px;text-align:center" >
                                                      ${selectedDietObjectTemplate.time_14}
                                                </td>
                                                <td style="font-size:16px;text-align:center;width:70%;" >
                                                      ${selectedDietObjectTemplate.activity_14}
                                                </td>
                                             </tr>
                                             <tr>
                                                <td style="font-size:16px;text-align:center" >
                                                      ${selectedDietObjectTemplate.time_15}
                                                </td>
                                                <td style="font-size:16px;text-align:center;width:70%;" >
                                                      ${selectedDietObjectTemplate.activity_15}
                                                </td>
                                             </tr>
                                             <tr>
                                                <td style="font-size:16px;text-align:center" >
                                                      ${selectedDietObjectTemplate.time_16}
                                                </td>
                                                <td style="font-size:16px;text-align:center;width:70%;" >
                                                      ${selectedDietObjectTemplate.activity_16}
                                                </td>
                                             </tr>
                                             <tr>
                                                <td style="font-size:16px;text-align:center" >
                                                      ${selectedDietObjectTemplate.time_17}
                                                </td>
                                                <td style="font-size:16px;text-align:center;width:70%;" >
                                                      ${selectedDietObjectTemplate.activity_17}
                                                </td>
                                             </tr>
                                             <tr>
                                                <td></td>
                                                <td></td>
                                                <td>
                                                   <c:if test="${clientObject.adp != selectedDietObjectTemplate.id}">
                                                      <a href="<c:url value='activateDietPlanTemplate?templateId=${selectedDietObjectTemplate.id}&clientId=${clientObject.id}&gender=${clientObject.gender}'/>">
                                                         <input class="btn btn-success" type="button" value="&nbsp;&nbsp;Activate Plan&nbsp;&nbsp;" />
                                                      </a>
                                                   </c:if>
                                                </td>
                                             </tr>

                                          </c:if>
                                          <c:if test="${selectedDietPlanObject != null}">
                                             <tr>
                                                <td style="font-size:16px;text-align:center" >
                                                      ${selectedDietPlanObject.dietPlanTemplate.time_1}
                                                </td>
                                                <td style="font-size:16px;text-align:center;width:70%;" >
                                                      ${selectedDietPlanObject.dietPlanTemplate.activity_1}
                                                </td>
                                                <td style="font-size:16px;text-align:center" >
                                                   <c:if test="${selectedDietPlanObject.clientCompletionStatus_timeActivity_1 == 'yes'}"><img style="max-width: 18px" src="https://tavrostechinfo.com/PROGYM/img/green_tck.png"></c:if>
                                                   <c:if test="${selectedDietPlanObject.clientCompletionStatus_timeActivity_1 == 'no'}"><img style="max-width: 18px" src="https://tavrostechinfo.com/PROGYM/img/cross_mark.jpg"></c:if>
                                                </td>
                                             </tr>
                                             <tr>
                                                <td style="font-size:16px;text-align:center" >
                                                      ${selectedDietPlanObject.dietPlanTemplate.time_2}
                                                </td>
                                                <td style="font-size:16px;text-align:center;width:70%;" >
                                                      ${selectedDietPlanObject.dietPlanTemplate.activity_2}
                                                </td>
                                                <td style="font-size:16px;text-align:center" >
                                                   <c:if test="${selectedDietPlanObject.clientCompletionStatus_timeActivity_2 == 'yes'}"><img style="max-width: 18px" src="https://tavrostechinfo.com/PROGYM/img/green_tck.png"></c:if>
                                                   <c:if test="${selectedDietPlanObject.clientCompletionStatus_timeActivity_2 == 'no'}"><img style="max-width: 18px" src="https://tavrostechinfo.com/PROGYM/img/cross_mark.jpg"></c:if>
                                                </td>
                                             </tr>
                                             <tr>
                                                <td style="font-size:16px;text-align:center" >
                                                      ${selectedDietPlanObject.dietPlanTemplate.time_3}
                                                </td>
                                                <td style="font-size:16px;text-align:center;width:70%;" >
                                                      ${selectedDietPlanObject.dietPlanTemplate.activity_3}
                                                </td>
                                                <td style="font-size:16px;text-align:center" >
                                                   <c:if test="${selectedDietPlanObject.clientCompletionStatus_timeActivity_3 == 'yes'}"><img style="max-width: 18px" src="https://tavrostechinfo.com/PROGYM/img/green_tck.png"></c:if>
                                                   <c:if test="${selectedDietPlanObject.clientCompletionStatus_timeActivity_3 == 'no'}"><img style="max-width: 18px" src="https://tavrostechinfo.com/PROGYM/img/cross_mark.jpg"></c:if>
                                                </td>
                                             </tr>
                                             <tr>
                                                <td style="font-size:16px;text-align:center" >
                                                      ${selectedDietPlanObject.dietPlanTemplate.time_4}
                                                </td>
                                                <td style="font-size:16px;text-align:center;width:70%;" >
                                                      ${selectedDietPlanObject.dietPlanTemplate.activity_4}
                                                </td>
                                                <td style="font-size:16px;text-align:center" >
                                                   <c:if test="${selectedDietPlanObject.clientCompletionStatus_timeActivity_4 == 'yes'}"><img style="max-width: 18px" src="https://tavrostechinfo.com/PROGYM/img/green_tck.png"></c:if>
                                                   <c:if test="${selectedDietPlanObject.clientCompletionStatus_timeActivity_4 == 'no'}"><img style="max-width: 18px" src="https://tavrostechinfo.com/PROGYM/img/cross_mark.jpg"></c:if>
                                                </td>
                                             </tr>
                                             <tr>
                                                <td style="font-size:16px;text-align:center" >
                                                      ${selectedDietPlanObject.dietPlanTemplate.time_5}
                                                </td>
                                                <td style="font-size:16px;text-align:center;width:70%;" >
                                                      ${selectedDietPlanObject.dietPlanTemplate.activity_5}
                                                </td>
                                                <td style="font-size:16px;text-align:center" >
                                                   <c:if test="${selectedDietPlanObject.clientCompletionStatus_timeActivity_5 == 'yes'}"><img style="max-width: 18px" src="https://tavrostechinfo.com/PROGYM/img/green_tck.png"></c:if>
                                                   <c:if test="${selectedDietPlanObject.clientCompletionStatus_timeActivity_5 == 'no'}"><img style="max-width: 18px" src="https://tavrostechinfo.com/PROGYM/img/cross_mark.jpg"></c:if>
                                                </td>
                                             </tr>
                                             <tr>
                                                <td style="font-size:16px;text-align:center" >
                                                      ${selectedDietPlanObject.dietPlanTemplate.time_6}
                                                </td>
                                                <td style="font-size:16px;text-align:center;width:70%;" >
                                                      ${selectedDietPlanObject.dietPlanTemplate.activity_6}
                                                </td>
                                                <td style="font-size:16px;text-align:center" >
                                                   <c:if test="${selectedDietPlanObject.clientCompletionStatus_timeActivity_6 == 'yes'}"><img style="max-width: 18px" src="https://tavrostechinfo.com/PROGYM/img/green_tck.png"></c:if>
                                                   <c:if test="${selectedDietPlanObject.clientCompletionStatus_timeActivity_6 == 'no'}"><img style="max-width: 18px" src="https://tavrostechinfo.com/PROGYM/img/cross_mark.jpg"></c:if>
                                                </td>
                                             </tr>
                                             <tr>
                                                <td style="font-size:16px;text-align:center" >
                                                      ${selectedDietPlanObject.dietPlanTemplate.time_7}
                                                </td>
                                                <td style="font-size:16px;text-align:center;width:70%;" >
                                                      ${selectedDietPlanObject.dietPlanTemplate.activity_7}
                                                </td>
                                                <td style="font-size:16px;text-align:center" >
                                                   <c:if test="${selectedDietPlanObject.clientCompletionStatus_timeActivity_7 == 'yes'}"><img style="max-width: 18px" src="https://tavrostechinfo.com/PROGYM/img/green_tck.png"></c:if>
                                                   <c:if test="${selectedDietPlanObject.clientCompletionStatus_timeActivity_7 == 'no'}"><img style="max-width: 18px" src="https://tavrostechinfo.com/PROGYM/img/cross_mark.jpg"></c:if>
                                                </td>
                                             </tr>
                                             <tr>
                                                <td style="font-size:16px;text-align:center" >
                                                      ${selectedDietPlanObject.dietPlanTemplate.time_8}
                                                </td>
                                                <td style="font-size:16px;text-align:center;width:70%;" >
                                                      ${selectedDietPlanObject.dietPlanTemplate.activity_8}
                                                </td>
                                                <td style="font-size:16px;text-align:center" >
                                                   <c:if test="${selectedDietPlanObject.clientCompletionStatus_timeActivity_8 == 'yes'}"><img style="max-width: 18px" src="https://tavrostechinfo.com/PROGYM/img/green_tck.png"></c:if>
                                                   <c:if test="${selectedDietPlanObject.clientCompletionStatus_timeActivity_8 == 'no'}"><img style="max-width: 18px" src="https://tavrostechinfo.com/PROGYM/img/cross_mark.jpg"></c:if>
                                                </td>
                                             </tr>
                                             <tr>
                                                <td style="font-size:16px;text-align:center" >
                                                      ${selectedDietPlanObject.dietPlanTemplate.time_9}
                                                </td>
                                                <td style="font-size:16px;text-align:center;width:70%;" >
                                                      ${selectedDietPlanObject.dietPlanTemplate.activity_9}
                                                </td>
                                                <td style="font-size:16px;text-align:center" >
                                                   <c:if test="${selectedDietPlanObject.clientCompletionStatus_timeActivity_9 == 'yes'}"><img style="max-width: 18px" src="https://tavrostechinfo.com/PROGYM/img/green_tck.png"></c:if>
                                                   <c:if test="${selectedDietPlanObject.clientCompletionStatus_timeActivity_9 == 'no'}"><img style="max-width: 18px" src="https://tavrostechinfo.com/PROGYM/img/cross_mark.jpg"></c:if>
                                                </td>
                                             </tr>
                                             <tr>
                                                <td style="font-size:16px;text-align:center" >
                                                      ${selectedDietPlanObject.dietPlanTemplate.time_10}
                                                </td>
                                                <td style="font-size:16px;text-align:center;width:70%;" >
                                                      ${selectedDietPlanObject.dietPlanTemplate.activity_10}
                                                </td>
                                                <td style="font-size:16px;text-align:center" >
                                                   <c:if test="${selectedDietPlanObject.clientCompletionStatus_timeActivity_10 == 'yes'}"><img style="max-width: 18px" src="https://tavrostechinfo.com/PROGYM/img/green_tck.png"></c:if>
                                                   <c:if test="${selectedDietPlanObject.clientCompletionStatus_timeActivity_10 == 'no'}"><img style="max-width: 18px" src="https://tavrostechinfo.com/PROGYM/img/cross_mark.jpg"></c:if>
                                                </td>
                                             </tr>
                                             <tr>
                                                <td style="font-size:16px;text-align:center" >
                                                      ${selectedDietPlanObject.dietPlanTemplate.time_11}
                                                </td>
                                                <td style="font-size:16px;text-align:center;width:70%;" >
                                                      ${selectedDietPlanObject.dietPlanTemplate.activity_11}
                                                </td>
                                                <td style="font-size:16px;text-align:center" >
                                                   <c:if test="${selectedDietPlanObject.clientCompletionStatus_timeActivity_11 == 'yes'}"><img style="max-width: 18px" src="https://tavrostechinfo.com/PROGYM/img/green_tck.png"></c:if>
                                                   <c:if test="${selectedDietPlanObject.clientCompletionStatus_timeActivity_11 == 'no'}"><img style="max-width: 18px" src="https://tavrostechinfo.com/PROGYM/img/cross_mark.jpg"></c:if>
                                                </td>
                                             </tr>
                                             <tr>
                                                <td style="font-size:16px;text-align:center" >
                                                      ${selectedDietPlanObject.dietPlanTemplate.time_12}
                                                </td>
                                                <td style="font-size:16px;text-align:center;width:70%;" >
                                                      ${selectedDietPlanObject.dietPlanTemplate.activity_12}
                                                </td>
                                                <td style="font-size:16px;text-align:center" >
                                                   <c:if test="${selectedDietPlanObject.clientCompletionStatus_timeActivity_12 == 'yes'}"><img style="max-width: 18px" src="https://tavrostechinfo.com/PROGYM/img/green_tck.png"></c:if>
                                                   <c:if test="${selectedDietPlanObject.clientCompletionStatus_timeActivity_12 == 'no'}"><img style="max-width: 18px" src="https://tavrostechinfo.com/PROGYM/img/cross_mark.jpg"></c:if>
                                                </td>
                                             </tr>
                                             <tr>
                                                <td style="font-size:16px;text-align:center" >
                                                      ${selectedDietPlanObject.dietPlanTemplate.time_13}
                                                </td>
                                                <td style="font-size:16px;text-align:center;width:70%;" >
                                                      ${selectedDietPlanObject.dietPlanTemplate.activity_13}
                                                </td>
                                                <td style="font-size:16px;text-align:center" >
                                                   <c:if test="${selectedDietPlanObject.clientCompletionStatus_timeActivity_13 == 'yes'}"><img style="max-width: 18px" src="https://tavrostechinfo.com/PROGYM/img/green_tck.png"></c:if>
                                                   <c:if test="${selectedDietPlanObject.clientCompletionStatus_timeActivity_13 == 'no'}"><img style="max-width: 18px" src="https://tavrostechinfo.com/PROGYM/img/cross_mark.jpg"></c:if>
                                                </td>
                                             </tr>
                                             <tr>
                                                <td style="font-size:16px;text-align:center" >
                                                      ${selectedDietPlanObject.dietPlanTemplate.time_14}
                                                </td>
                                                <td style="font-size:16px;text-align:center;width:70%;" >
                                                      ${selectedDietPlanObject.dietPlanTemplate.activity_14}
                                                </td>
                                                <td style="font-size:16px;text-align:center" >
                                                   <c:if test="${selectedDietPlanObject.clientCompletionStatus_timeActivity_14 == 'yes'}"><img style="max-width: 18px" src="https://tavrostechinfo.com/PROGYM/img/green_tck.png"></c:if>
                                                   <c:if test="${selectedDietPlanObject.clientCompletionStatus_timeActivity_14 == 'no'}"><img style="max-width: 18px" src="https://tavrostechinfo.com/PROGYM/img/cross_mark.jpg"></c:if>
                                                </td>
                                             </tr>
                                             <tr>
                                                <td style="font-size:16px;text-align:center" >
                                                      ${selectedDietPlanObject.dietPlanTemplate.time_15}
                                                </td>
                                                <td style="font-size:16px;text-align:center;width:70%;" >
                                                      ${selectedDietPlanObject.dietPlanTemplate.activity_15}
                                                </td>
                                                <td style="font-size:16px;text-align:center" >
                                                   <c:if test="${selectedDietPlanObject.clientCompletionStatus_timeActivity_15 == 'yes'}"><img style="max-width: 18px" src="https://tavrostechinfo.com/PROGYM/img/green_tck.png"></c:if>
                                                   <c:if test="${selectedDietPlanObject.clientCompletionStatus_timeActivity_15 == 'no'}"><img style="max-width: 18px" src="https://tavrostechinfo.com/PROGYM/img/cross_mark.jpg"></c:if>
                                                </td>
                                             </tr>
                                             <tr>
                                                <td style="font-size:16px;text-align:center" >
                                                      ${selectedDietPlanObject.dietPlanTemplate.time_16}
                                                </td>
                                                <td style="font-size:16px;text-align:center;width:70%;" >
                                                      ${selectedDietPlanObject.dietPlanTemplate.activity_16}
                                                </td>
                                                <td style="font-size:16px;text-align:center" >
                                                   <c:if test="${selectedDietPlanObject.clientCompletionStatus_timeActivity_16 == 'yes'}"><img style="max-width: 18px" src="https://tavrostechinfo.com/PROGYM/img/green_tck.png"></c:if>
                                                   <c:if test="${selectedDietPlanObject.clientCompletionStatus_timeActivity_16 == 'no'}"><img style="max-width: 18px" src="https://tavrostechinfo.com/PROGYM/img/cross_mark.jpg"></c:if>
                                                </td>
                                             </tr>
                                             <tr>
                                                <td style="font-size:16px;text-align:center" >
                                                      ${selectedDietPlanObject.dietPlanTemplate.time_17}
                                                </td>
                                                <td style="font-size:16px;text-align:center;width:70%;" >
                                                      ${selectedDietPlanObject.dietPlanTemplate.activity_17}
                                                </td>
                                                <td style="font-size:16px;text-align:center" >
                                                   <c:if test="${selectedDietPlanObject.clientCompletionStatus_timeActivity_17 == 'yes'}"><img style="max-width: 18px" src="https://tavrostechinfo.com/PROGYM/img/green_tck.png"></c:if>
                                                   <c:if test="${selectedDietPlanObject.clientCompletionStatus_timeActivity_17 == 'no'}"><img style="max-width: 18px" src="https://tavrostechinfo.com/PROGYM/img/cross_mark.jpg"></c:if>
                                                </td>
                                             </tr>
                                          </c:if>
                                          </tbody>
                                       </table>

                                    </div>

                                 </div>
                              </div>
                           </div>
                        </c:if>
                        <!-- DIET TAB 6 ENDS-->
                     </div>
                     <%--ALL MODULE TABS CONTENT ENDS--%>

                     </div>
                  </div>
               </div>
            </div>
         </div>
      </div>
      <jsp:include page="copyright.jsp" />
      </div>
      <jsp:include page="bottom_script.jsp" />
   </div>
   </body>

</html>