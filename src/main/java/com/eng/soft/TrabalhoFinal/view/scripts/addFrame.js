let addressCount = 1;
let cardCount = 1;

function addAddress() {
    const addressContainer = document.getElementById("address-container");
    const newAddress = document.getElementById("address-template").cloneNode(true);
    newAddress.removeAttribute("id");
    newAddress.style.display = "block";
    newAddress.classList.add("endereco-frame"); // Adiciona a classe necessária

    // Atribui ids únicos aos selects de país, estado e cidade
    const selects = newAddress.querySelectorAll("select.form-select");
    if (selects.length >= 5) {
        selects[2].id = `pais-${addressCount}`;
        selects[3].id = `estado-${addressCount}`;
        selects[4].id = `cidade-${addressCount}`;
    }


    addressCount++;

    newAddress.querySelector(".remove-address-btn").onclick = () => newAddress.remove();
    addressContainer.appendChild(newAddress);
}

function addCard() {
    const cardContainer = document.getElementById("card-container");
    const newCard = document.getElementById("card-template").cloneNode(true);
    newCard.removeAttribute("id");
    newCard.style.display = "block";
    newCard.classList.add("cartao-frame"); // Adiciona a classe necessária

//      // Ajusta radio e label
//        const radioInput = newAddress.querySelector(".form-check-input");
//        const radioLabel = newAddress.querySelector(".form-check-label");
//        radioInput.id = `endereco-cobranca-${addressCount}`;
//        radioLabel.setAttribute("for", `endereco-cobranca-${addressCount}`);

//ajusta radio cartao-preferencial
    const radioInput = newCard.querySelector(".form-check-input");
    const radioLabel = newCard.querySelector(".form-check-label");
    radioInput.id = `cartao-preferencial-${cardCount}`;
    radioLabel.setAttribute("for", `cartao-preferencial-${cardCount}`);



    newCard.querySelector(".remove-card-btn").onclick = () => newCard.remove();
    cardContainer.appendChild(newCard);
}