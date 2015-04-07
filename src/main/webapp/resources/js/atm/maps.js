var atmMap
function initialize() {
	var mapOptions = {
		center : new google.maps.LatLng(46.899616, 8.267212),
		zoom : 8,
		mapTypeId : google.maps.MapTypeId.ROADMAP
	};
	atmMap = new google.maps.Map(document.getElementById("map_canvas"),
			mapOptions);

//	var goldStar = {
//		path : 'M 125,5 155,90 245,90 175,145 200,230 125,180 50,230 75,145 5,90 95,90 z',
//		fillColor : "yellow",
//		fillOpacity : 0.8,
//		scale : 1,
//		strokeColor : "gold",
//		strokeWeight : 14
//	};

//	var marker = new google.maps.Marker({
//		
//		position : new google.maps.LatLng(46.899616, 8.267212),
//		icon: 'http://maps.google.com/mapfiles/ms/icons/green-dot.png',
//		map : map
//	});
//
//	var marker2 = new google.maps.Marker({
//		position : new google.maps.LatLng(46.749616, 8.367212),
//
//		map : map
//	});
//
//	var marker3 = new google.maps.Marker({
//		position : new google.maps.LatLng(46.905246, 7.454224),
//
//		map : map
//	});
//
	var marker4 = new google.maps.Marker({
		position : new google.maps.LatLng(47.402067, 8.794556),
		icon: 'http://maps.google.com/mapfiles/ms/icons/yellow-dot.png',
		map : atmMap
	});
//
//	var marker5 = new google.maps.Marker({
//		position : new google.maps.LatLng(47.271775, 7.827759),
//
//		map : map
//	});

}

function addMarkersToMap(data){
	
	var selectedStations = stationHandler.getSelectedStations();
	console.log(selectedStations);
	
	for(var i = 0; i < data.stationMapList.length; i++){
		
		var latitude = data.stationMapList[i].latitude;
		var longitude = data.stationMapList[i].longitude;
		var icon = 'http://maps.google.com/mapfiles/ms/icons/red-dot.png';
		console.log(longitude);
		console.log(latitude);
		console.log(data.stationMapList[i]);
		
		
		if(stationHandler.isStationSelected(data.stationMapList[i].stationId)){
			icon = 'http://maps.google.com/mapfiles/ms/icons/green-dot.png';
		}
		
		
		
		var marker = new google.maps.Marker({
			position : new google.maps.LatLng(latitude, longitude),
			icon: icon,
//			animation: google.maps.Animation.DROP,
			map : atmMap
		});
		
		
//		marker.setIcon('http://maps.google.com/mapfiles/ms/icons/blue-dot.png');
		console.log("added marker");
		console.log(marker);
		
	}
	
}

$(function(){

	initialize();
	
	
	$('#stationList').on('change', 'input', function() {
		var stationId = $(this).val();
		
		if ($(this).is(':checked')) {
			
			stationHandler.selectStation(stationId);
			stationHandler.printSelectedStations();
		} else {
			stationHandler.deselectStation(stationId);
			stationHandler.printSelectedStations();
		}
	
	});
	
	
	
		
		$.ajax({
			type : "GET",
			contentType : "application/json",
			url: '../public/0/getMapList',
			dataType: "json",
			success: addMarkersToMap
		});
		
		
	
	
	
	
//	
//	for(var i = 0; i<selectedStation.length; i++){
//				
//		
//	}
	

	
	
	

});
