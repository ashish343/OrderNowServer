<%@ include file="/WEB-INF/jsp/include.jsp" %>
<html>
<head>
<link type="text/css" href="${path}/resources/css/bootstrap.min.css" rel="stylesheet"/>
 <script type="text/javascript" src="/resources/new-layout/js/jquery-1.7.1.min.js"></script>
 <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places"></script>
 <link type="text/css" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500">
 
 <script> 
	//This example displays an address form, using the autocomplete feature
	//of the Google Places API to help users fill in the information.
	
	var placeSearch, autocomplete;
	var componentForm = {
	street_number: 'short_name',
	route: 'long_name',
	locality: 'long_name',
	administrative_area_level_1: 'short_name',
	country: 'long_name',
	postal_code: 'short_name'
	};
	
	function initialize() {
	// Create the autocomplete object, restricting the search
	// to geographical location types.
	autocomplete = new google.maps.places.Autocomplete(
	    /** @type {HTMLInputElement} */(document.getElementById('autocomplete')),
	    { types: ['geocode'] });
	// When the user selects an address from the dropdown,
	// populate the address fields in the form.
	google.maps.event.addListener(autocomplete, 'place_changed', function() {
	  fillInAddress();
	});
	}
	
	//The START and END in square brackets define a snippet for our documentation:
	//[START region_fillform]
	function fillInAddress() {
	// Get the place details from the autocomplete object.
	var place = autocomplete.getPlace();
	$('#address').show();
	for (var component in componentForm) {
	  /* document.getElementById(component).value = ''; */
	  document.getElementById(component).disabled = false;
	}
	
	// Get each component of the address from the place details
	// and fill the corresponding field on the form.
	for (var i = 0; i < place.address_components.length; i++) {
	  var addressType = place.address_components[i].types[0];
	  if (componentForm[addressType]) {
	    var val = place.address_components[i][componentForm[addressType]];
	    document.getElementById(addressType).value = val;
	  }
	}
	}
	//[END region_fillform]
	
	//[START region_geolocation]
	//Bias the autocomplete object to the user's geographical location,
	//as supplied by the browser's 'navigator.geolocation' object.
	function geolocate() {
	if (navigator.geolocation) {
	  navigator.geolocation.getCurrentPosition(function(position) {
	    var geolocation = new google.maps.LatLng(
	        position.coords.latitude, position.coords.longitude);
	    autocomplete.setBounds(new google.maps.LatLngBounds(geolocation,
	        geolocation));
	  });
	}
	}
//[END region_geolocation]

 </script>
 <style>
      #locationField, #controls {
        position: relative;
        width: 480px;
      }
      #autocomplete {
       /*  position: absolute;
        top: 0px;
        left: 0px;
        width: 99%; */
      }
      .label {
        text-align: right;
        font-weight: bold;
        width: 100px;
        color: #303030;
      }
      #address {
        border: 1px solid #000090;
        /* background-color: #f0f0ff;
        width: 480px;
        padding-right: 2px; */
      }
      #address td {
        font-size: 10pt;
      }
      .field {
        width: 99%;
      }
      .slimField {
        width: 80px;
      }
      .wideField {
        width: 200px;
      }
      #locationField {
        height: 20px;
        margin-bottom: 2px;
      }
    </style>
</head>
	<body onload="initialize()">
		<div style="max-width: 400px;margin: 0 auto; padding: 15px">
			
			  <div class="form-group">    
			      <input type="text" class="form-control" id="restaurant_name" placeholder="Restaurant Name">
			  </div>
			  
			  <div class="form-group">
			  <input id="autocomplete" placeholder="Enter your address"
			             onFocus="geolocate()" type="text" class="form-control">
			             </input>
			   </div>
			  
			  
			  <table id="address" style="margin: 5px; display: none" class="form-group" >
      <tr>
        <td class="label">Street address</td>
        <td class="slimField"><input class="field form-control" id="street_number"
              disabled="true"></input></td>
        <td class="wideField" colspan="2"><input class="field form-control" id="route"
              disabled="true"></input></td>
      </tr>
      <tr>
        <td class="label">City</td>
        <td class="wideField" colspan="3"><input class="field form-control" id="locality"
              disabled="true"></input></td>
      </tr>
      <tr>
        <td class="label">State</td>
        <td class="slimField"><input class="field form-control"
              id="administrative_area_level_1" disabled="true"></input></td>
        <td class="label">Zip code</td>
        <td class="wideField"><input class="field form-control" id="postal_code"
              disabled="true"></input></td>
      </tr>
      <tr>
        <td class="label">Country</td>
        <td class="wideField" colspan="3"><input class="field form-control"
              id="country" disabled="true"></input></td>
      </tr>
    </table>
		<div class="form-group">    
			      <input type="text" class="form-control" id="num_tables" placeholder="Number of Tables">
			  </div>
			<div class="form-group">
			      <button name="commit" class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
			  </div>
		</div>
	</body>
</html>