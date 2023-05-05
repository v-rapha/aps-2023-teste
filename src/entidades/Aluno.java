package entidades;

import java.util.ArrayList;
import java.util.List;

public class Aluno {
  private String id;
  private String nome;
  private List<Rendimento> rendimentos;

  public Aluno(String id, String nome) {
    this.id = id;
    this.nome = nome;
    this.rendimentos = new ArrayList<>();
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
