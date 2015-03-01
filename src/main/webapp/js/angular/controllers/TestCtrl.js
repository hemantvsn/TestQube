app.controller("TestCtrl", ['$scope', 
                            '$window',
                            '$location',
                            'TestService',
                            '$routeParams',
                            '$modal',
	function($scope, $window, $location, TestService, $routeParams, $modal) {
	
	$scope.testTypes=TestService.testTypes;
	$scope.tests=TestService.tests;
	$scope.testId = $routeParams.testId;
	$scope.test =TestService.getTestById($scope.testId);
	if($scope.test!=null)
	$scope.testType =TestService.getTestTypeById($scope.test.type);
	
	
	$scope.populateOptions = function(){
		var id=$scope.test.type;
		
		for(var i=0;i<$scope.testTypes.length;i++){
			if($scope.testTypes[i].id == id){
				$scope.testType =$scope.testTypes[i];
				break;
			}
		}
	};
	
	$scope.addTest =function(){
		$scope.test.id = Math.floor((Math.random() * 1000) + 1);
		$scope.tests.push($scope.test);
		$scope.test={};
	};
	
	$scope.removeTest =function(index){
		$scope.tests.splice(index, 1);
		
	};
	
}
	
]);