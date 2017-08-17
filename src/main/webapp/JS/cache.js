/**
 * 
 */
lineGraphCache={};
	lineGraphCache.instrumentNames = [];

addLineGraphData = function(data) {
	if(lineGraphCache[data.instrument] === undefined) {
		lineGraphCache.instrumentNames.push(data.instrument);
		lineGraphCache[data.instrument] = data;
	}
}

	
// Validate if the Data parameter has got the needed attributes
validateLineGraphData = function(data) {
	if(data.instrument !== undefined && data.deals !== undefined) {
		return true;
	} else {
		console.error("Data not valid.");
		return false;
	}
}

svgheight = 400,
svgwidth = 600;

margin = {top: 50, right: 100, bottom: 30, left: 70},
width = +svgwidth - margin.left - margin.right,
height = +svgheight - margin.top - margin.bottom;

d3.json('data.json', function(err, data) {
	if(err) {
		console.error(err);
		return;
	}
	
})
