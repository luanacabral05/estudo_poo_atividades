import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Emprestimo implements Exibivel, CalculavelMulta {

    private String id;
    private Usuario usuario;
    private Material material;
    private LocalDate dataEmprestimo;
    private LocalDate dataPrevistaDevolucao;
    private LocalDate dataRealDevolucao;

    public Emprestimo(String id, Usuario usuario, Material material, LocalDate dataEmprestimo) {
        this.id = id;
        this.usuario = usuario;
        this.material = material;
        this.dataEmprestimo = dataEmprestimo;
        this.dataPrevistaDevolucao = dataEmprestimo.plusDays(usuario.getPrazoDevolucao());
        this.dataRealDevolucao = null;
    }

    public String getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Material getMaterial() {
        return material;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }

    public LocalDate getDataRealDevolucao() {
        return dataRealDevolucao;
    }

    public void registrarDevolucao(LocalDate dataRealDevolucao) {
        if (isFinalizado()) {
            throw new IllegalStateException("Este empréstimo já foi devolvido anteriormente.");
        }
        this.dataRealDevolucao = dataRealDevolucao;
        material.aumentarQuantidade();
    }

    public boolean isFinalizado() {
        return dataRealDevolucao != null;
    }

    public long calcularDiasAtraso() {
        if (!isFinalizado()) {
            return 0;
        }
        long atraso = ChronoUnit.DAYS.between(dataPrevistaDevolucao, dataRealDevolucao);
        return atraso > 0 ? atraso : 0;
    }

    @Override
    public double calcularMulta() {
        return calcularDiasAtraso() * usuario.getMultaDiaria();
    }

    @Override
    public String exibirResumo() {
        String status = isFinalizado() ? "Finalizado" : "Em andamento";
        String devolvido = isFinalizado() ? dataRealDevolucao.toString() : "Pendente";
        return "Empréstimo #" + id
                + " | Usuário: " + usuario.getNome()
                + " | Material: " + material.getTitulo()
                + " | Emprestado em: " + dataEmprestimo
                + " | Prazo: " + dataPrevistaDevolucao
                + " | Devolução: " + devolvido
                + " | Status: " + status;
    }
}
