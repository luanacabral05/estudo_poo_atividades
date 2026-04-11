import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {

    private List<Usuario> usuarios;
    private List<Material> materiais;
    private List<Emprestimo> emprestimos;
    private int contadorEmprestimos;

    public Biblioteca() {
        this.usuarios = new ArrayList<>();
        this.materiais = new ArrayList<>();
        this.emprestimos = new ArrayList<>();
        this.contadorEmprestimos = 0;
    }

    public void cadastrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
        System.out.println("Usuário cadastrado com sucesso: " + usuario.getNome());
    }

    public void cadastrarMaterial(Material material) {
        materiais.add(material);
        System.out.println("Material cadastrado com sucesso: " + material.getTitulo());
    }

    public Usuario buscarUsuarioPorCodigo(String codigo) {
        for (Usuario u : usuarios) {
            if (u.getCodigo().equals(codigo)) {
                return u;
            }
        }
        return null;
    }

    public Material buscarMaterialPorCodigo(String codigo) {
        for (Material m : materiais) {
            if (m.getCodigo().equals(codigo)) {
                return m;
            }
        }
        return null;
    }

    public Emprestimo buscarEmprestimoPorId(String id) {
        for (Emprestimo e : emprestimos) {
            if (e.getId().equals(id)) {
                return e;
            }
        }
        return null;
    }

    public Emprestimo realizarEmprestimo(String codigoUsuario, String codigoMaterial, LocalDate dataEmprestimo) {
        Usuario usuario = buscarUsuarioPorCodigo(codigoUsuario);
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não encontrado: " + codigoUsuario);
        }

        Material material = buscarMaterialPorCodigo(codigoMaterial);
        if (material == null) {
            throw new IllegalArgumentException("Material não encontrado: " + codigoMaterial);
        }

        if (!material.estaDisponivel()) {
            throw new IllegalStateException("Material indisponível: '" + material.getTitulo() + "' não possui exemplares.");
        }

        int ativos = contarEmprestimosAtivos(codigoUsuario);
        if (ativos >= usuario.getLimiteEmprestimos()) {
            throw new IllegalStateException("Limite atingido: " + usuario.getNome()
                    + " já possui " + ativos + " empréstimo(s) ativo(s). Limite: " + usuario.getLimiteEmprestimos() + ".");
        }

        contadorEmprestimos++;
        String id = "EMP" + String.format("%03d", contadorEmprestimos);
        Emprestimo emprestimo = new Emprestimo(id, usuario, material, dataEmprestimo);
        material.reduzirQuantidade();
        emprestimos.add(emprestimo);

        System.out.println("Empréstimo realizado! " + usuario.getNome() + " pegou '"
                + material.getTitulo() + "' | Prazo de devolução: " + emprestimo.getDataPrevistaDevolucao());
        return emprestimo;
    }

    public void registrarDevolucao(String idEmprestimo, LocalDate dataRealDevolucao) {
        Emprestimo emprestimo = buscarEmprestimoPorId(idEmprestimo);
        if (emprestimo == null) {
            throw new IllegalArgumentException("Empréstimo não encontrado: " + idEmprestimo);
        }
        emprestimo.registrarDevolucao(dataRealDevolucao);
        System.out.println("Devolução registrada: " + emprestimo.getUsuario().getNome()
                + " devolveu '" + emprestimo.getMaterial().getTitulo() + "'");
    }

    public void listarUsuarios() {
        System.out.println("\n=== USUÁRIOS CADASTRADOS ===");
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }
        for (Usuario u : usuarios) {
            System.out.println(u.exibirResumo());
        }
    }

    public void listarMateriais() {
        System.out.println("\n=== MATERIAIS CADASTRADOS ===");
        if (materiais.isEmpty()) {
            System.out.println("Nenhum material cadastrado.");
            return;
        }
        for (Material m : materiais) {
            System.out.println(m.exibirResumo());
        }
    }

    public void listarEmprestimosEmAndamento() {
        System.out.println("\n=== EMPRÉSTIMOS EM ANDAMENTO ===");
        boolean encontrou = false;
        for (Emprestimo e : emprestimos) {
            if (!e.isFinalizado()) {
                System.out.println(e.exibirResumo());
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhum empréstimo em andamento.");
        }
    }

    public void listarEmprestimosFinalizados() {
        System.out.println("\n=== EMPRÉSTIMOS FINALIZADOS ===");
        boolean encontrou = false;
        for (Emprestimo e : emprestimos) {
            if (e.isFinalizado()) {
                System.out.println(e.exibirResumo());
                System.out.printf("   Multa: R$ %.2f%n", e.calcularMulta());
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhum empréstimo finalizado.");
        }
    }

    public int contarEmprestimosAtivos(String codigoUsuario) {
        int count = 0;
        for (Emprestimo e : emprestimos) {
            if (e.getUsuario().getCodigo().equals(codigoUsuario) && !e.isFinalizado()) {
                count++;
            }
        }
        return count;
    }
}
