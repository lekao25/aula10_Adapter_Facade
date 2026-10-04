package siga;

import java.util.List;

public interface MatriculaDAO {

    void inserir(Matricula matricula);

    List<Matricula> listarTodas();
}
