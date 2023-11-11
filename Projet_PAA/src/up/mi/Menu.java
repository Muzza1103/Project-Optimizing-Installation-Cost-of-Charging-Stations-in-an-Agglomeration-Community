package up.mi;

import java.util.Scanner;

public class Menu {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Initialisation de l'objet 'Ca' pour gérer les données des villes et des routes
        Ca ca = new Ca();
     // Initialisation du choix
        int choix = 0;

        do {
            // Afficher le menu des options disponibles
            menu1();
            
            // Lire le choix de l'utilisateur
            choix = sc.nextInt();
            sc.nextLine(); // Consommer la nouvelle ligne après avoir lu un entier

            switch (choix) {
                case 1:
                    System.out.println("Entrez le nom de la ville :\n");
                    String nomVille = sc.nextLine().toUpperCase();//Affin d'eviter les pb d'oublie de maj ect...
                    
                    // Appel de la méthode pour ajouter une ville
                    ca.ajouterVille(nomVille);
                    break;
                case 2:
                    System.out.println("Entrez le nom de la ville A qui va vers la ville B.\n");
                    String a = sc.nextLine().toUpperCase();//Affin d'eviter les pb d'oublie de maj ect...
                    String b = sc.nextLine().toUpperCase();
  
                    // Appel de la méthode pour ajouter une route entre deux villes
                    ca.ajouterRoute(a, b);
                    break;
                case 3:
                    System.out.println("Entrez le nom de la ville où vous souhaitez ajouter une borne de recharge.\n");
                    String ajout = sc.nextLine().toUpperCase(); //Affin d'eviter les pb d'oublie de maj ect...
                    
                    // Appel de la méthode pour ajouter une zone de recharge dans une ville
                    ca.ajouterZoneDeRecharge(ajout);
                    break;
                case 4:
                    System.out.println("Entrez le nom de la ville où vous souhaitez retirer une borne de recharge.\n");
                    String retirer = sc.nextLine().toUpperCase();//Affin d'eviter les pb d'oublie de maj ect...

                    
                    // Appel de la méthode pour retirer une zone de recharge d'une ville
                    ca.retirerZoneDeRecharge(retirer);
                    break;
                case 0:
                    // Sortir de la boucle et terminer le programme
                    break;
                default:
                    System.out.println("Erreur : l'option " + choix + " n'est pas valable.");
            }
        } while (choix != 4);

        // Ferme le scanner
        sc.close();
    }

    // Méthode pour afficher le menu des options
    private static void menu1() {
        System.out.println("Que souhaitez vous faire ?");
        System.out.println("1 - Ajouter une ville");
        System.out.println("2 - Ajouter une route");
        System.out.println("3 - Ajouter une zone de recharge");
        System.out.println("4 - Retirer une zone de recharge");
        System.out.println("0 - Fin");
    }
}
