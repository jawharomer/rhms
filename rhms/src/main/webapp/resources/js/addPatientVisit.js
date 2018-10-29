$(document).ready()
{
	$("#visitDate").datepicker().datepicker("setDate", new Date());
}

// Angular
app = angular.module("addPatientVisit", []);

app.controller('addPatientVisit', function($scope, $http) {

	$scope.patientVisit;
	$scope.doctors;

	$scope.init = function() {
		console.log("init->fired");
		console.log("jsonPatientVisit=", jsonPatientVisit);
		$scope.patientVisit = JSON.parse(jsonPatientVisit);

		console.log("jsonDoctors=", jsonDoctors);
		$scope.doctors = JSON.parse(jsonDoctors);
	}

	$scope.getTotalPrice = function() {

	}

	$scope.save = function() {
		console.log("save->fired");
		console.log("$scope.patient=", $scope.patient);

		$http({
			method : 'POST',
			data : $scope.patient,
			url : $$ContextURL + '/patients/add'
		}).then(function(response) {
			$("#modal-body").html(response.data);
			$("#modal").modal("show");
		}, function(response) {
			console.log(response);
			document.open();
			document.write(response.data);
			document.close();
		});

	}

});
