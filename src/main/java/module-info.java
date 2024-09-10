module com.example.dbproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.dbproject to javafx.fxml;
    exports com.example.dbproject;
}