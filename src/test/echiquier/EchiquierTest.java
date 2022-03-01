package test.echiquier;

import coord.Coord;
import echiquier.Echiquier;
import equipe.Team;
import org.junit.jupiter.api.Test;
import piece.Roi;
import piece.Tour;

import static org.junit.jupiter.api.Assertions.*;

class EchiquierTest {

    @Test
    void Echiquier() {
        Echiquier plateau = new Echiquier();
        Roi test = new Roi(Team.blanche);
        Coord coordroi = new Coord(0,0);
        Coord coordtour = new Coord(1,1);
        plateau.setPiece(coordroi,  test);
        assertEquals(plateau.getCoord(test).getColonne(), coordroi.getColonne());
        assertEquals(plateau.getCoord(test).getRangee(), coordroi.getRangee());
        assertEquals(plateau.getPiece(coordroi), test);
        Tour adv = new Tour(Team.noir);
        plateau.setPiece(coordtour,adv);
        assertTrue(plateau.eatPiece(coordtour, coordroi, test));
        plateau.movePiece(coordroi, coordtour, test);
        assertFalse(plateau.eatPiece(coordroi, coordtour, adv));
        plateau.movePiece(coordroi, coordtour, test);
        assertEquals(plateau.getPiece(coordroi), test);
        assertFalse(plateau.DeplacementTest(new Coord(7,7), test));
        assertTrue(plateau.DeplacementTest(new Coord(0,1), test));
        assertTrue(plateau.Deplacement(new Coord(7, 7), test));
    }

    @Test
    void testAffichage() {
        Echiquier test = new Echiquier();
        String resAttendu =
                "    a   b   c   d   e   f   g   h\n" +
                "   --- --- --- --- --- --- --- ---\n" +
                "8 |   |   |   |   |   |   |   |   | 8\n" +
                "   --- --- --- --- --- --- --- ---\n" +
                "7 |   |   |   |   |   |   |   |   | 7\n" +
                "   --- --- --- --- --- --- --- ---\n" +
                "6 |   |   |   |   |   |   |   |   | 6\n" +
                "   --- --- --- --- --- --- --- ---\n" +
                "5 |   |   |   |   |   |   |   |   | 5\n" +
                "   --- --- --- --- --- --- --- ---\n" +
                "4 |   |   |   |   |   |   |   |   | 4\n" +
                "   --- --- --- --- --- --- --- ---\n" +
                "3 |   |   |   |   |   |   |   |   | 3\n" +
                "   --- --- --- --- --- --- --- ---\n" +
                "2 |   |   |   |   |   |   |   |   | 2\n" +
                "   --- --- --- --- --- --- --- ---\n" +
                "1 |   |   |   |   |   |   |   |   | 1\n" +
                "   --- --- --- --- --- --- --- ---\n" +
                "    a   b   c   d   e   f   g   h";
        System.out.println(resAttendu);
        assertFalse(test.toString().equals(resAttendu));
    }
}