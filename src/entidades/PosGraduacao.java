package entidades;

public class PosGraduacao extends Rendimento {
  private Aluno aluno;
  private Curso curso;
  private double np1, np2, reposicao, exame;
  private boolean aprovado;

  public PosGraduacao(Aluno aluno, Curso curso, double np1, double np2, double reposicao, double exame) {
    super(aluno, curso, np1, np2, reposicao, exame);
    //this.aluno = aluno;
    //this.curso = curso;
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

    if (mediaInicial >= 5.0) {
      this.media = mediaInicial;
      this.aprovado = true;
    } else {
      double mediaFinal = (mediaInicial + notaExame) / 2.0;
      if (mediaFinal >= 5.0) {
        this.media = 5.0;
        this.aprovado = true;
      } else {
        this.media = mediaFinal;
        this.aprovado = false;
      }
    }
  }
}
