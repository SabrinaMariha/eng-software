// A senha cadastrada pelo usuário deve ser composta de pelo menos 8 caracteres, 
// ter letras maiúsculas e minúsculas além de conter caracteres especiais.
// A senha deve ser criptografada 


public class Senha{
    private String valor;

    public Senha(String valor) {
        this.valor = valor;
        validarSenha(valor);
        criptografar();
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public void criptografar() {
        // Lógica de criptografia
        
    }

    public void descriptografar() {
        // Lógica de descriptografia
    }

    public void validarSenha(String senha) {
        if (senha.length() < 8) {
            throw new IllegalArgumentException("A senha deve ter pelo menos 8 caracteres.");
        }
        if (!senha.matches(".*[A-Z].*")) {
            throw new IllegalArgumentException("A senha deve conter pelo menos uma letra maiúscula.");
        }
        if (!senha.matches(".*[a-z].*")) {
            throw new IllegalArgumentException("A senha deve conter pelo menos uma letra minúscula.");
        }
        if (!senha.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
            throw new IllegalArgumentException("A senha deve conter pelo menos um caractere especial.");
        }
    }
}