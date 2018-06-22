<!DOCTYPE html>
<html>   
	<head>
        <#include "headerinc.html.ftl">
        <link rel="stylesheet" type="text/css" href="<@spring.url '/resources/css/service.css'/>"/>
        <script src="<@spring.url '/resources/javascript/service.js'/>"></script>
        <#import "/spring.ftl" as spring/>
    </head>
    <body>
    	<input type="hidden" id="root-context" value="${rootContext.getContextPath()}"></input>
        <#-- Navigation bar -->
        <div id="main-service">
            <#include "nav.html.ftl">
            <#include "carousel.html.ftl">

            <div class="container">
            	<div class="row">
                    <div class="col-lg-12" id="header-service-ctn">
                    	<div id="title">
                    		<span>${service.name}</span>
                    	</div>
                    	<div class="line"></div>
                    </div>
                </div>
                
            	<#-- services -->
                <div class="row" id="child-service-ctn">
            
		            <#-- services loop -->
		            <div class="col-lg-12 text-center">
		                <ul>
		                    <#list service.childServices as childService>
		                	<li class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
		                		<div class="service thumbnail" data-url="${childService.url}" data-parent-url="${service.url}">	
		                			<div class="service-img-ctn">
		                				<img src="${'data:image/png;base64,' + childService.image}" width="200" height="200" alt="${childService.name}">
		                			</div>
		                			<div class="service-text">
		                				<span>${childService.name}</span>
		                			</div>	
		                		</div>
		                	</li>
		                	</#list>
		            	</ul>        
		            </div>
            	</div>
            	
            	<div class="row">
                    <div class="col-lg-12" id="header-other-services-ctn">
                    	<div id="title">
                    		<span><@spring.message "menu.other.interest"/></span>
                    	</div>
                    	<div class="line"></div>
                    </div>
                </div>
                
                <div class="row" id="recommend-service-list">
                	<div class="col-lg-12">
                		<ul>
	                        <#list otherServices as service>
	                    	<li class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
	                    		<div class="thumbnail other-services" data-url="${service.url}">
	                    			<div class="other-services-img-ctn">
	                    				<img src="${'data:image/png;base64,' + service.image}" width="200" height="200" alt="${service.name}">
	                    			</div>
	                    			<div class="service-text">
	                    				<strong><span>${service.name}</span></strong>
	                    			</div>
	                    		</div>
	                    	</li>
	                    	</#list>
	                	</ul>   
                	</div>
                </div>
            </div>         
        </div>
        <#include "footer.html.ftl">
    </body>
</html>