package br.edu.ifsp.application.main;

import br.edu.ifsp.application.repository.utils.DatabaseBuilder;
import br.edu.ifsp.application.repository.dao.SqliteAlunoDAO;
import br.edu.ifsp.application.repository.dao.SqliteExercicioDAO;
import br.edu.ifsp.application.repository.dao.SqliteInstrutorDAO;
import br.edu.ifsp.application.repository.dao.SqliteRegistroTreinoDAO;
import br.edu.ifsp.domain.entities.*;
import br.edu.ifsp.domain.usecases.aluno.AlunoDAO;
import br.edu.ifsp.domain.usecases.aluno.BuscarAlunoUC;
import br.edu.ifsp.domain.usecases.aluno.CriarAlunoUC;
import br.edu.ifsp.domain.usecases.aluno.EditarAlunoUC;
import br.edu.ifsp.domain.usecases.exercicio.*;
import br.edu.ifsp.domain.usecases.instrutor.BuscarInstrutorUC;
import br.edu.ifsp.domain.usecases.instrutor.CriarInstrutorUC;
import br.edu.ifsp.domain.usecases.instrutor.InstrutorDAO;
import br.edu.ifsp.domain.usecases.registroTreino.BuscarRegistroTreinoUC;
import br.edu.ifsp.domain.usecases.registroTreino.RegistrarFinalTreinoUC;
import br.edu.ifsp.domain.usecases.registroTreino.RegistrarInicioTreinoUC;
import br.edu.ifsp.domain.usecases.registroTreino.RegistroTreinoDAO;

import java.time.LocalDate;
import java.util.List;

public class Main {

    public static CriarAlunoUC criarAlunoUC;
    public static BuscarAlunoUC buscarAlunoUC;
    public static EditarAlunoUC editarAlunoUC;

    public static CriarInstrutorUC criarInstrutorUC;
    public static BuscarInstrutorUC buscarInstrutorUC;

    public static RegistrarInicioTreinoUC registrarInicioTreinoUC;
    public static RegistrarFinalTreinoUC registrarFinalTreinoUC;
    public static BuscarRegistroTreinoUC buscarRegistroTreinoUC;

    public static CriarExercicioUC criarExercicioUC;
    public static BuscarExercicioUC buscarExercicioUC;
    public static EditarExercicioUC editarExercicioUC;
    public static DeletarExercicioUC deletarExercicioUC;

    public static void main(String[] args) {
        System.out.println("Configurando INJECTIONS");
        configureInjection();
        System.out.println("Setup DATABASE");
        setupDatabase();
        populateFakeDatabase();

    }

