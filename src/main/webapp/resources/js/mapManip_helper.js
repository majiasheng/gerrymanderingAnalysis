
function title(str) {
  if (typeof str !== "string")
    return str;
  return str.replace(/\w\S*/g, function(txt){return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();});
}

function generateRandomColor() {
  var r = Math.floor(Math.random() * 200);
  var g = Math.floor(Math.random() * 200);
  var b = Math.floor(Math.random() * 200);
  return 'rgb(' + r + ', ' + g + ', ' + b + ')';
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
