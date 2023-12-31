module projet.tape_bonbon {
    requires javafx.controls;
    requires javafx.fxml;


    opens projet.tape_bonbon to javafx.fxml;
    exports projet.tape_bonbon;
}