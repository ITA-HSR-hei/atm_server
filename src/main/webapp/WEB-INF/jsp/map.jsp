<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:template>

	<jsp:attribute name="head">

<script type="text/javascript"
			src="http://maps.googleapis.com/maps/api/js?key=AIzaSyD7aV_rNQDmmcQCJ0fWYBqqCxn_YEUg-Hk&sensor=false"></script>
<script type="text/javascript" src="../resources/js/atm/stationList.js"></script>
<script type="text/javascript" src="../resources/js/atm/maps.js"></script>


</jsp:attribute>


	<jsp:attribute name="menu">
		<jsp:include page="/WEB-INF/template/menu.jsp" />
	</jsp:attribute>


	<jsp:body>
	
	<div class="span9">
		
		<div class="hero-unit" style="margin-left: 0px; margin-right: 50px; padding: 0px; background: white">
				
		<div id="map_canvas" style="width: 100%; height: 700px;"></div>
		
				
			</div>
    	
    	</div>
    	
    
	</jsp:body>

</t:template>