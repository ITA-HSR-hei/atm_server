
function Station(stationId) {
	this.id = stationId;
	this.status;

	this.setStatus = function(status) {
		this.status = status;
	};

	this.getStatus = function() {
		return status;

	};
}

function StationHandler() {
	var self = this;

	self.atmStationList;

	self.cookieStations;
	self.selectedStations = [];

	// ********************************************************//

	self.selectStation = function(stationId, addCookie) {
		addCookie = typeof addCookie !== 'undefined' ? addCookie : true;
		console.log("selected station");
		
		var station = new Station(stationId);
		station.setStatus("loading");
		self.selectedStations.push(station);
		
		
		// add station to cookie
		if(addCookie){
			
			var atm_cookie = $.cookie('atm_stations');
			if (typeof atm_cookie === 'undefined') {
				//no cookie exist
				var newAtmCookie = {selectedStations: [], version: 1};
				newAtmCookie.selectedStations.push(station.id);
				$.cookie('atm_stations', JSON.stringify(newAtmCookie));
				
			} else {
				
				var atmCookieObj = JSON.parse(atm_cookie);
				atmCookieObj.selectedStations.push(station.id);
				$.cookie('atm_stations', JSON.stringify(atmCookieObj));
			}
			
			console.log("cookie value after insert: ");
			console.log($.cookie('atm_stations'));
			
		}
		
		
		

	};

	self.deselectStation = function(stationId) {

		//remove object from selectedStations
		for ( var i = 0; i < self.selectedStations.length; i++) {
			if (self.selectedStations[i].id == stationId) {
				self.selectedStations.splice(i, 1);

			}
		}

		
		
		// remove station from cookie
		var atmCookie = $.cookie('atm_stations');
		if (typeof atmCookie === 'undefined') {
			// no cookie exists, nothing to remove
			
		} else {
			
			var atmCookieObj = JSON.parse(atmCookie);
			
			var lengthOfArr = atmCookieObj.selectedStations.length;
			for(var j = lengthOfArr- 1; j >= 0; j--){
				
				if(atmCookieObj.selectedStations[j] == stationId){
					atmCookieObj.selectedStations.splice(j, 1);
				}
			}
			$.cookie('atm_stations', JSON.stringify(atmCookieObj));
		}
		
		console.log("cookie value after remove: ");
		console.log($.cookie('atm_stations'));
		

	};
	
	self.getSelectedStations = function(){
		return self.selectedStations;
	};
	
	self.getStationName = function(stationId){
		
		for(var i = 0; i < self.atmStationList.length; i++){
			if(self.atmStationList[i].id == stationId){
				return self.atmStationList[i].name;
			}
		}
		return "";
	};
	
	
	self.printSelectedStations = function() {
		console.log(self.selectedStations);

	};
	
	
	self.isStationSelected = function(stationId){
		
		for(var i = 0; i < self.selectedStations.length; i++){
			if(self.selectedStations[i].id == stationId){
				return true;
			}
		}
		return false;
		
	};

	// not used anymore
	self.isNewStation = function(stationId) {
		for ( var i = 0; i < self.stations.length; i++) {
			if (self.stations[i].id == stationId) {
				return false;
			}
		}
		return true;
	};
	
	self.getStation = function(stationId){
		for(var i = 0; i < self.stations.length; i++){
			if(self.stations[i].id == stationId){
				return self.stations[i];
			}
		}
		return null;
	};

	self.isStationActive = function(station) {
		console.log("log for station");
		console.log(station);
		if (station.status == "active") {
			return true;
		}
		return false;
	};

	

	// ********************************************************//

	self.initStationtMenu = function(data) {
		self.atmStationList = data.stationList;

		$('#stationList').append("<li><strong>Stations</strong></li>");

		for ( var i = 0; i < data.stationList.length; i++) {
			var stationId = data.stationList[i].id;
			var stationName = data.stationList[i].name;
			var listEntry = '<li><label class="checkbox"><input type="checkbox" name="station" value="'
					+ stationId + '" />' + stationName + '</label></li>';

			$('#stationList').append(listEntry);
		}

		

	};

	self.loadStationList = function() {
		$.ajax({
			type : "GET",
			url : "../public/0/getStations",
			success : self.initStationtMenu,
			dataType : 'json',
			async : false

		});

	};
	
	self.initCheckboxes = function(){
		//todo check what happens when cookies are disabled in browser
		var atmCookie = $.cookie('atm_stations');
		if (typeof atmCookie === 'undefined') {
			console.log("init new cookie");
			var newAtmCookie = {selectedStations: [], version: 1};
			$.cookie('atm_stations', JSON.stringify(newAtmCookie));
		}
		
		var stationsToSelect = JSON.parse($.cookie('atm_stations')).selectedStations;
		console.log("station to selecet length: "+stationsToSelect.length);
		console.log("stations to select: "+stationsToSelect);
		console.log("selected stations before init: (nextline) ");
		console.log(self.selectedStations);
		
		$.each(stationsToSelect, function(index, stationIdFromCookie){
			//todo check if station still exist
			self.selectStation(stationIdFromCookie, false);
			
			console.log("input of station id: "+stationIdFromCookie);
			$('input[value='+stationIdFromCookie+']').click();
			
		});
		
		if(stationsToSelect == 0){
			//todo change to first list value
			self.selectStation(1, false);
			$('input[value=1]').click();
			
		}
		
		console.log("selected stations after init: (nextline)");
		console.log(self.selectedStations);
		
	};

}


var stationHandler;

$(function() {

	stationHandler = new StationHandler();
	stationHandler.loadStationList();
	stationHandler.initCheckboxes();
	console.log("station handler");
	console.log(stationHandler);

});
