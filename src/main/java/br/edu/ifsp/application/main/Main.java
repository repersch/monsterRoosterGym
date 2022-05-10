package br.edu.ifsp.application.main;

import br.edu.ifsp.application.repository.DatabaseBuilder;
import br.edu.ifsp.application.repository.SqliteAlunoDAO;
import br.edu.ifsp.application.repository.SqliteInstrutorDAO;
import br.edu.ifsp.application.repository.SqliteRegistroTreinoDAO;
import br.edu.ifsp.domain.entities.Aluno;
import br.edu.ifsp.domain.entities.Instrutor;
import br.edu.ifsp.domain.entities.RegistroTreino;
import br.edu.ifsp.domain.entities.Treino;
import br.edu.ifsp.domain.usecases.aluno.AlunoDAO;
import br.edu.ifsp.domain.usecases.aluno.BuscarAlunoUC;
import br.edu.ifsp.domain.usecases.aluno.CriarAlunoUC;
import br.edu.ifsp.domain.usecases.aluno.EditarAlunoUC;
import br.edu.ifsp.domain.usecases.instrutor.BuscarInstrutorUC;
import br.edu.ifsp.domain.usecases.instrutor.CriarInstrutorUC;
import br.edu.ifsp.domain.usecases.instrutor.InstrutorDAO;
import br.edu.ifsp.domain.usecases.registroTreino.RegistrarInicioTreinoUC;
import br.edu.ifsp.domain.usecases.registroTreino.RegistroTreinoDAO;

import java.time.LocalDate;

public class Main {

    public static CriarAlunoUC criarAlunoUC;
    public static BuscarAlunoUC buscarAlunoUC;
    public static EditarAlunoUC editarAlunoUC;

    public static CriarInstrutorUC criarInstrutorUC;
    public static BuscarInstrutorUC buscarInstrutorUC;

    public static RegistrarInicioTreinoUC registrarInicioTreinoUC;

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

        RegistroTreino registroTreino1 = new RegistroTreino(LocalDate.now(), LocalDate.now().plusDays(15), false, buscarAlunoUC.buscarPorCpf(aluno1.getCpf()).get());
        RegistroTreino registroTreino2 = new RegistroTreino(LocalDate.now().minusDays(5), LocalDate.now().plusDays(30), false, buscarAlunoUC.buscarPorCpf(aluno2.getCpf()).get());
        RegistroTreino registroTreino3 = new RegistroTreino(LocalDate.now().minusDays(3), LocalDate.now().plusDays(20), false, buscarAlunoUC.buscarPorCpf(aluno1.getCpf()).get());

        registrarInicioTreinoUC.salvar(registroTreino1);
        registrarInicioTreinoUC.salvar(registroTreino2);
        registrarInicioTreinoUC.salvar(registroTreino3);


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


    }

    private static void setupDatabase() {
        DatabaseBuilder dbBuilder = new DatabaseBuilder();
        dbBuilder.buildDatabaseIfMissing();
    }
}
