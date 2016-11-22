package ProjetJava.Personnes;

import ProjetJava.Tennis.MainJeu;

import java.util.GregorianCalendar;

/**
 * Created by Antoine on 17/10/2016.
 */
public class Joueur extends Personne {
    private MainJeu mainJeu;
    private String sponsor;
    private int classement;
    private Personne entraineur;
    private JoueurVetement vetement;
    private int reputation;

    public Joueur(String nomNaissance, String prenom, String surnom, GregorianCalendar dateNaissance, String lieuNaissance, GregorianCalendar dateDeces, String nationalite, float taille, float poids, PersonneGenre genre, Personne partenaire, MainJeu mainDeJeu, String sponsor, int classement, Personne entraineur, String couleurVetement, int reputation) {
        super(nomNaissance, prenom, surnom, dateNaissance, lieuNaissance, dateDeces, nationalite, taille, poids, genre, partenaire);
        this.sponsor = sponsor;
        this.mainJeu = mainDeJeu;
        this.classement = classement;
        this.entraineur = entraineur;
        if (this.getGenre() == PersonneGenre.Femme) {
            this.vetement = new JoueurVetement(this, JoueurTypeVetement.Jupe, couleurVetement);
        } else {
            this.vetement = new JoueurVetement(this, JoueurTypeVetement.Short, couleurVetement);
        }
        this.reputation = reputation;
    }
 
    public MainJeu getMainJeu() {
        return this.mainJeu;
    }

    public String getSponsor() {
        return this.sponsor;
    }

    public int getClassement() {
        return this.classement;
    }

    public Personne getEntraineur() {
        return this.entraineur;
    }

    public JoueurVetement getVetement() {
        return this.vetement;
    }

    public int getReputation(){
        return this.reputation;
    }
}
