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
            criarTabelas();
        }
    }

    private boolean isDatabaseMissing() {
        return !Files.exists(Paths.get("gym.db"));
    }

    private static void apagarBancoDeDados() {
        try {
            Files.deleteIfExists(Paths.get("gym.db"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void criarTabelas() {
        try (Statement statement = ConnectionFactory.createStatement()) {
            statement.addBatch(criarTabelaUsuario());
            statement.addBatch(criarTabelaRegistroTreino());
            statement.addBatch(criarTabelaExercicio());
            statement.addBatch(criarTabelaTreino());
            statement.addBatch(criarTabelaFichaTreino());
            statement.addBatch(criarTabelaExercicioTreino());
            statement.executeBatch();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private String criarTabelaUsuario() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Usuario(\n");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT, \n");
        builder.append("nome TEXT NOT NULL, \n");
        builder.append("email TEXT NOT NULL UNIQUE, \n");
        builder.append("senha TEXT NOT NULL, \n");
        builder.append("isInstrutor INTEGER NOT NULL, \n");
        builder.append("cpf TEXT, \n");
        builder.append("telefone TEXT, \n");
        builder.append("genero TEXT, \n");
        builder.append("data_nascimento STRING, \n");
        builder.append("peso DOUBLE, \n");
        builder.append("altura DOUBLE, \n");
        builder.append("observacao TEXT\n");
        builder.append("); \n");

        System.out.println(builder.toString());
        return builder.toString();
    }


    private String criarTabelaRegistroTreino() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Registro_Treino(\n");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT,\n");
        builder.append("inicio TEXT NOT NULL,\n");
        builder.append("fim TEXT,\n");
        builder.append("estado TEXT NOT NULL,\n");
        builder.append("id_aluno INTEGER NOT NULL,\n");
        builder.append("id_treino INTEGER NOT NULL,\n");
        builder.append("FOREIGN KEY(id_aluno) REFERENCES Aluno(id)\n");
        builder.append("FOREIGN KEY(id_treino) REFERENCES Treino(id)\n");
        builder.append("); \n");

        System.out.println(builder.toString());
        return builder.toString();
    }

    private String criarTabelaExercicio() {

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

    private String criarTabelaTreino() {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE Treino(\n");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT,\n");
        builder.append("nome TEXT NOT NULL UNIQUE,\n");
        builder.append("observacao TEXT NOT NULL,");
        builder.append("id_ficha_treino INTEGER NOT NULL,\n");
        builder.append("FOREIGN KEY(id_ficha_treino) REFERENCES FichaTreino(id)\n");
        builder.append("); \n");
        System.out.println(builder.toString());
        return builder.toString();
    }

    private String criarTabelaFichaTreino() {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE FichaTreino(\n");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT,\n");
        builder.append("valido INTEGER NOT NULL,\n");
        builder.append("dataInicio STRING,\n");
        builder.append("validade STRING,\n");
        builder.append("id_aluno STRING NOT NULL,\n");
        builder.append("id_instrutor STRING,\n");
        builder.append("FOREIGN KEY(id_aluno) REFERENCES Usuario(id),\n");
        builder.append("FOREIGN KEY(id_instrutor) REFERENCES Usuario(id)\n");
        builder.append("); \n");
        System.out.println(builder.toString());
        return builder.toString();
    }

    private String criarTabelaExercicioTreino() {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE ExercicioTreino(\n");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT,\n");
        builder.append("serie INTEGER,\n");
        builder.append("carga DOUBLE,\n");
        builder.append("repeticao INTEGER,\n");
        builder.append("id_treino INTEGER NOT NULL,\n");
        builder.append("id_exercicio INTEGER NOT NULL,");
        builder.append("FOREIGN KEY(id_treino) REFERENCES Treino(id),\n");
        builder.append("FOREIGN KEY(id_exercicio) REFERENCES Exercicio(id)\n");
        builder.append("); \n");
        System.out.println(builder.toString());
        return builder.toString();
    }

}
