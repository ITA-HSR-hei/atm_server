<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="navbar navbar-inverse navbar-fixed-top">
	<div style="float: left; width: 150px;"><img alt="logo" src="../resources/img/airplane.png" style="height: 40px"></div>
	<div class="navbar-inner">
		<div class="container-fluid">
			<button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
			<a class="brand" href="#"><spring:message code="project.name" /></a>
			<div class="nav-collapse collapse">
				
				<ul class="nav">
					<!-- <li><a href="home"><spring:message code="navigation.home" /></a></li> -->
					<li><a href="timeperiod"><spring:message code="navigation.timeperiod" /></a></li>
					<li><a href="map"><spring:message code="navigation.map" /></a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>
</div>