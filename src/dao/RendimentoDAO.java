package dao;

import dados.CursoDados;
import dados.AlunoDados;
import dados.RendimentoDados;
import entidades.*;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class RendimentoDAO {
  private CursoDados cursoDados;
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


          Aluno aluno = alunoDados.getAlunoById(idAluno);

          String notaNP1 = palavras[1];
          String notaNP2 = palavras[2];
          String notaReposicao = palavras[3];
          String notaExame = palavras[4];

          double np1Parse = Double.parseDouble(notaNP1);
          double np2Parse = Double.parseDouble(notaNP2);
          double reposicaoParse = Double.parseDouble(notaReposicao);
          double exameParse = Double.parseDouble(notaExame);

          //Rendimento rendimento;

          Rendimento rendimento1 = new Graduacao(aluno, c, np1Parse, np2Parse, reposicaoParse, exameParse);
          if (rendimentoDados.hasRendimento(idAluno, rendimento1.getCurso().getNome(), rendimento1.getCurso().getNivel())) {
            continue;// Pula para a próxima iteração do loop
          }
          rendimentoDados.addRendimento(rendimento1);
          Rendimento rendimento2 = new PosGraduacao(aluno, c, np1Parse, np2Parse, reposicaoParse, exameParse);
          if (rendimentoDados.hasRendimento(idAluno, rendimento2.getCurso().getNome(), rendimento2.getCurso().getNivel())) {
            continue;
          }
          rendimentoDados.addRendimento(rendimento2);

          /*if (c.getNivel().equals(Nivel.GRADUACAO)) {
           *//*if (rendimentoDados.addRendimento(rendimento)) {
              System.out.println("Adicionando graduacao " + rendimento);
            } else {
              System.out.println("Falha ao adicionar graduacao " + rendimento);
            }*//*
          } else if (c.getNivel().equals(Nivel.POS_GRADUACAO)) {
            *//*if (rendimentoDados.addRendimento(rendimento)) {
              System.out.println("Adicionando posGraduacao " + rendimento);
            } else {
              System.out.println("Falha ao adicionar posGraduacao " + rendimento);
            }*//*
          }*/


        }
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }

  /*public void saveRendimentos() {
    Map<String, StringBuilder> conteudoPorArquivo = new HashMap<>();

    for (Rendimento ro : rendimentoDados.getRendimentos()) {
      String path = "files/" + ro.getCurso().getNome() + "_" +
              ro.getCurso().getNivel() + "_" +
              ro.getCurso().getAno() + ".csv";

      String line = ro.getAluno().getId() + "," +
              ro.getNp1() + "," +
              ro.getNp2() + "," +
              ro.getReposicao() + "," +
              ro.getExame();

      if (!conteudoPorArquivo.containsKey(path)) {
        conteudoPorArquivo.put(path, new StringBuilder());
      }

      StringBuilder conteudo = conteudoPorArquivo.get(path);
      conteudo.append(line).append("\n");
    }

    for (Map.Entry<String, StringBuilder> entry : conteudoPorArquivo.entrySet()) {
      String path = entry.getKey();
      StringBuilder conteudo = entry.getValue();

      try (OutputStream os = new FileOutputStream(path, true);
           OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
           BufferedWriter bw = new BufferedWriter(osw)) {

        String conteudoExistente = conteudo.toString();

        if (conteudoExistente.isEmpty() || !conteudoExistente.equals(conteudo.toString())) {
          bw.write(conteudo.toString());
        }

      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }*/


  /*public void saveRendimentos() {
    //Dados únicos por arquivo
    Map<String, List<Rendimento>> rendimentosPorArquivo = new HashMap<>();
    //List<String> arquivosProcessados = new ArrayList<>();
    //String path = "";
    for (Rendimento ro : rendimentoDados.getRendimentos()) {
      String path = "files/" + ro.getCurso().getNome() + "_" +
              ro.getCurso().getNivel() + "_" +
              ro.getCurso().getAno() + ".csv";

      if (!rendimentosPorArquivo.containsKey(path)) {
        rendimentosPorArquivo.put(path, new ArrayList<>());
      }

      List<Rendimento> rendimentos = rendimentosPorArquivo.get(path);
      rendimentos.add(ro);
    }
    //System.out.println("MAP 158 rendimentosPorArquivo " + rendimentosPorArquivo);

      //truncateFile(path);
    for (Map.Entry<String, List<Rendimento>> entry: rendimentosPorArquivo.entrySet()) {
      String path = entry.getKey();
      List<Rendimento> rendimentos = entry.getValue();

      try (OutputStream os = new FileOutputStream(path, false);
           OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
           BufferedWriter bw = new BufferedWriter(osw)) {

        for (Rendimento ro: rendimentos) {
          String line = ro.getAluno().getId() + "," +
                  ro.getNp1() + "," +
                  ro.getNp2() + "," +
                  ro.getReposicao() + "," +
                  ro.getExame();
          bw.write(line);
          bw.newLine();
        }

      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }*/

  public void saveRendimentos() {
    Map<String, StringBuilder> conteudoPorArquivo = new HashMap<>();

    for (Rendimento ro : rendimentoDados.getRendimentos()) {
      String path = "files/" + ro.getCurso().getNome() + "_" +
              ro.getCurso().getNivel() + "_" +
              ro.getCurso().getAno() + ".csv";

      String line = ro.getAluno().getId() + "," +
              ro.getNp1() + "," +
              ro.getNp2() + "," +
              ro.getReposicao() + "," +
              ro.getExame();

      if (!conteudoPorArquivo.containsKey(path)) {
        conteudoPorArquivo.put(path, new StringBuilder());
      }

      StringBuilder conteudo = conteudoPorArquivo.get(path);
      conteudo.append(line).append("\n");
    }

    for (Map.Entry<String, StringBuilder> entry : conteudoPorArquivo.entrySet()) {
      String path = entry.getKey();
      StringBuilder conteudo = entry.getValue();

      try (BufferedReader reader = new BufferedReader(new FileReader(path));
           BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {

        Set<String> linhasExistentes = new HashSet<>();
        String linha;

        // Ler as linhas existentes no arquivo
        while ((linha = reader.readLine()) != null) {
          linhasExistentes.add(linha);
        }

        // Adicionar apenas as novas linhas ao arquivo
        for (String novaLinha : conteudo.toString().split("\n")) {
          if (!linhasExistentes.contains(novaLinha)) {
            writer.write(novaLinha);
            writer.newLine();
          }
        }

      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }

  public List<Rendimento> getRendimentos() {
    return rendimentos;
  }
}
