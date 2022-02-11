$(document).ready(function() {
	//KoSelectHandler();
	var bookingModel = new Booking();
	ko.applyBindings(bookingModel);
	bookingModel.getStatuses();
	bookingModel.getPageNo('P').done(function (res) {	
		// Initialize pagination
		bookingModel.initPagination();
	});
	//bookingModel.initSearch();
})

function Booking() {
	var self = this;
	var pageSize = 5;

	// Url
	var rootContext = $('#root-context').val();
	var getPageNoUrl = rootContext + '/admin/bookings/all';

	// Message
	var agree = 'Đồng ý';
	var cancel = 'Không';

	// Observable

	self.status = ko.observable('');
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
			'name': 'Pending'
		}, {
			'status': 'C',
			'name': 'Completed'
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
				pageOptions.totalPages = Math.ceil(totalService / pageSize);
			}
		});
	}

	self.loadBooking = function(pageNo, pageSize, pageSort, status) {
		var pageable = {
			page : pageNo,
			size : pageSize,
			sort : pageSort,
			status: status
		}
		$.ajax({
			type : "GET",
			url : getPageNoUrl,
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
			$('#pagination-post').twbsPagination(pageOptions);
		}
	}

    self.formatDate = function(date) {
    	var result = date.replace('T', ' ');
    	return result;
    }
}