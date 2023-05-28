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
		<div id="main-posts-post">
			<#include "nav.html.ftl">
			<#include "carousel.html.ftl">
			<div class="container">
				<#-- content -->
				<div class="row row-eq-height" style="padding-top: 30px; padding-bottom: 30px">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="header-service-ctn">
								<h2>${post.name}</h2>
							</div>
						</div>
						<div class="row" style="padding-top: 30px">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
								<div>
									<span>${post.content}</span>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row" style="padding-top: 30px; padding-bottom: 30px; background-color: #fff">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="recommend-post-header">
						<div class="row">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="header-service-ctn">
								<h2><@spring.message "menu.other.interest"/></h2>
							</div>
						</div>
					</div>
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center" id="recommend-post-list">
						<ul>
							<#list latestPosts as post>						
								<li class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
									<div class="card">
										<img src="${post.imageBaseUrl + post.imageKey}" width=180 height=180 class="card-img-top" alt="${post.name}">
										<div class="card-body">
											<h5 class="card-title">${post.name}</h5>
											<a style="font-family: Quicksand-Bold" href="${post.url}">Xem chi tiết →</a>
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