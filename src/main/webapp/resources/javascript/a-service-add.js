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
	var saveUrl = rootContext + '/admin/service-create';
	var returnListUrl = rootContext + '/admin/service-list';
	var validateUrl = rootContext + '/admin/service-validate/url/noid';
	var loadServiceGroupsUrl = rootContext + '/admin/service-group-list/all'
	
	// Message
	var savedSuccess = 'Tạo mới thành công';
	var savedFail = 'Không thể tạo mới';
	var entryRemind = 'Vui lòng nhập đúng dữ liệu';
	var saveConfirm = 'Bạn muốn tạo dịch vụ này?';
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
		var validator = $('#create-service-form').data('bootstrapValidator');
		debugger;
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
  					var imgfile = document.getElementById("image-inp").files[0];
  					var data = new Blob([JSON.stringify({
							id: null,
							url: self.url(),
							name: self.name(),
							image: null,
							parentServiceId: self.groupId(),
							isShownHome: self.isShownHome(),
							intro: self.intro(),
							content: self.content()
						})], {
	                    type: "application/json"
	                });
					var formData = new FormData();
					formData.append("data", data);
					formData.append("imgFile", imgfile);
					$.ajax({
		        		type : "POST",
		        		url : saveUrl,
		        		data: formData,
		        		processData: false,
		                cache: false,
    					contentType: false,
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
								errorPopup(savedFail);
		    	        	}
		        		}, error: function(e) {
							errorPopup(savedFail);
		        		}
		        	});
  				}
  			});
    	} else {
			errorPopup(entryRemind);
    	}
	}
	
	self.initValidator = function() {
		$('#create-service-form').bootstrapValidator({
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',

                validating: 'glyphicon glyphicon-refresh'
            },
			excluded: ':disabled',
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
                                    url: validator.getFieldElements('url').val(),
                                };
                            },
                        }
                    }
                },
                groupId: {
                	validators: {
                		notEmpty: {
                            message: 'Vui lòng chọn dịch vụ'
                        }
                    }
                },
                image: {
                	validators: {
                        notEmpty: {
                            message: 'Vui lòng chọn hình'
                        },
						file: {
						      extension: 'png,jpeg',
						      type: 'image/jpeg,image/png',
						      maxSize: 5*1024*1024,   // 5 MB
						      message: 'The selected file is not valid, it should be (doc,docx,pdf,zip,rtf) and 5 MB at maximum.'
						},
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
		var getUrl = loadServiceGroupsUrl;
		$.ajax({
			type : "GET",
			url : getUrl,
			success : function(res) {
				if (res.code == 200) {
					self.serviceGroups(res.data);
				}
			}, error: function(e) {
				console.log(e);
			}
		})
	}
	
	self.initSummernote = function() {
    	$('#service-content-inp').summernote({
    		toolbar: [
    		    ['style', ['style']],
    		    ['fontsize', ['fontsize']],
    		    ['font', ['bold', 'italic', 'underline', 'clear']],
    		    ['fontname', ['fontname']],
    		    ['color', ['color']],
    		    ['para', ['ul', 'ol', 'paragraph']],
    		    ['height', ['height']],
    		    ['insert', ['picture', 'hr']],
    		    ['table', ['table']]
    		  ],
    		  fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New', 'Merriweather', 'Verdana', 'Cambria', 'Cochin', 'Georgia', 'Times', 'Times New Roman', 'serif'],
    		fontSizes: ['8', '9', '10', '11', '12', '14', '16', '18', '24', '36', '48' , '64', '82', '150'],
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