package siga;

public class RelatorioSituacao {

    private final FonteDeAlunos fonteDeAlunos;

    public RelatorioSituacao(FonteDeAlunos fonteDeAlunos) {
        this.fonteDeAlunos = fonteDeAlunos;
    }

    public void imprimir() {
        System.out.println("   Situação segundo o relatório:");
        for (Aluno aluno : fonteDeAlunos.listar()) {
            System.out.println("     " + aluno);
        }
    }
}
