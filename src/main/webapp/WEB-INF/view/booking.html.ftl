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
					</div>
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
						<form class="question-form" id="question-form" action="booking/create">
							<input name="status" type="hidden">
							<input name="id" type="hidden">
							<div class="form-group">
								<label for="name">Your name</label>
								<input required name="name" type="text" class="form-control" id="name">
							</div>
							<div class="form-group">
								<label for="phone">Your phone number</label>
								<input required name="phone" type="number" class="form-control" id="phone">
							</div>
							<div class="form-group">
								<label for="email">Your email address</label>
								<input name="email" type="text" class="form-control" id="email">
							</div>
							<div class="form-group">
								<label for="datepicker">Choose a time for your appointment</label>
								<div class='input-group form-inline date' id='datepicker'>
									<input required type="text" id="processdate" class="form-control" placeholder="2023-12-01" name="consultDate" /> <span class="input-group-addon btn"><i class="glyphicon glyphicon-calendar"></i> </span>
								</div>
							</div>

							<div class="form-inline">
								<div class='form-inline date' id='datepicker'>
									<select id="hour" name="hour" class="form-control">
										<option>9</option>
										<option>10</option>
										<option>11</option>
										<option>12</option>
										<option>13</option>
										<option>14</option>
										<option>15</option>
										<option>16</option>
										<option>17</option>
										<option>18</option>
										<option value="19">19</option>
									</select>
									<select id="minute" name="minute" class="form-control">
										<option value="00" disabled="disabled">00</option>
										<option value="30" selected="selected">30</option>
									</select>
								</div>
								<small>Office hours are 9:30am to 7pm</small>
							</div>
							<div class="form-group">
								<label for="selectServices">Choose your service(s)</label>
								<select id="selectServices" name="services" class="selectpicker form-control" multiple data-live-search="true">
									<#list menuServices?keys as key>
										<h3 style="margin-top: 20px">${key}</h3>

											<#list menuServices[key] as service>
												<option value="${service.name}">${service.name}</option>
											</#list>

									</#list>
								</select>
							</div>
							<div class="form-group">
								<label for="question">Remarks if any</label>
								<textarea name="question" class="form-control" id="question" rows="3"></textarea>
							</div>
							<button type="submit" id="submit-btn" class="btn btn-primary">Book</button>
						</form>
					</div>
				</div>
			</div>  
		</div>
		<#include "footer.html.ftl">
	</body>
</html>