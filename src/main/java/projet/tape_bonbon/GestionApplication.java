// La classe principale de l'application qui hérite de la classe Application de JavaFX.
package projet.tape_bonbon;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class GestionApplication extends Application {

    // La méthode start() est appelée lors du lancement de l'application.
    @Override
    public void start(Stage stage) throws IOException {
        // Chargement de l'interface graphique depuis le fichier FXML.
        Parent root = FXMLLoader.load(getClass().getResource("interface-gestion.fxml"));

        // Configuration de la scène avec le titre et la taille initiale.
        stage.setTitle("Gestionnaire stock Tape Bonbon");
        stage.setScene(new Scene(root, 400, 450));

        // Affichage de la scène.
        stage.show();
    }

    // Point d'entrée de l'application.
    public static void main(String[] args) {
        // Lancement de l'application.
        launch(args);
    }
}
