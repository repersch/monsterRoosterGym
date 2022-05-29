module br.edu.ifsp {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires java.sql;
    requires org.xerial.sqlitejdbc;


    opens br.edu.ifsp.application.view;
    opens br.edu.ifsp.application.view.aluno;
    opens br.edu.ifsp.application.view.instrutor;
    opens br.edu.ifsp.application.controller;
    opens br.edu.ifsp.domain.entities to java.base;
    opens br.edu.ifsp.application.controller.instrutor;
    opens br.edu.ifsp.application.controller.aluno;

    exports br.edu.ifsp.domain.entities;
    exports br.edu.ifsp.application.view;
    exports br.edu.ifsp.application.controller;
    exports br.edu.ifsp.application.controller.instrutor;
    exports br.edu.ifsp.application.controller.aluno;
}
