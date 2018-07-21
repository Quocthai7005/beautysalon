$(document).ready(function() {
	//KoSelectHandler();
	var serviceModel = new Service();
	ko.applyBindings(serviceModel);
	serviceModel.getServiceGroups();
	serviceModel.getPageNo(0).done(function (res) {	
		// Initialize pagination
		serviceModel.initPagination();
	});
})

function Service() {
	var self = this;
	var pageSize = 5
	
	// Url
	var rootContext = $('#root-context').val();
	var getServicesUrl = rootContext + '/admin/service-list/all'
	var goToEditUrl = rootContext + '/admin/service-edit/';
	var returnServiceList = rootContext + '/admin/service-list';
	var goToCreateUrl = rootContext + '/admin/service-add';
	var deleteUrl = rootContext + '/admin/service-delete/';
	var loadServiceGroupsUrl = rootContext + '/admin/service-group-list/all';
	var getPageNoUrl = rootContext + '/admin/service-list/no';
	
	// Message
	var deleteSuccess = 'Xoá thành công';
	var deleteFail = 'Không thể xoá';
	var deleteConfirm = 'Bạn muốn xoá tin này?';
	var agree = 'Đồng ý';
	var cancel = 'Không';
	
	// Observable
	self.groupId = ko.observable(0);
	self.services = ko.observableArray([]);
	self.serviceGroups = ko.observableArray([]);
	
	var pageOptions = {
			totalPages: 1,
			visiblePages: 3,
			startPage: 1,
			first: 'Trang đầu',
			last: 'Trang cuối',
			prev: 'Trang trước',
			next: 'Trang sau',
			onPageClick : function(event, page) {
				// get group id
				self.loadService(page - 1, pageSize, 'asc', self.groupId());
			}
		}
	
	self.groupId.subscribe(function() {
		
		// Destroy old pagination
		$('#pagination-post').twbsPagination('destroy');
		
		
		// get service group quantity
		self.getPageNo(self.groupId()).done(function (res) {
			// Initialize pagination
			self.initPagination();
		});
	});
	
	self.getServiceGroups = function() {
		$.ajax({
			type : "GET",
			url : loadServiceGroupsUrl,
			success : function(res) {
				if (res.code == 200) {
					self.serviceGroups(res.data);
				}
			}, error: function(e) {
				console.log(e);
			}
		})
	}
	
	self.getPageNo = function(groupId) {
		var query = {
			groupId: groupId
		}
		return $.ajax({
			type : "GET",
			data: query,
			url : getPageNoUrl,
			success : function(res) {
				console.log(res);
				var totalService = res.data;
				pageOptions.totalPages = Math.ceil(totalService / pageSize);
			}
		});
	}
	
	self.loadService = function(pageNo, pageSize, pageSort, groupId) {
		var that = this;
		var pageable = {
			page : pageNo,
			size : pageSize,
			sort : pageSort,
			groupId: groupId
		}
		$.ajax({
			type : "GET",
			url : getServicesUrl,
			dataType : 'json',
			data : pageable,
			success : function(res) {			
				self.services(res.data.content);
			}
		});
	}
	
	self.initPagination = function() {
		$('#pagination-post').twbsPagination(pageOptions);
	}
	
	self.showImage = function(data) {
    	swal({
		  imageUrl: 'data:image/png;base64,' + data.image,
		  imageHeight: 250,
		  imageAlt: 'service name'
		});
    }
    
    self.deleteService = function(data) {
    	var url = deleteUrl + data.id;
    	swal({
    		  	text: deleteSuccess,
    		  	type: 'warning',
    		  	showCancelButton: true,
    		  	confirmButtonText: agree,
    		  	cancelButtonText: cancel,
		}).then((result) => {
			if (result.value) {
				$.ajax({
		    	    type: 'DELETE',
		    	    url: url,
		    	    success: function(msg) {
		    	        if (msg.code === 200) {
		    	        	if (msg.data === true) {
		    	        		swal({
			        			  type: 'success',
			        			  text: deleteSuccess,
			        			  onClose: function() {
			        				  window.location.href = returnServiceList;
			        			  }
			        			});
		    	        	} else {
		    	        		swal({
		  	        			  type: 'error',
		  	        			  text: deleteFail,
		  	        			});
		    	        	}
		    	        }
		    	    },
		    	    error: function(e) {
		    	    	console.log(e);
		    	    	if (e.status === 901) {
		    	    		swal({
			        			  type: 'info',
			        			  text: 'Hết phiên làm việc, vui lòng đăng nhập lại',
			        			  onClose: function() {
			        				  window.location.href = 'admin/login';
			        			  }
			        		});
		    	    	}
		    	    }
		    	});
			}
		});
    };
    
    self.goToEdit = function(data) {
    	var getUrl = goToEditUrl + data.id;
    	window.location.href = getUrl;
    }
    
    self.goToCreate = function(data) {
    	window.location.href = goToCreateUrl;
    }
    
    self.formatDate = function(date) {
    	var result = date.replace('T', ' ');
    	return result;
    }
}