$(document).ready(function() {
	var newsDetailPage = new NewsDetail();
	newsDetailPage.rootContext = $('#root-context').val();
	newsDetailPage.initPost();
	newsDetailPage.initServiceScroll();
	newsDetailPage.initService();
})

function NewsDetail() {
	this.rootContext = '';
}

NewsDetail.prototype.initPost = function() {
	var that = this;
	// remove previous events
	$('.other-post').off();
	
	// bind new event
	$('.other-post').on('click', function() {
		const postName = $(this).attr('data-url');
    	const url = that.rootContext + '/news/post/' + postName;
    	window.location.href = url;
	})
}

NewsDetail.prototype.initServiceScroll = function() {
	var postHeight = $('#post-content').height();
	if ($(window).width() > 500) {
		$('#service-list-ctn').slimScroll({
			height: postHeight
		});
	}
}
NewsDetail.prototype.initService = function() {
	var that = this;
	$('.child-service').on('click', function() {
		const serviceName = $(this).attr('data-url');
    	const url = that.rootContext + '/service/' + serviceName;
    	window.location.href = url;
	})
}