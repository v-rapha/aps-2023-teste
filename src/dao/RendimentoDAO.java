package dao;

import dados.CursoDados;
import dados.Dados;
import dados.RendimentoDados;
import entidades.*;


import java.io.*;
import java.nio.charset.StandardCharsets;

public class RendimentoDAO {
  private String filePath;
  private CursoDados cursoDados;
  private CursoDAO cursoDAO;
  private RendimentoDados rendimentoDados;
  private Dados alunoDados;

  public RendimentoDAO(RendimentoDados graduacaoDados, CursoDados cursoDados, Dados alunoDados) {
    this.rendimentoDados = graduacaoDados;
   this.alunoDados = alunoDados;
   this.cursoDados = cursoDados;
  }

  public void loadRendimentos() {
	  String path="";
	  for(Curso c: cursoDados.getCursos()) {
		  path = "files/" + c.getNome() + "_" +
	              c.getNivel() + "_" +
	              c.getAno() + ".csv";
		  
		  try (InputStream is = new FileInputStream(path);
			         InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
			         BufferedReader br = new BufferedReader(isr)) {
			      String linha;
			      while((linha = br.readLine()) != null) {
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
			        
			      Rendimento rendimento;
			        
			        if (c.getNivel().equals(Nivel.GRADUACAO)) {
			            rendimento = new Graduacao(aluno, c, np1Parse, np2Parse, reposicaoParse, exameParse);
			            if(rendimentoDados.addRendimento(rendimento)) {
			              System.out.println("Adicionando graduacao " + rendimento);
			            } else {
			              System.out.println("Falha ao adicionar graduacao " + rendimento);
			            }
			          } else if (c.getNivel().equals(Nivel.POS_GRADUACAO)) {
			        	  rendimento = new PosGraduacao(aluno, c, np1Parse, np2Parse, reposicaoParse, exameParse);
			            if(rendimentoDados.addRendimento(rendimento)) {
			              System.out.println("Adicionando posGraduacao " + rendimento);
			            } else {
			              System.out.println("Falha ao adicionar posGraduacao " + rendimento);
			            }
			          }

			        
			      }
			    } catch (IOException e) {
			      throw new RuntimeException(e);
			    }
	  }
    
  }

  public void saveGraduacoes() {
    //Collection<Graduacao> cursos = graduacaoDados.getGraduacoes();
    System.out.println(rendimentoDados.getRendimentos());
    String path = "";
    for(Rendimento gs: rendimentoDados.getRendimentos()) {
      path = "files/" + gs.getCurso().getNome() + "_" +
              gs.getCurso().getNivel() + "_" +
              gs.getCurso().getAno() + ".csv";
      System.out.println(path);
      try (OutputStream os = new FileOutputStream(path, true);
           OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
           BufferedWriter bw = new BufferedWriter(osw)) {

        //pw.println(gs.getAluno().getId() + "," + gs.getNp1() + "," + gs.getNp2() + "," + gs.getReposicao() + "," + gs.getExame());
        String line = gs.getAluno().getId() + "," +
                      gs.getNp1() + "," +
                      gs.getNp2() + "," +
                      gs.getReposicao() + "," +
                      gs.getExame();
        bw.write(line);
        bw.newLine();

      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
