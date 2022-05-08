module br.edu.ifsp {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires java.sql;
    requires org.xerial.sqlitejdbc;

    opens br.edu.ifsp to javafx.fxml;
    exports br.edu.ifsp;
}
