package piece;

import coord.Coord;
import coord.Deplacement;
import echiquier.Echiquier;
import equipe.Team;

import java.util.Locale;

public class Tour extends Piece {
    private final static String tour = "T";

    public Tour(Team crew) {
        super(crew);
    }

    @Override
    public String toString() {
        if(this.getTeam().isWhite())
            return tour;
        return tour.toLowerCase(Locale.ROOT);
    }

    /**
     * @param d Vecteur de deplacement
     * @return vrai si le deplacement de la tour est valide sinon faux
     * ou en horizontale.
     * indique la plage de deplacement invalide de la piece
     */
    @Override
    public boolean plageDeplacementInvalide(Deplacement d) {
        return d.getMoveColonne() != 0 && d.getMoveRangee() != 0; //
    }

    /**
     * @param plateau Echiquier a prendre en compte
     * @param finish coordonne d'arrive
     * @return true, si il y a un obstacle sinon false
     */
    @Override
    public boolean obstacle(Echiquier plateau, Coord finish) {
        Coord start = plateau.getCoord(this);       //obtention des coordonnées de la piece a deplacer
        if(start.getRangee() == finish.getRangee()) {
            return rechercheObstacle(plateau, start.getColonne(), finish.getColonne(), start.getRangee(), true); // vrai = deplacement en colonne
        }
        return rechercheObstacle(plateau, start.getRangee(), finish.getRangee(), start.getColonne(), false); // faux = deplacement en rangee
    }

    /**
     * @param plateau     Echiquier a prendre en compte
     * @param destination Destination de la piece a deplacer
     * @return constamment true car le deplacement de la tour concorde avec sa zone d'attaque
     */
    @Override
    public boolean zoneAttaqueValide(Echiquier plateau, Coord destination) {
        return true;
    }

    /**
     * @return le type de la piece à savoir TOUR
     */
    @Override
    public PiecesEnum getType() {
        return PiecesEnum.Tour;
    }

    /**
     * @param plateau Echiquier sur lequel la recherche est faite
     * @param depart Coord de départ de la piece
     * @param arrive Coord d'arrivee de la piece
     * @param c3 coord fixe
     * @param dim vrai si le deplacement est en colonne, faux si non
     * @return vrai si un obstacle est sur la route de la piece sinon faux
     */
    private boolean rechercheObstacle(Echiquier plateau, int depart, int arrive, int c3, boolean dim) {
        if(depart + 1 == arrive || depart -1 == arrive)    // si le deplacement ne se fait que d'une case
            return false;           // renvoyer qu'il n'y a pas d'obstacle entre le depart et l'arrivee
        int start, finish;
        if(depart > arrive) {   // permet de partir du plus petit indice +1 et d'arrive au plus grand
            start = arrive + 1; // partir de la destination
            finish = depart;    // arrive a la piece
        }
        else {
            start = depart + 1; // partir de la piece a deplacer
            finish = arrive;    // arrive a la piece de destination
        }
        if(dim){    // si le deplacement est en colonne
            for(int i = start; i < finish; i++){    // pour chacune des colonnes dans la rangee c3
                if(!plateau.getPiece(new Coord(c3, i)).getType().equals(PiecesEnum.Vide)) //verifier si la case est vide
                    return true; // renvoi qu'un obstale est trouve
            }
        }
        else {  // si le deplacement est en rangee
            for(int i = start; i < finish; i++){ // pour chacune des rangee dans la colonne c3
                if(!plateau.getPiece(new Coord(i, c3)).getType().equals(PiecesEnum.Vide)) //verifier si la case est vide
                    return true; // renvoi qu'un obstale est trouve
            }
        }
        return false; // renvoi qu'aucun obstale est trouve
    }
}