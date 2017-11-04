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
		c = $(this).val();
		y = $("#dataSelection").val();

		$.ajax({
			url: "/state",
			type: "GET",
			contentType: "application/json",
			data: {"code": c, "year": y},
			dataType: "json",
			success: function(response, status, xhr) {
				$.each(response, function(k, v){
					// populate year options to data drop down
					$("<option value" + "=" + v + ">" + v+ "</option>").insertAfter($("#dataSelection option").last());
				});
				console.log(response);
				$("#dataSelection").prop({
					disabled: false
				});
			}

			//TODO: on reponse==null -> console.log("GET?code=" + c + '&year=' + y);

		});
	});

	// send get on data selection
	$('#dataSelection').change(function(){
		//TODO: disallow selecting "Data" option 
		c = $("#stateSelection").val();
		y = $("#dataSelection").val();

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
			}
		});
	});

	// send get on data selection
	$('#gerrymanderingMeasure').change(function(){

		c = $("#stateSelection").val();
		y = $("#dataSelection").val();

		$.ajax({
			url: "/measure",
			type: "GET",
			contentType: "application/json",
			data: {"code": c, "year": y},
			dataType: "json",
			success: function(response, status, xhr) {
				//TODO: display measure result
				
			}
		});
	});
	

});
