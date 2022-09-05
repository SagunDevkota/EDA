let searchForm = document.querySelector('.search-form');

document.querySelector('#search-btn').onclick = () =>{
    searchForm.classList.toggle('active');
    navbar.classList.remove('active');
    cartItem.classList.remove('active');
}

let profileItem = document.querySelector('.profile-items-container');

document.querySelector('#profile-btn').onclick = () =>{
    profileItem.classList.toggle('active');
    navbar.classList.remove('active');
    searchForm.classList.remove('active');
}


function togglePopup() {
 document.getElementById("popup-1")
  .classList.toggle("active");
  toggle = false;
}

let form = document.querySelector("#supportForm");
        form.addEventListener("submit", e => {
            e.preventDefault();
            var xhr=new XMLHttpRequest();
            formdata = new FormData(form);
            var doc = document.getElementById("supportForm");
            document.getElementById("btn-submit-contact").value = "Sending...";
            var csrfToken = doc.childNodes[doc.childNodes.length-1].
            getElementsByTagName("input")[0].value
            xhr.open("POST","./sendEmail?_csrf="+csrfToken,true);
            xhr.send(formdata);
            xhr.onload = function() {
                console.log(`Loaded: ${xhr.status} ${xhr.response}`);
                var popup = document.getElementsByClassName("popup")[0];
                if(popup.classList.length == 1){
                	popup.classList.add("active");
                }
                var x = `${xhr.response}`
                if(`${xhr.response}` === "Could not send email"){
                	popup.innerHTML = "<div class='content' style='background: red;'><span class='message'>"+`${xhr.response}`+"</span><span class='close-btn' onclick='togglePopup()'>x</span>"
                }else{
                	popup.innerHTML = "<div class='content' style='background: green;'><span class='message'>"+`${xhr.response}`+"</span><span class='close-btn' onclick='togglePopup()'>x</span>"
                }
                document.getElementById("btn-submit-contact").value = "contact now";
                //var response = document.getElementById("responseText")
                //response.innerHTML = xhr.response
                // var object = document.getElementsByClassName("response")[0]
                // object.innerHTML = xhr.response
                // document.getElementsByClassName("recordingInitialized")[0].innerHTML = ""
            };
        })