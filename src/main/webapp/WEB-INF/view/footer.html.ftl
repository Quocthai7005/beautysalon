<div class="container-fluid">
	<div class="fb-customerchat" page_id="377855302704456" minimized="true"></div>
	<script>
	  window.fbAsyncInit = function() {
	    FB.init({
	      appId            : '912333495590130',
	      autoLogAppEvents : true,
	      xfbml            : true,
	      version          : 'v2.11'
	    });
	  };
	(function(d, s, id){
	     var js, fjs = d.getElementsByTagName(s)[0];
	     if (d.getElementById(id)) {return;}
	     js = d.createElement(s); js.id = id;
	     js.src = "https://connect.facebook.net/en_US/sdk.js";
	     fjs.parentNode.insertBefore(js, fjs);
	   }(document, 'script', 'facebook-jssdk'));
	</script>
	<#-- Contact -->
	<div class="row" id="page-footer">
		<#--<div class="col-lg-1 hidden-xs">
			<img src="" width="80">
		</div>-->
	    <div class="col-lg-12 col-xs-12" id="address-text">
	        <h2 style="margin-bottom: 10px">${pageTexts[3].title}</h2>
	        <p><a href="${pageTexts[5].content}" id="facebook-site"><i class="fa fa-facebook" aria-hidden="true"></i> https://www.facebook.com/skincareDrDuong</a></p>
	        <p><span class="glyphicon glyphicon-home"></span>&nbsp;&nbsp; ${pageTexts[3].content}</p>
	        <p><span class="glyphicon glyphicon-phone-alt"></span>&nbsp;&nbsp; ${pageTexts[4].content}</p>
	    </div>
	    <#-- <div class="col-lg-1 hidden-xs">
			<img src="" width="80">
		</div> -->
	</div>
</div>