// var dataSelection = function(c, y){
// 	$.ajax({
// 		url: "/state",
// 		type: "GET",
// 		contentType: "application/json",
// 		data: {"code": c, "year": y},

// 		dataType: "json",
// 		success: function(response, status, xhr) {
			
// 			console.log("GET?code=" + c + '&year=' + y);
			
// 			if($.isEmptyObject(response)){
// 				//FIXME
// 				console.log("no correct request param");
// 			} else {
// 				//TODO: use reponse, a list of year, to populate the data dropdown
// 				$.each(response, function(k, v){
// 					// append <option value=v>v</option>
// 				});
// 				console.log(response);
// 				$("#dataSelection").prop({
// 					disabled: false
// 				});
// 			}
// 		}
// 		// error: function(xhr,status,error) {
// 		// 	console.log(error);
// 		// }
// 	});
// }

$(document).ready(function() {

	// send get on state selection
	$('#stateSelection').change(function(){
		var c = $(this).val();
		var options = "";
		$.ajax({
			url: "/state",
			type: "GET",
			contentType: "application/json",
			data: {"code": c},
			dataType: "json",
			success: function(response, status, xhr) {
				// zoom to state 
				map1.fitBounds($.grep(allStates.getLayers(), function(state){
				return state.feature.properties.STUSPS == c;
				})[0].getBounds());
				
				// populate year options to data drop down
				$.each(response, function(k, v){
					options+="<option value" + "=" + v + ">" + v+ "</option>"
				});
				$('#dataSelection').html(options);
				console.log("Enabling Data drop down menu...");
				$("#dataSelection").prop({
					disabled: false
				});

				//TODO: send get request for default year (or selected year)
				var y = $("#dataSelection").val();

			},
			error: function(xhr,status,error) {
				// disallow selecting "Data" option if response is empty
				$("#dataSelection").prop({
					disabled: true
				});
			}
		});
	});

	// send get on data selection
	$('#dataSelection').change(function(){
		//TODO: disallow selecting "Data" option 
		var c = $("#stateSelection").val();
		var y = $("#dataSelection").val();
		$.ajax({
			url: "/data",
			type: "GET",
			contentType: "application/json",
			data: {"code": c, "year": y},
			dataType: "json",
			success: function(response, status, xhr) {
				//TODO: display district boundary
				console.log("Enabling GerrymanderingMeasure drop down menu...");
				$("#gerrymanderingMeasure").prop({
					disabled: false
				});
			},
			error: function(xhr, textStatus, errorThrown){
				console.log(textStatus 
					+ ": Cannot enable GerrymanderingMeasure drop down menu" 
					+ "\nCan be caused by empty response");
				$("#gerrymanderingMeasure").prop({
					disabled: true
				});
			}
		});
	});

	// send get on measure/test selection
	$('#gerrymanderingMeasure').change(function(){
		var c = $("#stateSelection").val();
		var y = $("#dataSelection").val();
		var m = $(this).val();
		$.ajax({
			url: "/measure/"+m,
			type: "GET",
			contentType: "application/json",
			data: {"code": c, "year": y},
			dataType: "json",
			success: function(response, status, xhr) {
				//TODO: display measure result

				//TEST
				// $('.info').html(m);
			},
			error: function(xhr, textStatus, errorThrown){
				console.log(textStatus + "; errorThrown: " + errorThrown);
			}
		});
	});
	

});