    private static void populateFakeDatabase() {

        Aluno aluno1 = new Aluno("Renata Persch", "366.923.004-77", "renata@mail.com",
                "16985239632", "feminino", LocalDate.of(1990, 10, 30),
                68.5, 1.76, "");

        Aluno aluno2 = new Aluno("Hermione Granger", "458.913.074-85", "mione@email.com",
                "16996217598", "feminino", LocalDate.of(1992, 7, 13),
                65.0, 1.62, "Alergica");

        criarAlunoUC.salvar(aluno1);
        criarAlunoUC.salvar(aluno2);

        System.out.println("Busca por CPF: " + buscarAlunoUC.buscarPorCpf(aluno1.getCpf()));
        System.out.println("Busca por Nome: " + buscarAlunoUC.buscarPorNome(aluno2.getNome()));
        System.out.println("Buscar Todos: " + buscarAlunoUC.buscarTodos());

        editarAlunoUC.atualizar(new Aluno(1,"Renata Persch Camacho", "366.923.004-77", "renata_persch@mail.com",
                "16985239632", "feminino", LocalDate.of(1990, 10, 30),
                68.5, 1.76, "Cansada"));

        System.out.println("Buscar por ID depois de atualizar: " + buscarAlunoUC.buscarPorId(1));

        Aluno aluno3 = new Aluno("Paola Bracho", "145.987.204-41", "paola@mail.com",
                "16985274535", "feminino", LocalDate.of(1985, 4, 15),
                74.0, 1.56, "");

        Aluno aluno4 = new Aluno("Jorjinho", "459.974.458-03", "jorjin@email.com",
                "16996274856", "masculino", LocalDate.of(1993, 3, 26),
                85.0, 1.87, "");

        criarAlunoUC.salvar(aluno3);
        criarAlunoUC.salvar(aluno4);


        System.out.println("-------------------- INSTRUTOR ---------------------");

        Instrutor instrutor1 = new Instrutor("Marcio SalvaVidas", "marcio@mail.com", "senha");
        Instrutor instrutor2 = new Instrutor("Guillermo Presegura", "gui@mail.com", "senha");



        criarInstrutorUC.salvar(instrutor1);
        criarInstrutorUC.salvar(instrutor2);

        System.out.println("Buscar todos: " + buscarInstrutorUC.buscarTodos());
        System.out.println("Buscar por nome: " + buscarInstrutorUC.buscarPorNome(instrutor1.getNome()));
        System.out.println("Buscar por email: " + buscarInstrutorUC.buscarPorEmail(instrutor2.getEmail()));
        System.out.println("Buscar por id: " + buscarInstrutorUC.buscarPorId(1));

        System.out.println("-------------------- Registro Treino ---------------------");

        RegistroTreino registroTreino1 = new RegistroTreino(LocalDate.now(), LocalDate.now().plusDays(15), EstadoRegistroTreino.INICIADO, buscarAlunoUC.buscarPorCpf(aluno1.getCpf()).get());
        RegistroTreino registroTreino2 = new RegistroTreino(LocalDate.now().minusDays(5), LocalDate.now().plusDays(30), EstadoRegistroTreino.FINALIZADO, buscarAlunoUC.buscarPorCpf(aluno2.getCpf()).get());
        RegistroTreino registroTreino3 = new RegistroTreino(LocalDate.now().minusDays(3), LocalDate.now().plusDays(20), EstadoRegistroTreino.INICIADO, buscarAlunoUC.buscarPorCpf(aluno1.getCpf()).get());

        System.out.println(registrarInicioTreinoUC.iniciarTreino(registroTreino1));
        System.out.println(registrarInicioTreinoUC.iniciarTreino(registroTreino2));
        System.out.println(registrarInicioTreinoUC.iniciarTreino(registroTreino3));

        RegistroTreino registroTreino4 = new RegistroTreino(LocalDate.now().minusDays(7), LocalDate.now().plusDays(10), EstadoRegistroTreino.FINALIZADO, buscarAlunoUC.buscarPorCpf(aluno3.getCpf()).get());
        RegistroTreino registroTreino5 = new RegistroTreino(LocalDate.now().minusDays(2), LocalDate.now().plusDays(3), EstadoRegistroTreino.INICIADO, buscarAlunoUC.buscarPorCpf(aluno2.getCpf()).get());
        RegistroTreino registroTreino6 = new RegistroTreino(LocalDate.now(), LocalDate.now().plusDays(5), EstadoRegistroTreino.FINALIZADO, buscarAlunoUC.buscarPorCpf(aluno4.getCpf()).get());

        System.out.println(registrarInicioTreinoUC.iniciarTreino(registroTreino4));
        System.out.println(registrarInicioTreinoUC.iniciarTreino(registroTreino5));
        System.out.println(registrarInicioTreinoUC.iniciarTreino(registroTreino6));

        System.out.println(buscarRegistroTreinoUC.buscarTodos());

        List<RegistroTreino> registroTreinoAluno1 = buscarRegistroTreinoUC.buscarPorAluno(1);
        System.out.println(registroTreinoAluno1);

        System.out.println(registrarFinalTreinoUC.finalizarTreino(buscarRegistroTreinoUC.buscarPorAluno(3).get(0)));
        System.out.println(registrarFinalTreinoUC.finalizarTreino(buscarRegistroTreinoUC.buscarPorAluno(2).get(0)));

        System.out.println(buscarRegistroTreinoUC.buscarPorAluno(buscarAlunoUC.buscarPorCpf(aluno3.getCpf()).get()));
//        System.out.println(buscarRegistroTreinoUC.buscarPorId(registroTreino4.getId()));
        System.out.println(buscarRegistroTreinoUC.buscarPorAluno(buscarAlunoUC.buscarPorCpf(aluno4.getCpf()).get()));


        System.out.println("-------------------- EXERCÍCIO ---------------------");
        Exercicio exercicio1 = new Exercicio(1, "Supino Reto", GrupoMuscular.PEITORAL, "Utilizar halteres", true);
        Exercicio exercicio2 = new Exercicio(2, "Elevação Lateral", GrupoMuscular.DELTOIDES, "Utilizar anilha", true);
        Exercicio exercicio3 = new Exercicio(3, "Leg Press", GrupoMuscular.QUADRICEPS, "Ajustar inclinação em 45°", false);
        Exercicio exercicio4 = new Exercicio(4, "Rosca Direta", GrupoMuscular.BICEPS, "Utilizar barra", false);

        criarExercicioUC.salvar(exercicio1);
        criarExercicioUC.salvar(exercicio2);
        criarExercicioUC.salvar(exercicio3);
        criarExercicioUC.salvar(exercicio4);

        System.out.println("Busca por Id: " + buscarExercicioUC.buscarPorId(exercicio2.getId()));
        System.out.println("Busca por Nome: " + buscarExercicioUC.buscarPorNome(exercicio1.getNome()));
        System.out.println("Buscar Todos: " + buscarExercicioUC.buscarTodos());

        editarExercicioUC.atualizar(new Exercicio(3, "Prancha Reta", GrupoMuscular.ABDOMEN, "Isometria de 60 segundos", true));
        System.out.println("Buscar por ID depois de atualizar: " + buscarExercicioUC.buscarPorId(3));

        deletarExercicioUC.remover(2);
        System.out.println("Buscar Todos Deleção por Id: " + buscarExercicioUC.buscarTodos());

        deletarExercicioUC.remover(exercicio4);
        System.out.println("Buscar Todos Deleção por Exercicio: " + buscarExercicioUC.buscarTodos());





    }


