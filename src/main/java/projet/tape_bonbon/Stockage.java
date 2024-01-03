package projet.tape_bonbon;

public class Stockage {

    private String produit;
    private String prix;
    private int quantite;

    public Stockage(String produit, String prix, int quantite) {
        this.produit = produit; //Sert a différencier la variable de l'argument
        this.prix = prix;
        this.quantite = quantite;
    }

    public String getProduit() {
        return produit;
    }

    public String getPrix() {
        return prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
