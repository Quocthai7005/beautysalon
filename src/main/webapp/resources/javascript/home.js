$( document ).ready(function() {
	
    var home = new Home();
    home.rootContext = $('#root-context').val();
    
    home.initService();
    home.initOutstandService();
    home.initNewsBtn();

	$('#datepicker').datepicker({
          showOn: "button",
          buttonImage: "https://jqueryui.com/resources/demos/datepicker/images/calendar.gif",
          buttonImageOnly: true,
          buttonText: "Select date",
          autoclose: true
	});
	
	$("#question-form").submit(function(event) {
		event.preventDefault();
		var actionurl = event.currentTarget.action;
		var formData = $("#question-form").serialize();
		$.ajax({
			url: actionurl,
			type: 'post',
			contentType: 'application/json;charset=UTF-8',
			data: formData,
			success: function(data) {
	
			}
		});
	});
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