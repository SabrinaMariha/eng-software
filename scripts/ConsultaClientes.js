document.querySelector("form").addEventListener("submit", async (event) => {
    event.preventDefault(); // Evita o reload da página

    const nome = document.getElementById("nome").value;
    const cpf = document.getElementById("cpf").value;
    const email = document.getElementById("email").value;

    try {
        const response = await fetch(`http://localhost:8080/clientes?nome=${nome}&cpf=${cpf}&email=${email}`);
        if (!response.ok) {
            throw new Error("Erro ao buscar clientes");
        }

        const clientes = await response.json();

        const tbody = document.querySelector("tbody");
        tbody.innerHTML = ""; // Limpa os resultados anteriores

        clientes.forEach((cliente) => {
            const tr = document.createElement("tr");

            tr.innerHTML = `
                <td>${cliente.nome}</td>
                <td>${cliente.dataNascimento}</td>
                <td>${cliente.cpf}</td>
                <td>${cliente.telefone}</td>
                <td>${cliente.email}</td>
                <td>
                    <button type="button" class="btn btn-outline-primary btn-sm" onclick="abrirModal(${cliente.id})">🔍</button>
                </td>
            `;
            tbody.appendChild(tr);
        });
    } catch (error) {
        console.error(error);
        alert("Ocorreu um erro ao buscar os clientes.");
    }
});
async function abrirModal(id) {
    try {
        const response = await fetch(`http://localhost:8080/clientes/${id}`);
        if (!response.ok) {
            throw new Error("Erro ao buscar detalhes do cliente");
        }

        const cliente = await response.json();

        document.querySelector("#modalClienteLabel").textContent = `Detalhes do Cliente: ${cliente.nome}`;
        document.querySelector("#modalCliente .modal-body").innerHTML = `
            <p><strong>Nome:</strong> ${cliente.nome}</p>
            <p><strong>Data de Nascimento:</strong> ${cliente.dataNascimento}</p>
            <p><strong>CPF:</strong> ${cliente.cpf}</p>
            <p><strong>Telefone:</strong> ${cliente.telefone}</p>
            <p><strong>E-mail:</strong> ${cliente.email}</p>
        `;

        const modal = new bootstrap.Modal(document.querySelector("#modalCliente"));
        modal.show();
    } catch (error) {
        console.error(error);
        alert("Ocorreu um erro ao buscar os detalhes do cliente.");
    }
}
