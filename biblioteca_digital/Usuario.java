public abstract class Usuario implements Exibivel {

    private String codigo;
    private String nome;
    private String email;

    public Usuario(String codigo, String nome, String email) {
        this.codigo = codigo;
        this.nome = nome;
        this.email = email;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public abstract int getLimiteEmprestimos();

    public abstract int getPrazoDevolucao();

    public abstract double getMultaDiaria();

    public abstract String getTipoUsuario();

    @Override
    public String exibirResumo() {
        return "[" + getTipoUsuario() + "] " + nome
                + " | Código: " + codigo
                + " | Email: " + email
                + " | Limite: " + getLimiteEmprestimos() + " empréstimos"
                + " | Prazo: " + getPrazoDevolucao() + " dias";
    }
}
