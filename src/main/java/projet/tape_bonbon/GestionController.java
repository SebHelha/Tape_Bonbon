// Contrôleur pour l'interface graphique définie dans le fichier FXML.
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

    // Annotations FXML indiquant les composants de l'interface graphique.
    @FXML
    protected TableView<Stockage> conteneur;
    @FXML
    protected TableColumn<Stockage, String> prdt;
    @FXML
    protected TableColumn<Stockage, Integer> qtt;
    @FXML
    protected TableColumn<Stockage, String> prx;
    @FXML
    protected TextField qttin;
    @FXML
    protected TextField qttout;
    @FXML
    protected ChoiceBox<String> selecteur;

    // Liste observable pour stocker les données.
    protected ObservableList<Stockage> list;

    // Méthode appelée lors de l'initialisation du contrôleur.
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Chargement des données depuis un fichier dans la liste observable.
        list = FXCollections.observableArrayList(Sauvegarde.loadDataFromFile(Sauvegarde.routeFichier));

        // Liaison des colonnes de la table avec les propriétés de l'objet Stockage.
        prdt.setCellValueFactory(new PropertyValueFactory<>("produit"));
        qtt.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        prx.setCellValueFactory(new PropertyValueFactory<>("prix"));

        // Affichage des données dans la table.
        conteneur.setItems(list);

        // Remplissage de la ChoiceBox avec les produits existants.
        for (Stockage stockage : list){
            selecteur.getItems().add(stockage.getProduit());
        }

        // Configuration des champs de texte pour n'accepter que des valeurs numériques.
        qttin.setTextFormatter(Inventaire.createNumericTextFormatter());
        qttout.setTextFormatter(Inventaire.createNumericTextFormatter());
    }

    // Méthode appelée lors du clic sur le bouton "Ok".
    @FXML
    void okPressed(MouseEvent event) {
        // Récupération des valeurs sélectionnées et entrées par l'utilisateur.
        String selectedProduit = selecteur.getValue();
        String qttEntranteText = qttin.getText().trim();
        String qttSortanteText = qttout.getText().trim();

        // Mise à jour des quantités dans la liste.
        Inventaire.updateQuantities(list, selectedProduit, qttEntranteText, qttSortanteText);

        // Sauvegarde des données dans le fichier.
        Sauvegarde.saveDataToFile(list, Sauvegarde.routeFichier);

        // Effacement des champs de texte après la mise à jour.
        qttin.clear();
        qttout.clear();
    }
}
