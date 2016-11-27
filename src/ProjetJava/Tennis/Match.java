package ProjetJava.Tennis;

import ProjetJava.Personnes.Arbitre;
import ProjetJava.Personnes.Joueur;
import ProjetJava.Personnes.Spectateur;
import ProjetJava.ProjetJavaJeuTennis;

import java.util.ArrayList;

/**
 * The type Match.
 */
public class Match {
    private ArrayList<Joueur> equipe1 = new ArrayList<Joueur>();
    private ArrayList<Joueur> equipe2 = new ArrayList<Joueur>();
    private ArrayList<Set> sets = new ArrayList<Set>();
    private ArrayList<Spectateur> spectateurs = new ArrayList<Spectateur>();
    private Arbitre arbitre;
    private MatchStatus status = MatchStatus.EnCours;
    private int tour;
    private int nbSetsPourGagner;

    /**
     * The enum Match status.
     */
    public enum MatchStatus {
        /**
         * En cours match status.
         */
        EnCours, /**
         * Gagnant 1 match status.
         */
        Gagnant1, /**
         * Gagnant 2 match status.
         */
        Gagnant2
    }

    /**
     * Instantiates a new Match.
     *
     * @param arbitre          arbitre
     * @param nbSetsPourGagner nb sets pour gagner
     * @param tour             tour
     */
    public Match(Arbitre arbitre, int nbSetsPourGagner, int tour) {
        this.arbitre = arbitre;
        this.nbSetsPourGagner = nbSetsPourGagner;
        this.tour = tour;
    }

    /**
     * Add joueurs.
     *
     * @param j1 j1
     * @param j2 j2
     */
    public void addJoueurs(Joueur j1, Joueur j2) {
        // Match simple
        this.equipe1.add(j1);
        this.equipe2.add(j2);
    }

    /**
     * Add joueurs.
     *
     * @param j1 j1
     * @param j2 j2
     * @param j3 j3
     * @param j4 j4
     */
    public void addJoueurs(Joueur j1, Joueur j2, Joueur j3, Joueur j4) {
        // Match double
        this.equipe1.add(j1);
        this.equipe1.add(j2);
        this.equipe2.add(j3);
        this.equipe2.add(j4);
    }

    /**
     * Add spectateur.
     *
     * @param spec the spec
     */
    public void addSpectateur(Spectateur spec) {
        this.spectateurs.add(spec);
    }

    /**
     * Check winner.
     */
    public void checkWinner() {
        int score1 = this.getScore(1);
        int score2 = this.getScore(2);
        if (score1 >= this.nbSetsPourGagner) {
            this.status = MatchStatus.Gagnant1;
        } else if (score2 >= this.nbSetsPourGagner) {
            this.status = MatchStatus.Gagnant2;
        } else {
            this.status = MatchStatus.EnCours;
        }
    }

    /**
     * Jouer.
     *
     * @param auto auto
     */
    public void jouer(boolean auto) {
        int currentService = 1;
        if (!auto) {
            System.out.println("Le match opposant " + this.getNomEquipe(1) + " et " + this.getNomEquipe(2) + " va commencer...");
        }
        while (this.status == MatchStatus.EnCours) {
            Set newSet = new Set(this, 6, currentService);
            this.sets.add(newSet);
            newSet.jouer(auto);
            currentService = ProjetJavaJeuTennis.getOtherEquipe(currentService);
            checkWinner();
            if (!auto && this.status == MatchStatus.EnCours) {
                this.arbitre.annonceSet(newSet);
            }
        }
        if (this.status == MatchStatus.Gagnant1) {
            System.out.println("Match remporté par " + this.getNomEquipe(1));
        } else {
            System.out.println("Match remporté par " + this.getNomEquipe(2));
        }
        String recapJeuxEquipe1 = "";
        for (int i = 0; i < this.sets.size(); i++) {
            recapJeuxEquipe1 = recapJeuxEquipe1 + " " + this.sets.get(i).jeuxGagnes(1);
        }
        System.out.println(this.getNomEquipe(1) + " " + recapJeuxEquipe1);
        String recapJeuxEquipe2 = "";
        for (int i = 0; i < this.sets.size(); i++) {
            recapJeuxEquipe2 = recapJeuxEquipe2 + " " + this.sets.get(i).jeuxGagnes(2);
        }
        System.out.println(this.getNomEquipe(2) + " " + recapJeuxEquipe2);
    }

    /**
     * Gets score.
     *
     * @param equipe equipe
     * @return score
     */
    public int getScore(int equipe) {
        int scoreCount = 0;
        for (int i = 0; i < this.sets.size(); i++) {
            if (equipe == 1 & this.sets.get(i).getStatus() == Set.SetStatus.Gagnant1) {
                scoreCount++;
            } else if (equipe == 2 & this.sets.get(i).getStatus() == Set.SetStatus.Gagnant2) {
                scoreCount++;
            }
        }
        return scoreCount;
    }

