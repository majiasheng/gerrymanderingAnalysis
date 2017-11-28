// temporary
function getAreas(districtsGeoJson) {
    // get area
    $.grep(districtsGeoJson.features, function (feature) {
        var poly;
        if (feature.geometry.type === "Polygon")
            poly = turf.polygon(feature.geometry.coordinates);
        else if (feature.geometry.type === "MultiPolygon")
            poly = turf.multiPolygon(feature.geometry.coordinates);
        console.log("area: " + turf.area(poly) + " square meter");
    });
}

$(document).ready(function () {

    // get district area and perimeter


});