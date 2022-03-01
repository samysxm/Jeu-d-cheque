package test.coord;

import coord.Coord;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CoordTest {
private final static int TAILLE = 8;
    @Test
    void coordCorrecte() {
        Coord test = new Coord(8,9);
        assertFalse(Coord.coordCorrecte(test, TAILLE));
        test = new Coord(-4,-7);
        assertFalse(Coord.coordCorrecte(test, TAILLE));
        test = new Coord(7,4);
        assertTrue(Coord.coordCorrecte(test, TAILLE));
    }
}