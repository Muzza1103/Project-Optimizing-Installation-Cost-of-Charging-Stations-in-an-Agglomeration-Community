package up.mi;

import java.util.Scanner;

public class Menu {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nbrVille;
        do {
        	System.out.println("Entrer le nombre de villes que vous souhaitez ajouté ( doit être compris entre 1 et 26 ) : ");
        	nbrVille = sc.nextInt();
        	//Verifie que le nombre de ville crée est compris entre 1 et 26.
        	if(nbrVille<1||nbrVille>26) {
        		System.out.println("Le nombre de ville rentré est incorrecte !\n");
        	}
        }while(nbrVille<1||nbrVille>26);
        
        // Initialisation de l'objet 'Ca' pour gérer les données des villes et des routes
        Ca ca = new Ca(nbrVille);
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
                    System.out.println("\nEntrez le nom de la ville A qui relie la ville B.");
                    String a = sc.nextLine().toUpperCase();//Affin d'eviter les pb d'oublie de maj ect...
                    System.out.println("Entrer le nom de la ville B");
                    String b = sc.nextLine().toUpperCase();
  
                    // Appel de la méthode pour ajouter une route entre deux villes
                    ca.ajouterRoute(a, b);
                    break;
                case 2:
                    // Sortir de la boucle et terminer le programme
                    break;
                default:
                    System.out.println("Erreur : l'option " + choix + " n'est pas valable.");
            }
        } while (choix != 2);
        	
        do {
        	// Afficher le menu des options disponibles
            menu2();
            System.out.println("\nListe des villes possédant des zones de recharge :");
            ca.afficheVillesAvecZone();
            System.out.println("\nListe des villes ne possédant pas de zone de recharge :");
            ca.afficheVillesSansZone();
            
            // Lire le choix de l'utilisateur
            choix = sc.nextInt();
            sc.nextLine(); // Consommer la nouvelle ligne après avoir lu un entier

            switch (choix) {
            	case 1:
            		System.out.println("\nEntrez le nom de la ville où vous souhaitez ajouter une borne de recharge.");
            		String ajout = sc.nextLine().toUpperCase(); //Affin d'eviter les pb d'oublie de maj ect...
                    
                
            		// Appel de la méthode pour ajouter une zone de recharge dans une ville
            		ca.ajouterZoneDeRecharge(ajout);
            		break;
            	case 2:
            		System.out.println("Entrez le nom de la ville où vous souhaitez retirer une borne de recharge.");
            		String retirer = sc.nextLine().toUpperCase();//Affin d'eviter les pb d'oublie de maj ect...

                
            		// Appel de la méthode pour retirer une zone de recharge d'une ville
            		ca.retirerZoneDeRecharge(retirer);
            		break;
            	case 3:
            		// Sortir de la boucle et terminer le programme
            		break;
            	default:
            		System.out.println("Erreur : l'option " + choix + " n'est pas valable.");
            }
        } while (choix != 3);

        // Ferme le scanner
        sc.close();
    }

    // Méthode pour afficher le menu des options
    private static void menu1() {
        System.out.println("\n1 - Ajouter une route;");
        System.out.println("2 - Fin.");
    }
    
    private static void menu2() {
    	System.out.println("\n1 - Ajouter une zone de recharge;");
        System.out.println("2 - Retirer une zone de recharge;");
        System.out.println("3 - Fin.");
    }
    
}
