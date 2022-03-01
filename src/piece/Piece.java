package piece;

import coord.Coord;
import coord.Deplacement;
import echiquier.Echiquier;
import echiquier.IPiece;
import equipe.Team;

public abstract class Piece implements IPiece{
    private final Team team;
    /**
     * @param crew equipe de la piece
     */
    public Piece(Team crew) {
        team = crew;
    }
    /**
     * @return format d'affichage de la piece
     */
    public abstract String toString();
    /**
     * @param d Vecteur de deplacement
     * @return vrai si la plage de déplacement est invalide, faux si la plage de déplacement est valide
     * indique la plage de deplacement invalide de la piece
     */
    public abstract boolean plageDeplacementInvalide(Deplacement d);
    /**
     * @param plateau Echiquier a prendre en compte
     * @param destination Coordonnee d'arrivee de la piece a deplacer
     * @return vrai si un obstacle empeche le deplacement
     */
    public abstract boolean obstacle(Echiquier plateau, Coord destination);
    /**
     * @param plateau Echiquier a prendre en compte
     * @param destination Destination de la piece a deplacer
     * @return vrai si les coordonnees correspondent a une zone d'attaque possible pour la piece
     */
    public abstract boolean zoneAttaqueValide(Echiquier plateau, Coord destination);
    /**
     * @return l'equipe de la piece
     */
    public Team getTeam(){
        return this.team;
    }
    /**
     * @param plateau Echiquier a prendre en compte
     * @param c Coordonnee d'arrivee
     * @return vrai si le deplacement respecte toutes les conditions a l'exception de la mise en echec du roi
     */
    @Override
    public boolean deplacementValide(Echiquier plateau, Coord c) {
        Deplacement d = new Deplacement(plateau.getCoord(this), c);
        if(!Coord.coordCorrecte(c, plateau.getTaille()))
            return false;
        if(this.getTeam().equals((plateau.getPiece(c).getTeam())))
            return false;
        if(!this.zoneAttaqueValide(plateau, c))
            return false;
        if(this.plageDeplacementInvalide(d))
            return false;
        if(obstacle(plateau,c))
            return false;
        return plateau.getCoord(this) != c;
    }
    /**
     * @param plateau Echiquier pris en compte
     * @param adversaire Equipe des pieces menaçantes pour la piece
     * @return vrai si la piece est attaque par une piece ennemi, faux si elle ne l'est pas
     */
    @Override
    public boolean estAttaque(Echiquier plateau, Team adversaire) {
        IPiece test;
        Coord coordTest = plateau.getCoord(this);
        for(int colonne = 0; colonne < plateau.getTaille(); colonne++)
            for (int rangee = 0; rangee < plateau.getTaille(); rangee++) {
                if (colonne == coordTest.getColonne() && rangee == coordTest.getRangee())
                    continue;
                test = plateau.getPiece(new Coord(rangee, colonne));
                if (test.getTeam().equals(adversaire))
                    if (test.deplacementValide(plateau, coordTest))
                        return true;
            }
        return false;
    }
}
