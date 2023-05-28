<!DOCTYPE html>
<html lang="en">

<head>
	<#include "a-headerinc.html.ftl">
	<link rel="stylesheet" type="text/css" href="<@spring.url '/resources/css/a-post-list.css'/>"/>
	<script src="<@spring.url '/resources/javascript/a-file-detail.js'/>"></script>
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
											<h4 class="title">CHI TIẾT FILE</h4>
										</div>
									</div>
								</div>
								<div class="card-content table-responsive">
									<div class="row">			  
										<div class="col-lg-12 col-md-12">
											<div class="col-lg-12 col-md-12">
											<div class="form-inline form-group-ctn">
												<label for="key" class="custom-label">
													Tên file:
												</label>
												<span data-bind="text: key"></span>
												<input type="hidden" data-bind="value: key" name="key" type="text" class="form-control" id="key">
											</div>
										</div>
										<div class="col-lg-12 col-md-12">
											<div class="form-inline form-group-ctn">
												<label for="date" class="custom-label">
													Ngày tạo:
												</label>
												<span id="date" data-bind="text: createdDate"></span>
											</div>
										</div>
										<div class="col-lg-12 col-md-12">
											<div class="form-inline form-group-ctn">
												<label for="uri" class="custom-label">
													Url:
												</label>
												<a data-bind="attr: {href: uri}" target="_blank">hiển thị/download file</a>
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
		</div>
	</div>
</body>
</html>