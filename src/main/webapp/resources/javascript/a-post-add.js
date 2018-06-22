$(document).ready(function() {
	
	var newsModel = new News();
	ko.applyBindings(newsModel);
	
	newsModel.initValidator();
	newsModel.initSummernote();
	newsModel.initImageUpload();
	newsModel.getServiceGroups();
	
});

function News() {
	
	var self = this;
	
	// Url
	var rootContext = $('#root-context').val();
	var saveUrl = rootContext + '/admin/news-create';
	var newsListUrl = rootContext + '/admin/news-list';
	var validateUrl = rootContext + '/admin/news-validate/url/noid';
	var loadServiceGroupsUrl = rootContext + '/admin/service-group-list/all'
	
	// Messages
	var savedSuccess = 'Tạo mới thành công';
	var savedFail = 'Không thể tạo mới';
	var entryRemind = 'Vui lòng nhập đúng dữ liệu';
	var saveConfirm = 'Bạn muốn tạo tin này';
	var returnList = 'Bạn muốn quay lại danh sách tin?';
	var agree = 'Đồng ý';
	var cancel = 'Không';
	
	// Observable
	self.id = ko.observable();
	self.name = ko.observable();
	self.url = ko.observable();
	self.image = ko.observable();
	self.groupId = ko.observable();
	self.intro = ko.observable();
	self.content = ko.observable();
	self.serviceGroups = ko.observableArray([]);
	
	self.save = function() {
		var validator = $('#create-news-form').data('bootstrapValidator');
    	validator.validate();
    	if (validator.isValid()) {
    		// Confirm before saving
    		swal({
    			  text: saveConfirm,
    			  type: 'info',
    			  showCancelButton: true,
    			  confirmButtonText: agree,
    			  cancelButtonText: cancel,
			}).then((result) => {
			  if (result.value) {
				  var data = {
						  id: null,
						  url: self.url(),
						  name: self.name(),
						  image: self.image().slice(22),
						  parentServiceId: self.groupId(),
						  parentServiceName: null,
						  intro: self.intro(),
						  content: $('#news-content-inp').summernote('code')
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
									  window.location.href = newsListUrl;
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
    		})	
    	} else {
    		swal({
			  type: 'error',
			  text: entryRemind,
			});
    	}	
	}
	
	self.initValidator = function() {
		$('#create-news-form').bootstrapValidator({
            message: 'This value is not valid',
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
				window.location.href = newsListUrl;
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
				}
			}, error: function(e) {
				console.log(e);
			}
		})
	}
	
	self.initSummernote = function() {
    	$('#news-content-inp').summernote({
            tabsize: 2,
            height: 100
    	});
    }
    
    self.showImage = function() {
    	swal({
  		  imageUrl: self.image(),
  		  imageHeight: 250,
  		  imageAlt: 'news name'
  		});
    }
	
}