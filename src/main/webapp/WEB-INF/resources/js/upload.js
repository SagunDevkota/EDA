var status = false;
var toggle = false;
exampleFormControlFile1.onchange = function(event) {
   var fileList = exampleFormControlFile1.files;
   status = fileList[0].name.split(".")[1]=="csv";
}
function validateUploadForm(){
	if(status=="false"){
		if(toggle === false){
			togglePopup();
			toggle = true;
		}
		return false;
	}
	return true;
}


//To toggle error box.
function togglePopup() {
 document.getElementById("popup-1")
  .classList.toggle("active");
  toggle = false;
}