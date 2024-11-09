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

/*
 * Classe reponsable de la génération aléatoire des entités (personnages et ennemis)
 */
public class Generationaleatoireentitee{

    /**
     * Génère aléatoirement des personnages sur le plateau de jeu.
     *
     * @param fj La fenêtre du jeu dans laquelle les entités seront générées.
     */
    public static void persoaleatoire(FenetreJeu fj){
        Random random = new Random();
        int min = 1;
        int max = 2;   
        int randomNumber ;
        int min1 = 1;
        int max1 = 3;   
        int randomNumber1 ;
        int mi=1;
        int ma=5;
        int quelclass;
        quelclass= random.nextInt(ma - mi + 1) + mi;

        if (quelclass==1){
            randomNumber1= random.nextInt(max1 - min1 + 1) + min1;
            if (randomNumber1==1){
            Ajoutentitee.ajouterperso1(1, 2,1,fj.getPlateau());
        }
        if (randomNumber1==2){
            Ajoutentitee.ajouterperso9(1, 2,1,fj.getPlateau());
        }
        if (randomNumber1==3){
            Ajoutentitee.ajouterperso12(1, 2,1,fj.getPlateau());
        }

        randomNumber= random.nextInt(max - min + 1) + min;
        if (randomNumber==1){
            Ajoutentitee.ajouterperso2(1, 5,2,fj.getPlateau());
        }
        if (randomNumber==2){
            Ajoutentitee.ajouterperso7(1, 5,2,fj.getPlateau());
        }

        
        randomNumber1= random.nextInt(max1 - min1 + 1) + min1;
        if (randomNumber1==1){
            Ajoutentitee.ajouterperso3(1, 8,3,fj.getPlateau());
        }
        if (randomNumber1==2){
            Ajoutentitee.ajouterperso6(1, 8,3,fj.getPlateau());
        }
        if (randomNumber1==3){
            Ajoutentitee.ajouterperso8(1, 8,3,fj.getPlateau());
        }

        randomNumber= random.nextInt(max - min + 1) + min;
        if (randomNumber==1){
            Ajoutentitee.ajouterperso4(1, 11,4,fj.getPlateau());
        }
        if (randomNumber==2){
            Ajoutentitee.ajouterperso10(1, 11,4,fj.getPlateau());
        }
        
        }
        if (quelclass==2){
            randomNumber= random.nextInt(max - min + 1) + min;
            if (randomNumber==1){
                Ajoutentitee.ajouterperso2(1, 2,1,fj.getPlateau());
            }
            if (randomNumber==2){
                Ajoutentitee.ajouterperso7(1, 2,1,fj.getPlateau());
            }
    
            
            randomNumber1= random.nextInt(max1 - min1 + 1) + min1;
            if (randomNumber1==1){
                Ajoutentitee.ajouterperso3(1, 5,2,fj.getPlateau());
            }
            if (randomNumber1==2){
                Ajoutentitee.ajouterperso6(1, 5,2,fj.getPlateau());
            }
            if (randomNumber1==3){
                Ajoutentitee.ajouterperso8(1, 5,2,fj.getPlateau());
            }
    
            randomNumber= random.nextInt(max - min + 1) + min;
            if (randomNumber==1){
                Ajoutentitee.ajouterperso4(1, 8,3,fj.getPlateau());
            }
            if (randomNumber==2){
                Ajoutentitee.ajouterperso10(1, 8,3,fj.getPlateau());
            }
            randomNumber= random.nextInt(max - min + 1) + min;

            if (randomNumber==1){
                Ajoutentitee.ajouterperso5(1, 11,4,fj.getPlateau());
            }
            if (randomNumber==2){
                Ajoutentitee.ajouterperso11(1, 11,4,fj.getPlateau());
            }
        }
        if(quelclass==3){
            randomNumber1= random.nextInt(max1 - min1 + 1) + min1;
            if (randomNumber1==1){
            Ajoutentitee.ajouterperso1(1, 2,1,fj.getPlateau());
        }
        if (randomNumber1==2){
            Ajoutentitee.ajouterperso9(1, 2,1,fj.getPlateau());
        }
        if (randomNumber1==3){
            Ajoutentitee.ajouterperso12(1, 2,1,fj.getPlateau());
        }

            randomNumber= random.nextInt(max - min + 1) + min;
            if (randomNumber==1){
                Ajoutentitee.ajouterperso2(1, 5,2,fj.getPlateau());
            }
            if (randomNumber==2){
                Ajoutentitee.ajouterperso7(1, 5,2,fj.getPlateau());
            }
    
            randomNumber= random.nextInt(max - min + 1) + min;
            if (randomNumber==1){
                Ajoutentitee.ajouterperso4(1, 8,3,fj.getPlateau());
            }
            if (randomNumber==2){
                Ajoutentitee.ajouterperso10(1, 8,3,fj.getPlateau());
            }
            randomNumber= random.nextInt(max - min + 1) + min;

            if (randomNumber==1){
                Ajoutentitee.ajouterperso5(1, 11,4,fj.getPlateau());
            }
            if (randomNumber==2){
                Ajoutentitee.ajouterperso11(1, 11,4,fj.getPlateau());
            }
        }
        if (quelclass==4){
            randomNumber= random.nextInt(max - min + 1) + min;
            if (randomNumber==1){
                Ajoutentitee.ajouterperso4(1, 2,1,fj.getPlateau());
            }
            if (randomNumber==2){
                Ajoutentitee.ajouterperso10(1, 2,1,fj.getPlateau());
            }
            randomNumber= random.nextInt(max - min + 1) + min;

            if (randomNumber==1){
                Ajoutentitee.ajouterperso5(1, 5,2,fj.getPlateau());
            }
            if (randomNumber==2){
                Ajoutentitee.ajouterperso11(1, 5,2,fj.getPlateau());
            }
            
            randomNumber1= random.nextInt(max1 - min1 + 1) + min1;
            if (randomNumber1==1){
                Ajoutentitee.ajouterperso3(1, 8,3,fj.getPlateau());
            }
            if (randomNumber1==2){
                Ajoutentitee.ajouterperso6(1, 8,3,fj.getPlateau());
            }
            if (randomNumber1==3){
                Ajoutentitee.ajouterperso8(1, 8,3,fj.getPlateau());
            }
            randomNumber1= random.nextInt(max1 - min1 + 1) + min1;
            if (randomNumber1==1){
            Ajoutentitee.ajouterperso1(1, 11,4,fj.getPlateau());
        }
        if (randomNumber1==2){
            Ajoutentitee.ajouterperso9(1, 11,4,fj.getPlateau());
        }
        if (randomNumber1==3){
            Ajoutentitee.ajouterperso12(1, 11,4,fj.getPlateau());
        }
        }
        if (quelclass==5){
            
            randomNumber1= random.nextInt(max1 - min1 + 1) + min1;
            if (randomNumber1==1){
                Ajoutentitee.ajouterperso3(1, 2,1,fj.getPlateau());
            }
            if (randomNumber1==2){
                Ajoutentitee.ajouterperso6(1, 2,1,fj.getPlateau());
            }
            if (randomNumber1==3){
                Ajoutentitee.ajouterperso8(1, 2,1,fj.getPlateau());
            }
            randomNumber1= random.nextInt(max1 - min1 + 1) + min1;
            if (randomNumber1==1){
            Ajoutentitee.ajouterperso1(1, 5,2,fj.getPlateau());
        }
        if (randomNumber1==2){
            Ajoutentitee.ajouterperso9(1, 5,2,fj.getPlateau());
        }
        if (randomNumber1==3){
            Ajoutentitee.ajouterperso12(1, 5,2,fj.getPlateau());
        }
        randomNumber= random.nextInt(max - min + 1) + min;

            if (randomNumber==1){
                Ajoutentitee.ajouterperso5(1, 8,3,fj.getPlateau());
            }
            if (randomNumber==2){
                Ajoutentitee.ajouterperso11(1, 8,3,fj.getPlateau());
            }
            randomNumber= random.nextInt(max - min + 1) + min;
            if (randomNumber==1){
                Ajoutentitee.ajouterperso2(1, 11,4,fj.getPlateau());
            }
            if (randomNumber==2){
                Ajoutentitee.ajouterperso7(1, 11,4,fj.getPlateau());
            }
        }

        
        
        
               
                
            }
            
    

