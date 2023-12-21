package up.mi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

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
        Ca ca =null;
        do {
            menu0();
            choix0 = sc.nextInt();
            sc.nextLine();

            switch (choix0) {
                case 1: {
                	
                    // Traitement pour l'option 1
                	ca = traitementFichierTexte(filePath);
                	if(!ca.verifConditionAcc()) {
                		System.out.println("On repart de la solution naïve ( toutes les villes ont des zones de recharge )");
                		ca.ajouterZonesDeRechargeDeToutesLesVilles();
                	}
                	
                	int choix = 0;
                	
                    do { 
                        menu1();
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
                	ca = traitementFichierTexte(filePath);
                	if(!ca.verifConditionAcc()) {
                		System.out.println("On repart de la solution naïve ( toutes les villes ont des zones de recharge )");
                		ca.ajouterZonesDeRechargeDeToutesLesVilles();
                	}
                	System.out.println("Cette communauté d'agglomération posséde "+ca.scoreCa()+" zones de recharges\n");
                	int choix;
                	System.out.println("Voulez vous appliquer l'algo n°2 du sujet (1) ou appliquer notre nouvel algo ? (2) (fonctionne à partir du nombres de voisins respectifs des villes)");
                	choix = sc.nextInt();
                    sc.nextLine();
                    switch(choix) {
                    	//Algo sujet 
                    case 1:
                    		System.out.println("Début de la résolution automatique\n");
                    		ca=resolutionAlgo2(ca,ca.getNbrVille());
                        	System.out.println("\nFin de la résolution automatique\n");
                        	System.out.println("Cette communauté d'agglomération posséde "+ca.scoreCa()+" zones de recharges");
                        	System.out.println("\nListe des villes possédant des zones de recharge :");
                            ca.afficheVillesAvecZone();
                            System.out.println("\nListe des villes ne possédant pas de zone de recharge :");
                            ca.afficheVillesSansZone();
                            break;
                            
                            //Algo perso
                    	case 2:
                    		System.out.println("Début de la résolution automatique\n");
                    		resolutionAutomatique(ca);
                        	System.out.println("\nFin de la résolution automatique\n");
                        	System.out.println("Cette communauté d'agglomération posséde "+ca.scoreCa()+" zones de recharges");
                        	System.out.println("\nListe des villes possédant des zones de recharge :");
                            ca.afficheVillesAvecZone();
                            System.out.println("\nListe des villes ne possédant pas de zone de recharge :");
                            ca.afficheVillesSansZone();
                            break;
                    	default:
                    		System.out.println("Votre choix ne correspond a aucune option, retour au menu");
                    }

                    // Sortir de la boucle principale après le traitement de l'option 2
                    break;
                }
                case 3: {
                    	if(ca == null) {
            // Lance une exception si la communauté d'agglomération n'est pas chargée
                    System.err.println("\u001B[31mSauvegarde Impossible car la CA est vide.\u001B[0m");
                    break;
            	}
                else{
                    String save;
            		System.out.println("Entrez le chemin d'acces du fichier de sauvegarde");
                	save=sc.next();
                    sauvegarde(save, ca);
                    System.out.println("\u001B[32mLa sauvegarde a réussi !\u001B[0m"); // Affiche en vert 
                    break;}
                }
                case 4: {
                    if (ca == null){
                    System.err.println("\u001B[31mLa communauté d'agglomération est vide. Vous devez d'abord choisir un moyen de solutionner le problème avec l'option 1 ou 2.\u001B[0m");

                    
                    }
                    else{
                    launchJavaFX(ca);
                    // Afichage javaFX.
                    }
                        break;
                }
                case 5: {
                    // Sortir du programme pour l'option 5
                    break;
                }
                default: {
                    System.err.println("Erreur : l'option " + choix0 + " n'est pas valable.");
                }
            }
        } while (choix0 != 5);

        // Ferme le scanner
        sc.close();
    }
//Menu principal.
    private static void menu0() {
        System.out.println("\n1 - résoudre manuellement");
        System.out.println("2 - résoudre automatiquement");
        System.out.println("3 - sauvegarder ");
        System.out.println("4 - Affichage graphique");
        System.out.println("5 - Fin");
    }
