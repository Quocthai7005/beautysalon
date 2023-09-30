<!DOCTYPE html>
<html lang="en">

<head>
    <#include "a-headerinc.html.ftl">
    <link rel="stylesheet" type="text/css" href="<@spring.url '/resources/css/summernote.css'/>"/>
    <link rel="stylesheet" type="text/css" href="<@spring.url '/resources/css/a-service-edit.css'/>"/>
    <script src="<@spring.url '/resources/javascript/summernote.js'/>"></script>
    <script src="<@spring.url '/resources/javascript/a-service-edit.js'/>"></script>
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
                                    <h4 class="title">EDIT SERVICE</h4>
                                </div>
                                <div class="card-content table-responsive">
                                
                                	<div class="row">              
                        				<div class="col-lg-12 col-md-12">
		                                    <form id="edit-service-form">
		                                    	<input data-bind="value: id" name="id" type="hidden" id="service-id">

												<#-- service name -->
		                                    	<div class="col-lg-12 col-md-12">
		                                    		<div class="form-inline form-group-ctn">
												    	<label for="service-name-inp" class="custom-label">
												    		Service name *
												    	</label>
												    	<input required data-bind="value: name" name="name" type="text" class="form-control" id="service-name-inp">
											  		</div>
											  	</div>

												<#-- service price -->
												<div class="col-lg-12 col-md-12">
													<div class="form-inline form-group-ctn">
														<label for="service-price-inp" class="custom-label">
															Service price *
														</label>
														<input required data-bind="value: price" name="price" type="text" class="form-control" id="service-price-inp">
													</div>
												</div>
											  	
											  	<#-- image -->
											  	<div class="col-lg-12 col-md-12">
											    	<div class="form-inline">
												    	<label for="image-inp" class="custom-label">Image</label>
												    	<label class="custom-image-upload form-control">
														    <i class="fa fa-cloud-upload"></i> Upload image
														</label>
														<input name="image" type="file" class="form-control" id="image-inp" accept="image/*">
														<input required type="hidden" name="base64Field" data-bind="value: image"/>							
											  			<button data-bind="click: showImage" class="btn btn-info" id="preview-image">Preview</button>
											  		</div>
											  	</div>
											  	
											  	<#-- service group -->
											  	<div class="col-lg-12 col-md-12" id="select-ctn">
											  		<div>
												  		<label for="serviceGroupId" class="custom-label" style="width: 100%">
												  			Service Type *
												  		</label>
												  		<select class="selectpicker" required="required" id="group-select" name="groupId" data-bind="selectPicker: groupId, optionsText: 'name', optionsValue: 'id', selectPickerOptions: { optionsArray: serviceGroups }">
											  				<option value="0" disabled>Select a type</option>
											  			</select>
											  		</div>
											  	</div>

											  	<#-- intro -->
											  	<div class="col-lg-12 col-md-12">
												  	<div class="form-group">
												    	<label for="service-content-inp" class="custom-label">Description</label>
												    	<textarea data-bind="value: intro" class="form-control" name="content"></textarea>
												  	</div>
											  	</div>
											  										  	
											  	<div class="col-lg-12 col-md-12">
												  	<button data-bind="click: save" class="btn btn-info">Save</button>
												  	<button data-bind="click: toList" class="btn" id="a-main-return-btn">Back</button>
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