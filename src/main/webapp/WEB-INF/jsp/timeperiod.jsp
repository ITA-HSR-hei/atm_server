<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:template>

<jsp:attribute name="head">


<script type="text/javascript" src="../resources/js/highstock/highstock.src.js"></script>
<script type="text/javascript" src="../resources/js/highstock/themes/grid.js"></script>
<script type="text/javascript" src="../resources/js/atm/stationList.js"></script>
<script type="text/javascript" src="../resources/js/atm/timeperiod.js"></script>
<script type="text/javascript" src="../resources/js/spin/spin.js"></script>

<script type="text/javascript" src="../resources/js/bootstrap/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="../resources/js/bootstrap/bootstrap-select.min.js"></script>

<link href="../resources/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css" />		

<style>
	.mySpinner{
		left: 50%;
		top: 250px;
		
	
	}
</style>
</jsp:attribute>


	<jsp:attribute name="menu">
		<jsp:include page="/WEB-INF/template/menu.jsp" />
		
	</jsp:attribute>
	
	
	
	

	<jsp:body>
		<div class="span9">
		
			<div class="hero-unit" style="margin-left: 0px; margin-right:50px; padding: 0px; background: white">
				<div id="spinnerContainer" class="spinner" style="left: 100; top:  0; bottom: 0;right: 100">
				</div>
				<div id="container"	style="width: 100%; height: 500px;"></div>
			</div>
			
			<div class="row-fluid">
				
				<div style="text-align: center; height: 200px">
					<!-- 
					<div style="float: left; padding: 20px;">
						<div >
							<label for="selectManualDate">Schnellauswahl: </label>
							<select class="selectpicker" id="selectManualDate">
								<optgroup label="Heute">
									<option>Ganzer Tag</option>
								</optgroup>
									<optgroup label="Morgen">
									<option value="today">Ganzer Tag</option>
									<option value="last30min">letzte 30 Minuten</option>
									<option>letze Stunde</option>
								</optgroup>
									<optgroup label="Gestern">
									<option>von 6:00 Uhr bis 18.00 Uhr</option>
								</optgroup>
							</select>
						</div>
					</div> -->
					
					
					
					<div style="float: left; padding: 20px;">
						
						<div id="dateDay" class="input-append date" style="width: 250px">
							
							<label for="inputDateTo">Tageswahl: </label>
							<button id="previousDay" class="btn" style="margin-right: 5px"><i class="icon-arrow-left"></i></button>
							<input data-format="dd.MM.yyyy" type="text" id="inputDateTo"></input>
							<span class="add-on">
								<i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
							</span>
							<button id="nextDay" class="btn" style="margin-left: 5px"><i class="icon-arrow-right"></i></button>
						</div>
					</div>
					
				</div>	
					
			</div>
		</div>			
	</jsp:body>

</t:template>