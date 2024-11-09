package Controleur;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Vue.FenetreJeu;
import historyMode.history_main.GamePanel;
import historyMode.history_object.SuperObject;

/*
 * Cette classe contient des méthodes liées au lancement et à la gestion du mode Histoire dans le jeu.
 */
public class LanceurNivHist {
    
    /**
     * Lance une bataille dans le mode Histoire.
     *
     * @param gP Le GamePanel associé à la bataille.
     * @param e  L'objet SuperObject associé à la bataille.
     */
    public static void battailleCommence(GamePanel gP, SuperObject e) {
        
        FenetreJeu fJ = gP.getFj(); // Obtenir la Fenêtre du Jeu

        fJ.setNiveau(1); // Niveau de difficulté (Facile)
        fJ.setEstdanarcade(false);
        fJ.setElement(true); // Elements activées

        fJ.getContentPane().remove(fJ.getCadre());
		fJ.revalidate(); 
		fJ.repaint();
        
		fJ.setCadre(new JPanel()) ;

        fJ.getJoueur().setArgent(100);
                NiveauH.startNiv1(fJ,gP.getPlayer(),e);


    }

    /**
     * Retourne à l'écran d'Histoire après une victoire.
     *
     * @param fj  La Fenêtre du Jeu associée.
     * @param enn L'objet SuperObject associé à la victoire.
     */
    public static void retourHistoire(FenetreJeu fj, SuperObject enn) {
        fj.getGestionAudio().arreterMusique();
        fj.setEstdanarcade(false);
        fj.getContentPane().remove(fj.getCadre());
        if (fj.getPlateau()!= null) {
        }

        if (fj.getPlateau() != null) {
            fj.getContentPane().remove(fj.getPlateau());
            fj.setPlateau(null); 
        }
        fj.startHistoryModeH();
        JOptionPane.showMessageDialog(fj, "Félicitations, vous avez gagné " + enn.getButin() +"$");
    }

    /**
     * Retourne à l'écran d'Histoire après une défaite.
     *
     * @param fj La Fenêtre du Jeu associée.
     */
    public static void retourHistoiredefaite(FenetreJeu fj) {
        fj.getGestionAudio().arreterMusique();
        fj.setEstdanarcade(false);
        fj.getContentPane().remove(fj.getCadre());
        if (fj.getPlateau()!= null) {
        }

        if (fj.getPlateau() != null) {
            fj.getContentPane().remove(fj.getPlateau());
            fj.setPlateau(null); 
        }
        fj.startHistoryModeH1();
    }
}
