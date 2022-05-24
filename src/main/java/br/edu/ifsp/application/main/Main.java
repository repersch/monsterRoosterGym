package br.edu.ifsp.application.main;

import br.edu.ifsp.application.repository.dao.*;
import br.edu.ifsp.application.repository.utils.DatabaseBuilder;
import br.edu.ifsp.domain.entities.*;
import br.edu.ifsp.domain.usecases.autenticar.AutenticarUC;
import br.edu.ifsp.domain.usecases.exercicioTreino.AlterarCargaExercicioTreinoUC;
import br.edu.ifsp.domain.usecases.exercicioTreino.BuscarExercicioTreinoUC;
import br.edu.ifsp.domain.usecases.exercicioTreino.CriarExercicioTreinoUC;
import br.edu.ifsp.domain.usecases.exercicioTreino.ExercicioTreinoDAO;
import br.edu.ifsp.domain.usecases.fichaTreino.BuscarFichaTreinoUC;
import br.edu.ifsp.domain.usecases.fichaTreino.CriarFichaTreinoUC;
import br.edu.ifsp.domain.usecases.fichaTreino.EditarFichaTreinoUC;
import br.edu.ifsp.domain.usecases.fichaTreino.FichaTreinoDAO;
import br.edu.ifsp.domain.usecases.treino.*;
import br.edu.ifsp.domain.usecases.usuario.BuscarUsuarioUC;
import br.edu.ifsp.domain.usecases.usuario.CriarUsuarioUC;
import br.edu.ifsp.domain.usecases.usuario.EditarUsuarioUC;
import br.edu.ifsp.domain.usecases.usuario.UsuarioDAO;
import br.edu.ifsp.domain.usecases.exercicio.*;
import br.edu.ifsp.domain.usecases.registroTreino.BuscarRegistroTreinoUC;
import br.edu.ifsp.domain.usecases.registroTreino.RegistrarFinalTreinoUC;
import br.edu.ifsp.domain.usecases.registroTreino.RegistrarInicioTreinoUC;
import br.edu.ifsp.domain.usecases.registroTreino.RegistroTreinoDAO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class Main {

    public static CriarUsuarioUC criarUsuarioUC;
    public static BuscarUsuarioUC buscarUsuarioUC;
    public static EditarUsuarioUC editarUsuarioUC;

    public static RegistrarInicioTreinoUC registrarInicioTreinoUC;
    public static RegistrarFinalTreinoUC registrarFinalTreinoUC;
    public static BuscarRegistroTreinoUC buscarRegistroTreinoUC;

    public static CriarExercicioUC criarExercicioUC;
    public static BuscarExercicioUC buscarExercicioUC;
    public static EditarExercicioUC editarExercicioUC;
    public static DeletarExercicioUC deletarExercicioUC;

    public static CriarTreinoUC criarTreinoUC;
    public static BuscarTreinoUC buscarTreinoUC;
    public static EditarTreinoUC editarTreinoUC;

    public static CriarFichaTreinoUC criarFichaTreinoUC;
    public static BuscarFichaTreinoUC buscarFichaTreinoUC;
    public static EditarFichaTreinoUC editarFichaTreinoUC;

    public static CriarExercicioTreinoUC criarExercicioTreinoUC;
    public static BuscarExercicioTreinoUC buscarExercicioTreinoUC;
    public static AlterarCargaExercicioTreinoUC alterarCargaExercicioTreinoUC;

    public static AutenticarUC autenticarUC;

    public static void main(String[] args) {
        System.out.println("Configurando INJECTIONS");
        configureInjection();
        System.out.println("Setup DATABASE");
        setupDatabase();
        populateFakeDatabase();
    }

    private static void populateFakeDatabase() {

        System.out.println("-----------------------------------------------------------");
        System.out.println("--------------- TESTES DE USUÁRIO - ALUNO ------------------");
        System.out.println("-----------------------------------------------------------\n");

        Usuario aluno1 = new Usuario("Renata Persch", "renata@mail.com", "senha", false, new Aluno("366.923.004-77",
                "16985239632", "feminino", LocalDate.of(1990, 10, 30),
                68.5, 1.76, null));

        Usuario aluno2 = new Usuario("Hermione Granger", "mione@email.com", "senha", false, new Aluno("458.913.074-85",
                "16996217598", "feminino", LocalDate.of(1992, 7, 13),
                65.0, 1.62, "Alergica"));

        criarUsuarioUC.salvar(aluno1);
        criarUsuarioUC.salvar(aluno2);

        System.out.println("\n-----------Busca por Nome: " + buscarUsuarioUC.buscarPorNome(aluno2.getNome()));
        System.out.println("\n-----------Buscar por Id: " + buscarUsuarioUC.buscarPorId(1));

        editarUsuarioUC.atualizar(new Usuario(1,"Renata Persch Camacho", "renata_persch@mail.com", "senha",
                false, new Aluno("366.923.004-77", "16985239632", "feminino", LocalDate.of(1990, 10, 30),
                68.5, 1.76, "Cansada")));

        System.out.println("\n-----------Buscar por ID depois de atualizar: " + buscarUsuarioUC.buscarPorId(1));

        Usuario aluno3 = new Usuario("Paola Bracho", "paola@mail.com", "senha", false, (new Aluno("145.987.204-41",
                "16985274535", "feminino", LocalDate.of(1985, 4, 15),
                74.0, 1.56, null)));

        Usuario aluno4 = new Usuario("Jorjinho", "jorjin@email.com", "senha", false, new Aluno("459.974.458-03",
                "16996274856", "masculino", LocalDate.of(1993, 3, 26),
                85.0, 1.87, null));

        criarUsuarioUC.salvar(aluno3);
        criarUsuarioUC.salvar(aluno4);

        Usuario aluno5 = new Usuario("Ana Maria", "jorjin@email.com", "senha", false, new Aluno("459.974.458-03",
                "169955484856", "feminino", LocalDate.of(1995, 10, 11),
                74.0, 1.66, null));

        System.out.println("\n-----------Buscar Todos Alunos: " + buscarUsuarioUC.buscarTodosAlunos());

//        System.out.println("---------------Testando erro: ");
//        criarUsuarioUC.salvar(aluno5);

        System.out.println("\n\n------------------------------------------------------------");
        System.out.println("--------------- TESTES DE USUÁRIO - INSTRUTOR ---------------");
        System.out.println("-------------------------------------------------------------\n");

        Usuario instrutor1 = new Usuario("Marcio SalvaVidas", "marcio@mail.com", "senha", true, null);
        Usuario instrutor2 = new Usuario("Guillermo Presegura", "gui@mail.com", "senha", true, null);

        criarUsuarioUC.salvar(instrutor1);
        criarUsuarioUC.salvar(instrutor2);

        System.out.println("\n---------------Buscar todos instrutores: " + buscarUsuarioUC.buscarTodosInstrutores());
        System.out.println("\n---------------Buscar por nome: " + buscarUsuarioUC.buscarPorNome(instrutor1.getNome()));
        System.out.println("\n---------------Buscar por id: " + buscarUsuarioUC.buscarPorId(6));


        System.out.println("\n\n-----------------------------------------------------------------");
        System.out.println("--------------- TESTES DE USUÁRIO E INSTRUTOR ---------------------");
        System.out.println("-----------------------------------------------------------------\n");


        System.out.println("\n---------------Buscando todos os ALUNOS: " + buscarUsuarioUC.buscarTodosAlunos());
        System.out.println("\n---------------Buscando todos os INSTRUTORES: " + buscarUsuarioUC.buscarTodosInstrutores());


        System.out.println("\n\n--------------- TESTES DE AUTENTICAÇÃO ---------------\n");

        System.out.println("\n---------------Autenticando aluno 2: " + autenticarUC.autenticar(aluno2.getEmail(), "senha"));

        System.out.println("\n---------------Autenticando instrutor 2: " + autenticarUC.autenticar(instrutor1.getEmail(), "senha"));

//        System.out.println("---------------Testando erro: ");
//        System.out.println("\n---------------Autenticação com email errado: " + autenticarUC.autenticar("renata@mail.comm", "senha"));
//        System.out.println("\n---------------Autenticação com senha errada: " + autenticarUC.autenticar(aluno4.getEmail(), "senhaa"));


        System.out.println("\n\n-----------------------------------------------------------------");
        System.out.println("------------------------- EXERCÍCIOS --------------------------");
        System.out.println("-----------------------------------------------------------------\n");


        Exercicio exercicio1 = new Exercicio("Supino Reto", GrupoMuscular.PEITORAL, "Utilizar halteres", true);
        Exercicio exercicio2 = new Exercicio("Elevação Lateral", GrupoMuscular.DELTOIDES, "Utilizar anilha", false);
        Exercicio exercicio3 = new Exercicio("Leg Press", GrupoMuscular.QUADRICEPS, "Ajustar inclinação em 45°", false);
        Exercicio exercicio4 = new Exercicio("Rosca Direta", GrupoMuscular.BICEPS, "Utilizar barra", false);

        criarExercicioUC.salvar(exercicio1);
        criarExercicioUC.salvar(exercicio2);
        criarExercicioUC.salvar(exercicio3);
        criarExercicioUC.salvar(exercicio4);

        System.out.println("\n---------------Busca por Id: " + buscarExercicioUC.buscarPorId(3));
        System.out.println("\n---------------Busca por Nome: " + buscarExercicioUC.buscarPorNome(exercicio1.getNome()));
        System.out.println("\n---------------Buscar Todos: " + buscarExercicioUC.buscarTodos());

//        System.out.println("\n---------------Testando erro: ");
//        deletarExercicioUC.remover(buscarExercicioUC.buscarPorId(1).get());


        editarExercicioUC.atualizar(new Exercicio(3, "Prancha Reta", GrupoMuscular.ABDOMEN, "Isometria de 60 segundos", true));
        System.out.println("\n---------------Buscar por ID depois de atualizar: " + buscarExercicioUC.buscarPorId(3));

        deletarExercicioUC.remover(2);
        System.out.println("\n---------------Buscar Todos depois de exluir ID 2: " + buscarExercicioUC.buscarTodos());

        deletarExercicioUC.remover(buscarExercicioUC.buscarPorNome(exercicio4.getNome()).get());
        System.out.println("\n---------------Buscar Todos depois de excluir Exercicio 4: " + buscarExercicioUC.buscarTodos());

        System.out.println("\n\n-------------------------------------------------------------");
        System.out.println("-------------------- FICHA TREINO ---------------------");
        System.out.println("--------------------------------------------------------------\n");


        FichaTreino fichaTreino1 = new FichaTreino(true, LocalDate.now(), LocalDate.now(), (buscarUsuarioUC.buscarPorNome(aluno4.getNome()).get()), buscarUsuarioUC.buscarPorNome(instrutor1.getNome()).get());
        FichaTreino fichaTreino2 = new FichaTreino(true, LocalDate.now(), LocalDate.now(), (buscarUsuarioUC.buscarPorNome(aluno3.getNome()).get()), buscarUsuarioUC.buscarPorNome(instrutor2.getNome()).get());

        criarFichaTreinoUC.salvar(fichaTreino1);
        criarFichaTreinoUC.salvar(fichaTreino2);

        System.out.println("\n-----------Buscar Ficha Treino por ID: " + buscarFichaTreinoUC.buscarPorId(2));
        System.out.println("\n-----------Buscar Todas as Fichas Treino: " + buscarFichaTreinoUC.buscarTodos());

        editarFichaTreinoUC.atualizar(new FichaTreino(1,true, LocalDate.now().minusDays(7), LocalDate.now().minusDays(7), buscarUsuarioUC.buscarPorCpf(aluno2.getAluno().getCpf()).get(), (Usuario) buscarUsuarioUC.buscarPorNome(instrutor2.getNome()).get()));
        System.out.println("Buscar por ID depois de atualizar: " + buscarFichaTreinoUC.buscarPorId(1));



        System.out.println("\n\n-----------------------------------------------------------------");
        System.out.println("---------------------------- TREINO -----------------------------");
        System.out.println("-------------------------------------------------------------------\n");

        Treino treino1 = new Treino("Treino A", "Treino de segunda e quinta", buscarFichaTreinoUC.buscarPorId(1).get());
        Treino treino2 = new Treino("Treino B", "Treino de terça e sexta", buscarFichaTreinoUC.buscarPorId(2).get());
        Treino treino3 = new Treino("Treino C", "Treino de quarta e sábado", buscarFichaTreinoUC.buscarPorId(1).get());

        criarTreinoUC.salvar(treino1);
        criarTreinoUC.salvar(treino2);
        criarTreinoUC.salvar(treino3);

        System.out.println("\n---------------Busca por Id: " + buscarTreinoUC.buscarPorId(3));
        System.out.println("\n---------------Busca por Nome: " + buscarTreinoUC.buscarPorNome(treino2.getNome()));
        System.out.println("\n---------------Buscar Todos Treinos: " + buscarTreinoUC.buscarTodos());

        treino2 = buscarTreinoUC.buscarPorId(2).get();
        treino2.setObservacao("Treino de segunda, quarta e sexta");
        editarTreinoUC.editar(treino2);
        System.out.println("\n---------------Buscar Treino 2 depois de editar: " + buscarTreinoUC.buscarPorId(2));


        System.out.println("\n\n-----------------------------------------------------------------");
        System.out.println("------------------------ REGISTRO TREINO --------------------------");
        System.out.println("-------------------------------------------------------------------\n");


        RegistroTreino registroTreino1 = new RegistroTreino(LocalDate.now(), LocalDate.now().plusDays(15), EstadoRegistroTreino.INICIADO, buscarUsuarioUC.buscarPorId(1).get(), buscarTreinoUC.buscarPorNome(treino2.getNome()).get());
        RegistroTreino registroTreino2 = new RegistroTreino(LocalDate.now().minusDays(5), LocalDate.now().plusDays(30), EstadoRegistroTreino.FINALIZADO, buscarUsuarioUC.buscarPorCpf(aluno2.getAluno().getCpf()).get(), buscarTreinoUC.buscarPorNome(treino2.getNome()).get());
        RegistroTreino registroTreino3 = new RegistroTreino(LocalDate.now().minusDays(3), LocalDate.now().plusDays(20), EstadoRegistroTreino.INICIADO, buscarUsuarioUC.buscarPorNome("Renata Persch Camacho").get(), buscarTreinoUC.buscarPorNome(treino1.getNome()).get());


        System.out.println("\n---------------Registro treino 1: " + registrarInicioTreinoUC.iniciarTreino(registroTreino1));
        System.out.println("\n---------------Registro treino 2: " + registrarInicioTreinoUC.iniciarTreino(registroTreino2));
        System.out.println("\n---------------Registro treino 3: " + registrarInicioTreinoUC.iniciarTreino(registroTreino3));

        RegistroTreino registroTreino4 = new RegistroTreino(LocalDate.now().minusDays(7), LocalDate.now().plusDays(10), EstadoRegistroTreino.FINALIZADO, buscarUsuarioUC.buscarPorCpf(aluno3.getAluno().getCpf()).get(), buscarTreinoUC.buscarPorNome(treino1.getNome()).get());
        RegistroTreino registroTreino5 = new RegistroTreino(LocalDate.now().minusDays(2), LocalDate.now().plusDays(3), EstadoRegistroTreino.INICIADO, buscarUsuarioUC.buscarPorCpf(aluno2.getAluno().getCpf()).get(), buscarTreinoUC.buscarPorNome(treino3.getNome()).get());
        RegistroTreino registroTreino6 = new RegistroTreino(LocalDate.now(), LocalDate.now().plusDays(5), EstadoRegistroTreino.FINALIZADO, buscarUsuarioUC.buscarPorCpf(aluno4.getAluno().getCpf()).get(), buscarTreinoUC.buscarPorNome(treino2.getNome()).get());

        System.out.println("\n---------------Registro Treino 4: " + registrarInicioTreinoUC.iniciarTreino(registroTreino4));
        System.out.println("\n---------------Registro Treino 5: " + registrarInicioTreinoUC.iniciarTreino(registroTreino5));
        System.out.println("\n---------------Registro Treino 6: " + registrarInicioTreinoUC.iniciarTreino(registroTreino6));

        System.out.println("\n---------------Todos os Registro Treino: " + buscarRegistroTreinoUC.buscarTodos());

        List<RegistroTreino> registroTreinoAluno2 = buscarRegistroTreinoUC.buscarPorAluno(2);
        System.out.println("\n---------------Registros treino do Aluno 2: " + registroTreinoAluno2);

        System.out.println("\n---------------Registrar final treino do aluno 3: " + registrarFinalTreinoUC.finalizarTreino(buscarRegistroTreinoUC.buscarPorAluno(3).get(0)));
        System.out.println("\n---------------Registrar final treino do aluno 2: " + registrarFinalTreinoUC.finalizarTreino(buscarRegistroTreinoUC.buscarPorAluno(2).get(0)));

        System.out.println("\n---------------Registro treino do aluno 3: " + buscarRegistroTreinoUC.buscarPorAluno(buscarUsuarioUC.buscarPorCpf(aluno3.getAluno().getCpf()).get()));
        System.out.println("\n---------------Registro treino 2: " + buscarRegistroTreinoUC.buscarPorId(2));
        System.out.println("\n---------------Registro treino do aluno 4: " + buscarRegistroTreinoUC.buscarPorAluno(buscarUsuarioUC.buscarPorCpf(aluno4.getAluno().getCpf()).get()));


        System.out.println("\n\n-----------------------------------------------------------");
        System.out.println("-------------------- EXERCICIO TREINO -----------------------");
        System.out.println("-----------------------------------------------------------\n");


        ExercicioTreino exercicioTreino1 = new ExercicioTreino(5, 3.3, 5, buscarTreinoUC.buscarPorId(2).get(),buscarExercicioUC.buscarPorId(3).get());
        ExercicioTreino exercicioTreino2 = new ExercicioTreino(2, 2.0, 4, buscarTreinoUC.buscarPorId(1).get(),buscarExercicioUC.buscarPorId(1).get());
        ExercicioTreino exercicioTreino3 = new ExercicioTreino(7, 5.1, 3, buscarTreinoUC.buscarPorId(3).get(),buscarExercicioUC.buscarPorId(1).get());


        criarExercicioTreinoUC.salvar(exercicioTreino1);
        criarExercicioTreinoUC.salvar(exercicioTreino2);
        criarExercicioTreinoUC.salvar(exercicioTreino3);

        alterarCargaExercicioTreinoUC.atualizarCargaExercicio(3, 16.8);
        alterarCargaExercicioTreinoUC.atualizarCargaExercicio(2, 20.0);
        alterarCargaExercicioTreinoUC.atualizarCargaExercicio(1, 15.5);


        System.out.println("\n-----------Buscar Todos Exercicios Treino: " + buscarExercicioTreinoUC.buscarTodos());


        System.out.println("\n\n-----------------------------------------------------------");
        System.out.println("-------------------- GERAR RELATORIO DE TREINO ------------------");
        System.out.println("--------------------------------------------------------------\n");



        Usuario usuarioRelatorio = buscarUsuarioUC.buscarPorId(1).get();
        GerarRelatorioTreinoAluno relatorio1 = new GerarRelatorioTreinoAluno(usuarioRelatorio, buscarFichaTreinoUC.buscarPorId(usuarioRelatorio.getId()).get());

        System.out.println(relatorio1);


    }


    private static void configureInjection() {

        UsuarioDAO usuarioDAO = new SqliteUsuarioDAO();
        criarUsuarioUC = new CriarUsuarioUC(usuarioDAO);
        editarUsuarioUC = new EditarUsuarioUC(usuarioDAO);
        buscarUsuarioUC = new BuscarUsuarioUC(usuarioDAO);

        RegistroTreinoDAO registroTreinoDAO = new SqliteRegistroTreinoDAO();
        registrarInicioTreinoUC = new RegistrarInicioTreinoUC(registroTreinoDAO);
        registrarFinalTreinoUC = new RegistrarFinalTreinoUC(registroTreinoDAO);
        buscarRegistroTreinoUC = new BuscarRegistroTreinoUC(registroTreinoDAO);

        ExercicioDAO exercicioDAO = new SqliteExercicioDAO();
        criarExercicioUC = new CriarExercicioUC(exercicioDAO);
        editarExercicioUC = new EditarExercicioUC(exercicioDAO);
        buscarExercicioUC = new BuscarExercicioUC(exercicioDAO);
        deletarExercicioUC = new DeletarExercicioUC(exercicioDAO);

        TreinoDAO treinoDAO = new SqliteTreinoDAO();
        criarTreinoUC = new CriarTreinoUC(treinoDAO);
        buscarTreinoUC = new BuscarTreinoUC(treinoDAO);
        editarTreinoUC = new EditarTreinoUC(treinoDAO);

        FichaTreinoDAO fichaTreinoDAO = new SqliteFichaTreinoDAO();
        criarFichaTreinoUC = new CriarFichaTreinoUC(fichaTreinoDAO);
        buscarFichaTreinoUC = new BuscarFichaTreinoUC(fichaTreinoDAO);
        editarFichaTreinoUC = new EditarFichaTreinoUC(fichaTreinoDAO);

        ExercicioTreinoDAO exercicioTreinoDAO = new SqliteExercicioTreinoDAO();
        criarExercicioTreinoUC = new CriarExercicioTreinoUC(exercicioTreinoDAO);
        buscarExercicioTreinoUC = new BuscarExercicioTreinoUC(exercicioTreinoDAO);
        alterarCargaExercicioTreinoUC = new AlterarCargaExercicioTreinoUC(exercicioTreinoDAO);

        autenticarUC = new AutenticarUC(usuarioDAO);
    }

    private static void setupDatabase() {
        DatabaseBuilder dbBuilder = new DatabaseBuilder();
        dbBuilder.buildDatabaseIfMissing();
    }
}
