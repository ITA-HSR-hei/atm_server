var chart;
var spinner;
var chartHandler = new ChartHandler();
var dateHandler = new DateHandler();

var chartOptions = {
	    chart: {
			
			renderTo: 'container',
			type: 'spline',
			zoomType: 'x'
	    },
		
		colors: [
		   '#2f7ed8', 
		   '#0d233a', 
		   
		   '#910000', 
		   '#1aadce', 
		   '#492970',
		   '#f28f43', 
		   '#77a1e5', 
		   '#c42525', 
		   '#a6c96a'
		],
		
		
		xAxis: {
			type : 'datetime',
			minRange: 1000*60,
			plotBands: []
		},

	    

		yAxis: {
			min : 20,
			max: 80,
			
			title: {
				text: 'Sound Level dB(A)'
			}
		},
		
		navigator: {
		
			margin: 20,
		
			outlineColor: 'black',
			outlineWidth: 1,
		
			
			
//			series: {
//				fillOpacity: 1.0,
//				color: '#8bbc21'
//				//data: [[Date.UTC(2013, 3, 1), 1], [Date.UTC(2013, 3, 1, 23, 59), 1]]	
//			}
			
			
		},
		
		legend: {
			enabled: true
		
		},
		plotOptions: {
			spline : {
				marker : {
					enabled : false
				},
				events:{
					legendItemClick: function () {
						return false;
					}
				}	
			}
		},
		
		rangeSelector: {
		
			buttons: [
				{
					type: 'minute',
					count: 5,
					text: '5min'
				}, {
					type: 'minute',
					count: 15,
					text: '15min'
				}, {
					type: 'minute',
					count: 60,
					text: '1hour'
				}, {
					type: 'minute',
					count: 360,
					text: '6hour'
				}, {
					type: 'minute',
					count: 720,
					text: '12hour'
				}, {
					type: 'all',
					text: 'All'
				}],
		
			buttonTheme: { // styles for the buttons
				fill: '#066EA5',
				stroke: '#066EA5',
				width : 60,
				height : 14,
				style: {
						color: '#FFFFFF',
						fontSize: '10px',
						fontWeight: 'bold'
				}
			},
		
			inputEnabled: false
		},

	    title: {
			text: 'TITLE TO ADD'
		},

		subtitle: {
			text: 'SUBTITLE TO ADD'
		}
		
		
};




function ChartHandler(){
	var self = this;
	//var dateTimeHandler = new DateTimeHandler();
	
	self.stationsColor = [];
	
	
	self.createChart = function(dateFrom, dateTo){
		
		var selectedStations = stationHandler.getSelectedStations();
		var seriesCounter = 0;
		var seriesForChart = [];
		
	
		
		$.each(selectedStations, function(index, station){
			
			self.loadStationData(station.id, dateFrom, dateTo, function(stationData){
				console.log("received station Data: ");
				console.log(stationData);
				seriesCounter++;
				seriesForChart.push(stationData);
				
				if(seriesCounter == selectedStations.length){
					console.log("all data loaded, series array is: ");
					console.log(seriesForChart);
					self.displayChart(seriesForChart);
				}
			});
		});
		
	};
	
	
	self.loadStationData = function(stationId, dateFrom, dateTo, callback){
		
		var getStationData = {
				stationId: stationId,
				timestampFrom: dateFrom,
				timestampTo: dateTo
		};
		
		$.ajax({
			type : "GET",
			contentType : "application/json",
			url: '../public/0/getChartData',
			data: getStationData,
			dataType: "json",
			success: callback
		});
		
		
	};
	
	
	
	
	
	
	self.displayChart = function(seriesForChart){
		
		//add chartBand to options
//		var dateFrom = new Date(seriesForChart[0].dateFrom);
//		
//		var plotBandMorning = {
//			color: '#FCFFC5',
//			from: Date.UTC(dateFrom.getFullYear(), dateFrom.getMonth(), dateFrom.getDate()),
//			to: Date.UTC(dateFrom.getFullYear(), dateFrom.getMonth(), dateFrom.getDate(), 6)	
//		};
//		
//		var plotBandEvening = {
//			color: '#FCFFC5',
//			from: Date.UTC(dateFrom.getFullYear(), dateFrom.getMonth(), dateFrom.getDate(), 22),
//			to: Date.UTC(dateFrom.getFullYear(), dateFrom.getMonth(), dateFrom.getDate(), 23, 59, 59)	
//		};
//			
//		var	currentPlotBands = [plotBandMorning, plotBandEvening];
//		console.log("plot bands are: ");
//		console.log(currentPlotBands);
//		var currentChartOptions = chartOptions;
		
//		currentChartOptions.xAxis.plotBands = currentPlotBands;
		
		//navigator
		//chartOptions.navigator.series = {};
		//chartOptions.navigator.series.data = [ [seriesForChart[0].dateFrom, 1], [seriesForChart[0].dateTo, 1] ];
		
		
		//return date of station Data
//		if(seriesForChart != null){
//			console.log("data of series");
//			console.log(seriesForChart[0].data);
//		}
		
		
		chartOptions.series = seriesForChart;
		
		chart = new Highcharts.StockChart(chartOptions);
		
		spinner.stop();
		$('#container').css('visibility','visible');
		
		
	};
	
	
	self.addSerieToChart = function(stationId){
		console.log('add station to chart, with id: '+ stationId);
		chartHandler.loadStationData(stationId, dateHandler.dateFrom, dateHandler.dateTo, function(stationData){
			chart.addSeries(stationData);
			
		});
		
		
	};
	
	
	self.removeSerieFromChart = function(stationId){
		console.log('remove station from chart,  with id: '+stationId);
		var stationName = stationHandler.getStationName(stationId);
		var series = chart.series;
		for(var i = 0; i < series.length; i++){
			
			if(series[i].name == stationName){
				
				self.saveStationColor(stationId, chart.series[i].color);
				chart.series[i].remove(true);
			}
		}
		
	};
	
	
	
	self.getStationColor = function(stationId){

		if(self.stationsColor[stationId]){
			return self.stationsColor[stationId];
		}
		return null;
	};

	self.saveStationColor = function(stationId, color){
		self.stationsColor[stationId] = color;
	};
	
	

}


