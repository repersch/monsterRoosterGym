module br.edu.ifsp {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires java.sql;
    requires org.xerial.sqlitejdbc;


    opens br.edu.ifsp.application.view to javafx.fxml;
    opens br.edu.ifsp.application.controller to javafx.fxml;
//    opens br.edu.ifsp.domain.entities.* to javafx.base;

    opens br.edu.ifsp to javafx.fxml;
    exports br.edu.ifsp;
    exports br.edu.ifsp.application.controller;
    exports br.edu.ifsp.application.controller.aluno;
    opens br.edu.ifsp.application.controller.aluno to javafx.fxml;
    exports br.edu.ifsp.application.controller.instrutor;
    opens br.edu.ifsp.application.controller.instrutor to javafx.fxml;
//    exports br.edu.ifsp.application.view;
}
