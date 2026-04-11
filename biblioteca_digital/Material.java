public abstract class Material implements Exibivel {

    private String codigo;
    private String titulo;
    private int anoPublicacao;
    private int quantidadeDisponivel;

    public Material(String codigo, String titulo, int anoPublicacao, int quantidadeDisponivel) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.anoPublicacao = anoPublicacao;
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public boolean estaDisponivel() {
        return quantidadeDisponivel > 0;
    }

    public void reduzirQuantidade() {
        quantidadeDisponivel--;
    }

    public void aumentarQuantidade() {
        quantidadeDisponivel++;
    }

    public abstract String getTipoMaterial();

    @Override
    public String exibirResumo() {
        return "[" + getTipoMaterial() + "] " + titulo
                + " (" + anoPublicacao + ")"
                + " | Disponíveis: " + quantidadeDisponivel;
    }
}
