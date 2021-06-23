window.onload = () => {
    let checkout = document.querySelector(".pay")
    checkout.onclick = function () {
        fetch(`/updateCart?name=clear`)
            .then((response) => response.json())
}}