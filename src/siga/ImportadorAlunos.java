package siga;

import java.util.ArrayList;
import java.util.List;

public class ImportadorAlunos {

    private final SecretariaLegadoWS servicoLegado;
    private final AlunoDAO dao;

    public ImportadorAlunos(SecretariaLegadoWS servicoLegado, AlunoDAO dao) {
        this.servicoLegado = servicoLegado;
        this.dao = dao;
    }

    public int importar() {
        List<Aluno> alunos = new ArrayList<>();

        for (String[] linha : servicoLegado.consultarTabelaAlunos()) {
            Aluno aluno = new Aluno(linha[0], linha[1]);
            aluno.setAtivo("A".equals(linha[2]));
            alunos.add(aluno);
        }

        int importados = 0;
        for (Aluno aluno : alunos) {
            dao.inserir(aluno);
            importados++;
        }
        return importados;
    }
}
