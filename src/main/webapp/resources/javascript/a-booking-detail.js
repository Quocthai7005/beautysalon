$(document).ready(function() {

	var bookingModel = new Booking();
	ko.applyBindings(bookingModel);
	bookingModel.getStatuses();
	bookingModel.loadbooking();
})

function Booking() {
	var self = this;

	// Url
	var rootContext = $('#root-context').val();
	var getbookingsUrl = rootContext + '/admin/bookings/all';
	var getPageNoUrl = rootContext + '/admin/bookings/no';
	var goToDetailUrl = rootContext + '/admin/bookings/detail/';
	var goToDetailUpdateUrl = rootContext + '/admin/bookings/update';

	// Message
	var savedSuccess = 'Chỉnh sửa thành công';
	var savedFail = 'Không thể chỉnh sửa';
	var entryRemind = 'Vui lòng nhập đúng dữ liệu';
	var saveConfirm = 'Bạn muốn sửa tin này';
	var agree = 'Đồng ý';
	var cancel = 'Không';

	// Observable
	self.status = ko.observable();
	self.statuses = ko.observable([]);
	self.id = ko.observable();
	self.email = ko.observable();
	self.phone = ko.observable();
	self.consultDate = ko.observable();
	self.question = ko.observable();
	self.name = ko.observable();
	
	self.loadbooking = function() {
		// get service id from url
		var getUrl = goToDetailUrl + self.getId();

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
					self.phone(res.data.phone);
					self.email(res.data.email);
					self.consultDate(self.formatDate(res.data.consultDate));
					self.status(res.data.status);
					console.log(self.status()== 'P');
					self.question(res.data.question);
				}
			}, error: function(e) {
				console.log(e)
			}
		});
	};

	self.update = function() {
		var query = {
			'id': self.id(),
			'status': self.status()
		}
		
		swal({
  			text: saveConfirm,
  			type: 'info',
  			showCancelButton: true,
  			confirmButtonText: agree,
  			cancelButtonText: cancel,
  		}).then((result) => {
			$.ajax({
				type : "POST",
				url : goToDetailUpdateUrl,
				data: query,
				dataType : 'json',
				success : function(res) {
					console.log(res);
					if (res.code == 200) {
						window.location.href = rootContext + '/admin/bookings/';
					} else {
    	        		swal({
    	        			type: 'error',
    	        			text: savedFail
    	        		});
    	        	}
				}, error: function(e) {
				    swal({
	        			type: 'error',
	        			text: savedFail
	        		});
				}
			});
		});
	}

	self.getStatuses = function() {
		self.statuses([{
			'status': 'P',
			'name': 'CHỜ TRẢ LỜI'
		}, {
			'status': 'O',
			'name': 'ĐANG XỬ LÝ'
		}, {
			'status': 'C',
			'name': 'HOÀN TẤT'
		}]);
	}

	self.getId = function() {
		const params = new Proxy(new URLSearchParams(window.location.search), {
		  get: (searchParams, prop) => searchParams.get(prop),
		});
		// Get the value of "some_key" in eg "https://example.com/?some_key=some_value"
		return params.id; //
	}

	self.goToDetail = function(data) {
		var getUrl = goToDetailUrl + '?id=' + data.id;
		window.location.href = getUrl;
	}

	self.formatDate = function(date) {
		var result = date.replace('T', ' ');
		return result;
	}
}