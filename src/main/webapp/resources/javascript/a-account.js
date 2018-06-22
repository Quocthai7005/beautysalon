$(document).ready(function() {
	
	var accountModel = new Account();
	ko.applyBindings(accountModel);
	accountModel.username($('#username').val());
	accountModel.initValidation();
});

function Account() {
	
	var self = this;
	
	// Url
	var rootContext = $('#root-context').val();
	var saveUrl = rootContext + '/admin/account/update';
	var logoutUrl = rootContext + '/admin/logout';
	var agree = 'Đồng ý';
	var cancel = 'Không';
	
	// Message
	var saveSuccess = 'Đổi mật khẩu thành công, vui lòng đăng nhập lại';
	var saveFail = 'Không thể đổi mật khẩu';
	var saveConfirm = 'Bạn muốn đổi mật khẩu?';
	
	// Observable
	self.username = ko.observable();
	self.oldPassword = ko.observable();
	self.newPassword = ko.observable();
	self.retypePassword = ko.observable();
	
	var rootContext = $('#root-context').val();
	
	self.savePassword = function() {
		var validator = $('#user-form').data('bootstrapValidator');
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
							username: self.username(),
							oldPassword: self.oldPassword(),
							newPassword: self.newPassword(),
							confirmPassword: self.retypePassword()
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
		            			  text: saveSuccess,
		            			  onClose: function() {
		            				  window.location.href = logoutUrl;
		            			  }
		            			});
		    	        	} else {
		    	        		swal({
		    	        			  type: 'error',
		    	        			  text: saveFail
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
			  text: 'Vui lòng nhập đúng dữ liệu',
			});
    	}	
	}
	
	self.initValidation = function() {
		$('#user-form').bootstrapValidator({
            message: 'This value is not valid',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                'old-password': {
                    message: 'Tên không đúng',
                    validators: {
                        notEmpty: {
                            message: 'Vui lòng nhập tên'
                        }
                    }
                },
                password: {
                    validators: {
                        notEmpty: {
                            message: 'Vui lòng nhập mật khẩu mới'
                        },
                        identical: {
                            field: 'confirmPassword',
                            message: 'Mật khẩu không khớp'
                        }
                    }
                },
                confirmPassword: {
                	validators: {
                		notEmpty: {
                            message: 'Vui lòng xác nhập mật khẩu mới'
                        },
                        identical: {
                            field: 'password',
                            message: 'Mật khẩu không khớp'
                        }
                    }
                }
            }
        });
	}
}