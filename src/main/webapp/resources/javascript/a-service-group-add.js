$(document).ready(function() {
	
	serviceModel = new Service();
	
	ko.applyBindings(serviceModel);
	serviceModel.initSummernote();
	serviceModel.initImageUpload();
	serviceModel.initValidator();
})

function Service() {
	
	var self = this;
	
	// Url
	var rootContext = $('#root-context').val();
	var saveUrl = rootContext + '/admin/service-group-create';
	var serviceGroupListUrl = rootContext + '/admin/service-group-list';
	var validateUrl = rootContext + '/admin/service-group-validate/url/noid';
	
	// Message
	var savedSuccess = 'Tạo mới thành công';
	var savedFail = 'Không thể tạo mới';
	var entryRemind = 'Vui lòng nhập đúng dữ liệu';
	var saveConfirm = 'Bạn muốn tạo nhóm dịch vụ này';
	var returnList = 'Bạn muốn quay lại danh sách nhóm dịch vụ?';
	var agree = 'Đồng ý';
	var cancel = 'Không';
	
	// Observable
    self.name = ko.observable();
    self.id = ko.observable();
    self.url = ko.observable();
    self.content = ko.observable();
    self.image = ko.observable()
    
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
    
    self.save = function() {	
    	var validator = $('#create-service-form').data('bootstrapValidator');
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
  						  id: self.id(),
  						  name: self.name(),
  						  url: self.url(),
  						  image: null,
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
    	$('#create-service-form').bootstrapValidator({
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
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
                                    url: validator.getFieldElements('url').val(),
                                };
                            },
                        }
                    }
                },
                image: {
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



