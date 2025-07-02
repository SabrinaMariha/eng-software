
let clienteId;
document.addEventListener("DOMContentLoaded", async () => {
  const params = new URLSearchParams(window.location.search);
   clienteId = params.get("id");

  if (!clienteId) {
    alert("Cliente não encontrado.");
    return;
  }

  try {
    const response = await fetch(`http://localhost:8080/editar?id=${clienteId}`, {
      headers: { Accept: "application/json" },
    });

    if (!response.ok) throw new Error("Erro ao buscar cliente.");

    const clienteData = await response.json();

    // Preencher campos do cliente
    document.getElementById("nome").value = clienteData.nome || "";
    document.getElementById("data-nascimento").value = clienteData.dataDeNascimento || "";
    document.getElementById("genero").value = clienteData.genero || "Escolha...";
    document.getElementById("cpf").value = clienteData.cpf || "";
    document.getElementById("tipo-telefone").value = clienteData.tipoDeTelefone || "Escolha...";
    document.getElementById("email").value = clienteData.email || "";
    document.getElementById("telefone").value = clienteData.telefone || "";
    document.getElementById("senha").value = clienteData.senha || "";
    document.getElementById("confirmacao-senha").value = clienteData.senha || "";

    // Preencher endereços
    clienteData.enderecos.forEach((endereco) => {
      addAddress(endereco);
    });

    // Preencher cartões de crédito
    clienteData.cartoesDeCredito.forEach((cartao) => {
      addCard(cartao);
    });
  } catch (error) {
    console.error(error);
    alert("Erro ao carregar os dados do cliente.");
  }
});

function addAddress(endereco = {}) {
    const addressContainer = document.getElementById("address-container");
    const addressTemplate = document.getElementById("address-template");

    const newAddress = addressTemplate.cloneNode(true);
    newAddress.style.display = "block";
    newAddress.classList.add("endereco-frame");

    // Set the ID to null if not provided
    newAddress.setAttribute("data-id", endereco.id || null);
    console.log("Adicionando endereço com ID:", endereco.id || null);

    // Populate fields
    newAddress.querySelector('[name="tipo-residencia"]').value = endereco.tipoDeResidencia || "Escolha...";
    newAddress.querySelector('[name="tipo-logradouro"]').value = endereco.tipoDeLogradouro || "Escolha...";
    newAddress.querySelector('[name="logradouro"]').value = endereco.logradouro || "";
    newAddress.querySelector('[name="numero"]').value = endereco.numero || "";
    newAddress.querySelector('[name="bairro"]').value = endereco.bairro || "";
    newAddress.querySelector('[name="complemento"]').value = endereco.complemento || "";
    newAddress.querySelector('[name="cep"]').value = endereco.cep || "";
    newAddress.querySelector('[name="cidade"]').value = endereco.nomeCidade || "";
    newAddress.querySelector('[name="estado"]').value = endereco.nomeEstado || "";
    newAddress.querySelector('[name="pais"]').value = endereco.nomePais || "";
    newAddress.querySelector('input[name="endereco-cobranca"]').checked = endereco.cobranca === "Cobranca";
    newAddress.querySelector('input[name="endereco-entrega"]').checked = endereco.entrega === "Entrega";
    // Observações
    newAddress.querySelector('[name="observacoes"]').value = endereco.observacoes || "";

    addressContainer.appendChild(newAddress);
}

function addCard(cartao = {}) {
    const cardContainer = document.getElementById("card-container");
    const cardTemplate = document.getElementById("card-template");

    const newCard = cardTemplate.cloneNode(true);
    newCard.style.display = "block";
    newCard.classList.add("cartao-frame");

    // Set the ID to null if not provided
    newCard.setAttribute("data-id", cartao.id || null);
    console.log("Adicionando cartão com ID:", cartao.id || null);

    // Populate fields
    newCard.querySelector('[name="numero-cartao"]').value = cartao.numero || "";
    newCard.querySelector('[name="nome-titular"]').value = cartao.nomeTitular || "";
    newCard.querySelector('[name="bandeira"]').value = cartao.bandeira || "Escolha...";
    newCard.querySelector('[name="cvv"]').value = cartao.cvv || "";
    newCard.querySelector('[name="validade"]').value = cartao.validade || "";
    newCard.querySelector('[name="cartao-preferencial"]').checked = cartao.preferencial || false;
    cardContainer.appendChild(newCard);
}


