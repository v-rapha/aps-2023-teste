package view;

import dados.CursoDados;
import dados.Dados;
import dao.AlunoDAO;
import dao.CursoDAO;
import entidades.Aluno;
import entidades.Curso;
import entidades.Nivel;

import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class View {
  AlunoDAO alunoDAO;
  CursoDAO cursoDAO;
  Dados dados;
  CursoDados cursoDados;

  public View() {
    this.dados = new Dados();
    this.cursoDados = new CursoDados();
    this.alunoDAO = new AlunoDAO("files/alunos.csv", this.dados);
    this.cursoDAO = new CursoDAO("files/cursos.csv", this.cursoDados);
  }

  public void init(){
    alunoDAO.loadAlunos();
    cursoDAO.loadCursos();
    controller();
  }

  private void controller() {
    int opcao = 0;
    do {
      opcao = getOpcao();
      switch (opcao) {
        case 1: adicionaAluno(); break;
        case 2: listaTodosAlunos(); break;
        case 3: listaAlunosByNome(); break;
        case 4: encontraAlunoById(); break;
        case 5: adicionaCurso(); break;
        case 6: listaTodosCursos(); break;
        case 7: listaCursosByAno(); break;
        case 0: sair(); break;
      }
    } while(opcao != 0);
  }

  public void adicionaCurso() {
    Curso curso = entraCurso();
    if(curso == null) {
      return;
    }
    if(cursoDados.addCurso(curso)) {
      System.out.println("Adicionando Curso " + curso);
    } else {
      System.out.println("Falha ao adicionar curso " + curso);
    }
  }

  public void listaTodosCursos() {
    System.out.println("Listando todos os cursos");
    for(Curso c: cursoDados.getCursos()) {
      System.out.println(c);
    }
  }

  public void listaCursosByAno() {
    int ano = entraAnoCurso();
    listaCursosByAno(ano);
  }

  public void listaCursosByAno(int keyAno) {
    System.out.println("Listando todos os cursos contém \"" + keyAno + "\"");
    Collection<Curso> cursos = cursoDados.getCursosByAno(keyAno);
    if(cursos.size() == 0) {
      System.out.println("Nenhum curso em que ano contém " + keyAno);
    }
    System.out.println(cursos.size() + " cursos encontrados");
    for(Curso c: cursos) {
      System.out.println(c);
    }
    System.out.println();
  }

  public Curso entraCurso() {
    String nome = entraNomeCurso();
    Nivel nivel = entraNivelCurso();
    int ano = entraAnoCurso();

    Curso temCurso = cursoDados.getCursoByProperties(nome, nivel, ano);
    if(temCurso != null) {
      System.out.println("Já temos um curso com esses dados:");
      System.out.println(temCurso);
      return null;
    }

    return new Curso(nome, nivel, ano);
  }

  private String entraNomeCurso() {
    System.out.println("Entre com o nome do Curso");
    Scanner in = new Scanner(System.in);
    String nome = in.nextLine();
    return nome.trim();
  }

  private Nivel entraNivelCurso() {
    System.out.println("Entre com o nível do curso (GRADUACAO ou POS_GRADUACAO)");
    Scanner in = new Scanner(System.in);
    String nivelIn = in.nextLine();
    Nivel nivel = Nivel.valueOf(nivelIn.toUpperCase().trim());
    return nivel;
  }

  private int entraAnoCurso() {
    System.out.println("Entre com o ano do curso");
    Scanner in = new Scanner(System.in);
    int ano = in.nextInt();
    //int ano = Integer.parseInt(String.valueOf(in));
    return ano;
  }

  private int getOpcao() {
    System.out.println();
    System.out.println("-----------------------");
    System.out.println("Escolha uma opção: ");
    System.out.println("1 - Para adicionar Aluno");
    System.out.println("2 - Listar todos alunos");
    System.out.println("3 - listar todos alunos por nome");
    System.out.println("4 - encontrar aluno pelo id");
    System.out.println("5 - Para adicionar Curso");
    System.out.println("6 - Listar todos cursos");
    System.out.println("7 - encontrar curso pelo ano");
    System.out.println("0 - para sair do programa");
    System.out.println("-----------------------");

    Scanner in = new Scanner(System.in);
    String linha = in.nextLine();

    try {
      return Integer.parseInt(linha);
    } catch (NumberFormatException e) {
      System.out.println("O valor entrado: " + linha + " não é válido");
      System.out.println("A opção deve ser um número inteiro\n");
      return getOpcao();
    }
  }

  public void sair() {
    System.out.println("saindo do programa");
    System.out.println("salvando alunos");
    alunoDAO.saveAlunos();
    cursoDAO.saveCursos();
  }

  public void adicionaAluno() {
    Aluno aluno = entraAluno();
    if(aluno == null) {
      return;
    }
    if(dados.addAluno(aluno)) {
      System.out.println("Adicionando Aluno " + aluno);
    } else {
      System.out.println("Falha ao adicionar Aluno" + aluno);
    }
  }

  public void listaTodosAlunos() {
    System.out.println("Listando todos os alunos");
    for(Aluno a: dados.getAlunos()) {
      System.out.println(a);
    }
  }

  public void listaAlunosByNome() {
    String nome = entraNome();
    listaAlunosByNome(nome);
  }

  public void listaAlunosByNome(String keyNome) {
    System.out.println("Listando todos os alunos contém \" " + keyNome + "\"");
    Collection<Aluno> alunos = dados.getAlunosByName(keyNome);
    if(alunos.size() == 0) {
      System.out.println("Nenhum aluno em que nome contém " + keyNome);
    }
    System.out.println(" " + alunos.size() + " alunos encontrados");
    for(Aluno a: alunos) {
      System.out.println(a);
    }
    System.out.println();
  }

  public void encontraAlunoById() {
    String id = entraId();
    encontraAlunoById(id);
  }

  public void encontraAlunoById(String keyId) {
    System.out.println("Procurando aluno com id \"" + keyId + "\"");
    Aluno a = dados.getAlunoById(keyId);
    if(a == null) {
      System.out.println("Aluno não encontrado");
    } else {
      System.out.println("Aluno encontrado:");
      System.out.println(a);
    }
  }

  public Aluno entraAluno() {
    String id = entraId();

    Aluno temAluno = dados.getAlunoById(id);
    if(temAluno != null) {
      System.out.println("Já temos um aluno com esse Id:");
      System.out.println(temAluno);
      return null;
    }

    String nome = entraNome();
    return new Aluno(id, nome);
  }

  public String entraId() {
    System.out.println("Entra com o Id do Aluno");
    Scanner in = new Scanner(System.in);

    String id = in.nextLine();

    return id.trim();
  }

  public String entraNome() {
    System.out.println("Entre com o nome do Aluno");
    Scanner in = new Scanner(System.in);
    return in.nextLine().trim();
  }
}
