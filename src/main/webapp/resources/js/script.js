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
				
				console.log("GET?code=" + c + '&year=' + y);
				
				if($.isEmptyObject(response)){
					//FIXME
					console.log("no correct request param");
				} else {
					$.each(response, function(k, v){
						// populate year options to data drop down
						$("<option value" + "=" + v + ">" + v+ "</option>").insertAfter($("#dataSelection option").last());
					});
					console.log(response);
					$("#dataSelection").prop({
						disabled: false
					});
				}
			},
			error: function(xhr,status,error) {
				console.log(error);
			}
		});

	});
});
