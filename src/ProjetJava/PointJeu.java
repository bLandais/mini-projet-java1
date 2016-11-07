package ProjetJava;

/**
 * Created by Antoine on 24/10/2016.
 */
public class PointJeu {
    private int pointValue;

    public PointJeu(int pointJeu) {
        this.pointValue = pointJeu;
    }

    public int GetPointValue(){
        return this.pointValue;
    }

    @Override
    public String toString(){
        switch (this.pointValue){
            case 0:
                return "0";
            case 1:
                return "15";
            case 2:
                return "30";
            case 3:
                return "40";
            case 4:
                return "AV";
            default:
                return null;
        }
    }
}
