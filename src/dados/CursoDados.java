package dados;

import entidades.Curso;
import entidades.Nivel;

import java.util.*;

public class CursoDados {
  private List<Curso> cursos = new ArrayList<>();

  /*public Map<String, Curso> getCursosById() {
   return this.cursos;
  }*/

  public boolean addCurso(Curso c) {
    if (cursos.contains(c.getNome()) && cursos.contains(c.getNivel()) && cursos.contains(c.getAno())) {
      return false;
    }
    this.cursos.add(c);

    return true;
  }

  public Collection<Curso> getCursos() {
    return this.cursos;
  }

  public Curso getCursoByProperties(String nome, Nivel nivel, int ano) {
    for (Curso c : cursos) {
      if (c.getNome().equals(nome) && c.getNivel().equals(nivel) && c.getAno() == ano) {
        return c;
      }
    }
    return null;
  }

  public List<Curso> getCursosByAno(int keyAno) {
    List<Curso> cursosByAno = new ArrayList<>();

    for (Curso c : cursos) {
      if (c.getAno() == keyAno) {
        cursosByAno.add(c);
      }
    }

    return cursosByAno;
  }
}
