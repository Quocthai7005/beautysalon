<nav class="navbar navbar-default navbar-fixed-top" id="nav-container" role="navigation">
    <div class="container">
        <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#menu-navbar">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="<@spring.url '/home'/>">
            <img src="<@spring.url '/resources/image/other/br.png'/>" width="90" alt="brand">
        </a>
        </div>
        <div class="collapse navbar-collapse" id="menu-navbar">
        <ul class="nav navbar-nav navbar-right">
            <li class="${(menu=='home')?then('active','')}"><a href="<@spring.url '/home'/>"><@spring.message "menu.greetings"/></a></li>
            <li class="${(menu=='service')?then('active','')} dropdown">
            	<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><@spring.message "menu.services"/> <b class="caret"></b></a>
            	<ul class="dropdown-menu">          	
            		<#list menuServices as service>
	                	<li><a href="<@spring.url '/service/'/>${service.url}">${service.name}</a></li>
                	</#list>
            	</ul>
            </li>
            <li class="${(menu=='posts')?then('active','')}"><a href="<@spring.url '/posts'/>"><@spring.message "menu.posts"/></a></li>
            <li class="${(menu=='contact')?then('active','')}"><a href="<@spring.url '/contact'/>"><@spring.message "menu.contact"/></a></li>
     		<li class="${(menu=='booking')?then('active','')}"><a href="<@spring.url '/booking'/>"><@spring.message "menu.booking"/></a></li>  
        </ul>
        </div>
    </div>
</nav>