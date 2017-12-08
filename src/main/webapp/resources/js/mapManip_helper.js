
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

function displayTestVar(val) {
  if (val === "skipped") {
    return false;
  } else if (val === "uniqueTestResult") {
    return false;
  } else {
    return true;
  }
}

function translateTestKeyName(val) {
  if (val === "gerrymandered") {
    return "Is State Gerrymandered?";
  } else if (val === "confidenceLvl") {
    return "Confidence Level";
  } else if (val === "pValue") {
    return "P-Value";
  } else {
    return val;
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

function arrayToRgb(arr) {
  return 'rgb('+ arr[0]+',' + arr[1]+',' + arr[2] +')';
}

/**
 * Generate distinct RGB colors
 *
 * t is the total number of colors
 * you want to generate.
 */
var rgbColors = function(t) {
  t = parseInt(t);
  if (t < 2)
    throw new Error("'t' must be greater than 1.");

  // distribute the colors evenly on
  // the hue range (the 'H' in HSV)
  var i = 360 / (t - 1);

  // hold the generated colors
  var r = [];
  var sv = 70;
  for (var x = 0; x < t; x++) {
    // alternate the s, v for more
    // contrast between the colors.
    sv = sv > 90 ? 70 : sv+10;
    r.push(hsvToRgb(i * x, sv, sv));
  }
  return r;
};

/**
 * HSV to RGB color conversion
 *
 * H runs from 0 to 360 degrees
 * S and V run from 0 to 100
 *
 * Ported from the excellent java algorithm by Eugene Vishnevsky at:
 * http://www.cs.rit.edu/~ncs/color/t_convert.html
 */
var hsvToRgb = function(h, s, v) {
  var r, g, b;
  var i;
  var f, p, q, t;

  // Make sure our arguments stay in-range
  h = Math.max(0, Math.min(360, h));
  s = Math.max(0, Math.min(100, s));
  v = Math.max(0, Math.min(100, v));

  // We accept saturation and value arguments from 0 to 100 because that's
  // how Photoshop represents those values. Internally, however, the
  // saturation and value are calculated from a range of 0 to 1. We make
  // That conversion here.
  s /= 100;
  v /= 100;

  if (s == 0) {
    // Achromatic (grey)
    r = g = b = v;
    return [Math.round(r * 255), Math.round(g * 255), Math.round(b * 255)];
  }

  h /= 60; // sector 0 to 5
  i = Math.floor(h);
  f = h - i; // factorial part of h
  p = v * (1 - s);
  q = v * (1 - s * f);
  t = v * (1 - s * (1 - f));

  switch (i) {
    case 0:
      r = v;
      g = t;
      b = p;
      break;

    case 1:
      r = q;
      g = v;
      b = p;
      break;

    case 2:
      r = p;
      g = v;
      b = t;
      break;

    case 3:
      r = p;
      g = q;
      b = v;
      break;

    case 4:
      r = t;
      g = p;
      b = v;
      break;

    default: // case 5:
      r = v;
      g = p;
      b = q;
  }

  return [Math.round(r * 255), Math.round(g * 255), Math.round(b * 255)];
};
