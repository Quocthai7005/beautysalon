<!DOCTYPE html>
<html lang="en">

<head>
    <#include "a-headerinc.html.ftl">
    <#-- <link rel="stylesheet" type="text/css" href="<@spring.url '/resources/css/home.css'/>"/> -->
    <script src="<@spring.url '/resources/javascript/a-service-group-list.js'/>"></script>
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
                                    <h4 class="title">Danh sách</h4>
                                </div>
                                <div class="card-content table-responsive">
                                
                                	<div class="row">              
                        				<div class="col-lg-12 col-md-12">
		                                    <table class="table table-hover">
		                                        <thead>
		                                            <th class="text-center">Số thứ tự</th>
		                                            <th class="text-center">Tên nhóm dịch vụ</th>
		                                            <th class="text-center">Ngày tạo</th>
		                                            <th class="text-center">Hình đại diện</th>
		                                            <th class="text-center">Chỉnh sửa</th>
		                                            <th class="text-center">Xoá</th>
		                                        </thead>
		                                        <tbody data-bind="foreach: services">
		                                            <tr>
		                                                <td class="text-center" data-bind="text: $index() + 1">1</td>
		                                                <td class="text-center" data-bind="text: name"></td>
		                                                <td class="text-center" data-bind="text: createdDate"></td>
		                                                <td class="text-center" data-bind="click: $parent.showImage.bind($data)"><i class="fa fa-image"></i></td>
		                                                <td class="text-center" data-bind="click: $parent.goToEdit.bind($data)"><i class="fa fa-pencil"></i></td>
		                                                <td class="text-center" data-bind="click: $parent.deleteService.bind($data)"><i class="fa fa-remove"></i></td>
		                                            </tr>
		                                        </tbody>
		                                    </table>
		                                </div>
                                    </div>
                                    <div class="row">              
                        				<div class="col-lg-4 col-md-12 col-xs-12">
		                                    <a href="<@spring.url '/admin/service-group-add'/>" class="btn btn-info pull-left"><span><i class="fa fa-plus"></i>&nbsp;&nbsp;</span>Tạo mới</a>   
                                    	</div>
                                    	<div class="col-lg-8 col-md-12 col-xs-12">
											<ul id="pagination-post" class="pagination-sm pull-right"></ul>		                                    
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