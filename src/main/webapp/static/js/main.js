async function updateCart(data) {
    fetch(`/updateCart${data}`)
}

window.onload = () => {
    let cartSize = document.querySelector(".cart-size");
    cartSize.textContent = cartSize.dataset.value;
    let addToCartButtons = document.querySelectorAll(".btn-success");
    for (let button of addToCartButtons) {

        button.addEventListener('click', () => {
            let productName = button.dataset.name;
            let fetchParam = `?name=${productName}`;
            updateCart(fetchParam);
            cartSize.textContent = (parseInt(cartSize.textContent) + 1).toString()
        });
    }

    initButtons();
    initSideBar();
    initCartEventListener();
    openLoginModal();
}
    function initButtons() {
        let filterBtn = document.getElementById('filter-btn');
        filterBtn.addEventListener('click', function() {
            if (this.parentElement.parentElement.style.marginLeft === '300px'){
                closeNav();
            } else {
                openNav();
            }
        });
        let closeBtn = document.getElementById('close-btn');
        closeBtn.addEventListener('click', closeNav);
    }

    function initSideBar() {
        let categoryCheckBoxes = document.querySelectorAll('.category-check');
        categoryCheckBoxes.forEach(function (checkbox) {
            checkbox.addEventListener('change', function () {
                let category = checkbox.parentElement.querySelector('.category-check-label').textContent;
                category = category.substring(0, category.indexOf('(') - 1);
                let categoryContainer = document.querySelector(`.${category}`);
                if (this.checked) {
                    categoryContainer.style.display = 'block';
                } else {
                    categoryContainer.style.display = 'none';
                }
            })
        })
        let categoryClear = document.getElementById('category-clear');
        categoryClear.addEventListener('click', function () {
            categoryCheckBoxes.forEach(function (checkbox) {
                if (checkbox.checked) {
                    checkbox.click();
                }
            })
        })
        let categorySelect = document.getElementById('category-select');
        categorySelect.addEventListener('click', function () {
            categoryCheckBoxes.forEach(function (checkbox) {
                if (!checkbox.checked) {
                    checkbox.click();
                }
            })
        })
        let supplierCheckBoxes = document.querySelectorAll('.supplier-check');
        supplierCheckBoxes.forEach(function (checkbox) {
            checkbox.addEventListener('change', function () {
                let supplier = checkbox.parentElement.querySelector('.supplier-check-label').textContent;
                supplier = supplier.substring(0, supplier.indexOf('(') - 1);
                let cards = document.querySelectorAll('.supplier');
                cards.forEach(function (card) {
                    if (card.textContent === supplier) {
                        if (checkbox.checked) {
                            card.parentElement.style.display = 'block';
                        } else {
                            card.parentElement.style.display = 'none';
                        }
                    }
                })
            })
        })
        let supplierClear = document.getElementById('supplier-clear');
        supplierClear.addEventListener('click', function () {
            supplierCheckBoxes.forEach(function (checkbox) {
                if (checkbox.checked) {
                    checkbox.click();
                }
            })
        })
        let supplierSelect = document.getElementById('supplier-select');
        supplierSelect.addEventListener('click', function () {
            supplierCheckBoxes.forEach(function (checkbox) {
                if (!checkbox.checked) {
                    checkbox.click();
                }
            })
        })
    }



    function openNav() {
        document.getElementById("mySidenav").style.width = "300px";
        document.getElementById("main").style.marginLeft = "300px";
        document.querySelector("header").style.marginLeft = "300px";
    }

    function closeNav() {
        document.getElementById("mySidenav").style.width = "0";
        document.getElementById("main").style.marginLeft = "0";
        document.querySelector("header").style.marginLeft = "0";


    }

    function initCartEventListener() {
        let cartButton = document.querySelector('.cart-button');
        cartButton.addEventListener('click', function () {
            window.location.href = "/cart";
        })
    }

    function openLoginModal() {
    let loginButton = document.querySelector(".login")
    loginButton.addEventListener('click', () => {
        $('#loginModal').modal('show');
        closeLoginModal()
    })
    }

    function closeLoginModal() {
        let closeButton = document.querySelector(".close")
        closeButton.addEventListener('click', () => {
            $('#loginModal').modal('hide')
        })
    }

