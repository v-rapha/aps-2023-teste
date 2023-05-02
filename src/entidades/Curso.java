package entidades;

public class Curso {
  private String nome;
  private Nivel nivel;
  private int ano;

  public Curso(String nome, Nivel nivel, int ano) {
    this.nome = nome;
    this.nivel = nivel;
    this.ano = ano;
  }

  public String getNome() {
    return this.nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public Nivel getNivel() {
    return this.nivel;
  }

  public void setNivel(Nivel nivel) {
    this.nivel = nivel;
  }

  public int getAno() {
    return this.ano;
  }

  public void setAno(int ano) {
    this.ano = ano;
  }

  @Override
  public String toString() {
    return this.nome + "," + this.nivel + "," + this.ano;
  }
}
