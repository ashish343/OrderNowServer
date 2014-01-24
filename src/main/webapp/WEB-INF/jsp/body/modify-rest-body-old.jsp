<style>
svg {
  padding: 10px 0 0 10px;
	cursor: pointer;
}

.arc {
  stroke: #fff;
}
#tables{
background-color : aliceblue;
width: 100%;
}
#order{
	background-color : cadetblue;
	width:auto;
}
</style>
<body>
<script src="http://d3js.org/d3.v3.min.js"></script>
<script src="http://code.jquery.com/jquery-2.0.3.min.js"></script>

<script>


var radius = 74,
    padding = 10;

var color = d3.scale.ordinal()
    .range(["#98abc5", "#8a89a6", "#7b6888", "#6b486b", "#a05d56", "#d0743c",
"#ff8c00"]);

var arc = d3.svg.arc()
    .outerRadius(radius)
    .innerRadius(radius - 30);

var pie = d3.layout.pie()
    .sort(null)
    .value(function(d) { return d.population; });

d3.csv("${path}/resources/testJson/data.csv", function(error, data) {
  color.domain(d3.keys(data[0]).filter(function(key) { return key !== "State";
}));

  data.forEach(function(d) {
    d.ages = color.domain().map(function(name) {
      return {name: name, population: +d[name]};
    });
  });

  var legend = d3.select("#tables").append("svg")
      .attr("class", "legend")
      .attr("width", radius * 2)
      .attr("height", radius * 2)
    .selectAll("g")
      .data(color.domain().slice().reverse())
    .enter().append("g")
      .attr("transform", function(d, i) { return "translate(0," + i * 20 + ")";
});

  legend.append("rect")
      .attr("width", 18)
      .attr("height", 18)
      .style("fill", color);

  legend.append("text")
      .attr("x", 24)
      .attr("y", 9)
      .attr("dy", ".35em")
      .text(function(d) { return d; });

  var svg = d3.select("#tables").selectAll(".pie")
      .data(data)
    .enter().append("svg")
      .attr("class", "pie")
      .attr("width", radius * 2)
      .attr("height", radius * 2)
    .append("g")
      .attr("transform", "translate(" + radius + "," + radius + ")")
			.on("click",fn);

  svg.selectAll(".arc")
      .data(function(d) { return pie(d.ages); })
    .enter().append("path")
      .attr("class", "arc")
      .attr("d", arc)
      .style("fill", function(d) { return color(d.data.name); });

  svg.append("text")
      .attr("dy", ".35em")
      .style("text-anchor", "middle")
      .text(function(d) { return d.State; });

});
function fn(d){
	$('#tables').animate({width : "40%"}, 1500);
	$('#order').animate({width : "60%", height : "20%"}, 1500);
	$('#order').html("cars");
	console.log(d);
}
</script>

<div id='tables'></div>
<div id="order"></div>

