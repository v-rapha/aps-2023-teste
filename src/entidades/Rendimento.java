package entidades;

public abstract class Rendimento {
  private Aluno aluno;
  private Curso curso;
  private double np1, np2, reposicao, exame;
  private boolean aprovado;

  protected double media;

  public Rendimento(Aluno aluno, Curso curso, double np1, double np2, double reposicao, double exame) {
    this.aluno = aluno;
    this.curso = curso;
    this.np1 = np1;
    this.np2 = np2;
    this.reposicao = reposicao;
    this.exame = exame;
  }

  public Aluno getAluno() {
    return aluno;
  }

  public void setAluno(Aluno aluno) {
    this.aluno = aluno;
  }

  public Curso getCurso() {
    return curso;
  }

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

  public boolean isAprovado() {
    return aprovado;
  }

  public void setAprovado(boolean aprovado) {
    this.aprovado = aprovado;
  }

  public double getMedia() {
    return media;
  }

  public void setMedia(double media) {
    this.media = media;
  }

  public abstract void calcularMedia(
          double notaNP1, double notaNP2, double notaSub, double notaExame);

  @Override
  public String toString() {
    return this.aluno + "," +
            this.curso + "," +
            this.np1 + "," +
            this.np2 + "," +
            this.reposicao + "," +
            this.exame + "," +
            this.media + "," +
            (this.aprovado ? "Aprovado" : "Reprovado");
  }
}
