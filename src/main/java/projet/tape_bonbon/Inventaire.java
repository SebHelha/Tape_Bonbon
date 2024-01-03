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

        if (selectedStockage != null) {
            updateQuantities(selectedStockage, qttEntrante, qttSortante, list);

        }
    }

    private static void updateQuantities(Stockage selectedStockage, int qttEntrante, int qttSortante, ObservableList<Stockage> list) {
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
    public static TextFormatter<String> createNumericTextFormatter() {
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")) {
                return change;
            }
            return null;
        };

        return new TextFormatter<>(filter);
    }
    private static final String routeFichier = "src/main/java/projet/tape_bonbon/Sauvegarde.txt";

    // Charger la liste depuis le fichier texte
    public static List<Stockage> loadDataFromFile(String s) {
        List<Stockage> loadedList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(routeFichier))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String produit = line.trim();
                String prix = reader.readLine().trim();
                int quantite = Integer.parseInt(reader.readLine().trim());

                loadedList.add(new Stockage(produit, prix, quantite));
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return loadedList;
    }

    // Sauvegarder la liste dans le fichier texte
    public static void saveDataToFile(List<Stockage> list, String s) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(routeFichier))) {
            for (Stockage stockage : list) {
                writer.write(stockage.getProduit());
                writer.newLine();
                writer.write(stockage.getPrix());
                writer.newLine();
                writer.write(String.valueOf(stockage.getQuantite()));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
