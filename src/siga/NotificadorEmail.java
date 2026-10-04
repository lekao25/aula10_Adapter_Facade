package siga;

public class NotificadorEmail {

    public void enviarConfirmacao(Aluno aluno, Matricula matricula) {
        System.out.println("   [e-mail] Confirmação enviada a " + aluno.getNome()
                + ": " + matricula);
    }
}
