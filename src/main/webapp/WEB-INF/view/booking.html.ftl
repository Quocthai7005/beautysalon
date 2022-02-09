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
                	<div class="col-lg-12 col-xs-12 text-center" style="align-items:center">
                	
	                	 <div style="width:500px">
							<form>
							<div class="form-group">
								<label for="exampleFormControlInput1">Họ tên</label>
							    <input type="email" class="form-control" id="exampleFormControlInput1">
						  	</div>
							  <div class="form-group">
							    <label for="exampleFormControlInput1">Địa chỉ email</label>
							    <input type="email" class="form-control" id="exampleFormControlInput1" placeholder="name@example.com">
							  </div>
							  <div class="form-group">
							    <label for="exampleFormControlInput1">Số điện thoại</label>
							    <input type="email" class="form-control" id="exampleFormControlInput1">
							  </div>
							  <div class="form-group">
							    <label for="exampleFormControlTextarea1">Câu hỏi tư vấn</label>
							    <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
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