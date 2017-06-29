function validate() {
	var p1 = document.getElementById("password").value;
	var p2 = document.getElementById("newpassword").value;
	
	if(p1 == p2) {
		return true;
	}
	
	alert("Passwords do not match");
	return false;
}