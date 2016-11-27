package ProjetJava.Tennis;

/**
 * The type Point jeu.
 */
public class PointJeu {
    private int pointValue;
    private boolean decisif;

    /**
     * Instantiates a new Point jeu.
     *
     * @param pointJeu point jeu
     * @param decisif  decisif
     */
    public PointJeu(int pointJeu, boolean decisif) {
        this.pointValue = pointJeu;
        this.decisif=decisif;
    }

    /**
     * Gets point value.
     *
     * @return point value
     */
    public int getPointValue() {
        return this.pointValue;
    }

    /**
     * Is decisif boolean.
     *
     * @return isDecisif.
     */
    public boolean isDecisif() {
        return decisif;
    }

    @Override
    public String toString() {
        if (this.decisif) {
            return this.pointValue + "";
        }
        switch (this.pointValue) {
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
