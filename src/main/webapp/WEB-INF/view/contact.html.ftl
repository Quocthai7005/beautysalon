<!DOCTYPE html>
<html>   
	<head>
        <#include "headerinc.html.ftl">
        <link rel="stylesheet" type="text/css" href="<@spring.url '/resources/css/home.css'/>"/>
        <script src="<@spring.url '/resources/javascript/home.js'/>"></script>
        <#import "/spring.ftl" as spring/>
    </head>
    <body>
    	<input type="hidden" id="root-context" value="${rootContext.getContextPath()}"></input>
        <#-- Navigation bar -->
        <#include "nav.html.ftl">
        <#include "carousel.html.ftl">
        <div id="main">       
            <div class="container">   
				<#-- services -->
                <div class="row" id="contact-ctn">
                	<div class="col-lg-12 col-xs-12" id="address-text">
				        <h2 style="margin-bottom: 10px">${pageTexts[3].title}</h2>
				        <p><a href="${pageTexts[5].content}"><i class="fa fa-facebook" aria-hidden="true"></i> ${pageTexts[5].content}</a></p>
				        <p><span class="glyphicon glyphicon-home"></span>&nbsp;&nbsp; ${pageTexts[3].content}</p>
				        <p><span class="glyphicon glyphicon-phone-alt"></span>&nbsp;&nbsp; ${pageTexts[4].content}</p>
				    </div>
                    <div class="col-lg-12 col-xs-12" id="map-ctn">
                        ${pageTexts[6].content}
                    </div>
                </div>
            </div>  
        </div>
        <#include "footer.html.ftl">
    </body>
</html>