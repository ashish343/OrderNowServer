<style>

tr {
	padding: 3px;
}
.node rect {
  cursor: pointer;
  fill: #fff;
  fill-opacity: .85;
  stroke: #3182bd;
  stroke-width: 1.5px;
  width: 30px;
  
}

.node text {
  font: 10px sans-serif;
  pointer-events: none;
}

path.link {
  fill: none;
  stroke: #9ecae1;
  stroke-width: 1.5px;
}

</style>

<script>
P.when("d3","jQuery").execute(function(d3,$){
	abc(this.d3,this.$)
});
 function abc(d3,$){
	window.margin = {top: 30, right: 20, bottom: 30, left: 20};	
window.maxID=-1;
    /* var width = 960 - margin.left - margin.right; */
    var width = $('#chart').width() *0.9;
    
    window.barWidth = width * .8;
    
window.barHeight = 20;

window.i = 0;	
    window.duration = 400;
    window.root;

window.tree = d3.layout.tree()
    .size([0, 100]);

window.diagonal = d3.svg.diagonal()
    .projection(function(d) { return [d.y, d.x]; });

window.svg = d3.select("#chart").append("svg")
    .attr("width", width + margin.left + margin.right)
  .append("g")
    .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

d3.json("${path}/resources/testJson/flare.json", function(error, flare) {
  flare.x0 = 0;
  flare.y0 = 0;
  update(root = flare, tree, diagonal, svg, root, barHeight, barWidth, duration, margin, i);
});

 }
function update(source) {

  // Compute the flattened node list. TODO use d3.layout.hierarchy.
  var nodes = tree.nodes(root);
  var height = Math.max(500, nodes.length * barHeight + margin.top + margin.bottom);
  d3.select("svg")
      .attr("height", height);
  d3.select(self.frameElement)
      .style("height", height + "px");

  // Compute the "layout".
  nodes.forEach(function(n, i) {
    n.x = i * barHeight;
  });
  // Update the nodes
  var node = svg.selectAll("g.node")
      .data(nodes, function(d) { return d.id || (d.id = ++i); });

  var nodeEnter = node.enter().append("g")
      .attr("class", "node")
      .attr("transform", function(d) { return "translate(" + source.y0 + "," + source.x0 + ")"; })
      .style("opacity", 1e-6);

  // Enter any new nodes at the parent's previous position.
  nodeEnter.append("rect")
      .attr("y", -barHeight / 2)
      .attr("height", barHeight)
      .attr("width", barWidth)
      .style("fill", color)
      .on("click", click);

  nodeEnter.append("text")
      .attr("dy", 3.5)
      .attr("dx", 5.5)
      .attr("id", function(d){if (window.maxID<d.id)window.maxID=d.id;return d.id})
      .text(function(d) { return d.name; });

  // Transition nodes to their new position.
  nodeEnter.transition()
      .duration(duration)
      .attr("transform", function(d) { return "translate(" + d.y + "," + d.x + ")"; })
      .style("opacity", 1);

  node.transition()
      .duration(duration)
      .attr("transform", function(d) { return "translate(" + d.y + "," + d.x + ")"; })
      .style("opacity", 1)
    .select("rect")
      .style("fill", color);

  // Transition exiting nodes to the parent's new position.
  node.exit().transition()
      .duration(duration)
      .attr("transform", function(d) { return "translate(" + source.y + "," + source.x + ")"; })
      .style("opacity", 1e-6)
      .remove();

  // Update the links
  var link = svg.selectAll("path.link")
      .data(tree.links(nodes), function(d) { return d.target.id; });

  // Enter any new links at the parent's previous position.
  link.enter().insert("path", "g")
      .attr("class", "link")
      .attr("d", function(d) {
        var o = {x: source.x0, y: source.y0};
        return diagonal({source: o, target: o});
      })
    .transition()
      .duration(duration)
      .attr("d", diagonal);

  // Transition links to their new position.
  link.transition()
      .duration(duration)
      .attr("d", diagonal);

  // Transition exiting nodes to the parent's new position.
  link.exit().transition()
      .duration(duration)
      .attr("d", function(d) {
        var o = {x: source.x, y: source.y};
        return diagonal({source: o, target: o});
      })
      .remove();

  // Stash the old positions for transition.
  nodes.forEach(function(d) {
    d.x0 = d.x;
    d.y0 = d.y;
  });
}

