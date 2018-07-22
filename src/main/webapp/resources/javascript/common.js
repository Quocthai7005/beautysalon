$(document).ready(function() {
	
	var contextPath = $('#root-context').val();
	var messengerUrl = contextPath + '/messenger/info';
	
	$.ajax({
		type : "GET",
		url : messengerUrl,
		success : function(res) {
			if (res.code == 200) {
				$('.fb-customerchat').attr('page_id', res.data.pageId);
				$('.fb-customerchat').attr('minimized', res.data.minimized);
				window.fbAsyncInit = function() {
				    FB.init({
				      appId            : res.data.appId,
				      autoLogAppEvents : res.data.autoLogAppEvents == 'true' ? true: false,
				      xfbml            : res.data.xfbml == 'true' ? true: false,
				      version          : res.data.version
				    });
				  };
				(function(d, s, id){
				     var js, fjs = d.getElementsByTagName(s)[0];
				     if (d.getElementById(id)) {return;}
				     js = d.createElement(s); js.id = id;
				     js.src = "https://connect.facebook.net/en_US/sdk.js";
				     fjs.parentNode.insertBefore(js, fjs);
				   }(document, 'script', 'facebook-jssdk'));
			}
		}, error: function(e) {
			console.log(e);
		}
	});
})

