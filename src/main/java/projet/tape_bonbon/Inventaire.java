package projet.tape_bonbon;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextFormatter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

public class Inventaire {

    //Méthode pour la mise à jours des quantités de produits dans le tableau:
    public static void updateQuantities(ObservableList<Stockage> list, String selectedProduit, String qttEntranteText, String qttSortanteText) {

        if (selectedProduit == null || selectedProduit.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Veuillez sélectionner un produit.", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        if (qttEntranteText.isEmpty() && qttSortanteText.isEmpty()) {
            return;
        }

        int qttEntrante = (qttEntranteText.isEmpty()) ? 0 : Integer.parseInt(qttEntranteText);
        int qttSortante = (qttSortanteText.isEmpty()) ? 0 : Integer.parseInt(qttSortanteText);

        Stockage selectedStockage = null;
        for (Stockage stockage : list) {
            if (stockage.getProduit().equals(selectedProduit)) {
                selectedStockage = stockage;
                break;
            }
        }

        int newQuantite = selectedStockage.getQuantite() + qttEntrante - qttSortante;

        if (newQuantite < 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING,"La quantitée ne peut pas être négative. Veuillez vérifier les entrées et sorties", ButtonType.OK);
            alert.showAndWait();
        } else {
            selectedStockage.setQuantite(newQuantite);

            for (Stockage stockage : list) {
                if (stockage.getProduit().equals(selectedStockage.getProduit())) {
                    list.set(list.indexOf(stockage), selectedStockage);
                    break;
                }
            }
        }
    }


    //Méthode pour la protection contre les l'écriture de caractères:
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
