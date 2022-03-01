package test.piece;

import coord.Coord;
import echiquier.Echiquier;
import equipe.Team;
import org.junit.jupiter.api.Test;
import piece.PiecesEnum;
import piece.Tour;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class TourTest {
    private final static String NOM = "T";

    @Test
    void testToString() {
        Tour blanche = new Tour(Team.blanche);
        assertEquals(blanche.toString(), NOM);
        Tour noire = new Tour(Team.noir);
        assertEquals(noire.toString(), NOM.toLowerCase(Locale.ROOT));
    }

    @Test
    void getType() {
        Tour tour = new Tour(Team.blanche);
        assertEquals(tour.getType(), PiecesEnum.Tour);
    }

    @Test
    void deplacementValide_plageDeplacementInvalide_obstacle() {
        Echiquier plateau = new Echiquier();
        Tour tour = new Tour(Team.blanche);
        Tour t1 = new Tour(Team.noir);
        Tour t2 = new Tour(Team.noir);
        Tour t3 = new Tour(Team.noir);
        Tour t4 = new Tour(Team.noir);
        plateau.setPiece(new Coord(3,3), tour);
        plateau.setPiece(new Coord(3,1), t3); // gauche
        plateau.setPiece(new Coord(3,6), t1); // droite
        plateau.setPiece(new Coord(6,3), t2); // haut
        plateau.setPiece(new Coord(1,3), t4); // bas
//      Droite
        assertTrue(tour.deplacementValide(plateau, new Coord(3,5)));
        assertFalse(tour.deplacementValide(plateau, new Coord(3,7))); // obstacle
//      Gauche
        assertTrue(tour.deplacementValide(plateau, new Coord(3,2)));
        assertFalse(tour.deplacementValide(plateau, new Coord(3,0))); // obstacle
//      Bas
        assertTrue(tour.deplacementValide(plateau, new Coord(2,3)));
        assertFalse(tour.deplacementValide(plateau, new Coord(0,3))); // obstacle
//      Haut
        assertTrue(tour.deplacementValide(plateau, new Coord(5,3)));
        assertFalse(tour.deplacementValide(plateau, new Coord(7,3))); // obstacle
//      Haut droite
        assertFalse(tour.deplacementValide(plateau, new Coord(0,7)));
//      Haut gauche
        assertFalse(tour.deplacementValide(plateau, new Coord(0,0)));
//      Bas droite
        assertFalse(tour.deplacementValide(plateau, new Coord(7,7)));
//      Bas gauche
        assertFalse(tour.deplacementValide(plateau, new Coord(7,0)));
//      Mange adv
        assertTrue(tour.deplacementValide(plateau, new Coord(3,1)));
    }
}