const form = document.querySelector("form");
if (form) {
    form.addEventListener("submit", async (event) => {
        event.preventDefault();

        if (!clienteId) {
            alert("Cliente ID não encontrado.");
            return;
        }

        // Verificar se os endereços e cartões estão sendo capturados
        const enderecos = Array.from(document.querySelectorAll('.endereco-frame')).map(frame => {
         const id = frame.getAttribute("data-id") || null; // Capturar o ID do endereço
            console.log("Endereço ID:", id); // Depuração
            const nomePais = frame.querySelector('input[name="pais"]').value.trim();
            const nomeEstado = frame.querySelector('input[name="estado"]').value.trim();
            const nomeCidade = frame.querySelector('input[name="cidade"]').value.trim();

            // Verificar se os campos estão preenchidos
            if (!nomePais && !nomeEstado && !nomeCidade) {
                console.warn("Endereço vazio ignorado.");
                return null; // Ignorar endereços vazios
            }

            return {
                id: frame.getAttribute("data-id") || null, // Capturar o ID do endereço, se existir
                nomePais,
                nomeEstado,
                nomeCidade,
                tipoDeResidencia: frame.querySelector('select[name="tipo-residencia"]').value,
                tipoDeLogradouro: frame.querySelector('select[name="tipo-logradouro"]').value,
                logradouro: frame.querySelector('input[name="logradouro"]').value.trim(),
                numero: frame.querySelector('input[name="numero"]').value.trim(),
                bairro: frame.querySelector('input[name="bairro"]').value.trim(),
                complemento: frame.querySelector('input[name="complemento"]').value.trim(),
                cep: frame.querySelector('input[name="cep"]').value.trim(),
                cobranca: frame.querySelector('input[name="endereco-cobranca"]').checked ? "Cobranca" : "",
                entrega: frame.querySelector('input[name="endereco-entrega"]').checked ? "Entrega" : "",
                observacoes: frame.querySelector('textarea[name="observacoes"]').value.trim()
            };
        }).filter(endereco => endereco !== null); // Remover endereços vazios

        const cartoesDeCredito = Array.from(document.querySelectorAll('.cartao-frame')).map(frame => {
         const id = frame.getAttribute("data-id") || null; // Capturar o ID do endereço
            console.log("cartao ID:", id); // Depuração
            const numero = frame.querySelector('input[name="numero-cartao"]').value.trim();
            const nomeTitular = frame.querySelector('input[name="nome-titular"]').value.trim();

            // Verificar se os campos estão preenchidos
            if (!numero && !nomeTitular) {
                console.warn("Cartão vazio ignorado.");
                return null; // Ignorar cartões vazios
            }

            return {
                id: frame.getAttribute("data-id") || null, // Capturar o ID do cartão, se existir
                numero,
                bandeira: frame.querySelector('select[name="bandeira"]').value,
                nomeTitular,
                validade: frame.querySelector('input[name="validade"]').value.trim(),
                cvv: frame.querySelector('input[name="cvv"]').value.trim(),
                preferencial: frame.querySelector('input[type="radio"][name="cartao-preferencial"]').checked,

            };
        }).filter(cartao => cartao !== null); // Remover cartões vazios

        const cliente = {
            nome: document.getElementById("nome").value.trim(),
            dataDeNascimento: document.getElementById("data-nascimento").value.trim(),
            genero: document.getElementById("genero").value,
            cpf: document.getElementById("cpf").value.trim(),
            email: document.getElementById("email").value.trim(),
            tipoDeTelefone: document.getElementById("tipo-telefone").value,
            telefone: document.getElementById("telefone").value.trim(),
           // senha: document.getElementById("senha").value.trim(),
            //confirmacaoSenha: document.getElementById("confirmacao-senha").value.trim(),
            enderecos,
            cartoesDeCredito
        };

        console.log("Dados do cliente:", cliente);

        try {
            const response = await fetch(`http://localhost:8080/editar?id=${clienteId}`, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json",
                    "Accept": "application/json",
                },
                body: JSON.stringify(cliente),
            });

            if (!response.ok) throw new Error("Erro ao atualizar o cliente");

            const result = await response.json();
            alert(`Cliente atualizado com sucesso: ${result.nome}`);
        } catch (error) {
            console.error(error);
            alert("Erro ao atualizar cliente.");
        }
    }); // Missing closing brace added here
}