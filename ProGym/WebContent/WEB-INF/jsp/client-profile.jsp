<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html class="no-js" lang="en">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
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
</head>

<body>
   
       <!-- Start Left menu area -->
   <jsp:include page="topPage.jsp" />
   
        <!-- Single pro tab review Start-->
        <div class="single-pro-review-area mt-t-30 mg-b-15">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                        <div class="profile-info-inner">
                            <div class="profile-img">
                                <img src="img/profile/1.jpg" alt="" />
                            </div>
                            <div class="profile-details-hr">
                                <div class="row">
                                    <div class="col-lg-6 col-md-12 col-sm-12 col-xs-6">
                                        <div class="address-hr">
                                            <p><b>Name</b><br /> Fly Zend</p>
                                        </div>
                                    </div>
                                    <div class="col-lg-6 col-md-12 col-sm-12 col-xs-6">
                                        <div class="address-hr tb-sm-res-d-n dps-tb-ntn">
                                            <p><b>Department</b><br /> CSE</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-lg-6 col-md-12 col-sm-12 col-xs-6">
                                        <div class="address-hr">
                                            <p><b>Email ID</b><br /> fly@gmail.com</p>
                                        </div>
                                    </div>
                                    <div class="col-lg-6 col-md-12 col-sm-12 col-xs-6">
                                        <div class="address-hr tb-sm-res-d-n dps-tb-ntn">
                                            <p><b>Phone</b><br /> +01962067309</p>
                                        </div>
                                    </div>
                                </div>
                               
                                
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-8 col-md-8 col-sm-8 col-xs-12">
                        <div class="product-payment-inner-st res-mg-t-30 analysis-progrebar-ctn">
                            <ul id="myTabedu1" class="tab-review-design">
                                <li class="active"><a href="#description">History</a></li>
                                <li><a href="#reviews">Update Profile</a></li>
                                <li><a href="#INFORMATION">Update Packages</a></li>
                            </ul>
                            
                            <div id="myTabContent" class="tab-content custom-product-edit st-prf-pro">
                                <div class="product-tab-list tab-pane fade active in" id="description">
                                    <div class="row">
                                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                            <div class="review-content-section">            
                                             <button type="button" class="btn btn-info btn-lg" id="myBtn">Add Package</button>

                                             		<!-- Modal -->
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
                                                                <div class="form-group">
													              <label for="psw"><span class="glyphicon"></span> Package Start Date</label>
													              <form:input path="startDate" type="date" class="form-control" id="psw" />
													            </div>	
													            <form:input type="hidden" path="clientId" value="${clientObject.id}"/>												            
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
								                                                <th>No</th>
								                                                <th>Package</th>
								                                                <th>Start Date</th>
								                                                <th>End Date</th>
								                                                <th>Status</th>								                                                
								                                                <th>Collection</th>
								                                                <th>Payment Date</th>
								                                                <th>Action</th>
								                                            </tr>
								                                        </thead>
								                                        <tbody>
								                                            <tr>
								                                                <td>1</td>
								                                                <td>GYM+Cardio</td>
								                                                <td>02/01/2020</td>
								                                                <td>02/03/2020</td>
								                                                <td>Paid</td>
								                                                <td>Rs.500</td>
								                                                <td>03/04/2020</td>  	
								                                                <td><button type="submit" class="btn btn-success"><span class="glyphicon"></span> Details </button></td>							                                                
								                                            </tr>
								                                           <tr>
								                                                <td>1</td>
								                                                <td>GYM+Cardio</td>
								                                                <td>02/01/2020</td>
								                                                <td>02/03/2020</td>
								                                                <td>Paid</td>
								                                                <td>Rs.500</td>
								                                                <td>03/04/2020</td>
								                                                <td><button type="submit" class="btn btn-success"><span class="glyphicon"></span> Details </button></td>
								                                            </tr>
								                                            <tr>
								                                                <td>1</td>
								                                                <td>GYM+Cardio</td>
								                                                <td>02/01/2020</td>
								                                                <td>02/03/2020</td>
								                                                <td>Paid</td>
								                                                <td>Rs.500</td>
								                                                <td>03/04/2020</td>
								                                                <td><button type="submit" class="btn btn-success"><span class="glyphicon"></span> Details </button></td>
								                                            </tr>
								                                            <tr>
								                                                <td>1</td>
								                                                <td>GYM+Cardio</td>
								                                                <td>02/01/2020</td>
								                                                <td>02/03/2020</td>
								                                                <td>Paid</td>
								                                                <td>Rs.500</td>
								                                                <td>03/04/2020</td>
								                                                <td><button type="submit" class="btn btn-success"><span class="glyphicon"></span> Details </button></td>
								                                            </tr>
								                                            <tr>
								                                                <td>1</td>
								                                                <td>GYM+Cardio</td>
								                                                <td>02/01/2020</td>
								                                                <td>02/03/2020</td>
								                                                <td>Paid</td>
								                                                <td>Rs.500</td>
								                                                <td>03/04/2020</td>
								                                                <td><button type="submit" class="btn btn-success"><span class="glyphicon"></span> Details </button></td>
								                                            </tr>
								                                            <tr>
								                                                <td>1</td>
								                                                <td>GYM+Cardio</td>
								                                                <td>02/01/2020</td>
								                                                <td>02/03/2020</td>
								                                                <td>Paid</td>
								                                                <td>Rs.500</td>
								                                                <td>03/04/2020</td>
								                                                <td><button type="submit" class="btn btn-success"><span class="glyphicon"></span> Details </button></td>
								                                            </tr>
								                                            
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
                                <div class="product-tab-list tab-pane fade" id="reviews">
                                    <div class="row">
                                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                            <div class="review-content-section">
                                                <div class="chat-discussion" style="height: auto">
                                                    <div class="chat-message">
														<div class="profile-hdtc">
															  <img class="message-avatar" src="img/contact/1.jpg" alt="">
														</div>
                                                        <div class="message">
                                                            <a class="message-author" href="#"> Michael Smith </a>
                                                            <span class="message-date"> Mon Jan 26 2015 - 18:39:23 </span>
                                                            <span class="message-content">Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.
															</span>
                                                            <div class="m-t-md mg-t-10">
                                                                <a class="btn btn-xs btn-default"><i class="fa fa-thumbs-up"></i> Like </a>
                                                                <a class="btn btn-xs btn-success"><i class="fa fa-heart"></i> Love</a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="chat-message">
														<div class="profile-hdtc">
															  <img class="message-avatar" src="img/contact/2.jpg" alt="">
														</div>
                                                        <div class="message">
                                                            <a class="message-author" href="#"> Karl Jordan </a>
                                                            <span class="message-date">  Fri Jan 25 2015 - 11:12:36 </span>
                                                            <span class="message-content">
																	Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover.
																	</span>
                                                            <div class="m-t-md mg-t-10">
                                                                <a class="btn btn-xs btn-default"><i class="fa fa-thumbs-up"></i> Like </a>
                                                                <a class="btn btn-xs btn-default"><i class="fa fa-heart"></i> Love</a>
                                                                <a class="btn btn-xs btn-primary"><i class="fa fa-pencil"></i> Message</a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="chat-message">
														<div class="profile-hdtc">
															  <img class="message-avatar" src="img/contact/3.jpg" alt="">
														</div>
                                                        <div class="message">
                                                            <a class="message-author" href="#"> Michael Smith </a>
                                                            <span class="message-date">  Fri Jan 25 2015 - 11:12:36 </span>
                                                            <span class="message-content">
																	There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration.
																	</span>
                                                        </div>
                                                    </div>
                                                    <div class="chat-message">
														<div class="profile-hdtc">
															  <img class="message-avatar" src="img/contact/4.jpg" alt="">
														</div>
                                                        <div class="message">
                                                            <a class="message-author" href="#"> Alice Jordan </a>
                                                            <span class="message-date">  Fri Jan 25 2015 - 11:12:36 </span>
                                                            <span class="message-content">
																	All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet.
																		It uses a dictionary of over 200 Latin words.
																	</span>
                                                            <div class="m-t-md mg-t-10">
                                                                <a class="btn btn-xs btn-default"><i class="fa fa-thumbs-up"></i> Like </a>
                                                                <a class="btn btn-xs btn-warning"><i class="fa fa-eye"></i> Nudge</a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="chat-message">
														<div class="profile-hdtc">
															  <img class="message-avatar" src="img/contact/1.jpg" alt="">
														</div>
                                                        <div class="message">
                                                            <a class="message-author" href="#"> Mark Smith </a>
                                                            <span class="message-date">  Fri Jan 25 2015 - 11:12:36 </span>
                                                            <span class="message-content">
																	All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet.
																		It uses a dictionary of over 200 Latin words.
																	</span>
                                                            <div class="m-t-md mg-t-10">
                                                                <a class="btn btn-xs btn-default"><i class="fa fa-thumbs-up"></i> Like </a>
                                                                <a class="btn btn-xs btn-success"><i class="fa fa-heart"></i> Love</a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="chat-message">
														<div class="profile-hdtc">
															  <img class="message-avatar" src="img/contact/2.jpg" alt="">
														</div>
                                                        <div class="message">
                                                            <a class="message-author" href="#"> Karl Jordan </a>
                                                            <span class="message-date">  Fri Jan 25 2015 - 11:12:36 </span>
                                                            <span class="message-content">
																	Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover.
																	</span>
                                                            <div class="m-t-md mg-t-10">
                                                                <a class="btn btn-xs btn-default"><i class="fa fa-thumbs-up"></i> Like </a>
                                                                <a class="btn btn-xs btn-default"><i class="fa fa-heart"></i> Love</a>
                                                                <a class="btn btn-xs btn-primary"><i class="fa fa-pencil"></i> Message</a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="chat-message">
														<div class="profile-hdtc">
															  <img class="message-avatar" src="img/contact/3.jpg" alt="">
														</div>
                                                        <div class="message">
                                                            <a class="message-author" href="#"> Michael Smith </a>
                                                            <span class="message-date">  Fri Jan 25 2015 - 11:12:36 </span>
                                                            <span class="message-content">
																	There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration.
																	</span>
                                                        </div>
                                                    </div>
                                                    <div class="chat-message">
														<div class="profile-hdtc">
															  <img class="message-avatar" src="img/contact/4.jpg" alt="">
														</div>
                                                        <div class="message">
                                                            <a class="message-author" href="#"> Alice Jordan </a>
                                                            <span class="message-date">  Fri Jan 25 2015 - 11:12:36 </span>
                                                            <span class="message-content">
																	All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet.
																		It uses a dictionary of over 200 Latin words.
																	</span>
                                                            <div class="m-t-md mg-t-10">
                                                                <a class="btn btn-xs btn-default"><i class="fa fa-thumbs-up"></i> Like </a>
                                                                <a class="btn btn-xs btn-default"><i class="fa fa-heart"></i> Love</a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="product-tab-list tab-pane fade" id="INFORMATION">
                                    <div class="row">
                                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                            <div class="review-content-section">
                                                <div class="row">
                                                    <div class="col-lg-6">
                                                        <div class="form-group">
                                                            <input name="number" type="text" class="form-control" placeholder="First Name">
                                                        </div>
                                                        <div class="form-group">
                                                            <input type="text" class="form-control" placeholder="Last Name">
                                                        </div>
                                                        <div class="form-group">
                                                            <input type="text" class="form-control" placeholder="Address">
                                                        </div>
                                                        <div class="form-group">
                                                            <input type="text" class="form-control" placeholder="Date of Birth">
                                                        </div>
                                                        <div class="form-group">
                                                            <input type="text" class="form-control" placeholder="Department">
                                                        </div>
                                                        <div class="form-group">
                                                            <input type="number" class="form-control" placeholder="Pincode">
                                                        </div>
                                                        <div class="file-upload-inner ts-forms">
                                                            <div class="input prepend-big-btn">
                                                                <input type="file" placeholder="Select Photo" value="Select Photo">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-6">
                                                        <div class="form-group sm-res-mg-15 tbpf-res-mg-15">
                                                            <input type="text" class="form-control" placeholder="Description">
                                                        </div>
                                                        <div class="form-group">
                                                            <select class="form-control">
																<option>Select Gender</option>
																<option>Male</option>
																<option>Female</option>
															</select>
                                                        </div>
                                                        <div class="form-group">
                                                            <select class="form-control">
																	<option>Select country</option>
																	<option>India</option>
																	<option>Pakistan</option>
																	<option>Amerika</option>
																	<option>China</option>
																	<option>Dubai</option>
																	<option>Nepal</option>
																</select>
                                                        </div>
                                                        <div class="form-group">
                                                            <select class="form-control">
																	<option>Select state</option>
																	<option>Gujarat</option>
																	<option>Maharastra</option>
																	<option>Rajastan</option>
																	<option>Maharastra</option>
																	<option>Rajastan</option>
																	<option>Gujarat</option>
																</select>
                                                        </div>
                                                        <div class="form-group">
                                                            <select class="form-control">
																	<option>Select city</option>
																	<option>Surat</option>
																	<option>Baroda</option>
																	<option>Navsari</option>
																	<option>Baroda</option>
																	<option>Surat</option>
																</select>
                                                        </div>
                                                        <div class="form-group">
                                                            <input type="text" class="form-control" placeholder="Website URL">
                                                        </div>
                                                        <input type="number" class="form-control" placeholder="Mobile no.">
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