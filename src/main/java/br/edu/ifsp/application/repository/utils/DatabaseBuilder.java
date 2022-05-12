package br.edu.ifsp.application.repository.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseBuilder {

    public void buildDatabaseIfMissing() {
        apagarBancoDeDados();
        if (isDatabaseMissing()) {
            System.out.println("Database is missing. Building database: \n");
            criarTabelas();
        }
    }

    private boolean isDatabaseMissing() {
        return !Files.exists(Paths.get("gym.db"));
    }

    private static void apagarBancoDeDados() {
        System.out.println("Apagando banco de dados...");
        try {
            Files.deleteIfExists(Paths.get("gym.db"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // batch serve para executar várias sql de uma vez
    private void criarTabelas() {
        try (Statement statement = ConnectionFactory.createStatement()) {
            statement.addBatch(createUsuarioTable());
            statement.addBatch(createRegistroTreinoTable());
            statement.addBatch(createExercicioTable());
//            statement.addBatch(createTransactionTable());
            statement.executeBatch();

            System.out.println("Criando banco de dados...");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private String createUsuarioTable() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Usuario(\n");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT, \n");
        builder.append("nome TEXT NOT NULL, \n");
        builder.append("email TEXT NOT NULL, \n");
        builder.append("senha TEXT NOT NULL, \n");
        builder.append("isInstrutor INTEGER NOT NULL, \n");
        builder.append("cpf TEXT, \n");
        builder.append("telefone TEXT, \n");
        builder.append("genero TEXT, \n");
        builder.append("data_nascimento STRING, \n");
        builder.append("peso DOBLE, \n");
        builder.append("altura DOUBLE, \n");
        builder.append("observacao TEXT\n");
//        builder.append("ultimoTreinoRealizado TEXT\n");
        builder.append("); \n");

        System.out.println(builder.toString());
        return builder.toString();
    }


    private String createRegistroTreinoTable() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Registro_Treino(\n");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT,\n");
        builder.append("inicio TEXT NOT NULL,\n");
        builder.append("fim TEXT,\n");
        builder.append("estado TEXT NOT NULL,\n");
        builder.append("id_aluno INTEGER NOT NULL,\n");
        builder.append("FOREIGN KEY(id_aluno) REFERENCES Aluno(id)\n");
        builder.append("); \n");

        System.out.println(builder.toString());
        return builder.toString();
    }

    private String createExercicioTable() {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE Exercicio(\n");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT,\n");
        builder.append("nome TEXT NOT NULL UNIQUE,\n");
        builder.append("grupo_muscular TEXT NOT NULL,\n");
        builder.append("descricao TEXT NOT NULL,\n");
        builder.append("em_uso INTEGER NOT NULL");
        builder.append("); \n");
        System.out.println(builder.toString());
        return builder.toString();
    }

//    private String createUserTable() {
//        StringBuilder builder = new StringBuilder();
//
//        builder.append("CREATE TABLE User(\n");
//        builder.append("id TEXT NOT NULL PRIMARY KEY, \n");
//        builder.append("name TEXT NOT NULL, \n");
//        builder.append("email TEXT NOT NULL UNIQUE, \n");
//        builder.append("phone TEXT NOT NULL, \n");
//        builder.append("course TEXT, \n");
//        builder.append("department TEXT\n");
//        builder.append("); \n");
//
//        System.out.println(builder.toString());
//        return builder.toString();
//    }
//
//    private String createTransactionTable() {
//        StringBuilder builder = new StringBuilder();
//
//        builder.append("CREATE TABLE BookTransaction(\n");
//        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT, \n");
//        builder.append("user TEXT NOT NULL, \n");
//        builder.append("book INTEGER NOT NULL, \n");
//        builder.append("issue_date TEXT NOT NULL, \n");
//        builder.append("due_date TEXT NOT NULL, \n");
//        builder.append("return_date TEXT, \n");
//        builder.append("FOREIGN KEY(user) REFERENCES User(id), \n");
//        builder.append("FOREIGN KEY(book) REFERENCES Book(id)\n");
//        builder.append("); \n");
//
//        System.out.println(builder.toString());
//        return builder.toString();
//    }
}