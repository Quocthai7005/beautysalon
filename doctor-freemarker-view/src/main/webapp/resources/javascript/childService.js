$(document).ready(function() {
	
	var childServicePage = new ChildService();
	childServicePage.rootContext = $('#root-context').val();
	/*childServicePage.initServiceScroll();*/
	childServicePage.initRelatedChildServices();
	childServicePage.initService();
})

function ChildService() {
	this.rootContext = '';
}

// Direct to other child service
ChildService.prototype.initRelatedChildServices = function() {
	$('.other-child-services').on('click', function() {
		var url = $(this).data('url');
		var rootUrl = window.location.href;
		rootUrl = rootUrl.slice(0, rootUrl.lastIndexOf('/'));
		window.location.href = rootUrl + '/' + url;
	})
}

// Initialize slim scroll
ChildService.prototype.initServiceScroll = function() {
	var serviceHeight = $('#child-service-content').height();
	if ($(window).width() > 500) {
		$('#other-child-services-ctn').slimScroll({
			height: serviceHeight
		});
	}
}

// Direct to service
ChildService.prototype.initService = function() {
	var that = this;
	$('.other-services').on('click', function() {
		var url = $(this).data('url');
		window.location.href = that.rootContext + '/service/' + url;
	});
}