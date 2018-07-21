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
            <#include "carousel.html.ftl">
            <div class="container">
                <div class="row">
                	<div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
	                	<div class="row">
		                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="header-child-service-ctn">
		                    	<div id="title">
		                    		<span><@spring.message "header.detailInfo"/></span>
		                    	</div>
		                    	<div class="line"></div>
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
                    
	                <div class="col-lg-3 col-md-3 col-sm-3 col-xs-12" id="interested">
                    	<div class="row">
	                    	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                    		<div class="" id="other-child-service-header-ctn">
	                    			<h4><@spring.message "menu.other.interest"/></h4>
	                    		</div>
	                    	</div>
                    	</div>
                    	<div class="row">
                    		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	                    		<div class="panel" id="other-child-services-ctn">
		                    		<ul>
				                        <#list otherChildServices as service>
				                    	<li>
				                    		<div class="thumbnail other-child-services" data-url="${service.url}">
				                    			<div class="child-service-image">
				                    				<img src="${'data:image/png;base64,' + service.image}" alt="${service.name}">
				                    			</div>
				                    			<div class="child-service-name">
				                    				<span>${service.name}</span>
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
                
                <#--  recommend Service -->
                <div class="row">
	                
	                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="recommend-service-header">
	                    	<div id="title">
	                    		<span><@spring.message "menu.other.interest"/></span>
	                    	</div>
	                    	<div class="line"></div>
	                    </div>
	                
	                
	                	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="recommend-service-list">
	                		<ul>
		                        <#list services as service>
		                    	<li class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
		                    		<div class="thumbnail other-services" data-url="${service.url}">
		                    			<div class="other-services-img-ctn">
		                    				<img src="${'data:image/png;base64,' + service.image}" alt="${service.name}">
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