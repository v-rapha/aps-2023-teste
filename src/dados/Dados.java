package dados;

import entidades.Aluno;

import java.util.*;

public class Dados {
  private Map<String, Aluno> alunos = new TreeMap<>();

  /*public Map<String, Aluno> getAlunosById() {
    return this.alunos;
  }*/

  public boolean addAluno(Aluno a) {
    if(alunos.containsKey(a.getId())) {
      return false;
    }
    this.alunos.put(a.getId(), a);

    return true;
  }

  public Collection<Aluno> getAlunos() {
    return this.alunos.values();
  }

  public Aluno getAlunoById(String id) {
    return alunos.get(id);
  }

  public List<Aluno> getAlunosByName(String keyName) {
    List<Aluno> alunosByName = new ArrayList<>();

    for(Aluno a: alunos.values()) {
      if(a.getNome().toLowerCase().contains(keyName.toLowerCase())) {
        alunosByName.add(a);
      }
    }

    return alunosByName;
  }
}
