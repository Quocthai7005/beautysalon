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
<#--        <#include "carousel.html.ftl">-->
        <div id="main">       
            <div class="container" style="background-color: #fff">   
				<#-- services -->
                <div class="row" id="pricelist-ctn">
                	<div class="col-lg-12 col-xs-12" style="white-space: pre">
                        <#list serviceMap?keys as key>
                            <h3 style="margin-top: 20px">${key}</h3>
                            <table class="table table-striped" style="width:60%;align-self: center;">
                                <#list serviceMap[key] as service>
                                    <tr><td class="width40ptg text-left">${service.name}</td><td class="width20ptg text-center"><span>$</span>${service.price}</td></tr>
                                </#list>
                            </table>
                        </#list>
				    </div>
                </div>
            </div>  
        </div>
        <#include "footer.html.ftl">
    </body>
</html>