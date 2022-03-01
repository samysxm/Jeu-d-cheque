package test.piece;

import echiquier.IPiece;
import equipe.Team;
import org.junit.jupiter.api.Test;
import piece.FabriquePiece;
import piece.PiecesEnum;

import static org.junit.jupiter.api.Assertions.assertTrue;

class FabriquePieceTest {

    @Test
    void fab() {
        IPiece test = FabriquePiece.fab(PiecesEnum.Vide, Team.blanche);
        assertTrue(test.getType().equals(PiecesEnum.Vide));
        assertTrue(test.getTeam().equals(Team.autre));;
        test = FabriquePiece.fab(PiecesEnum.Vide, Team.noir);
        assertTrue(test.getType().equals(PiecesEnum.Vide));
        assertTrue(test.getTeam().equals(Team.autre));
        test = FabriquePiece.fab(PiecesEnum.Roi, Team.blanche);
        assertTrue(test.getType().equals(PiecesEnum.Roi));
        assertTrue(test.getTeam().isWhite());
        test = FabriquePiece.fab(PiecesEnum.Tour, Team.blanche);
        assertTrue(test.getType().equals(PiecesEnum.Tour));
        assertTrue(test.getTeam().isWhite());
        test = FabriquePiece.fab(PiecesEnum.Roi, Team.noir);
        assertTrue(test.getType().equals(PiecesEnum.Roi));
        assertTrue(test.getTeam().isBlack());
        test = FabriquePiece.fab(PiecesEnum.Tour, Team.noir);
        assertTrue(test.getType().equals(PiecesEnum.Tour));
        assertTrue(test.getTeam().isBlack());

    }
}