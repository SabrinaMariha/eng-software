document.querySelector("form").addEventListener("submit", async (event) => {
    event.preventDefault(); // Evita o reload da página ao enviar o formulário
    console.log("Botão 'Filtrar' foi clicado"); // Log para depuração

    // Coleta os dados do formulário
    const nome = document.getElementById("nome").value.trim() || null;
    const dataDeNascimento = document.getElementById("dataNascimento").value.trim() || null;
    const cpf = document.getElementById("cpf").value.trim() || null;
    const telefone = document.getElementById("telefone").value.trim() || null;
    const email = document.getElementById("email").value.trim() || null;

    console.log("Dados coletados:", { nome, dataDeNascimento, cpf, telefone, email });

    // Constrói os parâmetros de consulta somente com os valores existentes
    const params = new URLSearchParams();
    if (nome) params.append("nome", nome);
    if (dataDeNascimento) params.append("dataDeNascimento", dataDeNascimento);
    if (cpf) params.append("cpf", cpf);
    if (telefone) params.append("telefone", telefone);
    if (email) params.append("email", email);

    try {
        // Faz a requisição para o servidor com os parâmetros de consulta
        const response = await fetch(`http://localhost:8080/consulta?${params.toString()}`);
        const data = await response.json();

        if (response.ok) {
            console.log("Resposta recebida do servidor:", data);
            renderizarResultados(data.clientes || []); // Use data.clientes
        } else {
            console.error("Erro na consulta:", data);
            alert(data.mensagem || "Erro ao consultar clientes. Verifique os logs.");
        }
    } catch (error) {
        console.error("Erro na requisição:", error);
        alert("Erro na comunicação com o servidor.");
    }
});

function renderizarResultados(resultados) {
    const tabela = document.querySelector("table tbody");
    tabela.innerHTML = ""; // Clear previous results

    if (resultados.length === 0) {
        tabela.innerHTML = "<tr><td colspan='6' class='text-center'>Nenhum cliente encontrado.</td></tr>";
        return;
    }

    resultados.forEach((cliente) => {
        const row = document.createElement("tr");

        row.innerHTML = `
            <td>${cliente.nome || "N/A"}</td>
            <td>${cliente.dataDeNascimento || "N/A"}</td>
            <td>${cliente.cpf || "N/A"}</td>
            <td>${cliente.telefone || "N/A"}</td>
            <td>${cliente.email || "N/A"}</td>
            <td>
                <button type="button" class="btn btn-outline-primary btn-sm">🔍</button>
                <button type="button" class="btn btn-outline-secondary btn-sm">✏️</button>
            </td>
        `;

        tabela.appendChild(row);
    });
}