package projet.tape_bonbon;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Sauvegarde {
    public static final String routeFichier = "src/main/java/projet/tape_bonbon/Sauvegarde.txt";

    //Méthode de chargement des données depuis le fichier txt
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

    //Méthode de sauvegarde des éléments dans le fichier txt
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
