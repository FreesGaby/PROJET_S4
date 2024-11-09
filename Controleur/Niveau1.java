//Importation des packages
package Controleur;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javax.swing.*;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.*;
import Vue.*;
import historyMode.history_object.SuperObject;
import Modele.Attaques.*;
import Controleur.*;
import Modele.Entités.*;
import Modele.Entités.Ennemis.*;
import Modele.Entités.Ennemis.Boss.*;
import Modele.Entités.Personnages.*;
import Modele.*;
import Modele.Incantation.*;
import Modele.Items.Item1;

// Supprime les avertissements inutilisés dans le code
@SuppressWarnings("unused")
public class Niveau1{
    private static Timer t, tim, to;                                            // Timer pour gérer les événements au fil du temps
    public static int levelNumber = 0;                                          // Numéro 
    public static ArrayList<Personnage> persos = new ArrayList<Personnage>();   // Liste des personnages 
    private static JButton sauvegarderButton;
    public static boolean isDamageIncreased, isRegenIncreased, isCostIncreased, isPVincreased, isBossBuffed, isArmorIncreased, isDouane, isSpeedIncreased, isIncantationDecreased, isItemsLimited, isDelay, is1PV;
    public static int multDamage, multRegen, multCost, multPV, multBoss, multArmor, multIncantations, multItem, multDelay;

