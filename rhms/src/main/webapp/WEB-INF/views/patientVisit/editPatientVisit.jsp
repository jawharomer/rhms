<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>


<script type="text/javascript">
	var jsonPatientVisit = '${jsonPatientVisit}';
	var jsonDoctors = '${jsonDoctors}';
</script>

<div  ng-app="editPatientVisit"
	ng-controller="editPatientVisit" ng-init="init()" ng-form name="form">
	<h4>Edit Patient Visit</h4>

	<div class="p-1 m-1">
		<sf:form commandName="patientVisit">
			<sf:errors path="*"></sf:errors>
		</sf:form>
	</div>

	<table id="edit-patient-visit-table">

		<tr>
			<td>Patient ID</td>
			<td>{{patientVisit.patient.id}}</td>
		</tr>

		<tr>
			<td>Full Name</td>
			<td>{{patientVisit.patient.fullName}}&nbsp;-&nbsp;{{patientVisit.patient.arabicFullName}}</td>
		</tr>

		<tr>
			<td>Doctor</td>
			<td><select required name="doctor"
				class="form-control form-control-sm" required="required"
				ng-model="patientVisit.doctor">
					<option value="">Choose</option>
					<option ng-repeat="item in doctors" ng-value="item">{{item.fullName}}</option>
			</select></td>
		</tr>

		<tr>
			<td>Visit Case</td>
			<td><input class="form-control form-control-sm" name="visitCase"
				ng-model="patientVisit.visitCase"></td>
		</tr>


		<tr>
			<td>Discount Type</td>
			<td><select  name="discountType"
				class="form-control form-control-sm" 
				ng-model="patientVisit.discountType">
					<option value="">Choose</option>
					<option value="Doctor">User</option>
					<option value="Doctor">Doctor</option>
					<option value="Doctor">Admin</option>
			</select></td>
		</tr>

		<tr>
			<td>Discount Amount</td>
			<td><input ng-disabled="!patientVisit.discountType"
				type="number" min="0" max="1" class="form-control form-control-sm"
				name="discountAmount" ng-model="patientVisit.discountAmount"></td>
		</tr>
		<tr>
			<td>Total Price</td>
			<td><input required="required" min="0" type="number"
				class="form-control form-control-sm" name="totalPrice"
				ng-model="patientVisit.totalPrice"></td>
		</tr>

		<tr ng-if="patientVisit.discountType">
			<td>Total Payment</td>
			<td><input readonly="readonly"
				class="form-control form-control-sm" ng-value="getTotalPayment()"></td>
		</tr>



		<tr>
			<td>Visit Date</td>
			<td><input required="required" readonly="readonly"
				id="visitDate" class="form-control form-control-sm" name="visitDate"
				ng-model="patientVisit.visitDate"></td>
		</tr>

		<tr>
			<td>Note</td>
			<td><input class="form-control form-control-sm" name="note"
				ng-model="patientVisit.note"></td>
		</tr>




	</table>


	<div class="p-2">
		<button class="btn btn-warning" ng-disabled="form.$invalid"
			ng-click="save()">
			<i class="fa fa-edit"></i>
		</button>

	</div>



</div>
