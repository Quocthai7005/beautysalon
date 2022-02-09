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
                	<div class="col-lg-12 col-xs-12" id="address-text">
				        <h3 style="margin-top: 20px">FACEBOOK</h3>
				        <p>https://www.facebook.com/stinajones</a></p>
				        <h3 style="margin-top: 20px">ĐỊA CHỈ</h3>
				        <p>&nbsp;&nbsp; A16 Điện Biên Phủ, P. Vĩnh Quang, TP Rạch Giá, Kiên Giang</p>
				        <h3 style="margin-top: 20px">ĐIỆN THOẠI</h3>
				        <p>0979-000-000</p>
				        <h3 style="margin-top: 20px">GIỜ LÀM VIỆC</h3>
				        <p>Thứ Hai - Thứ Bảy: 7am - 5pm</p>
				        <p>Chủ Nhật: 7am - 13pm</p>
				    </div>
                    <div class="col-lg-12 col-xs-12" id="map-ctn">
                        
                    </div>
                </div>
            </div>  
        </div>
        <#include "footer.html.ftl">
    </body>
</html>