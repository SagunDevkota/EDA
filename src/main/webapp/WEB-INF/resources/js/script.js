document.querySelector('.img-btn').addEventListener('click', function()
	{
		document.querySelector('.cont').classList.toggle('s-signup')
	}
);

document.getElementById("sInButton").addEventListener('click', validateForm);

function validateForm(e) {
	e.preventDefault();
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

