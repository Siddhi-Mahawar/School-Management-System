module School {

    requires javafx.fxml;
    requires javafx.media;
    requires javafx.controls;
    requires java.sql;

    exports students;
    exports Admin;

    opens loginapp;

}