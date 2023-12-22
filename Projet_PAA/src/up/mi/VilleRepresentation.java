package up.mi;

import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class VilleRepresentation extends Application {
    private static Ca ca; // Communauté d'agglomération
    private boolean[][] matrice; // Matrice représentant les connexions entre les villes

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, 800, 600);
        scene.setFill(Color.GREY);

        try {
            List<Ville> villes = ca.getVilles();
            matrice = ca.getMatrice();

            if (villes == null || villes.isEmpty() || matrice == null || matrice.length != villes.size() || matrice[0].length != villes.size()) {
                throw new NullPointerException("La liste de villes est vide, nulle, ou la matrice est incorrecte");
            }

            int gridSize = 3; // Modifier la densité de la grille

            // Boucle pour créer les cercles représentant les villes
            for (int i = 0; i < villes.size(); i++) {
                Circle villeACircle = new Circle();
                int row = i / gridSize;
                int col = i % gridSize;
                double x = 100 + col * 150; // Ajustez ces valeurs pour rapprocher les villes horizontalement
                double y = 150 + row * 150; // Ajustez ces valeurs pour rapprocher les villes verticalement

                villeACircle.setCenterX(x);
                villeACircle.setCenterY(y);
                villeACircle.setRadius(20);

                // Remplissage du cercle en vert si la ville a une zone de recharge, sinon en rouge
                if (!villes.get(i).getZone()) {
                    villeACircle.setFill(Color.RED);
                } else {
                    villeACircle.setFill(Color.GREEN);
                }

                // Affichage du nom de la ville
                Text cityName = new Text(villes.get(i).getNom());
                cityName.setX(x - cityName.getBoundsInLocal().getWidth() / 2);
                cityName.setY(y + cityName.getBoundsInLocal().getHeight() / 4);
                cityName.setFill(Color.WHITE);
                root.getChildren().addAll(villeACircle, cityName);
            }

            // Boucle pour créer les lignes représentant les routes entre les villes
            for (int i = 0; i < villes.size(); i++) {
                for (int j = 0; j < villes.size(); j++) {
                    if (matrice[i][j]) {
                        int row = i / gridSize;
                        int col = i % gridSize;
                        int rowJ = j / gridSize;
                        int colJ = j % gridSize;
                        if (row != rowJ || col != colJ) {
                            createCurvedLine(root, 100 + col * 150, 150 + row * 150, 100 + colJ * 150, 150 + rowJ * 150);
                        }
                    }
                }
            }

            primaryStage.setTitle("Représentation des villes");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (NullPointerException e) {
            System.out.println("\u001B[31mErreur : " + e.getMessage() + "\u001B[0m");
        }
    }

    // Méthode pour creer des lignes en relief afin de ne pas confondre les routes qui se superposent 
    private void createCurvedLine(Pane root, double startX, double startY, double endX, double endY) {
        double radius = 20;

        double deltaX = endX - startX;
        double deltaY = endY - startY;
        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

        // Normalisation du vecteur de direction
        double normalizedDeltaX = deltaX / distance;
        double normalizedDeltaY = deltaY / distance;

        // Points de départ et d'arrivée sur le cercle
        double startXOnCircle = startX + normalizedDeltaX * radius;
        double startYOnCircle = startY + normalizedDeltaY * radius;
        double endXOnCircle = endX - normalizedDeltaX * radius;
        double endYOnCircle = endY - normalizedDeltaY * radius;

        // Dessin de la ligne
        Line routeLine = new Line(startXOnCircle, startYOnCircle, endXOnCircle, endYOnCircle);
        root.getChildren().add(routeLine);
    }

    // Méthode pour définir la communauté d'agglomération
    public static void setCa(Ca ca) {
        VilleRepresentation.ca = ca;
    }

    // Méthode principale pour lancer l'application JavaFX
    public static void main(String[] args) {
        launch(args);
    }
}
