package siga;

public class RelatorioSituacao {

    private final SecretariaLegadoWS servicoLegado;

    public RelatorioSituacao(SecretariaLegadoWS servicoLegado) {
        this.servicoLegado = servicoLegado;
    }

    public void imprimir() {
        System.out.println("   Situação segundo o relatório:");
        for (String[] linha : servicoLegado.consultarTabelaAlunos()) {
            Aluno aluno = new Aluno(linha[0], linha[1]);
            aluno.setAtivo("A".equalsIgnoreCase(linha[2]));   // aqui trata
            System.out.println("     " + aluno);
        }
    }
}
