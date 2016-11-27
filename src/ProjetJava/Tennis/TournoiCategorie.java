package ProjetJava.Tennis;

import ProjetJava.Personnes.Arbitre;
import ProjetJava.Personnes.Joueur;
import ProjetJava.Personnes.PersonneGenre;
import ProjetJava.Personnes.Spectateur;
import ProjetJava.ProjetJavaJeuTennis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


/**
 * The type Tournoi categorie.
 */
public class TournoiCategorie {
    private Categorie categorie;
    private Tournoi tournoi;
    private ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
    private ArrayList<Tour> tours = new ArrayList<Tour>();

    /**
     * Instantiates a new Tournoi categorie.
     *
     * @param tournoi   tournoi
     * @param categorie categorie
     */
    public TournoiCategorie(Tournoi tournoi, Categorie categorie) {
        this.tournoi = tournoi;
        this.categorie = categorie;
        this.generateJoueurs();
        this.generateTours();
        this.generateSpectateurs();
    }

    private void generateTours() {
        Random rdm = new Random();
        int nbSetsPourGagner = 2;
        if (this.categorie.getGenre() == CategorieGenre.Homme) {
            nbSetsPourGagner = 3;
        }
        ArrayList<Arbitre> arbitres = this.tournoi.getArbitres();
        ArrayList<Joueur> randomSortJoueurs = new ArrayList<Joueur>();
        for (int i = 0; i < this.joueurs.size(); i++) {
            randomSortJoueurs.add(this.joueurs.get(i));
        }
        Collections.shuffle(randomSortJoueurs);
        // 1er tour
        int currentMatchCount = this.joueurs.size() / 2;
        int currentTour = 1;
        Tour premierTour = new Tour(currentTour);
        for (int i = 0; i < currentMatchCount; i++) {
            Match newMatch = new Match(arbitres.get(rdm.nextInt(arbitres.size())), nbSetsPourGagner, currentTour);
            newMatch.addJoueurs(randomSortJoueurs.get(2 * i), randomSortJoueurs.get(2 * i + 1));
            premierTour.addMatch(newMatch);
        }
        this.tours.add(premierTour);
        currentMatchCount = currentMatchCount / 2;
        currentTour++;
        while (currentMatchCount > 0) {
            Tour newTour = new Tour(currentTour);
            for (int i = 0; i < currentMatchCount; i++) {
                Match newMatch = new Match(arbitres.get(rdm.nextInt(arbitres.size())), nbSetsPourGagner, currentTour);
                newTour.addMatch(newMatch);
            }
            this.tours.add(newTour);
            currentMatchCount = currentMatchCount / 2;
            currentTour++;
        }
    }

    private void generateJoueurs() {
        int count = 128;
        PersonneGenre joueurGenre = PersonneGenre.Femme;
        if (this.categorie.getGenre() == CategorieGenre.Homme) {
            joueurGenre = PersonneGenre.Homme;
        }
        for (int i = 0; i < count; i++) {
            Joueur newJoueur = new Joueur(ProjetJavaJeuTennis.getRandomName(), ProjetJavaJeuTennis.getRandomName(), ProjetJavaJeuTennis.getRandomName(), ProjetJavaJeuTennis.getRandomDateNaissance(), "Paris", null, "Francaise", ProjetJavaJeuTennis.getRandomTaille(), ProjetJavaJeuTennis.getRandomPoids(), joueurGenre, null, ProjetJavaJeuTennis.getRandomMainJeu(), "Pas de sponsor", i + 1, null, ProjetJavaJeuTennis.getRandomCouleur(), ProjetJavaJeuTennis.getRandomReputation());
            this.joueurs.add(newJoueur);
        }
    }

    private void generateSpectateurs() {
        for (int i = 0; i < this.tours.size(); i++) {
            Tour currentTour = this.tours.get(i);
            for (int j = 0; j < currentTour.getMatchs().size(); j++) {
                Match currentMatch = currentTour.getMatchs().get(j);
                char nomTribune = 'A';
                for (int n = 0; n < 4; n++) {
                    for (int s = 0; s < 25; s++) {
                        Spectateur newSpectateur = new Spectateur(nomTribune + "", s + 1, ProjetJavaJeuTennis.getRandomBoolean(), ProjetJavaJeuTennis.getRandomCouleur());
                        currentMatch.addSpectateur(newSpectateur);
                    }
                    nomTribune++;
                }
            }
        }
    }

    /**
     * Gets tournoi.
     *
     * @return tournoi
     */
    public Tournoi getTournoi() {
        return tournoi;
    }

    /**
     * Gets categorie.
     *
     * @return categorie
     */
    public Categorie getCategorie() {
        return this.categorie;
    }

    /**
     * Jouer.
     */
    public void jouer() {
        System.out.println("Catégorie " + this.categorie.toString() + " : le tournoi va commencer !");
        System.out.println("Le premier tour sera joué automatiquement. Prêt ?");
        ProjetJavaJeuTennis.saisieUser();
        for (int i = 0; i < this.tours.size(); i++) {
            Tour currentTour = this.tours.get(i);
            System.out.println();
            System.out.println(currentTour.toString());
            if (i == 0) {
                currentTour.jouer(1);
            } else {
                System.out.println("Comment souhaitez-vous jouer ce tour ?");
                System.out.println("(0 = manuellement, 1 = automatiquement, 2 = avoir le choix pour chaque match)");
                int saisie = Integer.parseInt(ProjetJavaJeuTennis.saisieUser("0", "1", "2"));
                currentTour.jouer(saisie);
            }
            // Ajout des gagnants du tour actuel dans les matchs du tour suivant
            if (i + 1 < this.tours.size()) {
                Tour nextTour = this.tours.get(i + 1);
                for (int j = 0; j < nextTour.getMatchs().size(); j++) {
                    Match currentMatch = nextTour.getMatchs().get(j);
                    Joueur j1;
                    Joueur j2;
                    if (currentTour.getMatchs().get(2 * j).getStatus() == Match.MatchStatus.Gagnant1) {
                        j1 = currentTour.getMatchs().get(2 * j).getEquipe1().get(0);
                    } else {
                        j1 = currentTour.getMatchs().get(2 * j).getEquipe2().get(0);
                    }
                    if (currentTour.getMatchs().get(2 * j + 1).getStatus() == Match.MatchStatus.Gagnant1) {
                        j2 = currentTour.getMatchs().get(2 * j + 1).getEquipe1().get(0);
                    } else {
                        j2 = currentTour.getMatchs().get(2 * j + 1).getEquipe2().get(0);
                    }
                    currentMatch.addJoueurs(j1, j2);
                }
            }
        }
    }

    /**
     * Gets stats.
     *
     * @return stats
     */
    public String getStats() {
        String stats = "Catégorie " + this.categorie.toString() + "\r\n";
        for (int i = 0; i < this.tours.size(); i++) {
            stats = stats + this.tours.get(i).getStats() + "\r\n" + "\r\n";
        }
        return stats;
    }
}
