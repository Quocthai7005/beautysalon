$( document ).ready(function() {
	
    var home = new Home();
    home.rootContext = $('#root-context').val();
    
    home.initService();
    home.initOutstandService();
    home.initNewsBtn();
	window.onscroll = function() {scrollFunction()};
	var viewport = $(window).width();
	console.log(viewport);
	if (viewport < 550) {
		$("#navbar-home-scroll").css("display", "none")
		$("#navbar-scroll").css("display", "")
	} else {
		$("#navbar-home-scroll").css("display", "")
	}

	$('#datepicker').datepicker({
          showOn: "button",
          buttonImage: "https://jqueryui.com/resources/demos/datepicker/images/calendar.gif",
          buttonImageOnly: true,
          buttonText: "Select appointment date time",
          autoclose: true,
		  format: 'yyyy-mm-dd'
	});
	
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
			"question": $('textarea[name="question"]').val()
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

	function scrollFunction() {

		if (document.documentElement.scrollTop > 200) {
			$("#navbar-scroll").css("display", "")
		} else if (viewport < 550) {
			$("#navbar-scroll").css("display", "");
		} else {
			$("#navbar-scroll").css("display", "none");
		}
	}
});
function Home() {
	var rootContext = '';
}



Home.prototype.initService = function() {
	var that = this;
	$('.service').on('click', function() {
    	const serviceName = $(this).attr('data-url');
    	const url = that.rootContext + '/service/' + serviceName;
    	window.location.href = url;
    });
}

Home.prototype.initOutstandService = function() {
	$('.outstand-service').on('click', function() {
		var url = $(this).data('url');
		var rootUrl = window.location.href;
		rootUrl = rootUrl.slice(0, rootUrl.lastIndexOf('/'));
		window.location.href = rootUrl + '/service/' + url;
    });
}
Home.prototype.initNewsBtn = function() {
	var that = this;
	$('#btn-news').on('click', function() {
		window.location.href = that.rootContext + '/news';
	})
}