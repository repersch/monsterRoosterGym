package br.edu.ifsp.application.repository;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseBuilder {

    // public pq é o unico método que pode ser chamado de fora
    public void buildDatabaseIfMissing() {
        if (isDatabaseMissing()) {
            System.out.println("Database is missing. Building database: \n");
            buildTables();
        }
    }

    private boolean isDatabaseMissing() {
        return !Files.exists(Paths.get("database.db"));
    }

    // batch serve para executar várias sql de uma vez
    private void buildTables() {
        try (Statement statement = ConnectionFactory.createStatement()) {
            statement.addBatch(createBookTable());
//            statement.addBatch(createUserTable());
//            statement.addBatch(createTransactionTable());
            statement.executeBatch();

            System.out.println("Database successfully created.");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private String createBookTable() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Aluno(\n");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT, \n");
        builder.append("nome TEXT NOT NULL, \n");
        builder.append("cpf TEXT NOT NULL UNIQUE, \n");
        builder.append("email TEXT NOT NULL, \n");
        builder.append("telefone TEXT NOT NULL, \n");
        builder.append("genero TEXT NOT NULL, \n");
        builder.append("data_nascimento DATE NOT NULL, \n");
        builder.append("peso DOBLE NOT NULL, \n");
        builder.append("altura DOUBLE NOT NULL, \n");
        builder.append("observacao TEXT,\n");
//        builder.append("ultimoTreinoRealizado TEXT\n");
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