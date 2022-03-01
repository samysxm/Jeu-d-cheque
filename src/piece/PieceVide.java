package piece;

import coord.Coord;
import coord.Deplacement;
import echiquier.Echiquier;
import equipe.Team;

public class PieceVide extends Piece {
    private final static String none = " ";

    public PieceVide(){
        super(Team.autre);
    }

    @Override
    public String toString() {
        return none;
    }

    /**
     * @param d Vecteur de deplacement
     * @return false car la piece vide n'a pas de plage de depalcement.
     * indique la plage de deplacement invalide de la piece
     */
    @Override
    public boolean plageDeplacementInvalide(Deplacement d) {
        return false;
    }

    /**
     * @param plateau     Echiquier a prendre en compte
     * @param destination Coordonnee d'arrivee de la piece a deplacer
     * @return true car partout où il va, ce sera bloqué.
     */
    @Override
    public boolean obstacle(Echiquier plateau, Coord destination) {
        return true;
    }

    /**
     * @param plateau     Echiquier a prendre en compte
     * @param destination Destination de la piece a deplacer
     * @return false car la piece vide n'attaque jamais.
     */
    @Override
    public boolean zoneAttaqueValide(Echiquier plateau, Coord destination) {
        return false;
    }

    /**
     * @return PiecesEnume.Vide car la piece est une Piece vide
     */
    @Override
    public PiecesEnum getType() {
        return PiecesEnum.Vide;
    }

}
