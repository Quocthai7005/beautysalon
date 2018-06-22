$(document).ready(function() {
	
	var serviceModel = new Service();
	ko.applyBindings(serviceModel);
	
	serviceModel.initValidator();
	serviceModel.initSummernote();
	serviceModel.initImageUpload();
	serviceModel.getServiceGroups();
	
});

function Service() {
	
	var self = this;
	
	// Url
	var rootContext = $('#root-context').val();
	var saveUrl = rootContext + '/admin/service-update';
	var loadServiceUrl = rootContext + '/admin/service/child/';
	var returnListUrl = rootContext + '/admin/service-list';
	var validateUrl = rootContext + '/admin/service-validate/url';
	var loadServiceGroupsUrl = rootContext + '/admin/service-group-list/all'
	
	// Message
	var savedSuccess = 'Chỉnh sửa thành công';
	var savedFail = 'Không thể chỉnh sửa';
	var entryRemind = 'Vui lòng nhập đúng dữ liệu';
	var saveConfirm = 'Bạn muốn chỉnh sửa dịch vụ này?';
	var returnList = 'Bạn muốn quay lại danh sách dịch vụ?';
	var agree = 'Đồng ý';
	var cancel = 'Không';
	
	// Observable
	self.id = ko.observable();
	self.name = ko.observable();
	self.url = ko.observable();
	self.image = ko.observable();
	self.groupId = ko.observable();
	self.isShownHome = ko.observable();
	self.intro = ko.observable();
	self.content = ko.observable();
	self.serviceGroups = ko.observableArray([]);
	
	self.save = function() {
		var validator = $('#edit-service-form').data('bootstrapValidator');
    	validator.validate();
    	if (validator.isValid()) {
    		swal({
  			  text: saveConfirm,
  			  type: 'info',
  			  showCancelButton: true,
  			  confirmButtonText: agree,
  			  cancelButtonText: cancel,
  			}).then((result) => {
  				if (result.value) {
  					var data = {
						id: self.id(),
						url: self.url(),
						name: self.name(),
						image: self.image().slice(22),
						parentServiceId: self.groupId(),
						isShownHome: self.isShownHome(),
						intro: self.intro(),
						content: $('#service-content-inp').summernote('code')
					}
					$.ajax({
		        		type : "POST",
		        		url : saveUrl,
		        		data: JSON.stringify(data),
		                contentType: "application/json; charset=utf-8",
		        		success : function(msg) {
		        			if (msg.data === true) {
		    	        		swal({
		            			  type: 'success',
		            			  text: savedSuccess,
		            			  onClose: function() {
		            				  window.location.href = returnListUrl;
		            			  }
		            			});
		    	        	} else {
		    	        		swal({
		    	        			  type: 'error',
		    	        			  text: savedFail
		    	        			});
		    	        	}
		        		}, error: function(e) {
		        			console.log(e);
		        		}
		        	});
  				}
  			});
    	} else {
    		swal({
			  type: 'error',
			  text: entryRemind,
			});
    	}	
	}
	
	self.loadService = function() {
		// get service id from url
    	var path = window.location.href
    	var slashIdx = path.lastIndexOf('/');
    	var serviceId = path.slice(slashIdx + 1);
    	var getUrl = loadServiceUrl + serviceId;
    	
    	// load service
    	$.ajax({
    		type : "GET",
    		url : getUrl,
    		dataType : 'json',
    		success : function(res) {				
    			console.log(res);
    			if (res.code == 200) {			
    				self.name(res.data.name);
    				self.id(res.data.id);
    			    self.url(res.data.url);
    			    self.content(res.data.content); 
    			    self.intro(res.data.intro)
    			    $('#service-content-inp').summernote('code', self.content()),
    			    self.image('data:image/png;base64,' + res.data.image);
    			    self.groupId(res.data.parentServiceId);
    				self.isShownHome(res.data.isShownHome);	    
    			    var validator = $('#edit-service-form').data('bootstrapValidator');
    			    validator.validate();
    			}
    		}, error: function(e) {
    			console.log(e)
    		}
    	});
    };
	
	self.initValidator = function() {
		$('#edit-service-form').bootstrapValidator({
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                name: {
                    message: 'Tên không đúng',
                    validators: {
                        notEmpty: {
                            message: 'Vui lòng nhập tên'
                        },
                        stringLength: {
                            max: 45,
                            message: 'Tối đa 45 ký tự'
                        }
                    }
                },
                url: {
                    validators: {
                        notEmpty: {
                            message: 'Vui lòng nhập link'
                        },
                        remote: {
                            message: 'link đã tồn tại',
                            url: validateUrl,
                            delay: 1000,
                            dataType:'json',
                            data: function(validator, $field, value) {
                                return {
                                	id: validator.getFieldElements('id').val(),
                                    url: validator.getFieldElements('url').val(),
                                };
                            },
                        }
                    }
                },
                groupId: {
                	validators: {
                		notEmpty: {
                            message: 'Vui lòng chọn nhóm dịch vụ'
                        }
                    }
                },
                base64Field: {
                	validators: {
                        notEmpty: {
                            message: 'Vui lòng chọn hình'
                        }
                    }
                }
            }
        });
	}
	
	self.toList = function() {
		swal({
			text: returnList,
			type: 'info',
			showCancelButton: true,
			confirmButtonText: agree,
			cancelButtonText: cancel,
		}).then((result) => {
			if (result.value) {
				window.location.href = returnListUrl;
			}
		});
	}
	
	self.initImageUpload = function() {
		$('#image-inp').change(function() {
    		var i = $('.custom-image-upload').clone();
    		var file = $('#image-inp')[0].files[0].name;
    		$('.custom-image-upload').text(file);
    		
    		var reader = new FileReader();
    	    reader.onload = function (e) {
    	        self.image(e.target.result);
    	    };
    	    reader.readAsDataURL($('#image-inp')[0].files[0]);
    	});
	}
	
	self.getServiceGroups = function() {
		$.ajax({
			type : "GET",
			url : loadServiceGroupsUrl,
			success : function(res) {
				if (res.code == 200) {
					self.serviceGroups(res.data);
					self.loadService();
				}
			}, error: function(e) {
				console.log(e);
			}
		})
	}
	
	self.initSummernote = function() {
    	$('#service-content-inp').summernote({
            tabsize: 2,
            height: 100
    	});
    }
    
    self.showImage = function() {
    	swal({
  		  imageUrl: self.image(),
  		  imageHeight: 250,
  		  imageAlt: 'service name'
  		});
    }
}