    /**
     * Gets equipe 1.
     *
     * @return equipe 1
     */
    public ArrayList<Joueur> getEquipe1() {
        return this.equipe1;
    }

    /**
     * Gets equipe 2.
     *
     * @return equipe 2
     */
    public ArrayList<Joueur> getEquipe2() {
        return this.equipe2;
    }

    /**
     * Gets sets.
     *
     * @return sets
     */
    public ArrayList<Set> getSets() {
        return this.sets;
    }

    /**
     * Gets arbitre.
     *
     * @return arbitre
     */
    public Arbitre getArbitre() {
        return this.arbitre;
    }

    /**
     * Gets tour.
     *
     * @return tour
     */
    public int getTour() {
        return this.tour;
    }

    /**
     * Gets nom equipe.
     *
     * @param equipe equipe
     * @return nom equipe
     */
    public String getNomEquipe(int equipe) {
        String nomEquipe = "";
        ArrayList<Joueur> currentEquipe = null;
        if (equipe == 1) {
            currentEquipe = this.equipe1;
        } else {
            currentEquipe = this.equipe2;
        }
        if (currentEquipe.size() > 0) {
            nomEquipe = currentEquipe.get(0).getNom();
            if (currentEquipe.size() > 1) {
                nomEquipe = nomEquipe + "/" + currentEquipe.get(1).getNom();
            }
        }
        return nomEquipe;
    }

    /**
     * Gets spectateurs.
     *
     * @return spectateurs
     */
    public ArrayList<Spectateur> getSpectateurs() {
        return spectateurs;
    }

    /**
     * Gets nb sets pour gagner.
     *
     * @return nb sets pour gagner
     */
    public int getNbSetsPourGagner() {
        return nbSetsPourGagner;
    }

    /**
     * Gets status.
     *
     * @return status
     */
    public MatchStatus getStatus() {
        return status;
    }

    /**
     * Gets stats.
     *
     * @return stats
     */
    public String getStats() {
        String stats = "Match opposant " + this.getNomEquipe(1) + " à " + this.getNomEquipe(2) + "\r\n";
        if (this.status == MatchStatus.Gagnant1) {
            stats = stats + "Match remporté par " + this.getNomEquipe(1) + "\r\n";
        } else {
            stats = stats + "Match remporté par " + this.getNomEquipe(2) + "\r\n";
        }
        String recapJeuxEquipe1 = "";
        for (int i = 0; i < this.sets.size(); i++) {
            recapJeuxEquipe1 = recapJeuxEquipe1 + " " + this.sets.get(i).jeuxGagnes(1);
        }
        stats = stats + this.getNomEquipe(1) + " " + recapJeuxEquipe1 + "\r\n";
        String recapJeuxEquipe2 = "";
        for (int i = 0; i < this.sets.size(); i++) {
            recapJeuxEquipe2 = recapJeuxEquipe2 + " " + this.sets.get(i).jeuxGagnes(2);
        }
        stats = stats + this.getNomEquipe(2) + " " + recapJeuxEquipe2 + "\r\n";
        stats = stats + "Nombre de coups : " + this.getNbBalles() + "\r\n";
        stats = stats + "Nombre d'échanges : " + this.getNbEchanges() + "\r\n";
        stats = stats + "Nombre de jeux : " + this.getNbJeux() + "\r\n";
        stats = stats + "Nombre de sets : " + this.sets.size() + "\r\n";
        stats = stats + "Doubles fautes : " + this.getNomEquipe(1) + " " + this.getDoubleFautesCount(1) + " | " + this.getNomEquipe(2) + " " + this.getDoubleFautesCount(2);
        return stats;
    }

    /**
     * Gets double fautes count.
     *
     * @param equipe equipe
     * @return double fautes count
     */
    public int getDoubleFautesCount(int equipe) {
        int count = 0;
        for (int i = 0; i < this.sets.size(); i++) {
            count += this.sets.get(i).getDoubleFautesCount(equipe);
        }
        return count;
    }

    /**
     * Gets nb balles.
     *
     * @return nb balles
     */
    public int getNbBalles() {
        int count = 0;
        for (int i = 0; i < this.sets.size(); i++) {
            count += this.sets.get(i).getNbBalles();
        }
        return count;
    }

    /**
     * Gets nb echanges.
     *
     * @return nb echanges
     */
    public int getNbEchanges() {
        int count = 0;
        for (int i = 0; i < this.sets.size(); i++) {
            count += this.sets.get(i).getNbEchanges();
        }
        return count;
    }

    /**
     * Gets nb jeux.
     *
     * @return nb jeux
     */
    public int getNbJeux() {
        int count = 0;
        for (int i = 0; i < this.sets.size(); i++) {
            count += this.sets.get(i).getNbJeux();
        }
        return count;
    }
}
