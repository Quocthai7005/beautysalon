<!DOCTYPE html>
<html>   
	<head>
		<#include "headerinc.html.ftl">
		<link rel="stylesheet" type="text/css" href="<@spring.url '/resources/css/home.css'/>"/>
		<script src="<@spring.url '/resources/javascript/booking.js'/>"></script>
		<#import "/spring.ftl" as spring/>
	</head>
	<body>
		<input type="hidden" id="root-context" value="${rootContext.getContextPath()}"></input>
		<#-- Navigation bar -->
		<#include "nav.html.ftl">
		<div id="main">	   
			<div class="container" style="background-color: #fff">   
				<#-- services -->
				<div class="row" id="contact-ctn">
					<div class="col-lg-12 col-xs-12" id="what-we-bring" style="margin-bottom: 10px;margin-top:20px">
						<h2 class="text-center" id="title" style="margin-bottom: 10px"><@spring.message "menu.booking"/></h2>
						<br>
						<h4>You have successfully booked your appointment, if you typed email, please check confirmation email</h4>
						<br>
						<h4>We will also contact you for reconfirmation.</h4>
						<br>

						<a href="${rootContext.getContextPath()}/booking" style="width: 150px" class="center-block btn btn-primary">Back to Booking</a>
					</div>
				</div>
			</div>  
		</div>
		<#include "footer.html.ftl">
	</body>
</html>