<div class="sidebar" data-background-color="orange" data-image="<@spring.url '/resources/image/other/sidebar-5.jpg'/>">
	<div class="logo">
		<a href="<@spring.url '/home'/>">
			<img src="<@spring.url '/resources/image/Majestic/logo2.png'/>" width="120" alt="brand">
		</a>
	</div>
	<div class="sidebar-wrapper">
		<ul class="nav">
			<li class="nav-item ">
				<a href="<@spring.url '/admin/main'/>">
					<i class="material-icons">dashboard</i>
					<p>MAIN</p>
				</a>	
			</li>
			<li class="nav-item">
				<a class="nav-link collapsed" data-toggle="collapse" href="#contents" aria-expanded="false">
					<i class="material-icons">content_paste</i>
					<p>
						SERVICES
						<b class="caret"></b>
					</p>
				</a>
				<div class="collapse" id="contents" style="">
					<ul class="nav">
						<li class="nav-item ">
							<a class="nav-link" href="<@spring.url '/admin/service-group-list'/>">
								<i class="material-icons">content_paste</i>
								<p><@spring.message "menu.serviceTypes"/></p>
							</a>
						</li>
						<li class="nav-item ">
							<a class="nav-link" href="<@spring.url '/admin/service-list'/>">
								<i class="material-icons">content_paste</i>
								<p><@spring.message "menu.services"/></p>
							</a>
						</li>
					</ul>
				</div>
			</li>
			<li class="nav-item">
				<a class="nav-link collapsed" data-toggle="collapse" href="#customer" aria-expanded="false">
					<i class="material-icons">face</i>
					<p>
						<@spring.message "menu.customer"/>
						<b class="caret"></b>
					</p>
				</a>
				<div class="collapse" id="customer" style="">
					<ul class="nav">
						<li class="nav-item ">
							<a class="nav-link" href="<@spring.url '/admin/bookings'/>">
								<i class="material-icons">watch_later</i>
								<p><@spring.message "menu.booking"/></p>
							</a>
						</li>
					</ul>
				</div>
			</li>
			<li class="nav-item">
				<a class="nav-link collapsed" data-toggle="collapse" href="#account" aria-expanded="false">
					<i class="material-icons">person</i>
					<p>
						ACCOUNT
						<b class="caret"></b>
					</p>
				</a>
				<div class="collapse" id="account" style="">
					<ul class="nav">
						<li class="nav-item ">
							<a class="nav-link" href="<@spring.url '/admin/account'/>">
								<i class="material-icons">person</i>
								<p><@spring.message "menu.account"/></p>
							</a>
						</li>
					</ul>
				</div>
			</li>
		</ul>
		<form id="logoutForm" method="GET" action="${rootContext.getContextPath() + '/admin/logout'}">
			<button type="submit" class="btn btn-info" type="button">LOGOUT</button>
		</form>
	</div>
</div>