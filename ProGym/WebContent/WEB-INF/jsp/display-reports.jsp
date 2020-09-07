<%@page import="com.progym.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List"%>
<%
if(session.getAttribute("loggedInUser") == null)
response.sendRedirect("login");
%>
<html class="no-js" lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Data Table | Kiaalap - Kiaalap Admin Template</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" type="image/x-icon" href="img/favicon.ico">
    <link href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,700,900" rel="stylesheet">
    <link rel="stylesheet" href="css1/bootstrap.min.css">
    <link rel="stylesheet" href="css1/font-awesome.min.css">
    <link rel="stylesheet" href="css1/owl.carousel.css">
    <link rel="stylesheet" href="css1/owl.theme.css">
    <link rel="stylesheet" href="css1/owl.transitions.css">
    <link rel="stylesheet" href="css1/animate.css">
    <link rel="stylesheet" href="css1/normalize.css">
    <link rel="stylesheet" href="css1/meanmenu.min.css">
    <link rel="stylesheet" href="css1/main.css">
    <link rel="stylesheet" href="css1/educate-custon-icon.css">
    <link rel="stylesheet" href="css1/morrisjs/morris.css">
    <link rel="stylesheet" href="css1/scrollbar/jquery.mCustomScrollbar.min.css">
    <link rel="stylesheet" href="css1/metisMenu/metisMenu.min.css">
    <link rel="stylesheet" href="css1/metisMenu/metisMenu-vertical.css">
    <link rel="stylesheet" href="css1/calendar/fullcalendar.min.css">
    <link rel="stylesheet" href="css1/calendar/fullcalendar.print.min.css">
    <link rel="stylesheet" href="css1/editor/select2.css">
    <link rel="stylesheet" href="css1/editor/datetimepicker.css">
    <link rel="stylesheet" href="css1/editor/bootstrap-editable.css">
    <link rel="stylesheet" href="css1/editor/x-editor-style.css">
    <link rel="stylesheet" href="css1/data-table/bootstrap-table.css">
    <link rel="stylesheet" href="css1/data-table/bootstrap-editable.css">
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="css1/responsive.css">
    <script src="js1/vendor/modernizr-2.8.3.min.js"></script>
    <script>
    
    function showHideDiv(e)
    {
    	var e = document.getElementById(e);
    	if(e.value == '1')
    	{
    		document.getElementById('1d').style.display = "block";
    		document.getElementById('2d').style.display = "none";
    		document.getElementById('3d').style.display = "none";
    	}
    	else if(e.value == '2')
    	{
    		document.getElementById('1d').style.display = "none";
    		document.getElementById('2d').style.display = "block";
    		document.getElementById('3d').style.display = "none";
    	}else if(e.value == '3')
    	{
    		document.getElementById('1d').style.display = "none";
    		document.getElementById('2d').style.display = "none";
    		document.getElementById('3d').style.display = "block";
    	}
    	else if(e.value == 'none')
    	{
    		document.getElementById('1d').style.display = "none";
    		document.getElementById('2d').style.display = "none";
    		document.getElementById('3d').style.display = "none";
    	}
    	
    }
    
    window.onload = function() {
    	var filter = '${filtername}';
    	console.log(filter);
    	if(filter == '1d')
    	{
    		document.getElementById('1d').style.display = "block";
    		document.getElementById('2d').style.display = "none";
    		document.getElementById('3d').style.display = "none";
    	}
    	else if(filter == '2d')
    	{
    		document.getElementById('1d').style.display = "none";
    		document.getElementById('2d').style.display = "block";
    		document.getElementById('3d').style.display = "none";
    	}else if(filter == '3d')
    	{
    		document.getElementById('1d').style.display = "none";
    		document.getElementById('2d').style.display = "none";
    		document.getElementById('3d').style.display = "block";
    	}
    	
    	};
    </script>
</head>

