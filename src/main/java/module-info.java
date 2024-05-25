module com.example.simulador {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.simuladorv to javafx.fxml;
    exports com.example.simuladorv;
}
