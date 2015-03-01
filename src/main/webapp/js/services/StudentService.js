// Restangular API implementation using $resource. 
app.factory('StudentService', function() {

	function StudentService() {
		this.students = [ {
			id : 101,
			firstname : 'Hemant',
			lastname : 'Nagpure',
			email : 'hem@gmail.com',
			sex : 'male',
			mobile : 9970290766,
			dob : '26-Mar-1988'
		}, {
			id : 102,
			firstname : 'Harshad',
			lastname : 'Nagpure',
			email : 'harshad@gmail.com',
			sex : 'male',
			mobile : 9405104446,
			dob : '17-Dec-1989'
		} ];
		
		this.getStudentById = function(id){
			for(var i=0; i<this.students.length; i++){
				if(this.students[i].id==id){
					return this.students[i];
				}
			}
		};
	};
	

	return new StudentService();

});

app.factory('TestService', function() {

	function TestService() {
		this.testTypes = [ {
			id : 1,
			name : 'CET',
			questions : 10,
			time : 180,
			positive : 1,
			negative : 0
		}, {
			id : 2,
			name : 'CMAT',
			questions : 5,
			time : 120,
			positive : 1,
			negative : -1
		}, {
			id : 3,
			name : 'GRE',
			questions : 10,
			time : 120,
			positive : 3,
			negative : -1
		} ];
		
		this.getTestTypeById = function(id){
			for(var i=0; i<this.testTypes.length; i++){
				if(this.testTypes[i].id==id){
					return this.testTypes[i];
				}
			}
		};

		this.tests = [ {
			id : 1,
			name : 'CET 101',
			type : 1
		}, {
			id : 2,
			name : 'CET FINAL',
			type : 1
		}, {
			id : 3,
			name : 'GRE FINAL',
			type : 3
		} ];
		
		this.getTestById = function(id){
			for(var i=0; i<this.tests.length; i++){
				if(this.tests[i].id==id){
					return this.tests[i];
				}
			}
		};
	};

	return new TestService();

});