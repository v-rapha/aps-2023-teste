package entidades;

import java.util.ArrayList;
import java.util.List;

public abstract class Rendimento {
  private Aluno aluno;
  private Curso curso;
  private double nota, np1, np2, reposicao, exame;
  private boolean aprovado;

  protected double media;

  public Rendimento() {}

  public Rendimento(Aluno aluno, Curso curso, double np1, double np2, double reposicao, double exame) {
    this.aluno = aluno;
    this.curso = curso;
    this.np1 = np1;
    this.np2 = np2;
    this.reposicao = reposicao;
    this.exame = exame;
  }

  public Rendimento(Aluno aluno, Curso curso, double nota, boolean aprovado) {
    this.aluno = aluno;
    this.curso = curso;
    this.nota = nota;
    this.aprovado = aprovado;
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

  public double getNota() {
    return nota;
  }

  public void setNota(double nota) {
    this.nota = nota;
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

  public abstract void calcularMedia(
          double notaNP1, double notaNP2, double notaSub, double notaExame, boolean graduacao);

  @Override
  public String toString() {
    return this.aluno +"," +
            this.curso +"," +
            this.np1 +"," +
            this.np2 +"," +
            this.reposicao +"," +
            this.exame +"," +
            this.aprovado;
  }

  /*public double calculaMedia() {
    double mediaI = getMediaNivel();
    //double maiorNota = 0;
    double mediaInicial = 0;
    double mediaFinal = 0;

    if (this.reposicao > this.np1) {
      //maiorNota = reposicao;
      mediaInicial = (reposicao + np2) / 2;
    } else if (this.reposicao > this.np2) {
      //maiorNota = reposicao;
      mediaInicial = (np1 + reposicao) / 2;
    }

    if (mediaInicial >= mediaI) {
      this.media = mediaInicial;
      this.aprovado = true;
    } else {
      mediaFinal = (mediaInicial + this.exame) / 2;
      if (mediaFinal >= 5) {
        this.media = mediaFinal;
        this.aprovado = true;
      } else {
        this.media = mediaFinal;
        this.aprovado = false;
      }
    }
    return 0;
  }*/
}
