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
										<div class="col-lg-12 col-md-12">
											<table class="table table-hover">
												<thead>
													<th class="text-center">No.</th>
													<th class="text-center">Tên file</th>
													<th class="text-center">Kích thước (KBs)</th>
													<th class="text-center">Ngày upload</th>
													<th class="text-center">Storage class</th>
													<th class="text-center">Hiển thị/download</th>
													<th class="text-center">Nơi gọi</th>
													<th class="text-center">Xóa file</th>
												</thead>
												<tbody data-bind="foreach: files">
													<tr>
														<td class="text-center" data-bind="text: $index() + 1">1</td>
														<td class="text-left" data-bind="text: key"></td>
														<td class="text-center" data-bind="text: $parent.formatSize(size)"></td>
														<td class="text-center" data-bind="text: $parent.formatDate(lastModified)"></td>
														<td class="text-center" data-bind="text: objectClass"></td>
														<td class="text-center"><a data-bind="attr: { 'href': uri }" target="_blank">-></a></td>
														
														
														<td class="text-center">
														<!-- ko if: $parent.directory() == 'posts' -->
															<ol data-bind="foreach: usedInNews">
																<li>
																	<a data-bind="attr: { 'href': 'posts-edit/' + id }" target="_blank">
																		<span data-bind="text: name"></span>
																	</a>
																</li>
															</ol>
														<!-- /ko -->
														<!-- ko if: $parent.directory() == 'product' -->
															<ol data-bind="foreach: usedInProduct">
																<li>
																	<a data-bind="attr: { 'href': 'service-group-edit/' + id }" target="_blank">
																		<span data-bind="text: name"></span>
																	</a>
																</li>
															</ol>
														<!-- /ko -->
														<!-- ko if: $parent.directory() == 'subproduct' -->
															<ol data-bind="foreach: usedInSubProduct">
																<li>
																	
																	<a data-bind="attr: { 'href': 'service-edit/' + id }" target="_blank">
																		<span data-bind="text: name"></span>
																	</a>
																</li>
															</ol>
														<!-- /ko -->
														</td>
														<td class="text-center"><button class="btn btn-danger btn-sm" data-bind="click: $parent.deleteFile.bind($data)">Xóa</button></td>
													</tr>
												</tbody>
											</table>
										</div>
									</div>
									<div class="row">
										<div class="col-lg-12 col-md-12 col-xs-12">
											<ul id="pagination-bucket" class="pagination-sm pull-right"></ul>
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