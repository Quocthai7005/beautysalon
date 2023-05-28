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
			<#include "a-nav-top.html.ftl">
			<div class="content">
				<div class="container-fluid">
					<div class="row">			  
						<div class="col-lg-12 col-md-12">
							<div class="card">
								<div class="card-header" data-background-color="dark-red">
									<div class="row"> 
										<div class="col-lg-6 col-md-6">
											<h4 class="title">Danh sách</h4>
										</div>
										<div class="col-lg-6 col-md-6">
											<div class="pull-right">
												<select class="selectpicker" id="service-group-select" data-bind="selectPicker: status, optionsText: 'name', optionsValue: 'status', selectPickerOptions: { optionsArray: statuses }">
												  	<option value="" selected="selected">TẤT CẢ LỊCH HẸN</option>
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
													<th class="text-center">tên file</th>
													<th class="text-center">Email</th>
													<th class="text-center">Số điện thoại</th>
													<th class="text-center">Ngày hẹn</th>
													<th class="text-center">Tình trạng</th>
													<th class="text-center">Chi tiết</th>
												</thead>
												<tbody data-bind="foreach: bookings">
													<tr>
														<td class="text-center" data-bind="text: $index() + 1">1</td>
														<td class="text-center" data-bind="text: name"></td>
														<td class="text-center" data-bind="text: email"></td>
														<td class="text-center" data-bind="text: phone"></td>
														<td class="text-center" data-bind="text: consultDate"></td>
														<td class="text-center">
															<span data-bind="if: status === 'P'">CHỜ XỬ LÝ</span>
															<span data-bind="if: status === 'O'">ĐANG XỬ LÝ</span>
															<span data-bind="if: status === 'C'">XỬ LÝ HOÀN TẤT</span>
														</td>
														<td class="text-center" data-bind="click: $parent.goToDetail.bind($data)"><i class="fa fa-pencil"></i></td>
													</tr>
												</tbody>
											</table>
										</div>
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