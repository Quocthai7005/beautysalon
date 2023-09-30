<#--<nav class="navbar navbar-default navbar-fixed-top" id="nav-container" role="navigation">-->
<#--    <div class="container">-->
<#--        <div class="navbar-header">-->
<#--        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#menu-navbar">-->
<#--            <span class="icon-bar"></span>-->
<#--            <span class="icon-bar"></span>-->
<#--            <span class="icon-bar"></span>-->
<#--        </button>-->
<#--        <a class="navbar-brand" href="<@spring.url '/home'/>">-->

<#--        </a>-->
<#--        </div>-->
<#--        <div class="collapse navbar-collapse mx-auto" id="menu-navbar">-->
<#--            <ul class="nav navbar-nav">-->
<#--                <li class="${(menu=='service')?then('active','')} dropdown">-->
<#--                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><@spring.message "menu.services"/> <b class="caret"></b></a>-->
<#--                    <ul class="dropdown-menu">-->
<#--                        <#list menuServices as service>-->
<#--                            <li><a href="<@spring.url '/service/'/>${service.url}">${service.name}</a></li>-->
<#--                        </#list>-->
<#--                    </ul>-->
<#--                </li>-->
<#--                <li class="${(menu=='home')?then('active','')}"><a href="<@spring.url '/home'/>"><img src="<@spring.url '/resources/image/Majestic/logo2.png'/>" width="150" alt="brand"></a></li>-->
<#--                <li class="${(menu=='contact')?then('active','')}"><a href="<@spring.url '/contact'/>"><@spring.message "menu.contact"/></a></li>-->
<#--            </ul>-->

<#--            <div class="navbar-nav mx-auto">-->
<#--                <a class="nav-link active" href="#">Home <span class="sr-only">(current)</span></a>-->
<#--                <a class="nav-link" href="#">Features</a>-->
<#--                <a class="nav-link" href="#">Pricing</a>-->
<#--                <a class="nav-link disabled" href="#">Disabled</a>-->
<#--            </div>-->
<#--        </div>-->
<#--    </div>-->
<#--</nav>-->


<nav class="navbar navbar-default navbar-fixed-top" id="nav-container" role="navigation" style="background-image: -webkit-linear-gradient(bottom, #f2f2e8 0%, #ececd7 100%);">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#menu-navbar">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="<@spring.url '/home'/>">

        </a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse navbar-ex1-collapse" style="text-align: center;" id="menu-navbar">
        <ul class="nav navbar-nav">
            <li class="${(menu=='home')?then('active','')}"><a href="<@spring.url '/home'/>"><@spring.message "menu.greetings"/></a></li>
            <li class="${(menu=='pricelist')?then('active','')}"><a href="<@spring.url '/pricelist'/>"><@spring.message "menu.price"/></a></li>
            <li class="${(menu=='home')?then('active','')}"><a href="<@spring.url '/home'/>"><img src="<@spring.url '/resources/image/Majestic/logo2.png'/>" width="150" alt="brand"></a></li>
            <li class="${(menu=='booking')?then('active','')}"><a href="<@spring.url '/booking'/>"><@spring.message "menu.booking"/></a></li>
            <li class="${(menu=='contact')?then('active','')}"><a href="<@spring.url '/contact'/>"><@spring.message "menu.contact"/></a></li>
        </ul>
    </div><!-- /.navbar-collapse -->
</nav>