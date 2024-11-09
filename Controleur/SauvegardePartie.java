package Controleur;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Array;
import java.util.ArrayList;

import Vue.*;
import Modele.Attaques.*;
import Controleur.*;
import Modele.Entités.*;
import Modele.Entités.Ennemis.*;
import Modele.Entités.Ennemis.Boss.*;
import Modele.Entités.Personnages.*;
import Modele.*;
import Modele.Items.*;
import Modele.Incantation.*;

/*
 * La classe SauvegardePartie gère la sauvegarde et le chargement d'une partie.
 */
public class SauvegardePartie {

    /**
     * Sauvegarde la partie en écrivant les objets dans un fichier.
     *
     * @param plateau      Le plateau de jeu.
     * @param personnages  La liste des personnages.
     * @param levelNumber  Le numéro du niveau.
     * @param items        La liste des items.
     * @param argent       La quantité d'argent.
     * @param succes       La liste des succès.
     */
    public static void sauvegarder(Plateau plateau, ArrayList<Personnage> personnages, int levelNumber, ArrayList<Item> items, int argent, ArrayList<Succes> succes) {
        try {

            // Vérifie et crée le dossier de sauvegarde s'il n'existe pas
            File savesFolder = new File("saves");
            if (!savesFolder.exists()) savesFolder.mkdir();

            // Utilise try-with-resources pour garantir la fermeture automatiques des ressources
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(savesFolder.getAbsolutePath() + "/partie.sav"))) {

                // Écrit les objets dans le fichier de sauvegarde
                oos.writeObject(plateau);
                oos.writeObject(personnages);
                oos.writeInt(levelNumber);
                oos.writeObject(items);
                oos.writeInt(argent);
                oos.writeObject(succes);
                System.out.println("Partie sauvegardée avec succès.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Charge le plateau à partir d'un fichier de sauvegarde.
     *
     * @param fileName Le nom du fichier de sauvegarde.
     * @return Le plateau chargé.
     */
    public static Plateau chargerPlateau(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("saves/" + fileName))) {

            // Lit et retourne le plateau
            Plateau plateau = (Plateau) ois.readObject();
            return plateau;
        } catch (FileNotFoundException e) {
            System.out.println("Le fichier " + fileName + " n'a pas été trouvé.");
            e.printStackTrace();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Charge la liste des personnages à partir d'un fichier de sauvegarde.
     *
     * @param fileName Le nom du fichier de sauvegarde.
     * @return La liste des personnages chargée.
     */
    public static ArrayList<Personnage> chargerPersonnages(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("saves/" + fileName))) {

            // Lit et retourne la liste des personnages
            ois.readObject();   // Ignore le premier objet (plateau)
            @SuppressWarnings("unchecked")
            ArrayList<Personnage> personnages = (ArrayList<Personnage>) ois.readObject();
            return personnages;
        } catch (FileNotFoundException e) {
            System.out.println("Le fichier " + fileName + " n'a pas été trouvé.");
            e.printStackTrace();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Charge le numéro du niveau à partir d'un fichier de sauvegarde.
     *
     * @param fileName Le nom du fichier de sauvegarde.
     * @return Le numéro du niveau chargé.
     */
    public static int chargerLevelNumber(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("saves/" + fileName))) {

            // Lit et retourne le numéro du niveau
            ois.readObject();   // Ignore le plateau
            ois.readObject();   // Ignore la liste des personnages
            int levelNumber = ois.readInt();
            return levelNumber;
        } catch (FileNotFoundException e) {
            System.out.println("Le fichier " + fileName + " n'a pas été trouvé.");
            e.printStackTrace();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Charge l'inventaire à partir d'un fichier de sauvegarde.
     *
     * @param fileName Le nom du fichier de sauvegarde.
     * @return La liste des items chargée.
     */
    public static ArrayList<Item> chargerInventaire(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("saves/" + fileName))) {

            // Lit et retourne la liste des items
            ois.readObject();   // Ignore le plateau
            ois.readObject();   // Ignore la liste des personnages
            ois.readInt();      // Ignore le numéro du niveau
            @SuppressWarnings("unchecked")
            ArrayList<Item> items = (ArrayList<Item>) ois.readObject();
            return items;
        } catch (FileNotFoundException e) {
            System.out.println("Le fichier " + fileName + " n'a pas été trouvé.");
            e.printStackTrace();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Charge l'argent à partir d'un fichier de sauvegarde.
     *
     * @param fileName Le nom du fichier de sauvegarde.
     * @return La quantité d'argent chargée.
     */
    public static int chargerArgent(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("saves/" + fileName))) {

            // Lit et retourne la quantité de l'argent
            ois.readObject();   // Ignore le plateau
            ois.readObject();   // Ignore la liste des personnages
            ois.readInt();      // Ignore le numéro de niveau
            ois.readObject();   // Ignore la liste des items
            int argent = ois.readInt();
            return argent;
        } catch (FileNotFoundException e) {
            System.out.println("Le fichier " + fileName + " n'a pas été trouvé.");
            e.printStackTrace();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Charge la liste des succès à partir d'un fichier de sauvegarde.
     *
     * @param fileName Le nom du fichier de sauvegarde.
     * @return La liste des succès chargée.
     */
    public static ArrayList<Succes> chargerSucces(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("saves/" + fileName))) {

            // Lit et retourne la liste des succès
            ois.readObject();   // Ignore le plateau
            ois.readObject();   // Ignore la liste des personnages
            ois.readInt();      // Ignore le numéro de niveau
            ois.readObject();   // Ignore la liste des items 
            ois.readInt();      // Ignore la quantité d'argent
            @SuppressWarnings("unchecked")
            ArrayList<Succes> succes = (ArrayList<Succes>) ois.readObject();
            return succes;
        } catch (FileNotFoundException e) {
            System.out.println("Le fichier " + fileName + " n'a pas été trouvé.");
            e.printStackTrace();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}