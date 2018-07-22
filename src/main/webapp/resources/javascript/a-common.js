$(document).ready(function() {
	$(document).ajaxError(function (e, xhr, settings) {
		var rootContext = $('#root-context').val();
		var login = rootContext + '/admin/login';
		swal({
			type: 'info',
			text: 'Hết phiên làm việc, vui lòng đăng nhập lại',
			onClose: function() {
			window.location.href = login;
		  }
		});
    });
});
