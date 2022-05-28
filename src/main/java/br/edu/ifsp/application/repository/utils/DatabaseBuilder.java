package br.edu.ifsp.application.repository.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseBuilder {

    public void buildDatabaseIfMissing() {
//        apagarBancoDeDados();
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
        String sql = """
                CREATE TABLE Usuario(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome TEXT NOT NULL,
                    email TEXT NOT NULL UNIQUE,
                    senha TEXT NOT NULL,
                    isInstrutor INTEGER NOT NULL,
                    cpf TEXT,
                    telefone TEXT,
                    genero TEXT,
                    data_nascimento STRING,
                    peso DOUBLE,
                    altura DOUBLE,
                    observacao TEXT);
                """;

        System.out.println(sql);
        return sql;
    }


    private String criarTabelaRegistroTreino() {
        String sql = """
                CREATE TABLE Registro_Treino(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    inicio TEXT NOT NULL,
                    fim TEXT,
                    estado TEXT NOT NULL,
                    id_aluno INTEGER NOT NULL,
                    id_treino INTEGER NOT NULL,
                    FOREIGN KEY(id_aluno) REFERENCES Aluno(id),
                    FOREIGN KEY(id_treino) REFERENCES Treino(id));
                """;

        System.out.println(sql);
        return sql;
    }

    private String criarTabelaExercicio() {
        String sql = """
                CREATE TABLE Exercicio(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome TEXT NOT NULL UNIQUE,
                    grupo_muscular TEXT NOT NULL,
                    descricao TEXT NOT NULL,
                    em_uso INTEGER NOT NULL);
                """;

        System.out.println(sql);
        return sql;
    }

    private String criarTabelaTreino() {
        String sql = """
                CREATE TABLE Treino(
                     id INTEGER PRIMARY KEY AUTOINCREMENT,
                     nome TEXT NOT NULL UNIQUE,
                     observacao TEXT NOT NULL,
                     id_ficha_treino INTEGER NOT NULL,
                     FOREIGN KEY(id_ficha_treino) REFERENCES FichaTreino(id));
                """;

        System.out.println(sql);
        return sql;
    }

    private String criarTabelaFichaTreino() {
        String sql = """
                CREATE TABLE FichaTreino(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    valido INTEGER NOT NULL,
                    dataInicio STRING,
                    validade STRING,
                    id_aluno STRING NOT NULL,
                    id_instrutor STRING,
                    FOREIGN KEY(id_aluno) REFERENCES Usuario(id),
                    FOREIGN KEY(id_instrutor) REFERENCES Usuario(id));
                """;

        System.out.println(sql);
        return sql;
    }

    private String criarTabelaExercicioTreino() {
        String sql = """
                CREATE TABLE ExercicioTreino(
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        serie INTEGER,
                        carga DOUBLE,
                        repeticao INTEGER,
                        id_treino INTEGER NOT NULL,
                        id_exercicio INTEGER NOT NULL,
                        FOREIGN KEY(id_treino) REFERENCES Treino(id),
                        FOREIGN KEY(id_exercicio) REFERENCES Exercicio(id));
                """;

        System.out.println(sql);
        return sql;
    }

}
