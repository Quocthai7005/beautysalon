$(document).ready(function() {
	var messenger = new Messenger();
	ko.applyBindings(messenger);
	messenger.loadMessengerInfo();
	messenger.initValidator();
});

function Messenger() {
	
	var self = this;
	
	self.appId = ko.observable();
	self.pageId = ko.observable();
	self.autoLogAppEvents = ko.observable();
	self.xfbml = ko.observable();
	self.version = ko.observable();
	self.minimized = ko.observable();
	
	// Messages
	var savedSuccess = 'Cập nhật thành công';
	var savedFail = 'Không thể chỉnh sửa';
	var entryRemind = 'Vui lòng nhập đúng dữ liệu';
	var saveConfirm = 'Bạn muốn cập nhật thông tin này?';
	var agree = 'Đồng ý';
	var cancel = 'Không';
	var contextPath = $('#root-context').val();
	var messengerUrl = contextPath + '/messenger/info';
	var updateUrl = contextPath + '/admin/messenger/update';
	
	self.loadMessengerInfo = function() {
		$.ajax({
			type : "GET",
			url : messengerUrl,
			success : function(res) {
				if (res.code == 200) {
					self.appId(res.data.appId);
					self.pageId(res.data.pageId);
					self.xfbml(res.data.xfbml);
					self.version(res.data.version);
					self.autoLogAppEvents(res.data.autoLogAppEvents);
					self.minimized(res.data.minimized);
				}
			}, error: function(e) {
				console.log(e);
			}
		});
	}
	
	self.saveMessengerInfo = function() {
		var validator = $('#messenger-form').data('bootstrapValidator');
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
						appId: self.appId(),
						pageId: self.pageId(),
						version: self.version(),
						xfbml: self.xfbml() == 'true' ? true : false,
						autoLogAppEvents: self.autoLogAppEvents() == 'true' ? true : false,
						minimized: self.minimized() == 'true' ? true : false,
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
		$('#messenger-form').bootstrapValidator({
            message: 'This value is not valid',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                appId: {
                    message: 'App ID không đúng',
                    validators: {
                        notEmpty: {
                            message: 'Vui lòng nhập App ID'
                        },
                        stringLength: {
                            max: 255,
                            message: 'Tối đa 255 ký tự'
                        }
                    }
                },
                pageId: {
                    message: 'Page ID không đúng',
                    validators: {
                        notEmpty: {
                            message: 'Vui lòng nhập Page ID'
                        },
                        stringLength: {
                            max: 255,
                            message: 'Tối đa 255 ký tự'
                        }
                    }
                },
                autoLogAppEvents: {
                    message: 'Auto Log App Events không đúng',
                    validators: {
                        notEmpty: {
                            message: 'Vui lòng nhập Auto Log App Events'
                        },
                        regexp: {
                            regexp: /^(true|false)$/i,
                            message: 'nhập true hoặc false'
                        }
                    }
                },
                xfbml: {
                    message: 'xfbml không đúng',
                    validators: {
                        notEmpty: {
                            message: 'Vui lòng nhập xfbml'
                        },
                        regexp: {
                            regexp: /^(true|false)$/i,
                            message: 'nhập true hoặc false'
                        }
                    }
                },
                xfbml: {
                    message: 'xfbml không đúng',
                    validators: {
                        notEmpty: {
                            message: 'Vui lòng nhập xfbml'
                        },
                        regexp: {
                            regexp: /^(true|false)$/i,
                            message: 'nhập true hoặc false'
                        }
                    }
                },
                version: {
                    message: 'Version không đúng',
                    validators: {
                        notEmpty: {
                            message: 'Vui lòng nhập version'
                        },
                        stringLength: {
                            max: 255,
                            message: 'Tối đa 255 ký tự'
                        }
                    }
                },
                minimized: {
                    message: 'Minimized không đúng',
                    validators: {
                        notEmpty: {
                            message: 'Vui lòng nhập minimized'
                        },
                        regexp: {
                            regexp: /^(true|false)$/i,
                            message: 'nhập true hoặc false'
                        }
                    }
                },
            }
        });
	}
	
}