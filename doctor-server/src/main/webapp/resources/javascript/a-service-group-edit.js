$(document).ready(function() {
	
	
	
	serviceModel = new Service();
	
	ko.applyBindings(serviceModel);
	serviceModel.initSummernote();
	serviceModel.loadService();
	serviceModel.initImageUpload();
	serviceModel.initValidator();
})

function Service() {
	
	var self = this;
	// Url
	var rootContext = $('#root-context').val();
	var updateUrl = rootContext + '/admin/service-group-update';
	var serviceGroupListUrl = rootContext + '/admin/service-group-list';
	var validateUrl = rootContext + '/admin/service-group-validate/url';
	var loadServiceGroupUrl = rootContext + '/admin/service/'
	
	// Messages
	var savedSuccess = 'Chỉnh sửa thành công';
	var savedFail = 'Không thể chỉnh sửa';
	var entryRemind = 'Vui lòng nhập đúng dữ liệu';
	var saveConfirm = 'Bạn muốn sửa nhóm dịch vụ này';
	var returnList = 'Bạn muốn quay lại danh sách nhóm dịch vụ?';
	var agree = 'Đồng ý';
	var cancel = 'Không';
	
	// Observable
    self.name = ko.observable('');
    self.id = ko.observable();
    self.url = ko.observable('');
    self.content = ko.observable();
    self.image = ko.observable('')
    
    self.initSummernote = function() {
    	$('#service-content-inp').summernote({
            tabsize: 2,
            height: 100
    	});
    }
    
    self.loadService = function() {
    	// get service id from url
    	var path = window.location.href
    	var slashIdx = path.lastIndexOf('/');
    	var serviceId = path.slice(slashIdx + 1);
    	var getUrl = loadServiceGroupUrl + serviceId;
    	
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
    			    console.log(self.url());
    			    self.content(res.data.content);    
    			    $('#service-content-inp').summernote('code', self.content()),
    			    self.image('data:image/png;base64,' + res.data.image);
    			    var validator = $('#edit-service-form').data('bootstrapValidator');
    		    	validator.validate();
    			}
    		}, error: function(e) {
    			console.log(e)
    		}
    	});
    };
    
    self.showImage = function() {
    	swal({
  		  imageUrl: self.image(),
  		  imageHeight: 250,
  		  imageAlt: 'service name'
  		});
    }
    
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
					var base64Img = self.image().slice(23);
		        	var data = {
		        		id: self.id(),
		        		name: self.name(),
		        		url: self.url(),
		        		image: base64Img,
		        		content: $('#service-content-inp').summernote('code')
		        	}
		        	$.ajax({
		        		type : "POST",
		        		url : updateUrl,
		        		data: JSON.stringify(data),
		                contentType: "application/json; charset=utf-8",
		        		success : function(msg) {
		        			if (msg.data === true) {
		    	        		swal({
		            			  type: 'success',
		            			  text: savedSuccess,
		            			  onClose: function() {
		            				  window.location.href = serviceGroupListUrl;
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
    
    self.toList = function() {
    	swal({
			text: returnList,
			type: 'info',
			showCancelButton: true,
			confirmButtonText: agree,
			cancelButtonText: cancel,
		}).then((result) => {
			if (result.value) {
				window.location.href = serviceGroupListUrl;
			}
		});
    }
    
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
                            dataType: 'json',
                            data: function(validator, $field, value) {
                                return {
                                    id: self.id(),
                                    url: validator.getFieldElements('url').val(),
                                };
                            },
                        },
                        regexp: {
                            regexp: /^[a-z]+$/i,
                            message: 'Vui Đừng nhập số, ký tự đặc biệt'
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
}



