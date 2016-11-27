package ProjetJava.Tennis;

import ProjetJava.ProjetJavaJeuTennis;

import java.util.ArrayList;

/**
 * The type Tour.
 */
public class Tour {
    private ArrayList<Match> matchs = new ArrayList<Match>();
    private int tour;

    /**
     * Instantiates a new Tour.
     *
     * @param tour tour
     */
    public Tour(int tour) {
        this.tour = tour;
    }

    /**
     * Add match.
     *
     * @param match match
     */
    public void addMatch(Match match) {
        this.matchs.add(match);
    }

    /**
     * Gets tour.
     *
     * @return tour
     */
    public int getTour() {
        return tour;
    }

    /**
     * Gets matchs.
     *
     * @return matchs
     */
    public ArrayList<Match> getMatchs() {
        return matchs;
    }

    @Override
    public String toString() {
        switch (this.tour) {
            case 1:
                return "1er tour";
            case 2:
                return "2eme tour";
            case 3:
                return "3eme tour";
            case 4:
                return "Huitièmes de finale";
            case 5:
                return "Quarts de finale";
            case 6:
                return "Demi finale";
            case 7:
                return "Finale";
            default:
                return "Tour " + this.tour;
        }
    }

    /**
     * Jouer.
     *
     * @param auto auto
     */
    public void jouer(int auto) {
        for (int i = 0; i < this.matchs.size(); i++) {
            if (auto == 0) {
                this.matchs.get(i).jouer(false);
            } else if (auto == 1) {
                this.matchs.get(i).jouer(true);
            } else if (auto == 2) {
                System.out.println("Match " + (i + 1) + " opposant " + this.matchs.get(i).getNomEquipe(1) + " à " + this.matchs.get(i).getNomEquipe(2));
                System.out.println("(1 = automatiquement, 2 = manuellement) ?");
                String saisie = ProjetJavaJeuTennis.saisieUser("1", "2");
                switch (saisie) {
                    case "1":
                        this.matchs.get(i).jouer(true);
                        break;
                    case "2":
                        this.matchs.get(i).jouer(false);
                        break;
                }
            }
            System.out.println();
        }
    }

    /**
     * Gets stats.
     *
     * @return stats
     */
    public String getStats() {
        String stats = this.toString() + "\r\n" + "\r\n";
        for (int i = 0; i < this.matchs.size(); i++) {
            stats = stats + this.toString() + " Match " + (i + 1) + "\r\n" + "\r\n";
            stats = stats + this.matchs.get(i).getStats() + "\r\n" + "\r\n";
        }
        return stats;
    }
}
