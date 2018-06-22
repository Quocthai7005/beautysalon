$(document).ready(function() {
	var pictureModel = new Picture();
	ko.applyBindings(pictureModel);
	pictureModel.loadImages();
	
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
    					self['base64Field' + i](res.data[i].base64Image);
    				}
    				self.initImageUpload();
    			}
    		}, error: function(e) {
    			console.log(e)
    		}
    	});
	}
	
	self.save = function() {
		swal({
		  	text: saveConfirm,
		  	type: 'info',
		  	showCancelButton: true,
		  	confirmButtonText: agree,
		  	cancelButtonText: cancel,
		}).then((result) => {
			if (result.value) {
				for (var i = 0; i < self.images().length; i++) {
					self.images()[i].base64Image = self['base64Field' + i]().slice(22);
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
  		  	imageUrl: 'data:image/png;base64' + (self['base64Field' + item])(),
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