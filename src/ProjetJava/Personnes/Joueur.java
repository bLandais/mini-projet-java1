package ProjetJava.Personnes;

import ProjetJava.Tennis.MainJeu;

import java.util.GregorianCalendar;

/**
 * The type Joueur.
 */
public class Joueur extends Personne implements AssisterMatch{
    private MainJeu mainJeu;
    private String sponsor;
    private int classement;
    private Personne entraineur;
    private JoueurVetement vetement;
    private int reputation;

    /**
     * Instantiates a new Joueur.
     *
     * @param nomNaissance    nom de naissance
     * @param prenom          prenom
     * @param surnom          surnom
     * @param dateNaissance   date de naissance
     * @param lieuNaissance   lieu de naissance
     * @param dateDeces       date de deces (null si pas mort)
     * @param nationalite     nationalite
     * @param taille          taille
     * @param poids           poids
     * @param genre           genre
     * @param partenaire      partenaire (null si pas de partenaire)
     * @param mainDeJeu       main de jeu
     * @param sponsor         sponsor
     * @param classement      classement
     * @param entraineur      entraineur
     * @param couleurVetement couleur des vetements
     * @param reputation      reputation
     */
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

    /**
     * Gets main jeu.
     *
     * @return main de jeu
     */
    public MainJeu getMainJeu() {
        return this.mainJeu;
    }

    /**
     * Gets sponsor.
     *
     * @return sponsor
     */
    public String getSponsor() {
        return this.sponsor;
    }

    /**
     * Gets classement.
     *
     * @return classement
     */
    public int getClassement() {
        return this.classement;
    }

    /**
     * Gets entraineur.
     *
     * @return entraineur
     */
    public Personne getEntraineur() {
        return this.entraineur;
    }

    /**
     * Gets vetement.
     *
     * @return vetement
     */
    public JoueurVetement getVetement() {
        return this.vetement;
    }

    /**
     * Get reputation int.
     *
     * @return reputation
     */
    public int getReputation(){
        return this.reputation;
    }

    public void applaudir(){
        System.out.println("CLAP CLAP CLAP CLAP!");
    }

    public void crier(){
        System.out.println("Je crie !");
    }

    public void huer(){
        System.out.println("Je hue !");
    }

    public void dormir(){
        System.out.println("Je ronfle !");
    }
}
