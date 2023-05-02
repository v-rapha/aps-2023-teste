package entidades;

public class Aluno {
  private String id;
  private String nome;

  public Aluno(String id, String nome) {
    this.id = id;
    this.nome = nome;
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getNome() {
    return this.nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  @Override
  public String toString() {
    return this.id + "," + this.nome;
  }
}
