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
