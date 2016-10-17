package ProjetJava;
import java.util.GregorianCalendar;

public class Main {

    public static void main(String[] args) {
        GregorianCalendar naissanceSylvain = new GregorianCalendar(1995,12,14);
        Joueur nouvellePersonne = new Joueur("Pryfer","Sylvain","Feitte",naissanceSylvain, "Lille",null, "Française", 182, 50, PersonneGenre.Homme, null,MainJeu.Droitier,"Lacoste",1,null,"Blanc");
        nouvellePersonne.getVetement().setCouleur("Rouge");
    }
}
