<!DOCTYPE html>
<html lang="en">

<head>
    <#include "a-headerinc.html.ftl">
    <link rel="stylesheet" type="text/css" href="<@spring.url '/resources/css/a-post-list.css'/>"/>
    <script src="<@spring.url '/resources/javascript/a-post-list.js'/>"></script>
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
                                	<div class="row"> 
	                                	<div class="col-lg-6 col-md-6">
	                                    	<h4 class="title">Danh sách</h4>
	                                    </div>
	                                    <div class="col-lg-6 col-md-6">
		                                    <div class="pull-right">
	                                    		<select class="selectpicker" id="service-group-select" data-bind="selectPicker: groupId, optionsText: 'name', optionsValue: 'id', selectPickerOptions: { optionsArray: serviceGroups }">
												  	<option value="0">Tất cả nhóm dịch vụ</option>
												</select>
		                                    </div>
		                                    <div class="pull-right" id="search-text">
	                                    		<input type="text" data-bind="value: searchText" placeholder="Tìm theo tên">
	                                    		<i class="fa fa-search" id="search-btn"></i>
		                                    </div> 
	                                    </div>
	                                </div>
                                </div>
                                <div class="card-content table-responsive">
                                
                                	<div class="row">              
                        				<div class="col-lg-12 col-md-12">
		                                    <table class="table table-hover">
		                                        <thead>
		                                            <th class="text-center">Số thứ tự</th>
		                                            <th class="text-center">Tên bài</th>
		                                            <th class="text-center">Nhóm dịch vụ</th>
		                                            <th class="text-center">Ngày tạo</th>
		                                            <th class="text-center">Hình đại diện</th>
		                                            <th class="text-center">Chỉnh sửa</th>
		                                            <th class="text-center">Xoá</th>
		                                        </thead>
		                                        <tbody data-bind="foreach: newsPosts">
		                                            <tr>
		                                                <td class="text-center" data-bind="text: $index() + 1">1</td>
		                                                <td class="text-center" data-bind="text: name"></td>
		                                                <td class="text-center" data-bind="text: parentServiceName"></td>
		                                                <td class="text-center" data-bind="text: createdDate"></td>
		                                                <td class="text-center" data-bind="click: $parent.showImage.bind($data)"><i class="fa fa-image"></i></td>
		                                                <td class="text-center" data-bind="click: $parent.goToEdit.bind($data)"><i class="fa fa-pencil"></i></td>
		                                                <td class="text-center" data-bind="click: $parent.deletePost.bind($data)"><i class="fa fa-remove"></i></td>
		                                            </tr>
		                                        </tbody>
		                                    </table>
		                                </div>
                                    </div>
                                    <div class="row">              
                        				<div class="col-lg-4 col-md-12 col-xs-12">
		                                    <a href="<@spring.url '/admin/news-add'/>" class="btn btn-info pull-left" data-bind="click: goToCreate"><span><i class="fa fa-plus"></i>&nbsp;&nbsp;</span>Tạo mới</a>   
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