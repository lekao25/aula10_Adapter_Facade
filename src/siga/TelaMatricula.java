package siga;

public class TelaMatricula {

    private final FachadaMatricula fachadaMatricula;

    public TelaMatricula(FachadaMatricula fachadaMatricula) {
        this.fachadaMatricula = fachadaMatricula;
    }

    public void aoClicarEmMatricular(String matricula, String cpf, String codigoTurma) {
        try {
            Matricula nova = fachadaMatricula.matricular(matricula, cpf, codigoTurma);
            System.out.println("   [tela] Matrícula realizada: " + nova);
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("   [tela] Erro: " + e.getMessage());
        }
    }
}
