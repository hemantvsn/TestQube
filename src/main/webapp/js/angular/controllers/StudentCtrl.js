app.controller('StudentCtrl', [ '$scope', 
                                '$window',
                                '$location',
                                'StudentService',
                                '$routeParams',
                                '$modal',
		function($scope, $window, $location, StudentService, $routeParams, $modal) {

			$scope.students = StudentService.students;
			$scope.userId = $routeParams.studentId;
			$scope.student = StudentService.getStudentById($scope.userId);

			if ($scope.student != null) {
				$scope.disabled = $routeParams.mode == 'edit' ? false : true;
			}

			
		

			$scope.addStudent = function() {
				$scope.student.id = Math.floor((Math.random() * 1000) + 1);
				$scope.students.push($scope.student);
				$scope.student = {};
			};

			$scope.removeStudent = function(index) {
				$scope.students.splice(index, 1);

			};

			$scope.updateStudent = function() {

				$location.path('/admin/students');

			};

		} ]);
