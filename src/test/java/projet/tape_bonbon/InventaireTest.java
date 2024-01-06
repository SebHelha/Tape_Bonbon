package projet.tape_bonbon;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class InventaireTest {

    @Test
    public void testUpdateQuantities() {
        ObservableList<Stockage> list = FXCollections.observableArrayList(
                new Stockage("Gauffre au Chocolat", "1€", 10),
                new Stockage("Gauffre au Sucre", "1€", 10),
                new Stockage("Cookie", "1,80€", 10),
                new Stockage("Oreo", "1,50€", 10)
        );

        Inventaire.updateQuantities(list, "Cookie", "5", "2");

        // Vérifier que la quantité du produit "Cookie" a été mise à jour correctement
        int updatedQuantity = list.stream()
                .filter(stockage -> stockage.getProduit().equals("Cookie"))
                .findFirst()
                .map(Stockage::getQuantite)
                .orElse(-1);

        assertEquals(13, updatedQuantity);
        System.out.println("Liste après la mise à jour : ");
        for (Stockage stockage : list){
            System.out.println(stockage.getProduit() + " " + stockage.getPrix() + " " + stockage.getQuantite());
        }
    }
}