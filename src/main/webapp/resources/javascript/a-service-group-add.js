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
	var savedSuccess = 'Create new service type successfully';
	var savedFail = 'Cannot create new service type';
	var entryRemind = 'Please type correct information';
	var saveConfirm = 'Do you really want to create this service type?';
	var returnList = 'Do you really want to leave this page?';
	var agree = 'Yes';
	var cancel = 'No';
	
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
                    message: 'Name is incorrect',
                    validators: {
                        notEmpty: {
                            message: 'Please type service type name'
                        },
                        stringLength: {
                            max: 45,
                            message: '45 letters max'
                        }
                    }
                }
            }
        });
    }
}



