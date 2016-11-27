package ProjetJava.Tennis;

import ProjetJava.ProjetJavaJeuTennis;

import java.util.ArrayList;

/**
 * The type Jeu.
 */
public class Jeu {
    private PointJeu pointsEquipe1;
    private PointJeu pointsEquipe2;
    private ArrayList<Echange> echanges = new ArrayList<Echange>();
    private JeuStatus jeuStatus = JeuStatus.EnCours;
    private int serviceEquipe;
    private Set set;
    private boolean decisif;

    /**
     * The enum Jeu status.
     */
    public enum JeuStatus {
        /**
         * En cours jeu status.
         */
        EnCours,
        /**
         * Gagnant 1 jeu status.
         */
        Gagnant1,
        /**
         * Gagnant 2 jeu status.
         */
        Gagnant2
    }

    /**
     * Instantiates a new Jeu.
     *
     * @param set           set
     * @param serviceEquipe service equipe
     * @param decisif       decisif
     */
    public Jeu(Set set, int serviceEquipe, boolean decisif) {
        this.set = set;
        this.serviceEquipe = serviceEquipe;
        this.decisif = decisif;
        this.pointsEquipe1 = new PointJeu(0, decisif);
        this.pointsEquipe2 = new PointJeu(0, decisif);
    }

    /**
     * Add point.
     *
     * @param equipe equipe
     */
    public void addPoint(int equipe) {
        if (this.decisif) {
            if (equipe == 1) {
                this.pointsEquipe1 = new PointJeu(this.pointsEquipe1.getPointValue() + 1, true);
            } else {
                this.pointsEquipe2 = new PointJeu(this.pointsEquipe2.getPointValue() + 1, true);
            }
            if (this.pointsEquipe1.getPointValue() >= 7 || this.pointsEquipe2.getPointValue() >= 7) {
                int ecart = this.pointsEquipe1.getPointValue() - this.pointsEquipe2.getPointValue();
                if (Math.abs(ecart) >= 2) {
                    if (ecart > 0) {
                        this.jeuStatus = JeuStatus.Gagnant1;
                    } else {
                        this.jeuStatus = JeuStatus.Gagnant2;
                    }
                }
            }
        } else {
            if (this.jeuStatus == JeuStatus.EnCours) {
                if (equipe == 1) {
                    PointJeu[] result = nextPoint(this.pointsEquipe1, this.pointsEquipe2);
                    if (result == null) {
                        this.jeuStatus = JeuStatus.Gagnant1;
                    } else {
                        this.pointsEquipe1 = result[0];
                        this.pointsEquipe2 = result[1];
                    }
                } else if (equipe == 2) {
                    PointJeu[] result = nextPoint(this.pointsEquipe2, this.pointsEquipe1);
                    if (result == null) {
                        this.jeuStatus = JeuStatus.Gagnant2;
                    } else {
                        this.pointsEquipe1 = result[1];
                        this.pointsEquipe2 = result[0];
                    }
                }
            }
        }
    }

    private static PointJeu[] nextPoint(PointJeu pointsActuels1, PointJeu pointsActuels2) {
        String pts1 = pointsActuels1.toString();
        String pts2 = pointsActuels2.toString();
        if (pts2 != "AV") {
            if (pts2 != "40") {
                if (pts1 == "40") {
                    return null;
                } else {
                    pointsActuels1 = new PointJeu(pointsActuels1.getPointValue() + 1, false);
                }
            } else {
                if (pts1 == "AV") {
                    return null;
                } else {
                    pointsActuels1 = new PointJeu(pointsActuels1.getPointValue() + 1, false);
                }
            }
        } else {
            pointsActuels2 = new PointJeu(pointsActuels2.getPointValue() - 1, false);
        }
        return new PointJeu[]{pointsActuels1, pointsActuels2};
    }

    /**
     * Gets points equipe.
     *
     * @param equipe equipe
     * @return points equipe
     */
    public PointJeu getPointsEquipe(int equipe) {
        if (equipe == 1) {
            return this.pointsEquipe1;
        } else {
            return this.pointsEquipe2;
        }
    }

    /**
     * Gets echanges.
     *
     * @return echanges
     */
    public ArrayList<Echange> getEchanges() {
        return this.echanges;
    }

    /**
     * Gets status.
     *
     * @return status
     */
    public JeuStatus getStatus() {
        return this.jeuStatus;
    }

    /**
     * Gets service equipe.
     *
     * @return service equipe
     */
    public int getServiceEquipe() {
        return this.serviceEquipe;
    }

    /**
     * Gets set.
     *
     * @return set
     */
    public Set getSet() {
        return this.set;
    }

    /**
     * Is decisif boolean.
     *
     * @return the boolean
     */
    public boolean isDecisif() {
        return decisif;
    }

    /**
     * Jouer.
     *
     * @param auto the auto
     */
    public void jouer(boolean auto) {
        int currentJoueur = this.serviceEquipe;
        int decisifChangeJoueur = 1;
        while (this.jeuStatus == JeuStatus.EnCours) {
            Echange newEchange;
            newEchange = new Echange(this, currentJoueur);
            this.echanges.add(newEchange);
            newEchange.jouer(auto);
            if (newEchange.getStatus() == Echange.EchangeStatus.Gagnant1) {
                this.addPoint(1);
            } else if (newEchange.getStatus() == Echange.EchangeStatus.Gagnant2) {
                this.addPoint(2);
            }
            if (!(newEchange.getStatus() == Echange.EchangeStatus.Annule) && this.decisif) {
                decisifChangeJoueur = (decisifChangeJoueur + 1) % 2;
                if (decisifChangeJoueur == 0) {
                    currentJoueur = ProjetJavaJeuTennis.getOtherEquipe(currentJoueur);
                }
            }
            if (!auto && this.jeuStatus == JeuStatus.EnCours) {
                this.set.getMatch().getArbitre().annonceEchange(newEchange);
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
        for (int i = 0; i < this.echanges.size(); i++) {
            if (this.echanges.get(i).isDoubleFaute() && this.echanges.get(i).getCurrentJoueur() == equipe) {
                count++;
            }
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
        for (int i = 0; i < this.echanges.size(); i++) {
            count += this.echanges.get(i).getNbBalles();
        }
        return count;
    }

    /**
     * Get nb echanges.
     *
     * @return nb echanges
     */
    public int getNbEchanges(){
        return this.echanges.size();
    }
}
