<!DOCTYPE html>
<html>   
	<head>
        <#include "headerinc.html.ftl">
        <link rel="stylesheet" type="text/css" href="<@spring.url '/resources/css/newsDetail.css'/>"/>
        <script src="<@spring.url '/resources/javascript/newsDetail.js'/>"></script>
        <#import "/spring.ftl" as spring/>
    </head>
    <body>
    	<input type="hidden" id="root-context" value="${rootContext.getContextPath()}"></input>
        <#-- Navigation bar -->
        <div id="main-news-post">
            <#include "nav.html.ftl">
            <#include "carousel.html.ftl">
            <div class="container">
            
            	
                <#-- content -->
                <div class="row row-eq-height" id="post-detail">
                    <div class="col-lg-9 col-sm-12 col-xs-12">
	                    <div class="row">
		                    <div class="col-lg-12" id="header-news-detail-ctn">
		                    	<div id="title">
		                    		<span><@spring.message "header.newsDetail"/></span>
		                    	</div>
		                    	<div class="line"></div>
		                    </div>
		                </div>
	                    <div class="row">
		                    <div class="col-lg-12">
		                    	<#-- <div id="post-title">
		                    		<h3>${post.name}</h3>
		                    	</div> -->
		                    	<div id="post-content">
		                    		<span>${post.content}</span>
		                    	</div>
	                    	</div>
	                    </div>
                    </div>
	                <div class="col-lg-3 col-sm-12 col-xs-12" id="interested">
                    	<div class="row">
                    		<div class="col-lg-12 col-sm-12 col-xs-12">
	                    		<div class="" id="other-post-header-ctn">
	                    			<h4><@spring.message "menu.other.interest"/></h4>
	                    		</div>
	                    	</div>
	                    </div>
	                   	<div class="row">
	                   	
		                   	<div class="col-lg-12 col-sm-12 col-xs-12" id="service-list-wrapper">
	                    		<div class="panel" id="service-list-ctn">
		                    		<ul>
				                        <#list childServices as service>
				                    	<li>
				                    		<div class="thumbnail child-service" data-url="${service.url}">
				                    			<div class="service-image">
				                    				<img src="${'data:image/png;base64,' + service.image}" width="220" height="220" alt="${service.name}">
				                    			</div>
				                    			<div class="service-name">
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
                
                <div class="row">
                    <div class="col-lg-12" id="recommend-post-header">
                    	<div id="title">
                    		<span><@spring.message "menu.other.interest"/></span>
                    	</div>
                    	<div class="line"></div>
                    </div>
                
                	<div class="col-lg-12" id="recommend-post-list">
                		<ul>
	                        <#list latestPosts as post>
	                    	<li class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
	                    		<div class="thumbnail other-post" data-url="${post.url}">
	                    			<div class="outstand-service-img-ctn">
	                    				<img src="${'data:image/png;base64,' + post.image}" width="210" height="210" alt="${post.name}">
	                    			</div>
	                    			<div class="">
	                    				<strong><span>${post.name}</span></strong>
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