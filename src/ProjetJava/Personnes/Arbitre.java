package ProjetJava.Personnes;

import ProjetJava.Tennis.Echange;
import ProjetJava.Tennis.Jeu;
import ProjetJava.Tennis.Match;
import ProjetJava.Tennis.Set;

import java.util.GregorianCalendar;
import java.util.Random;

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

    public void annonceEchange(Echange echange) {
        Match match = echange.getJeu().getSet().getMatch();
        int premier = echange.getJeu().getServiceEquipe();
        int deuxieme = Match.getOtherEquipe(premier);
        String annonce = match.getNomEquipe(premier) + " : " + echange.getJeu().getPointsEquipe(premier).toString() + "|" + match.getNomEquipe(deuxieme) + " : " + echange.getJeu().getPointsEquipe(deuxieme).toString();
        this.sExprimer(annonce);
    }

    public void annonceJeu(Jeu jeu) {
        Match match = jeu.getSet().getMatch();
        int equipeGagnante = 1;
        if (jeu.getStatus() == Jeu.JeuStatus.Gagnant2) {
            equipeGagnante = 2;
        }
        String annonce = "Jeu : " + match.getNomEquipe(equipeGagnante) + "|" + match.getNomEquipe(1) + " : " + jeu.getSet().jeuxGagnes(1) + "|" + match.getNomEquipe(2) + " : " + jeu.getSet().jeuxGagnes(2);
        this.sExprimer(annonce);
    }

    public void annonceSet(Set set) {
        Match match = set.getMatch();
        int equipeGagnante = 1;
        if (set.getStatus() == Set.SetStatus.Gagnant2) {
            equipeGagnante = 2;
        }
        String annonce = "Set : " + match.getNomEquipe(equipeGagnante) + "|" + match.getNomEquipe(1) + ":" + match.getScore(1) + "|" + match.getNomEquipe(2) + ":" + match.getScore(2);
        this.sExprimer(annonce);
    }

    public boolean litige(Joueur joueur) {
        //Retourne true si l'arbitre est en faveur du joueur..
        Random rand = new Random();
        int nombre = rand.nextInt(100) + joueur.getReputation();
        if (nombre <= 100) {
            return false;
        } else {
            return true;
        }
    }
}