    /**
     * Démarre le niveau 1.
     *
     * @param fj         La Fenêtre du Jeu associée.
     * @param vagues     Le nombre de vagues dans le niveau.
     * @param difficile  Indique si le niveau est en mode difficile.
     * @param modeHist   Indique si le mode histoire est activé.
     * @param ennemi     L'objet SuperObject représentant l'ennemi.
     */
   public static void startNiv1(FenetreJeu fj, int vagues, boolean difficile, boolean modeHist,SuperObject ennemi) {
        // Efface l'historique des dégâts
        fj.clearHistoDegat();

        // Vérifie si le niveau actuel est terminé
        if (levelNumber>vagues){

            // Affiche l'écran de fin de jeu
            Afficherecran.afficherEcrandefin(fj);
        } else{
        
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

        // Vérifie si c'est le dernier niveau
        if (levelNumber==vagues){
            fj.setBossfinal(true);
        }
        // Initialise le plateau avec la grille et d'autres éléments
        fj.setPlateau(new Plateau(grille, fj,fj.getElement(),ennemi));
        fj.setCadre(fj.getPlateau());
		fj.add(fj.getPlateau());
        
        Buttonmenu.buttonnmenu(fj);
        ajouterBoutonSauvegarde(fj);
        Afficherecran aff = new Afficherecran();
        aff.incantationBar(fj);
        Afficherecran.afficherTitreNiv(fj);
        Affichagesucces.afficherSucces(fj);
		
        
        //fj.incantationBar();
        
        

        // Ajoute l'icône des malédictions en mode difficile
        if(difficile) {
            aff.maledictIcon(fj);
            fj.getPlateau().setDifficile(true);
        }

        
        fj.getPlateau().viderlist();  

        if (vagues==20){
            Specificitémodedifficile.reRollDifficile(fj);
        }

        // Gère la génération aléatoire des entités selon le niveau
        if (Niveau1.levelNumber == 0) {
            Generationaleatoireentitee.persoaleatoire(fj);
        } else {
            fj.getPlateau().getPerso().clear();
            Sauvegardedesperso.regenPerso();
            Sauvegardedesperso.chargerPerso(fj);
        }

        // Génère les ennemis ou le boss final selon le niveau
        if (levelNumber==vagues){
            Generationaleatoireentitee.genererbossfinal(fj,fj.getPlateau().getQuelboss());
        } else{
            Generationaleatoireentitee.persoennemi(fj,fj.getPlateau().getQuelboss());
        }

        for (Ennemi e : fj.getPlateau().getEnnemis()) {
            if (isDamageIncreased) {
                for (Attaque a : e.getAttaques()) {
                    a.setAmelioration(a.getAmelioration() + multDamage);
                }
            }
            if (isRegenIncreased) {
                for (Attaque a : e.getAttaques()) {
                    if (a.getNom().equals("regen")) a.ameliorer();
                }
            }
            if (isCostIncreased) {
                for (Incantation inc : fj.getPlateau().getIncantations()) inc.setPrix(inc.getPrix() + multCost);
            }
            if (isPVincreased) {
                e.setPointsDeVie(e.getPointsDeVie() + multPV);
                e.setViemax(e.getViemax() + multPV);
            }
            if (isBossBuffed) {
                
            }
            if (isArmorIncreased) {
                e.setBouclier(multArmor);
            }
            if (isDouane) {
                for (Personnage perso : fj.getPlateau().getPerso()) perso.setItemappliqué(null);
            }
            if (isSpeedIncreased) {

            }
            if (isIncantationDecreased) {

            }
            if (isItemsLimited) {

            } 
            if (isDelay) {

            }
            if (is1PV) {
                for (Personnage perso : fj.getPlateau().getPerso()) {
                    perso.setPointsDeVie(1);
                    perso.setViemax(1);
                }
            }
        }
        

        Dé.déperso(fj);
        for (Personnage perso : fj.getPlateau().getPerso()) {
            perso.dessinercube();
        }
        Dé.déennemi(fj);
        for (Ennemi enn : fj.getPlateau().getEnnemis()) {
            enn.dessinercube();
        }

        Font police = null;
        if (fj.getFont() != null) police = fj.geFont().deriveFont(Font.PLAIN, 15f);

        // Propose des récompenses aux personnages après le combat
        if (levelNumber == 0) {
            Entreniveau.proposerItemsAuxPersonnages2(fj.getPlateau());
            
            Entreniveau.choix(fj);

        } else {
            Affichagesucces.afficherRecompense2(fj);
            Entreniveau.choix(fj);
        }
        
        // Applique les malédictions en mode difficile
        if(difficile)
            fj.getPlateau().getMalusEnCours().maudir(fj.getPlateau());
        
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
                                    fj.getHistoDegat().add(fj.getPlateau().getEnnemis().get(index).getAttaqueencours().getAmelioration());
                                    fj.getHistoDegatImageAttaquant().add(fj.getPlateau().getEnnemis().get(index).getImage().toString());
                                    fj.getHistoDegatImageVictime().add(GestionIA.findLeastHP(fj.getPlateau().getEnnemis()).getImage().toString());
                                    fj.getHistoDegatouHeal().add(true);
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
                                            if(fj.getPlateau().getEnnemis().get(index).getAttaqueencours().getNom().equals("dégats")){
                                                fj.getHistoDegat().add(fj.getPlateau().getEnnemis().get(index).getAttaqueencours().getAmelioration());
                                                fj.getHistoDegatImageAttaquant().add(fj.getPlateau().getEnnemis().get(index).getImage().toString());
                                                fj.getHistoDegatImageVictime().add(personnage.getImage().toString());
                                                fj.getHistoDegatouHeal().add(false);
                                            }
                                            if(fj.getPlateau().getEnnemis().get(index).getAttaqueencours().getNom().equals("vampirisme")){
                                                fj.getHistoDegat().add(fj.getPlateau().getEnnemis().get(index).getAttaqueencours().getAmelioration());
                                                fj.getHistoDegatImageAttaquant().add(fj.getPlateau().getEnnemis().get(index).getImage().toString());
                                                fj.getHistoDegatImageVictime().add(personnage.getImage().toString());
                                                fj.getHistoDegatouHeal().add(false);
                                            }
                                            if(fj.getPlateau().getEnnemis().get(index).getAttaqueencours().getNom().equals("malediction")){
                                                fj.getHistoDegat().add(personnage.getPointsDeVie()/2);
                                                fj.getHistoDegatImageAttaquant().add(fj.getPlateau().getEnnemis().get(index).getImage().toString());
                                                fj.getHistoDegatImageVictime().add(personnage.getImage().toString());
                                                fj.getHistoDegatouHeal().add(false);
                                            }
                                            if(fj.getPlateau().getEnnemis().get(index).getAttaqueencours().getNom().equals("faucheuse")){
                                                fj.getHistoDegat().add(personnage.getPointsDeVie());
                                                fj.getHistoDegatImageAttaquant().add(fj.getPlateau().getEnnemis().get(index).getImage().toString());
                                                fj.getHistoDegatImageVictime().add(personnage.getImage().toString());
                                                fj.getHistoDegatouHeal().add(false);
                                            }
                                            if (fj.getPlateau().getEnnemis().get(index).getElement() == 2 && personnage.getElement() == 1 
                                            || fj.getPlateau().getEnnemis().get(index).getElement() == 3 && personnage.getElement() == 2 
                                            || fj.getPlateau().getEnnemis().get(index).getElement() == 1 && personnage.getElement() == 3) {
                                                fj.getHistoEfficacité().add(" C'était super efficace !");
                                            } 
                                            else if (fj.getPlateau().getEnnemis().get(index).getElement() == 1 && personnage.getElement() == 2 
                                            || fj.getPlateau().getEnnemis().get(index).getElement() == 2 && personnage.getElement() == 3 
                                            || fj.getPlateau().getEnnemis().get(index).getElement() == 3 && personnage.getElement() == 1) {
                                                fj.getHistoEfficacité().add(" Ce n'était pas très efficace...");
                                            } else fj.getHistoEfficacité().add(" ");
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

                                for (int i = 0; i < fj.getPlateau().getEnnemis().size(); i++) {
                                    if (fj.getPlateau().getEnnemis().get(i).getClass().getName() == "Modele.Entités.Ennemis.Boss.Boss5") {
                                        ((Boss5)fj.getPlateau().getEnnemis().get(i)).decrementerToursAvantDuplication();
                                        fj.getPlateau().repaint();
                                    }
                                }

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
                                        fj.getHistoDegat().add(enn.getPoisonMultiplier());
                                        fj.getHistoDegatImageAttaquant().add("Image/poison.png");
                                        fj.getHistoDegatImageVictime().add(enn.getImage().toString());
                                        fj.getHistoDegatouHeal().add(false);
                                        enn.setPointsDeVie(enn.getPointsDeVie()-enn.getPoisonMultiplier());
                                    }
                                }

