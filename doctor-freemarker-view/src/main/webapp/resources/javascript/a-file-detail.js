$(document).ready(function() {

	var fileModel = new BucketFile();
	ko.applyBindings(fileModel);
	fileModel.loadFile();
})

function BucketFile() {
	var self = this;

	self.getKeyParam = function() {
		const params = new Proxy(new URLSearchParams(window.location.search), {
		  get: (searchParams, prop) => searchParams.get(prop),
		});
		// Get the value of "some_key" in eg "https://example.com/?some_key=some_value"
		return params.key; //
	}
	
	var keyParam = self.getKeyParam();
	// Url
	var rootContext = $('#root-context').val();
	var goToDetailUrl = rootContext + '/admin/bucket/file/detail?key=';
	var goToDetailUpdateUrl = rootContext + '/admin/bucket/file/update';

	var agree = 'Đồng ý';
	var cancel = 'Không';
	
	self.key = ko.observable();
	self.objectClass = ko.observable();
	self.uri = ko.observable();
	self.createdDate = ko.observable();

	self.loadFile = function() {
		// get service id from url
		var getUrl = goToDetailUrl + keyParam;

		// load service
		$.ajax({
			type : "GET",
			url : getUrl,
			dataType : 'json',
			success : function(res) {
				console.log(res);
				if (res.code == 200) {
					self.key(res.data.key);
					self.createdDate(res.data.createdDate);
					self.objectClass(res.data.objectClass);
					self.uri(res.data.uri);
				}
			}, error: function(e) {
				console.log(e)
			}
		});
	};

    self.formatDate = function(timestamp) {
	    var date = new Date(timestamp/1000);
		return date.toISOString().split('T')[0];
    }
}