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
                        <h2 class="text-center" id="title" style="margin-bottom: 10px">${pageTexts[2].title}</h2>
                        <p class="text-center">${pageTexts[2].content}</p>
                    </div>
                    <div id="services">
                        <div class="col-lg-12">
                            <div class="row">
                            
                            	<#-- services loop -->
                                <div class="col-lg-12 text-center">
	                                <ul>
		                                <#list menuServices as service>
				                    	<li class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
				                    		<div class="service" data-url="${service.url}">
				                    			<div class="service-text">
				                    				<span>${service.name}</span>
				                    			</div>
				                    			<div class="service-img-ctn">
				                    				<img src="${'data:image/png;base64,' + service.image}" width="200" height="200" alt="${service.name}">
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
                
                <#-- Outstanding services -->
                <div class="row" id="outstand-service-ctn" style="padding-top: 50px">
                    <div class="col-lg-2 col-xs-3">
                        <img src="<@spring.url '/resources/image/other/olivetree.jpg'/>" alt="facility" style="width: 80%">
                    </div>
                    <div class="col-lg-8 col-xs-6" id="what-we-bring" style="margin-bottom: 10px;">
                        <h2 class="text-center" id="title" style="margin-bottom: 10px">${pageTexts[1].title}</h2>
                        <p class="text-center">${pageTexts[1].content}</p>
                    </div>
                    <div class="col-lg-2 col-xs-3">
                        <img src="<@spring.url '/resources/image/other/olivetree.jpg'/>" alt="facility" style="width: 80%">
                    </div>
                    
                    <#-- outstanding services loop -->
                    <div class="col-lg-12 col-xs-12">
	                    <ul>
	                        <#list childServiceDtos as service>
	                    	<li class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
	                    		<div class="outstand-service thumbnail" data-url="${service.url}">
	                    			<div class="outstand-service-img-ctn">
	                    				<img src="${'data:image/png;base64,' + service.image}" width="200" height="200" alt="${service.name}">
	                    			</div>
	                    			<div class="outstand-service-intro">
	                    				<span>${service.intro}</span>
	                    			</div>
	                    		</div>
	                    	</li>
	                    	</#list>
	                	</ul>     
                    </div>

                    <div class="col-lg-12 col-xs-12" style="margin-bottom: 20px">
                        <button class="btn btn-default" id="btn-news"><@spring.message "menu.news"/></button>
                    </div>
                </div>
            </div>  
        </div>
        <#include "footer.html.ftl">
    </body>
</html>