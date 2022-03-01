package test.joueur;

import coord.Coord;
import echiquier.Echiquier;
import equipe.Team;
import joueur.Humain;
import org.junit.jupiter.api.Test;
import piece.FabriquePiece;
import piece.PiecesEnum;

import static org.junit.jupiter.api.Assertions.*;

class HumainTest {
    @Test
    void test() {
        Humain blanc = new Humain(Team.blanche);
        Humain noir = new Humain(Team.noir);
        Echiquier plateau = new Echiquier();
        plateau.setPiece(new Coord(0,0), FabriquePiece.fab(PiecesEnum.Roi, Team.blanche));
        plateau.setPiece(new Coord(7,7), FabriquePiece.fab(PiecesEnum.Roi, Team.noir));
        assertFalse(blanc.coupLegal(plateau, noir, new Coord(7,7), new Coord(7,6)));
        assertTrue(noir.coupLegal(plateau, noir, new Coord(7,7), new Coord(7,6)));
        assertTrue(blanc.coupLegal(plateau, noir, new Coord(0,0), new Coord(0,1)));
        assertFalse(noir.coupLegal(plateau, noir, new Coord(0,0), new Coord(0,1)));
    }
}