module br.edu.ifsp {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires java.sql;
    requires org.xerial.sqlitejdbc;


    opens br.edu.ifsp.application.view to javafx.fxml;
    opens br.edu.ifsp.application.controller to javafx.fxml;
    opens br.edu.ifsp.application.controller.instrutor to javafx.fxml;
    opens br.edu.ifsp.application.controller.aluno to javafx.fxml;
    opens br.edu.ifsp.domain.entities to java.base;
    opens br.edu.ifsp.domain.usecases.autenticar to java.base;
    opens br.edu.ifsp.domain.usecases.usuario to java.base;
    opens br.edu.ifsp.domain.usecases.fichaTreino to java.base;

    exports br.edu.ifsp.application.controller;
    exports br.edu.ifsp.domain.entities;
    exports br.edu.ifsp.application.view;
    exports br.edu.ifsp.domain.usecases.fichaTreino;
    exports br.edu.ifsp.domain.usecases.usuario;
    exports br.edu.ifsp.domain.usecases.autenticar;
    exports br.edu.ifsp.domain.usecases.exercicio;
    exports br.edu.ifsp.application.controller.instrutor;
    exports br.edu.ifsp.application.controller.aluno;
}