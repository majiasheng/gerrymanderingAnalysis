// map params
var zoomLvl = 3.91;
var usLat = 36.4051421;
var usLng = -95.5136459;
var token = 'pk.eyJ1IjoibWEzMDgiLCJhIjoiY2o4ZGxoa3hyMHJrdDMwbzA5emM5Y3pzcSJ9.ZsR3x4DhKRrkTD7goSnE3w';
var map1 = L.map('mapid', {
    center:        L.latLng(usLat, usLng),
    zoom:          zoomLvl,
    worldCopyJump: true
});
L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token={accessToken}', {
    attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="http://mapbox.com">Mapbox</a>',
    maxZoom: 18,
    id: 'mapbox.streets',
    accessToken: token
}).addTo(map1);

// state boundary
var geojson = "/resources/json/state-boundary-cenus-bureau.json";
var allStates = null;
var districtBoundary = null;
$.ajax(geojson).done(function(d){
    allStates = L.geoJson(d, {
        style: function(feature) {
          return {
            color: 'green'
          }
        },
        onEachFeature: onStates
    });
    allStates.addTo(map1);
});

function onStates(feature, layer) {
    layer.on({
        // mouseover: highlightFeature,
        // mouseout: resetHighlight,
        click: selectState
    });
}

function selectState(e) {
    console.log(e.target.feature.properties.STUSPS);
    var selected = 0;
    $("#stateSelection option").each(function (i, val) {
        if ($(val).val() === e.target.feature.properties.STUSPS) {
            selected = 1;
            $("#stateSelection").val(e.target.feature.properties.STUSPS).change();
        }
    });
    if (!selected) {
        alert("State " + e.target.feature.properties.STUSPS + " Not Available");
    }
}
