package projet.tape_bonbon;

public class Stockage {

    private String produit;
    private int reference;
    private int quantite;

    public Stockage(String produit, int reference, int quantite) {
        this.produit = produit; //Sert a différencier la variable de l'argument
        this.reference = reference;
        this.quantite = quantite;
    }

    public String getProduit() {
        return produit;
    }

    public int getReference() {
        return reference;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
