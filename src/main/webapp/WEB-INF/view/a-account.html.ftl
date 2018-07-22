<!DOCTYPE html>
<html lang="en">

<head>
    <#include "a-headerinc.html.ftl">
    <link rel="stylesheet" type="text/css" href="<@spring.url '/resources/css/summernote.css'/>"/>
    <link rel="stylesheet" type="text/css" href="<@spring.url '/resources/css/a-account.css'/>"/>
    <script src="<@spring.url '/resources/javascript/summernote.js'/>"></script>
    <script src="<@spring.url '/resources/javascript/a-account.js'/>"></script>
</head>

<body>
	<input type="hidden" id="root-context" value="${rootContext.getContextPath()}"></input>
    <div class="wrapper">
        <#include "a-nav-left.html.ftl">
        <div class="main-panel">
            <nav class="navbar navbar-transparent navbar-absolute">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <a class="navbar-brand" href="#"> Quản lý tài khoản </a>
                    </div>
                </div>
            </nav>
            <div class="content">
                <div class="container-fluid">
                    <div class="row">              
                        <div class="col-lg-12 col-md-12">
                            <div class="card">
                                <div class="card-header" data-background-color="green">
                                    <h4 class="title">Đổi mật khẩu</h4>
                                </div>
                                <div class="card-content table-responsive">
                                
                                	<div class="row">              
                        				<div class="col-lg-12 col-md-12">
		                                    <form id="user-form">
		                                    	<input type="hidden" value="${username}" id="username">
												<#-- link -->
											  	<div class="col-lg-12 col-md-12">
											  		<div class="form-inline form-group-ctn">
												  		<label for="old-password" class="custom-label">
												  			Mật khẩu cũ *
												  		</label>
												  		<input data-bind="value: oldPassword" name="old-password" type="password" class="form-control" id="old-password">
											  		</div>
											  	</div>
											  	
											  	<#-- link -->
											  	<div class="col-lg-12 col-md-12">
											  		<div class="form-inline form-group-ctn">
												  		<label for="new-password" class="custom-label">
												  			Mật khẩu mới *
												  		</label>
												  		<input data-bind="value: newPassword" type="password" name="password" class="form-control" id="new-password">
											  		</div>
											  	</div>
											  	
											  	<div class="col-lg-12 col-md-12">
											  		<div class="form-inline form-group-ctn">
												  		<label for="retype-new-password" class="custom-label">
												  			Nhập lại mật khẩu mới *
												  		</label>
												  		<input data-bind="value: retypePassword" type="password" name="confirmPassword" class="form-control" id="retype-new-password">
											  		</div>
											  	</div>
											  		  										  	
											  	<div class="col-lg-12 col-md-12">
												  	<button class="btn btn-info" data-bind="click: savePassword">Lưu mật khẩu</button>
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