<body>
   
   <!-- Start Left menu area -->
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
                                <li><a title="Edit Students" href="allMembers?gender=all&zone=none"><span class="mini-sub-pro">All Members</span></a></li>
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
                                <%} %>
                                <li><a title="Data Table" href="notifications"><span class="mini-sub-pro">Notifications</span></a></li>
                                <li><a title="Data Table" href="fileUploadPage"><span class="mini-sub-pro">Upload Photo</span></a></li>
                                <li><a title="Data Table" href="sendPendingInvoices"><span class="mini-sub-pro">Invoice Receipts</span></a></li>
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
                                                <li class="nav-item"><a href="allMembers?gender=all&zone=none" class="nav-link">Members</a>
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
                                                <li class="nav-item"><a href="allReports" class="nav-link">Reports</a>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="col-lg-5 col-md-5 col-sm-12 col-xs-12">
                                        <div class="header-right-info">
                                            <ul class="nav navbar-nav mai-top-nav header-right-menu">
                                                
                                                <li class="nav-item">
                                                    <a href="#" data-toggle="dropdown" role="button" aria-expanded="false" class="nav-link dropdown-toggle">
															<img src="img/product/pro4.jpg" alt="" />
															<span class="admin-name">Pranav</span>
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
                                       
                                       
                                        
                                        <li><a data-toggle="collapse" data-target="#demopro" href="#">Students <span class="admin-project-icon edu-icon edu-down-arrow"></span></a>
                                            <ul id="demopro" class="collapse dropdown-header-top">
                                                <li><a href="all-students.html">All Students</a>
                                                </li>
                                                <li><a href="add-student.html">Add Student</a>
                                                </li>
                                                <li><a href="edit-student.html">Edit Student</a>
                                                </li>
                                                <li><a href="student-profile.html">Student Profile</a>
                                                </li>
                                            </ul>
                                        </li>
                                        <li><a data-toggle="collapse" data-target="#democrou" href="#">Courses <span class="admin-project-icon edu-icon edu-down-arrow"></span></a>
                                            <ul id="democrou" class="collapse dropdown-header-top">
                                                <li><a href="all-courses.html">All Courses</a>
                                                </li>
                                                <li><a href="add-course.html">Add Course</a>
                                                </li>
                                                <li><a href="edit-course.html">Edit Course</a>
                                                </li>
                                                <li><a href="course-profile.html">Courses Info</a>
                                                </li>
                                                <li><a href="course-payment.html">Courses Payment</a>
                                                </li>
                                            </ul>
                                        </li>
                                        <li><a data-toggle="collapse" data-target="#demolibra" href="#">Library <span class="admin-project-icon edu-icon edu-down-arrow"></span></a>
                                            <ul id="demolibra" class="collapse dropdown-header-top">
                                                <li><a href="library-assets.html">Library Assets</a>
                                                </li>
                                                <li><a href="add-library-assets.html">Add Library Asset</a>
                                                </li>
                                                <li><a href="edit-library-assets.html">Edit Library Asset</a>
                                                </li>
                                            </ul>
                                        </li>
                                        <li><a data-toggle="collapse" data-target="#demodepart" href="#">Departments <span class="admin-project-icon edu-icon edu-down-arrow"></span></a>
                                            <ul id="demodepart" class="collapse dropdown-header-top">
                                                <li><a href="departments.html">Departments List</a>
                                                </li>
                                                <li><a href="add-department.html">Add Departments</a>
                                                </li>
                                                <li><a href="edit-department.html">Edit Departments</a>
                                                </li>
                                            </ul>
                                        </li>
                                      
                                       
                                    </ul>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Mobile Menu end -->
            <div class="breadcome-area">
                <div class="container-fluid">
                </br>
                <!-- CURRENT MONTH COLLECTION STARTS-->
                <div class="row">                
                	<div class="col-lg-2 col-md-6 col-sm-6 col-xs-12">
                        
                        <div class="analytics-sparkle-line reso-mg-b-30">
                            <div class="analytics-content">
                                <h5></h5>
                                <h1>${currentMonthName} Collection</h1>
                                <div class="progress m-b-0">
                                </div>
                            </div>
                        </div>                        
                    </div>
                    <div class="col-lg-2 col-md-6 col-sm-6 col-xs-12">
                        <a href="#">
                        <div class="analytics-sparkle-line reso-mg-b-30">
                            <div class="analytics-content">
                                <h5>Gym Collection - MALE</h5>
                                <h2>Rs. <span class="counter">${currentMale}</span> </h2>
                                <div class="progress m-b-0">
                                    <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width:100%;"> <span class="sr-only">20% Complete</span> </div>
                                </div>
                            </div>
                        </div>
                        </a>
                    </div>
                    <div class="col-lg-2 col-md-6 col-sm-6 col-xs-12">
                    <a href="#">
                        <div class="analytics-sparkle-line reso-mg-b-30">
                            <div class="analytics-content">
                                <h5>Gym Collection - FEMALE</h5>
                                <h2>Rs. <span class="counter">${currentFemale}</span> </h2>
                                <div class="progress m-b-0">
                                    <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width:100%;"> <span class="sr-only">230% Complete</span> </div>
                                </div>
                            </div>
                        </div>
                        </a>
                    </div>
                    <div class="col-lg-2 col-md-6 col-sm-6 col-xs-12">
                        <a href="#">
                        <div class="analytics-sparkle-line reso-mg-b-30 table-mg-t-pro dk-res-t-pro-30">
                            <div class="analytics-content">
                                <h5>Steam Collection</h5>
                                <h2>Rs. <span class="counter">${currentSteam}</span> </h2>
                                <div class="progress m-b-0">
                                    <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width:100%;"> <span class="sr-only">20% Complete</span> </div>
                                </div>
                            </div>
                        </div>
                        </a>
                    </div>
                    <div class="col-lg-2 col-md-6 col-sm-6 col-xs-12">
                        <a href="#">
                        <div class="analytics-sparkle-line table-mg-t-pro dk-res-t-pro-30">
                            <div class="analytics-content">
                                <h5>Total Collection</h5>
                                <h2>Rs. <span class="counter">${currentTotal}</span> </h2>
                                <div class="progress m-b-0">
                                    <div class="progress-bar progress-bar-inverse" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width:100%;"> <span class="sr-only">230% Complete</span> </div>
                                </div>
                            </div>
                        </div>
                        </a>
                    </div>
                </div>
                <!-- CURRENT MONTH COLLECTION ENDS-->
                </br>
                
                <!-- LAST MONTH COLLECTION STARTS-->
                	
                <div class="row">                
                	<div class="col-lg-2 col-md-6 col-sm-6 col-xs-12">
                        <div class="analytics-sparkle-line reso-mg-b-30">
                            <div class="analytics-content">
                                <h5></h5>
                                <h1>${lasttMonthName} Collection</h1>
                                <div class="progress m-b-0">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-2 col-md-6 col-sm-6 col-xs-12">
                        <a href="#">
                        <div class="analytics-sparkle-line reso-mg-b-30">
                            <div class="analytics-content">
                                <h5>Gym Collection - MALE</h5>
                                <h2>Rs. <span class="counter">${lastMale}</span> </h2>
                                <div class="progress m-b-0">
                                    <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width:100%;"> <span class="sr-only">20% Complete</span> </div>
                                </div>
                            </div>
                        </div>
                        </a>
                    </div>
                    <div class="col-lg-2 col-md-6 col-sm-6 col-xs-12">
                    <a href="#">
                        <div class="analytics-sparkle-line reso-mg-b-30">
                            <div class="analytics-content">
                                <h5>Gym Collection - FEMALE</h5>
                                <h2>Rs. <span class="counter">${lastFemale}</span> </h2>
                                <div class="progress m-b-0">
                                    <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width:100%;"> <span class="sr-only">230% Complete</span> </div>
                                </div>
                            </div>
                        </div>
                        </a>
                    </div>
                    <div class="col-lg-2 col-md-6 col-sm-6 col-xs-12">
                        <a href="#">
                        <div class="analytics-sparkle-line reso-mg-b-30 table-mg-t-pro dk-res-t-pro-30">
                            <div class="analytics-content">
                                <h5>Steam Collection</h5>
                                <h2>Rs. <span class="counter">${lastSteam}</span> </h2>
                                <div class="progress m-b-0">
                                    <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width:100%;"> <span class="sr-only">20% Complete</span> </div>
                                </div>
                            </div>
                        </div>
                        </a>
                    </div>
                    <div class="col-lg-2 col-md-6 col-sm-6 col-xs-12">
                        <a href="#">
                        <div class="analytics-sparkle-line table-mg-t-pro dk-res-t-pro-30">
                            <div class="analytics-content">
                                <h5>Total Collection</h5>
                                <h2>Rs. <span class="counter">${lastTotal}</span> </h2>
                                <div class="progress m-b-0">
                                    <div class="progress-bar progress-bar-inverse" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width:100%;"> <span class="sr-only">230% Complete</span> </div>
                                </div>
                            </div>
                        </div>
                        </a>
                    </div>
                </div>
                <!-- LAST MONTH COLLECTION ENDS-->
                
                </br>
                                
                <!-- OVERALL COLLECTION STARTS-->
                	
                <div class="row">                
                	<div class="col-lg-2 col-md-6 col-sm-6 col-xs-12">
                        <div class="analytics-sparkle-line reso-mg-b-30">
                            <div class="analytics-content">
                                <h5></h5>
                                <h1>Overall Collection</h1>
                                <div class="progress m-b-0">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-2 col-md-6 col-sm-6 col-xs-12">
                        <a href="#">
                        <div class="analytics-sparkle-line reso-mg-b-30">
                            <div class="analytics-content">
                                <h5>Gym Collection - MALE</h5>
                                <h2>Rs. <span class="counter">${male}</span> </h2>
                                <div class="progress m-b-0">
                                    <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width:100%;"> <span class="sr-only">20% Complete</span> </div>
                                </div>
                            </div>
                        </div>
                        </a>
                    </div>
                    <div class="col-lg-2 col-md-6 col-sm-6 col-xs-12">
                    <a href="#">
                        <div class="analytics-sparkle-line reso-mg-b-30">
                            <div class="analytics-content">
                                <h5>Gym Collection - FEMALE</h5>
                                <h2>Rs. <span class="counter">${female}</span> </h2>
                                <div class="progress m-b-0">
                                    <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width:100%;"> <span class="sr-only">230% Complete</span> </div>
                                </div>
                            </div>
                        </div>
                        </a>
                    </div>
                    <div class="col-lg-2 col-md-6 col-sm-6 col-xs-12">
                        <a href="#">
                        <div class="analytics-sparkle-line reso-mg-b-30 table-mg-t-pro dk-res-t-pro-30">
                            <div class="analytics-content">
                                <h5>Steam Collection</h5>
                                <h2>Rs. <span class="counter">${steam}</span> </h2>
                                <div class="progress m-b-0">
                                    <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width:100%;"> <span class="sr-only">20% Complete</span> </div>
                                </div>
                            </div>
                        </div>
                        </a>
                    </div>
                    <div class="col-lg-2 col-md-6 col-sm-6 col-xs-12">
                        <a href="#">
                        <div class="analytics-sparkle-line table-mg-t-pro dk-res-t-pro-30">
                            <div class="analytics-content">
                                <h5>Total Collection</h5>
                                <h2>Rs. <span class="counter">${total}</span> </h2>
                                <div class="progress m-b-0">
                                    <div class="progress-bar progress-bar-inverse" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width:100%;"> <span class="sr-only">230% Complete</span> </div>
                                </div>
                            </div>
                        </div>
                        </a>
                    </div>
                </div>	
                <!-- OVERALL COLLECTION ENDS-->
                
                 
                    <div class="row">
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <div class="breadcome-list">
                                <div class="row">
                                    <div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
                                        <div class="breadcome-heading">
                                            <div class="form-group">
											  <label for="sel1">Select Filter</label>
											  <select class="form-control" id="filter" style="width:auto;" onchange="showHideDiv('filter')">
											    <option value="none">None</option>
											    <option value="1">By Month & Year</option>
											    <option value="2">By Date</option>
											    <option value="3">By Gender</option>											    
											  </select>
											</div>
                                        </div>
                                    </div>
                                    
                                </div>
                                
                                <form action="viewCollectionByGMY" method="get">
                                <div class="row" id="1d" style="display: none;">
                                    <div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
                                        <div class="breadcome-heading">
                                            <div class="form-group">
											  <label for="sel1">Select Gender:</label>
											  <select name="gender" class="form-control" id="sel1" required="required">
											    <option value="">Select</option>
											    <option value="male">Male</option>
											    <option value="female">Female</option>
											    <option value="all">All</option>											    
											  </select>
											</div>
                                        </div>
                                    </div>
                                    <div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
                                        <div class="breadcome-heading">
                                            <div class="form-group">
											  <label for="sel1">Select Month:</label>
											  <select name="month" class="form-control" id="sel1" required="required">
											    <option value="">Select</option>
											    <option value="0">January</option>
											    <option value="1">February</option>
											    <option value="2">March</option>
											    <option value="3">April</option>
											    <option value="4">May</option>
											    <option value="5">June</option>
											    <option value="6">July</option>
											    <option value="7">August</option>
											    <option value="8">September</option>
											    <option value="9">October</option>
											    <option value="10">November</option>
											    <option value="11">December</option>
											  </select>
											</div>
                                        </div>
                                    </div>
                                    <div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
                                        <div class="breadcome-heading">
                                            <div class="form-group">
											  <label for="sel1">Select Year:</label>
											  <select name="year" class="form-control" id="sel1" required>
											    <option value="">Select</option>
											    <option value="2020">2020</option>
											    <option value="2019">2019</option>
											    <option value="2018">2018</option>
											    <option value="2017">2017</option>
											  </select>
											</div>
                                        </div>
                                    </div>
                                    <div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
                                        <div class="breadcome-heading">
                                        </br>                                        
											<button type="submit" class="btn btn-success">View Collection</button>
                                        </div>
                                    </div>
                                    </form>
                                </div>
                                
                                <form action="viewCollectionByGD" method="get">
                                <div class="row" id="2d" hidden="true">
                                    <div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
                                        <div class="breadcome-heading">
                                            <div class="form-group">
											  <label for="sel1">Select Gender:</label>
											  <select name="gender" class="form-control" id="sel1" required="required">
											    <option value="">Select</option>
											    <option value="male">Male</option>
											    <option value="female">Female</option>	
											    <option value="all">All</option>										    
											  </select>
											</div>
                                        </div>
                                    </div>
                                   
                                    <div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
                                        <div class="breadcome-heading">
                                        <label for="sel1">Select Date:</label>
                                            <input type="date" name="date">
                                        </div>
                                    </div>
                                    <div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
                                        <div class="breadcome-heading">
                                        </br>                                        
											<button type="submit" class="btn btn-success">View Collection</button>
                                        </div>
                                    </div>
                                </div>
                                </form>
                                
                                <form action="viewCollectionByG" method="get">
                                <div class="row" id="3d" hidden="true">
                                    <div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
                                        <div class="breadcome-heading">
                                            <div class="form-group">
											  <label for="sel1">Select Gender:</label>
											  <select name="gender" class="form-control" id="sel1" required="required">
											    <option value="">Select</option>
											    <option value="male">Male</option>
											    <option value="female">Female</option>
											    <option value="all">All</option>											    
											  </select>
											</div>
                                        </div>
                                    </div>
                                   
                                    <div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
                                        <div class="breadcome-heading">
                                        </br>                                        
											<button type="submit" class="btn btn-success">View Collection</button>
                                        </div>
                                    </div>
                                </div>
								</form>
								
								<div class="row" id="total" >
                                    <div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
                                        <div class="breadcome-heading">
                                            <div class="form-group">
											  <h1>Rs. <c:out value="${totalCollection}"/></h1>
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
		
	
        <!-- Static Table Start -->
        <div class="data-table-area mg-b-15">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <div class="sparkline13-list">
                            <div class="sparkline13-hd">
                                <div class="main-sparkline13-hd">
                                    <h1>Collections <span class="table-project-n">Data</span> Table</h1>
                                </div>
                            </div>
                            <div class="sparkline13-graph">
                                <div class="datatable-dashv1-list custom-datatable-overright">
                                    <!-- <div id="toolbar">
                                        <select class="form-control dt-tb">
											<option value="">Export Basic</option>
											<option value="all">Export All</option>
											<option value="selected">Export Selected</option>
										</select>
                                    </div> -->
                                    <table id="table" data-toggle="table" data-pagination="true" data-search="true" data-show-columns="true" data-show-pagination-switch="true" data-show-refresh="true" data-key-events="true" data-show-toggle="true" data-resizable="true" data-cookie="true"
                                        data-cookie-id-table="saveId" data-show-export="true" data-click-to-select="true" data-toolbar="#toolbar">
                                        <thead>
                                            <tr>
                                                <th data-field="clientName">Client Name</th>
                                                <th data-field="packageName">Package</th>
                                                <th data-field="feesPaid">Fees Paid</th>
                                                <th data-field="paymentStatus">Payment Status</th>
                                                <th data-field="lastPaymentDate">Last payment date</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${collectionDataPVOList}" var="collectionObject" varStatus="status">
                                            <tr>
                                                <td><c:out value="${collectionObject.name}"/></td>
                                                <td><c:out value="${collectionObject.packageName}"/></td>
                                                <td><c:out value="${collectionObject.feesPaid}"/></td>
                                                <td><c:out value="${collectionObject.paymentStatus}"/></td>
                                                <td><c:out value="${collectionObject.lastPaymentDate}"/></td>
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
        <!-- Static Table End -->
        <div class="footer-copyright-area">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <div class="footer-copy-right">
                            <p>Copyright © 2018. All rights reserved. Template by <a href="https://colorlib.com/wp/templates/">Colorlib</a></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="js1/vendor/jquery-1.12.4.min.js"></script>
    <script src="js1/bootstrap.min.js"></script>
    <script src="js1/wow.min.js"></script>
    <script src="js1/jquery-price-slider.js"></script>
    <script src="js1/jquery.meanmenu.js"></script>
    <script src="js1/owl.carousel.min.js"></script>
    <script src="js1/jquery.sticky.js"></script>
    <script src="js1/jquery.scrollUp.min.js"></script>
    <script src="js1/scrollbar/jquery.mCustomScrollbar.concat.min.js"></script>
    <script src="js1/scrollbar/mCustomScrollbar-active.js"></script>
    <script src="js1/metisMenu/metisMenu.min.js"></script>
    <script src="js1/metisMenu/metisMenu-active.js"></script>
    <script src="js1/data-table/bootstrap-table.js"></script>
    <script src="js1/data-table/tableExport.js"></script>
    <script src="js1/data-table/data-table-active.js"></script>
    <script src="js1/data-table/bootstrap-table-editable.js"></script>
    <script src="js1/data-table/bootstrap-editable.js"></script>
    <script src="js1/data-table/bootstrap-table-resizable.js"></script>
    <script src="js1/data-table/colResizable-1.5.source.js"></script>
    <script src="js1/data-table/bootstrap-table-export.js"></script>
    <script src="js1/editable/jquery.mockjax.js"></script>
    <script src="js1/editable/mock-active.js"></script>
    <script src="js1/editable/select2.js"></script>
    <script src="js1/editable/moment.min.js"></script>
    <script src="js1/editable/bootstrap-datetimepicker.js"></script>
    <script src="js1/editable/bootstrap-editable.js"></script>
    <script src="js1/editable/xediable-active.js"></script>
    <script src="js1/chart/jquery.peity.min.js"></script>
    <script src="js1/peity/peity-active.js"></script>
    <script src="js1/tab.js"></script>
    <script src="js1/plugins.js"></script>
    <script src="js1/main.js"></script>
    <script src="js1/tawk-chat.js"></script>
</body>

</html>