//menu pour la resolution manuelle.
    private static void menu1() {
        System.out.println("\n1 - Ajouter une zone de recharge;");
        System.out.println("2 - Retirer une zone de recharge;");
        System.out.println("3 - Fin.");
    }
 // menu de sauvegarde si fichier existe deja 
    private static void menu2() {
        System.out.println("\n1 - Remplacer le fichier existant");
        System.out.println("2 - Faire une copie");
    }
    
    // Methode afin de traiter le fichier texte entré en argument.
    private static Ca traitementFichierTexte(String filePath) {
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
                	int indexFin = line2.indexOf(')');
                    String ville = line2.substring(6,indexFin);
                    ca.ajouterVille(ville);
                } else if (line2.substring(0, 5).equals("route")) {
                	int indexSeparateur = line2.indexOf(',');
                    String depart = line2.substring(6,indexSeparateur);
                    int indexFin = line2.indexOf(')');
                    String arrivee = line2.substring(indexSeparateur+1,indexFin);
                    System.out.println("\n" + depart + "---" + arrivee);
                    ca.ajouterRoute(depart, arrivee);
                } else if (line2.substring(0, 8).equals("recharge")) {
                    if (!aDejaRetireZonesDeRecharge) {
                        ca.retirerZonesDeRechargeDeToutesLesVilles();
                        aDejaRetireZonesDeRecharge = true;
                        System.out.println("les villes avec recharge sont:");
                    }
                    int indexFin = line2.indexOf(')');
                    String recharge = line2.substring(9,indexFin);
                    ca.ajouterZoneDeRecharge(recharge);
                } else {
                    System.err.println("\u001B[31mVous avez une erreur dans votre fichier.\u001B[0m");
                    break;
                }
            }
            reader2.close();
            ca.afficheVillesAvecZone();
            return ca;
        } catch (IOException e) {
        	System.err.println("\u001B[31mVous avez une erreur dans votre fichier.\u001B[0m");
            e.printStackTrace();
            System.exit(1); // A changer potentiellement, quitte le programme de force
            return null;
        }
    }
    
    
    //Algo du sujet 
    private static Ca resolutionAlgo2(Ca ca,int k) {
    	int i=0;
    	int scoreCourant = ca.scoreCa();
    	List<Ville> villes = ca.getVilles();
    	while(i<k) {
    		Random random = new Random();
    		int nombreAleatoire = random.nextInt(ca.getNbrVille());
    		if(villes.get(nombreAleatoire).getZone()==true) {
    			System.out.println("Retire la zone de "+villes.get(nombreAleatoire).getNom());
    			ca.retirerZoneDeRecharge(villes.get(nombreAleatoire).getNom());
    		}else {
    			System.out.println("Ajoute la zone de "+villes.get(nombreAleatoire).getNom());
    			ca.ajouterZoneDeRecharge(villes.get(nombreAleatoire).getNom());
    		}
    		if(ca.scoreCa()<scoreCourant) {
    			i=0;
    			scoreCourant = ca.scoreCa();
    		}else {
    			i++;
    		}
    	}
    	return ca;
    }
    
    
    // Algo perso
    private static void resolutionAutomatique(Ca ca) {
    	List<Ville> villes = ca.getVilles(); // Creer une liste de ville 
    	boolean [][] matriceAdj = ca.getMatrice();// Cree une matrice qui represente les route 
		int [] listeVoisin = new int[villes.size()]; // Cree un tableau avec la taille des villes
		for (int i=0;i<villes.size();i++) {
			for (int j=0;j<villes.size();j++) {
				if (matriceAdj[i][j]==true) { // si une route existe alors il est un voison, donc il l'ajoute dans le tableau
					listeVoisin[i]++; // ajoute dans le tab
				}
			}
		}
		for(int i=1;i<villes.size();i++) {
			for (int j=0;j<villes.size();j++) {
				if (listeVoisin[j]==i && villes.get(j).getZone()== true) { // on prend les villes qui possedent le moins de voisin jusqu'au ville qui possedent le plus de voisin (odre croissant), et une ville qui posssede une zone de recharge
					System.out.println("Retire la zone de "+villes.get(j).getNom());
					ca.retirerZoneDeRecharge(villes.get(j).getNom());// on retire la zone de recharge sachant que la methode doit respecter la contrainte d'accessibilité definit auparavant
				}
			}
		}
	}
    
    // Methode pour l'affichage graphique
    private static void launchJavaFX(Ca ca) {
        VilleRepresentation.setCa(ca); // Ajoutez cette ligne pour initialiser Ca dans VilleRepresentation
        VilleRepresentation.launch(VilleRepresentation.class);
    }
    

 // methode de Sauvegarde 
    private static void sauvegarde(String fileName, Ca ca) {
            try {
        // Vérifie si l'instance de la communauté d'agglomération est nulle
		if(ca == null) {
            // Lance une exception si la communauté d'agglomération n'est pas chargée
            		throw new IOException("La communauté d'agglomération n'a pas été chargé !");
            	}
		// Crée un objet File en utilisant le nom de fichier spécifié
        File file = new File(fileName);
		// Vérifier si le fichier existe déja
        if (file.exists()) {
        	Scanner sc = new Scanner(System.in);
        	int choix;
        	 
        	//Afichage du menu
        	do {
        		 menu2();
        		 //Choix Utilisateur
        		 choix = sc.nextInt();
        		 sc.nextLine();
        	switch(choix) {
        	
        	// remplacer le fichier existant.
        	case 1:{
        		ecraserFichier(fileName,ca);
        	}break;
        	
        	//Faire une copie du fichier existant et l'enregistrer.
        	case 2:{
        		copieFichier(fileName);

        	}break;
        	
        	default:
                System.out.println("Erreur : l'option " + choix + " n'est pas valable.");
        	
        	}
        	}while(choix!=1&&choix!=2);
        	 
        }
        // Enregistre un fichier.
        FileWriter fileWriter = new FileWriter(fileName);
                List<Ville> villes = ca.getVilles();
                boolean [][] route = ca.getMatrice();
                for (Ville ville : villes) {
                    fileWriter.write(ville.toString() + "\n");
                }
                for (int i=0;i<villes.size();i++) {
                	for (int j=0;j<=i;j++) {
                		if (route[i][j]==true) {
                            fileWriter.write("route("+villes.get(i).getNom()+","+villes.get(j).getNom()+")."+ "\n");

                		}
                	}
                }
                for (int i=0;i<villes.size();i++) {
                	if (villes.get(i).getZone()==true) {
                		fileWriter.write("recharge("+villes.get(i).getNom()+")."+"\n");
                	}
                }
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    
 // Méthode pour sauvegarder les données dans un fichier
    private static void ecraserFichier(String fileName, Ca ca) throws IOException {
        // Initialise un FileWriter pour écrire dans le fichier spécifié
        FileWriter fileWriter = new FileWriter(fileName);
        // Récupère les informations sur les villes et les routes de la communauté d'agglomération
        List<Ville> villes = ca.getVilles();
        boolean[][] route = ca.getMatrice();

        // Écrit les informations sur les villes dans le fichier
        for (Ville ville : villes) {
            fileWriter.write(ville.toString() + "\n");
        }

        // Écrit les informations sur les routes dans le fichier
        for (int i = 0; i < villes.size(); i++) {
            for (int j = 0; j <= i; j++) {
                if (route[i][j]) {
                    fileWriter.write("route(" + villes.get(i).getNom() + "," + villes.get(j).getNom() + ")." + "\n");
                }
            }
        }

        // Écrit les informations sur les points de recharge dans le fichier
        for (Ville ville : villes) {
            if (ville.getZone()) {
                fileWriter.write("recharge(" + ville.getNom() + ")." + "\n");
            }
        }

        // Ferme le FileWriter pour libérer les ressources
        fileWriter.close();
    }

    // Méthode pour créer une copie du fichier existant avec une date ajoutée au nom du fichier
    private static void copieFichier(String fileName) throws IOException {
        // Obtient la date actuelle sous forme de chaîne
        String dateString = new SimpleDateFormat("HH:mm_dd-MM-yyyy").format(new Date());
        // Crée un objet File pour le fichier d'origine
        File file = new File(fileName);
        // Crée un objet File pour la nouvelle copie du fichier
        File tempFile = new File(fileName + "_Copy_" + dateString);
        // Effectue la copie du fichier d'origine vers la nouvelle copie avec la date ajoutée au nom
        Files.copy(file.toPath(), tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }
}
