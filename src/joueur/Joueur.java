package joueur;

import jeu.IJoueur;
import coord.Coord;
import echiquier.Echiquier;
import echiquier.IPiece;
import equipe.Team;
import piece.FabriquePiece;
import piece.PiecesEnum;

import java.util.Locale;

public abstract class Joueur implements IJoueur {
    private final Team equipe;
    private final static String NUL = "NUL";
    protected final static int char1 = 0, num1 = 1, char2 = 2, num2 = 3, tailleSaisie = 4;
    private boolean perdant = false;
    private boolean nul = false;
    private boolean isTurn = false;


    public Joueur(Team team){
        equipe = team;
    }

    /**
     * @param plateau Echiquier concerne par le coup a jouer
     * @param adv adversaire du joueur concerne
     * @return les coordonnes du coup sous format de chaine de caracteres
     * Obtient une chaine de caractères des coordonnes du coup joué avec un synthaxe correct
     */
    public abstract String getCoup(Echiquier plateau, IJoueur adv);

    /**
     * @param plateau Echiquier concerne par le test
     * @param adv adversaire du joueur
     * @param depart coord de depart de la piece
     * @param arrive coord d'arrive de la pice
     * @return vrai si le coup est legal sinon faux
     */
    public abstract boolean coupLegal(Echiquier plateau, IJoueur adv, Coord depart, Coord arrive);

    /**
     * @param plateau Echiquier sur lequel on jouer
     * @param adv adversaire du joueur
     * @return vrai si le joueur a pu faire un coup, sinon faux
     * permet au joueur de jouer un coup si les conditions sont réunis et accepte son coup si il est legal
     */
    @Override
    public boolean jouer(Echiquier plateau, IJoueur adv) {
        if (isMat(plateau, adv))
            return false;
        if(isNul())
            return false;
        String coupJoue = getCoup(plateau, adv);
        if(coupJoue == null) {
            System.out.println("coup illegal, veuillez recommencer");
            return false;
        }
        if(coupJoue.equalsIgnoreCase(NUL)) {
            return false;
        }
        Coord coordDepart = getCoord(coupJoue.substring(char1,char2));
        Coord coordArrive = getCoord(coupJoue.substring(char2,tailleSaisie));
        if(!coupLegal(plateau, adv, coordDepart, coordArrive)) {
            System.out.println("coup illegal, veuillez recommencer");
            return false;
        }
        if(!plateau.Deplacement(coordArrive, plateau.getPiece(coordDepart))){
            System.out.println("coup illegal, veuillez recommencer");
            return false;
        }
        return !materielInsuffisant(plateau);
    }

    /**
     * @param plateau dans lequel on cherche le materiel
     * @return vrai si le materiel est insuffisant pour finir sur autre chose qu'un match nul, sinon faux
     */
    private boolean materielInsuffisant(Echiquier plateau) {
        int nbPieceBlanche = 0;
        int nbPieceNoir = 0;
        for(int rangee = 0; rangee < plateau.getTaille(); rangee++)
            for(int colonne = 0; colonne < plateau.getTaille(); colonne++) {
                if(plateau.getPiece(new Coord(rangee, colonne)).getTeam().isBlack())
                    nbPieceNoir++;
                if(plateau.getPiece(new Coord(rangee, colonne)).getTeam().isWhite())
                    nbPieceBlanche++;
            }
        if(nbPieceBlanche == 1 && nbPieceNoir == 1) {
            System.out.println(plateau);
            this.nul = true;
        }
        return nul;

    }

    /**
     * @return vrai si le joueur a perdu sinon faux
     */
    @Override
    public boolean aPerdu() {
        return perdant;
    }

    /**
     * @return vrai s'il y a match nul, sinon faux
     */
    @Override
    public boolean isNul() {
        return nul;
    }

    /**
     * @return vrai si c'est le tour du joueur sinon faux
     */
    @Override
    public boolean isTurn() {
        return isTurn;
    }

    /**
     * @param tour affecte l'attribut tour du joueur
     */
    @Override
    public void setTurn(boolean tour) {
        isTurn = tour;
    }

    /**
     * @return l'equipe du joueur
     */
    @Override
    public Team getTeam() {
        return equipe;
    }

    @Override
    public String toString(){
        String res;
        if(getTeam().isBlack())
            res = "NOIR";
        else
            res = "BLANC";
        return res;
    }

    /**
     * le joueur est pat
     */
    protected void setPat(){
        this.nul = true;
    }

    /**
     * definit le joueur comme perdant
     */
    protected void setPerdant(){
        this.perdant = true;
    }

