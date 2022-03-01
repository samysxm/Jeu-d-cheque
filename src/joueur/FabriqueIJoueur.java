package joueur;

import jeu.IFabriqueIJoueur;
import jeu.IJoueur;
import equipe.Team;

public class FabriqueIJoueur implements IFabriqueIJoueur {
    /**
     * @param choix  des joueurs a initialiser
     * @param joueur compteur du joueur inialise ( xieme joueur )
     * @return
     * initialise le joueur
     */
    public IJoueur initJoueur(int choix, int joueur) {
        switch (choix) {
            case 1:
                return (joueur == 0) ? new Humain(Team.blanche) : new Humain(Team.noir);
            case 2:
                return (joueur == 0) ? new Humain(Team.blanche) : new IA(Team.noir);
            case 3:
                return (joueur == 0) ? new IA(Team.blanche) : new IA(Team.noir);
        }
        return null;
    }

}
