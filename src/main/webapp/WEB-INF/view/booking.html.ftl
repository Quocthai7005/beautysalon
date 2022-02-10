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
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center" style="align-items:center">
						<div style="min-width: 200px; max-width: 500px">
							<form>
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
								<div class="form-group form-inline">
									<div class="form-check">
										<input class="form-check-input" type="radio" name="exampleRadios" id="phone-consult" value="phone" checked>
										<label class="form-check-label" for="phone-consult">
											Tư vấn qua điện thoại
										</label>
									</div>
									<div class="form-check">
										<input class="form-check-input" type="radio" name="exampleRadios" id="exampleRadios2" value="onsite">
										<label class="form-check-label" for="onsite-consult">
											Tư vấn tại phòng khám
										</label>
									</div>
								</div>
								<div class="form-group">
									<label for="question">Câu hỏi tư vấn</label>
									<textarea class="form-control" id="question" rows="3"></textarea>
								</div>
								<button type="submit" class="btn btn-primary">Gửi câu hỏi</button>
							</form>
						</div>
					</div>
				</div>
			</div>  
		</div>
		<#include "footer.html.ftl">
	</body>
</html>