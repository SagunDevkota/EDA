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