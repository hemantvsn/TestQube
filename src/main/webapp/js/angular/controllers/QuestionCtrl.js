app.controller("QuestionCtrl", ['$scope', 
                            '$window',
                            '$location',
                            'TestService',
                            '$routeParams',
                            '$modal',
                            '$alert',
	function($scope, $window, $location, TestService, $routeParams, $modal, $alert) {
	
	
    var saveGuideDraftAlert = $alert({
    	title : '',
    	content : 'Question has been saved.',
    	placement : 'top-right',
    	type : 'success',
    	keyboard : true,
    	show : false,
    	duration : "3",
    	animation : "am-fade-and-slide-top",
    	dismissable : false
        });
	
	$scope.testId = $routeParams.testId;
	$scope.test =TestService.getTestById($scope.testId);
	$scope.testType =TestService.getTestTypeById($scope.test.type);
	
	$scope.totalQuestions= $scope.testType.questions;
	
	
	if($scope.test.questions==null){
		$scope.test.questions =[];
	}
	
	$scope.completed =($scope.test.questions.length/ $scope.totalQuestions) * 100;
	
	if($scope.test!=null) {
		$scope.testType =TestService.getTestTypeById($scope.test.type);
	}
		
	$scope.question ={};
	
	$scope.addQuestion = function(){
		if(validateQuestion()) {
		$scope.test.questions.push($scope.question);
		$scope.completed =($scope.test.questions.length/ $scope.totalQuestions) * 100;
		$scope.question ={};
		saveGuideDraftAlert.show();
		
		 
		}
	};
	
	function validateQuestion() {
		if($('form[name="myForm"]').hasClass('ng-invalid') || ($scope.question.correctOption == null)){
			alert("Please Fill all the required fields");
			return false;
		}
		return true;
	};
	
	
	
}
	
]);