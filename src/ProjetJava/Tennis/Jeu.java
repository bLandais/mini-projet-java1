package ProjetJava.Tennis;

import java.util.ArrayList;

/**
 * Created by Antoine on 24/10/2016.
 */
public class Jeu {
    private PointJeu pointsEquipe1 = new PointJeu(0);
    private PointJeu pointsEquipe2 = new PointJeu(0);
    private ArrayList<Echange> echanges = new ArrayList<Echange>();
    private JeuStatus jeuStatus = JeuStatus.EnCours;
    private int serviceEquipe;
    private Set set;

    public enum JeuStatus {
        EnCours, Gagnant1, Gagnant2
    }

    public Jeu(Set set, int serviceEquipe) {
        this.set = set;
        this.serviceEquipe = serviceEquipe;
    }

    public void AddPoint(int equipe) {
        if (this.jeuStatus == JeuStatus.EnCours) {
            if (equipe == 1) {
                PointJeu[] result = nextPoint(this.pointsEquipe1, this.pointsEquipe2);
                if (result == null) {
                    this.jeuStatus = JeuStatus.Gagnant1;
                } else {
                    this.pointsEquipe1 = result[0];
                    this.pointsEquipe2 = result[1];
                }
            } else if (equipe == 2) {
                PointJeu[] result = nextPoint(this.pointsEquipe2, this.pointsEquipe1);
                if (result == null) {
                    this.jeuStatus = JeuStatus.Gagnant2;
                } else {
                    this.pointsEquipe1 = result[1];
                    this.pointsEquipe2 = result[0];
                }
            }
        }
    }

    private static PointJeu[] nextPoint(PointJeu pointsActuels1, PointJeu pointsActuels2) {
        String pts1 = pointsActuels1.toString();
        String pts2 = pointsActuels2.toString();
        if (pts2 != "AV") {
            if (pts2 != "40") {
                if (pts1 == "40") {
                    return null;
                } else {
                    pointsActuels1 = new PointJeu(pointsActuels1.GetPointValue() + 1);
                }
            } else {
                if (pts1 == "AV") {
                    return null;
                } else {
                    pointsActuels1 = new PointJeu(pointsActuels1.GetPointValue() + 1);
                }
            }
        } else {
            pointsActuels2 = new PointJeu(pointsActuels2.GetPointValue() - 1);
        }
        return new PointJeu[]{pointsActuels1, pointsActuels2};
    }

    public PointJeu getPointsEquipe(int equipe){
        if (equipe == 1){
            return this.pointsEquipe1;
        }
        else{
            return this.pointsEquipe2;
        }
    }

    public ArrayList<Echange> getEchanges() {
        return this.echanges;
    }

    public JeuStatus getStatus() {
        return this.jeuStatus;
    }

    public int getServiceEquipe() {
        return this.serviceEquipe;
    }
    public Set getSet(){
        return this.set;
    }
}