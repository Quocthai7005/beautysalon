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
						<form class="question-form" id="question-form" action="booking/create">
							<input name="status" type="hidden">
							<input name="id" type="hidden">
							<div class="form-group">
								<label for="name">Họ tên</label>
								<input required name="name" type="text" class="form-control" id="name">
							</div>
							<div class="form-group">
								<label for="email">Địa chỉ email</label>
								<input name="email" type="email" class="form-control" id="email" placeholder="name@example.com">
							</div>
							<div class="form-group">
								<label for="phone">Số điện thoại</label>
								<input required name="phone" type="number" class="form-control" id="phone">
							</div>
							<div class="form-group">
								<label for="datepicker">Ngày hẹn</label>
								<div class='input-group date' id='datepicker'>
									<input required type="text" id="processdate" class="form-control" placeholder="2022-03-02" name="consultDate" /> <span class="input-group-addon btn"><i class="glyphicon glyphicon-calendar"></i> </span>
								</div>
							</div>
							<div class="form-group">
								<label for="question">Câu hỏi tư vấn</label>
								<textarea name="question" class="form-control" id="question" rows="3"></textarea>
							</div>
							<button type="submit" class="btn btn-primary">Gửi câu hỏi</button>
						</form>
					</div>
				</div>
			</div>  
		</div>
		<#include "footer.html.ftl">
	</body>
</html>