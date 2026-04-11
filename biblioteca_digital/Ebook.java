public class Ebook extends Material {

    private String formato;
    private double tamanhoArquivo;

    public Ebook(String codigo, String titulo, int anoPublicacao, int quantidadeDisponivel,
                 String formato, double tamanhoArquivo) {
        super(codigo, titulo, anoPublicacao, quantidadeDisponivel);
        this.formato = formato;
        this.tamanhoArquivo = tamanhoArquivo;
    }

    public String getFormato() {
        return formato;
    }

    public double getTamanhoArquivo() {
        return tamanhoArquivo;
    }

    @Override
    public String getTipoMaterial() {
        return "Ebook";
    }

    @Override
    public String exibirResumo() {
        return super.exibirResumo() + " | Formato: " + formato + " | Tamanho: " + tamanhoArquivo + " MB";
    }
}
