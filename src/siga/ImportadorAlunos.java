package siga;

import java.util.List;

public class ImportadorAlunos {

    private final FonteDeAlunos fonteDeAlunos;
    private final AlunoDAO dao;

    public ImportadorAlunos(FonteDeAlunos fonteDeAlunos, AlunoDAO dao) {
        this.fonteDeAlunos = fonteDeAlunos;
        this.dao = dao;
    }

    public int importar() {
        List<Aluno> alunos = fonteDeAlunos.listar();

        int importados = 0;
        for (Aluno aluno : alunos) {
            dao.inserir(aluno);
            importados++;
        }
        return importados;
    }
}
