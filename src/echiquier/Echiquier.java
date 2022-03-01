package echiquier;

import coord.Coord;
import equipe.Team;
import piece.FabriquePiece;
import piece.PiecesEnum;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Echiquier {
    private IPiece[][] echiquier = new IPiece[taille][taille];
    private final static int taille = 8;

    public Echiquier() {
        for (int row = 0; row < taille; row++) {
            for (int column = 0; column < taille; column++) {
                  echiquier[row][column] = FabriquePiece.fab(PiecesEnum.Vide, Team.autre);
            }
        }
    }

    /**
     * initialise la position de depart des pieces dans l'echiquier
     */
    public void init(){
        this.setPiece(new Coord(0,4), FabriquePiece.fab(PiecesEnum.Roi, Team.noir));
        this.setPiece(new Coord(1,1), FabriquePiece.fab(PiecesEnum.Tour, Team.blanche));
        this.setPiece(new Coord(2,4), FabriquePiece.fab(PiecesEnum.Roi, Team.blanche));
    }

    /**
     * @param destination de la piece
     * @param toPut piece a deplacer
     * Si les coordonnees sont correctes, place la piece a l'emplacement indique
     */
    public void setPiece(Coord destination, IPiece toPut) {
        assert(Coord.coordCorrecte(destination, this.getTaille()));
        this.echiquier[destination.getRangee()][destination.getColonne()] = toPut;      // on remplace l'ancienne piece dans l'echiquier par la nouvelle
    }

    /**
     * @return taille de l'echiquier
     * obtient la taille de l'echiquier
     */
    public int getTaille() {
        return taille;
    }

    /**
     * @param p piece a trouver
     * @return les coordonnes de la piece dans l'echiquier
     * cherche une piece dans l'echiquier et renvoi ses coordonnees
     */
    public Coord getCoord(IPiece p) {
        for (int row = 0; row < taille; row++) {
            for (int column = 0; column < taille; column++) {
                if (echiquier[row][column].equals(p))
                    return new Coord(row, column);
            }
        }
        return new Coord(taille, taille);
    }

    /**
     * @param c coordonnee de la piece
     * @return la piece
     * cherche aux coordonnees dans l'echiquier une piece et la retourne
     */
    public IPiece getPiece(Coord c) {
        assertTrue(Coord.coordCorrecte(c, this.getTaille()));
        return this.echiquier[c.getRangee()][c.getColonne()];
    }

    /**
     * @param destination coord de la piece a manger
     * @param depart coord de la piece qui mange
     * @param toMove piece qui mange
     * @return vrai si l'on mange une piece autre que le roi sinon faux
     * creer une piece vide a l'emplacement de la piece qui mange puis remplace la piece qui se fait manger par celle qui mange
     */
    public boolean eatPiece(Coord destination, Coord depart, IPiece toMove){
            if(!getPiece(destination).getType().equals(PiecesEnum.Roi)) {
                setPiece(depart, FabriquePiece.fab(PiecesEnum.Vide, Team.autre));// setPiece(destination, Piece a mettre);
                setPiece(destination, toMove);
                return true;
            }
            return false;
    }

    /**
     * @param destination coord vers ou la piece va etre deplacer
     * @param depart coord de depart de la piece
     * @param toMove piece a deplacer
     * deplace une piece vers une case vide, permet de ne pas recreer de piece vide a chaque mouvement
     */
    public void movePiece(Coord destination, Coord depart, IPiece toMove){ // pas besoin d'instancier une nouvelle piece vide a chaque mouvement
        setPiece(depart, this.getPiece(destination));
        setPiece(destination, toMove);
    }

    /**
     * @param destination coord d'arrive a tester
     * @param toMove piece a deplacer
     * @return vrai si le coup est legal, faux si il ne l'est pas
     * verifie que l'on ne deplace pas une piece vide et que les deplacements sont legaux
     */
    public boolean DeplacementTest(Coord destination, IPiece toMove){
        Coord depart = getCoord(toMove);
        if(!toMove.getType().equals(PiecesEnum.Vide) && toMove.deplacementValide(this, destination) )
            return Coord.coordCorrecte(depart, this.getTaille());
        return false;
    }

    /**
     * @param destination coord d'arrive a tester
     * @param toMove piece a deplacer
     * @return vrai si le deplacement est legal
     * effectue le deplacement a la seul condition que la piece ne tente pas de manger un roi
     */
    public boolean Deplacement(Coord destination, IPiece toMove){
        Coord depart = getCoord(toMove);
        if(getPiece(destination).getType().equals(PiecesEnum.Vide)){
            movePiece(destination, depart, toMove);
        }
        else {
            return eatPiece(destination, depart, toMove);
        }
        return true;
    }

    public String toString(){
        int cptRow = 8;
        char col = 'a';
        StringBuilder column = new StringBuilder();
        StringBuilder dash = new StringBuilder();
        StringBuilder res = new StringBuilder();
        column.append(" ");
        dash.append("  ");
        for(int i = 0; i < taille; i++){
            column.append("   ").append(col);
            dash.append(" ---");
            col++;
        }
        column.append(System.lineSeparator());
        dash.append(System.lineSeparator());
        res.append(column).append(dash);
        for(IPiece[] row : echiquier ) {
            res.append(cptRow).append(" | ");
            for (IPiece box : row) {
                res.append(box.toString()).append(" | ");
            }
            res.append(cptRow).append(System.lineSeparator()).append(dash);
            cptRow--;
        }
        res.append(column);
        return res.toString();
    }
}