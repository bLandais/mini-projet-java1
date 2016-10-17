package ProjetJava;

import java.util.GregorianCalendar;

/**
 * Created by Antoine on 17/10/2016.
 */
public class Arbitre extends Personne {
    public Arbitre(String nomNaissance, String prenom, String surnom, GregorianCalendar dateNaissance, String lieuNaissance, GregorianCalendar dateDeces, String nationalite, float taille, float poids, PersonneGenre genre, Personne partenaire) {
        super(nomNaissance, prenom, surnom, dateNaissance, lieuNaissance, dateDeces, nationalite, taille, poids, genre, partenaire);
    }

    @Override
    public void sExprimer(String phrase) {
        System.out.println("L'arbitre " + super.getPrenom() + " " + super.getNom() + " a dit au micro: " + phrase);
    }

    public void litige(Joueur joueur){
        System.out.println("Litige !");
    }
}