    private static void configureInjection() {

        AlunoDAO alunoDAO = new SqliteAlunoDAO();
        criarAlunoUC = new CriarAlunoUC(alunoDAO);
        editarAlunoUC = new EditarAlunoUC(alunoDAO);
        buscarAlunoUC = new BuscarAlunoUC(alunoDAO);

        InstrutorDAO instrutorDAO = new SqliteInstrutorDAO();
        criarInstrutorUC = new CriarInstrutorUC(instrutorDAO);
        buscarInstrutorUC = new BuscarInstrutorUC(instrutorDAO);

        RegistroTreinoDAO registroTreinoDAO = new SqliteRegistroTreinoDAO();
        registrarInicioTreinoUC = new RegistrarInicioTreinoUC(registroTreinoDAO);
        registrarFinalTreinoUC = new RegistrarFinalTreinoUC(registroTreinoDAO);
        buscarRegistroTreinoUC = new BuscarRegistroTreinoUC(registroTreinoDAO);

        ExercicioDAO exercicioDAO = new SqliteExercicioDAO();
        criarExercicioUC = new CriarExercicioUC(exercicioDAO);
        editarExercicioUC = new EditarExercicioUC(exercicioDAO);
        buscarExercicioUC = new BuscarExercicioUC(exercicioDAO);
        deletarExercicioUC = new DeletarExercicioUC(exercicioDAO);



    }

    private static void setupDatabase() {
        DatabaseBuilder dbBuilder = new DatabaseBuilder();
        dbBuilder.buildDatabaseIfMissing();
    }
}
