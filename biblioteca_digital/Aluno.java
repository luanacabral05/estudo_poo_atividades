public class Aluno extends Usuario {

    public Aluno(String codigo, String nome, String email) {
        super(codigo, nome, email);
    }

    @Override
    public int getLimiteEmprestimos() {
        return 3;
    }

    @Override
    public int getPrazoDevolucao() {
        return 7;
    }

    @Override
    public double getMultaDiaria() {
        return 2.50;
    }

    @Override
    public String getTipoUsuario() {
        return "Aluno";
    }
}
