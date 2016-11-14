package ProjetJava;

import java.util.ArrayList;

/**
 * Created by Antoine on 24/10/2016.
 */
public class Set {
    private ArrayList<Jeu> jeux = new ArrayList<Jeu>();
    private int nbJeuMiniPourVictoire;
    private boolean estDecisif;
    private Match match;
    private SetStatus status = SetStatus.EnCours;

    public enum SetStatus {
        EnCours, Gagnant1, Gagnant2
    }

    public Set(Match match, boolean estDecisif) {
        this.estDecisif = estDecisif;
        this.match = match;
    }

    public Set(Match match, boolean estDecisif, int nbJeuMiniPourVictoire) {
        this.match = match;
        this.estDecisif = estDecisif;
        this.nbJeuMiniPourVictoire = nbJeuMiniPourVictoire;
    }

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

    public void checkWinner() {
        if (!this.estDecisif) {
            int joueurMax = 1;
            int max = this.jeuxGagnes(1);
            if (this.jeuxGagnes(2) > max) {
                joueurMax = 2;
                max = this.jeuxGagnes(2);
            }
            if (max < this.nbJeuMiniPourVictoire) {
                this.status=SetStatus.EnCours;
                return;
            }
            int ecartJeu = Math.abs(this.jeuxGagnes(1) - this.jeuxGagnes(2));
            if (ecartJeu >= 2) {
                if (joueurMax == 1){
                    this.status = SetStatus.Gagnant1;
                }
                else{
                    this.status = SetStatus.Gagnant2;
                }
                return;
            } else {
                this.status = SetStatus.EnCours;
                return;
            }
        } else {
            // Code pour set décisif...
            return;
        }
    }

    public Match getMatch() {
        return this.match;
    }

    public SetStatus getStatus(){
        return this.status;
    }

    public void Jouer(Jeu jeuAJouer) {

    }
}
