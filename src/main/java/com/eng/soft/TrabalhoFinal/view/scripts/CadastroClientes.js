document.addEventListener("DOMContentLoaded", () => {
    addAddress();
    addCard();

    const form = document.querySelector("form");
    if (form) {
        form.addEventListener("submit", async (event) => {
            event.preventDefault();

            const cliente = {
                nome: document.getElementById("nome").value.trim(),
                dataDeNascimento: document.getElementById("data-nascimento").value.trim(),
                genero: document.getElementById("genero").value,
                cpf: document.getElementById("cpf").value.trim(),
                email: document.getElementById("email").value.trim(),
                tipoDeTelefone: document.getElementById("tipo-telefone").value,
                telefone: document.getElementById("telefone").value.trim(),
                senha: document.getElementById("senha").value.trim(),
                confirmacaoSenha: document.getElementById("confirmacao-senha").value.trim(),
                enderecos: Array.from(document.querySelectorAll('.endereco-frame')).map(frame => ({
                    nomePais: frame.querySelector('input[name="pais"]').value.trim(),
                    nomeEstado: frame.querySelector('input[name="estado"]').value.trim(),
                    nomeCidade: frame.querySelector('input[name="cidade"]').value.trim(),
                    tipoDeResidencia: frame.querySelector('select[name="tipo-residencia"]').value,
                    tipoDeLogradouro: frame.querySelector('select[name="tipo-logradouro"]').value,
                    logradouro: frame.querySelector('input[name="logradouro"]').value.trim(),
                    numero: frame.querySelector('input[name="numero"]').value.trim(),
                    bairro: frame.querySelector('input[name="bairro"]').value.trim(),
                    complemento: frame.querySelector('input[name="complemento"]').value.trim(),
                    cep: frame.querySelector('input[name="cep"]').value.trim(),
                    //pear o tipo de endereço pelo checkbox selecionado
                    //<div class="col-md-4 d-flex flex-column">
                    //     <div class="form-check mt-3">
                    //         <input class="form-check-input" type="checkbox" name="endereco-entrega" id="endereco-entrega">
                    //         <label class="form-check-label" for="endereco-entrega">
                    //         Endereço de Entrega
                    //         </label>
                    //     </div>
                    //     <div class="form-check mt-3">
                    //         <input class="form-check-input" type="checkbox" name="endereco-cobranca" id="endereco-cobranca">
                    //         <label class="form-check-label" for="endereco-cobranca">
                    //         Endereço de Cobrança
                    //         </label>
                    //     </div>
                    // </div>
                    tipoDeEndereco: "nenhum", // Default value
                    cobranca: frame.querySelector('input[name="endereco-cobranca"]').checked ? "Cobranca" : "",
                    entrega: frame.querySelector('input[name="endereco-entrega"]').checked ? "Entrega" : "",
                    observacoes: frame.querySelector('textarea[name="observacoes"]').value.trim()
                })),
                cartoesDeCredito: Array.from(document.querySelectorAll('.cartao-frame')).map(frame => ({
                    numero: frame.querySelector('input[name="numero-cartao"]').value.trim(),
                    bandeira: frame.querySelector('select[name="bandeira"]').value,
                    nomeTitular: frame.querySelector('input[name="nome-titular"]').value.trim(),
                    validade: frame.querySelector('input[name="validade"]').value.trim(),
                    cvv: frame.querySelector('input[name="cvv"]').value.trim(),
                    preferencial: frame.querySelector('input[type="radio"][name="cartao-preferencial"]').checked,

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