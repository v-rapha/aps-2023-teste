package entidades;

public class PosGraduacao implements Rendimento {
  private double np1, np2, reposicao, exame, media;
  private boolean aprovado;

  public PosGraduacao(double np1, double np2, double reposicao, double exame) {
    this.np1 = np1;
    this.np2 = np2;
    this.reposicao = reposicao;
    this.exame = exame;
  }
  @Override
  public double calculaMedia() {
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

    if (mediaInicial >= 5) {
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
  }
}
