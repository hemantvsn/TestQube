<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<html>
<head>
<meta charset="utf-8">
<title>TestIt By SHASM</title>

<!-- Loading Bootstrap -->

<link rel="stylesheet"	href="css/bootstrap.css">
<link rel="stylesheet"	href="css/bootstrap-theme.css">
<link rel="stylesheet"  href="css/select2/select2.css" />
<link rel="stylesheet"  href="css/select2/select2-bootstrap.css" />
<!-- Custom CSS for AngularStrap : use by modal alerts -->
<link rel="stylesheet"  href="http://mgcrea.github.io/angular-strap/styles/libraries.min.css" />
<link rel="stylesheet"  href="css/custom-checkboxes.css">

<script src="js/vendor/jquery.min.js"></script>
<script	src="js/vendor/bootstrap.min.js"></script>
<script src="js/angular/angular.min.js"></script>
<script src="js/angular/angular-route.js"></script>
<script src="js/angular/angular-strap.min.js"></script>
<script src="js/angular/angular-strap.tpl.min.js"></script>
<script src="js/select2/select2.min.js"></script>
<script src="js/select2/select2-angular.js"></script>


<!--Accordian -->
<script src="js/angular/ui-bootstrap-custom-0.12.0.min.js"></script>
<script src="js/angular/ui-bootstrap-custom-tpls-0.12.0.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.3.15/angular-sanitize.min.js"></script>

<script src="//tinymce.cachefly.net/4.1/tinymce.min.js"></script>
<script src="js/tinymce/angular-tinymce.js"></script>






<!--1. Basic Angular App -->
<script src="js/angular/testit-app.js" type="text/javascript"></script>

<!--2. Define the controllers and services here : AFTER BASIC APP IS INITIALIZED -->
<script src="js/services/StudentService.js" type="text/javascript"></script>

<script src="js/angular/controllers/HomeCtrl.js" type="text/javascript"></script>
<script src="js/angular/controllers/StudentCtrl.js"	type="text/javascript"></script>
<script src="js/angular/controllers/TestCtrl.js" type="text/javascript"></script>
<script src="js/angular/controllers/QuestionCtrl.js" type="text/javascript"></script>

<!--3. Define routes here after 1. App, 2. Controller and Services are defined -->
<script src="js/angular/routes.js" type="text/javascript"></script>

</head>
<body ng-app="testit" style="padding: 0px;">
	<div class="container-fluid" style="padding: 0px;" ng-controller="NavCtrl">

		<nav class="navbar navbar-inverse" style="margin-bottom: 0px;"navigation">
			<!-- START-------Header -->
			<a class="navbar-brand" href="#">TEST QUBE</a>
			<!-- END-------Header -->

			<!-- START-------Remaining NavBar -->


			<ul class="nav navbar-nav navbar-left">
				<!-- Item 1 -->
				<li ng-repeat="item in menu " class="dropdown"
					ng-class="{'open': selectedMenuIndex===$index}"><a
					ng-click="openSubmenu($index)">{{item.name}}</a></li>
			</ul>
			<!-- END - Items on the left -->

			<!-- START - Items on the right -->

			<ul class="nav navbar-nav navbar-right" style="margin-right: 0px;">
				<li class="dropdown"><a href="" class="dropdown-toggle"
					data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span><b
						class="caret"></b></a> <span class="dropdown-arrow"></span>
					<ul class="dropdown-menu">
						<li><a href="#">Welcome :<sec:authorize access="isAuthenticated()"/> <sec:authentication property="principal" /> !!! </a></li>
						<li><a href="#">My Preferences</a></li>
						<li class="divider"></li>
						<li><a href="/do/logout">Logout</a></li>
					</ul></li>

			</ul>

			<form class="navbar-form navbar-right" action="#" role="search">
				<div class="form-group">
					<div class="input-group">
						<input class="form-control" id="navbarInput-01" type="search"
							placeholder="Search"> 
							 <div class="input-group-addon">
							 	<a href="">
							 		<span class="glyphicon glyphicon-search"></span>
							 	</a>
							 </div>
								
							
						
					</div>
				</div>
			</form>




			<!-- START - Items on the right -->

			<!-- /.navbar-collapse -->
			<!-- START-------Remaining NavBar -->


		</nav>
		<!-- /navbar -->
		<nav class="navbar navbar-inverse navbar-embossed"
			style="border-radius: 0 0 6px 6px; margin-top: -5px;"
			role="navigation">



			<ul class="nav navbar-nav navbar-left">

				<li ng-repeat="item in selectedSubmenu"
					ng-class="{'open': selectedSubMenuIndex===$index}"><a
					href="{{item.url}}" ng-click=selectSubMenu($index)>{{item.name}}
						<span class="badge" ng-show="item.count">{{item.count}}</span>
				</a></li>


			</ul>


		</nav>


	</div>

	<div ng-view class="container"></div>
	</div>
</body>


</html>

