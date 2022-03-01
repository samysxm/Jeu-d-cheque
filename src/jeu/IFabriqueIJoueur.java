package jeu;

public interface IFabriqueIJoueur {
    /**
     * @param choix des joueurs a initialiser
     * @param joueur compteur du joueur inialise ( xieme joueur )
     * @return le IJoueur souhaite
     */
    IJoueur initJoueur(int choix, int joueur);
}
