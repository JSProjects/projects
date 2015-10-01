function validateForm() {
    var so_number = document.forms["frm"]["so"].value;
    var enduser = document.forms["frm"]["enduser"].value;
    var key = document.forms["frm"]["ek"].value;
    var sno = document.forms["frm"]["sno"].value;
    var po = document.forms["frm"]["po"].value;
    if ((so_number == null || so_number == "") && (enduser == null || enduser == "") && (key == null || key == "") && (sno == null || sno == "") && (po ==null || po ==""))  {
    	document.getElementById("note").style.color="red";
        return false;
    }
}

function check()
{
	var sno = document.forms["frm"]["sno"].value;
    var message = document.getElementById('confirmMessage');
	var badColor = "#ff6666";
	var goodColor = "#008000";
	var pattern = /^\d{14}$/;
    if(sno!=null && sno!=""){
    	var pass = document.getElementById('sno');
    	var numbers = /^[0-9]+$/;
    	if(!sno.match(numbers)){
    	      pass.style.backgroundColor = badColor;
    	    message.innerHTML = "Please Input Numeric Value";
    	}else if(!pattern.test(sno)) {
    		pass.style.backgroundColor = badColor; 
    		message.innerHTML = "Expecting 14 digits";
    	}else{
    		pass.style.backgroundColor = goodColor;
    		message.innerHTML = "";
    	}
    }
}  