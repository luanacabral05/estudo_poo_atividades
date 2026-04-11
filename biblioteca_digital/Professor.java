public class Professor extends Usuario {

    public Professor(String codigo, String nome, String email) {
        super(codigo, nome, email);
    }

    @Override
    public int getLimiteEmprestimos() {
        return 5;
    }

    @Override
    public int getPrazoDevolucao() {
        return 14;
    }

    @Override
    public double getMultaDiaria() {
        return 1.00;
    }

    @Override
    public String getTipoUsuario() {
        return "Professor";
    }
}
