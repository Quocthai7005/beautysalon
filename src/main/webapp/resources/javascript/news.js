$(document).ready(function() {
	//KoSelectHandler();
	var newsModel = new News();
	ko.applyBindings(newsModel);
	newsModel.getPageNo('').done(function (res) {	
		// Initialize pagination
		newsModel.initPagination();
	});
	newsModel.initSearch();
})

function News() {
	var self = this;
	var pageSize = 4;
	
	// Url
	var rootContext = $('#root-context').val();
	var getPostsUrl = rootContext + '/news/page'
	var getPageNoUrl = rootContext + '/news/page/post/no';
	
	// Observable
	self.groupId = ko.observable();
	self.searchText = ko.observable('');
	self.newsPosts = ko.observableArray([]);
	
	var pageOptions = {
			totalPages: 1,
			visiblePages: 3,
			startPage: 1,
			first: '<<',
			last: '>>',
			prev: '<',
			next: '>',
			onPageClick : function(event, page) {
				// get group id
				self.loadNews(page - 1, pageSize, 'asc', self.searchText());
			}
		}
	
	self.groupId.subscribe(function() {
		
		// Destroy old pagination
		$('#pagination-post').twbsPagination('destroy');
		self.newsPosts([]);
		
		// get service group quantity
		self.getPageNo(self.searchText()).done(function (res) {
			// Initialize pagination
			self.initPagination();
		});
	});
	
	self.initSearch = function() {
		$('#search-btn').on('click', function() {
			$('#pagination-post').twbsPagination('destroy');
			self.newsPosts([]);
			
			// get service group quantity
			self.getPageNo(self.searchText()).done(function (res) {
				// Initialize pagination
				self.initPagination();
			});
		});
	}
	
	self.getPageNo = function(searchText) {
		var query = {
			groupId: 0,
			searchText: searchText
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
	
	self.loadNews = function(pageNo, pageSize, pageSort, searchText) {
		var that = this;
		var pageable = {
			page : pageNo,
			size : pageSize,
			sort : pageSort,
			groupId: 0,
			searchText: self.searchText()
		}
		$.ajax({
			type : "GET",
			url : getPostsUrl,
			dataType : 'json',
			data : pageable,
			success : function(res) {		
				console.log(res);
				self.newsPosts(res.data.content);
			}
		});
	}
	
	self.initPagination = function() {
		if (pageOptions.totalPages > 0) {
			$('#pagination-post').twbsPagination(pageOptions);
		}
	}
	
	self.toPostDetail = function(data) {
		const url = rootContext + '/news/post/' + data.url;
    	window.location.href = url;
	}
}