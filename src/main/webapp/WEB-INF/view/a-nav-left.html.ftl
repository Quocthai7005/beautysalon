<div class="sidebar" data-color="orange">
    <div class="logo">
        <a href="<@spring.url '/home'/>">
            <img src="<@spring.url '/resources/image/other/brand.png'/>" width="120" alt="brand">
        </a>
    </div>
    <div class="sidebar-wrapper">
        <ul class="nav">
            <li>
                <a href="<@spring.url '/admin/main'/>">
                    <i class="material-icons">dashboard</i>
                    <p>Trang chủ</p>
                </a>
            </li>
            <li>
                <a href="<@spring.url '/admin/service-group-list'/>">
                    <i class="material-icons">content_paste</i>
                    <p>Nhóm dịch vụ</p>
                </a>
            </li>
            <li>
                <a href="<@spring.url '/admin/service-list'/>">
                    <i class="material-icons">content_paste</i>
                    <p>Dịch vụ</p>
                </a>
            </li>
            <li>
                <a href="<@spring.url '/admin/news-list'/>">
                    <i class="material-icons">library_books</i>
                    <p>Trang tin</p>
                </a>
            </li>
            <li>
                <a href="<@spring.url '/admin/utils'/>">
                    <i class="material-icons">bubble_chart</i>
                    <p>Giao diện</p>
                </a>
            </li>
            <li>
                <a href="<@spring.url '/admin/account'/>">
                    <i class="material-icons">person</i>
                    <p>Quản lý tài khoản</p>
                </a>
            </li>
            <li>
                <a href="<@spring.url '/admin/messenger'/>">
                    <i class="material-icons">messenger</i>
                    <p>Quản lý FB Messenger</p>
                </a>
            </li>
        </ul>
        <form id="logoutForm" method="GET" action="${rootContext.getContextPath() + '/admin/logout'}">
			<button type="submit" class="btn btn-info" type="button">Đăng xuất</button>
		</form>
    </div>
    
</div>