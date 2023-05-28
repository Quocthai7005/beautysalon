$(document).ready(function() {
	var pictureModel = new Picture();
	ko.applyBindings(pictureModel, document.getElementById("picture-list"));
	pictureModel.loadImages();
	
	var contactModel = new Contact();
	ko.applyBindings(contactModel, document.getElementById("contact-info"));
	contactModel.loadContact();
	contactModel.initValidator();
	
})

function Picture() {
	
	var self = this;
	// Url
	var rootContext = $('#root-context').val();
	var saveUrl = rootContext + '/admin/image-update';
	var loadUrl = rootContext + '/admin/image-list/all';
	
	// Message
	var saveSuccess = 'Lưu thay đổi thành công';
	var saveFail = 'Không thể lưu thay đổi';
	var saveConfirm = 'Bạn muốn lưu thay đổi?';
	var agree = 'Đồng ý';
	var cancel = 'Không';
	
	// Observable
	self.images = ko.observableArray([]);
	
	self.id0 = ko.observable();
	self.base64Field0 = ko.observable();
	self.isShownHome0 = ko.observable();
	
	self.id1 = ko.observable();
	self.base64Field1 = ko.observable();
	self.isShownHome1 = ko.observable();
	
	self.id2 = ko.observable();
	self.base64Field2 = ko.observable();
	self.isShownHome2 = ko.observable();
	
	self.id3 = ko.observable();
	self.base64Field3 = ko.observable();
	self.isShownHome3 = ko.observable();
	
	self.id4 = ko.observable();
	self.base64Field4 = ko.observable();
	self.isShownHome4 = ko.observable();
	
	self.loadImages = function() {
    	$.ajax({
    		type : "GET",
    		url : loadUrl,
    		dataType : 'json',
    		success : function(res) {						
    			if (res.code == 200) {
    				self.images(res.data);
    				for (var i = 0; i < res.data.length; i++) {
    					self['id' + i](res.data[i].id);
    					self['isShownHome' + i](res.data[i].isShownHome);
    					self['base64Field' + i]('data: image/png;base64,' + res.data[i].base64Image);
    				}
    				self.initImageUpload();
    			}
    		}, error: function(e) {
    			console.log(e)
    		}
    	});
	}
	
	self.saveImage = function() {
		swal({
		  	text: saveConfirm,
		  	type: 'info',
		  	showCancelButton: true,
		  	confirmButtonText: agree,
		  	cancelButtonText: cancel,
		}).then((result) => {
			if (result.value) {
				for (var i = 0; i < self.images().length; i++) {
					self.images()[i].base64Image = self['base64Field' + i]().slice(23);
					self.images()[i].isShownHome = self['isShownHome' + i]();
				}
				$.ajax({
		    		type : "POST",
		    		url : saveUrl,
		    		data: JSON.stringify(self.images()),
		            contentType: "application/json; charset=utf-8",
		    		success : function(msg) {
		    			if (msg.data === true) {
			        		swal({
		        			  type: 'success',
		        			  text: 'Lưu thành công',
		        			});
			        	} else {
			        		swal({
			        			  type: 'error',
			        			  text: 'Không thể lưu'
			        			});
			        	}
		    		}, error: function(e) {
		    			console.log(e);
		    		}
		    	});
			}
		});
	}
	
	self.showImage = function(item) {
		console.log(item);
		swal({
  		  	imageUrl: self['base64Field' + item](),
  		  	imageHeight: 250,
  		  	imageAlt: 'header image'
  		});
	}
	
	self.initImageUpload = function() {
		$('.image-inp').change(function() {
			var imageItem = $(this).data('image');
			// get parent div
			var container = $(this).parent();
			
    		var i = container.find('.custom-image-upload').clone();
    		var file = container.find('.image-inp')[0].files[0].name;
    		container.find('.custom-image-upload').text(file);
    		var reader = new FileReader();
    	    reader.onload = function (e) {
    	        self['base64Field' + imageItem](e.target.result);
    	    };
    	    reader.readAsDataURL(container.find('.image-inp')[0].files[0]);
    	    console.log(self['base64Field' + imageItem]());
    	});
	}
}

function Contact() {
	
	var self = this;
	self.facebook = ko.observable();
	self.address = ko.observable();
	self.phone = ko.observable();
	self.map = ko.observable();
	
	var savedSuccess = 'Cập nhật thành công';
	var savedFail = 'Không thể chỉnh sửa';
	var entryRemind = 'Vui lòng nhập đúng dữ liệu';
	var saveConfirm = 'Bạn muốn cập nhật thông tin này?';
	var agree = 'Đồng ý';
	var cancel = 'Không';
	var contextPath = $('#root-context').val();
	var contactUrl = contextPath + '/admin/contact/info';
	var updateUrl = contextPath + '/admin/contact/update';
	
	self.loadContact = function() {
		$.ajax({
			type : "GET",
			url : contactUrl,
			success : function(res) {
				if (res.code == 200) {
					self.facebook(res.data.facebook);
					self.address(res.data.address);
					self.phone(res.data.phone);
					var x = res.data.map;
					self.map(res.data.map);
				}
				console.log(res);
			}, error: function(e) {
				console.log(e);
			}
		});
	}
	
	self.saveContact = function() {
		console.log('save')
		var validator = $('#contact-form').data('bootstrapValidator');
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
						address: self.address(),
						phone: self.phone(),
						map: self.map(),
						facebook: self.facebook()
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
		            			  text: savedSuccess
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
	
	self.initValidator = function() {
		$('#contact-form').bootstrapValidator({
            message: 'This value is not valid',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                address: {
                    message: 'Địa chỉ không hợp lệ',
                    validators: {
                        stringLength: {
                            max: 255,
                            message: 'Tối đa 255 ký tự'
                        }
                    }
                },
                facebook: {
                    message: 'Địa chỉ facebook không hợp lệ',
                    validators: {
                        stringLength: {
                            max: 255,
                            message: 'Tối đa 255 ký tự'
                        }
                    }
                },
                phone: {
                    message: 'Số điện thoại không đúng',
                    validators: {
                    	regexp: {
                            regexp: /^[0-9\+]{1,}[0-9\-]{3,15}$/i,
                            message: 'Số điện thoại không hợp lệ'
                        }
                    }
                },
            }
        });
	}
}