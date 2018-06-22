<!DOCTYPE html>
<html lang="en">

<head>
    <#include "a-headerinc.html.ftl">
    <link rel="stylesheet" type="text/css" href="<@spring.url '/resources/css/summernote.css'/>"/>
    <link rel="stylesheet" type="text/css" href="<@spring.url '/resources/css/a-post-add.css'/>"/>
    <script src="<@spring.url '/resources/javascript/summernote.js'/>"></script>
    <script src="<@spring.url '/resources/javascript/a-post-add.js'/>"></script>
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
                                    <h4 class="title">Tạo tin mới</h4>
                                </div>
                                <div class="card-content table-responsive">
                                
                                	<div class="row">              
                        				<div class="col-lg-12 col-md-12">
		                                    <form id="create-news-form">
		                                    	<input data-bind="value: id" name="id" type="hidden" id="service-id">

												<#-- service name -->
		                                    	<div class="col-lg-12 col-md-12">
		                                    		<div class="form-inline form-group-ctn">
												    	<label for="service-name-inp" class="custom-label">
												    		Tiêu đề tin *	
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
														<input name="image" type="file" class="form-control" id="image-inp" accept="image/*">
														<input required type="hidden" name="base64Field" data-bind="value: image"/>							
											  			<button data-bind="click: showImage" class="btn btn-info" id="preview-image">Xem hình</button>
											  		</div>
											  	</div>
											  	
											  	<#-- service group -->
											  	<div class="col-lg-12 col-md-12" id="select-ctn">
											  		<div>
												  		<label for="serviceGroupId" class="custom-label" style="width: 100%">
												  			Nhóm dịch vụ *
												  		</label>
												  		<select class="selectpicker" required="required" id="group-select" name="groupId" data-bind="selectPicker: groupId, optionsText: 'name', optionsValue: 'id', selectPickerOptions: { optionsArray: serviceGroups }">
											  				<option value="" disabled>Chọn nhóm dịch vụ</option>
											  			</select>
											  		</div>
											  	</div>
											  	
											  	<#-- intro -->
											  	<div class="col-lg-12 col-md-12">
												  	<div class="form-group">
												    	<label for="intro" class="custom-label">Giới thiệu sơ lược</label>
												    	<textarea data-bind="value: intro" class="form-control" id="intro" name="content"></textarea>
												  	</div>
											  	</div>
											  	
											  	<#-- content -->
											  	<div class="col-lg-12 col-md-12">
												  	<div class="form-group">
												    	<label for="news-content-inp" class="custom-label">Nội dung tin</label>
												    	<textarea data-bind="value: content" id="news-content-inp" name="content"></textarea>
												  	</div>
											  	</div>
											  										  	
											  	<div class="col-lg-12 col-md-12">
												  	<button data-bind="click: save" class="btn btn-info">Lưu tin tức</button>
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