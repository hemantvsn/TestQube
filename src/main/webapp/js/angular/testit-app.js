var app = angular.module("testit", ['ngRoute','mgcrea.ngStrap','ui.select2','ui.bootstrap','testit.services']);
app.controller("NavCtrl", function($scope) {

	$scope.menu = [ {
		name : "Home",
		submenu : [{
			name: "Home",
			url: "#home"		
		}, {
			name: "Home2",
			url:"#"}]
	}, {
		name : " Students",
		submenu : [ {
			name : "Enrolled Students",
			url : "#admin/students"
		}, {
			name : "Pending Enrollments",
			count : 5,
			url : "#admin/students/pending"
		}, {
			name : "Add Student",
			url : "#admin/students/enroll"
		} ]
	}, {
		name : "Test",
		submenu : [ {
			name : "Create Test",
			url : "#admin/tests/create"
		}, {
			name : "List Tests",
			url : "#admin/tests"
		}, {
			name : "Incomplete Draft Tests",
			url : "#admin/tests/incomplete"
		} ]
	} ];

	$scope.selectedMenuIndex = 0;
	$scope.selectedSubMenuIndex = 0;
	$scope.selectedSubmenu = $scope.menu[$scope.selectedMenuIndex].submenu;

	$scope.openSubmenu = function(index) {
		$scope.selectedSubmenu = $scope.menu[index].submenu;
		$scope.selectedMenuIndex = index;
		$scope.selectedSubMenuIndex=-1;

	};
	
	$scope.selectSubMenu = function(index){
		$scope.selectedSubMenuIndex = index;
	};

});

var servicesApp = angular.module("testit.services",[]);

