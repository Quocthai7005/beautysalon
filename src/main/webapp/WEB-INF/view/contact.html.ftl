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
                <div class="row" id="contact-ctn">
                	<div class="col-lg-12 col-xs-12" id="address-text" style="white-space: pre">
				        <h3 style="margin-top: 20px"><@spring.message "contact.workingHours"/></h3>
                        <table style="max-width: 300px; align-self: center;text-align: left">
                            <tr><td>Monday  </td><td>9:30 - 7:00</td></tr>
                            <tr><td>Tuesday </td><td>9:30 - 7:00</td></tr>
                            <tr><td>Wednesday   </td><td>9:30 - 7:00</td></tr>
                            <tr><td>Thursday</td><td>9:30 - 7:00</td></tr>
                            <tr><td>Friday  </td><td>9:30 - 7:00</td></tr>
                            <tr><td>Saturday</td><td>9:30 - 6:00</td></tr>
                            <tr><td>Sunday  </td><td>ClOSED</td></tr>
                        </table>
                        <h3 style="margin-top: 20px"><@spring.message "contact.phone"/></h3>
                        <p>(508) 267-3006</p>
                        <h3 style="margin-top: 20px"><@spring.message "contact.address"/></h3>
                        <p>&nbsp;&nbsp; 1057 Main Street, Holden, Massachusetts 01520</p>
				    </div>
                    <div class="col-lg-12 col-xs-12" id="map-ctn">
                        
                    </div>
                </div>
            </div>  
        </div>
        <#include "footer.html.ftl">
    </body>
</html>