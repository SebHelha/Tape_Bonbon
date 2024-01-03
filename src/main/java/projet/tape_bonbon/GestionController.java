package projet.tape_bonbon;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class GestionController implements Initializable {

    @FXML
    private TableView<Stockage> conteneur; //https://www.youtube.com/watch?v=fnU1AlyuguE&list=PLrzWQu7Ajpi26jZvP8JhEJgFPFEj_fojO&index=34&t=1s

    @FXML
    private TableColumn<Stockage, String> prdt;

    @FXML
    private TableColumn<Stockage, Integer> qtt;//Le tableau ne peut pas recevoir de "int", il faut donc ajouter un objet "Integer"

    @FXML
    private TableColumn<Stockage, String> prx;

    @FXML
    private TextField qttin;

    @FXML
    private TextField qttout;

    @FXML
    private ChoiceBox<String> selecteur;

    @FXML
    void okPressed(MouseEvent event) {
        String selectedProduit = selecteur.getValue();
        String qttEntranteText = qttin.getText().trim();
        String qttSortanteText = qttout.getText().trim();

        Inventaire.updateQuantities(list, selectedProduit, qttEntranteText, qttSortanteText);

        Inventaire.saveDataToFile(list, "src/main/java/projet/tape_bonbon/Sauvegarde.txt");

        qttin.clear();
        qttout.clear();
    }

    private ObservableList<Stockage> list;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        list = FXCollections.observableArrayList(Inventaire.loadDataFromFile("src/main/java/projet/tape_bonbon/Sauvegarde.txt"));

        prdt.setCellValueFactory(new PropertyValueFactory<>("produit"));
        qtt.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        prx.setCellValueFactory(new PropertyValueFactory<>("prix"));

        conteneur.setItems(list);

        for (Stockage stockage : list){
            selecteur.getItems().add(stockage.getProduit());
        }

        qttin.setTextFormatter(Inventaire.createNumericTextFormatter());
        qttout.setTextFormatter(Inventaire.createNumericTextFormatter());
    }
}
