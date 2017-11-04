// init map
var token = 'pk.eyJ1IjoibWEzMDgiLCJhIjoiY2o4ZGxoa3hyMHJrdDMwbzA5emM5Y3pzcSJ9.ZsR3x4DhKRrkTD7goSnE3w'
var map1 = L.map('mapid').setView([36.4051421,-95.5136459], 3.91);
L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token={accessToken}', {
    attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="http://mapbox.com">Mapbox</a>',
    maxZoom: 18,
    id: 'mapbox.streets',
    accessToken: token
}).addTo(map1);

// state boundary
// var geojson = "/resources/js/state-boundary-cenus-bureau.json"
var geojson = "/resources/js/state-boundary-cenus-bureau.json"
var allStates;
$.ajax(geojson).done(function(d){
  allStates = L.geoJson(d, {
      // style: style,
      onEachFeature: onEachFeature
  });

  allStates.addTo(map1);
});

function onEachFeature(feature, layer) {
    layer.on({
        // mouseover: highlightFeature,
        // mouseout: resetHighlight,
        click: zoomToFeature
    });
}

function zoomToFeature(e) {
    map1.fitBounds(e.target.getBounds());
}
