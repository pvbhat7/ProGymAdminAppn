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
                                <li><a title="All Students" href="maleMembers"><span class="mini-sub-pro">Male</span></a></li>
                                <li><a title="Add Students" href="femaleMembers"><span class="mini-sub-pro">Female</span></a></li>
                                <li><a title="Edit Students" href="allMembers"><span class="mini-sub-pro">All Members</span></a></li>
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
                                <li><a title="Peity Charts" href="#"><span class="mini-sub-pro">Static Table</span></a></li>
                                <li><a title="Data Table" href="allReports"><span class="mini-sub-pro">Data Table</span></a></li>
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
                                                <li class="nav-item"><a href="allMembers" class="nav-link">Members</a>
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
                                                        <li><a href="#"><span class="edu-icon edu-locked author-log-ic"></span>Log Out</a>
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
                    <div class="row">
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <div class="breadcome-list">
                                <div class="row">
                                    <div class="col-lg-2 col-md-2 col-sm-2 col-xs-3">
                                        <div class="breadcome-heading">
                                            <div class="form-group">
											  <label for="sel1">Select Filter</label>
											  <select class="form-control" id="filter" style="width:auto;" onchange="showHideDiv('filter');">
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
											    <option value="jan">January</option>
											    <option value="feb">February</option>
											    <option value="march">March</option>
											    <option value="april">April</option>
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
                                    <h1>Projects <span class="table-project-n">Data</span> Table</h1>
                                </div>
                            </div>
                            <div class="sparkline13-graph">
                                <div class="datatable-dashv1-list custom-datatable-overright">
                                    <div id="toolbar">
                                        <select class="form-control dt-tb">
											<option value="">Export Basic</option>
											<option value="all">Export All</option>
											<option value="selected">Export Selected</option>
										</select>
                                    </div>
                                    <table id="table" data-toggle="table" data-pagination="true" data-search="true" data-show-columns="true" data-show-pagination-switch="true" data-show-refresh="true" data-key-events="true" data-show-toggle="true" data-resizable="true" data-cookie="true"
                                        data-cookie-id-table="saveId" data-show-export="true" data-click-to-select="true" data-toolbar="#toolbar">
                                        <thead>
                                            <tr>
                                                <th data-field="state" data-checkbox="true"></th>
                                                <th data-field="id">ID</th>
                                                <th data-field="name" data-editable="true">Task</th>
                                                <th data-field="email" data-editable="true">Email</th>
                                                <th data-field="phone" data-editable="true">Phone</th>
                                                <th data-field="complete">Completed</th>
                                                <th data-field="task" data-editable="true">Task</th>
                                                <th data-field="date" data-editable="true">Date</th>
                                                <th data-field="price" data-editable="true">Price</th>
                                                <th data-field="action">Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td></td>
                                                <td>1</td>
                                                <td>Web Development</td>
                                                <td>admin@uttara.com</td>
                                                <td>+8801962067309</td>
                                                <td class="datatable-ct"><span class="pie">1/6</span>
                                                </td>
                                                <td>10%</td>
                                                <td>Jul 14, 2017</td>
                                                <td>$5455</td>
                                                <td class="datatable-ct"><i class="fa fa-check"></i>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <td>2</td>
                                                <td>Graphic Design</td>
                                                <td>fox@itpark.com</td>
                                                <td>+8801762067304</td>
                                                <td class="datatable-ct"><span class="pie">230/360</span>
                                                </td>
                                                <td>70%</td>
                                                <td>fab 2, 2017</td>
                                                <td>$8756</td>
                                                <td class="datatable-ct"><i class="fa fa-check"></i>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <td>3</td>
                                                <td>Software Development</td>
                                                <td>gumre@hash.com</td>
                                                <td>+8801862067308</td>
                                                <td class="datatable-ct"><span class="pie">0.42/1.461</span>
                                                </td>
                                                <td>5%</td>
                                                <td>Seb 5, 2017</td>
                                                <td>$9875</td>
                                                <td class="datatable-ct"><i class="fa fa-check"></i>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <td>4</td>
                                                <td>Woocommerce</td>
                                                <td>kyum@frok.com</td>
                                                <td>+8801962066547</td>
                                                <td class="datatable-ct"><span class="pie">2,7</span>
                                                </td>
                                                <td>15%</td>
                                                <td>Oct 10, 2017</td>
                                                <td>$3254</td>
                                                <td class="datatable-ct"><i class="fa fa-check"></i>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <td>5</td>
                                                <td>Joomla</td>
                                                <td>jams@game.com</td>
                                                <td>+8801962098745</td>
                                                <td class="datatable-ct"><span class="pie">200,133</span>
                                                </td>
                                                <td>80%</td>
                                                <td>Nov 20, 2017</td>
                                                <td>$58745</td>
                                                <td class="datatable-ct"><i class="fa fa-check"></i>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <td>6</td>
                                                <td>Wordpress</td>
                                                <td>flat@yem.com</td>
                                                <td>+8801962254781</td>
                                                <td class="datatable-ct"><span class="pie">0.42,1.051</span>
                                                </td>
                                                <td>30%</td>
                                                <td>Aug 25, 2017</td>
                                                <td>$789879</td>
                                                <td class="datatable-ct"><i class="fa fa-check"></i>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <td>7</td>
                                                <td>Ecommerce</td>
                                                <td>hasan@wpm.com</td>
                                                <td>+8801962254863</td>
                                                <td class="datatable-ct"><span class="pie">2,7</span>
                                                </td>
                                                <td>15%</td>
                                                <td>July 17, 2017</td>
                                                <td>$21424</td>
                                                <td class="datatable-ct"><i class="fa fa-check"></i>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <td>8</td>
                                                <td>Android Apps</td>
                                                <td>ATM@devep.com</td>
                                                <td>+8801962875469</td>
                                                <td class="datatable-ct"><span class="pie">2,7</span>
                                                </td>
                                                <td>15%</td>
                                                <td>June 11, 2017</td>
                                                <td>$78978</td>
                                                <td class="datatable-ct"><i class="fa fa-check"></i>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <td>9</td>
                                                <td>Prestashop</td>
                                                <td>presta@Prest.com</td>
                                                <td>+8801962067524</td>
                                                <td class="datatable-ct"><span class="pie">2,7</span>
                                                </td>
                                                <td>15%</td>
                                                <td>May 9, 2017</td>
                                                <td>$45645</td>
                                                <td class="datatable-ct"><i class="fa fa-check"></i>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <td>10</td>
                                                <td>Game Development</td>
                                                <td>Dev@game.com</td>
                                                <td>+8801962067457</td>
                                                <td class="datatable-ct"><span class="pie">2,7</span>
                                                </td>
                                                <td>15%</td>
                                                <td>April 5, 2017</td>
                                                <td>$4564545</td>
                                                <td class="datatable-ct"><i class="fa fa-check"></i>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <td>11</td>
                                                <td>Angular Js</td>
                                                <td>gular@angular.com</td>
                                                <td>+8801962067124</td>
                                                <td class="datatable-ct"><span class="pie">2,7</span>
                                                </td>
                                                <td>15%</td>
                                                <td>Dec 1, 2017</td>
                                                <td>$645455</td>
                                                <td class="datatable-ct"><i class="fa fa-check"></i>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <td>12</td>
                                                <td>Opencart</td>
                                                <td>open@cart.com</td>
                                                <td>+8801962067587</td>
                                                <td class="datatable-ct"><span class="pie">2,7</span>
                                                </td>
                                                <td>15%</td>
                                                <td>Jan 6, 2017</td>
                                                <td>$78978</td>
                                                <td class="datatable-ct"><i class="fa fa-check"></i>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <td>13</td>
                                                <td>Education</td>
                                                <td>john@example.com</td>
                                                <td>+8801962067471</td>
                                                <td class="datatable-ct"><span class="pie">2,7</span>
                                                </td>
                                                <td>15%</td>
                                                <td>Feb 6, 2016</td>
                                                <td>$456456</td>
                                                <td class="datatable-ct"><i class="fa fa-check"></i>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <td>14</td>
                                                <td>Construction</td>
                                                <td>mary@example.com</td>
                                                <td>+8801962012457</td>
                                                <td class="datatable-ct"><span class="pie">2,7</span>
                                                </td>
                                                <td>15%</td>
                                                <td>Jan 6, 2016</td>
                                                <td>$87978</td>
                                                <td class="datatable-ct"><i class="fa fa-check"></i>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <td>15</td>
                                                <td>Real Estate</td>
                                                <td>july@example.com</td>
                                                <td>+8801962067309</td>
                                                <td class="datatable-ct"><span class="pie">2,7</span>
                                                </td>
                                                <td>15%</td>
                                                <td>Dec 1, 2016</td>
                                                <td>$454554</td>
                                                <td class="datatable-ct"><i class="fa fa-check"></i>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <td>16</td>
                                                <td>Personal Regume</td>
                                                <td>john@example.com</td>
                                                <td>+8801962067306</td>
                                                <td class="datatable-ct"><span class="pie">2,7</span>
                                                </td>
                                                <td>15%</td>
                                                <td>May 9, 2016</td>
                                                <td>$564555</td>
                                                <td class="datatable-ct"><i class="fa fa-check"></i>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <td>17</td>
                                                <td>Admin Template</td>
                                                <td>mary@example.com</td>
                                                <td>+8801962067305</td>
                                                <td class="datatable-ct"><span class="pie">2,7</span>
                                                </td>
                                                <td>15%</td>
                                                <td>June 11, 2016</td>
                                                <td>$454565</td>
                                                <td class="datatable-ct"><i class="fa fa-check"></i>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <td>18</td>
                                                <td>FrontEnd</td>
                                                <td>july@example.com</td>
                                                <td>+8801962067304</td>
                                                <td class="datatable-ct"><span class="pie">2,7</span>
                                                </td>
                                                <td>15%</td>
                                                <td>May 9, 2015</td>
                                                <td>$456546</td>
                                                <td class="datatable-ct"><i class="fa fa-check"></i>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <td>19</td>
                                                <td>Backend</td>
                                                <td>john@range.com</td>
                                                <td>+8801962067303</td>
                                                <td class="datatable-ct"><span class="pie">2,7</span>
                                                </td>
                                                <td>15%</td>
                                                <td>Feb 9, 2014</td>
                                                <td>$564554</td>
                                                <td class="datatable-ct"><i class="fa fa-check"></i>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <td>20</td>
                                                <td>Java Advance</td>
                                                <td>lamon@ghs.com</td>
                                                <td>+8801962067302</td>
                                                <td class="datatable-ct"><span class="pie">2,7</span>
                                                </td>
                                                <td>15%</td>
                                                <td>July 6, 2014</td>
                                                <td>$789889</td>
                                                <td class="datatable-ct"><i class="fa fa-check"></i>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <td>21</td>
                                                <td>Jquery Advance</td>
                                                <td>hasad@uth.com</td>
                                                <td>+8801962067301</td>
                                                <td class="datatable-ct"><span class="pie">2,7</span>
                                                </td>
                                                <td>15%</td>
                                                <td>Jun 6, 2013</td>
                                                <td>$4565656</td>
                                                <td class="datatable-ct"><i class="fa fa-check"></i>
                                                </td>
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