// Toggle children on click.
function click(d) {
  if (d.children) {
    d._children = d.children;
    d.children = null;
  } else {
    d.children = d._children;
    d._children = null;
  }
    
  
  if(d._children == null && d.children == null){
	  /* 
	  $("#addType option[value='category']").remove();
	  $('#dynamic_category').html(" in "+d.parent.name);
	  $('#addCategory').hide();
	  $('#addDish').show();
	   */
	   $('#start').hide();
	   $('#form').hide();
	   $('#dish').show();
	   $('#img').attr("src","")
	   
	   $('.price').html(d.price);
	   $('.dish-name').html(d.name);
	   $('.dish_desc').html(d.desc);
	   $('#edit').attr('did', d.id);
	   $('#delete').attr('did', d.id);
	   
	   
	   
	   
	}else{
		if($("#addType option[value='category']").length == 0){
		$("#addType").append("<option value='category'>Add Category</option>")}
		$('#dynamic_category').html("/Categories in "+d.name);
		$('#start').hide();
		$('#form').show();		
	   	$('#dish').hide();
	   	$('#addDishForm input[name=parent_categ]').val(d.id);
	   	$('#addCategForm input[name=parent_categ]').val(d.id);
	}	   
 
  update(d);  
    
}

function color_highlight(d){
	return "#fd8d3c";
}

function color(d) {
  return d._children ? "#3182bd" : d.children ? "#c6dbef" : "#fd8d3c";
}

</script>


<div class="row dish-container">
	<div class="col-xs-4 col-md-5 col-lg-6" id="chart">	 
  </div>
	<div class="col-xs-4 col-md-5 col-lg-6" id="form" style="display: none;">
		<span >Add Dishes<span id='dynamic_category' ></span>
		</span>
		<select class="form-control" id="addType">
	    	<option onSelect="" value="category">Add Category</option>
	    	<option onSelect="" value="dish">Add Dish</option>
	    </select>
	    <div id='addCategory' >
	    	<form id='addCategForm'>
	    	<input type='hidden' name='parent_categ' value=''>
	    	<table>
	    	<input type='hidden' name='form_type' value='category'>
	    	<tr><td>
	    		<div class="input-group ">
	                <span class="input-group-addon">Category Name</span>
	                <input name="categ" type="text" class="form-control" placeholder="Category Name"  autocomplete="on">
	            </div>
	    	</td></tr>
	    	<tr><td>
				<div class="input-group">
				<button name ="commit" class="btn btn-lg btn-primary btn-block" onclick="addCateg(this); return false;" style="padding: 5px">Add</button>
				</div>
			</td></tr>
	    	</table>
	    	</form>
	    </div>
	    
	    <div id='addDish' style="display: none;" >
	    	<form id='addDishForm'>
	    	<input type='hidden' name='parent_categ' value=''>
	    	<table>
	    		<input type='hidden' name='form_type' value='dish'>
	   		<tr><td>
	    			<div class="input-group">
	                <span class="input-group-addon">Food Type</span>
	                <select class="form-control" name='foodType'>
	                <option value="Veg"> Veg</option>
	                <option value="NonVeg"> Non-Veg</option>                
	                </select>
	                </div>
			</td></tr>
	    	<tr><td>
	    			<div class = "input-group">
	    			<span class="input-group-addon">Dish Name</span>
	                <input name="dish" type="text" class="form-control" placeholder="Dish Name" autocomplete="on"></div></td>
	    	</tr>
	    	<tr><td>
	    			<div class="input-group">
	                <span class="input-group-addon">Price</span>
	                <input name="price" type="text" class="form-control" placeholder="Price">
	                </div>
			</td></tr>
			
			<tr><td>
	    			<div class="input-group">
	                <span class="input-group-addon">Image</span>
	                <input name="image" type="file" class="form-control">
	                </div>
			</td></tr>
			<tr><td>
	    			<div class="input-group">
	                <span class="input-group-addon">Description</span>
	                <textarea rows="3" cols="30" name='desc' placeholder="Description of the Dish"></textarea>
	                </div>
			</td></tr>
			
			<tr><td>
				<div class="input-group">
				<button name ="commit" class="btn btn-lg btn-primary btn-block"  style="padding: 5px" onclick="addDish(this); return false;">Add</button>
				</div>
			</td></tr>
	    	</table>
	    	</form>
	    </div>
	</div>
	
	<div class="col-xs-4 col-md-5 col-lg-6" id="dish" style="display: none;">
	
		<%@ include file="/WEB-INF/jsp/common/item_detail.jsp" %>		
		<div class="row">
			<div class="col-xs-4">
			<button name ="edit" class="btn btn-lg btn-primary btn-block"  style="padding: 5px" id="edit">Edit</button>				
			</div>			
			<div class="col-xs-4">
			<button name ="delete" class="btn btn-lg btn-danger	 btn-block" style="padding: 5px" id="delete" onclick="edit(this)">Delete</button>				
			</div>		
		</div>
	</div>		
	<div class="col-xs-4 col-md-5 col-lg-6" id="start" style="display: block;">
	
		Select a Category or a Dish
	</div>		
	
