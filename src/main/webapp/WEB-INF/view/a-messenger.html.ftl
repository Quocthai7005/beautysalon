<!DOCTYPE html>
<html lang="en">

<head>
    <#include "a-headerinc.html.ftl">
    <link rel="stylesheet" type="text/css" href="<@spring.url '/resources/css/a-messenger.css'/>"/>
    <script src="<@spring.url '/resources/javascript/a-messenger.js'/>"></script>
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
                                    <h4 class="title">Thay đổi thông tin Facebook Messenger</h4>
                                </div>
                                <div class="card-content table-responsive">
                                
                                	<div class="row">              
                        				<div class="col-lg-12 col-md-12">
		                                    <form id="messenger-form">
												<#-- App ID -->
											  	<div class="col-lg-12 col-md-12" style="padding-left: 6px">
											  		<div class="form-inline form-group-ctn">
												  		<label for="appId" class="custom-label">
												  			App ID *
												  		</label>
												  		<input data-bind="value: appId" name="appId" type="text" class="form-control" id="appId">
											  		</div>
											  	</div>
											  	
											  	<#-- Page ID -->
											  	<div class="col-lg-12 col-md-12">
											  		<div class="form-inline form-group-ctn">
												  		<label for="pageId" class="custom-label">
												  			Page ID *
												  		</label>
												  		<input data-bind="value: pageId" name="pageId" type="text" class="form-control" id="pageId">
											  		</div>
											  	</div>
											  	
											  	<#-- Auto Log App Events -->
											  	<div class="col-lg-12 col-md-12">
											  		<div class="form-inline form-group-ctn">
												  		<label for="autoLogAppEvents" class="custom-label">
												  			Auto Log App Events *
												  		</label>
												  		<input data-bind="value: autoLogAppEvents" name="autoLogAppEvents" type="text" class="form-control" id="autoLogAppEvents">
											  		</div>
											  	</div>
											  	
											  	<#-- xfbml -->
											  	<div class="col-lg-12 col-md-12">
											  		<div class="form-inline form-group-ctn">
												  		<label for="xfbml" class="custom-label">
												  			xfbml *
												  		</label>
												  		<input data-bind="value: xfbml" name="xfbml" type="text" class="form-control" id="xfbml">
											  		</div>
											  	</div>
											  	
											  	<#-- version -->
											  	<div class="col-lg-12 col-md-12">
											  		<div class="form-inline form-group-ctn">
												  		<label for="version" class="custom-label">
												  			Version *
												  		</label>
												  		<input data-bind="value: version" name="version" type="text" class="form-control" id="version">
											  		</div>
											  	</div>
											  	
											  	<#-- minimized -->
											  	<div class="col-lg-12 col-md-12">
											  		<div class="form-inline form-group-ctn">
												  		<label for="minimized" class="custom-label">
												  			Minimized *
												  		</label>
												  		<input data-bind="value: minimized" name="minimized" type="text" class="form-control" id="minimized">
											  		</div>
											  	</div>
											  		  										  	
											  	<div class="col-lg-12 col-md-12">
												  	<button class="btn btn-info" data-bind="click: saveMessengerInfo">Lưu thay đổi</button>
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