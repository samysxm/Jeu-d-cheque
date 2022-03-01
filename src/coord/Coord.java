package coord;

public class Coord {
    private int numR, numC;
    public Coord(int rangee, int colonne) {
        this.numR = rangee;
        this.numC = colonne;
    }
    /**
     * @return la rangee
     */
    public int getRangee() {
        return numR;
    }
    /**
     * @return la colonne
     */
    public int getColonne() {
        return numC;
    }
    /**
     * @param c coordonnee a tester
     * @param taille de l'echiquier
     * @return vrai si les coordonnee sont situes dans l'echiquier
     */
    public static boolean coordCorrecte(Coord c, int taille) {
        return 0 <= c.getColonne() && c.getColonne() < taille &&
                0 <= c.getRangee() && c.getRangee() < taille;
    }
}
