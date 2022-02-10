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
			<div class="container" style="background-color: #fff">   
				<#-- services -->
				<div class="row" id="contact-ctn">
					<div class="col-lg-12 col-xs-12" id="what-we-bring" style="margin-bottom: 10px;">
						<h2 class="text-center" id="title" style="margin-bottom: 10px">ĐẶT LỊCH</h2>
						<h5 class="text-center" id="title" style="margin-bottom: 10px">Vui lòng điền thông tin, chúng tôi sẽ liên hệ quý khách trong ngày</h2>
					</div>
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
						<form class="question-form">
							<div class="form-group">
								<label for="name">Họ tên</label>
								<input type="email" class="form-control" id="name">
							</div>
							<div class="form-group">
								<label for="email">Địa chỉ email</label>
								<input type="email" class="form-control" id="email" placeholder="name@example.com">
							</div>
							<div class="form-group">
								<label for="phone">Số điện thoại</label>
								<input type="email" class="form-control" id="phone">
							</div>
							<div class="form-group">
								<label for="datepicker">Ngày hẹn</label>
								<div class='input-group date' id='datepicker'>
									<input type="text" id="processdate" class="form-control" placeholder="Processing Date..." name="processdate" /> <span class="input-group-addon btn"><i class="glyphicon glyphicon-calendar"></i> </span>
								</div>
							</div>
							<div class="form-group">
								<label for="question">Câu hỏi tư vấn</label>
								<textarea class="form-control" id="question" rows="3"></textarea>
							</div>
							<button type="submit" class="btn btn-primary">Đặt lịch hẹn</button>
						</form>
					</div>
				</div>
			</div>  
		</div>
		<#include "footer.html.ftl">
	</body>
</html>