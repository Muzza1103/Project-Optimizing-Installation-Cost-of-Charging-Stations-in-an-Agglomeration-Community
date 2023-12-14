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
    private static Ca ca;
    private boolean[][] matrice;


    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, 800, 600);
        scene.setFill(Color.GREY);

        try {
            List<Ville> villes = ca.getVilles();
            matrice = ca.getMatrice();

            if (villes == null || villes.isEmpty() || matrice == null || matrice.length != villes.size() || matrice[0].length != villes.size()) {
                throw new NullPointerException("La liste de villes est vide ou nulle, ou la matrice est incorrecte");
            }

            int gridSize = 3; // Modifier la densité de la grille

            for (int i = 0; i < villes.size(); i++) {
                Circle villeACircle = new Circle();
                int row = i / gridSize;
                int col = i % gridSize;
                double x = 100 + col * 150; // Ajustez ces valeurs pour rapprocher les villes horizontalement
                double y = 150 + row * 150; // Ajustez ces valeurs pour rapprocher les villes verticalement

                villeACircle.setCenterX(x);
                villeACircle.setCenterY(y);
                villeACircle.setRadius(20);

                if (!villes.get(i).getZone()) {
                    villeACircle.setFill(Color.RED);
                } else {
                    villeACircle.setFill(Color.GREEN);
                }

                Text cityName = new Text(villes.get(i).getNom());
                cityName.setX(x - cityName.getBoundsInLocal().getWidth() / 2);
                cityName.setY(y + cityName.getBoundsInLocal().getHeight() / 4);
                cityName.setFill(Color.WHITE);
                root.getChildren().addAll(villeACircle, cityName);
            }

            for (int i = 0; i < villes.size(); i++) {
                for (int j = 0; j < villes.size(); j++) {
                    if (matrice[i][j]) {
                        int row = i / gridSize;
                        int col = i % gridSize;
                        int rowJ = j / gridSize;
                        int colJ = j % gridSize;
                        if (row != rowJ || col != colJ) {
                            // Modifier les calculs pour ajuster la position des routes
                            Line routeLine = new Line(100 + col * 150, 150 + row * 150, 100 + colJ * 150, 150 + rowJ * 150);
                            root.getChildren().add(0,routeLine);
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


    public static void setCa(Ca ca) {
        VilleRepresentation.ca = ca;
    }

    public static void main(String[] args) {
        launch(args);
    }
}