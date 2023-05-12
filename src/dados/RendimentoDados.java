package dados;

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

  public boolean hasRendimento(String idAluno) {
    for (Rendimento rendimento : this.rendimentos) {
      if (rendimento.getAluno().getId().equals(idAluno)) {
        return true; // Rendimento encontrado
      }
    }
    return false; // Rendimento não encontrado
  }
}
