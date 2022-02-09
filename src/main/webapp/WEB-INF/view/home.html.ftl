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
                <div class="row" id="service-ctn">
                    <div class="col-lg-12">
                        <h2 class="text-center" id="title" style="margin-bottom: 10px">DỊCH VỤ</h2>
                    </div>
                    <div id="services">
                        <div class="col-lg-12">
                            <div class="row">
                            
                            	<#-- services loop -->
                                <div class="col-lg-12 text-center">
		                                <#list menuServices as service>
			                                <#if service?index % 4 = 0>
			                                	<ul style="padding-top: 10px">
			                                </#if>
					                    	<li class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
					                    		<div class="card" style="display:inline-block">
													<img src="${service.image}" width=180 height=180 class="card-img-top" alt="${service.name}">
												    <div class="card-body">
												        <h5 class="card-title">${service.name}</h5>
												        <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
												        <a style="font-family: Quicksand-Bold" href="service/${service.url}">Xem chi tiết →</a>
												    </div>
	                                            </div>
					                    	</li>
					                    	<#if (service?index + 1) % 4 = 0>
			                                	</ul>
			                                </#if>
				                    	</#list>    
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                
                <div class="row" id="service-ctn" style="padding-top: 50px">
                    <div class="col-lg-12 col-xs-12" id="what-we-bring" style="margin-bottom: 10px;">
                        <h2 class="text-center" id="title" style="margin-bottom: 10px">CẢM NHẬN CỦA KHÁCH HÀNG</h2>
                    </div>
                    <div class="col-lg-12 col-xs-12">
	                    <ul style="margin-bottom: 20px">
	                    	<li class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
	                    		<div class="card" style="width: 100%; padding: 0">
									<img style="width: 100%" src="https://mypetswebsitebucket.s3.ap-southeast-1.amazonaws.com/4.jpg" class="card-img-top" alt="">
								    <div class="card-body">
								        <h5 class="card-title">My name</h5>
								       <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
								    </div>
                                </div>
	                    	</li>
	                    	<li class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
	                    		<div class="card" style="width: 100%; padding: 0">
									<img style="width: 100%" src="https://mypetswebsitebucket.s3.ap-southeast-1.amazonaws.com/4.jpg" class="card-img-top" alt="">
								    <div class="card-body">
								        <h5 class="card-title">My name</h5>
								        <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
								    </div>
                                </div>
	                    	</li>
	                    </ul>
	                    <ul style="margin-bottom: 20px">
	                    	<li class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
	                    		<div class="card" style="width: 100%; padding: 0">
									<img style="width: 100%" src="https://mypetswebsitebucket.s3.ap-southeast-1.amazonaws.com/4.jpg" class="card-img-top" alt="">
								    <div class="card-body">
								        <h5 class="card-title">My name</h5>
								       <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
								    </div>
                                </div>
	                    	</li>
	                    	<li class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
	                    		<div class="card" style="width: 100%; padding: 0">
									<img style="width: 100%" src="https://mypetswebsitebucket.s3.ap-southeast-1.amazonaws.com/4.jpg" class="card-img-top" alt="">
								    <div class="card-body">
								        <h5 class="card-title">My name</h5>
								        <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
								    </div>
                                </div>
	                    	</li>
	                	</ul>     
                    </div>
                </div>
                
                <#-- Outstanding services -->
                <div class="row" id="outstand-service-ctn" style="padding-top: 50px">
                    <div class="col-lg-12 col-xs-12" id="what-we-bring" style="margin-bottom: 10px;">
                        <h2 class="text-center" id="title" style="margin-bottom: 10px">TIN TỨC</h2>
                    </div>
                    
                    <#-- outstanding services loop -->
                    <div class="col-lg-12 col-xs-12">
	                        <#list postDtos as service>
	                        	<#if service?index % 4 = 0>
			                        <ul style="padding-top: 10px">
			                    </#if>
		                    	<li class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
		                    		<div class="card" style="display:inline-block">
										<img src="${service.image}" width=180 height=180 class="card-img-top" alt="${service.name}">
									    <div class="card-body">
									        <h5 class="card-title">${service.name}</h5>
									        <a style="font-family: Quicksand-Bold" href="${service.url}">Xem chi tiết →</a>
									    </div>
                                    </div>
		                    	</li>
		                    	<#if (service?index + 1) % 4 = 0>
			                         </ul>
			                    </#if>
	                    	</#list>    
                    </div>

                    <div class="col-lg-12 col-xs-12" style="margin-bottom: 20px; margin-top: 30px">
                    	<div class="row center-block">
                    		<button class="btn btn-default" id="btn-news"><@spring.message "menu.news"/></button>
                    	</div> 
                    </div>
                </div>
                

                
                <div class="row" id="outstand-service-ctn" style="padding-top: 50px">
                    <div class="col-lg-12 col-xs-12" id="what-we-bring" style="margin-bottom: 10px;">
                        <h2 class="text-center" id="title" style="margin-bottom: 10px">BẠN CẦN TƯ VẤN</h2>
                        <h5 class="text-center" id="title" style="margin-bottom: 10px">Vui lòng điền thông tin, chúng tôi sẽ liên hệ quý khách trong ngày</h2>
                    </div>
                    
                    <#-- outstanding services loop -->
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