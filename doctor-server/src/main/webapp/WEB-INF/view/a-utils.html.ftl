<!DOCTYPE html>
<html lang="en">

<head>
    <#include "a-headerinc.html.ftl">
</head>

<body>
    <div class="wrapper">
        <#include "a-nav-left.html.ftl">
        <div class="main-panel">
            <nav class="navbar navbar-transparent navbar-absolute">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="#"> Giao diện </a>
                    </div>
                    <div class="collapse navbar-collapse">
                        <ul class="nav navbar-nav navbar-right">  
                            <li>
                                <a href="#pablo" class="dropdown-toggle" data-toggle="dropdown">
                                    <i class="material-icons">person</i>
                                    <p class="hidden-lg hidden-md">Profile</p>
                                </a>
                            </li>
                        </ul>	
                    </div>
                </div>
            </nav>
            <div class="content">
                <div class="container-fluid">
                    <div class="row">              
                        <div class="col-lg-12 col-md-12">
                            <div class="card">
                                <div class="card-header" data-background-color="green">
                                    <h4 class="title">Hình ảnh trang chủ</h4>
                                </div>
                                <div class="card-content table-responsive">
                                    
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <#include "a-footer.html.ftl">
        </div>
    </div>
</body>
</html>