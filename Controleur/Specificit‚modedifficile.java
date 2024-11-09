package Controleur;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;


import java.util.ArrayList;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;

import java.io.IOException;
import Modele.Attaques.*;
import Controleur.*;
import Modele.Entités.*;
import Modele.Entités.Ennemis.*;
import Modele.Entités.Ennemis.Boss.*;
import Modele.Entités.Personnages.*;
import Modele.*;
import Modele.Incantation.*;
import Modele.Maledictions.Malediction;
import historyMode.*;
import historyMode.history_entity.*;
import historyMode.history_main.*;
import historyMode.history_object.*;
import historyMode.history_tile.*;

import javax.imageio.ImageIO;
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
import Modele.Entités.Personnages.*;
import Modele.*;
import Modele.Incantation.*;
import Modele.Maledictions.Malediction;

/*
 * La classe SpecificitéModeDifficile gère les spécificités du mode de difficulté difficile.
 */
@SuppressWarnings("unused")
public class Specificitémodedifficile{

    /**
     * Limite le joueur à un reroll pendant la partie.
     *
     * @param fj La fenêtre de jeu.
     */
    public static void reRollDifficile(FenetreJeu fj) { 
        fj.getJoueur().setNbrReroll(fj.getJoueur().getNbrReroll()-1);
    }

    /**
     * Empêche l'utilisation d'incantations de soin pendant la partie.
     *
     * @param fj La fenêtre de jeu.
     */
    public static void noHeal(FenetreJeu fj) {

        // Récupération de la liste des incantations sur le plateau
        ArrayList<Incantation> listInc;
        listInc = fj.getPlateau().getIncantations();
        
        // Parcoure la liste des incantations
        Iterator<Incantation> iterator = listInc.iterator();
        while (iterator.hasNext()) {
            Incantation inc = iterator.next();

            // Si l'incantation est de type soin, elle est retirée de la liste
            if (inc instanceof InctHeal) {
                iterator.remove();
            }
        }
    }
    
    /**
     * Affiche les informations sur une malédiction.
     *
     * @param malus La malédiction dont les informations seront affichées.
     */
    public static void afficherMalus(Malediction malus) {
        // Dimensions de la fenêtre d'information
        int width = 85;
        int height = 69;

        // Crée la fenêtre d'information
        JFrame infoFrame = new JFrame(malus.getNom());

        // Ajoute une étiquette de texte avec la description de la malédiction
        JLabel textLabel = new JLabel("<html>Description : " + malus.getDescription() + "</html>");
        textLabel.setBounds(width / 2, 0, width * 3, height);
        textLabel.setForeground(Color.WHITE);
        infoFrame.add(textLabel);

        // Propriétés de la fenêtre
        infoFrame.setSize(width * 3, height * 2);
        infoFrame.setLocationRelativeTo(null);
        infoFrame.setLayout(null);
        infoFrame.setResizable(false);

        // Rend la fenêtre visible
        infoFrame.setVisible(true);
    }
}