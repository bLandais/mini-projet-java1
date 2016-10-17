package ProjetJava;


/**
 * Created by Antoine on 17/10/2016.
 */
public class JoueurVetement {
    private Joueur joueurAssocie;
    private JoueurTypeVetement typeVetement;
    private String couleur;

    public JoueurVetement(Joueur joueurAssocie, JoueurTypeVetement typeVetement, String couleur) {
        this.joueurAssocie = joueurAssocie;
        this.typeVetement = typeVetement;
        this.couleur = couleur;
    }

    public void setCouleur(String couleur) {
        if (this.typeVetement == JoueurTypeVetement.Jupe) {
            this.joueurAssocie.sExprimer("Ma jupe est maintenant de couleur " + couleur);
        } else {
            this.joueurAssocie.sExprimer("Mon short est maintenant de couleur " + couleur);
        }
        this.couleur = couleur;
    }
}
