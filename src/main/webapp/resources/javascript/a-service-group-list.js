$(document).ready(function() {
	var aServiceGroupList = new AServiceGroupList();
	aServiceGroupList.rootContext = $('#root-context').val();
	ko.applyBindings(serviceModel);
	aServiceGroupList.getPageNo().done(function (res) {	
		// init pagination
		aServiceGroupList.initPagination();
	});
	
})

function Service() {
	var self = this;	 
    self.services = ko.observableArray([]);

    self.showImage = function(data) {
    	swal({
		  imageUrl: 'data:image/png;base64,' + data.image,
		  imageHeight: 250,
		  imageAlt: 'service name'
		});
    }
    
    self.deleteService = function(data) {
    	var url = 'service-group-delete/' + data.id;
    	swal({
    		  	text: "Do you really want to remove this service type?",
    		  	type: 'warning',
    		  	showCancelButton: true,
    		  	confirmButtonText: 'Yes',
    		  	cancelButtonText: 'No',
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
			        			  text: 'Remove service type successfully',
			        			  onClose: function() {
			        				  window.location.href = 'service-group-list';
			        			  }
			        			});
		    	        	} else {
		    	        		swal({
		  	        			  type: 'error',
		  	        			  text: 'Cannot remove this type',
		  	        			  footer: 'Please remove all services that is of this type first',
		  	        			});
		    	        	}
		    	        }
		    	    },
		    	    error: function(e) {
		    	    	console.log(e);
		    	    	if (e.status === 901) {
		    	    		swal({
			        			  type: 'info',
			        			  text: 'Session ended. Please login again',
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
    	var getUrl = 'service-group-edit/' + data.id;
    	window.location.href = getUrl;
    }
}

serviceModel = new Service();

function AServiceGroupList() {
	this.rootContext = '';
	this.servicePerPage = 10;
	var self = this;
	self.pageOptions = {
			totalPages: 1,
			visiblePages: 3,
			startPage: 1,
			first: 'First',
			last: 'Last',
			prev: 'Back',
			next: 'Next',
			onPageClick : function(event, page) {
				self.getServiceGroups(page - 1, self.servicePerPage, 'asc');
			}
		}
}

AServiceGroupList.prototype.initPagination = function() {
	$('#pagination-post').twbsPagination(this.pageOptions);
}

AServiceGroupList.prototype.getServiceGroups = function(pageNo, pageSize, pageSort) {
	var that = this;
	var pageable = {
		page : pageNo,
		size : pageSize,
		sort : pageSort
	}
	var getUrl = this.rootContext + '/admin/service/all';
	$.ajax({
		type : "GET",
		url : getUrl,
		dataType : 'json',
		data : pageable,
		success : function(res) {			
			serviceModel.services(res.data.content);
			console.log(res.data.content);
		}
	});
}

AServiceGroupList.prototype.getPageNo = function() {
	var that = this;
	var getUrl = this.rootContext + '/admin/service/no'
	return $.ajax({
		type : "GET",
		url : getUrl,
		success : function(res) {
			var totalService = res.data;
			that.pageOptions.totalPages = Math.ceil(totalService / that.servicePerPage);
		}
	});
}