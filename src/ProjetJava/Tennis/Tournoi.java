package ProjetJava.Tennis;

import ProjetJava.Personnes.Arbitre;
import ProjetJava.ProjetJavaJeuTennis;

import java.util.ArrayList;

/**
 * The type Tournoi.
 */
public class Tournoi {
    private String ville;
    private TournoiSurfaceTerrain surface;
    private ArrayList<TournoiCategorie> categories = new ArrayList<TournoiCategorie>();
    private ArrayList<Arbitre> arbitres = new ArrayList<Arbitre>();

    /**
     * Instantiates a new Tournoi.
     *
     * @param ville   ville
     * @param surface surface
     */
    public Tournoi(String ville, TournoiSurfaceTerrain surface) {
        this.ville = ville;
        this.surface = surface;
        System.out.println("Bienvenue dans le tournoi de tennis de " + this.ville);
        this.generateArbitres(10);
        this.addCategorie(new Categorie(CategorieGenre.Homme, CategorieSimpleDouble.Simple));
        this.addCategorie(new Categorie(CategorieGenre.Femme, CategorieSimpleDouble.Simple));
    }

    private void generateArbitres(int count) {
        System.out.println("Génération de " + count + " arbitres pour le tournoi:");
        for (int i = 0; i < count; i++) {
            Arbitre newArbitre = new Arbitre(ProjetJavaJeuTennis.getRandomName(), ProjetJavaJeuTennis.getRandomName(), ProjetJavaJeuTennis.getRandomName(), ProjetJavaJeuTennis.getRandomDateNaissance(), "Paris", null, "Francaise", ProjetJavaJeuTennis.getRandomTaille(), ProjetJavaJeuTennis.getRandomPoids(), ProjetJavaJeuTennis.getRandomGenre(), null);
            System.out.println("\t - " + newArbitre.getPrenom() + " " + newArbitre.getNom());
            this.arbitres.add(newArbitre);
        }
    }

    /**
     * Add categorie.
     *
     * @param categorie categorie
     */
    public void addCategorie(Categorie categorie) {
        this.categories.add(new TournoiCategorie(this, categorie));
    }

    /**
     * Gets ville.
     *
     * @return ville
     */
    public String getVille() {
        return this.ville;
    }

    /**
     * Gets arbitres.
     *
     * @return arbitres
     */
    public ArrayList<Arbitre> getArbitres() {
        return arbitres;
    }

    /**
     * Gets categories.
     *
     * @return categories
     */
    public ArrayList<TournoiCategorie> getCategories() {
        return categories;
    }

    /**
     * Gets surface.
     *
     * @return surface
     */
    public TournoiSurfaceTerrain getSurface() {
        return this.surface;
    }

    /**
     * Jouer.
     */
    public void jouer() {
        for (int i = 0; i < this.categories.size(); i++) {
            this.categories.get(i).jouer();
        }
    }

    /**
     * Gets stats.
     *
     * @return stats
     */
    public String getStats() {
        String stats = "RESUME DU TOURNOI" + "\r\n";
        for (int i = 0; i < this.categories.size(); i++) {
            stats = stats + this.categories.get(i).getStats() + "\r\n" + "\r\n";
        }
        return stats;
    }
}
