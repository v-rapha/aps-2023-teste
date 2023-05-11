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
}
