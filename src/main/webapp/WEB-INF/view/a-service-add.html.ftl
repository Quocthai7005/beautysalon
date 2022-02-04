<!DOCTYPE html>
<html lang="en">

<head>
    <#include "a-headerinc.html.ftl">
    <link rel="stylesheet" type="text/css" href="<@spring.url '/resources/css/summernote.css'/>"/>
    <link rel="stylesheet" type="text/css" href="<@spring.url '/resources/css/a-service-add.css'/>"/>
    <script src="<@spring.url '/resources/javascript/summernote.js'/>"></script>
    <script src="<@spring.url '/resources/javascript/a-service-add.js'/>"></script>
</head>

<body>
	<input type="hidden" id="root-context" value="${rootContext.getContextPath()}"></input>
    <div class="wrapper">
        <#include "a-nav-left.html.ftl">
        <div class="main-panel">
            <nav class="navbar navbar-transparent navbar-absolute">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <p class="navbar-brand" href="#"> Dịch vụ </p>
                    </div>
                </div>
            </nav>
            <div class="content">
                <div class="container-fluid">
                    <div class="row">              
                        <div class="col-lg-12 col-md-12">
                            <div class="card">
                                <div class="card-header" data-background-color="green">
                                    <h4 class="title">Tạo dịch vụ mới</h4>
                                </div>
                                <div class="card-content table-responsive">
                                
                                	<div class="row">              
                        				<div class="col-lg-12 col-md-12">
		                                    <form id="create-service-form">
		                                    	<input data-bind="value: id" name="id" type="hidden" id="service-id">

												<#-- service name -->
		                                    	<div class="col-lg-12 col-md-12">
		                                    		<div class="form-inline form-group-ctn">
												    	<label for="service-name-inp" class="custom-label">
												    		Tên dịch vụ *	
												    	</label>
												    	<input required data-bind="value: name" name="name" type="text" class="form-control" id="service-name-inp">
											  		</div>
											  	</div>
											  	
											  	<#-- link -->
											  	<div class="col-lg-12 col-md-12">
											  		<div class="form-inline form-group-ctn">
												  		<label for="url-inp" class="custom-label">
												  			Link truy cập *
												  		</label>
												  		<input pattern="[a-z]" required data-bind="value: url" name="url" type="text" class="form-control" id="url-inp">
											  		</div>
											  	</div>
											  	
											  	<#-- image -->
											  	<div class="col-lg-12 col-md-12">
											    	<div class="form-inline">
												    	<label for="image-inp" class="custom-label">Hình đại diện *</label>											    	
												    	<label class="custom-image-upload form-control">
														    <i class="fa fa-cloud-upload"></i> Upload hình
														</label>
														<input type="hidden" class="form-control" name="base64Field" data-bind="value: image"/>
														<input name="image" type="file" class="form-control" id="image-inp" accept="image/*">
											  			<span data-bind="if: image">
											  				<button data-bind="click: showImage" class="btn btn-info" id="preview-image">Xem hình</button>
											  			</span>
											  		</div>
											  	</div>
											  	
											  	<#-- service group -->
											  	<div class="col-lg-12 col-md-12" id="select-ctn">
											  		<div class="form-inline form-group-ctn">
												  		<label for="groupId" class="custom-label" style="width: 100%">
												  			Nhóm dịch vụ *
												  		</label>
											  			
											  			    <select class="form-control" id="group-select" name="groupId" data-bind="options: serviceGroups,
														                       optionsText: 'name',
														                       optionsValue: 'id',
														                       optionsCaption: 'Chọn nhóm dịch vụ'">
														    </select>
											  		</div>
											  	</div>
											  	
											  	<#-- shown on home page -->
											  	<div class="col-lg-12 col-md-12">
											  		<div class="form-inline" style="margin-top: 20px">
												  		<label for="shownInHome" class="custom-label" style="width: 100%">
												  			Hiển thị trên trang chủ 
												  		</label>
												  		<input name="isShownHome" data-bind="checked: isShownHome" id="shown-in-home" type="checkbox">
											  		</div>
											  	</div>
											  	
											  	<#-- intro -->
											  	<div class="col-lg-12 col-md-12">
												  	<div class="form-group">
												    	<label for="service-content-inp" class="custom-label">Giới thiệu sơ lược</label>
												    	<textarea data-bind="value: intro" class="form-control" name="content"></textarea>
												  	</div>
											  	</div>
											  	
											  	<#-- content -->
											  	<div class="col-lg-12 col-md-12">
												  	<div class="form-group">
												    	<label for="service-content-inp" class="custom-label">Nội dung dịch vụ</label>
												    	<textarea data-bind="value: content" id="service-content-inp" name="content"></textarea>
												  	</div>
											  	</div>
											  										  	
											  	<div class="col-lg-12 col-md-12">
												  	<button data-bind="click: save" class="btn btn-info">Lưu dịch vụ</button>
												  	<button data-bind="click: toList" class="btn" id="a-main-return-btn">Quay lại</button>
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
    </div>
</body>
</html>