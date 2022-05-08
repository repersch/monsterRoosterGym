package br.edu.ifsp.application.main;

import br.edu.ifsp.application.repository.DatabaseBuilder;
import br.edu.ifsp.application.repository.SqliteAlunoDAO;
import br.edu.ifsp.domain.entities.Aluno;
import br.edu.ifsp.domain.usecases.aluno.AlunoDAO;
import br.edu.ifsp.domain.usecases.aluno.CriarAlunoUC;

import java.time.LocalDate;

public class Main {

    public static CriarAlunoUC criarAlunoUC;

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

        criarAlunoUC.insert(aluno1);
        criarAlunoUC.insert(aluno2);
    }


    private static void configureInjection() {

        AlunoDAO alunoDAO = new SqliteAlunoDAO();
        criarAlunoUC = new CriarAlunoUC(alunoDAO);

    }

    private static void setupDatabase() {
        DatabaseBuilder dbBuilder = new DatabaseBuilder();
        dbBuilder.buildDatabaseIfMissing();
    }
}
