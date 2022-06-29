document.querySelector('.img-btn').addEventListener('click', function()
	{
		document.querySelector('.cont').classList.toggle('s-signup')
	}
);

document.getElementById("sInButton").addEventListener('click', validateSinForm);
document.getElementById("sUpButton").addEventListener('click', validateSupForm);


function validateSinForm(event) {
	event.preventDefault();
	var em = document.getElementById("email").value;
	var pw = document.getElementById("password").value;
	var regex = /^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/i;

	if(em == "") {
		document.getElementById("emError").innerHTML = "email is required";
	} else if(!regex.test(em)) {
		document.getElementById("emError").innerHTML = "email format is incorrect";
	}
	if(pw == "") {
		document.getElementById("pwError").innerHTML = "password is required";
	}
};

function validateSupForm(event) {
	event.preventDefault();
	var name = document.getElementById("sUpName").value;
	var email = document.getElementById("sUpEmail").value;
	var psw = document.getElementById("sUpPassword").value;
	var conPsw = document.getElementById("sUpConPassword").value;

	var regex = /^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/i;

	if(email == "") {
		document.getElementById("sUpEmError").innerHTML = "email is required";
	} else if(!regex.test(email)) {
		document.getElementById("sUpEmError").innerHTML = "email format is incorrect";
	}
	if(name == "") {
		document.getElementById("sUpNameError").innerHTML = "name is required";
	}
	if(psw == "") {
		document.getElementById("sUpPwError").innerHTML = "password is required";
	}
	if(conPsw === psw) {
		document.getElementById("sUpConfirmPwError").innerHTML = "password match";
		document.getElementById("sUpConfirmPwError").style.color = "green";
	} else {
		document.getElementById("sUpConfirmPwError").innerHTML = "password doesn't match";
		document.getElementById("sUpConfirmPwError").style.color = "red";
	}
};

