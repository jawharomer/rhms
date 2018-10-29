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

	$scope.$watch('patientVisit.discountType', function() {
     if(!$scope.patientVisit.discountType){
    	 $scope.patientVisit.discountAmount="";
     }
	});

	$scope.getTotalPayment = function() {
		if ($scope.patientVisit.discountAmount
				&& $scope.patientVisit.totalPrice) {
			var x = $scope.patientVisit.discountAmount
					* $scope.patientVisit.totalPrice;
			return $scope.patientVisit.totalPrice - x;
		}

	}

	$scope.save = function() {
		console.log("save->fired");
		console.log("$scope.patientVisit=", $scope.patientVisit);
		$http({
			method : 'POST',
			data : $scope.patientVisit,
			url : $$ContextURL + '/patientVisits/add'
		}).then(function(response) {
			$("#modal-body").html(response.data);
			$("#modal").modal("show");
		}, function(response) {
			// console.log(response);
			// document.open();
			// document.write(response.data);
			// document.close();
		});

	}

});
