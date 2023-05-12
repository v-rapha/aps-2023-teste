package view;

import dados.CursoDados;
import dados.AlunoDados;
import dados.RendimentoDados;
import dao.AlunoDAO;
import dao.CursoDAO;
import dao.RendimentoDAO;
import entidades.*;

import java.util.*;
import java.util.stream.Stream;

public class View {
  private AlunoDAO alunoDAO;
  private CursoDAO cursoDAO;
  private AlunoDados alunoDados;
  private CursoDados cursoDados;
  private RendimentoDados rendimentoDados;
  private Collection<Aluno> alunos;
  private Collection<Curso> cursos;
  private RendimentoDAO rendimentoDAO;

  public View() {
    this.alunoDados = new AlunoDados();
    this.cursoDados = new CursoDados();
    this.rendimentoDados = new RendimentoDados();
    this.alunoDAO = new AlunoDAO("files/alunos.csv", this.alunoDados);
    this.cursoDAO = new CursoDAO("files/cursos.csv", this.cursoDados);
    this.rendimentoDAO = new RendimentoDAO(this.rendimentoDados, this.cursoDados, this.alunoDados);
  }

  public void init() {
    alunoDAO.loadAlunos();
    cursoDAO.loadCursos();
    alunos = alunoDados.getAlunos();
    cursos = cursoDados.getCursos();
    rendimentoDAO.clearRendimentos();
    rendimentoDAO.loadRendimentos();
    controller();
  }

  private void controller() {
    int opcao = 0;
    do {
      opcao = getOpcao();
      switch (opcao) {
        case 1:
          adicionaAluno();
          break;
        case 2:
          listaTodosAlunos();
          break;
        case 3:
          listaAlunosByNome();
          break;
        case 4:
          encontraAlunoById();
          break;
        case 5:
          adicionaCurso();
          break;
        case 6:
          listaTodosCursos();
          break;
        case 7:
          listaCursosByAno();
          break;
        case 8:
          adicionaRendimento();
          break;
        case 9:
          listaTodosRendimentos();
          break;
        case 0:
          sair();
          break;
      }
    } while (opcao != 0);
  }

  private int getOpcao() {
    System.out.println();
    System.out.println("------------ Universidade Amazônia ------------");
    System.out.println("------------------ BEM-VINDO ------------------");
    System.out.println("Escolha uma opção: ");
    System.out.println("1 - Para adicionar um aluno");
    System.out.println("2 - Para listar todos os alunos");
    System.out.println("3 - Para listar todos alunos por nome");
    System.out.println("4 - Para encontrar um aluno pelo id (RA)");
    System.out.println("5 - Para Para adicionar um curso");
    System.out.println("6 - Para listar todos os cursos");
    System.out.println("7 - Para encontrar curso(s) pelo ano");
    System.out.println("8 - Para adicionar rendimentos");
    System.out.println("9 - Para listar todos os rendimentos");
    System.out.println("0 - para sair do programa");
    System.out.println("-----------------------------------------------");

    Scanner in = new Scanner(System.in);
    String linha = in.nextLine();

    try {
      return Integer.parseInt(linha);
    } catch (NumberFormatException e) {
      System.out.println("O valor " + linha + " não é válido");
      System.out.println("A opção deve ser um número inteiro\n");
      return getOpcao();
    }
  }

  public void sair() {
    System.out.println("saindo do programa");
    System.out.println("salvando alunos, cursos e rendimentos");
    alunoDAO.saveAlunos();
    cursoDAO.saveCursos();
    rendimentoDAO.saveRendimentos();
  }

  public void listaTodosRendimentos() {
    List<Rendimento> concatenated_list = new ArrayList<>();
    concatenated_list.addAll(rendimentoDAO.getRendimentos());
    concatenated_list.addAll(rendimentoDados.getRendimentos());
    System.out.println("-----------------------------");
    System.out.println("Listando todos os Rendimentos");
    System.out.println("-----------------------------");
    for (Rendimento r : concatenated_list) {
      System.out.println(r);
    }
  }

  public void adicionaRendimento() {
    System.out.println("Alunos cadastrados");
    System.out.println("------------------");
    for (Aluno a : alunoDados.getAlunos()) {
      System.out.println(a);
    }
    System.out.println("------------------");

    Scanner in = new Scanner(System.in);
    String idAluno = entraIdAluno();

    Aluno aluno = alunoDados.getAlunoById(idAluno);
    if (aluno == null) {
      System.out.println("Aluno não encontrado");
      return;
    }

    System.out.println("Aluno selecionado: " + aluno.getNome() + "\n");

    System.out.println("Cursos cadastrados. Selecione o curso");
    System.out.println("------------------");
    for (Curso c : cursoDados.getCursos()) {
      System.out.println(c);
    }

    String nome = entraNomeCurso();
    Nivel nivel = entraNivelCurso();
    int ano = entraAnoCurso();

    Curso curso = cursoDados.getCursoByProperties(nome, nivel, ano);
    if (curso == null) {
      System.out.println("Curso não encontrado");
      return;
    }
    if (!(curso.getNivel().equals(Nivel.GRADUACAO) || curso.getNivel().equals(Nivel.POS_GRADUACAO))) {
      System.out.println("Nível inválido");
      return;
    }

    System.out.println("Entre com a Np1");
    double np1 = in.nextDouble();
    System.out.println("Entre com a Np2");
    double np2 = in.nextDouble();
    System.out.println("Entre com a reposição");
    double repo = in.nextDouble();
    System.out.println("Entre com o exame");
    double exame = in.nextDouble();

    if (curso.getNivel().equals(Nivel.GRADUACAO)) {
      Graduacao graduacao = new Graduacao(aluno, curso, np1, np2, repo, exame);
      if (rendimentoDados.addRendimento(graduacao)) {
        System.out.println("Adicionando rendimento para graduacao: " + graduacao);
      } else {
        System.out.println("Falha ao adicionar rendimento: " + graduacao);
      }
    } else if (curso.getNivel().equals(Nivel.POS_GRADUACAO)) {
      PosGraduacao posGraduacao = new PosGraduacao(aluno, curso, np1, np2, repo, exame);
      if (rendimentoDados.addRendimento(posGraduacao)) {
        System.out.println("Adicionando rendimento para pos graduacao: " + posGraduacao);
      } else {
        System.out.println("Falha ao adicionar rendimento: " + posGraduacao);
      }
    }
  }