                                for(int i=0; i<fj.getPlateau().getPerso().size();i++){
                                    Personnage per = fj.getPlateau().getPerso().get(i);
                                    per.setAttaquefaite(false);
                                    if (per.getEstEmpoisonee()) {
                                        fj.getHistoDegat().add(per.getPoisonMultiplier());
                                        fj.getHistoDegatImageAttaquant().add("Image/poison.png");
                                        fj.getHistoDegatImageVictime().add(per.getImage().toString());
                                        fj.getHistoDegatouHeal().add(false);
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
                                Afficherecran.afficherEcranGameOver(fj);
                                fj.clearHistoDegat();
                            } else if (fj.getPlateau().getEnnemis().isEmpty()) {
                                Niveau1.Stoptimer();
                                Afficherecran.afficherEcranWin(fj);
                                fj.clearHistoDegat();
                                if (levelNumber <= vagues) {       
                                    levelNumber++;
                                   
                                    if(difficile) {
                                        startNiv1(fj, vagues, true, false,ennemi);
                                    }else {
                                        startNiv1(fj, vagues, false, false,ennemi);
                                    }  
                                    
                                                
                                }          
                            }
                        } catch(Exception j){
                        }
                    }
            });
            to.start();
            to.setRepeats(false);
        }           
    }});
        t.start();
    }
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
        if(t!=null) {
            t.stop();
        }
        if (tim!=null) {
           tim.stop(); 
        }
        if(to!=null) {
            to.stop();
        }
    }
    
    /**
     * Ajoute le bouton de sauvegarde à la fenêtre de jeu.
     *
     * @param fj La Fenêtre du Jeu associée.
     */
    public static void ajouterBoutonSauvegarde(FenetreJeu fj) {

        // Crée le bouton de sauvegarde
        sauvegarderButton = new JButton("Sauvegarder");
        sauvegarderButton.setFocusable(false);

        // Ajoute un écouteur pour gérer le clic sur le bouton
        sauvegarderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Appelle la méthode de sauvegarde
                sauvegarderPartie(fj.getPlateau(), fj);
            }
        });

        // Ajoute le bouton à la fenêtre de jeu
        fj.add(sauvegarderButton, BorderLayout.NORTH);
        fj.setVisible(true);
    }

    private static void sauvegarderPartie(Plateau plateau, FenetreJeu fj) {
        SauvegardePartie.sauvegarder(plateau, persos, levelNumber, Joueur.getInventaire(), fj.getJoueur().getArgent(), Joueur.getSucces());
    }

    public void setPerso(ArrayList<Personnage> persos) {
        Niveau1.persos = persos;
    }

    public void setLevelNumber(int levelNumber) {
        Niveau1.levelNumber = levelNumber;
    }

    public static void reinitialiserUnfair() {
        Niveau1.is1PV = false;
        Niveau1.isArmorIncreased = false;
        Niveau1.isBossBuffed = false;
        Niveau1.isCostIncreased = false;
        Niveau1.isDamageIncreased = false;
        Niveau1.isDelay = false;
        Niveau1.isDouane = false;
        Niveau1.isIncantationDecreased = false;
        Niveau1.isItemsLimited = false;
        Niveau1.isPVincreased = false;
        Niveau1.isRegenIncreased = false;
        Niveau1.isSpeedIncreased = false;
    }

}
