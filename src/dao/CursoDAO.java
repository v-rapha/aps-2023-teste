package dao;

import dados.CursoDados;
import entidades.Curso;
import entidades.Nivel;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class CursoDAO {
  private String filePath;
  private CursoDados cursoDados;

  public CursoDAO(String filePath, CursoDados cursoDados) {
    this.filePath = filePath;
    this.cursoDados = cursoDados;
  }

  public void loadCursos() {
    try (InputStream is = new FileInputStream(filePath);
         InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
         BufferedReader br = new BufferedReader(isr)) {
      String linha;
      while((linha = br.readLine()) != null) {
        String[] palavras = linha.split(",");

        String nome = palavras[0];
        String nivel = palavras[1];
        String ano = palavras[2];

        Nivel nivelParse = Nivel.valueOf(nivel);
        int anoParse = Integer.parseInt(ano);

        Curso curso = new Curso(nome, nivelParse, anoParse);
        cursoDados.addCurso(curso);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public void saveCursos() {
    try (OutputStream os = new FileOutputStream(filePath);
        OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
        PrintWriter pw = new PrintWriter(osw, true)) {
      for(Curso c: cursoDados.getCursos()) {
        pw.println(c.getNome() + "," + c.getNivel() + "," + c.getAno());
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
