package ProjetJava;

/**
 * Created by Antoine on 24/10/2016.
 */
public enum PointJeu {
    Point0(0), Point15(1), Point30(2), Point40(3), Avantage(4);

    public int pointValue;

    PointJeu(int pointJeu) {
        this.pointValue = pointJeu;
    }
}
