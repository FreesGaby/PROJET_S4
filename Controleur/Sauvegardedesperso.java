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

// Gère la sauvegarde et la régénération des personnages
public class Sauvegardedesperso{

    // Charge les personnages dans le plateau de jeu
    public static void chargerPerso(FenetreJeu fj) {

        // Vide la liste des entités du plateau
        fj.getPlateau().viderlist();

        // Charger chaque personnage depuis la liste statique Niveau1.persos
        for (int i = 0; i < Niveau1.persos.size(); i++) {
            Personnage p = Niveau1.persos.get(i);
            fj.getPlateau().chargerPerso(p);
        }
    }
    
    // Régénère les personnages après une partie
    public static void regenPerso() {
        
        // Parcourt la liste statique Niveau1.persos
        for (Personnage perso : Niveau1.persos) {

            // Nettoie les particules du personnage
            ((Entitée)perso).getParticules().clear();;

            // Réinitialise les états d'empoisonnement
            ((Entitée)perso).setEmpoisonnee(false);
            ((Entitée)perso).setPoisonMultiplier(0);

            // Réinitialise les fardeaux
            ((Entitée)perso).setAFardeau(false);
            ((Entitée)perso).setFardeau(null);

            // Réinitialise l'état d'attaque du personnage
            perso.setAttaquefaite(false);

            // Si le personnage a 0 points de vie, le ramène à la moitié de sa vie maximale + 1
            if (perso.getPointsDeVie() <= 0) {
                perso.setPointsDeVie((perso.getViemax() / 2) + 1);
            } else {

                // Sinon, ramène les points de vie au maximum
                perso.setPointsDeVie(perso.getViemax());
            }
        }
    }

}