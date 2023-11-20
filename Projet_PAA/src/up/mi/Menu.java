package up.mi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Menu {

    public static void main(String[] args) {
        // Vérifier s'il y a un argument passé lors de l'exécution
        if (args.length == 0) {
            System.err.println("\u001B[31mVeuillez spécifier le chemin du fichier en argument.\u001B[0m");
            return;
        }

        // Récupérer le chemin du fichier depuis les arguments
        String filePath = args[0];

        int choix0;
        Scanner sc = new Scanner(System.in);

        do {
            menu0();
            choix0 = sc.nextInt();
            sc.nextLine();

            switch (choix0) {
                case 1: {
                    // Traitement pour l'option 1
                    int nbrVille;
                    do {
                        System.out.println("Entrer le nombre de villes que vous souhaitez ajouter (doit être compris entre 1 et 26): ");
                        nbrVille = sc.nextInt();
                        if (nbrVille < 1 || nbrVille > 26) {
                            System.out.println("Le nombre de ville rentré est incorrecte !\n");
                        }
                    } while (nbrVille < 1 || nbrVille > 26);

                    // Initialisation de l'objet 'Ca' pour gérer les données des villes et des routes
                    Ca ca = new Ca(nbrVille);
                    System.out.print("Villes ajoutées : ");
                    for (int i = 0; i < nbrVille; i++) {
                        Character nomCharVille = (char) ('A' + i);
                        String nomVille = String.valueOf(nomCharVille);
                        ca.ajouterVille(nomVille);
                    }

                    // Initialisation du choix
                    int choix = 0;

                    do {
                        menu1();

                        choix = sc.nextInt();
                        sc.nextLine();

                        switch (choix) {
                            case 1:
                                System.out.println("\nEntrez le nom de la première ville à relier.");
                                String a = sc.nextLine().toUpperCase();
                                System.out.println("Entrez le nom de la seconde ville à relier");
                                String b = sc.nextLine().toUpperCase();
                                ca.ajouterRoute(a, b);
                                break;
                            case 2:
                                break;
                            default:
                                System.out.println("Erreur : l'option " + choix + " n'est pas valable.");
                        }
                    } while (choix != 2);

                    do {
                        menu2();
                        System.out.println("\nListe des villes possédant des zones de recharge :");
                        ca.afficheVillesAvecZone();
                        System.out.println("\nListe des villes ne possédant pas de zone de recharge :");
                        ca.afficheVillesSansZone();

                        choix = sc.nextInt();
                        sc.nextLine();

                        switch (choix) {
                            case 1:
                                System.out.println("\nEntrez le nom de la ville où vous souhaitez ajouter une borne de recharge.");
                                String ajout = sc.nextLine().toUpperCase();
                                ca.ajouterZoneDeRecharge(ajout);
                                break;
                            case 2:
                                System.out.println("Entrez le nom de la ville où vous souhaitez retirer une borne de recharge.");
                                String retirer = sc.nextLine().toUpperCase();
                                ca.retirerZoneDeRecharge(retirer);
                                break;
                            case 3:
                                break;
                            default:
                                System.out.println("Erreur : l'option " + choix + " n'est pas valable.");
                        }
                    } while (choix != 3);

                    // Sortir de la boucle principale après le traitement de l'option 1
                    break;
                }
                case 2: {
                    // Traitement pour l'option 2
                    boolean aDejaRetireZonesDeRecharge = false;

                    try {
                        BufferedReader reader = new BufferedReader(new FileReader(filePath));
                        String line;
                        int nbrVille = 0;

                        while ((line = reader.readLine()).substring(0, 5).equals("ville")) {
                            nbrVille++;
                        }
                        System.out.println("La communauté contient " + nbrVille + " villes :");
                        Ca ca = new Ca(nbrVille);
                        reader.close();

                        BufferedReader reader2 = new BufferedReader(new FileReader(filePath));
                        String line2;
                        while ((line2 = reader2.readLine()) != null) {
                            if (line2.substring(0, 5).equals("ville")) {
                                char character = line2.charAt(6);
                                String ville = Character.toString(character);
                                ca.ajouterVille(ville);
                            } else if (line2.substring(0, 5).equals("route")) {
                                char dep = line2.charAt(6);
                                char arr = line2.charAt(8);
                                String départ = Character.toString(dep);
                                String arrivée = Character.toString(arr);
                                System.out.println("\n" + départ + "---" + arrivée);
                                ca.ajouterRoute(départ, arrivée);
                            } else if (line2.substring(0, 8).equals("recharge")) {
                                if (!aDejaRetireZonesDeRecharge) {
                                    ca.retirerZonesDeRechargeDeToutesLesVilles();
                                    aDejaRetireZonesDeRecharge = true;
                                    System.out.println("les villes avec recharge sont:");
                                }
                                char rec = line2.charAt(9);
                                String recharge = Character.toString(rec);
                                ca.ajouterZoneDeRecharge(recharge);
                                ca.afficheVillesAvecZone();
                            } else {
                                System.err.println("\u001B[31mVous avez une erreur dans votre fichier.\u001B[0m");
                                break;
                            }
                        }
                        reader2.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    // Sortir de la boucle principale après le traitement de l'option 2
                    break;
                }
                case 3: {
                    // Traitement pour l'option de sauvegarde
                    break;
                }
                case 4: {
                    // Sortir du programme pour l'option 4
                    break;
                }
                default: {
                    System.err.println("Erreur : l'option " + choix0 + " n'est pas valable.");
                }
            }
        } while (choix0 != 4);

        // Ferme le scanner
        sc.close();
    }

    private static void menu0() {
        System.out.println("\n1 - résoudre manuellement");
        System.out.println("2 - résoudre automatiquement");
        System.out.println("3 - sauvegarder ");
        System.out.println("4 - Fin");
    }

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
