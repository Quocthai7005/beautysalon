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
				<div class="row" style="margin-bottom: 30px">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center" id="header-service-ctn">
						<h2>${service.name}</h2>
					</div>
				</div>
				
				<#-- services -->
				<div class="row" style="margin-bottom: 30px">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
							<#list service.childServices as childService>
								<#if childService?index % 4 = 0>
									<ul>
								</#if>
								<li class="col-lg-3 col-md-6 col-sm-6 col-xs-12" style="padding-top: 10px">
									<div class="card">
										<img src="${childService.imageBaseUrl + childService.imageKey}" width=180 height=180 class="card-img-top" alt="${childService.name}">
										<div class="card-body">
											<h5 class="card-title">${childService.name}</h5>
											<a style="font-family: Quicksand-Bold" href="${service.url}/${childService.url}">Xem chi tiết →</a>
										</div>
									</div>
								</li>
								<#if (childService?index + 1) % 4 = 0>
									</ul>
								</#if>
							</#list> 
					</div>
				</div>
				
				<div class="row" style="padding-bottom: 30px; padding-top: 30px; background-color: #fff">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
						<div>
							<h2><@spring.message "menu.other.interest"/></h2>
						</div>
					</div>
				</div>
				
				<div class="row" id="recommend-service-list" style="padding-bottom: 30px; background-color: #fff">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<#list otherServices as service>
							<#if service?index % 4 = 0>
								<ul>
							</#if>
							<li class="col-lg-3 col-md-3 col-sm-3 col-xs-12 text-center" style="padding-top: 10px">
								<div class="card">
									<img src="${service.imageBaseUrl + service.imageKey}" width=180 height=180 class="card-img-top" alt="${service.name}">
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
				</div>
			</div>		 
		</div>
		<#include "footer.html.ftl">
	</body>
</html>