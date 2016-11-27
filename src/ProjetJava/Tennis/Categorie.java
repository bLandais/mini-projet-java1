package ProjetJava.Tennis;

/**
 * The type Categorie.
 */
public class Categorie {
    private CategorieGenre genre;
    private CategorieSimpleDouble simpledouble;

    /**
     * Instantiates a new Categorie.
     *
     * @param genre        genre
     * @param simpleDouble simple ou double
     */
    public Categorie(CategorieGenre genre, CategorieSimpleDouble simpleDouble) {
        this.genre = genre;
        this.simpledouble = simpleDouble;
    }

    /**
     * Gets genre.
     *
     * @return genre
     */
    public CategorieGenre getGenre() {
        return genre;
    }

    /**
     * Gets simpledouble.
     *
     * @return simpledouble
     */
    public CategorieSimpleDouble getSimpledouble() {
        return simpledouble;
    }

    @Override
    public String toString(){
        return this.genre.toString() + " " + this.simpledouble.toString();
    }
}
