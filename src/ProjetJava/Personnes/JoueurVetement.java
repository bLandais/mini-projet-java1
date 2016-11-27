package ProjetJava.Personnes;


/**
 * The type Joueur vetement.
 */
public class JoueurVetement {
    private Joueur joueurAssocie;
    private JoueurTypeVetement typeVetement;
    private String couleur;

    /**
     * Instantiates a new Joueur vetement.
     *
     * @param joueurAssocie joueur associe
     * @param typeVetement  type de vetement
     * @param couleur       couleur
     */
    public JoueurVetement(Joueur joueurAssocie, JoueurTypeVetement typeVetement, String couleur) {
        this.joueurAssocie = joueurAssocie;
        this.typeVetement = typeVetement;
        this.couleur = couleur;
    }

    /**
     * Sets couleur.
     *
     * @param couleur the couleur
     */
    public void setCouleur(String couleur) {
        if (this.typeVetement == JoueurTypeVetement.Jupe) {
            this.joueurAssocie.sExprimer("Ma jupe est maintenant de couleur " + couleur);
        } else {
            this.joueurAssocie.sExprimer("Mon short est maintenant de couleur " + couleur);
        }
        this.couleur = couleur;
    }
}
