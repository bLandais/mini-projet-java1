package ProjetJava;

import java.util.ArrayList;

/**
 * Created by Antoine on 24/10/2016.
 */
public class Set {
    private ArrayList<Jeu> jeuxEquipe1 = new ArrayList<Jeu>();
    private ArrayList<Jeu> jeuxEquipe2 = new ArrayList<Jeu>();
    private int nbJeuMiniPourVictoire;
    private boolean estDecisif;

    public Set(boolean estDecisif) {
        this.estDecisif = estDecisif;
    }

    public Set(boolean estDecisif, int nbJeuMiniPourVictoire) {
        this.estDecisif = estDecisif;
        this.nbJeuMiniPourVictoire = nbJeuMiniPourVictoire;
    }

    public int checkWinner() {
        if (!this.estDecisif) {
            int joueurMax = 1;
            int max = this.jeuxEquipe1.size();
            if (this.jeuxEquipe2.size() > max) {
                joueurMax = 2;
                max = this.jeuxEquipe2.size();
            }
            if (max < this.nbJeuMiniPourVictoire) {
                return 0;
            }
            int ecartJeu = Math.abs(this.jeuxEquipe1.size() - this.jeuxEquipe2.size());
            if (ecartJeu >= 2) {
                return joueurMax;
            } else {
                return 0;
            }
        } else {
            // Code pour set décisif...
            return -1;
        }
    }

    public void Jouer(Jeu jeuAJouer){
        
    }
}
