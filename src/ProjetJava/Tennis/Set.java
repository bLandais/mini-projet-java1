package ProjetJava.Tennis;

import ProjetJava.ProjetJavaJeuTennis;

import java.util.ArrayList;

/**
 * The type Set.
 */
public class Set {
    private ArrayList<Jeu> jeux = new ArrayList<Jeu>();
    private int nbJeuMiniPourVictoire;
    private Match match;
    private SetStatus status = SetStatus.EnCours;
    private int serviceEquipe;

    /**
     * The enum Set status.
     */
    public enum SetStatus {
        /**
         * En cours set status.
         */
        EnCours,
        /**
         * Gagnant 1 set status.
         */
        Gagnant1,
        /**
         * Gagnant 2 set status.
         */
        Gagnant2,
        /**
         * Jeu decisif set status.
         */
        JeuDecisif
    }

    /**
     * Instantiates a new Set.
     *
     * @param match                 match
     * @param nbJeuMiniPourVictoire nb jeu mini pour victoire
     * @param serviceEquipe         service equipe
     */
    public Set(Match match, int nbJeuMiniPourVictoire, int serviceEquipe) {
        this.match = match;
        this.nbJeuMiniPourVictoire = nbJeuMiniPourVictoire;
        this.serviceEquipe = serviceEquipe;
    }

    /**
     * Jeux gagnes int.
     *
     * @param equipe equipe
     * @return jeuxGagnes
     */
    public int jeuxGagnes(int equipe) {
        int nbGagnes = 0;
        for (int i = 0; i < jeux.size(); i++) {
            if (equipe == 1 & jeux.get(i).getStatus() == Jeu.JeuStatus.Gagnant1) {
                nbGagnes++;
            } else if (equipe == 2 & jeux.get(i).getStatus() == Jeu.JeuStatus.Gagnant2) {
                nbGagnes++;
            }
        }
        return nbGagnes;
    }

    /**
     * Check winner.
     */
    public void checkWinner() {
        int jeuxGagnes1 = this.jeuxGagnes(1);
        int jeuxGagnes2 = this.jeuxGagnes(2);
        if ((jeuxGagnes1 == 7 || jeuxGagnes2 == 7)) {
            if (jeuxGagnes1 == 7) {
                this.status = SetStatus.Gagnant1;
            } else {
                this.status = SetStatus.Gagnant2;
            }
            return;
        } else if (!(jeuxGagnes1 == 6 && jeuxGagnes2 == 6)) {
            int joueurMax = 1;
            int max = jeuxGagnes1;
            if (jeuxGagnes2 > max) {
                joueurMax = 2;
                max = jeuxGagnes2;
            }
            if (max < this.nbJeuMiniPourVictoire) {
                this.status = SetStatus.EnCours;
                return;
            }
            int ecartJeu = Math.abs(jeuxGagnes1 - jeuxGagnes2);
            if (ecartJeu >= 2) {
                if (joueurMax == 1) {
                    this.status = SetStatus.Gagnant1;
                } else {
                    this.status = SetStatus.Gagnant2;
                }
                return;
            } else {
                this.status = SetStatus.EnCours;
                return;
            }
        } else {
            this.status = SetStatus.JeuDecisif;
            return;
        }
    }

    /**
     * Gets match.
     *
     * @return match
     */
    public Match getMatch() {
        return this.match;
    }

    /**
     * Gets status.
     *
     * @return status
     */
    public SetStatus getStatus() {
        return this.status;
    }

    /**
     * Gets jeux.
     *
     * @return jeux
     */
    public ArrayList<Jeu> getJeux() {
        return jeux;
    }

    /**
     * Gets nb jeu mini pour victoire.
     *
     * @return nb jeu mini pour victoire
     */
    public int getNbJeuMiniPourVictoire() {
        return nbJeuMiniPourVictoire;
    }

    /**
     * Gets service equipe.
     *
     * @return service equipe
     */
    public int getServiceEquipe() {
        return serviceEquipe;
    }

    /**
     * Jouer.
     *
     * @param auto the auto
     */
    public void jouer(boolean auto) {
        int currentService = this.serviceEquipe;
        while (this.status == SetStatus.EnCours || this.status == SetStatus.JeuDecisif) {
            Jeu newJeu;
            if (this.status == SetStatus.EnCours) {
                newJeu = new Jeu(this, currentService, false);
            } else {
                newJeu = new Jeu(this, currentService, true);
            }
            this.jeux.add(newJeu);
            newJeu.jouer(auto);
            currentService = ProjetJavaJeuTennis.getOtherEquipe(currentService);
            this.checkWinner();
            if (!auto && this.status == SetStatus.EnCours) {
                this.match.getArbitre().annonceJeu(newJeu);
            }
        }
    }

    /**
     * Gets double fautes count.
     *
     * @param equipe the equipe
     * @return double fautes count
     */
    public int getDoubleFautesCount(int equipe) {
        int count = 0;
        for (int i = 0; i < this.jeux.size(); i++) {
            count += this.jeux.get(i).getDoubleFautesCount(equipe);
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
        for (int i = 0; i < this.jeux.size(); i++) {
            count += this.jeux.get(i).getNbBalles();
        }
        return count;
    }

    /**
     * Get nb echanges int.
     *
     * @return int
     */
    public int getNbEchanges(){
        int count = 0;
        for (int i = 0; i < this.jeux.size(); i++) {
            count += this.jeux.get(i).getNbEchanges();
        }
        return count;
    }

    /**
     * Get nb jeux int.
     *
     * @return int
     */
    public int getNbJeux(){
        return this.jeux.size();
    }
}
