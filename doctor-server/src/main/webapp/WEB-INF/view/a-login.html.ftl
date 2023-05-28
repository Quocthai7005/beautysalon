<!DOCTYPE html>
<html>
    <head>
		<#import "/spring.ftl" as spring/>
		<#include "a-headerinc.html.ftl">
		<link rel="stylesheet" type="text/css" href="<@spring.url '/resources/css/a-login.css'/>"/>
    </head>

    <body class="login">
        <div class="main-content">
        	<div class="col-lg-4 col-md-4 col-sm-4"></div>
            <div class="col-lg-4 col-md-4 col-sm-4" id="login-form-ctn">
                <div class="panel panel-default panel-login">
	                <div class="panel-body">
	                    <form class="form" method="POST" action="<@spring.url '/admin/login'/>">
	                        <legend class="text-center">Đăng nhập</legend>
	                        <#if error> 
	                        	<div class="alert alert-danger">
										<p>
											Sai tên đăng nhập hoặc mật khẩu
										</p>
								</div> 
							</#if>
	                        <div class="form-group">
	                            <span class="input-icon"></span>
	                            <input name="username" type="text" class="form-control" placeholder="Tên đăng nhập">
	                        </div>
	                        <div class="form-group">
	                            <span class="input-icon"></span>
	                            <input name="password" type="password" class="form-control" placeholder="Mật khẩu">
	                        </div>
	                        <div class="form-group">
	                        	<button class="btn btn-success btn-block" type="submit">Đăng nhập</button>
	                        </div>
	                    </form>
	                </div>
	            </div>
            </div>
            <div class="col-lg-4 col-md-4 col-sm-4"></div>
        </div>
    </body>
</html>
