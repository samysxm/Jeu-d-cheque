package coord;

public class Deplacement {
    private final Coord deplacement;

    /**
     * @param start
     * @param finish
     */
    public Deplacement(Coord start, Coord finish){
        deplacement = new Coord(start.getRangee() - finish.getRangee(),//vertical
                        start.getColonne() - finish.getColonne());
    }

    /**
     * @return le deplacement en rangee
     */
    public int getMoveRangee(){
        return deplacement.getRangee();
    }

    /**
     * @return le deplacement en colonne
     */
    public int getMoveColonne(){
        return deplacement.getColonne();
    }
}