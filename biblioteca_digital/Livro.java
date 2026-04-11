public class Livro extends Material {

    private String autor;

    public Livro(String codigo, String titulo, int anoPublicacao, int quantidadeDisponivel, String autor) {
        super(codigo, titulo, anoPublicacao, quantidadeDisponivel);
        this.autor = autor;
    }

    public String getAutor() {
        return autor;
    }

    @Override
    public String getTipoMaterial() {
        return "Livro";
    }

    @Override
    public String exibirResumo() {
        return super.exibirResumo() + " | Autor: " + autor;
    }
}
