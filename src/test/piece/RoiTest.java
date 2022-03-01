package test.piece;

import coord.Coord;
import echiquier.Echiquier;
import equipe.Team;
import org.junit.jupiter.api.Test;
import piece.PiecesEnum;
import piece.Roi;
import piece.Tour;

import static org.junit.jupiter.api.Assertions.*;

class RoiTest {
    private final static String NOM = "R";

    @Test
    void testToString() {
        Roi B = new Roi(Team.blanche);
        Roi N = new Roi(Team.noir);
        assertEquals(B.toString(),NOM);
        assertEquals(N.toString(),NOM.toLowerCase());
    }

    @Test
    void obstacle() {
        Roi Roi = new Roi(Team.noir);
        Echiquier plateau = new Echiquier();
        assertFalse(Roi.obstacle(plateau, new Coord(2,2)));
    }

    @Test
    void getType() {
        Roi roi = new Roi(Team.noir);
        assertEquals(roi.getType(), PiecesEnum.Roi);
    }
    @Test
    void deplacementValide_plageDeplacementInvalide_obstacle() {
        Echiquier plateau = new Echiquier();
        Roi roi = new Roi(Team.blanche);
        Tour advt2 = new Tour(Team.noir);
        Tour t3 = new Tour(Team.blanche);
        Tour t4 = new Tour(Team.blanche);
        Tour advt5 = new Tour(Team.noir);
        Tour t6 = new Tour(Team.blanche);
        plateau.setPiece(new Coord(3,3),roi);
        plateau.setPiece(new Coord(3,2), t3); // gauche
        plateau.setPiece(new Coord(3,4), advt2); // haut
        plateau.setPiece(new Coord(4,3), t4); // bas
        plateau.setPiece(new Coord(5,1), advt5); // haut
        plateau.setPiece(new Coord(1,5), t6); // bas
//      case vide autorisée
        assertTrue(roi.deplacementValide(plateau, new Coord(2,2)));
        assertTrue(roi.deplacementValide(plateau, new Coord(2,4)));
        assertTrue(roi.deplacementValide(plateau, new Coord(4,4)));
        assertTrue(roi.deplacementValide(plateau, new Coord(4,2)));
//       case vide hors champ
        assertFalse(roi.deplacementValide(plateau, new Coord(1,1)));
        assertFalse(roi.deplacementValide(plateau, new Coord(5,5)));
//      mange une piece
        assertTrue(roi.deplacementValide(plateau, new Coord(3,4))); // mange adv
        assertFalse(roi.deplacementValide(plateau, new Coord(4,3))); // mange ami
        assertFalse(roi.deplacementValide(plateau, new Coord(3,2))); // mange ami
        assertFalse(roi.deplacementValide(plateau, new Coord(5,5))); // tour adv hors champ
        assertFalse(roi.deplacementValide(plateau, new Coord(1,5))); // tour ami hors champ
    }
}