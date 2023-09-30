<div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>

    <#-- Wrapper for slides -->
    <div class="carousel-inner">
    			<div class="item active">
                    <img src="<@spring.url '/resources/image/Majestic/carousel1.jpg'/>" alt="brand">
	        	</div>
	        	<div class="item">
                    <img src="<@spring.url '/resources/image/Majestic/carousel2.jpg'/>" alt="brand">
	        	</div>
                <div class="item">
                    <img src="<@spring.url '/resources/image/Majestic/carousel3.jpg'/>" alt="brand">
                </div>
    </div>

    <#-- Left and right controls -->
    <a class="left carousel-control" href="#myCarousel" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right"></span>
        <span class="sr-only">Next</span>
    </a>
</div>