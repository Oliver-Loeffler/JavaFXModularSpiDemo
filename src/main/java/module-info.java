module playground.javafx {
	requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
	requires java.sql;

    uses java.sql.Driver;
    
    opens playground.javafx to javafx.fxml;
    exports playground.javafx;
}