$(document).ready(function() {
	
	var servicePage = new Service();
	servicePage.rootContext = $('#root-context').val();
	servicePage.initChildService();
	servicePage.initService();
});

function Service() {
	this.rootContext = '';
}

Service.prototype.initService = function() {
	var that = this;
	$('.other-services').on('click', function() {
		var url = $(this).data('url');
		window.location.href = that.rootContext + '/service/' + url;
	})
}

Service.prototype.initChildService = function() {
	var that = this;
	$('.service').on('click', function() {
		var url = $(this).data('url');
		var parentUrl = $(this).data('parentUrl');
		window.location.href = that.rootContext + '/service/' + parentUrl + '/' + url;
	})
}