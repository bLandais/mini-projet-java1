package ProjetJava;

import ProjetJava.Personnes.PersonneGenre;
import ProjetJava.Tennis.MainJeu;
import ProjetJava.Tennis.Tournoi;
import ProjetJava.Tennis.TournoiSurfaceTerrain;

import java.io.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.Scanner;

/**
 * The type Projet java jeu tennis.
 */
public class ProjetJavaJeuTennis {
    private static ArrayList<String> names;
    private static String[] couleurs = new String[]{"Bleu", "Noir", "Blanc", "Rouge", "Jaune", "Vert", "Violet", "Rose", "Marron", "Gris"};

    /**
     * Gets names.
     *
     * @return names
     */
    public static ArrayList<String> getNames() {
        if (names == null) {
            try {
                File nameTxt = new File("names.txt");
                BufferedReader br = new BufferedReader(new FileReader(nameTxt));
                String line;
                names = new ArrayList<String>();
                while ((line = br.readLine()) != null) {
                    names.add(line);
                }
                return names;
            } catch (FileNotFoundException e) {
                System.out.println("Impossible d'obtenir une liste de noms (fichier names.txt introuvable).");
                // On retourne quand même une liste !
                ArrayList<String> newList = new ArrayList<String>();
                newList.add("Nom bidon");
                return newList;
            } catch (Exception e) {
                System.out.println("Impossible d'obtenir une liste de noms (fichier names.txt introuvable).");
                // On retourne quand même une liste !
                ArrayList<String> newList = new ArrayList<String>();
                newList.add("Nom bidon");
                return newList;
            }
        } else {
            return names;
        }
    }

    /**
     * Gets random name.
     *
     * @return random name
     */
    public static String getRandomName() {
        Random rdm = new Random();
        return getNames().get(rdm.nextInt(names.size()));
    }

    /**
     * Gets random date naissance.
     *
     * @return random date naissance
     */
    public static GregorianCalendar getRandomDateNaissance() {
        Random rdm = new Random();
        return new GregorianCalendar(rdm.nextInt(50) + 1950, rdm.nextInt(11) + 1, rdm.nextInt(27) + 1);
    }

    /**
     * Gets random taille.
     *
     * @return random taille
     */
    public static float getRandomTaille() {
        Random rdm = new Random();
        return 1.15f + rdm.nextFloat();
    }

    /**
     * Gets random poids.
     *
     * @return random poids
     */
    public static float getRandomPoids() {
        Random rdm = new Random();
        return 40 + 50 * rdm.nextFloat();
    }

    /**
     * Gets random genre.
     *
     * @return random genre
     */
    public static PersonneGenre getRandomGenre() {
        Random rdm = new Random();
        int rand = rdm.nextInt(2);
        if (rand == 0) {
            return PersonneGenre.Femme;
        } else {
            return PersonneGenre.Homme;
        }
    }

    /**
     * Gets random main jeu.
     *
     * @return random main jeu
     */
    public static MainJeu getRandomMainJeu() {
        Random rdm = new Random();
        int rand = rdm.nextInt(2);
        if (rand == 0) {
            return MainJeu.Droitier;
        } else {
            return MainJeu.Gauche;
        }
    }

    /**
     * Gets random couleur.
     *
     * @return random couleur
     */
    public static String getRandomCouleur() {
        Random rdm = new Random();
        int rand = rdm.nextInt(10);
        return couleurs[rand];
    }

    /**
     * Gets random boolean.
     *
     * @return random boolean
     */
    public static boolean getRandomBoolean() {
        Random rdm = new Random();
        int rand = rdm.nextInt(2);
        return rand == 1;
    }

    /**
     * Gets random reputation.
     *
     * @return random reputation
     */
    public static int getRandomReputation() {
        Random rdm = new Random();
        int rand = rdm.nextInt(100);
        return rand;
    }

    /**
     * Gets other equipe.
     *
     * @param equipe equipe
     * @return other equipe
     */
    public static int getOtherEquipe(int equipe) {
        if (equipe == 1) {
            return 2;
        } else {
            return 1;
        }
    }

    /**
     * Array contains string boolean.
     *
     * @param array stringArray
     * @param str   string
     * @return boolean
     */
    public static boolean arrayContainsString(String[] array, String str) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Saisie user string.
     *
     * @param saisies saisies
     * @return string
     */
    public static String saisieUser(String... saisies) {
        Scanner sc = new Scanner(System.in);
        String saisieUser = null;
        while (!arrayContainsString(saisies, saisieUser) && (saisies.length > 0 || saisieUser == null)) {
            saisieUser = sc.nextLine();
        }
        return saisieUser;
    }

    /**
     * Gets random litige chance.
     *
     * @return random litige chance
     */
    public static boolean getRandomLitigeChance() {
        Random rdm = new Random();
        int rand = rdm.nextInt(1000);
        // 1 chance sur 100 que le joueur fait appel à la décision de l'arbitre
        return (rand == 0);
    }

    /**
     * Play.
     */
    public static void play() {
        System.out.println("Bienvenue dans votre simulateur de tournoi de tennis !");
        System.out.println("Veuillez tout d'abord saisir la ville où votre tournoi a lieu :");
        System.out.println("(m = Melbourne, p = Paris, l = Londres, n = New York)");
        String input = ProjetJavaJeuTennis.saisieUser("m", "p", "l", "n");
        String ville = null;
        TournoiSurfaceTerrain surface = TournoiSurfaceTerrain.TerreBattue;
        switch (input) {
            case "m":
                ville = "Melbourne";
                surface = TournoiSurfaceTerrain.Plexicushion;
                break;
            case "p":
                ville = "Paris";
                surface = TournoiSurfaceTerrain.TerreBattue;
                break;
            case "l":
                ville = "Londres";
                surface = TournoiSurfaceTerrain.Gazon;
                break;
            case "n":
                ville = "New York";
                surface = TournoiSurfaceTerrain.Decoturf;
                break;
        }
        Tournoi newTournoi = new Tournoi(ville, surface);
        newTournoi.jouer();
        writeStats(newTournoi.getStats());
    }

    /**
     * Write stats.
     *
     * @param stats stats
     */
    public static void writeStats(String stats) {
        try {
            File newFile = new File("stats.txt");
            if (newFile.exists()) {
                newFile.delete();
            }
            newFile.createNewFile();
            FileWriter fw = new FileWriter(newFile);
            fw.write(stats);
            fw.close();
            System.out.println("Les statistiques du tournoi ont été enregistrées dans stats.txt!");
        } catch (Exception e) {
            System.out.println("Impossible de sauvegarder les statistiques du match");
        }
    }
}
