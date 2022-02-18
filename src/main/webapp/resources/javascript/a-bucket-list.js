$(document).ready(function() {
	var bucketModel = new Bucket();
	ko.applyBindings(bucketModel);
	bucketModel.getPageNo(null).done(function (res) {	
		bucketModel.initPagination();
	});
})

function Bucket() {
	var self = this;
	var pageSize = 10;
	
	var deleteSuccess = 'Xoá thành công';
	var deleteFail = 'Không thể xoá';
	var deleteRemind = 'Bạn đã xoá tất cả dịch vụ con chưa?';
	var deleteConfirm = 'Bạn muốn xoá tin này?';
	var agree = 'Đồng ý';
	var cancel = 'Không';
	
	self.getUrlParameter = function getUrlParameter(sParam) {
	    var sPageURL = window.location.search.substring(1),
	        sURLVariables = sPageURL.split('&'),
	        sParameterName,
	        i;
	
	    for (i = 0; i < sURLVariables.length; i++) {
	        sParameterName = sURLVariables[i].split('=');
	
	        if (sParameterName[0] === sParam) {
	            return sParameterName[1] === undefined ? true : decodeURIComponent(sParameterName[1]);
	        }
	    }
	    return false;
	};

	var dir = self.getUrlParameter('directory');

	// Url
	var rootContext = $('#root-context').val();
	var getFilesUrl = rootContext + '/admin/bucket/files';
	var getListPage = rootContext + '/admin/bucket?directory=' + dir;
	var getPageNoUrl = rootContext + '/admin/bucket/files/no?directory=' + dir;
	var fileDetailLink = rootContext + '/admin/bucket/file?key=';
	var deleteUrl = rootContext + '/admin/bucket/file/remove?';

	// Observable
	self.files = ko.observableArray([]);

	var pageOptions = {
		totalPages: 1,
		visiblePages: 3,
		startPage: 1,
		first: 'Trang đầu',
		last: 'Trang cuối',
		prev: 'Trang trước',
		next: 'Trang sau',
		onPageClick : function(event, page) {
			self.loadBucket(page - 1, pageSize, 'asc', dir);
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

	self.loadBucket = function(pageNo, pageSize, pageSort, dir) {
		var pageable = {
			page : pageNo,
			size : pageSize,
			sort : pageSort,
			directory: dir
		}
		$.ajax({
			type : "GET",
			url : getFilesUrl,
			dataType : 'json',
			data : pageable,
			success : function(res) {
				console.log(res);
				self.files(res.data.content);
			}
		});
	}
	
	self.initPagination = function() {
		if (pageOptions.totalPages > 0) {
			$('#pagination-bucket').twbsPagination(pageOptions);
		}
	}

    self.formatDate = function(timestamp) {
	    var date = new Date(timestamp);
		return date.toISOString().split('T')[0];
    }

	self.formatSize = function(sizeInByte) {
		return Math.round(sizeInByte / 1024);
	}

	self.getFileDetailLink = function(key) {
		return fileDetailLink + key;
	}
	
	self.deleteFile = function(data) {
		var directory = data.key.split("/")[0];
		var name = data.key.split("/")[1];
		var url = deleteUrl + 'name=' + name + '&directory=' + directory;
		swal({
			  	text: "Bạn muốn xoá file này?",
			  	type: 'warning',
			  	showCancelButton: true,
			  	confirmButtonText: agree,
			  	cancelButtonText: cancel,
			}).then((result) => {
				if (result.value) {
					$.ajax({
						type: 'DELETE',
						url: url,
						success: function(msg) {
							if (msg.code === 200) {
								if (msg.data === true) {
									swal({
									  type: 'success',
									  text: deleteSuccess,
									  onClose: function() {
										  window.location.href = getListPage;
									  }
									});
								} else {
									swal({
									  type: 'error',
									  text: deleteFail,
									  footer: deleteRemind,
									});
								}
							}
						},
						error: function(e) {
							console.log(e);
						}
					});
				}
			});
	};
}