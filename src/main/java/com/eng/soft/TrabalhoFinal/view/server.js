const express = require("express");
const path = require("path");

const app = express();
const PORT = 3000;

// Para interpretar JSON no body das requisições POST
app.use(express.json());

// Servir os arquivos estáticos
app.use("/scripts", express.static(path.join(__dirname, "scripts")));
app.use("/styles", express.static(path.join(__dirname, "styles")));
// Servir toda a pasta index (html e json)
app.use("/index", express.static(path.join(__dirname, "index")));
app.use((req, res, next) => {
    res.set('Cache-Control', 'no-store');
    next();
});

// Rota para dados.json (arquivo específico)
app.get("/dados.json", (req, res) => {
  res.sendFile(path.join(__dirname, "index", "dados.json"));
});

// Rota para o HTML inicial
app.get("/", (req, res) => {
  res.sendFile(path.join(__dirname, "index", "CadastroClientes.html"));
});

app.get("/consulta", (req, res) => {
  res.sendFile(path.join(__dirname, "index", "ConsultaClientes.html"));
});
// http://localhost:3000/editar?id=${clienteId}
app.get("/editar", async (req, res) => {
  const clienteId = req.query.id;

  if (!clienteId) {
    return res.status(400).json({ error: "Cliente ID não fornecido." });
  }

  try {
    // Faz a requisição ao backend Spring Boot usando fetch
    const backendResponse = await fetch(`http://localhost:8080/editar?id=${clienteId}`, {
      headers: { Accept: "application/json" },
    });

    if (!backendResponse.ok) {
      throw new Error(`Erro ao buscar cliente do backend: ${backendResponse.statusText}`);
    }

    const clienteData = await backendResponse.json();

    // Verifica se a requisição é para JSON ou HTML
    const acceptHeader = req.headers.accept || "";
    if (acceptHeader.includes("application/json")) {
      return res.json(clienteData); // Retorna JSON
    }
    console.log("Cliente encontrado:", clienteData);
    // Caso contrário, serve o HTML
    res.sendFile(path.join(__dirname, "index", "EdicaoClientes.html"));
  } catch (error) {
    console.error("Erro ao buscar cliente do backend:", error.message);
    res.status(500).json({ error: "Erro ao buscar cliente do backend." });
  }
});
app.put("/editar", async (req, res) => {
  const cliente = req.body;

  if (!cliente || !cliente.id) {
    return res.status(400).json({ error: "Dados do cliente inválidos." });
  }

  try {
    // Aqui você poderia enviar os dados para o backend Spring Boot
    // Simulando sucesso
    console.log("Cliente atualizado:", cliente);
    res.json({ message: "Cliente atualizado com sucesso!" });
  } catch (error) {
    console.error("Erro ao atualizar cliente:", error.message);
    res.status(500).json({ error: "Erro ao atualizar cliente." });
  }
});

// Exemplo básico de recebimento do POST (cadastro)
app.post("/", (req, res) => {
  const cliente = req.body;

  // Aqui você poderia salvar o cliente no banco, etc
  console.log("Recebido cliente:", cliente);

  // Simula sucesso
  res.json({ nome: cliente.nome });
});

app.listen(PORT, () => {
  console.log(`Servidor rodando em http://localhost:${PORT}`);
});
