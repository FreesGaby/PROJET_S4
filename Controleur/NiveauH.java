//Importation des packages
package Controleur;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javax.swing.*;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.*;
import Vue.*;
import Modele.Attaques.*;
import Controleur.*;
import Modele.Entités.*;
import Modele.Entités.Ennemis.*;
import Modele.Entités.Ennemis.Boss.*;
import Modele.Entités.Personnages.*;
import Modele.*;
import Modele.Incantation.*;
import historyMode.history_main.*;
import historyMode.history_object.*;
import historyMode.history_tile.*;
import historyMode.history_entity.*;


// Supprime les avertissements inutilisés dans le code
@SuppressWarnings("unused")
public class NiveauH{
    private static Timer t, tim, to;                                                     // Timers pour gérer les événements au fil du temps
    // Démarre le niveau 1
   public static void startNiv1(FenetreJeu fj, Player joueur, SuperObject ennemi) {
        // Vérifie si le niveau actuel est terminé
        
        
        // Définit la grille du plateau de jeu
		char[][] grille = {{'R', ' ', ' ', ' ', ' ', ' ',' ',' ', ' ',' ',' ', ' ',' ',' '},
                           {' ', ' ', ' ', ' ', ' ', ' ',' ',' ', ' ',' ',' ', ' ',' ',' '},
                           {' ', ' ', ' ', ' ', ' ', ' ',' ',' ', ' ',' ',' ', ' ',' ',' '},
                           {' ', ' ', ' ', ' ', ' ', ' ',' ',' ', ' ',' ',' ', ' ',' ',' '},
                           {' ', ' ', ' ', ' ', ' ', ' ',' ',' ', ' ',' ',' ', ' ',' ',' '},
                           {' ', ' ', ' ', ' ', ' ', ' ',' ',' ', ' ',' ',' ', ' ',' ',' '},
                           {' ', ' ', ' ', ' ', ' ', ' ',' ',' ', ' ',' ', ' ',' ',' ',' '},
                           {' ', ' ', ' ', ' ', ' ', ' ',' ', ' ', ' ',' ', ' ',' ',' ',' '},
                           {' ', ' ', ' ', ' ', ' ', ' ',' ',' ', ' ',' ', ' ',' ',' ',' '},
                           {' ', ' ', ' ', ' ', ' ', ' ',' ',' ', ' ',' ',' ', ' ',' ',' '},
                           {' ', ' ', ' ', ' ', ' ', ' ',' ',' ', ' ',' ',' ', ' ',' ',' '},
                           {' ', ' ', ' ', ' ', ' ', ' ',' ',' ', ' ',' ',' ', ' ',' ',' '},
                           {' ', ' ', ' ', ' ', ' ', ' ',' ',' ', ' ',' ',' ', ' ',' ',' '},
                           {' ', ' ', ' ', ' ', ' ', ' ',' ',' ', ' ',' ',' ', ' ',' ',' '}};  
        // Initialise le plateau avec la grille et d'autres éléments
        fj.setPlateau(new Plateau(grille, fj,fj.getElement(),ennemi));
        fj.setCadre(fj.getPlateau());
		fj.add(fj.getPlateau());
		//fj.getCadre().revalidate();
		//fj.getCadre().repaint();
        //fj.incantationBar();
        Afficherecran aff = new Afficherecran();
        aff.incantationBar(fj);
        fj.getCadre().revalidate();
		fj.getCadre().repaint();

        // Ajoute l'icône des malédictions en mode difficile
        fj.getPlateau().viderlist();  
        // Gère la génération aléatoire des entités selon le niveau
        joueur.initialiserteam();
        if(fj.getEstboss()){
            ennemi.combat1();  
        }else{
            ennemi.combat();
        }
            
        Dé.déperso(fj);
        for (Personnage perso : fj.getPlateau().getPerso()) {
            perso.dessinercube();
        }
        Dé.déennemi(fj);
        for (Ennemi enn : fj.getPlateau().getEnnemis()) {
            enn.dessinercube();
        }
        // Applique les malédictions en mode difficile 
        Entreniveau.proposerReroll(fj.getPlateau());
        GestionIA.setAllAimingAt(fj.getPlateau().getEnnemis(), fj);
        GestionIA.mettreAimingAtRand(fj.getPlateau().getEnnemis(), fj);

        // Timer pour gérer le tour par tour des entités
        t= new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Vérifie l'état du plateau de jeu
                if (fj.getPlateau()!=null){
                    fj.getPlateau().estmort();
                fj.getPlateau().repaint();
                Gestiondesclics.sourissurentitée(fj.getPlateau());

                // Vérifie si tous les personnages ont joué dans ce tour
                if (fj.getPlateau().getTour().size() == fj.getPlateau().getPerso().size()) {
                    Stoptimer();
                    int tempstimer = 1000;
                    // Itère à travers les ennemis pour les faire attaquer
                    for (int i = 0; i < fj.getPlateau().getEnnemis().size(); i++) {
                        final int index = i;
                        tim = new Timer(tempstimer, new ActionListener() {      
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                try {
                                    if (fj.getPlateau().getEnnemis().get(index).getAttaqueencours().getClass().getName().equals("Modele.Attaques.Attaqueregen")) {
                                        fj.getPlateau().getEnnemis().get(index).attaquer(GestionIA.findLeastHP(fj.getPlateau().getEnnemis()));
                                        fj.getPlateau().getTour().add(0);
                                        fj.getPlateau().repaint();

                                        } else {
                                            if (fj.getPlateau().getPerso().size() != 0) {
                                            int k = 0;
                                            Ennemi ennemi = fj.getPlateau().getEnnemis().get(index);
                                            Entitée personnage = fj.getPlateau().getPerso().get(0);

                                            // Fais une sélection aléatoire
                                            if(ennemi.getAimingAt() != -1){
                                                if(ennemi.getAimingAt() >= fj.getPlateau().getPerso().size()){
                                                    GestionIA.mettreAimingAtRand(fj.getPlateau().getEnnemis(), fj);
                                                    personnage = fj.getPlateau().getPerso().get(ennemi.getAimingAt());
                                                }
                                                else{
                                                    personnage = fj.getPlateau().getPerso().get(ennemi.getAimingAt());
                                                }
                                            }

                                            if (ennemi.getAttaqueencours().getClass().getName() != "Modele.Attaques.Attaqueregen") {
                                                Point positionEnnemi = Gestiondesclics.getPositionEntite(ennemi,fj.getPlateau());
                                                Point positionPersonnage = Gestiondesclics.getPositionEntite(personnage,fj.getPlateau());
                            
                                                Graphics2D g = (Graphics2D)fj.getPlateau().getGraphics();
                                                g.setStroke(new BasicStroke(5));

                                                // Choix de la couleur pour le trait de visée
                                                if (k == 0) {
                                                    g.setColor(Color.RED);
                                                    k++;
                                                } else if (k == 1) {
                                                    g.setColor(Color.BLUE);
                                                    k++;
                                                } else if (k == 2) {
                                                    g.setColor(Color.GREEN);
                                                    k++;
                                                } else if (k == 3) {
                                                    g.setColor(Color.YELLOW);
                                                    k++;
                                                } else if (k == 4) {
                                                    g.setColor(Color.ORANGE);
                                                    k++;
                                                } else if (k == 5) {
                                                    g.setColor(Color.PINK);
                                                    k++;
                                                } else if (k == 6) {
                                                    g.setColor(Color.CYAN);
                                                    k++;
                                                } else if (k == 7) {
                                                    g.setColor(Color.GRAY);
                                                    k++;
                                                } else if (k == 8) {
                                                    g.setColor(Color.MAGENTA);
                                                    k++;
                                                } else if (k == 9) {
                                                    g.setColor(Color.BLACK);
                                                    k++;
                                                }

                                                // Dessine la ligne de visée entre l'ennemi et le personnage
                                                g.drawLine(positionEnnemi.x, positionEnnemi.y, positionPersonnage.x, positionPersonnage.y);
                                                fj.getPlateau().repaint();
                                                try {
                                                    Thread.sleep(1000);
                                                }
                                                catch (InterruptedException m) {}
                                            }
                                
                                // Attaque l'ennemi ciblé
                                if (fj.getPlateau().getPerso().get(fj.getPlateau().getEnnemis().get(index).getAimingAt()).getEsquive()==false) {
                                    fj.getPlateau().getEnnemis().get(index).attaquer(personnage);
                                }
                                else {
                                    if(fj.getPlateau().getEnnemis().get(index).getAttaqueencours().getClass().getName().equals("Modele.Attaques.Attaquemalediction")) {
                                        fj.getPlateau().getEnnemis().get(index).addParticule("malediction");
                                        fj.getPlateau().getEnnemis().get(index).setPointsDeVie(fj.getPlateau().getEnnemis().get(index).getPointsDeVie()/2);
                                    }
                                }
                                fj.getPlateau().estmort();
                                Timer t = new Timer(1000, new ActionListener() {      
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        fj.getPlateau().getTour().add(0);
                                        fj.getPlateau().repaint();
                                    }
                                });
                                t.start();
                                t.setRepeats(false);
                            }
                        }
                    }
                    catch (Exception j) {
                    } 
                    }
                });
                tim.start();
                        tim.setRepeats(false);
                        tempstimer+=3000;
                    }
                }
                int time = fj.getPlateau().getEnnemis().size()*3000;
                to = new Timer(time+1000, new ActionListener() { 
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {     
                // Vérifie si tous les tours sont terminés
                if (fj.getPlateau().getTour().size()==fj.getPlateau().getEnnemis().size()+fj.getPlateau().getPerso().size()){

                    // Réinitialise les cibles des ennemis
                    GestionIA.setAllAimingAtNull(fj.getPlateau().getEnnemis());
                    fj.getPlateau().setNombretour(fj.getPlateau().getNombretour()+1);

                    // Duplique certains ennemis à chaque tour
                    if (fj.getPlateau().getNombretour()%2==0){
                        for(int i=0; i<fj.getPlateau().getEnnemis().size();i++){
                            if (fj.getPlateau().getEnnemis().get(i).getClass().getName() == "Modele.Entités.Ennemis.Boss.Boss5"){
                                fj.getPlateau().getEnnemis().get(i).dupliquer();
                            }   
                        }
                        
                        if (fj.getPlateau().getNombretour()==10){
                            for(int i=0; i<fj.getPlateau().getEnnemis().size();i++){
                                if (fj.getPlateau().getEnnemis().get(i).getClass().getName() == "Modele.Entités.Ennemis.Boss.Boss11"){
                                    fj.getPlateau().getEnnemis().get(i).dupliquer();
                                }   
                            }
                            
                        }
                    }

                    // Duplique certains ennemis spécifiques
                    for(int i=0; i<fj.getPlateau().getEnnemis().size();i++){
                        if (fj.getPlateau().getEnnemis().get(i).getClass().getName() == "Modele.Entités.Ennemis.Boss.Boss13"){
                            fj.getPlateau().getEnnemis().get(i).dupliquer();
                        }   
                    }

                    // Réinitialise les attaques des entités
                    for(int i=0; i<fj.getPlateau().getEnnemis().size();i++){
                        Ennemi enn = fj.getPlateau().getEnnemis().get(i);
                        enn.setAttaquefaite(false);
                        if (enn.getEstEmpoisonee()) {
                            enn.setPointsDeVie(enn.getPointsDeVie()-enn.getPoisonMultiplier());
                        }
                    }

                    for(int i=0; i<fj.getPlateau().getPerso().size();i++){
                        Personnage per = fj.getPlateau().getPerso().get(i);
                        per.setAttaquefaite(false);
                        if (per.getEstEmpoisonee()) {
                            per.setPointsDeVie(per.getPointsDeVie()-per.getPoisonMultiplier());
                        }
                        if(per.getVmaxtour()>0) {
                            if(per.getPointsDeVie()>per.getViemax()-per.getVmaxtour()) {
                                per.setViemax(per.getViemax()-per.getVmaxtour());
                                per.setPointsDeVie(per.getViemax());
                            }
                            else {
                                per.setViemax(per.getViemax()-per.getVmaxtour());
                            }
                            per.setVmaxtour(0);
                        }
                        per.setBouclier(0);
                        if (per.getParticules().contains("bouclier")==true) {
                            per.getParticules().remove("bouclier");
                        }
                        per.setEsquive(false);
                        if(per.getParticules().contains("esquive")){
                            per.getParticules().remove("esquive");
                        } 
                    }

                    // Réinitialise les tours
                    fj.getPlateau().getTour().clear();

                    // Applique des effets spéciaux pour certaines entités
                    for(int i=0; i<fj.getPlateau().getEnnemis().size();i++){
                        if (fj.getPlateau().getEnnemis().get(i).getClass().getName() == "Modele.Entités.Ennemis.Boss.Boss7"){
                            for (int j=0;j<fj.getPlateau().getPerso().size();j++){
                                if (fj.getPlateau().getPerso().get(j).getEstpetrifié()){ 
                                    if (fj.getPlateau().getPerso().get(j).getPointsDeVie()>0){
                                        if (fj.getPlateau().getPerso().get(j).getToursPetrifie() <= 0) {
                                            fj.getPlateau().getPerso().get(j).leverPetrification(); 
                                        }else{
                                            fj.getPlateau().getPerso().get(j).setAttaquefaite(true);
                                            fj.getPlateau().addtour();
                                        }
                                        fj.getPlateau().getPerso().get(j).setToursPetrifie(fj.getPlateau().getPerso().get(j).getToursPetrifie() - 1);
                                    }                
                                }
                            } 
                        }   
                    }    

                    // Lance le tour des entités
                    fj.getPlateau().estmort();
                    Dé.déperso(fj);
                    for (Personnage perso : fj.getPlateau().getPerso()) {
                        perso.miseajourcube();
                    }
                    Dé.déennemi(fj);
                    for (Ennemi enn: fj.getPlateau().getEnnemis()) {
                        enn.miseajourcube();
                        enn.getCube().setRoule(true,enn.getAttaqueencours().getNom());
                    }
                    fj.getPlateau().repaint();
                    Entreniveau.proposerReroll(fj.getPlateau());
                    Starttimer();

                    // Définit les cibles des ennemis pour le prochain tour
                    if(fj.getPlateau().getPerso().size() > 0){
                        GestionIA.setAllAimingAt(fj.getPlateau().getEnnemis(), fj);
                        GestionIA.mettreAimingAtRand(fj.getPlateau().getEnnemis(), fj);
                    }
        }
        
        if (fj.getPlateau().getPerso().isEmpty()){
            Stoptimer();

            LanceurNivHist.retourHistoiredefaite(fj);
            
            }
        if (fj.getPlateau().getEnnemis().isEmpty()) {
            Stoptimer();
            if (fj.getGp().getQuelboss()==1){
                fj.getGp().getPlayer().setCombatlancé(true);
                fj.getGp().getObj().remove(ennemi);
            }
            if(fj.getCombatpnjgagné()){
                fj.getGp().getObj().remove(ennemi);
                fj.setCombatpnjgagné(false);
            }

            fj.getGp().setArgent(fj.getGp().getArgent()+ennemi.getButin());
            int k=0;
            System.out.println("repeat");
            for(int i = 0; i < fj.getGp().getPlayer().getPersoEquipeAuCombat().size(); i++){
                if(fj.getGp().getPlayer().getPersoEquipeAuCombat().get(i)&&fj.getPlateau().getPerso().size()>k){
                    fj.getGp().getPlayer().getVieperso()[i]=fj.getPlateau().getPerso().get(k).getPointsDeVie();    
                    k++;
                }
                else if(fj.getGp().getPlayer().getPersoEquipeAuCombat().get(i)){
                    fj.getGp().getPlayer().getVieperso()[i]=0;
                    k++;
                }
                System.out.println(i);
                System.out.println(fj.getGp().getPlayer().getPersoEquipeAuCombat().size());
            }        
            

            LanceurNivHist.retourHistoire(fj,ennemi);
            
                   
        }
        
    }
        catch(Exception j){
        }
    }
    });
    to.start();
    to.setRepeats(false);
    }   
    }});

        t.start();
    }
    
    
    /*
     * Arrête le timer du niveau.
     */
    public static void Stoptimer(){
        t.stop();
    }

    /*
     * Démarre le timer du niveau
     */
    public static void Starttimer() {
        t.start();
    }

    /*
     * Arrête tous les timers initialisés.
     */
    public static void stopAllTimer() {
        t.stop();
        if (tim!=null) {
           tim.stop(); 
        }
        if(to!=null) {
            to.stop();
        }
    }
}