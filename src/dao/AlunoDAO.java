package dao;

import dados.AlunoDados;
import entidades.Aluno;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class AlunoDAO {
  private String filePath;
  private AlunoDados alunoDados;

  public AlunoDAO(String filePath, AlunoDados alunoDados) {
    this.filePath = filePath;
    this.alunoDados = alunoDados;
  }

  public void loadAlunos() {
    try (InputStream is = new FileInputStream(filePath);
         InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
         BufferedReader br = new BufferedReader(isr)) {
      String linha;
      while ((linha = br.readLine()) != null) {
        String[] palavras = linha.split(",");

        String id = palavras[0];
        String nome = palavras[1];

        Aluno aluno = new Aluno(id, nome);
        alunoDados.addAluno(aluno);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void saveAlunos() {
    try (OutputStream os = new FileOutputStream(filePath);
         OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
         PrintWriter pw = new PrintWriter(osw, true)) {
      for (Aluno a : alunoDados.getAlunos()) {
        pw.println(a.getId() + "," + a.getNome());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
