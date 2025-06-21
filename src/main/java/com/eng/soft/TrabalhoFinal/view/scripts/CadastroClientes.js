let dados;

// Carrega os dados dos países e preenche todos os selects de país dinâmicos
async function carregarDadosPaises() {
    try {
        const response = await fetch("/dados.json");
        if (!response.ok) throw new Error("Erro ao carregar os dados");
        dados = await response.json();
        preencherTodosSelectsPais();
    } catch (error) {
        console.error("Erro ao carregar os dados:", error);
        alert("Não foi possível carregar os dados. Tente novamente mais tarde.");
    }
}

// Preenche todos os selects de país visíveis
function preencherTodosSelectsPais() {
    document.querySelectorAll('select[id^="pais-"]').forEach(select => {
        // Limpa opções antigas, exceto a primeira
        select.length = 1;
        dados.paises.forEach(pais => {
            const option = document.createElement("option");
            option.value = pais.id;
            option.textContent = pais.nome;
            select.appendChild(option);
        });
        select.onchange = () => preencherEstados(select);
    });
}

// Preenche os estados conforme o país selecionado
function preencherEstados(paisSelect) {
    const addressDiv = paisSelect.closest('.row').parentElement.parentElement;
    const estadoSelect = addressDiv.querySelector('select[id^="estado-"]');
    const cidadeSelect = addressDiv.querySelector('select[id^="cidade-"]');
    estadoSelect.length = 1;
    cidadeSelect.length = 1;
    const pais = dados.paises.find(p => p.id == paisSelect.value);
    if (pais && pais.estados) {
        pais.estados.forEach(estado => {
            const option = document.createElement("option");
            option.value = estado.id;
            option.textContent = estado.nome;
            estadoSelect.appendChild(option);
        });
        estadoSelect.onchange = () => preencherCidades(estadoSelect, pais);
    }
}

// Preenche as cidades conforme o estado selecionado
function preencherCidades(estadoSelect, pais) {
    const addressDiv = estadoSelect.closest('.row').parentElement.parentElement;
    const cidadeSelect = addressDiv.querySelector('select[id^="cidade-"]');
    cidadeSelect.length = 1;
    const estado = pais.estados.find(e => e.id == estadoSelect.value);
    if (estado && estado.cidades) {
        estado.cidades.forEach(cidade => {
            const option = document.createElement("option");
            option.value = cidade.id;
            option.textContent = cidade.nome;
            cidadeSelect.appendChild(option);
        });
    }
}

// Sempre que um novo endereço for adicionado, chame preencherTodosSelectsPais()
document.addEventListener("DOMContentLoaded", () => {
    carregarDadosPaises();
    addAddress();
    addCard();

    // Observa adição de novos endereços para atualizar selects
    const addressContainer = document.getElementById("address-container");
    const observer = new MutationObserver(() => preencherTodosSelectsPais());
    observer.observe(addressContainer, { childList: true });

    // Evento para envio do formulário
    const form = document.querySelector("form");
    if (form) {
        form.addEventListener("submit", async (event) => {
            event.preventDefault();
//
            const cliente = {
                nome: document.getElementById("nome").value.trim(),
                dataDeNascimento: document.getElementById("data-nascimento").value.trim(),
                cpf: document.getElementById("cpf").value.trim(),
                email: document.getElementById("email").value.trim(),
                tipoDeTelefone: document.getElementById("tipo-telefone").value,
                telefone: document.getElementById("telefone").value.trim(),
                senha: document.getElementById("senha").value.trim(),
                confirmacaoSenha: document.getElementById("confirmacao-senha").value.trim(),
                enderecos: Array.from(document.querySelectorAll('.endereco-frame')).map(frame => ({
                    nomePais: frame.querySelector('select[id^="pais-"]').value,
                    nomeEstado: frame.querySelector('select[id^="estado-"]').value,
                    nomeCidade: frame.querySelector('select[id^="cidade-"]').value,
                    tipoDeResidencia: frame.querySelector('select[name="tipo-residencia"]').value,
                    tipoDeLogradouro: frame.querySelector('select[name="tipo-logradouro"]').value,
                    logradouro: frame.querySelector('input[name="logradouro"]').value.trim(),
                    numero: frame.querySelector('input[name="numero"]').value.trim(),
                    bairro: frame.querySelector('input[name="bairro"]').value.trim(),
                    complemento: frame.querySelector('input[name="complemento"]').value.trim(),
                    cep: frame.querySelector('input[name="cep"]').value.trim(),
                    cobranca: frame.querySelector('input[type="radio"][name="endereco-cobranca"]').checked,
                    tipoDeEndereco: frame.querySelector('select[name="tipo-endereco"]').value,
                    observacoes: frame.querySelector('textarea[name="observacoes"]').value.trim()
                })),
                cartoesDeCredito: Array.from(document.querySelectorAll('.cartao-frame')).map(frame => ({
                    numero: frame.querySelector('input[name="numero-cartao"]').value.trim(),
                    bandeira: frame.querySelector('select[name="bandeira"]').value,
                    nomeTitular: frame.querySelector('input[name="nome-titular"]').value.trim(),
                    validade: frame.querySelector('input[name="validade"]').value.trim(),
                    cvv: frame.querySelector('input[name="cvv"]').value.trim()
                }))

            };

            if (cliente.senha !== cliente.confirmacaoSenha) {
                alert("As senhas não coincidem. Verifique e tente novamente.");
                return;
            }
            console.log(JSON.stringify(cliente, null, 2));
            try {
                const response = await fetch("http://localhost:8080", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                    },
                    body: JSON.stringify(cliente),
                });

                if (!response.ok) throw new Error("Erro ao cadastrar cliente");

                const result = await response.json();
                alert(`Cliente cadastrado com sucesso: ${result.nome}`);
            } catch (error) {
                console.error("Erro no cadastro:", error);
                alert("Ocorreu um erro ao cadastrar o cliente. Tente novamente mais tarde.");
            }
        });
    }
});
