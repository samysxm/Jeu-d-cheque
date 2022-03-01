package test.piece;

import coord.Coord;
import echiquier.Echiquier;
import echiquier.IPiece;
import org.junit.jupiter.api.Test;
import piece.PieceVide;
import piece.PiecesEnum;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class PieceVideTest {
    private final static String NOM = " ";

    @Test
    void testToString() {
        PieceVide pV = new PieceVide();
         assertEquals(pV.toString(), NOM);
    }

    @Test
    void getType() {
        PieceVide pV = new PieceVide();
        assertEquals(pV.getType(), PiecesEnum.Vide );
    }

    @Test
    void deplacementValide_plageDeplacementInvalide_obstacle() {
        Echiquier plateau = new Echiquier();
        IPiece pV = plateau.getPiece(new Coord(3,3));
        PieceVide pv = (PieceVide) pV;
        assertFalse(pv.deplacementValide(plateau, new Coord(4,8)));
    }

}