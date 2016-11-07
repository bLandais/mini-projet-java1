package ProjetJava;

import java.util.ArrayList;

/**
 * Created by Toshiba on 24/10/2016.
 */
public class Match {
    private ArrayList<Joueur> equipe1 = new ArrayList<Joueur>();
    private ArrayList<Joueur> equipe2 = new ArrayList<Joueur>();
    private ArrayList<Set> sets1 = new ArrayList<Set>();
    private ArrayList<Set> sets2 = new ArrayList<Set>();
    private Arbitre arbitre;
    private Categorie categorie;
    private String niveau;
    private int points;

    public Match(Joueur j1, Joueur j2, Arbitre arbitre) {
        this.arbitre = arbitre;
        this.equipe1.add(j1);
        this.equipe2.add(j2);
    }

    public Match(Joueur j1, Joueur j2, Joueur j3, Joueur j4, Arbitre arbitre) {
        this.arbitre = arbitre;
        this.equipe1.add(j1);
        this.equipe1.add(j2);
        this.equipe2.add(j3);
        this.equipe2.add(j4);
    }

    public ArrayList<Joueur> getEquipe1() {
        return this.equipe1;
    }

    public ArrayList<Joueur> getEquipe2() {
        return this.equipe2;
    }

    public Arbitre getArbitre() {
        return this.arbitre;
    }

    public String getNiveau() {
        return this.niveau;
    }

    public Categorie getCategore() {
        return this.categorie;
    }

    public String GetNomEquipe(int equipe){
        String nomEquipe = "";
        ArrayList<Joueur> currentEquipe = null;
        if(equipe == 1){
            currentEquipe = this.equipe1;
        }
        else{
            currentEquipe = this.equipe2;
        }
        if (currentEquipe.size() > 0){
            nomEquipe = currentEquipe.get(0).getNom();
            if (currentEquipe.size() > 1){
                nomEquipe = nomEquipe + "/" + currentEquipe.get(1).getNom();
            }
        }
        return nomEquipe;
    }
}
