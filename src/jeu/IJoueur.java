package jeu;

import echiquier.Echiquier;
import equipe.Team;

public interface IJoueur {
    /**
     * @param plateau Echiquier sur lequel on jouer
     * @param adv adversaire du joueur
     * @return vrai si le coup jouer est legal
     * Propose au IJoueur de jouer un tour
     */
    boolean jouer(Echiquier plateau, IJoueur adv);

    /**
     * @return l'attribut perdant du joueur
     * Verifie si l'attribut du joueur a etait mis a jour et que la partie finie sur un echec et mat
     */
    boolean aPerdu();

    /**
     * @return l'attribut nul du joueur
     * Verifie si l'attribut nul du jouer a etait mis a jouer et que la partie finie sur un echec et mat
     */
    boolean isNul();

    /**
     * @param adv adversaire du joueur
     * @return vrai si l'adversaire accepte le match nul
     * propose un match nul a l'adversaire
     */
    boolean wantMatchNul(IJoueur adv);

    /**
     * @return vrai si c'est le tour du joueur sinon faux
     */
    boolean isTurn();

    /**
     * @param tour affecte vrai a l'attribut isTurn du joueur si c'est son tour sinon non
     */
    void setTurn(boolean tour);

    /**
     * @return l'equipe du joueur
     */
    Team getTeam();

    String toString();
}
