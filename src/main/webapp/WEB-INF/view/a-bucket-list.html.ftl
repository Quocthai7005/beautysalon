<!DOCTYPE html>
<html lang="en">

<head>
	<#include "a-headerinc.html.ftl">
	<link rel="stylesheet" type="text/css" href="<@spring.url '/resources/css/a-post-list.css'/>"/>
	<script src="<@spring.url '/resources/javascript/a-bucket-list.js'/>"></script>
</head>

<body>
	<input type="hidden" id="root-context" value="${rootContext.getContextPath()}"></input>
	<div class="wrapper">
		<#include "a-nav-left.html.ftl">
		<div class="main-panel">
			<#include "a-nav-top.html.ftl">
			<div class="content">
				<div class="container-fluid">
					<div class="row">			  
						<div class="col-lg-12 col-md-12">
							<div class="card">
								<div class="card-header" data-background-color="dark-red">
									<div class="row"> 
										<div class="col-lg-12 col-md-12">
											<h4 class="title">DANH SÁCH FILE</h4>
										</div>
									</div>
								</div>
								<div class="card-content table-responsive">
									<div class="row">			  

									</div>
									<div class="row">
										<div class="col-lg-12 col-md-12 col-xs-12">
											<ul id="pagination-booking" class="pagination-sm pull-right"></ul>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>