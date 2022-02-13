<div class="sidebar" data-background-color="orange" data-image="/spa/resources/image/other/sidebar-5.jpg">
	<div class="logo">
		<a href="<@spring.url '/home'/>">
			<img src="<@spring.url '/resources/image/other/br.png'/>" width="120" alt="brand">
		</a>
	</div>
	<div class="sidebar-wrapper">
		<ul class="nav">
			<li class="nav-item ">
				<a href="<@spring.url '/admin/main'/>">
					<i class="material-icons">dashboard</i>
					<p>TRANG CHỦ</p>
				</a>	
			</li>
			<li class="nav-item">
				<a class="nav-link collapsed" data-toggle="collapse" href="#contents" aria-expanded="false">
					<i class="material-icons">content_paste</i>
					<p>
						NỘI DUNG
						<b class="caret"></b>
					</p>
				</a>
				<div class="collapse" id="contents" style="">
					<ul class="nav">
						<li class="nav-item ">
							<a class="nav-link" href="<@spring.url '/admin/service-group-list'/>">
								<i class="material-icons">content_paste</i>
								<p>NHÓM DỊCH VỤ</p>
							</a>
						</li>
						<li class="nav-item ">
							<a class="nav-link" href="<@spring.url '/admin/service-list'/>">
								<i class="material-icons">content_paste</i>
								<p>DỊCH VỤ</p>
							</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="<@spring.url '/admin/news-list'/>">
								<i class="material-icons">library_books</i>
								<p>TRANG TIN</p>
							</a>
						</li>
						<li class="nav-item ">
							<a class="nav-link" ref="<@spring.url '/admin/utils'/>">
								<i class="material-icons">bubble_chart</i>
								<p>GIAO DIỆN</p>
							</a>
						</li>
						<li class="nav-item ">
							<a class="nav-link" href="<@spring.url '/admin/bucket'/>">
								<i class="material-icons">cloud_queue</i>
								<p>AWS S3</p>
							</a>
						</li>
					</ul>
				</div>
			</li>
			<li class="nav-item">
				<a class="nav-link collapsed" data-toggle="collapse" href="#customer" aria-expanded="false">
					<i class="material-icons">face</i>
					<p>
						KHÁCH HÀNG
						<b class="caret"></b>
					</p>
				</a>
				<div class="collapse" id="customer" style="">
					<ul class="nav">
						<li class="nav-item ">
							<a class="nav-link" href="<@spring.url '/admin/bookings'/>">
								<i class="material-icons">watch_later</i>
								<p>LỊCH HẸN</p>
							</a>
						</li>
						<li class="nav-item ">
							<a class="nav-link" href="<@spring.url '/admin/bookings'/>">
								<i class="material-icons">bookmark_border</i>
								<p>SUBSCRIPTION</p>
							</a>
						</li>
					</ul>
				</div>
			</li>
			<li class="nav-item">
				<a class="nav-link collapsed" data-toggle="collapse" href="#account" aria-expanded="false">
					<i class="material-icons">person</i>
					<p>
						TÀI KHOẢN
						<b class="caret"></b>
					</p>
				</a>
				<div class="collapse" id="account" style="">
					<ul class="nav">
						<li class="nav-item ">
							<a class="nav-link" href="<@spring.url '/admin/account'/>">
								<i class="material-icons">person</i>
								<p>TÀI KHOẢN</p>
							</a>
						</li>
						<li class="nav-item ">
							<a class="nav-link" href="<@spring.url '/admin/messenger'/>">
								<i class="material-icons">share</i>
								<p>FACEBOOK</p>
							</a>
						</li>
					</ul>
				</div>
			</li>
		</ul>
		<form id="logoutForm" method="GET" action="${rootContext.getContextPath() + '/admin/logout'}">
			<button type="submit" class="btn btn-info" type="button">Đăng xuất</button>
		</form>
	</div>
</div>