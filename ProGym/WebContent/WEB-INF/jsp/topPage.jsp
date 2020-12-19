<%@page import="com.progym.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
 <%
if(session.getAttribute("loggedInUser") == null)
response.sendRedirect("login");
%>
<div class="left-sidebar-pro">
        <nav id="sidebar" class="">
            </br>
            <div class="sidebar-header">
                <a href="index"><img class="main-logo" src="img/progym.png" alt="" width="140" height="100" /></a>
                <strong><a href="index"><img src="img/progym.png" width="60" height="60" alt="" /></a></strong>
            </div>
            </br>
            <div class="left-custom-menu-adp-wrap comment-scrollbar">
                <nav class="sidebar-nav left-sidebar-menu-pro">
                    <ul class="metismenu" id="menu1">

                        <li>
                            <a class="has-arrow" href="#" aria-expanded="false"><span class="educate-icon educate-student icon-wrap"></span> <span class="mini-click-non">Members</span></a>
                            <ul class="submenu-angle" aria-expanded="false">
                                <!-- <li><a title="All Students" href="maleMembers"><span class="mini-sub-pro">Male</span></a></li>
                                <li><a title="Add Students" href="femaleMembers"><span class="mini-sub-pro">Female</span></a></li> -->
                                <li><a title="Edit Students" href="allMembers?gender=all&zone=none&enableDisable=enable"><span class="mini-sub-pro">All Members</span></a></li>
                                <li><a title="Students Profile" href="addMember"><span class="mini-sub-pro">Add Member</span></a></li>
                            </ul>
                        </li>
                        <li>
                            <a class="has-arrow" href="#" aria-expanded="false"><span class="educate-icon educate-course icon-wrap"></span> <span class="mini-click-non">Packages</span></a>
                            <ul class="submenu-angle" aria-expanded="false">
                                <li><a title="All Courses" href="malePackage"><span class="mini-sub-pro">Male Package</span></a></li>
                                <li><a title="Add Courses" href="femalePackage"><span class="mini-sub-pro">Female Package</span></a></li>
                                <li><a title="Add Courses" href="addPackage"><span class="mini-sub-pro">Add Package</span></a></li>                                
                            </ul>
                        </li>
                        <li>
                            <a class="has-arrow" href="#" aria-expanded="false"><span class="educate-icon educate-library icon-wrap"></span> <span class="mini-click-non">Payments</span></a>
                            <ul class="submenu-angle" aria-expanded="false">
                                <li><a title="All Library" href="paidPayments?gender=all"><span class="mini-sub-pro">Paid</span></a></li>
                                <li><a title="Add Library" href="pendingPayments?gender=all"><span class="mini-sub-pro">Pending</span></a></li>                                
                            </ul>
                        </li>
                        
                        <li>
                            <a class="has-arrow" href="#" aria-expanded="false"><span class="educate-icon educate-data-table icon-wrap"></span> <span class="mini-click-non">Reports</span></a>
                            <ul class="submenu-angle" aria-expanded="false">
	                            <%
								User u = (User)session.getAttribute("loggedInUser");
								if(u.getAuthorizedToApprovePayment().equalsIgnoreCase("YES")){
								%>
                                <li><a title="Data Table" href="allReports"><span class="mini-sub-pro">View Collection</span></a></li>
                                <li><a title="Data Table" href="sendPendingInvoices"><span class="mini-sub-pro">Invoice Receipts</span></a></li>
                                <%} %>
                                <li><a title="Data Table" href="notifications"><span class="mini-sub-pro">Notifications</span></a></li>
                                <li><a title="Data Table" href="fileUploadPage"><span class="mini-sub-pro">Upload Photo</span></a></li>
                                <%--<li><a title="Data Table" href="steamView"><span class="mini-sub-pro">Steam</span></a></li>--%>
                                <li><a title="Data Table" href="getSmsLogs"><span class="mini-sub-pro">SMS Logs</span></a></li>
                                
                            </ul>
                        </li>
                        
                    </ul>
                </nav>
            </div>
        </nav>
    </div>
    <!-- End Left menu area -->
    <!-- Start Welcome area -->
    <div class="all-content-wrapper">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="logo-pro">
                        <a href="index"><img class="main-logo" src="img/logo/logo.png" alt="" /></a>
                    </div>
                </div>
            </div>
        </div>
        <div class="header-advance-area">
            <div class="header-top-area">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <div class="header-top-wraper">
                                <div class="row">
                                    <div class="col-lg-1 col-md-0 col-sm-1 col-xs-12">
                                        <div class="menu-switcher-pro">
                                            <button type="button" id="sidebarCollapse" class="btn bar-button-pro header-drl-controller-btn btn-info navbar-btn">
													<i class="educate-icon educate-nav"></i>
												</button>
                                        </div>
                                    </div>
                                    <div class="col-lg-6 col-md-7 col-sm-6 col-xs-12">
                                        <div class="header-top-menu tabl-d-n">
                                            <ul class="nav navbar-nav mai-top-nav">
                                                <li class="nav-item"><a href="index" class="nav-link">Home</a>
                                                </li>
                                                <li class="nav-item"><a href="allMembers?gender=all&zone=none&enableDisable=enable" class="nav-link">Members</a>
                                                </li>
                                                <li class="nav-item dropdown res-dis-nn">
                                                    <a href="#" data-toggle="dropdown" role="button" aria-expanded="false" class="nav-link dropdown-toggle">Packages <span class="angle-down-topmenu"><i class="fa fa-angle-down"></i></span></a>
                                                    <div role="menu" class="dropdown-menu animated zoomIn">
                                                        <a href="malePackage" class="dropdown-item">Male Package</a>
                                                        <a href="femalePackage" class="dropdown-item">Female Package</a>                                                        
                                                    </div>
                                                </li>
                                                <li class="nav-item dropdown res-dis-nn">
                                                    <a href="#" data-toggle="dropdown" role="button" aria-expanded="false" class="nav-link dropdown-toggle">Payments <span class="angle-down-topmenu"><i class="fa fa-angle-down"></i></span></a>
                                                    <div role="menu" class="dropdown-menu animated zoomIn">
                                                        <a href="pendingPayments?gender=all" class="dropdown-item">Pending</a>
                                                        <a href="paidPayments?gender=all" class="dropdown-item">Paid</a>                                                        
                                                    </div>
                                                </li>
                                                <%
												User u1 = (User)session.getAttribute("loggedInUser");
												if(u1.getAuthorizedToApprovePayment().equalsIgnoreCase("YES")){
												%>
                                                <li class="nav-item"><a href="allReports" class="nav-link">Collections</a>
                                                </li>
                                                <%} %>
                                                
                                                                                                                        
                                                <!-- backupDatabase -->
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="col-lg-5 col-md-5 col-sm-12 col-xs-12">
                                        <div class="header-right-info">
                                            <ul class="nav navbar-nav mai-top-nav header-right-menu">
                                                
                                                <li class="nav-item">
                                                    <a href="#" data-toggle="dropdown" role="button" aria-expanded="false" class="nav-link dropdown-toggle">
															<img src="img/product/pro4.jpg" alt="" />
															<span class="admin-name">${username}</span>
															<i class="fa fa-angle-down edu-icon edu-down-arrow"></i>
														</a>
                                                    <ul role="menu" class="dropdown-header-top author-log dropdown-menu animated zoomIn">
                                                        <li><a href="logout"><span class="edu-icon edu-locked author-log-ic"></span>Log Out</a>
                                                        </li>
                                                    </ul>
                                                </li>
                                                
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Mobile Menu start -->
            <div class="mobile-menu-area">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <div class="mobile-menu">
                                <nav id="dropdown">
                                    <ul class="mobile-menu-nav">
                                       
                                       <li><a data-toggle="collapse" data-target="#demopro" href="#">Members <span class="admin-project-icon edu-icon edu-down-arrow"></span></a>
                                            <ul id="demopro" class="collapse dropdown-header-top">
                                                <li><a href="allMembers?gender=all&zone=none">All Members</a>
                                                </li>
                                                <li><a href="addMember">Add Member</a>
                                                </li>                                                
                                            </ul>
                                        </li>
                                        
                                        <!-- 
                                        <li><a data-toggle="collapse" data-target="#democrou" href="#">Packages <span class="admin-project-icon edu-icon edu-down-arrow"></span></a>
                                            <ul id="democrou" class="collapse dropdown-header-top">
                                                <li><a href="malePackage">Male Package</a>
                                                </li>
                                                <li><a href="femalePackage">Female Package</a>
                                                </li>
                                                <li><a href="addPackage">Add Package</a>
                                                </li>
                                                
                                            </ul>
                                        </li>
                                        <li><a data-toggle="collapse" data-target="#demolibra" href="#">Payments <span class="admin-project-icon edu-icon edu-down-arrow"></span></a>
                                            <ul id="demolibra" class="collapse dropdown-header-top">
                                                <li><a href="paidPayments?gender=all">Paid Payments</a>
                                                </li>
                                                <li><a href="pendingPayments?gender=all">Pending Payments</a>
                                                </li>                                                
                                            </ul>
                                        </li> 
                                        <li><a data-toggle="collapse" data-target="#demodepart" href="#"> <span class="admin-project-icon edu-icon edu-down-arrow"></span></a>
                                            <ul id="demodepart" class="collapse dropdown-header-top">
                                                <li><a href="allReports">View Collection</a>
                                                </li>
                                                <li><a href="notifications">Notifications</a>
                                                </li>
                                                <li><a href="fileUploadPage">Upload Photo</a>
                                                </li>
                                            </ul>
                                        </li> -->
                                        
                                        <li><a href="fileUploadPage"> Upload Photo<span class="admin-project-icon edu-icon edu-down-arrow"></span></a></li>
                                        <li><a title="Data Table" href="mobilenotifications"><span class="mini-sub-pro">Notifications</span></a></li>
                                        
                                        
                                        
                                      
                                       
                                    </ul>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            </br>
            <!-- <!-- Mobile Menu end -->
            <!-- <div class="breadcome-area">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <div class="breadcome-list">
                                <div class="row">
                                   <marquee><img src="img/progym.png" alt="" width="50" height="20" /> ProGym Kolhapur<img src="img/progym.png" alt="" width="50" height="20" /></marquee>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div> --> 
        </div>