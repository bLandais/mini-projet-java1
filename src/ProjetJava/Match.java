package ProjetJava;

/**
 * Created by Toshiba on 24/10/2016.
 */
public class Match {
        private Joueur j1;
        private Joueur j2;
        private Joueur j3;
        private Joueur j4;
        private Arbitre arbitre;
        private Categorie categorie;
        private String niveau;
        private int points;

public Match(Joueur j1,Joueur j2, Arbitre arbitre){
        this.arbitre=arbitre;
        this.j1=j1;
        this.j2=j2;

}
public Match(Joueur j1,Joueur j2,Joueur j3,Joueur j4, Arbitre arbitre){
        this.arbitre=arbitre;
        this.j1=j1;
        this.j2=j2;
        this.j3=j3;
        this.j4=j4;
}

public Joueur getJ1() {return this.j1;}
public Joueur getJ2() {return this.j2;}
public Joueur getJ3() {return this.j3;}
public Joueur getJ4() {return this.j4;}
public Arbitre getArbitre(){return this.arbitre;}
public String getNiveau(){return this.niveau;}
public Categorie getCategore(){return this.categorie;}
}
