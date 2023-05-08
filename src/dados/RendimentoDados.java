package dados;

import entidades.Graduacao;
import entidades.Rendimento;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RendimentoDados {
  private List<Rendimento> graduacoes = new ArrayList<>();

  public boolean addRendimento(Rendimento g) {
    if (g == null) {
      return false;
    }
    this.graduacoes.add(g);
    return true;
  }

  public Collection<Rendimento> getRendimentos() {
    return this.graduacoes;
  }
}
