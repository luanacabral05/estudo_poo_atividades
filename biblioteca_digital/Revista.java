public class Revista extends Material {

    private String edicao;

    public Revista(String codigo, String titulo, int anoPublicacao, int quantidadeDisponivel, String edicao) {
        super(codigo, titulo, anoPublicacao, quantidadeDisponivel);
        this.edicao = edicao;
    }

    public String getEdicao() {
        return edicao;
    }

    @Override
    public String getTipoMaterial() {
        return "Revista";
    }

    @Override
    public String exibirResumo() {
        return super.exibirResumo() + " | Edição: " + edicao;
    }
}
