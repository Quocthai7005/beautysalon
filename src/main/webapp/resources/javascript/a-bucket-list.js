$(document).ready(function() {
	//KoSelectHandler();
	var bucketModel = new Bucket();
	ko.applyBindings(bucketModel);
	bucketModel.getPageNo(null).done(function (res) {	
		bucketModel.initPagination();
	});
})

function Bucket() {
	var self = this;
	var pageSize = 10;

	// Url
	var rootContext = $('#root-context').val();
	var getFilesUrl = rootContext + '/admin/bucket/news';
	var getPageNoUrl = rootContext + '/admin/bucket/news/no';

	// Observable
	self.files = ko.observableArray([]);
	self.lastKey = ko.observableArray();

	var pageOptions = {
		totalPages: 1,
		visiblePages: 3,
		startPage: 1,
		first: 'Trang đầu',
		last: 'Trang cuối',
		prev: 'Trang trước',
		next: 'Trang sau',
		onPageClick : function(event, page) {
			self.loadBucket(page - 1, pageSize, 'asc', self.lastKey());
		}
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

	self.loadBucket = function(pageNo, pageSize, pageSort, Lkey) {
		var pageable = {
			page : pageNo,
			size : pageSize,
			sort : pageSort,
			lastKey: Lkey
		}
		$.ajax({
			type : "GET",
			url : getFilesUrl,
			dataType : 'json',
			data : pageable,
			success : function(res) {		
				console.log(res);
				self.files(res.data.content);
				var lastKey = null;
				var length = self.files().length;
				if (length > 0) {
					lastKey = self.files()[length - 1].key;
				}
				self.lastKey(lastKey);
				console.log(lastKey);
			}
		});
	}
	
	self.initPagination = function() {
		if (pageOptions.totalPages > 0) {
			$('#pagination-bucket').twbsPagination(pageOptions);
		}
	}

    self.formatDate = function(date) {
    	var result = date.replace('T', ' ');
    	return result;
    }
}