package dados;

import dao.RendimentoDAO;
import entidades.Rendimento;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RendimentoDados {
  private List<Rendimento> rendimentos = new ArrayList<>();

  public boolean addRendimento(Rendimento r) {
    if (r == null) {
      return false;
    }
    this.rendimentos.add(r);
    return true;
  }

  public Collection<Rendimento> getRendimentos() {
    return this.rendimentos;
  }

  /*public List<Rendimento> getRentimentosAluno(String idAluno) {
    List<Rendimento> rendimentosAluno = new ArrayList<>();

    for (Rendimento r: this.rendimentos) {
      System.out.println("RendimentoDados.java " + rendimentos);
      if (r.getAluno().getId().equals(idAluno)) {
        rendimentosAluno.add(r);
      }
    }

    return rendimentosAluno;
  }*/

  public boolean hasRendimento(String idAluno) {
    for (Rendimento rendimento : this.rendimentos) {
      if (rendimento.getAluno().getId().equals(idAluno)) {
        return true; // Rendimento encontrado
      }
    }
    return false; // Rendimento não encontrado
  }
}
