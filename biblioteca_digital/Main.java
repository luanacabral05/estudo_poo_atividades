import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== SISTEMA DE BIBLIOTECA DIGITAL ===\n");
        Biblioteca biblioteca = new Biblioteca();

        System.out.println("--- Cadastro de Usuários ---");
        Aluno ana = new Aluno("A001", "Ana", "ana@email.com");
        Professor carlos = new Professor("P001", "Carlos", "carlos@email.com");

        biblioteca.cadastrarUsuario(ana);
        biblioteca.cadastrarUsuario(carlos);

        System.out.println("\n--- Cadastro de Materiais ---");
        Livro livro1 = new Livro("L001", "Java Básico", 2021, 2, "Herbert Schildt");
        Livro livro2 = new Livro("L002", "Estruturas de Dados", 2019, 1, "Thomas Cormen");
        Revista revista1 = new Revista("R001", "Revista Tech", 2024, 1, "Edição 42");
        Ebook ebook1 = new Ebook("E001", "POO em Java", 2022, 3, "PDF", 5.2);

        biblioteca.cadastrarMaterial(livro1);
        biblioteca.cadastrarMaterial(livro2);
        biblioteca.cadastrarMaterial(revista1);
        biblioteca.cadastrarMaterial(ebook1);

        biblioteca.listarUsuarios();
        biblioteca.listarMateriais();

        System.out.println("\n--- Realizando Empréstimos ---");
        LocalDate hoje = LocalDate.of(2026, 4, 1);

        Emprestimo emp1 = biblioteca.realizarEmprestimo("A001", "L001", hoje);
        Emprestimo emp2 = biblioteca.realizarEmprestimo("A001", "L002", hoje);
        Emprestimo emp3 = biblioteca.realizarEmprestimo("A001", "R001", hoje);
        Emprestimo emp4 = biblioteca.realizarEmprestimo("P001", "E001", hoje);

        System.out.println("\n--- Tentativa de Empréstimo Inválido ---");

        try {
            biblioteca.realizarEmprestimo("A001", "E001", hoje);
        } catch (IllegalStateException e) {
            System.out.println("ERRO: " + e.getMessage());
        }

        try {
            biblioteca.realizarEmprestimo("P001", "R001", hoje);
        } catch (IllegalStateException e) {
            System.out.println("ERRO: " + e.getMessage());
        }

        biblioteca.listarEmprestimosEmAndamento();

        System.out.println("\n--- Registrando Devoluções ---");

        LocalDate devolucaoComAtraso = LocalDate.of(2026, 4, 13);
        biblioteca.registrarDevolucao(emp1.getId(), devolucaoComAtraso);

        LocalDate devolucaoSemAtraso = LocalDate.of(2026, 4, 10);
        biblioteca.registrarDevolucao(emp4.getId(), devolucaoSemAtraso);

        System.out.println("\n--- Tentativa de Devolução Duplicada ---");
        try {
            biblioteca.registrarDevolucao(emp1.getId(), devolucaoComAtraso);
        } catch (IllegalStateException e) {
            System.out.println("ERRO: " + e.getMessage());
        }

        System.out.println("\n--- Multas ---");
        System.out.printf("Multa do empréstimo %s (%s - com atraso de %d dia(s)): R$ %.2f%n",
                emp1.getId(),
                emp1.getMaterial().getTitulo(),
                emp1.calcularDiasAtraso(),
                emp1.calcularMulta());

        System.out.printf("Multa do empréstimo %s (%s - sem atraso): R$ %.2f%n",
                emp4.getId(),
                emp4.getMaterial().getTitulo(),
                emp4.calcularMulta());

        biblioteca.listarMateriais();
        biblioteca.listarEmprestimosFinalizados();
        biblioteca.listarEmprestimosEmAndamento();

        System.out.println("\n=== FIM DA SIMULAÇÃO ===");
    }
}
