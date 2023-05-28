<!DOCTYPE html>
<html>   
	<head>
		<#include "headerinc.html.ftl">
		<link rel="stylesheet" type="text/css" href="<@spring.url '/resources/css/posts.css'/>"/>
		<script src="<@spring.url '/resources/javascript/posts.js'/>"></script>
		<#import "/spring.ftl" as spring/>
	</head>
	<body>
		<input type="hidden" id="root-context" value="${rootContext.getContextPath()}"></input>
		<#-- Navigation bar -->
		<div id="main-posts">
			<#include "nav.html.ftl">
			<#include "carousel.html.ftl">
			<div class="container">
				<#-- header -->
				<div class="row" id="header-posts">
					<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
						<h2><@spring.message "header.newPost"/></h2>
					</div>
					<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
						 <div class="pull-right" id="search-text">
							<input type="text" data-bind="value: searchText" placeholder="Tìm theo tên">
							<i class="fa fa-search" id="search-btn"></i>
						</div>
					</div>
				</div>

				<#-- content -->
				<div class="row" id="content-posts">
					<div class="col-lg-12 text-center">
						<div id="page-content">
							<div data-bind="if: newsPosts().length == 0" style="text-align: center">
								<p>Không có kết quả nào</p>
							</div>
							<div data-bind="if: newsPosts().length > 0">
								<ul data-bind="foreach: newsPosts">
									<li class="col-lg-3 col-md-4 col-sm-6 col-xs-12">
										<div class="card">											
											<img data-bind="attr: {src: $data.image}" width=180 height=180 class="card-img-top">
											<div class="card-body">
												<h5 class="card-title"><span data-bind="text: $data.name"></h5>
												<a data-bind="attr: {href: 'posts/post/' + $data.url}" style="font-family: Quicksand-Bold">Xem chi tiết →</a>
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