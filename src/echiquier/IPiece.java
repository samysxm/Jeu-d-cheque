package echiquier;

import coord.Coord;
import equipe.Team;
import piece.PiecesEnum;

public interface IPiece {

    /**
     * @return l'equipe de la piece
     */
    Team getTeam();

    /**
     * @param e l'echiquier sur lequel la piece se trouve
     * @param c les coordonnees ou la piece veut aller
     * @return vrai si le coup est legal sinon faux
     * Permet de savoir les deplacements autorisee d'une piece
     */
    boolean deplacementValide(Echiquier e, Coord c);

    /**
     * @param plateau l'echiquier sur lequel la piece se trouve
     * @param adversaire adversaire de la piece
     * @return vrai si une piece adverse peut manger la piece allie sinon faux
     * permet de savoir si le roi est en echec et peut servir a creer un IA intelligent deplaçant les pieces importantes menacees
     */
    boolean estAttaque(Echiquier plateau, Team adversaire); // offrir cette methode a toutes les classes permettrait a l'avenir de mettre en surbrillance les pions en danger dans un mode de jeu facile

    /**
     * @return renvoi le type de la piece
     */
    PiecesEnum getType();

}
