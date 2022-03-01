package joueur;

import jeu.IJoueur;
import coord.Coord;
import echiquier.Echiquier;
import equipe.Team;

import java.util.Scanner;

public class Humain extends Joueur{
    private final static String NUL = "NUL", ABANDON = "STOP";
    private final static char debutAlphabet = 'a';

    public Humain(Team team){
       super(team);
    }

    /**
     * @param plateau Echiquier concerne par le coup a jouer
     * @param adv     adversaire du joueur concerne
     * @return la chaine de caractere contenant les coords de depart de la piece et d'arrive
     */
    @Override
    public String getCoup(Echiquier plateau, IJoueur adv) {
        System.out.println("Tour joueur : " + this);
        String coup = getScanner();
        StringBuilder res = new StringBuilder();
        if(coup.equalsIgnoreCase(ABANDON)) {
            this.setPerdant();
            return NUL;
        }
        if(coup.equalsIgnoreCase(NUL)) {
            adv.wantMatchNul(this);
            return NUL;
        }
        if(verifSyntaxe(coup)) {
            res.append(getStringCoord(coup.substring(char1,char2), plateau));
            res.append(getStringCoord(coup.substring(char2,tailleSaisie), plateau));
            return res.toString();
        }
        return null;
    }

    /**
     * @param plateau Echiquier concerne par le test
     * @param adv     adversaire du joueur
     * @param depart  coord de depart de la piece
     * @param arrive  coord d'arrive de la pice
     * @return true si le coup n'est pas l'egal / false si le coup est legal
     */
    @Override
    public boolean coupLegal(Echiquier plateau, IJoueur adv, Coord depart, Coord arrive){
        if(!plateau.getPiece(depart).getTeam().equals(this.getTeam()))
            return false;
        if(!plateau.DeplacementTest(arrive, plateau.getPiece(depart)))
            return false;
        return coupSimuleValide(plateau, depart, arrive, adv);
    }

    /**
     * @param adv adversaire du joueur
     * @return vrai si le joueur adverse accepte le match nul sinon faux
     */
    @Override
    public boolean wantMatchNul(IJoueur adv) {
        System.out.println("Joueur " + adv + " propose de finir sur un match nul.");
        System.out.println("Joueur " + this + ", pour accepter saisissez NUL, pour refuser saisissez un mot different");
        Scanner decision = new Scanner(System.in);
        String res = decision.next();
        if(res.equalsIgnoreCase(NUL)){
            this.setPat();
        }
        return res.equals(NUL);
    }

    /**
     * @param coupJoue
     * @return false si la chaine de charactere ne respecte pas la syntaxe predefinie / true si la chaine de charactere respecte la syntaxe
     */
    private boolean verifSyntaxe(String coupJoue) {
        if(coupJoue.length() != tailleSaisie)
            return false;
        if(!Character.isAlphabetic(coupJoue.charAt(char1)) || !Character.isAlphabetic(coupJoue.charAt(char2)))
            return false;
        return Character.isDigit(coupJoue.charAt(num1)) && Character.isDigit(coupJoue.charAt(num2));
    }

    /**
     * @return la chaine de caractere saisie
     */
    private static String getScanner() {
        Scanner saisie = new Scanner(System.in);
        return saisie.next();
    }

    /**
     * @param coupDepart coord a convertir
     * @param plateau sur lequel le joueur joue
     * @return converti la saisie alphabetique en saisie numerique dans une chaine
     */
    private String getStringCoord(String coupDepart, Echiquier plateau) {
        int colonne = coupDepart.charAt(char1) - debutAlphabet;
        int rangee = plateau.getTaille() - Integer.parseInt(Character.toString(coupDepart.charAt(num1))) ;
        return colonne + Integer.toString(rangee);
    }
}