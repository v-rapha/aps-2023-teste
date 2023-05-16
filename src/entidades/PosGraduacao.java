package entidades;

public class PosGraduacao extends Rendimento {

  public PosGraduacao(Aluno aluno, Curso curso, double np1, double np2, double reposicao, double exame) {
    super(aluno, curso, np1, np2, reposicao, exame);
    calcularMedia(np1, np2, reposicao, exame);
  }

  @Override
  public void calcularMedia(double notaNP1, double notaNP2, double notaReposicao, double notaExame) {
    double menorNota = Math.min(notaNP1, notaNP2);
    double maiorNota = Math.max(notaNP1, notaNP2);
    if (notaReposicao > menorNota) {
      menorNota = notaReposicao;
    }
    double mediaInicial = (maiorNota + menorNota) / 2.0;

    if (mediaInicial >= 5.0) {
      setMedia(mediaInicial);
      setAprovado(true);
    } else {
      double mediaFinal = (mediaInicial + notaExame) / 2.0;
      if (mediaFinal >= 5.0) {
        setMedia(5.0);
        setAprovado(true);
      } else {
        setMedia(mediaFinal);
        setAprovado(false);
      }
    }
  }
}
