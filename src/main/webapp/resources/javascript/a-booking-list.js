$(document).ready(function() {
	//KoSelectHandler();
	var bookingModel = new Booking();
	ko.applyBindings(bookingModel);
	bookingModel.getStatuses();
	bookingModel.getPageNo('').done(function (res) {	
		// Initialize pagination
		console.log("sdfsdf");
		bookingModel.initPagination();
	});
})

function Booking() {
	var self = this;
	var pageSize = 5;

	// Url
	var rootContext = $('#root-context').val();
	var getbookingsUrl = rootContext + '/admin/bookings/all';
	var getPageNoUrl = rootContext + '/admin/bookings/no';
	var goToDetailUrl = rootContext + '/admin/bookings/detail';
	// Message
	var agree = 'Đồng ý';
	var cancel = 'Không';

	// Observable
	self.status = ko.observable();
	self.bookings = ko.observableArray([]);
	self.statuses = ko.observableArray([]);

	var pageOptions = {
		totalPages: 1,
		visiblePages: 3,
		startPage: 1,
		first: 'Trang đầu',
		last: 'Trang cuối',
		prev: 'Trang trước',
		next: 'Trang sau',
		onPageClick : function(event, page) {
			// get group id
			self.loadBookings(page - 1, pageSize, 'asc', self.status());
		}
	}
	
	self.status.subscribe(function() {
		
		// Destroy old pagination
		$('#pagination-booking').twbsPagination('destroy');
		self.bookings([]);
		
		// get service group quantity
		self.getPageNo(self.status()).done(function (res) {
			// Initialize pagination
			self.initPagination();
		});
	});

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

	self.getPageNo = function(status) {
		var query = {
			status: status,
		}
		return $.ajax({
			type : "GET",
			data: query,
			url : getPageNoUrl,
			success : function(res) {
				console.log(res);
				var totalService = res.data;
				pageOptions.totalPages = totalService == 0 ? 1 : Math.ceil(totalService / pageSize);
			}
		});
	}

	self.loadBookings = function(pageNo, pageSize, pageSort, status) {
		var pageable = {
			page : pageNo,
			size : pageSize,
			sort : pageSort,
			status: status
		}
		$.ajax({
			type : "GET",
			url : getbookingsUrl,
			dataType : 'json',
			data : pageable,
			success : function(res) {		
				console.log(res);
				self.bookings(res.data.content);
			}
		});
	}
	
	self.initPagination = function() {
		if (pageOptions.totalPages > 0) {
			$('#pagination-booking').twbsPagination(pageOptions);
		}
	}

	self.initSearch = function() {
		$('#search-btn').on('click', function() {
			$('#pagination-booking').twbsPagination('destroy');
			self.bookings([]);
			
			// get service group quantity
			self.getPageNo(self.status()).done(function (res) {
				// Initialize pagination
				self.initPagination();
			});
		});
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