</div>

<script>

function addDish(){
	var n1={};
	n1["name"] = $('#addDishForm input[name=dish]').val();
	window.maxID += 1;
	n1["id"] = window.maxID;	 
	n1["price"] = $('#addDishForm input[name=price]').val(); 
	n1["desc"] =$('#addDishForm textarea').val(); 
	n1["food_type"] = $('#addDishForm select[name=foodType]').val();
	n1["dishId"] ="-1";			
	x = findNode(root, $('#addDishForm input[name=parent_categ]').val());
	if(x.children != undefined){
	x.children.push(n1);
	}
	else if(x._children != undefined){
	x._children.push(n1);
	}
		
	update(root);
	tmp = convertCircularJSONToString(root);	
	var request = $.ajax({
		  url: "/updateDatabase",
		  type: "POST",
		  data: { json : tmp }		  
		});
		 
	request.fail(function( jqXHR, textStatus ) {
	  alert( "Request failed: " + textStatus );
	});
		
}

function addCateg(){
	
	var n1={};
	n1["name"] = $('#addCategForm input[name=categ]').val();
	window.maxID += 1;
	n1["id"] = window.maxID;
	window.maxID += 1;
	n1["children"] = [{"id":9999}];
	
		 				
	x = findNode(root, $('#addDishForm input[name=parent_categ]').val());
	if(x.children != undefined){
		x.children.push(n1);
	}
	else if(x._children != undefined){
		x._children.push(n1);
	}
	console.log(convertCircularJSONToString(root));
	update(root);
	
}



function convertCircularJSONToString(obj){
	allowed= ["name", "children","desc","price","image"]
	var cache=[]
	var txt = JSON.stringify(obj, function(key, value) {		
	    if (typeof value === 'object' && value !== null) {
	        if (cache.indexOf(value) !== -1) {
	            // Circular reference found, discard key
	            return;
	        }
	        // Store value in our collection	        
	        cache.push(value);
	    }
	    return value;
	});
	return txt;
}


function edit(e){
	var  id = parseInt(e.getAttribute("did"));
	var temp = root;
	if (temp.id == id){
		delete temp;	
	}	
	else{
		if(temp.children!=null){
			
			for ( jx=0;jx< temp.children.length;jx++){			
				ix = recursivefind(temp.children[jx], id)
				
				if (ix){													
					temp.children.splice(jx,1);
					break;
				}
			}		
		}	
	}
	update(root);	
}

function recursivefind(node, id){
	
	if (node.id == id)
		return true;
	else if (node.children != null){
		for(ix=0;ix< node.children.length;ix++){
			var x=recursivefind(node.children[ix], id)
			if(x){				
				delete node.children.splice(ix,1);
				return false;
				}				
		}			
	}else return false;
}


function findNode(node, id){	
	if (node.id == id)
		return node;
	
	else if (node.children != null){
		for(ix=0;ix< node.children.length;ix++){
			var x=findNode(node.children[ix], id)
			if(x != null){							
				return x;				
			}				
		}				
	}
}	

</script>

    
    
    
    
