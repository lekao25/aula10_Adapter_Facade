package siga;

public class Main {

    public static void main(String[] args) {
        SecretariaLegadoWS servicoLegado = new SecretariaLegadoWS();
        FonteDeAlunos fonteDeAlunos = new AdaptadorSecretariaLegado(servicoLegado);
        AlunoDAO alunoDAO = new AlunoDAOMemoria();

        System.out.println("=== SIGA — Aula 10: Adapter e Facade (após refatoração) ===");

        System.out.println();
        System.out.println("1) Importação a partir do sistema legado");
        ImportadorAlunos importador = new ImportadorAlunos(fonteDeAlunos, alunoDAO);
        System.out.println("   Alunos importados: " + importador.importar());
        System.out.println("   Situação segundo o importador:");
        for (Aluno aluno : alunoDAO.listarTodos()) {
            System.out.println("     " + aluno);
        }

        System.out.println();
        System.out.println("2) O mesmo dado, convertido pelo mesmo Adapter");
        new RelatorioSituacao(fonteDeAlunos).imprimir();
        System.out.println("   >> A aluna 2024003 agora aparece ATIVA nos dois lugares.");

        System.out.println();
        System.out.println("3) Matrícula pela tela");
        Aluno bolsista = alunoDAO.buscarPorMatricula("2024001");
        bolsista.setBolsista(true);

        FachadaMatricula fachadaMatricula = new FachadaMatricula(
                new ValidadorCpf(),
                alunoDAO,
                new ControleVagas(),
                new CalculadoraDesconto(),
                new MatriculaDAOMemoria(),
                new NotificadorEmail());

        TelaMatricula tela = new TelaMatricula(fachadaMatricula);

        tela.aoClicarEmMatricular("2024001", "123.456.789-09", "DSM-2A");
        tela.aoClicarEmMatricular("2024002", "111", "DSM-2A");
        tela.aoClicarEmMatricular("2024009", "123.456.789-09", "DSM-2A");
        tela.aoClicarEmMatricular("2024004", "123.456.789-09", "DSM-2B");
    }
}