  public void adicionaCurso() {
    Curso curso = entraCurso();
    if (curso == null) {
      return;
    }
    if (cursoDados.addCurso(curso)) {
      System.out.println("Adicionando curso: " + curso);
    } else {
      System.out.println("Falha ao adicionar curso: " + curso);
    }
  }

  public void listaTodosCursos() {
    System.out.println("Listando todos os cursos");
    for (Curso c : cursoDados.getCursos()) {
      System.out.println(c);
    }
  }

  public void listaCursosByAno() {
    int ano = entraAnoCurso();
    listaCursosByAno(ano);
  }

  public void listaCursosByAno(int keyAno) {
    System.out.println("Listando todos os cursos de \"" + keyAno + "\"");
    Collection<Curso> cursos = cursoDados.getCursosByAno(keyAno);
    if (cursos.size() == 0) {
      System.out.println("Nenhum curso do ano de " + keyAno);
    }
    System.out.println(cursos.size() + " cursos encontrados");
    for (Curso c : cursos) {
      System.out.println(c);
    }
    System.out.println();
  }

  public Curso entraCurso() {
    String nome = entraNomeCurso();
    Nivel nivel = entraNivelCurso();
    int ano = entraAnoCurso();

    Curso temCurso = cursoDados.getCursoByProperties(nome, nivel, ano);
    if (temCurso != null) {
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
    return nome.trim().toUpperCase();
  }

  private Nivel entraNivelCurso() {
    Nivel nivel = null;
    boolean entradaValida = false;

    do {
      try {
        System.out.println("Entre com o nível do curso (GRADUACAO ou POS_GRADUACAO)");
        Scanner in = new Scanner(System.in);
        String nivelIn = in.nextLine().toUpperCase().trim();
        nivel = Nivel.valueOf(nivelIn);
        if (nivel.equals(Nivel.GRADUACAO) || nivel.equals(Nivel.POS_GRADUACAO)) {
          entradaValida = true;
        } else {
          System.out.println("Nível inválido, tente novamente.");
        }
      } catch (IllegalArgumentException e) {
        System.out.println("Entrada inválida, tente novamente.");
      }
    } while (!entradaValida);

    return nivel;
  }

  private int entraAnoCurso() {
    System.out.println("Entre com o ano do curso");
    Scanner in = new Scanner(System.in);
    int ano = in.nextInt();
    return ano;
  }

  public void adicionaAluno() {
    Aluno aluno = entraAluno();
    if (aluno == null) {
      return;
    }
    if (alunoDados.addAluno(aluno)) {
      System.out.println("Adicionando o aluno: " + aluno);
    } else {
      System.out.println("Falha ao adicionar aluno " + aluno);
    }
  }

  public void listaTodosAlunos() {
    System.out.println("Listando todos os alunos");
    for (Aluno a : alunoDados.getAlunos()) {
      System.out.println(a);
    }
  }

  public void listaAlunosByNome() {
    String nome = entraNomeAluno();
    listaAlunosByNome(nome);
  }

  public void listaAlunosByNome(String keyNome) {
    System.out.println("Listando todos os alunos contendo o nome: \" " + keyNome + "\"");

    Collection<Aluno> alunos = alunoDados.getAlunosByName(keyNome);
    if (alunos.size() == 0) {
      System.out.println("Nenhum aluno cujo o nome contenha: " + keyNome);
    }

    System.out.println(" " + alunos.size() + " alunos encontrados");
    for (Aluno a : alunos) {
      System.out.println(a);
    }
    System.out.println();
  }

  public void encontraAlunoById() {
    String id = entraIdAluno();
    encontraAlunoById(id);
  }

  public void encontraAlunoById(String keyId) {
    System.out.println("Procurando aluno com id (RA) \"" + keyId + "\"");
    Aluno a = alunoDados.getAlunoById(keyId);
    if (a == null) {
      System.out.println("Aluno não encontrado");
    } else {
      System.out.println("Aluno encontrado:");
      System.out.println(a);
    }
  }

  public Aluno entraAluno() {
    String id = entraIdAluno();

    Aluno temAluno = alunoDados.getAlunoById(id);
    if (temAluno != null) {
      System.out.println("Já temos um aluno com esse Id:");
      System.out.println(temAluno);
      return null;
    }

    String nome = entraNomeAluno();
    return new Aluno(id, nome);
  }

  public String entraIdAluno() {
    System.out.println("Entra com o Id (RA) do Aluno");
    Scanner in = new Scanner(System.in);

    String id = in.nextLine();

    return id.trim().toUpperCase();
  }

  public String entraNomeAluno() {
    System.out.println("Entre com o nome do Aluno");
    Scanner in = new Scanner(System.in);
    return in.nextLine().trim().toLowerCase();
  }
}
