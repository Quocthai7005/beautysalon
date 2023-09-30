<!DOCTYPE html>
<html>   
	<head>
		<#include "headerinc.html.ftl">
		<link rel="stylesheet" type="text/css" href="<@spring.url '/resources/css/home.css'/>"/>
		<script src="<@spring.url '/resources/javascript/home.js'/>"></script>
		<#import "/spring.ftl" as spring/>
	</head>
	<body>
		<input type="hidden" id="root-context" value="${rootContext.getContextPath()}"></input>
		<#-- Navigation bar -->
		<#include "nav.html.ftl">
		<#include "carousel.html.ftl">
		<div id="main">
			<div class="container">
				<#-- services -->
				<div class="row" style="padding-top: 20px; background-color:#ffffff; padding-bottom: 20px">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<h1 class="text-center" id="title" style="margin-bottom: 10px">WE</h1>
					</div>
					<div style="margin-top: 20px">
						<div class="col-lg-6 text-justify" style="padding-left: 50px">
							<p>Welcome to our exquisite nail spa nestled in the charming town of Holden, Massachusetts. At our establishment, we redefine the art of pampering, offering a haven of tranquility and top-notch services for your nail care needs. With a commitment to excellence, our spa boasts a serene ambiance that instantly transports you to a world of relaxation.</p>
						</div>
						<div class="col-lg-6">
							<div class="card" style="width: 60%; padding: 0">
								<img src="<@spring.url '/resources/image/Majestic/location.jpg'/>" width="95%" alt="brand" />
							</div>
						</div>
					</div>
				</div>

				<div class="row" style="margin-top: 20px; padding-bottom: 20px; border-radius: 15px;">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<h1 class="text-center" id="title" style="margin-bottom: 10px">MAKE YOU RELAXED & ATTRACTIVE</h1>
					</div>
					<div style="margin-top: 20px">
						<div class="col-lg-6">
							<div class="card" style="width: 80%; padding: 0">
								<a href="https://www.freepik.com/free-photo/woman-with-nail-art-promoting-design-luxury-earrings-ring_5588693.htm#query=nail%20banner&position=24&from_view=search&track=ais "><img src="<@spring.url '/resources/image/Majestic/homeImage1.jpg'/>" width="95%" alt="brand" /></a>
							</div>
						</div>
						<div class="col-lg-6 text-justify">
							<p>Our highly trained and experienced nail technicians are dedicated to providing you with impeccable nail treatments that leave you feeling rejuvenated and refreshed. From classic manicures and pedicures to trendy nail art and the latest in nail enhancements, we offer a wide array of services to cater to your unique style and preferences.</p>
						</div>
					</div>
				</div>

				<div class="row" style="padding-top: 20px;padding-bottom: 20px; background-color:#ffffff">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<h1 class="text-center" id="title" style="margin-bottom: 10px">WITH OUR PROFESSION</h1>
					</div>
					<div style="margin-top: 20px">
						<div class="col-lg-6 text-justify" style="padding-left: 50px">
							<p>Step into our oasis of comfort and elegance, where cleanliness and hygiene are our top priorities. Our state-of-the-art facilities and strict sanitation protocols ensure your safety and peace of mind. Whether you're seeking a quick touch-up or a full day of self-indulgence, our nail spa in Holden, MA, is your sanctuary for beauty and relaxation. Come experience the difference in nail care excellence with us.</p>
						</div>
						<div class="col-lg-6">
							<div class="card" style="width: 40%; padding: 0">
								<img src="<@spring.url '/resources/image/Majestic/inside.jpg'/>" width="95%" alt="brand" />
							</div>
							<div class="card" style="width: 40%; padding: 0">
								<img src="<@spring.url '/resources/image/Majestic/inside2.jpg'/>" width="95%" alt="brand" />
							</div>
						</div>
					</div>
				</div>
			</div>  
		</div>
		<#include "footer.html.ftl">
	</body>
</html>