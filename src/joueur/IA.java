package joueur;

import jeu.IJoueur;
import coord.Coord;
import echiquier.Echiquier;
import echiquier.IPiece;
import equipe.Team;

import java.util.ArrayList;
import java.util.Random;

public class IA extends Joueur{
    private final static int NB_COUP_CALCULE = 10; // changer cette valeur pour étendre le jeu ou le réduire
    public IA(Team team){
        super(team);
    }

    /**
     * @param plateau Echiquier concerne par le test
     * @param adv     adversaire du joueur
     * @param depart  coord de depart de la piece
     * @param arrive  coord d'arrive de la pice
     * @return true constamment car l'IA calcul uniquement ses coups parmis les coups legaux
     */
    @Override
    public boolean coupLegal(Echiquier plateau, IJoueur adv, Coord depart, Coord arrive) {
        return true;
    }

    /**
     * @param plateau Echiquier concerne par le coup a jouer
     * @param adv     adversaire du joueur concerne
     * @return une chaine de caractere contenant les coups jouables
     * Fais le tour de l'echiquier et cherche NB_COUP_CALCULE nombre de deplacement possible pour le joueur
     * les stocks et en choisis aléatoirement un puis le renvoi
     */
    @Override
    public String getCoup(Echiquier plateau, IJoueur adv) {
        IPiece test;
        IPiece roi = getKing(plateau);
        ArrayList<Coord> depart= new ArrayList<>();
        ArrayList<Coord> arrivee= new ArrayList<>();
        Random rand = new Random();
        int indexCoord;
        for(int rangee = 0; rangee < plateau.getTaille(); rangee++)
            for(int colonne = 0; colonne < plateau.getTaille(); colonne++) {
                if(depart.size() == NB_COUP_CALCULE)
                    break;
                test = plateau.getPiece(new Coord(rangee, colonne));
                if(plateau.DeplacementTest(new Coord(rangee, colonne), roi))
                    if(coupSimuleValide(plateau, plateau.getCoord(roi), new Coord(rangee, colonne), adv)){

                        depart.add(new Coord(plateau.getCoord(roi).getRangee(), plateau.getCoord(roi).getColonne()));
                        arrivee.add(new Coord(rangee, colonne));
                    }
                if(test.getTeam().equals(this.getTeam())){
                    if(isKing(plateau, rangee, colonne))
                        continue;
                    for(int rawEnd = 0; rawEnd < plateau.getTaille(); rawEnd++)
                        for (int colEnd = 0; colEnd < plateau.getTaille(); colEnd++)
                            if(plateau.DeplacementTest(new Coord(rawEnd, colEnd), test))
                                if(coupSimuleValide(plateau, new Coord(rangee, colonne), new Coord(rawEnd, colEnd), adv)) {
                                    depart.add(new Coord(rangee, colonne));
                                    arrivee.add(new Coord(rawEnd, colEnd));
                                }
                }
            }
        indexCoord = rand.nextInt(arrivee.size());
        StringBuilder resultat = new StringBuilder().append(depart.get(indexCoord).getColonne()).append(depart.get(indexCoord).getRangee());
        return resultat.append(arrivee.get(indexCoord).getColonne()).append(arrivee.get(indexCoord).getRangee()).toString();
    }

    /**
     * @param adv adversaire du joueur
     * @return toujours vrai car un IA acceptera toujours une demande de NUL
     */
    @Override
    public boolean wantMatchNul(IJoueur adv) {
        this.setPat();
        return true;
    }
}
