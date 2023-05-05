package entidades;

import java.util.ArrayList;
import java.util.List;

public abstract class Rendimento {
  private Aluno aluno;
  private Curso curso;
  private double nota;
  private boolean aprovado;

  protected double media;

  public Rendimento() {}

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

  public boolean isAprovado() {
    return aprovado;
  }

  public double getMedia() {
    return media;
  }

  public abstract void calcularMedia(
          double notaNP1, double notaNP2, double notaSub, double notaExame, boolean graduacao);

  @Override
  public String toString() {
    return "Rendimento{" +
            "aluno=" + aluno +
            ", curso=" + curso +
            ", nota=" + nota +
            ", aprovado=" + aprovado +
            '}';
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
