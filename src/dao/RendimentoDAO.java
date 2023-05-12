package dao;

import dados.CursoDados;
import dados.AlunoDados;
import dados.RendimentoDados;
import entidades.*;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class RendimentoDAO {
  private String filePath;
  private CursoDados cursoDados;
  private CursoDAO cursoDAO;
  private RendimentoDados rendimentoDados;
  private AlunoDados alunoDados;
  private List<Rendimento> rendimentos;


  public RendimentoDAO(RendimentoDados rendimentoDados, CursoDados cursoDados, AlunoDados alunoDados) {
    this.rendimentoDados = rendimentoDados;
    this.alunoDados = alunoDados;
    this.cursoDados = cursoDados;
    this.rendimentos = new ArrayList<>();
  }

  public void loadRendimentos() {
    //String path = "";
    for (Curso c : cursoDados.getCursos()) {
      String path = "files/" + c.getNome() + "_" +
              c.getNivel() + "_" +
              c.getAno() + ".csv";

      try (InputStream is = new FileInputStream(path);
           InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
           BufferedReader br = new BufferedReader(isr)) {

        String linha;
        while ((linha = br.readLine()) != null) {
          String[] palavras = linha.split(",");

          String idAluno = palavras[0];

          if (rendimentoDados.hasRendimento(idAluno)) {
            continue; // Pula para a próxima iteração do loop
          }

          Aluno aluno = alunoDados.getAlunoById(idAluno);

          String notaNP1 = palavras[1];
          String notaNP2 = palavras[2];
          String notaReposicao = palavras[3];
          String notaExame = palavras[4];

          double np1Parse = Double.parseDouble(notaNP1);
          double np2Parse = Double.parseDouble(notaNP2);
          double reposicaoParse = Double.parseDouble(notaReposicao);
          double exameParse = Double.parseDouble(notaExame);

          Rendimento rendimento;

          if (c.getNivel().equals(Nivel.GRADUACAO)) {
            rendimento = new Graduacao(aluno, c, np1Parse, np2Parse, reposicaoParse, exameParse);
            rendimentos.add(rendimento);
            /*if (rendimentoDados.addRendimento(rendimento)) {
              System.out.println("Adicionando graduacao " + rendimento);
            } else {
              System.out.println("Falha ao adicionar graduacao " + rendimento);
            }*/
          } else if (c.getNivel().equals(Nivel.POS_GRADUACAO)) {
            rendimento = new PosGraduacao(aluno, c, np1Parse, np2Parse, reposicaoParse, exameParse);
            rendimentos.add(rendimento);
            /*if (rendimentoDados.addRendimento(rendimento)) {
              System.out.println("Adicionando posGraduacao " + rendimento);
            } else {
              System.out.println("Falha ao adicionar posGraduacao " + rendimento);
            }*/
          }


        }
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }

    saveRendimentos();
  }

  public void saveRendimentos() {
    //List<String> arquivosProcessados = new ArrayList<>();
    //String path = "";
    for (Rendimento ro : rendimentoDados.getRendimentos()) {
      String path = "files/" + ro.getCurso().getNome() + "_" +
              ro.getCurso().getNivel() + "_" +
              ro.getCurso().getAno() + ".csv";

      try (OutputStream os = new FileOutputStream(path, true);
           OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
           BufferedWriter bw = new BufferedWriter(osw)) {

        String line = ro.getAluno().getId() + "," +
                ro.getNp1() + "," +
                ro.getNp2() + "," +
                ro.getReposicao() + "," +
                ro.getExame();
        bw.write(line);
        bw.newLine();

      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }

  public void clearRendimentos() {
    for (Rendimento rendimento : rendimentoDados.getRendimentos()) {
      String path = "files/" + rendimento.getCurso().getNome() + "_" +
              rendimento.getCurso().getNivel() + "_" +
              rendimento.getCurso().getAno() + ".csv";
      truncateFile(path);
    }
  }

  private void truncateFile(String filePath) {
    try (RandomAccessFile raf = new RandomAccessFile(filePath, "rw")) {
      raf.setLength(0);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public List<Rendimento> getRendimentos() {
    return rendimentos;
  }
}
