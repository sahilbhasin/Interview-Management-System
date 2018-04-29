function validateForm(validateUrl,returnOnSuccess) {

	// Get all form elements and submit to URL
	var keyValuePairs = [];
	for ( var i = 0; i < document.myform.elements.length; i++ ) {
	   var e = document.myform.elements[i];
	   keyValuePairs.push(encodeURIComponent(e.name) + "=" + encodeURIComponent(e.value));
	}
	var params = keyValuePairs.join("&");
	//window.alert(params);
	// Send AJAX request to URL
	var req = new XMLHttpRequest();
	req.open("POST",validateUrl,false);
	req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	req.setRequestHeader("Content-length", params.length);
	req.setRequestHeader("Connection", "close");
	req.send(params);
	
	// Get response
	if(req.responseText=="success") {
			
			document.getElementById("errormsg").innerHTML="";
			document.getElementById("successmsg").innerHTML="Success!";
			
			// If returnOnSuccess is set, 
			if(returnOnSuccess=="true") {
				return true;
			}
			
	}
	else {
		document.getElementById("errormsg").innerHTML="ERROR: " + req.responseText;
		document.getElementById("successmsg").innerHTML="";
	}

	// Default to returning false
	return false;
	
	
}


function validateForm2(url) {
	
	// Get form
	var form = document.forms.namedItem("myform");
	
	// XML HTTP Request and FormData objects
	var XHR = new XMLHttpRequest();
	
	// Get data
	var formData = new FormData(form);
	
	// Open up the HTTP request
	XHR.open('POST', url,false);
	
	// Send it
	XHR.send(formData);
	
	// If the response is success, yay?
	if(XHR.responseText=="success") {
		
		document.getElementById("errormsg").innerHTML="";
		document.getElementById("successmsg").innerHTML="Success!";
		return true;
		
	}
	else {
		document.getElementById("errormsg").innerHTML="ERROR: " + XHR.responseText;
		document.getElementById("successmsg").innerHTML="";
	}

	// Default to returning false
	return false;
	
	
}