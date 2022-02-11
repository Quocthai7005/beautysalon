<!DOCTYPE html>
<html lang="en">

<head>
	<#include "a-headerinc.html.ftl">
	<link rel="stylesheet" type="text/css" href="<@spring.url '/resources/css/a-post-list.css'/>"/>
	<script src="<@spring.url '/resources/javascript/a-booking-list.js'/>"></script>
</head>

<body>
	<input type="hidden" id="root-context" value="${rootContext.getContextPath()}"></input>
	<div class="wrapper">
		<#include "a-nav-left.html.ftl">
		<div class="main-panel">
			<nav class="navbar navbar-transparent navbar-absolute">
				<div class="container-fluid">
					<div class="navbar-header">
						<p class="navbar-brand" href="#"> Trang tin </p>
					</div> 
				</div>
			</nav>
			<div class="content">
				<div class="container-fluid">
					<div class="row">			  
						<div class="col-lg-12 col-md-12">
							<div class="card">
								<div class="card-header" data-background-color="green">
									<div class="row"> 
										<div class="col-lg-6 col-md-6">
											<h4 class="title">Danh sách</h4>
										</div>
										<div class="col-lg-6 col-md-6">
											<div class="pull-right">
												<select class="selectpicker" id="service-group-select" data-bind="selectPicker: status, optionsText: 'name', optionsValue: 'status', selectPickerOptions: { optionsArray: bookings }">
												  	<option value="0">Tất cả lịch hẹn</option>
												</select>
											</div> 
										</div>
									</div>
								</div>
								<div class="card-content table-responsive">
								
									<div class="row">			  
										<div class="col-lg-12 col-md-12">
											<table class="table table-hover">
												<thead>
													<th class="text-center">Số thứ tự</th>
													<th class="text-center">Họ tên</th>
													<th class="text-center">Email</th>
													<th class="text-center">Số điện thoại</th>
													<th class="text-center">Ngày hẹ</th>
													
													<th class="text-center">Tình trạng</th>
												</thead>
												<tbody data-bind="foreach: bookings">
													<tr>
														<td class="text-center" data-bind="text: $index() + 1">1</td>
														<td class="text-center" data-bind="text: name"></td>
														<td class="text-center" data-bind="text: email"></td>
														<td class="text-center" data-bind="text: phone"></td>
														<td class="text-center" data-bind="text: consultDate"></td>
														<td class="text-center" data-bind="text: status"></td>
													</tr>
												</tbody>
											</table>
										</div>
									</div>
									<div class="row">
										<div class="col-lg-8 col-md-12 col-xs-12">
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