    /**
     * Génère aléatoirement des ennemis sur le plateau de jeu.
     *
     * @param fj       La fenêtre du jeu dans laquelle les entités seront générées.
     * @param quelBoss Indique le type de boss pour la génération.
     */
    public static void persoennemi(FenetreJeu fj, int quelboss ){
        Random random = new Random();
        int min = 1;
        int max = 21;
        int r=0;
        int k=0;
        int c=0;
        int randomNumber ;
        
        while (r<3){
            randomNumber= random.nextInt(max - min + 1) + min;
            
            if (r==1){
                if (randomNumber==1 && randomNumber!=k && randomNumber!=c){
                    Ajoutentitee.ajouterennemi1(11, 10,3,fj.getPlateau());
                    r=r+1;
                }
                if (randomNumber==2 && randomNumber!=k && randomNumber!=c){
                    Ajoutentitee.ajouterennemi2(11, 10,3,fj.getPlateau());
                    r=r+1;
                }
                if (randomNumber==3 && randomNumber!=k && randomNumber!=c){
                    Ajoutentitee.ajouterennemi3(11, 10,3,fj.getPlateau());
                    r=r+1;
                }
                if (randomNumber==4 && randomNumber!=k && randomNumber!=c){
                    Ajoutentitee.ajouterennemi4(11, 10,3,fj.getPlateau());
                    r=r+1;
                }
                if (randomNumber==5 && randomNumber!=k && randomNumber!=c){
                    Ajoutentitee.ajouterennemi5(11, 10,3,fj.getPlateau());
                    r=r+1;
                }
                if (randomNumber==6 && randomNumber!=k && randomNumber!=c){
                    Ajoutentitee.ajouterennemi13(11, 10,3,fj.getPlateau());
                    r=r+1;
                }
                if (randomNumber==7 && randomNumber!=k && randomNumber!=c){
                    Ajoutentitee.ajouterennemi7(11, 10,3,fj.getPlateau());
                    r=r+1;
                }
                if (randomNumber==8 && randomNumber!=k && randomNumber!=c){
                    Ajoutentitee.ajouterennemi8(11, 10,3,fj.getPlateau());
                    r=r+1;
                }
                if (randomNumber==9 && randomNumber!=k && randomNumber!=c){
                    Ajoutentitee.ajouterennemi9(11, 10,3,fj.getPlateau());
                    r=r+1;
                }
                if (randomNumber==10 && randomNumber!=k && randomNumber!=c){
                    Ajoutentitee.ajouterennemi10(11, 10,3,fj.getPlateau());
                    r=r+1;
                }
                if (randomNumber==11 && randomNumber!=k && randomNumber!=c){
                    Ajoutentitee.ajouterennemi11(11, 10,3,fj.getPlateau());
                    r=r+1;
                }
                if (randomNumber==12 && randomNumber!=k && randomNumber!=c){
                    Ajoutentitee.ajouterennemi14(11, 10,3,fj.getPlateau());
                    r=r+1;
                }
                if (randomNumber==13 && randomNumber!=k && randomNumber!=c){
                    Ajoutentitee.ajouterennemi15(11, 10,3,fj.getPlateau());
                    r=r+1;
                }
                if (randomNumber==14 && randomNumber!=k && randomNumber!=c){
                    Ajoutentitee.ajouterennemi16(11, 10,3,fj.getPlateau());
                    r=r+1;
                }
                if (randomNumber==15 && randomNumber!=k && randomNumber!=c){
                    Ajoutentitee.ajouterennemi17(11, 10,3,fj.getPlateau());
                    r=r+1;
                }
                if (randomNumber==16 && randomNumber!=k && randomNumber!=c){
                    Ajoutentitee.ajouterennemi18(11, 10,3,fj.getPlateau());
                    r=r+1;
                }
                if (randomNumber==17 && randomNumber!=k && randomNumber!=c){
                    Ajoutentitee.ajouterennemi19(11, 10,3,fj.getPlateau());
                    r=r+1;
                }
                if (randomNumber==18 && randomNumber!=k && randomNumber!=c){
                    Ajoutentitee.ajouterennemi20(11, 10,3,fj.getPlateau());
                    r=r+1;
                }
                if (randomNumber==19 && randomNumber!=k && randomNumber!=c){
                    Ajoutentitee.ajouterennemi21(11, 10,3,fj.getPlateau());
                    r=r+1;
                }
                if (randomNumber==20 && randomNumber!=k && randomNumber!=c){
                    Ajoutentitee.ajouterennemi22(11, 10,3,fj.getPlateau());
                    r=r+1;
                }
                if (randomNumber==21 && randomNumber!=k && randomNumber!=c){
                    Ajoutentitee.ajouterennemi23(11, 10,3,fj.getPlateau());
                    r=r+1;
                }
            }

            if (r==2 ){
                if (quelboss==1 ){
                    Ajoutentitee.ajouterboss1(12, 6,2,fj.getPlateau()).getId();
                    r=r+1;
                }
                if (quelboss==2 ){
                    Ajoutentitee.ajouterboss2(12, 6,2,fj.getPlateau()).getId();
                    r=r+1;
                }
                if (quelboss==3 ){
                    Ajoutentitee.ajouterboss3(12, 6,2,fj.getPlateau()).getId();
                    r=r+1;
                }
                if (quelboss==4){
                    Ajoutentitee.ajouterboss4(12, 6,2,fj.getPlateau()).getId();
                    r=r+1;
                }
                if (quelboss==5){
                    Ajoutentitee.ajouterboss5(12, 6,2,fj.getPlateau()).getId();
                    r=r+1;
                }
                if (quelboss==6 ){
                    Ajoutentitee.ajouterboss6(12, 6,2,fj.getPlateau()).getId();
                    r=r+1;
                }
                if (quelboss==7 ){
                    Ajoutentitee.ajouterboss7(12, 6,2,fj.getPlateau()).getId();
                    r=r+1;
                }
                if (quelboss==8 ){
                    Ajoutentitee.ajouterboss8(12, 6,2,fj.getPlateau()).getId();
                    r=r+1;
                }
                if (quelboss==9 ){
                    Ajoutentitee.ajouterboss9(12, 6,2,fj.getPlateau()).getId();
                    r=r+1;
                }
                if (quelboss==10){
                    Ajoutentitee.ajouterboss10(12, 6,2,fj.getPlateau()).getId();
                    r=r+1;
                }
                if (quelboss==11 ){
                    Ajoutentitee.ajouterboss11(12, 6,2,fj.getPlateau()).getId();
                    r=r+1;
                }
                if (quelboss==12 ){
                    Ajoutentitee.ajouterboss12(12, 6,2,fj.getPlateau()).getId();
                    r=r+1;
                }
                if (quelboss==13 ){
                    Ajoutentitee.ajouterboss13(12, 6,2,fj.getPlateau()).getId();
                    r=r+1;
                }
                if (quelboss==14 ){
                    Ajoutentitee.ajouterboss14(12, 6,2,fj.getPlateau()).getId();
                    r=r+1;
                }
            }
            
            if (r==0){
                if (randomNumber==1 ){
                    k=Ajoutentitee.ajouterennemi1(11, 2,1,fj.getPlateau()).getId();
                    r=r+1;
                }
                if (randomNumber==2 ){
                    k=Ajoutentitee.ajouterennemi2(11, 2,1,fj.getPlateau()).getId();
                    r=r+1;
                }
                if (randomNumber==3 ){
                    k=Ajoutentitee.ajouterennemi3(11, 2,1,fj.getPlateau()).getId();
                    r=r+1;
                }
                if (randomNumber==4 ){
                    k=Ajoutentitee.ajouterennemi4(11, 2,1,fj.getPlateau()).getId();
                    r=r+1;
                }
                if (randomNumber==5 ){
                    k=Ajoutentitee.ajouterennemi5(11, 2,1,fj.getPlateau()).getId();
                    r=r+1;
                }
                if (randomNumber==6 ){
                    k=Ajoutentitee.ajouterennemi13(11, 2,1,fj.getPlateau()).getId();
                    r=r+1;
                }
                if (randomNumber==7 ){
                    k=Ajoutentitee.ajouterennemi7(11, 2,1,fj.getPlateau()).getId();
                    r=r+1;
                }
                if (randomNumber==8 ){
                    k=Ajoutentitee.ajouterennemi8(11, 2,1,fj.getPlateau()).getId();
                    r=r+1;
                }
                if (randomNumber==9 ){
                    k=Ajoutentitee.ajouterennemi9(11, 2,1,fj.getPlateau()).getId();
                    r=r+1;
                }
                if (randomNumber==10 ){
                    k=Ajoutentitee.ajouterennemi10(11, 2,1,fj.getPlateau()).getId();
                    r=r+1;
                }
                if (randomNumber==11 ){
                    k=Ajoutentitee.ajouterennemi11(11, 2,1,fj.getPlateau()).getId();
                    r=r+1;
                }
                if (randomNumber==12){
                    k=Ajoutentitee.ajouterennemi14(11, 2,1,fj.getPlateau()).getId();
                    r=r+1;
                }
                if (randomNumber==13){
                    k=Ajoutentitee.ajouterennemi15(11, 2,1,fj.getPlateau()).getId();
                    r=r+1;
                }
                if (randomNumber==14){
                    k=Ajoutentitee.ajouterennemi16(11, 2,1,fj.getPlateau()).getId();
                    r=r+1;
                }
                if (randomNumber==15){
                    k=Ajoutentitee.ajouterennemi17(11, 2,1,fj.getPlateau()).getId();
                    r=r+1;
                }
                if (randomNumber==16){
                    k=Ajoutentitee.ajouterennemi18(11, 2,1,fj.getPlateau()).getId();
                    r=r+1;
                }
                if (randomNumber==17){
                    k=Ajoutentitee.ajouterennemi19(11, 2,1,fj.getPlateau()).getId();
                    r=r+1;
                }
                if (randomNumber==18){
                    k=Ajoutentitee.ajouterennemi20(11, 2,1,fj.getPlateau()).getId();
                    r=r+1;
                }
                if (randomNumber==19){
                    k=Ajoutentitee.ajouterennemi21(11, 2,1,fj.getPlateau()).getId();
                    r=r+1;
                }
                if (randomNumber==20){
                    k=Ajoutentitee.ajouterennemi22(11, 2,1,fj.getPlateau()).getId();
                    r=r+1;
                }
                if (randomNumber==21){
                    k=Ajoutentitee.ajouterennemi23(11, 2,1,fj.getPlateau()).getId();
                    r=r+1;
                }
                
               
                // Mise à jour des rangées minimales pour les monstres et les personnages
                int i = fj.getPlateau().getEnnemis().get(0).getRangee();
                for (Ennemi ennemi : fj.getPlateau().getEnnemis()) {
                    if (ennemi.getRangee()<i) {
                        i = ennemi.getRangee();
                    }
                }
                fj.getPlateau().setRangeeMinMonstre(i);

                int j = fj.getPlateau().getPerso().get(0).getRangee();
                for (Personnage perso : fj.getPlateau().getPerso()) {
                    if (perso.getRangee()<j) {
                        j = perso.getRangee();
                    }
                }
                fj.getPlateau().setRangeeMinPerso(j);   
            }   
        } 
    }

