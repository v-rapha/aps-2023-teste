package dao;

import dados.Dados;
import entidades.Aluno;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class AlunoDAO {
  private String filePath;
  private Dados dados;

  public AlunoDAO(String filePath, Dados dados) {
    this.filePath = filePath;
    this.dados = dados;
  }

  public void loadAlunos() {
    try (InputStream is = new FileInputStream(filePath);
         InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
         BufferedReader br = new BufferedReader(isr)) {
      String linha;
      while((linha = br.readLine()) != null) {
        String[] palavras = linha.split(",");

        String id = palavras[0];
        String nome = palavras[1];

        Aluno aluno = new Aluno(id, nome);
        dados.addAluno(aluno);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void saveAlunos() {
    try (OutputStream os = new FileOutputStream(filePath);
         OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
         PrintWriter pw = new PrintWriter(osw, true)) {
      for(Aluno a: dados.getAlunos()) {
        pw.println(a.getId() + "," + a.getNome());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
