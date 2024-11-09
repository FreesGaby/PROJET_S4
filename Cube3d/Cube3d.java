
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.awt.Graphics2D; 
import java.awt.BasicStroke;
import java.awt.Stroke;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Cube3d extends JPanel{
    private double angle = 0.45;
    int fff=30000;
    private Boolean animé=false;
    private String[] imagePaths = {"Cube3d/face1.png", "Cube3d/face2.png", "Cube3d/face3.png", "Cube3d/face4.png", "Cube3d/face5.png", "Cube3d/face6.png"};
    private String randomImagePath = "";
    private Random random = new Random();
    public Cube3d() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                angle = 0.45;
                fff=30000;
                animé=!animé;
                randomImagePath = imagePaths[random.nextInt(imagePaths.length)];
            }
        });
    }
    private double[][] points = {
            {-0.3, -0.3, -0.3},
            {0.3, -0.3, -0.3},
            {0.3, 0.3, -0.3},
            {-0.3, 0.3, -0.3},
            {-0.3, -0.3, 0.3},
            {0.3, -0.3, 0.3},
            {0.3, 0.3, 0.3},
            {-0.3, 0.3, 0.3}
    };

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int longueur = getWidth();
        int hauteur = getHeight();
        g.translate(longueur / 2, hauteur / 2);
        double[][] rotationZ = {
            {Math.cos(angle), -Math.sin(angle), 0},
            {Math.sin(angle), Math.cos(angle), 0},
            {0, 0, 1}
        };
    
        double[][] rotationX = {
            {1, 0, 0},
            {0, Math.cos(angle), -Math.sin(angle)},
            {0, Math.sin(angle), Math.cos(angle)}
        };
    
        double[][] projecte = new double[points.length][2];
        for (int i = 0; i < points.length; i++) {
            double[] rotate = matriceMultiplication(rotationX, points[i]);
            rotate = matriceMultiplication(rotationZ, rotate);
            
            double distance = 2;
            double z = 1 / (distance - rotate[2]);
            double[][] projection = {
                {z, 0, 0},
                {0, z, 0},
            };
            double[] projecte2d = matriceMultiplication(projection, rotate);
    
            projecte2d[0] *= 200;
            projecte2d[1] *= 200;
            projecte[i] = projecte2d;
        }
    
    
        
        g.setColor(Color.CYAN);
        int[][] faces = {
            {0, 1, 2, 3},
            {4, 5, 6, 7},
            {0, 4, 5, 1},
            {3, 7, 6, 2},
            {0, 3, 7, 4},
            {1, 5, 6, 2}
        };
        for (int[] face : faces) {
            drawFace(face, projecte, g);

        }
        g.setColor(Color.BLACK);
        for (int i = 0; i < 4; i++) {
            connect(i, (i + 1) % 4, projecte, g);
            connect(i + 4, ((i + 1) % 4) + 4, projecte, g);
            connect(i, i + 4, projecte, g);
        } 
        
        if (animé) {
            if (fff > 25000 && fff < 30000) {
                angle += 0.01;
            }
            if (fff > 20000 && fff < 25000) {
                angle += 0.005;
            }
            if (fff > 15000 && fff < 20000) {
                angle += 0.003;
            }
            if (fff > 10000 && fff < 15000) {
                angle += 0.002;
            }
            if (fff > 5000 && fff < 10000) {
                angle += 0.001;
            }
            if (fff > 0 && fff < 5000) {
                angle += 0.0003;
            }
            fff = fff - 1;
        }
        if (fff < 0) {
            ImageIcon imageIcon = new ImageIcon(randomImagePath);
            int newImageWidth = 165;
            int newImageHeight = 165;
            Graphics2D g2d = (Graphics2D) g.create();
            double rotationAngle = Math.toRadians(7.5);
            g2d.rotate(rotationAngle, 0, 0);
            g2d.drawImage(imageIcon.getImage(), -85, -85, newImageWidth, newImageHeight, this);
            g2d.dispose();
        }
        repaint();
        
    }


    private void connect(int i, int j, double[][] points, Graphics g) {
        double[] a = points[i];
        double[] b = points[j];
        g.setColor(Color.BLUE);
        Graphics2D g2d = (Graphics2D) g;
        Stroke oldStroke = g2d.getStroke();
        g2d.setStroke(new BasicStroke(3)); // Définit l'épaisseur de la ligne à 3 pixels (modifiable selon vos besoins)
        g2d.drawLine((int) a[0], (int) a[1], (int) b[0], (int) b[1]);
        g2d.setStroke(oldStroke); // Rétablit l'épaisseur de ligne par défaut
    }

    private double[] matriceMultiplication(double[][] a, double[] b) {
        double[] res = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            
            for (int j = 0; j < b.length; j++) {
                
                    res[i] += a[i][j] * b[j];
                
                
            }
        }
        return res;
    }
    private void drawFace(int[] face, double[][] points, Graphics g) {
        int[] xPoints = new int[face.length];
        int[] yPoints = new int[face.length];
        
        double centerX = 0;
    double centerY = 0;
    for (int i = 0; i < face.length; i++) {
        centerX += points[face[i]][0];
        centerY += points[face[i]][1];
    }
    centerX /= face.length;
    centerY /= face.length;

    // Calcule les nouveaux points de la face réduite
    for (int i = 0; i < face.length; i++) {
        xPoints[i] = (int) (points[face[i]][0] * 0.95 + centerX * (1 - 0.95));
        yPoints[i] = (int) (points[face[i]][1] * 0.95 + centerY * (1 - 0.95));
    }

    g.fillPolygon(xPoints, yPoints, face.length);
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Cube 3d");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setResizable(false);
        frame.add(new Cube3d());
        frame.setVisible(true);
    }

} 

