package jeu;

import echiquier.Echiquier;
import joueur.FabriqueIJoueur;

import java.util.Scanner;


public class Application {
    private final static int etatParti = 3;
    private static final int JOUEUR_BLANC = 0, JOUEUR_NOIR = 1;

    public static void main(String[] args) {
        IJoueur blanc, noir;
        Echiquier echiquier = new Echiquier();
        echiquier.init();
        msgMenu();
        int choixJoueur = gameSetting();
        IFabriqueIJoueur fab = new FabriqueIJoueur();
        blanc = fab.initJoueur(choixJoueur, JOUEUR_BLANC);
        noir = fab.initJoueur(choixJoueur, JOUEUR_NOIR);
        assert(noir != null && blanc != null);

        msgPreambule();
        IJoueur Actif = blanc;
        blanc.setTurn(true);
        if(etatParti(blanc,noir) != 0){
            System.out.println(echiquier);
        }
        while(etatParti(blanc,noir) == 0) {
            if(etatParti(blanc,noir) == 0)
                System.out.println(echiquier);
            while (!Actif.jouer(echiquier, getInactif(blanc, noir)))
                if (etatParti(blanc, noir) != 0)
                    break;
            Actif = swap(blanc, noir);
        }

        msgFin(etatParti(blanc,noir), blanc, noir);
    }

    /**
     * @return le choix de l'utilisateur
     * propose une saisie a l'utilisateurs lui permettant de choisir entre 3 valeurs a savoir 1, 2 et 3 pour determiner le type de joueur de la partie
     */
    private static int gameSetting() {
        Scanner menu = new Scanner(System.in);
        int choixJoueur;
        while (true) {
            String choixMenu = menu.next();
            if(!Character.isDigit(choixMenu.charAt(0))) {
                System.out.println("Veuillez saisir une valeur numérique");
                continue;
            }
            choixJoueur = Integer.parseInt(choixMenu);
            switch (choixJoueur) {
                case 1:
                case 2:
                case 3:
                    return choixJoueur;
                default:
                    System.out.println("Veuillez saisir une valeur entre 1 et 3");
            }
        }
    }

    /**
     * @param blanc joueur de l'equipe blanche
     * @param noir joueur de l'equipe noire
     * @return retourne le IJoueur qui ne joue pas
     * obtient le joueur qui n'est pas en train de jouer
     */
    private static IJoueur getInactif(IJoueur blanc, IJoueur noir) {
        return (!blanc.isTurn()) ? blanc : noir;
    }

    /**
     * @param blanc joueur de l'equipe blanche
     * @param noir joueur de l'equipe noire
     * @return retourne le IJoueur qui joue le prochain tour
     * inverse le joueur actif avec le joueur inactif et renvoi le nouveau joueur actif
     */
    private static IJoueur swap(IJoueur blanc, IJoueur noir) {
        blanc.setTurn(!blanc.isTurn());
        noir.setTurn(!noir.isTurn());
        return (blanc.isTurn()) ? blanc : noir;
    }

    /**
     * @param blanc joueur de l'equipe blanche
     * @param noir joueur de l'equipe noire
     * @return 3 si le blanc a perdu, 2 si le noir a perdu, 1 match nul et 0 si la partie est en cours
     * Permet de connaître l'avancement de la partie
     */
    private static int etatParti(IJoueur blanc, IJoueur noir) {
        int fin = etatParti;
        if(blanc.aPerdu())
            return fin;
        fin--;
        if(noir.aPerdu())
            return fin;
        fin--;
        if(blanc.isNul() || noir.isNul())
            return fin;
        fin--;
        return fin;
    }

    /**
     * Affiche un menu au joueur permettant de choisir le type de joueur pour la partie
     */
    private static void msgMenu(){
        System.out.println("Choisissez le nombre associe à votre partie : ");
        System.out.println("1 = Joueur contre Joueur");
        System.out.println("2 = Joueur contre IA");
        System.out.println("3 = IA contre IA");
    }

    /**
     * Indique au joueur la méthode pour abandonner et la méthode pour demander match nul
     */
    private static void msgPreambule(){
        System.out.println("Pour proposer a votre adversaire un match nul saisissez : NUL");
        System.out.println("Pour abandonner saisissez STOP");
    }

    /**
     * @param situation entier renvoyant la raison de fin de parti ( voir ligne 92 )
     * @param blanc joueur de l'equipe blanche
     * @param noir joueur de l'equipe noire
     * Affiche le message de fin avec la raison de la fin de partie
     */
    private static void msgFin(int situation, IJoueur blanc, IJoueur noir) {
        switch(situation){
            case 1:
                System.out.println("Match nul");
                break;
            case 2:
                System.out.println("BRAVO JOUEUR " + blanc.toString() + " VOUS AVEZ VAINCU JOUEUR " + noir.toString());
                break;
            case 3:
                System.out.println("BRAVO JOUEUR " + noir.toString() + " VOUS AVEZ VAINCU JOUEUR " + blanc.toString());
        }
    }
}