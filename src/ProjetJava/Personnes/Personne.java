package ProjetJava.Personnes;

import java.util.GregorianCalendar;
import java.sql.Timestamp;

/**
 * The type Personne.
 */
public abstract class Personne {
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

    /**
     * Instantiates a new Personne.
     */
    public Personne(){}

    /**
     * Instantiates a new Personne.
     *
     * @param nomNaissance  nom de naissance
     * @param prenom        prenom
     * @param surnom        surnom
     * @param dateNaissance date naissance
     * @param lieuNaissance lieu naissance
     * @param dateDeces     date de deces (null si pas mort)
     * @param nationalite   nationalite
     * @param taille        taille
     * @param poids         poids
     * @param genre         genre
     * @param partenaire    partenaire
     */
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
        this.findNomCourant();
    }

    /**
     * Gets age.
     *
     * @return age
     */
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

    private void findNomCourant() {
        if (this.partenaire != null && this.genre == PersonneGenre.Femme) {
            this.nomCourant = this.partenaire.nomNaissance;
        } else {
            this.nomCourant = null;
        }
    }

    /**
     * Gets nom naissance.
     *
     * @return nom de naissance
     */
    public String getNomNaissance() {
        return this.nomNaissance;
    }

    /**
     * Gets prenom.
     *
     * @return prenom
     */
    public String getPrenom() {
        return this.prenom;
    }

    /**
     * Gets surnom.
     *
     * @return surnom
     */
    public String getSurnom() {
        return this.surnom;
    }

    /**
     * Sets surnom.
     *
     * @param surnom surnom
     */
    public void setSurnom(String surnom) {
        this.surnom = surnom;
    }

    /**
     * Gets date naissance.
     *
     * @return date de naissance
     */
    public GregorianCalendar getDateNaissance() {
        return this.dateNaissance;
    }

    /**
     * Gets lieu naissance.
     *
     * @return lieu de naissance
     */
    public String getLieuNaissance() {
        return this.lieuNaissance;
    }

    /**
     * Gets date deces.
     *
     * @return date de deces
     */
    public GregorianCalendar getDateDeces() {
        return this.dateDeces;
    }

    /**
     * Sets date deces.
     *
     * @param dateDeces date de deces
     */
    public void setDateDeces(GregorianCalendar dateDeces) {
        this.dateDeces = dateDeces;
    }

    /**
     * Gets nationalite.
     *
     * @return nationalite
     */
    public String getNationalite() {
        return this.nationalite;
    }

    /**
     * Sets nationalite.
     *
     * @param nationalite nationalite
     */
    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    /**
     * Gets taille.
     *
     * @return taille
     */
    public float getTaille() {
        return this.taille;
    }

    /**
     * Sets taille.
     *
     * @param taille taille
     */
    public void setTaille(float taille) {
        this.taille = taille;
    }

    /**
     * Gets poids.
     *
     * @return poids
     */
    public float getPoids() {
        return this.poids;
    }

    /**
     * Sets poids.
     *
     * @param poids poids
     */
    public void setPoids(float poids) {
        this.poids = poids;
    }

    /**
     * Gets genre.
     *
     * @return genre
     */
    public PersonneGenre getGenre() {
        return this.genre;
    }

    /**
     * Sets genre.
     *
     * @param newGenre new genre
     */
    public void setGenre(PersonneGenre newGenre) {
        this.genre = newGenre;
        this.findNomCourant();
    }

    /**
     * Gets partenaire.
     *
     * @return partenaire
     */
    public Personne getPartenaire() {
        return this.partenaire;
    }

    /**
     * Sets partenaire.
     *
     * @param partenaire partenaire
     */
    public void setPartenaire(Personne partenaire) {
        this.partenaire = partenaire;
    }

    /**
     * Gets nom.
     *
     * @return nom
     */
    public String getNom() {
        if (this.nomCourant != null) {
            return this.nomCourant;
        } else {
            return this.nomNaissance;
        }
    }

    /**
     * S exprimer.
     *
     * @param phrase phrase
     */
    public void sExprimer(String phrase) {
        System.out.println(this.prenom + " " + this.getNom() + " a dit: " + phrase);
    }
}

