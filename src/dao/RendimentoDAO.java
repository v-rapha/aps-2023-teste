package dao;

import dados.CursoDados;
import dados.RendimentoDados;
import entidades.Rendimento;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class RendimentoDAO {
  private String filePath;
  private CursoDados cursoDados;
  private CursoDAO cursoDAO;
  private RendimentoDados rendimentoDados;

  public RendimentoDAO(RendimentoDados graduacaoDados) {
    this.rendimentoDados = graduacaoDados;
  }

  public void loadRendimentos() {
    try (InputStream is = new FileInputStream(filePath);
         InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
         BufferedReader br = new BufferedReader(isr)) {
      String linha;
      while((linha = br.readLine()) != null) {
        String[] palavras = linha.split(",");

        String idAluno = palavras[0];
        String notaNP1 = palavras[1];
        String notaNP2 = palavras[2];
        String notaReposicao = palavras[3];
        String notaExame = palavras[4];

        double np1Parse = Double.parseDouble(notaNP1);
        double np2Parse = Double.parseDouble(notaNP2);
        double reposicaoParse = Double.parseDouble(notaReposicao);
        double exameParse = Double.parseDouble(notaExame);

        //Rendimento rendimento = new Rendimento(np1Parse, np2Parse, reposicaoParse, exameParse);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public void saveGraduacoes() {
    //Collection<Graduacao> cursos = graduacaoDados.getGraduacoes();
    System.out.println(rendimentoDados.getRendimentos());
    String path = "";
    for(Rendimento gs: rendimentoDados.getRendimentos()) {
      path = gs.getCurso().getNome().toUpperCase() + "_" +
              gs.getCurso().getNivel() + "_" +
              gs.getCurso().getAno();
      System.out.println(path);
      try (OutputStream os = new FileOutputStream("files/" + path + ".csv");
           OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
           PrintWriter pw = new PrintWriter(osw, true)) {

        pw.println(gs.getAluno().getId() + "," + gs.getNp1() + "," + gs.getNp2() + "," + gs.getReposicao() + "," + gs.getExame());

      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
