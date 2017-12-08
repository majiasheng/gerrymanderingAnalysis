// temporary
// get area, unit = km^2
function getAreas(districtsGeoJson) {

    $.grep(districtsGeoJson.features, function (feature) {
        var poly;
        if (feature.geometry.type === "Polygon")
            poly = turf.polygon(feature.geometry.coordinates);
        else if (feature.geometry.type === "MultiPolygon")
            poly = turf.multiPolygon(feature.geometry.coordinates);
        console.log("area: " + turf.area(poly) / 1000000 + " square kilometers");
    });
}


// get perimeter, unit = km
function getPerimeter(districtsGeoJson) {

    $.grep(districtsGeoJson.features, function (feature) {
        var peri = 0;

        if (feature.geometry.type === "Polygon") {

            peri = turf.lineDistance(turf.lineString(feature.geometry.coordinates[0]))

        } else if (feature.geometry.type === "MultiPolygon") {

            var array = feature.geometry.coordinates;

            for (i = 0; i < array.lengthl; i++) {
                peri += turf.lineDistance(turf.lineString(feature.geometry.coordinates[i]));
            }

        }
        console.log("perimeter: " + peri + " kilometers");
    });

}

$(document).ready(function () {

    // get district area and perimeter


});