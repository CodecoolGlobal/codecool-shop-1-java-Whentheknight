async function updateCart(data, callback) {
    fetch(`/updateCart${data}`)
        .then((response) => response.json())
        .then((items) => callback(items))
        .catch(error => console.log(error));
}

window.onload = () => {
    updateCart("?name=show", showCartItems)
    let cartSize = document.querySelector(".cart-size");
    cartSize.textContent = cartSize.dataset.value;
    cartHoverListener();
}


function cartHoverListener() {
// Get the modal
    var modal = document.getElementById("myModal");

// Get the button that opens the modal
    var btn = document.querySelector(".shopcart");

// Get the <span> element that closes the modal
    var span = document.getElementsByClassName("close")[0];

// When the user clicks on the button, open the modal
    btn.onclick = function () {
        if (modal.style.display === "block") {
            modal.style.display = "none";
        } else {
            modal.style.display = "block";
        }
    }

// When the user clicks on <span> (x), close the modal
    span.onclick = function () {
        modal.style.display = "none";
    }

// When the user clicks anywhere outside of the modal, close it
    window.onclick = function (event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
}

function showCartItems(items) {
    let modalC = document.querySelector(".modal-content");
    let totalPrice = 0;
    let checkoutTag = `<span style="float: left;font-size: 16px;
    let totalItems = 0;
    font-weight: bold; cursor:pointer;text-decoration: none;" class="checkout">Checkout</span>`;
    modalC.innerHTML = '';
    let totalItems = 0;
    for (var key in items) {
        let listOfString = key.split(",");

        let name = listOfString[1].substr(7);
        let priceStr = listOfString[2].substr(15);
        let price = parseFloat(parseFloat(priceStr).toFixed(2));

        let amount = parseInt(items[key]);
        totalItems += amount;
        totalPrice += parseFloat(parseFloat((amount * price)).toFixed(2));

        let itemTag = `<div class="cartItem">
<p id="itemName" style="float:left; font-size:17px; width:100%;">${name}</p>
<br>
<div><p id="itemPrice" style="float:left;font-size:13px;">Price: ${(price * amount).toFixed(2)} USD</p>

<pre id="plusBtn" data-itemname="${name}" style="float:right;font-weight: bold;font-size: 20px;cursor:pointer;"> +</pre>
<p style="float:right; font-size: 14px" class="amountOfItem" data-itemname="${name}">${amount}</p>
<pre style="float:right;font-weight: bold;font-size: 20px;cursor:pointer;" id="minusBtn" data-itemname="${name}">- </pre></div>

</div>
`

        modalC.insertAdjacentHTML("beforeend", itemTag)

    }
    document.querySelector('.cart-size').innerHTML = totalItems.toString();
    modalC.insertAdjacentHTML("beforeend", `<p>__________________________________________</p>`);
    modalC.insertAdjacentHTML("beforeend", `<div class="totalPrice"><p style="float: right">Total: ${totalPrice.toFixed(2)}</p></div>`);
    modalC.insertAdjacentHTML("beforeend", `<br><span class="empty_cart close">Empty Cart</span><br><div class="cartFooter"><span class="close close_button">Close</span> ${checkoutTag} </div>`)


    setTimeout(() => {
        let span = document.querySelector(".close_button");
        let modal = document.getElementById("myModal");
        let checkout = document.querySelector(".checkout");
        let minusBtns = document.querySelectorAll("#minusBtn");
        console.log(minusBtns)
        let plusBtns = document.querySelectorAll("#plusBtn");
        let inputFields = document.querySelectorAll(".amountOfItem");
        let emptyCart = document.querySelector(".empty_cart")

        span.onclick = function () {
            modal.style.display = "none";
        }

        checkout.onclick = function () {
            window.location.href = "/checkout";
        }

        minusBtns.forEach(button =>{
            button.onclick = function(){
                console.log(button.dataset.itemname)
                inputFields.forEach(inputF =>{
                    if(inputF.dataset.itemname === button.dataset.itemname && parseInt(inputF.textContent) > 0){
                        let cartSize = document.querySelector(".cart-size");
                        let productName = button.dataset.itemname;
                        console.log(productName)
                        let del = "del";
                        let fetchParam = `?name=${productName}&mod=${del}`;
                        updateCart(fetchParam, showCartItems);

                        cartSize.textContent = (parseInt(cartSize.textContent) - 1).toString()

                        inputF.textContent = parseInt(inputF.textContent)-1;
                        if(inputF.textContent === '0'){
                            inputF.parentElement.parentElement.remove();
                        }
                    }
                })
            }
        })
        plusBtns.forEach(button => {
            button.onclick = function() {
                inputFields.forEach(inputF => {
                    if(inputF.dataset.itemname === button.dataset.itemname) {
                        let cartSize = document.querySelector(".cart-size");
                        let productName = button.dataset.itemname;
                        let mod = "add";
                        let fetchParam = `?name=${productName}&mod=${mod}`;
                        updateCart(fetchParam, showCartItems);
                        cartSize.textContent = (parseInt(cartSize.textContent) + 1).toString();
                        inputF.value += 1;
                    }
                })
            }
        })

        emptyCart.onclick = function () {
            updateCart("?name=clear", showCartItems);
            let cartSize = document.querySelector(".cart-size");
            cartSize.textContent = "0"
        }
    }, 1000)

}

function emptyCartTags(){
    let modalC = document.querySelector(".modal-content");
    modalC.innerHTML = '';
    modalC.insertAdjacentHTML("beforeend", `<p id="emptyCart">Your cart is empty!</p>
        <span class="close">Close</span>`)
}



