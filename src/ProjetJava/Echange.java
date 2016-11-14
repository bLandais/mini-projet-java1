package ProjetJava;

/**
 * Created by Antoine on 07/11/2016.
 */
public class Echange {
    private boolean isService = true;
    private EchangeStatus status = EchangeStatus.EnCours;
    private int balleService = 0;
    private int currentJoueur;
    private Jeu jeu;

    public enum EchangeStatus {
        EnCours, Gagnant1, Gagnant2
    }

    public EchangeStatus getStatus() {
        return this.status;
    }

    public Echange(Jeu jeu,int currentJoueur) {
        this.jeu = jeu;
        this.currentJoueur = jeu.getServiceEquipe();
    }

    public enum EchangeEvenement {
        Faute, Filet, Correct, Manquee
    }

    public void Evenement(EchangeEvenement event) {
        if (isService) {
            if (event == EchangeEvenement.Faute) {
                if (this.balleService > 1) {
                    if (this.currentJoueur == 1) {
                        this.status = EchangeStatus.Gagnant2;
                    } else {
                        this.status = EchangeStatus.Gagnant1;
                    }
                } else {
                    this.balleService += 1;
                }
            } else if (event == EchangeEvenement.Correct || event == EchangeEvenement.Filet) {
                isService = false;
                this.changerJoueur();
            }
        } else {
            if (event == EchangeEvenement.Correct || event == EchangeEvenement.Filet) {
                this.changerJoueur();
            } else if (event == EchangeEvenement.Manquee || event == EchangeEvenement.Faute) {
                if (this.currentJoueur == 1) {
                    this.status = EchangeStatus.Gagnant2;
                } else {
                    this.status = EchangeStatus.Gagnant1;
                }
            }
        }
    }

    public void changerJoueur() {
        if (this.currentJoueur == 1) {
            this.currentJoueur = 2;
        } else {
            this.currentJoueur = 1;
        }
    }

    public Jeu getJeu(){
        return this.jeu;
    }
}
