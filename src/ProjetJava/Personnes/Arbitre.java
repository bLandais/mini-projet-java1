package ProjetJava.Personnes;

import ProjetJava.ProjetJavaJeuTennis;
import ProjetJava.Tennis.Echange;
import ProjetJava.Tennis.Jeu;
import ProjetJava.Tennis.Match;
import ProjetJava.Tennis.Set;

import java.util.GregorianCalendar;
import java.util.Random;

/**
 * The type Arbitre.
 */
public class Arbitre extends Personne {

    /**
     * Instantiates a new Arbitre.
     *
     * @param nomNaissance  le nom de naissance
     * @param prenom        le prénom
     * @param surnom        le surnom
     * @param dateNaissance la date de naissance
     * @param lieuNaissance le lieu de naissance
     * @param dateDeces     la date de deces (null si pas mort...)
     * @param nationalite   la nationalité
     * @param taille        la taille
     * @param poids         le  poids
     * @param genre         le genre
     * @param partenaire    le partenaire (null si pas de partenaire)
     */
    public Arbitre(String nomNaissance, String prenom, String surnom, GregorianCalendar dateNaissance, String lieuNaissance, GregorianCalendar dateDeces, String nationalite, float taille, float poids, PersonneGenre genre, Personne partenaire) {
        super(nomNaissance, prenom, surnom, dateNaissance, lieuNaissance, dateDeces, nationalite, taille, poids, genre, partenaire);
    }

    @Override
    public void sExprimer(String phrase) {
        System.out.println("L'arbitre " + super.getPrenom() + " " + super.getNom() + " a dit au micro: " + phrase);
    }

    /**
     * Annonce echange.
     *
     * @param echange l'échange
     */
    public void annonceEchange(Echange echange) {
        Match match = echange.getJeu().getSet().getMatch();
        int premier = echange.getJeu().getServiceEquipe();
        int deuxieme = ProjetJavaJeuTennis.getOtherEquipe(premier);
        String annonce = "Score " + match.getNomEquipe(premier) + " : " + echange.getJeu().getPointsEquipe(premier).toString() + " | " + match.getNomEquipe(deuxieme) + " : " + echange.getJeu().getPointsEquipe(deuxieme).toString();
        this.sExprimer(annonce);
    }


    /**
     * Annonce jeu.
     *
     * @param jeu le jeu
     */
    public void annonceJeu(Jeu jeu) {
        Match match = jeu.getSet().getMatch();
        int equipeGagnante = 1;
        if (jeu.getStatus() == Jeu.JeuStatus.Gagnant2) {
            equipeGagnante = 2;
        }
        String annonce = "Jeu remporté par " + match.getNomEquipe(equipeGagnante) + ". Score : " + match.getNomEquipe(1) + " " + jeu.getSet().jeuxGagnes(1) + " | " + match.getNomEquipe(2) + " " + jeu.getSet().jeuxGagnes(2);
        this.sExprimer(annonce);
    }


    /**
     * Annonce set.
     *
     * @param set le set
     */
    public void annonceSet(Set set) {
        Match match = set.getMatch();
        int equipeGagnante = 1;
        if (set.getStatus() == Set.SetStatus.Gagnant2) {
            equipeGagnante = 2;
        }
        String annonce = "Set remporté par " + match.getNomEquipe(equipeGagnante) + ". Score : " + match.getNomEquipe(1) + " " + match.getScore(1) + " | " + match.getNomEquipe(2) + " " + match.getScore(2);
        this.sExprimer(annonce);
    }


    /**
     * Indique si l'aribtre est en faveur du joueur lors d'un litige.
     *
     * @param joueur the joueur
     * @return the boolean
     */
    public boolean litige(Joueur joueur) {
        //Retourne true si l'arbitre est en faveur du joueur.
        Random rand = new Random();
        int nombre = rand.nextInt(100) + joueur.getReputation();
        if (nombre <= 100) {
            return false;
        } else {
            return true;
        }
    }
}
