package entidades;

public class Graduacao extends Rendimento {
  private Aluno aluno;
  private Curso curso;
  private double np1, np2, reposicao, exame;
  private boolean aprovado;

  public Graduacao(Aluno aluno, Curso curso, double np1, double np2, double reposicao, double exame) {
    super(aluno, curso, np1, np2, reposicao, exame);
    /*this.aluno = aluno;
    this.curso = curso;
    this.np1 = np1;
    this.np2 = np2;
    this.reposicao = reposicao;
    this.exame = exame;*/
  }

  /*@Override
  public Aluno getAluno() {
    return aluno;
  }

  @Override
  public void setAluno(Aluno aluno) {
    this.aluno = aluno;
  }

  @Override
  public Curso getCurso() {
    return curso;
  }

  @Override
  public void setCurso(Curso curso) {
    this.curso = curso;
  }

  public double getNp1() {
    return np1;
  }

  public void setNp1(double np1) {
    this.np1 = np1;
  }

  public double getNp2() {
    return np2;
  }

  public void setNp2(double np2) {
    this.np2 = np2;
  }

  public double getReposicao() {
    return reposicao;
  }

  public void setReposicao(double reposicao) {
    this.reposicao = reposicao;
  }

  public double getExame() {
    return exame;
  }

  public void setExame(double exame) {
    this.exame = exame;
  }

  @Override
  public boolean isAprovado() {
    return aprovado;
  }

  public void setAprovado(boolean aprovado) {
    this.aprovado = aprovado;
  }*/

  @Override
  public void calcularMedia(double notaNP1, double notaNP2, double notaSub, double notaExame, boolean graduacao) {
    double menorNota = Math.min(notaNP1, notaNP2);
    if (notaSub > menorNota) {
      menorNota = notaSub;
    }
    double mediaInicial = (notaNP1 + notaNP2 - menorNota) / 2.0;

    if (mediaInicial >= 7.0) {
      this.media = mediaInicial;
      this.aprovado = true;
    } else {
      double mediaFinal = (mediaInicial + notaExame) / 2.0;
      media = mediaFinal;
      if (mediaFinal >= 5.0) {
        this.aprovado = true;
      } else {
        this.aprovado = false;
      }
    }
  }

  /*@Override
  public String toString() {
    return this.aluno +"," +
            this.curso +"," +
            this.np1 +"," +
            this.np2 +"," +
            this.reposicao +"," +
            this.exame +"," +
            this.aprovado;
  }*/
}
