$( document ).ready(function() {

	$('#datepicker').datepicker({
		showOn: "button",
		buttonImage: "https://jqueryui.com/resources/demos/datepicker/images/calendar.gif",
		buttonImageOnly: true,
		buttonText: "Select appointment date time",
		autoclose: true,
		format: 'yyyy-mm-dd'
	});
	$("#selectServices").selectpicker();
	$("#question-form").submit(function(event) {
		event.preventDefault();
		var actionurl = event.currentTarget.action;
		console.log( $('input[name="question"]').val());
		var formData = {
			"status": null,
			"id": null,
			"name": $('input[name="name"]').val(),
			"email": $('input[name="email"]').val(),
			"phone": $('input[name="phone"]').val(),
			"consultDate": $('input[name="consultDate"]').val() + 'T10:15:30',
			"question": $('textarea[name="question"]').val(),
			"hour": $('select[name="hour"]').val(),
			"minute": $('select[name="minute"]').val(),
			"services": $('select[name="services"]').val()
		}
		
		$.ajax({
			url: actionurl,
			type: 'post',
			contentType: 'application/json;charset=UTF-8',
			data: JSON.stringify(formData),
			success: function(data) {
				alert('Đặt lịch hẹn thành công, chân thành cảm ơn quý khách!');
				$('input[name="name"]').val("");
				$('input[name="email"]').val("");
				$('input[name="phone"]').val("");
				$('input[name="consultDate"]').val("");
				$('textarea[name="question"]').val("");
			}
		});
	});
});