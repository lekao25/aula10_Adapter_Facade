package siga;

public class CalculadoraDesconto {

    public double calcular(Aluno aluno) {
        return aluno.isBolsista() ? 0.50 : 0.0;
    }
}
