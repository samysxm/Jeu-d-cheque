package test.joueur;

import coord.Coord;
import echiquier.Echiquier;
import equipe.Team;
import joueur.IA;
import org.junit.jupiter.api.Test;
import piece.FabriquePiece;
import piece.PiecesEnum;

import static org.junit.jupiter.api.Assertions.*;

class IATest {

    @Test
    void test1() {
        IA test = new IA(Team.blanche);
        IA adv = new IA(Team.noir);
        Echiquier plateau = new Echiquier();
        assertTrue(test.coupLegal(plateau, adv, new Coord(0, 0), new Coord(17,98)));
    }

    @Test
    void getCoup() {
        IA test = new IA(Team.blanche);
        IA notest = new IA(Team.noir);
        Echiquier plateau = new Echiquier();
        plateau.setPiece(new Coord(0,0), FabriquePiece.fab(PiecesEnum.Roi, Team.blanche));
        plateau.setPiece(new Coord(7,0), FabriquePiece.fab(PiecesEnum.Roi, Team.noir));
        plateau.setPiece(new Coord(0,0), FabriquePiece.fab(PiecesEnum.Tour, Team.blanche));
        assertTrue(test.jouer(plateau, notest));
        assertTrue(test.jouer(plateau, notest));
        assertTrue(test.jouer(plateau, notest));
        assertTrue(test.jouer(plateau, notest));
    }

    @Test
    void wantMatchNul() {
        IA test = new IA(Team.blanche);
        IA adv = new IA(Team.noir);
        assertTrue(test.wantMatchNul(adv));
    }
}