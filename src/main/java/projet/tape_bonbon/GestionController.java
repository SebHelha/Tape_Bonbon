package projet.tape_bonbon;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


public class GestionController implements Initializable {

    @FXML
    private TableView<Stockage> conteneur;//https://www.youtube.com/watch?v=fnU1AlyuguE&list=PLrzWQu7Ajpi26jZvP8JhEJgFPFEj_fojO&index=34&t=1s

    @FXML
    private TableColumn<Stockage, String> prdt;

    @FXML
    private TableColumn<Stockage, Integer> qtt;//Le tableau ne peut pas recevoir de "int", il faut donc ajouter un objet "Integer"

    @FXML
    private TableColumn<Stockage, Integer> ref;

    @FXML
    private TextField qttin;

    @FXML
    private TextField qttout;

    @FXML
    private ChoiceBox<String> selecteur;

    @FXML
    void okPressed(MouseEvent event) {

    }
    ObservableList<Stockage> list = FXCollections.observableArrayList(
            new Stockage("Gauffre au Chocolat", 66529, 10),
            new Stockage("Gauffre au Sucre", 66534, 10),
            new Stockage("Cookie", 24698, 10),
            new Stockage("Oreo", 78126, 10)
    );

    FileChooser fileChooser = new FileChooser();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        prdt.setCellValueFactory(new PropertyValueFactory<>("produit"));
        qtt.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        ref.setCellValueFactory(new PropertyValueFactory<>("reference"));

        conteneur.setItems(list);

        for (Stockage stockage : list){
            selecteur.getItems().add(stockage.getProduit());
        }
    }
}
