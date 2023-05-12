package entidades;

public class Graduacao extends Rendimento {
  private Aluno aluno;
  private Curso curso;
  private double np1, np2, reposicao, exame;
  private boolean aprovado;

  public Graduacao(Aluno aluno, Curso curso, double np1, double np2, double reposicao, double exame) {
    super(aluno, curso, np1, np2, reposicao, exame);
    /*//this.aluno = aluno;
    //this.curso = curso;
    this.np1 = np1;
    this.np2 = np2;
    this.reposicao = reposicao;
    this.exame = exame;*/
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

    if (mediaInicial >= 7.0) {
      //this.media = mediaInicial;
      setMedia(mediaInicial);
      //this.aprovado = true;
      setAprovado(true);
    } else {
      double mediaFinal = (mediaInicial + notaExame) / 2.0;
      //media = mediaFinal;
      if (mediaFinal >= 5.0) {
        setMedia(mediaFinal);
        //this.aprovado = true;
        setAprovado(true);
      } else {
        //this.aprovado = false;
        setMedia(mediaFinal);
        setAprovado(false);
      }
    }
  }
}
