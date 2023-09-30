<!DOCTYPE html>
<html>   
	<head>
        <#include "headerinc.html.ftl">
        <link rel="stylesheet" type="text/css" href="<@spring.url '/resources/css/childService.css'/>"/>
        <script src="<@spring.url '/resources/javascript/childService.js'/>"></script>
        <#import "/spring.ftl" as spring/>
    </head>
    <body>
    	<input type="hidden" id="root-context" value="${rootContext.getContextPath()}"></input>
        
        <div id="main-child-service">
            <#include "nav.html.ftl"> <#-- Navigation bar -->
<#--            <#include "carousel.html.ftl">-->
            <div class="container">
                <div class="row">
                	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                	<div class="row" style="padding-top: 30px">
		                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center" id="header-service-ctn">
		                    	<h2>${childService.name}</h2>
		                    </div>
		                </div>
	                    <div class="row">
		                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="child-service-detail-ctn">
		                    	<#-- <div id="child-service-title">
		                    		<h3>${childService.name}</h3>
		                    	</div> -->
		                    	<div id="child-service-content">
		                    		<span>${childService.content}</span>
		                    	</div>
	                    	</div>
	                    </div>
                    </div>
                </div>
                <div class="row">
	                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="interested">
                    	
                    	<div class="row" style="padding-top: 30px">
		                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center" id="header-service-ctn">
		                    	<h2><@spring.message "menu.other.interest"/></h2>
		                    </div>
		                </div>
		                
                    	<div class="row" style="padding-top: 30px; padding-bottom: 30px">
                    		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
		                    		<ul>
				                        <#list otherChildServices as service>
					                    	<li class="col-lg-3 col-md-6 col-sm-6 col-xs-12" style="padding-top:10px">
					                    		<div class="card">
													<img src="${service.image}" width=180 height=180 class="card-img-top" alt="${service.name}">
												    <div class="card-body">
												        <h5 class="card-title">${service.name}</h5>
												        <a style="font-family: Quicksand-Bold" href="${service.url}">Xem chi tiết →</a>
												    </div>
			                                    </div>
					                    	</li>
				                    	</#list>
				                	</ul>
			                </div>   	
                    	</div>
                    </div>
                </div>
            </div>         
        </div>
        <#include "footer.html.ftl">
    </body>
</html>