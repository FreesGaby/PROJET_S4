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
 * Cette classe gère l'intelligence artificielle (IA) des ennemis dans le jeu.
 */
public class GestionIA{

    /**
     * Trouve l'ennemi avec le moins de points de vie dans une liste d'ennemis.
     *
     * @param list  La liste d'ennemis parmi lesquels rechercher.
     * @return      L'ennemi avec le moins de points de vie.
     */
    public static Ennemi findLeastHP(ArrayList<Ennemi> list){
        Ennemi minHpEntite = list.get(0);
        for(int i = 1; i < list.size(); i++){
            if(list.get(i).getPointsDeVie() < minHpEntite.getPointsDeVie()){
                minHpEntite = list.get(i);
            }
        }
        return minHpEntite;
    }
   
    /**
     * Configure la cible de tous les ennemis en fonction de leurs attaques.
     *
     * @param list La liste d'ennemis à configurer.
     * @param fj   La fenêtre du jeu associée.
     */
    public static void setAllAimingAt(ArrayList<Ennemi> list, FenetreJeu fj){
        int maxDegat = 0;
        ArrayList<Ennemi> ennemiNeeded = new ArrayList<>();
        for(Ennemi e : list){
            if(e.getAttaqueencours().getNom().equals("faucheuse")){
                e.setAimingAt(findMaxHPPersonnage(fj.getPlateau().getPerso()));
            }
            if(e.getAttaqueencours().getNom().equals("malediction")){
                e.setAimingAt(findMaxHPPersonnage(fj.getPlateau().getPerso()));
            }
            if(e.getAttaqueencours().getNom().equals("dégats")||e.getAttaqueencours().getNom().equals("vampirisme")){
                if(findHpPersonnageMaxDegat(fj.getPlateau().getPerso(), e.getAttaqueencours().getAmelioration(), list) != -1){
                    e.setAimingAt(findHpPersonnageMaxDegat(fj.getPlateau().getPerso(), e.getAttaqueencours().getAmelioration(), list));
                }
                else{
                    maxDegat += e.getAttaqueencours().getAmelioration();
                    ennemiNeeded.add(e);
                }
            }
        }
        int cible = findHpPersonnageMaxDegat(fj.getPlateau().getPerso(), maxDegat, list);
        if(cible != -1){
            for(int i = 0; i<ennemiNeeded.size(); i++){
                ennemiNeeded.get(i).setAimingAt(cible);
            }
        }
    }

    /**
     * Trouve l'index du personnage avec le plus de points de vie dans une liste de personnages.
     *
     * @param list La liste de personnages parmi lesquels rechercher.
     * @return L'index du personnage avec le plus de points de vie.
     */
    public static int findMaxHPPersonnage(ArrayList<Personnage> list){
        int maxHpEntite = 0;
        for(int i = 1; i<list.size(); i++){
            if(list.get(i).getPointsDeVie() > list.get(maxHpEntite).getPointsDeVie()){
                maxHpEntite = i;
            }
        }
        return maxHpEntite;
    }

    /**
     * Trouve l'index du personnage avec le plus de points de vie restants, compte tenu des dégâts max.
     *
     * @param list          La liste de personnages parmi lesquels rechercher.
     * @param sommeDegat    La somme des dégâts max.
     * @param list2         La liste d'ennemis pour vérifier si la cible est déjà visée.
     * @return              L'index du personnage ciblé ou -1 s'il n'y en a pas.
     */
    public static int findHpPersonnageMaxDegat(ArrayList<Personnage> list, int sommeDegat, ArrayList<Ennemi> list2){
        int entiteVisee = -1;
        boolean isAlreadyAimed = false;
        for(int i = 0; i<list.size(); i++){
            if(list.get(i).getPointsDeVie() <= sommeDegat){
                for(int j = 0; j<list2.size(); j++){
                    if (i == list2.get(j).getAimingAt()) {
                        isAlreadyAimed = true;
                    }
                }
                if(entiteVisee == -1){
                    if(!isAlreadyAimed){
                        entiteVisee = i;
                    }   
                }
                else{
                    if(list.get(i).getPointsDeVie() > list.get(entiteVisee).getPointsDeVie() && !isAlreadyAimed){
                        entiteVisee = i;
                    }
                } 
            }
            isAlreadyAimed = false;
        }
        return entiteVisee;
    }

    /**
     * Réinitialise la cible de tous les ennemis à -1 (aucune cible).
     *
     * @param list La liste d'ennemis à réinitialiser.
     */
    public static void setAllAimingAtNull(ArrayList<Ennemi> list){
        for(Ennemi e : list){
            e.setAimingAt(-1);
        }
    }

    /**
     * Attribue une cible aléatoire à tous les ennemis qui n'ont pas de cible.
     *
     * @param list La liste d'ennemis à mettre à jour.
     * @param fj   La fenêtre du jeu associée.
     */
    public static void mettreAimingAtRand(ArrayList<Ennemi> list, FenetreJeu fj){
        Random random = new Random();
        int min = 0;
        int max = fj.getPlateau().getPerso().size()-1;
        int randomNumber;
        for(Ennemi e : list){
            if(e.getAimingAt() == -1 || e.getAimingAt() > max){
                randomNumber = random.nextInt(max - min + 1) + min;
                e.setAimingAt(randomNumber);
            }
        }
    }

}