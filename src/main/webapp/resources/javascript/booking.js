$( document ).ready(function() {
	var rootContext = $('#root-context').val();
	var dateToday = new Date();
	$('#datepicker').datepicker({
		showOn: "button",
		buttonImage: "https://jqueryui.com/resources/demos/datepicker/images/calendar.gif",
		buttonImageOnly: true,
		buttonText: "Select appointment date time",
		autoclose: true,
		daysOfWeekDisabled: [0],
		format: 'yyyy-mm-dd',
		startDate:'+0d'
	}).on("changeDate", function (e) {
		var date = $(this).datepicker('getDate');
		var day = date.getUTCDay();
		console.log(day);
		var hour = $('select[name="hour"]').val();
		var minute = $('select[name="minute"]').val();
		if (day == '5') {

			if (hour == 19) {
				$('select[name="hour"]').val("18");
				$('select[name="minute"]').val("00");
			} if (hour == 18) {
				$('select[name="minute"]').val("00");
			}
			$('option[value="19"]').prop("disabled", true);
		} else {
			$('option[value="19"]').prop("disabled", false);
		}
	});
	$('select[name="hour"]').on("change", function() {
		var hour = $('select[name="hour"]').val();
		if (hour == 9) {
			$('select[name="minute"]').val(30);
			$('option[value="00"]').prop("disabled", true);
		} else {
			$('option[value="00"]').prop("disabled", false);
		}
	})

	$('select[name="hour"]').val()
	$("#selectServices").selectpicker();
	$("#question-form").submit(function(event) {
		$("#submit-btn").prop("disabled",true);
		event.preventDefault();
		var actionurl = event.currentTarget.action;
		console.log( $('input[name="question"]').val());
		var formData = {
			"status": null,
			"id": null,
			"name": $('input[name="name"]').val(),
			"email": $('input[name="email"]').val(),
			"phone": $('input[name="phone"]').val(),
			"consultDate": $('input[name="consultDate"]').val() + 'T00:00:00',
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
				alert('You booked your schedule! We will contact you for confirmation soon');
				$('input[name="name"]').val("");
				$('input[name="email"]').val("");
				$('input[name="phone"]').val("");
				$('input[name="consultDate"]').val("");
				$('textarea[name="question"]').val("");
				window.location = rootContext + "/booking/booking-success";
			}
		});
	});
});