// Classe gérant les opérations d'inventaire.
package projet.tape_bonbon;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextFormatter;
import java.io.*;
import java.util.function.UnaryOperator;

public class Inventaire {

    // Méthode pour la mise à jour des quantités de produits dans le tableau.
    public static void updateQuantities(ObservableList<Stockage> list, String selectedProduit, String qttEntranteText, String qttSortanteText) {

        // Vérification de la validité du produit sélectionné.
        if (selectedProduit == null || selectedProduit.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Veuillez sélectionner un produit.", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        // Vérification des champs de quantité entrante et sortante.
        if (qttEntranteText.isEmpty() && qttSortanteText.isEmpty()) {
            return;
        }

        // Conversion des chaînes de texte en entiers.
        int qttEntrante = (qttEntranteText.isEmpty()) ? 0 : Integer.parseInt(qttEntranteText);
        int qttSortante = (qttSortanteText.isEmpty()) ? 0 : Integer.parseInt(qttSortanteText);

        // Recherche de l'objet Stockage correspondant au produit sélectionné.
        Stockage selectedStockage = null;
        for (Stockage stockage : list) {
            if (stockage.getProduit().equals(selectedProduit)) {
                selectedStockage = stockage;
                break;
            }
        }

        // Calcul de la nouvelle quantité.
        int newQuantite = selectedStockage.getQuantite() + qttEntrante - qttSortante;

        // Vérification de la validité de la nouvelle quantité.
        if (newQuantite < 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "La quantité ne peut pas être négative. Veuillez vérifier les entrées et sorties.", ButtonType.OK);
            alert.showAndWait();
        } else {
            // Mise à jour de la quantité dans l'objet Stockage.
            selectedStockage.setQuantite(newQuantite);

            // Mise à jour de la liste observable.
            for (Stockage stockage : list) {
                if (stockage.getProduit().equals(selectedStockage.getProduit())) {
                    list.set(list.indexOf(stockage), selectedStockage);
                    break;
                }
            }
        }
    }

    // Méthode pour la protection contre l'écriture de caractères non numériques.
    public static TextFormatter<String> createNumericTextFormatter() {
        UnaryOperator<TextFormatter.Change> filtre = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")) {
                return change;
            }
            return null;
        };

        return new TextFormatter<>(filtre);
    }
}
