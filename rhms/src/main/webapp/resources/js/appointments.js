$(document).ready()
{
	$("#from").datepicker();
	$("#to").datepicker();
}

function getAddingAppointment(doctorId) {
	console.log("getAddingAppointment->fired");
	console.log("doctorId=" + doctorId);
	$.ajax({
		type : "GET",
		url : $$ContextURL + "/appointments/add/doctors/" + doctorId,
		contentType : "application/json",
		success : function(response) {
			$("#modal-body").html(response);
			$("#modal").modal("show");
		},
		error : function(response) {
			$("#modal-body").html(response.responseText);
			$("#modal").modal("show");
		}
	});

}

function getEditingAppointment(id) {
	console.log("getEditingAppointment->fired");
	console.log("id=" + id);
	$.ajax({
		type : "GET",
		url : $$ContextURL + "/appointments/edit/" + id,
		contentType : "application/json",
		success : function(response) {
			$("#modal-body").html(response);
			$("#modal").modal("show");
		},
		error : function(response) {
			$("#modal-body").html(response.responseText);
			$("#modal").modal("show");
		}
	});

}

function deleteAppointment(id) {
	console.log("deleteAppointment->fired");
	console.log("id=" + id);

	$.when(cusConfirm()).done(function() {
		$.ajax({
			url : $$ContextURL + "/appointments/delete/" + id,
			type : 'POST',
			success : function(response) {
				$("#modal-body").html(response);
				$("#modal").modal("show");
			},
			error : function(response) {
				$("#modal-body").html(response.responseText);
				$("#modal").modal("show");
			}
		});
	});

}