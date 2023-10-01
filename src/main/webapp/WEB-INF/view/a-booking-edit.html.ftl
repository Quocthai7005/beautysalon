<!DOCTYPE html>
<html lang="en">

<head>
	<#include "a-headerinc.html.ftl">
<#--	<link rel="stylesheet" type="text/css" href="<@spring.url '/resources/css/a-post-detail.css'/>"/>-->
	<script src="<@spring.url '/resources/javascript/a-booking-detail.js'/>"></script>
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
											<h4 class="title">BOOKING DETAILS</h4>
										</div>
									</div>
								</div>
								<div class="card-content table-responsive">
									<div class="row">			  
										<div class="col-lg-12 col-md-12">
											<div class="form-inline form-group-ctn">
												<label for="name" class="custom-label">
													Customer name
												</label>
												<span data-bind="text: name"></span>
												<input type="hidden" data-bind="value: name" name="name" type="text" class="form-control" id="name">
											</div>
										</div>
										<div class="col-lg-12 col-md-12">
											<div class="form-inline form-group-ctn">
												<label for="name" class="custom-label">
													Email
												</label>
												<span data-bind="text: email"></span>
												<input type="hidden" data-bind="value: email" name="email" type="text" class="form-control" id="email">
											</div>
										</div>
										<div class="col-lg-12 col-md-12">
											<div class="form-inline form-group-ctn">
												<label for="phone" class="custom-label">
													Phone No
												</label>
												<span data-bind="text: phone"></span>
												<input type="hidden" data-bind="value: phone" name="phone" type="text" class="form-control" id="phone">
											</div>
										</div>
										<div class="col-lg-12 col-md-12">
											<div class="form-inline form-group-ctn">
												<label for="consultDate" class="custom-label">
													Consult date
												</label>
												<span data-bind="text: consultDate"></span>
												<input type="hidden" data-bind="value: consultDate" name="consultDate" type="text" class="form-control" id="consultDate">
											</div>
										</div>

										<div class="col-lg-12 col-md-12">
											<div class="form-inline form-group-ctn">
												<label for="status" class="custom-label">
													Status
												</label>
												<span data-bind="text: status"></span>
												<input type="hidden" data-bind="value: status" name="status" type="text" class="form-control" id="status">
											</div>
										</div>

										<div class="col-lg-12 col-md-12">
											<div class="form-inline form-group-ctn">
												<label for="question" class="custom-label">
													Remarks
												</label>
												<span data-bind="text: question"></span>
												<input type="hidden" data-bind="value: question" name="question" type="text" class="form-control" id="question">
											</div>
										</div>

										<div class="col-lg-12 col-md-12">
											<div class="form-inline form-group-ctn">
												<label for="selectServices" class="custom-label">
													Services
												</label>


												<ol data-bind="foreach: services">
													<li><span data-bind="text: $data"></span></li>
												</ol>

												<input type="hidden" data-bind="value: services" name="services" class="form-control" id="services">
											</div>
										</div>


									</div>
								</div>
							</div>

							<div class="card">
								<div class="card-header" data-background-color="dark-red">
									<div class="row"> 
										<div class="col-lg-6 col-md-6">
											<h4 class="title">STATUS UPDATE</h4>
										</div>
									</div>
								</div>
								<div class="card-content table-responsive">
									<div class="row">			  

										<form id="edit-posts-form">
											<div class="col-lg-12 col-md-12">
												<div class="form-inline form-group-ctn">
													<label for="status-select" class="custom-label">
														Status:
													</label>
													<select class="selectpicker" id="status-select" data-bind="selectPicker: status, optionsText: 'name', optionsValue: 'status', selectPickerOptions: { optionsArray: statuses }">
													</select>
													<input type="hidden" data-bind="value: id" name="id" type="number" class="form-control" id="id">
													<button class="btn btn-info" data-bind="click: update">Update</button>
												</div>
											</div>
										</form>
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