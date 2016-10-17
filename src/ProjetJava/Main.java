package ProjetJava;
import java.util.GregorianCalendar;

public class Main {

    public static void main(String[] args) {
        GregorianCalendar naissanceSylvain = new GregorianCalendar(1950,12,14);
        Personne nouvellePersonne = new Personne("Pryfer","Sylvain","Feitte",naissanceSylvain, "Lille",new GregorianCalendar(1965,12,14), "Française", 182, 50, PersonneGenre.Homme, null);
        System.out.println(nouvellePersonne.getAge());
    }
}