    /**
     * Génère aléatoirement le boss final sur le plateau de jeu.
     *
     * @param fj       La fenêtre du jeu dans laquelle le boss final sera généré.
     * @param quelBoss Indique le type de boss final pour la génération.
     */
    public static void genererbossfinal(FenetreJeu fj, int quelboss ){
        Random random = new Random();
        int min = 1;
        int max = 21;
        int r=0;
        int k=0;
        int c=0;
        int randomNumber ;
        
        while (r<3){
            randomNumber= random.nextInt(max - min + 1) + min;
            
            if (r==1){
                if (randomNumber==1 && randomNumber!=k && randomNumber!=c){
                    Ajoutentitee.ajouterennemi1(11, 10,3,fj.getPlateau());
                    r=r+1;
                }
                if (randomNumber==2 && randomNumber!=k && randomNumber!=c){
                    Ajoutentitee.ajouterennemi2(11, 10,3,fj.getPlateau());
                    r=r+1;
                }
                if (randomNumber==3 && randomNumber!=k && randomNumber!=c){
                    Ajoutentitee.ajouterennemi3(11, 10,3,fj.getPlateau());
                    r=r+1;
                }
                if (randomNumber==4 && randomNumber!=k && randomNumber!=c){
                    Ajoutentitee.ajouterennemi4(11, 10,3,fj.getPlateau());
                    r=r+1;
                }
                if (randomNumber==5 && randomNumber!=k && randomNumber!=c){
                    Ajoutentitee.ajouterennemi5(11, 10,3,fj.getPlateau());
                    r=r+1;
                }
                if (randomNumber==6 && randomNumber!=k && randomNumber!=c){
                    Ajoutentitee.ajouterennemi13(11, 10,3,fj.getPlateau());
                    r=r+1;
                }
                if (randomNumber==7 && randomNumber!=k && randomNumber!=c){
                    Ajoutentitee.ajouterennemi7(11, 10,3,fj.getPlateau());
                    r=r+1;
                }
                if (randomNumber==8 && randomNumber!=k && randomNumber!=c){
                    Ajoutentitee.ajouterennemi8(11, 10,3,fj.getPlateau());
                    r=r+1;
                }
                if (randomNumber==9 && randomNumber!=k && randomNumber!=c){
                    Ajoutentitee.ajouterennemi9(11, 10,3,fj.getPlateau());
                    r=r+1;
                }
                if (randomNumber==10 && randomNumber!=k && randomNumber!=c){
                    Ajoutentitee.ajouterennemi10(11, 10,3,fj.getPlateau());
                    r=r+1;
                }
                if (randomNumber==11 && randomNumber!=k && randomNumber!=c){
                    Ajoutentitee.ajouterennemi11(11, 10,3,fj.getPlateau());
                    r=r+1;
                }
                if (randomNumber==12 && randomNumber!=k && randomNumber!=c){
                    Ajoutentitee.ajouterennemi14(11, 10,3,fj.getPlateau());
                    r=r+1;
                }
                if (randomNumber==13 && randomNumber!=k && randomNumber!=c){
                    Ajoutentitee.ajouterennemi15(11, 10,3,fj.getPlateau());
                    r=r+1;
                }
                if (randomNumber==14 && randomNumber!=k && randomNumber!=c){
                    Ajoutentitee.ajouterennemi16(11, 10,3,fj.getPlateau());
                    r=r+1;
                }
                if (randomNumber==15 && randomNumber!=k && randomNumber!=c){
                    Ajoutentitee.ajouterennemi17(11, 10,3,fj.getPlateau());
                    r=r+1;
                }
                if (randomNumber==16 && randomNumber!=k && randomNumber!=c){
                    Ajoutentitee.ajouterennemi18(11, 10,3,fj.getPlateau());
                    r=r+1;
                }
                if (randomNumber==17 && randomNumber!=k && randomNumber!=c){
                    Ajoutentitee.ajouterennemi19(11, 10,3,fj.getPlateau());
                    r=r+1;
                }
                if (randomNumber==18 && randomNumber!=k && randomNumber!=c){
                    Ajoutentitee.ajouterennemi20(11, 10,3,fj.getPlateau());
                    r=r+1;
                }
                if (randomNumber==19 && randomNumber!=k && randomNumber!=c){
                    Ajoutentitee.ajouterennemi21(11, 10,3,fj.getPlateau());
                    r=r+1;
                }
                if (randomNumber==20 && randomNumber!=k && randomNumber!=c){
                    Ajoutentitee.ajouterennemi22(11, 10,3,fj.getPlateau());
                    r=r+1;
                }
                if (randomNumber==21 && randomNumber!=k && randomNumber!=c){
                    Ajoutentitee.ajouterennemi23(11, 10,3,fj.getPlateau());
                    r=r+1;
                }
            }
            if (r==2 ){
                    Ajoutentitee.ajouterboss15(12, 6,2,fj.getPlateau()).getId();
                    r=r+1;
            }
            
            if (r==0){
                if (randomNumber==1 ){
                    k=Ajoutentitee.ajouterennemi1(11, 2,1,fj.getPlateau()).getId();
                    r=r+1;
                }
                if (randomNumber==2 ){
                    k=Ajoutentitee.ajouterennemi2(11, 2,1,fj.getPlateau()).getId();
                    r=r+1;
                }
                if (randomNumber==3 ){
                    k=Ajoutentitee.ajouterennemi3(11, 2,1,fj.getPlateau()).getId();
                    r=r+1;
                }
                if (randomNumber==4 ){
                    k=Ajoutentitee.ajouterennemi4(11, 2,1,fj.getPlateau()).getId();
                    r=r+1;
                }
                if (randomNumber==5 ){
                    k=Ajoutentitee.ajouterennemi5(11, 2,1,fj.getPlateau()).getId();
                    r=r+1;
                }
                if (randomNumber==6 ){
                    k=Ajoutentitee.ajouterennemi13(11, 2,1,fj.getPlateau()).getId();
                    r=r+1;
                }
                if (randomNumber==7 ){
                    k=Ajoutentitee.ajouterennemi7(11, 2,1,fj.getPlateau()).getId();
                    r=r+1;
                }
                if (randomNumber==8 ){
                    k=Ajoutentitee.ajouterennemi8(11, 2,1,fj.getPlateau()).getId();
                    r=r+1;
                }
                if (randomNumber==9 ){
                    k=Ajoutentitee.ajouterennemi9(11, 2,1,fj.getPlateau()).getId();
                    r=r+1;
                }
                if (randomNumber==10 ){
                    k=Ajoutentitee.ajouterennemi10(11, 2,1,fj.getPlateau()).getId();
                    r=r+1;
                }
                if (randomNumber==11 ){
                    k=Ajoutentitee.ajouterennemi11(11, 2,1,fj.getPlateau()).getId();
                    r=r+1;
                }
                if (randomNumber==12){
                    k=Ajoutentitee.ajouterennemi14(11, 2,1,fj.getPlateau()).getId();
                    r=r+1;
                }
                if (randomNumber==13){
                    k=Ajoutentitee.ajouterennemi15(11, 2,1,fj.getPlateau()).getId();
                    r=r+1;
                }
                if (randomNumber==14){
                    k=Ajoutentitee.ajouterennemi16(11, 2,1,fj.getPlateau()).getId();
                    r=r+1;
                }
                if (randomNumber==15){
                    k=Ajoutentitee.ajouterennemi17(11, 2,1,fj.getPlateau()).getId();
                    r=r+1;
                }
                if (randomNumber==16){
                    k=Ajoutentitee.ajouterennemi18(11, 2,1,fj.getPlateau()).getId();
                    r=r+1;
                }
                if (randomNumber==17){
                    k=Ajoutentitee.ajouterennemi19(11, 2,1,fj.getPlateau()).getId();
                    r=r+1;
                }
                if (randomNumber==18){
                    k=Ajoutentitee.ajouterennemi20(11, 2,1,fj.getPlateau()).getId();
                    r=r+1;
                }
                if (randomNumber==19){
                    k=Ajoutentitee.ajouterennemi21(11, 2,1,fj.getPlateau()).getId();
                    r=r+1;
                }
                if (randomNumber==20){
                    k=Ajoutentitee.ajouterennemi22(11, 2,1,fj.getPlateau()).getId();
                    r=r+1;
                }
                if (randomNumber==21){
                    k=Ajoutentitee.ajouterennemi23(11, 2,1,fj.getPlateau()).getId();
                    r=r+1;
                }
                
                // Met à jour les rangées minimales pour les monstres et les personnages
                int i = fj.getPlateau().getEnnemis().get(0).getRangee();
                for (Ennemi ennemi : fj.getPlateau().getEnnemis()) {
                    if (ennemi.getRangee()<i) {
                        i = ennemi.getRangee();
                    }
                }
                fj.getPlateau().setRangeeMinMonstre(i);
                
                int j = fj.getPlateau().getPerso().get(0).getRangee();
                for (Personnage perso : fj.getPlateau().getPerso()) {
                    if (perso.getRangee()<j) {
                        j = perso.getRangee();
                    }
                }
                fj.getPlateau().setRangeeMinPerso(j);   
            }   
        }
        
    }
}