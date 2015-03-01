app.config(['$routeProvider', function($routeProvider) {

	$routeProvider.when('/', {
		controller : 'HomeCtrl',
		templateUrl : 'views/home.html',
		
	}).when('/home', {
		controller : 'HomeCtrl',
		templateUrl : 'views/home.html',
		
	}).when('/admin/students', {
		controller : 'StudentCtrl',
		templateUrl : 'views/student/list.html'			
			
	}).when('/admin/students/enroll', {
		controller : 'StudentCtrl',
		templateUrl : 'views/student/add.html'
			
	}).when('/admin/students/pending', {
		controller : 'StudentCtrl',
		templateUrl : 'views/student/pendingEnrollment.html'
			
	}).when('/admin/students/:studentId', {
		controller : 'StudentCtrl',
		templateUrl : 'views/student/view.html'
			
	}).when('/admin/students/:studentId/:mode', {
		controller : 'StudentCtrl',
		templateUrl : 'views/student/view.html'
			
	}).when('/admin/tests/create', {
		controller : 'TestCtrl',
		templateUrl : 'views/test/add.html',
		
	}).when('/admin/tests/:testId', {
		controller : 'TestCtrl',
		templateUrl : 'views/test/view.html',
		
	}).when('/admin/tests', {
		controller : 'TestCtrl',
		templateUrl : 'views/test/list.html',
		
	}).when('/admin/tests/incomplete', {
		controller : 'TestCtrl',
		templateUrl : 'views/test/listIncomplete.html'
			
	}).when('/admin/tests/:testId/questions/add', {
		controller : 'QuestionCtrl',
		templateUrl : 'views/test/question/add.html',
		
	});
} ]);
