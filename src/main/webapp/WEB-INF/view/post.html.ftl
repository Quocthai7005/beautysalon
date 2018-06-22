<!DOCTYPE html>
<html>   
	<head>
        <#include "headerinc.html.ftl">
        <link rel="stylesheet" type="text/css" href="<@spring.url '/resources/css/news.css'/>"/>
        <script src="<@spring.url '/resources/javascript/news.js'/>"></script>
        <#import "/spring.ftl" as spring/>
    </head>
    <body>
    	<input type="hidden" id="root-context" value="${rootContext.getContextPath()}"></input>
        <#-- Navigation bar -->
        <div id="main-news">
            <#include "nav.html.ftl">
            <#include "carousel.html.ftl">
            <div class="container">
            	<#-- header -->
            	<div class="row" id="header-news">
                    <div class="col-lg-12" id="header-news-ctn">
                    	<div id="title">
                    		<span><@spring.message "header.newPost"/></span>
                    	</div>
                    	<div class="line"></div>
                    </div>
                </div>
                
                <div class="pull-right" id="search-text" style="margin-top: 10px; margin-bottom: 10px">
            		<input type="text" data-bind="value: searchText" placeholder="Tìm theo tên">
            		<i class="fa fa-search" id="search-btn"></i>
                </div>
                
                <#-- content -->
                <div class="row" id="content-news">
                    <div class="col-lg-12">
                    	<div id="page-content">
                    		<div data-bind="if: newsPosts().length == 0" style="text-align: center">
                    			<p>Không có kết quả nào</p>
                    		</div>
                    		<div data-bind="if: newsPosts().length > 0">
	                    		<ul data-bind="foreach: newsPosts">
	                    			<li class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
	                    				<div class="thumbnail new-post" data-bind="click: $parents[0].toPostDetail.bind($data)">	
	                    					<div class="article-img-ctn">
	                    						<img width="190" height="190" data-bind="attr: {src: 'data:image/png;base64,' + $data.image}" />
	                    					</div>
	                    					<div class="article-title-ctn">
	                    						<strong><span data-bind="text: $data.name"></span></strong>
	                    					</div>
	                    				<div>            			
	                    			</li>
	                    		</ul>
	                    	</div>
                    	</div>  
                    </div>
                </div>
                <ul id="pagination-post" class="pagination-sm"></ul>
            </div>
        </div>
        <#include "footer.html.ftl">
    </body>
</html>