function DateHandler(){
	
	self = this;
	self.dateFrom;
	self.dateTo;
	
	
	self.changedDate = function(){
		var picker = $('#dateDay').data('datetimepicker');
		
		var current = picker.getDate();
		
		self.dateFrom =  Date.UTC(current.getFullYear(), current.getMonth(), current.getDate());
		self.dateTo = Date.UTC(current.getFullYear(), current.getMonth(), current.getDate(), 23, 59, 59, 999);
		
		spinner.spin();
		$('#spinnerContainer').append(spinner.el);
		chartHandler.createChart(self.dateFrom, self.dateTo);
		
		
	};
	
}



$(function() {
	
	//$('#container').css('visibility','hidden');
	
	var today = new Date();
	dateHandler.dateFrom =  Date.UTC(today.getFullYear(), today.getMonth(), today.getDate());
	dateHandler.dateTo = Date.UTC(today.getFullYear(), today.getMonth(), today.getDate(), 23, 59, 59, 999);
	
	chartHandler.createChart(dateHandler.dateFrom, dateHandler.dateTo);
	
	
	
	//Register Listener
	console.log("add listener");
	$('#stationList').on('change', 'input', function() {
		var stationId = $(this).val();
		
		if ($(this).is(':checked')) {
			
			stationHandler.selectStation(stationId);
			stationHandler.printSelectedStations();
			chartHandler.addSerieToChart(stationId);
			
		} else {
			stationHandler.deselectStation(stationId);
			stationHandler.printSelectedStations();
			chartHandler.removeSerieFromChart(stationId);
		}
	
	});
	

	
	$('#dateDay').datetimepicker({
		language : 'de',
		pickTime: false
	});
	
	var picker = $('#dateDay').data('datetimepicker');
	picker.setDate(new Date());
	
	
	$('#previousDay').click(function(){
		var current = picker.getDate();
		picker.setDate(current.getTime() - (24 * 60 * 60 * 1000));
		dateHandler.changedDate();
	});
	
	
	$('#nextDay').click(function(){
		var current = picker.getDate();
		picker.setDate(current.getTime() + (24 * 60 * 60 * 1000));
		dateHandler.changedDate();
	});
	
	
	$('#dateDay').on('changeDate', function(e) {
		dateHandler.changedDate();
	});
	
	
	
	
	//Spinner Options
	var opts = {
			lines: 13, // The number of lines to draw
			length: 20, // The length of each line
			width: 10, // The line thickness
			radius: 30, // The radius of the inner circle
			corners: 1, // Corner roundness (0..1)
			rotate: 0, // The rotation offset
			direction: 1, // 1: clockwise, -1: counterclockwise
			color: '#000', // #rgb or #rrggbb
			speed: 1, // Rounds per second
			trail: 60, // Afterglow percentage
			shadow: false, // Whether to render a shadow
			hwaccel: false, // Whether to use hardware acceleration
			className: 'mySpinner', // The CSS class to assign to the spinner
	};
	spinner = new Spinner(opts).spin();
	$('#spinnerContainer').append(spinner.el);

	//plotbands
	//station nicht hinzufügen wenn die daten noch nicht
	

});