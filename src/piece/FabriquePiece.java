package piece;

import echiquier.IPiece;
import equipe.Team;

public class FabriquePiece {
    /**
     * @param piece type de la piece voulut
     * @param equipe de la piece voulut
     * @return la piece avec le type correspondant et l'equipe donnee en parametre
     */
    public static IPiece fab(PiecesEnum piece, Team equipe){
        if(piece.equals(PiecesEnum.Vide))
            return vide();
        if(piece.equals(PiecesEnum.Roi))
            return king(equipe);
        if(piece.equals(PiecesEnum.Tour))
            return tour(equipe);
        return vide();
    }

    /**
     * @return une piece vide
     */
    private static IPiece vide() {
        return new PieceVide();
    }

    /**
     * @param team
     * @return un Roi avec l'equipe donnee en parametre
     */
    private static IPiece king(Team team) {
        return new Roi(team) ;
    }

    /**
     * @param team
     * @returnun Tour avec l'equipe donnee en parametre
     */
    private static IPiece tour(Team team) {
        return new Tour(team);
    }
}
