/**
 * 
 */
var LineGraphDrawer = {
	svg : d3.select('#linegraph')
		.append('svg')        // create an <svg> element
			.attr('width', svgwidth) // set its dimentions
			.attr('height', svgheight)
			.attr('id', 'linegraph'),
	drawLine : function(instrument, index, focus, x,y) {	
		var line = d3.line()
		//.curve(d3.curveMonotoneX)
		.defined(function(d) {return  d.deal_time})
		.x(function(d) {return x(new Date(d.deal_time))})
		.y(function(d) {return y(d.deal_amount)});
			
		var color = d3.schemeCategory10[index];
		var legendX = svgwidth-margin.right+10,
			legendY = margin.top+index*30;
		var linechart = focus.append('path')
	    .datum(instrument.deals)
	    .attr("fill", "none")
	    .attr("stroke", color)
	    .attr("stroke-linejoin", "round")
	    .attr("stroke-linecap", "round")
	    .attr("stroke-width", 1.5)
	    .attr("d", line);
		var legend = this.svg.append('g')
			.attr('x', legendX)
		legend.append('rect')
		.attr('transform', 'translate('+(legendX)+','+(legendY)+')')
	  	.attr('width', 16)                          // NEW
	  	.attr('height', 16)
		.style('fill', color)
		.style('stroke', color);
		legend.append('text')
			.attr('x', legendX + 25)
	  		.attr('y', legendY + 10)
	  		.attr('font-family', 'arial')
	  		.attr('font-size', 14)
	  		.text(instrument.instrument); 
		},
	start : function(data) {
		console.log("inside linegraph");
		if(validateLineGraphData(data)) {
			addLineGraphData(data);
		}
		var x = d3.scaleTime()
	    .domain(d3.extent([d3.min(lineGraphCache[data.instrument].deals,
	    		function(d) {return new Date(d.deal_time)}),
	    		d3.max(lineGraphCache[data.instrument].deals,
	    		function(d) {return new Date(d.deal_time)})]))
	    .range([0, width]);
	  	
		var y = d3.scaleLinear()
	  	.domain(d3.extent([0, d3.max(lineGraphCache[data.instrument].deals,
				function(d) {return new Date(d.deal_amount)}
		)])) // $0 to $80
	  	.range([height, 0]); // Seems backwards because SVG is y-down

		var xAxis = d3.axisBottom(x)
		//.ticks(d3.timeMillisecond.every(50000))
	 	//.ticks(5);

	 	var yAxis = d3.axisLeft(y);
		var focus = this.svg.append('g')
	    	.attr('class', 'focus')
	    	.attr('fill', 'none')
	    	.attr("transform", "translate(" + margin.left + "," + margin.top + ")");
		
		var gY = focus.append('g')            // create a <g> element
	  	  .attr('class', 'y axis')
	  	  .call(yAxis)
		  .append("text")
			  .attr("fill", "#000")
			  .attr("transform", "rotate(-90)")
			  .attr("y", 6)
			  .attr("dy", "0.71em")
			  .attr("text-anchor", "end")
			  .text("Price ($)"); // specify 	classes
	  	              // let the axis do its thing
	  	var gX = focus.append('g')                        //height
	  	  .attr("transform", "translate("+0+"," + height + ")")         // create a <g> element
	  	  .attr('class', 'x axis') // specify 	classes
	  	  .call(xAxis);
	  	              // let the axis do its thing
	  	lineGraphCache.instrumentNames.forEach(function(instrumentName,index) {
	  		var instrument = lineGraphCache[instrumentName]
	  		this.drawLine(instrument, index, focus,x,y)
	  	},this);
	}	
}

/*
d3.json('JS/data.json', function(err, data) {
	if(err) {
		console.error(err);
		return;
	}
	LineGraphDrawer.start(data);
})
*/