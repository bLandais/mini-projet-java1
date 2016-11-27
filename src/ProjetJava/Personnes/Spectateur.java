package ProjetJava.Personnes;

import ProjetJava.Personnes.Personne;
import ProjetJava.Personnes.PersonneGenre;

/**
 * The type Spectateur.
 */
public class Spectateur extends Personne implements AssisterMatch{
    private String nomTribune;
    private int numeroPlace;

    /**
     * Instantiates a new Spectateur.
     *
     * @param nomTribune     nom de tribune
     * @param numeroPlace    numero de place
     * @param lunettes       lunettes
     * @param couleurChemise couleur de chemise
     */
    public Spectateur(String nomTribune, int numeroPlace, boolean lunettes, String couleurChemise) {
        this.numeroPlace = numeroPlace;
        this.nomTribune = nomTribune;
        if (couleurChemise == "Bleue" || couleurChemise == "Bleu") {
            this.setGenre(PersonneGenre.Homme);
        } else if (lunettes) {
            this.setGenre(PersonneGenre.Femme);
        } else {
            double randomNumber = Math.random();
            if (randomNumber < 0.5) {
                this.setGenre(PersonneGenre.Homme);
            } else {
                this.setGenre(PersonneGenre.Femme);
            }
        }
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