    /**
     * @param plateau Echiquier sur lequel on test
     * @param coordDepart coord de depart de la piece
     * @param coordArrive coord d'arrivee de la piece
     * @param adv adv du joueur
     * @return vrai si le coup est legal faux sinon
     * Simule un coup pour voir si le roi est en echec après celui-ci puis renvoi vrai si oui, sinon non
     * Remet les pieces dans l'etat d'avant appel de la methode
     */
    protected boolean coupSimuleValide(Echiquier plateau, Coord coordDepart, Coord coordArrive, IJoueur adv){
        boolean res;
        IPiece temp = plateau.getPiece(coordArrive);
        plateau.setPiece(coordArrive, plateau.getPiece(coordDepart));
        plateau.setPiece(coordDepart, FabriquePiece.fab(PiecesEnum.Vide, Team.autre));
        res = this.isEchec(plateau, adv);
        plateau.setPiece(coordDepart, plateau.getPiece(coordArrive)); // on remet la piece deplace a sa place
        plateau.setPiece(coordArrive, temp); // on remet aussi la piece mange
        return !res;
    }

    /**
     * @param plateau Echiquier sur lequel on cherche
     * @param rangee de la piece a verifier
     * @param colonne de la piece a verifier
     * @return vrai si la piece est un roi, sinon faux
     */
    protected boolean isKing(Echiquier plateau, int rangee, int colonne) {
        if(plateau.getPiece(new Coord(rangee, colonne)).getType().equals(PiecesEnum.Roi))
            return plateau.getPiece(new Coord(rangee, colonne)).getTeam().equals(this.getTeam());
        return false;
    }

    /**
     * @param plateau Echiquier pris en compte
     * @return le roi du joueur
     * cherche le roi dans le plateau
     */
    protected IPiece getKing(Echiquier plateau) {
        for(int rangee = 0; rangee < plateau.getTaille(); rangee++)
            for(int colonne = 0; colonne < plateau.getTaille(); colonne++)
                if (isKing(plateau, rangee, colonne))
                    return plateau.getPiece(new Coord(rangee, colonne));
        return null;
    }

    /**
     * @param plateau Echiquier pris en compte
     * @param adv adversaire du joueur
     * @return vrai si le joueur est mat sinon faux
     */
    private boolean isMat(Echiquier plateau, IJoueur adv) {
        IPiece roi = getKing(plateau), test;
        for(int rangee = 0; rangee < plateau.getTaille(); rangee++)
            for(int colonne = 0; colonne < plateau.getTaille(); colonne++){
                test = plateau.getPiece(new Coord(rangee, colonne));
                if (roiPeutSeLibererSeul(plateau, adv, roi, rangee, colonne))
                    return false;
                if(test.getTeam().equals(this.getTeam()))
                    if (canSaveKing(plateau, adv, test, rangee, colonne))
                        return false;
            }
        try {
            if (getKing(plateau).estAttaque(plateau, adv.getTeam())) {
                perdant = true;
                return true;
            }
            nul = true;
        } catch(NullPointerException e){
            perdant = true;
        }
        return false;
    }

    /**
     * @param plateau Echiquier pris en compte
     * @param adv adversaire du joueur
     * @param test piece sur laquel la simulation est faite
     * @param rangee de la piece tester
     * @param colonne de la piece tester
     * @return vrai si une piece peut s'interposer ou manger la piece adversaire mettant en echec le roi
     */
    private boolean canSaveKing(Echiquier plateau, IJoueur adv, IPiece test, int rangee, int colonne) {
        if(isKing(plateau, rangee, colonne)) // les deplacements du roi ont deja etait tester
            return false;
        for(int rawEnd = 0; rawEnd < plateau.getTaille(); rawEnd++)
            for (int colEnd = 0; colEnd < plateau.getTaille(); colEnd++)
                if(plateau.DeplacementTest(new Coord(rawEnd, colEnd), test))
                    if (coupSimuleValide(plateau, new Coord(rangee, colonne), new Coord(rawEnd, colEnd), adv))
                        return true;
        return false;
    }

    /**
     * @param plateau Echiquier pris en compte
     * @param adv adversaire du joueur
     * @param roi du joueur
     * @param rangee de test
     * @param colonne de test
     * @return vrai si le roi peut se liberer seul sinon non
     */
    private boolean roiPeutSeLibererSeul(Echiquier plateau, IJoueur adv, IPiece roi, int rangee, int colonne) {
        if(plateau.DeplacementTest(new Coord(rangee, colonne), roi))
            return coupSimuleValide(plateau, plateau.getCoord(roi), new Coord(rangee, colonne), adv);
        return false;
    }

    /**
     * @param plateau Echiquier sur lequel la recherche est faite
     * @param adv du joueur
     * @return vrai si le joueur est en echec, faux s'il ne l'est pas
     */
    private boolean isEchec(Echiquier plateau, IJoueur adv) {
        try {
            return this.getKing(plateau).estAttaque(plateau, adv.getTeam());
        } catch(NullPointerException e){
            System.out.println("joueur " + this +  " n'a pas de roi, veillez à bien initialiser les pieces de la partie");
            perdant = true;
        }
        return false;
    }

    /**
     * @param coupDepart chaine de caractere a changer en coordonnees
     * @return les coordonnees converti
     * Converti une chaine de caractere avec un format correcte en coordonnes
     */
    private Coord getCoord(String coupDepart) {
        int colonne = Integer.parseInt(Character.toString(coupDepart.charAt(char1)));
        int rangee = Integer.parseInt(Character.toString(coupDepart.charAt(num1)));
        return new Coord(rangee, colonne);
    }

}
