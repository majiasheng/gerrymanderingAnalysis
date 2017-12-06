
function title(str) {
  if (typeof str !== "string")
    return str;
  return str.replace(/\w\S*/g, function(txt){return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();});
}

function generateColor(val) {
  if (val === "white") {
    return "#9ACD32";
  } else if (val === "africanAmerican") {
    return "#B8860B";
  } else if (val === "americanNative") {
    return "#006400";
  } else if (val === "asian") {
    return "#DAA520";
  } else if (val === "pacificIslander") {
    return "#20B2AA";
  } else if (val === "otherRace") {
    return "#FF00FF";
  } else if (val === "twoOrMoreRaces") {
    return "#FF0000";
  } else {
    var r = Math.floor(Math.random() * 200);
    var g = Math.floor(Math.random() * 200);
    var b = Math.floor(Math.random() * 200);
    return 'rgb(' + r + ', ' + g + ', ' + b + ')';
  }
}

function translatePropKeyName(val) {
  if (val === "DISTRICT") {
    return "District";
  } else {
    return val;
  }
}

function translateElectionDataKeyName(val) {
  if (val === "winner") {
    return "Winning Party";
  } else if (val === "demStatus") {
    return "<span class=\"blue\">Democrat Standing</span>";
  } else if (val === "repStatus") {
    return "<span class=\"red\">Republican Standing</span>";
  } else if (val === "demVotes") {
    return "<span class=\"blue\">Democrat Votes</span>";
  } else if (val === "repVotes") {
    return "<span class=\"red\">Republican Votes</span>";
  } else {
    return val;
  }
}

function electionDataExcludeKey(key) {
  if (key === "districtNum") {
    return true;
  }
  return false;
}

function translateDemogDataKeyName(val) {
  if (val === "white") {
    return "White";
  } else if (val === "africanAmerican") {
    return "African American";
  } else if (val === "americanNative") {
    return "Native American";
  } else if (val === "asian") {
    return "Asian";
  } else if (val === "pacificIslander") {
    return "Pacific Islander";
  } else if (val === "otherRace") {
    return "Other Race";
  } else if (val === "twoOrMoreRaces") {
    return "Two Or More Races";
  } else {
    return val;
  }
}

function demogDataExcludeKey(key) {
  if (key === "districtId") {
    return true;
  }
  return false;
}

function translateElectionDataVal(val) {
  if (val === "Democratic") {
    return "Democrat";
  } else {
    return val;
  }
}

function pastel_colour(input_str) {

    //TODO: adjust base colour values below based on theme
    var baseRed = 128;
    var baseGreen = 128;
    var baseBlue = 128;

    //lazy seeded random hack to get values from 0 - 256
    //for seed just take bitwise XOR of first two chars
    var seed = input_str.charCodeAt(0) ^ input_str.charCodeAt(1);
    var rand_1 = Math.abs((Math.sin(seed++) * 10000)) % 256;
    var rand_2 = Math.abs((Math.sin(seed++) * 10000)) % 256;
    var rand_3 = Math.abs((Math.sin(seed++) * 10000)) % 256;

    //build colour
    var red = Math.round((rand_1 + baseRed) / 2);
    var green = Math.round((rand_2 + baseGreen) / 2);
    var blue = Math.round((rand_3 + baseBlue) / 2);

    return 'rgb('+red+','+green+', '+blue+')';
}
