<!DOCTYPE html>
<html lang="en">

<head>
    <#include "a-headerinc.html.ftl">
    <link rel="stylesheet" type="text/css" href="<@spring.url '/resources/css/summernote.css'/>"/>
    <link rel="stylesheet" type="text/css" href="<@spring.url '/resources/css/a-service-group-add.css'/>"/>
    <script src="<@spring.url '/resources/javascript/summernote.js'/>"></script>
    <script src="<@spring.url '/resources/javascript/a-service-group-add.js'/>"></script>
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
                                    <h4 class="title">CREATE NEW SERVICE TYPE</h4>
                                </div>
                                <div class="card-content table-responsive">
                                
                                	<div class="row">              
                        				<div class="col-lg-12 col-md-12">
		                                    <form id="create-service-form">
		                                    	<input data-bind="value: id" name="id" type="hidden" id="service-id">

		                                    	<div class="col-lg-12 col-md-12">
		                                    		<div class="form-inline form-group-ctn">
												    	<label for="service-name-inp" class="custom-label">
												    		Service Type Name *
												    	</label>
												    	<input required data-bind="value: name" name="name" type="text" class="form-control" id="service-name-inp">
											  		</div>
											  	</div>

												<div class="col-lg-12 col-md-12">
													<div class="form-group">
														<label for="service-content-inp" class="custom-label">Description</label>
														<textarea data-bind="value: content" id="service-content-inp" name="content"></textarea>
													</div>
												</div>
											  	
											  	<div class="col-lg-12 col-md-12">
											    	<div class="form-inline">
												    	<label for="image-inp" class="custom-label">Image</label>
												    	<label class="custom-image-upload form-control">
														    <i class="fa fa-cloud-upload"></i> Upload Image
														</label>
														<input name="image" type="file" class="form-control" id="image-inp" accept="image/*">
														<input class="form-control" type="hidden" name="base64Field" data-bind="value: image"/>							
											  			<span data-bind="if: image">
											  				<button data-bind="click: showImage" class="btn btn-info" id="preview-image">Preview</button>
											  			</span>
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