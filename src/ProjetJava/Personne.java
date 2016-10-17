package ProjetJava;

import java.util.GregorianCalendar;
import java.sql.Timestamp;

/**
 * Created by Antoine on 17/10/2016.
 */

public class Personne {
    private String nomNaissance;
    private String nomCourant;
    private String prenom;
    private String surnom;
    private GregorianCalendar dateNaissance;
    private String lieuNaissance;
    private GregorianCalendar dateDeces;
    private String nationalite;
    private float taille;
    private float poids;
    private PersonneGenre genre;
    private Personne partenaire;

    public Personne(String nomNaissance, String prenom, String surnom, GregorianCalendar dateNaissance, String lieuNaissance, GregorianCalendar dateDeces, String nationalite, float taille, float poids, PersonneGenre genre, Personne partenaire) {
        this.nomNaissance = nomNaissance;
        this.prenom = prenom;
        this.surnom = surnom;
        this.dateNaissance = dateNaissance;
        this.lieuNaissance = lieuNaissance;
        this.dateDeces = dateDeces;
        this.nationalite = nationalite;
        this.taille = taille;
        this.poids = poids;
        this.genre = genre;
        this.partenaire = partenaire;
        if (this.partenaire != null && this.genre == PersonneGenre.Femme) {
            this.nomCourant = this.partenaire.nomNaissance;
        }
    }

    public long getAge() {
        GregorianCalendar currentDate;
        if (this.dateDeces == null) {
            currentDate = new GregorianCalendar();
        } else {
            currentDate = this.dateDeces;
        }
        long differenceMs = currentDate.getTime().getTime() - this.dateNaissance.getTime().getTime();
        Timestamp difference = new Timestamp(differenceMs);
        return difference.getYear() - 70;
    }

    public String getNomNaissance() {
        return this.nomNaissance;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public String getSurnom() {
        return this.surnom;
    }

    public void setSurnom(String surnom) {
        this.surnom = surnom;
    }

    public GregorianCalendar getDateNaissance() {
        return this.dateNaissance;
    }

    public String getLieuNaissance() {
        return this.lieuNaissance;
    }

    public GregorianCalendar getDateDeces() {
        return this.dateDeces;
    }

    public void setDateDeces(GregorianCalendar dateDeces) {
        this.dateDeces = dateDeces;
    }

    public String getNationalite() {
        return this.nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public float getTaille() {
        return this.taille;
    }

    public void setTaille(float taille) {
        this.taille = taille;
    }

    public float getPoids() {
        return this.poids;
    }

    public void setPoids(float poids) {
        this.poids = poids;
    }

    public PersonneGenre getGenre() {
        return this.genre;
    }

    public Personne getPartenaire() {
        return this.partenaire;
    }

    public void setPartenaire(Personne partenaire) {
        this.partenaire = partenaire;
    }

}

