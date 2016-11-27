package ProjetJava.Tennis;

import ProjetJava.Personnes.Joueur;
import ProjetJava.ProjetJavaJeuTennis;

import java.util.Random;

/**
 * The type Echange.
 */
public class Echange {
    private boolean isService = true;
    private EchangeStatus status = EchangeStatus.EnCours;
    private int balleService = 0;
    private int currentJoueur;
    private Jeu jeu;
    private int nbBalles = 0;
    private boolean doubleFaute = false;

    /**
     * The enum Echange status.
     */
    public enum EchangeStatus {
        /**
         * En cours echange status.
         */
        EnCours,
        /**
         * Gagnant 1 echange status.
         */
        Gagnant1,
        /**
         * Gagnant 2 echange status.
         */
        Gagnant2,
        /**
         * Annule echange status.
         */
        Annule
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public EchangeStatus getStatus() {
        return this.status;
    }

    /**
     * Instantiates a new Echange.
     *
     * @param jeu           jeu
     * @param currentJoueur current joueur
     */
    public Echange(Jeu jeu, int currentJoueur) {
        this.jeu = jeu;
        this.currentJoueur = jeu.getServiceEquipe();
    }

    /**
     * The enum Echange evenement.
     */
    public enum EchangeEvenement {
        /**
         * Faute echange evenement.
         */
        Faute,
        /**
         * Filet echange evenement.
         */
        Filet,
        /**
         * Correct echange evenement.
         */
        Correct,
        /**
         * Manquee echange evenement.
         */
        Manquee
    }

    /**
     * Evenement.
     *
     * @param event the event
     */
    public void Evenement(EchangeEvenement event) {
        if (isService) {
            if (event == EchangeEvenement.Faute || event == EchangeEvenement.Manquee) {
                if (this.balleService >= 1) {
                    this.doubleFaute = true;
                    if (this.currentJoueur == 1) {
                        this.status = EchangeStatus.Gagnant2;
                    } else {
                        this.status = EchangeStatus.Gagnant1;
                    }
                } else {
                    this.balleService += 1;
                }
            } else if (event == EchangeEvenement.Correct) {
                isService = false;
                this.changerJoueur();
                this.nbBalles++;
            }
        } else {
            if (event == EchangeEvenement.Correct || event == EchangeEvenement.Filet) {
                this.changerJoueur();
                this.nbBalles++;
            } else if (event == EchangeEvenement.Manquee || event == EchangeEvenement.Faute) {
                if (this.currentJoueur == 1) {
                    this.status = EchangeStatus.Gagnant2;
                } else {
                    this.status = EchangeStatus.Gagnant1;
                }
            }
        }
    }

    /**
     * Changer joueur.
     */
    public void changerJoueur() {
        if (this.currentJoueur == 1) {
            this.currentJoueur = 2;
        } else {
            this.currentJoueur = 1;
        }
    }

    private EchangeEvenement getRandomEvent() {
        Random rdm = new Random();
        int rand = rdm.nextInt(10);
        if (rand < 5) {
            return EchangeEvenement.Correct;
        } else if (rand < 7) {
            return EchangeEvenement.Faute;
        } else if (rand < 9) {
            return EchangeEvenement.Manquee;
        } else {
            return EchangeEvenement.Filet;
        }
    }

    /**
     * Gets jeu.
     *
     * @return jeu
     */
    public Jeu getJeu() {
        return this.jeu;
    }

    /**
     * Jouer.
     *
     * @param auto auto
     */
    public void jouer(boolean auto) {
        while (this.status == EchangeStatus.EnCours) {
            EchangeEvenement event = EchangeEvenement.Correct;
            if (auto) {
                event = getRandomEvent();
            } else {
                String saisie;
                if (this.isService) {
                    System.out.println("Service " + (this.balleService + 1) + " " + this.getJeu().getSet().getMatch().getNomEquipe(this.currentJoueur) + " | c = correct, f = faute, m = manquée, l = let");
                    saisie = ProjetJavaJeuTennis.saisieUser("c", "f", "m", "l");
                } else {
                    System.out.println("Balle à " + this.getJeu().getSet().getMatch().getNomEquipe(this.currentJoueur) + " | c = correct, f = faute, m = manquée ");
                    saisie = ProjetJavaJeuTennis.saisieUser("c", "f", "m");
                }
                switch (saisie) {
                    case "c":
                        event = EchangeEvenement.Correct;
                        break;
                    case "f":
                        event = EchangeEvenement.Faute;
                        break;
                    case "m":
                        event = EchangeEvenement.Manquee;
                        break;
                    case "l":
                        event = EchangeEvenement.Filet;
                        break;
                }
            }
            this.Evenement(event);
            if (ProjetJavaJeuTennis.getRandomLitigeChance() && (event == EchangeEvenement.Faute || event == EchangeEvenement.Manquee)) {
                Joueur joueurFaisantAppel;
                if (this.currentJoueur == 1) {
                    joueurFaisantAppel = this.jeu.getSet().getMatch().getEquipe1().get(0);
                } else {
                    joueurFaisantAppel = this.jeu.getSet().getMatch().getEquipe2().get(0);
                }
                if (!auto) {
                    System.out.println(joueurFaisantAppel.getPrenom() + " " + joueurFaisantAppel.getNom() + " fait appel !");
                }
                if (this.jeu.getSet().getMatch().getArbitre().litige(joueurFaisantAppel)) {
                    this.status = EchangeStatus.Annule;
                    if (!auto) {
                        System.out.println("L'arbitre : Veuillez m'excuser, j'ai pris la mauvaise décision.");
                    }
                } else if (!auto) {
                    System.out.println("L'arbitre : Vous avez eu tord d'interrompre le jeu.");
                }
            }
        }
    }

    /**
     * Is double faute boolean.
     *
     * @return boolean
     */
    public boolean isDoubleFaute() {
        return doubleFaute;
    }

    /**
     * Is service boolean.
     *
     * @return boolean
     */
    public boolean isService() {
        return isService;
    }

    /**
     * Gets balle service.
     *
     * @return balle service
     */
    public int getBalleService() {
        return balleService;
    }

    /**
     * Gets current joueur.
     *
     * @return current joueur
     */
    public int getCurrentJoueur() {
        return currentJoueur;
    }

    /**
     * Gets nb balles.
     *
     * @return nb balles
     */
    public int getNbBalles() {
        return nbBalles;
    }
}
