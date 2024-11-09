//Importation des packages
import Modele.Attaques.*;
import Controleur.*;
import Modele.Entités.*;
import Modele.Entités.Ennemis.*;
import Modele.Entités.Ennemis.Boss.*;
import Modele.Entités.Personnages.*;
import Modele.*;
import Modele.Incantation.*;
import Modele.Items.*;
import Vue.*;

/*
 * La classe Jeu est la classe principale du programme qui gère la
 * fenêtre. Elle crée une instance de FenetreJeu et la rend visible.
 */
@SuppressWarnings("unused")
public class Jeu {

	//Fenêtre de jeu
	private FenetreJeu view ;

	/*
	 * Constructeur par défaut qui crée une nouvelle instance de
	 * FenetreJeu et rend la fenêtre visible.
	 */
	public Jeu() {
		view = new FenetreJeu();
		view.setVisible(true);
	}

	/**
	 * Constructeur prenant une instance de FenetreJeu préexistante
	 * et la rend également visible.
	 * 
	 * @param fj Instance préexistante de FenetreJeu
	 */
	public Jeu(FenetreJeu fj) {
		view = new FenetreJeu(fj);
		view.setVisible(true);
	}

	/*
	 * Point d'entrée du programme. Crée une nouvelle instance
	 * de Jeu.
	 */
	public static void main(String [] args) {
		 new Jeu();
	}
}