package piece;

import coord.Coord;
import coord.Deplacement;
import echiquier.Echiquier;
import equipe.Team;

import java.util.Locale;

public class Roi extends Piece {
    private final static String roi = "R";
    public Roi(Team team){
        super(team);
    }

    @Override
    public String toString() {
        if(this.getTeam().isWhite())
            return roi;
        else
            return roi.toLowerCase(Locale.ROOT);
    }

    /**
     * @param d Vecteur de deplacement
     * @return true si  le roi n'est pas dans sa plage de deplacement / false si le roi est dans sa plage de deplacement
     * indique la plage de deplacement invalide de la piece
     */
    @Override
    public boolean plageDeplacementInvalide(Deplacement d) {
        return Math.abs(Math.abs(d.getMoveRangee()) - Math.abs(d.getMoveColonne())) < -1 ||
                Math.abs(Math.abs(d.getMoveRangee()) - Math.abs(d.getMoveColonne())) > 1 ||
                Math.abs((Math.abs(d.getMoveRangee()) * Math.abs(d.getMoveColonne()))) > 1;
    }

    /**
     * @param plateau     Echiquier a prendre en compte
     * @param destination Coordonnee d'arrivee de la piece a deplacer
     * @return false car le roi n'a jamais d'obstacle, il se deplace toujours d'une case
     */
    @Override
    public boolean obstacle(Echiquier plateau, Coord destination) {
        return false;
    }

    /**
     * @param plateau     Echiquier a prendre en compte
     * @param destination Destination de la piece a deplacer
     * @return true car le roi peut toujours attaquer un ennemie qui est zone de deplacement
     * **/
    @Override
    public boolean zoneAttaqueValide(Echiquier plateau, Coord destination) {
        return true;
    }

    /**
     * @return PiecesEnum.Roi car la piece est un Roi
     */
    @Override
    public PiecesEnum getType() {
        return PiecesEnum.Roi;
    }

}