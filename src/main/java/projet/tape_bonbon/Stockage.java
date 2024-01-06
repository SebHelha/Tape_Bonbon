// Classe représentant les informations sur le stock d'un produit.
package projet.tape_bonbon;

public class Stockage {

    // Attributs représentant les propriétés du stock.
    private String produit;
    private String prix;
    private int quantite;

    // Constructeur pour initialiser les propriétés du stock.
    public Stockage(String produit, String prix, int quantite) {
        this.produit = produit;
        this.prix = prix;
        this.quantite = quantite;
    }

    // Méthodes d'accès aux propriétés du stock.
    public String getProduit() {
        return produit;
    }

    public String getPrix() {
        return prix;
    }

    public int getQuantite() {
        return quantite;
    }

    // Méthode pour mettre à jour la quantité du stock.
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
