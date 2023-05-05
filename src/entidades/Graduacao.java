package entidades;

public class Graduacao extends Rendimento {
  private Aluno aluno;
  private Curso curso;
  private double np1, np2, reposicao, exame;

  public Graduacao(Aluno aluno, Curso curso, double np1, double np2, double reposicao, double exame) {
    this.aluno = aluno;
    this.curso = curso;
    this.np1 = np1;
    this.np2 = np2;
    this.reposicao = reposicao;
    this.exame = exame;
  }
  @Override
  public void calcularMedia(double notaNP1, double notaNP2, double notaSub, double notaExame, boolean graduacao) {
    double menorNota = Math.min(notaNP1, notaNP2);
    if (notaSub > menorNota) {
      menorNota = notaSub;
    }
    double mediaInicial = (notaNP1 + notaNP2 - menorNota) / 2.0;

    if (mediaInicial >= 7.0) {
      media = mediaInicial;
      //aprovado = true;
    } else {
      double mediaFinal = (mediaInicial + notaExame) / 2.0;
      media = mediaFinal;
      if (mediaFinal >= 5.0) {
        //aprovado = true;
      } else {
        //aprovado = false;
      }
    }
  }
}
