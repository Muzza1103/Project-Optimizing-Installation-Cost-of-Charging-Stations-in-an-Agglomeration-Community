package up.mi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.Random;

public class Menu {

    public static void main(String[] args) {
        // Vérifier s'il y a un argument passé lors de l'exécution
        try {
    		if (args.length == 0) {
    			throw new IllegalArgumentException("Veuillez spécifier le chemin du fichier en argument.");
    		}
    		File file = new File(args[0]);
    		if (!file.exists()) {
    			throw new FileNotFoundException("Le fichier dont vous avez rentré le chemin n'existe pas.\nVérifier que le chemin rentré en argument 5est bien correct.");
    		}
    	}catch (Exception e){
    			System.err.println("\u001B[31mErreur : "+e.getMessage()+"\u001B[0m");
    			System.err.println("\u001B[31mFin.\u001B[0m");
    			return;
        }

        // Récupérer le chemin du fichier depuis les arguments
        String filePath = args[0];
        
        int choix0;
        Scanner sc = new Scanner(System.in);
        Ca ca =null;
        File file = new File(filePath);
    	System.out.println("Lecture du fichier "+file.getName());
        do {
            menu0();// menu de base pour l'utilisateur
   		 while (!sc.hasNextInt()) {
             System.out.println("\u001B[31mVeuillez entrer un nombre valide.\u001B[0m");
             sc.next(); // Consommer la saisie invalide
         }// Bloc pour gerer l'erreur si l'utilisateur n'entre pas un int.
            choix0 = sc.nextInt();
            sc.nextLine();
            


            switch (choix0) { //en fonction de son choix sur le menu 0 on entre dans ce switch
                case 1: {
                	
                    // Traitement pour l'option 1
                	ca = traitementFichierTexte(filePath);// appel de la méthode qui lis et extrait les informations du fichier texte
                	if(!ca.verifConditionAcc()) {// si le fichier ne verifie pas la contrainte d'accessibilité on suppose que toutes les villes possédent une zone de recharge
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
			System.out.println("Votre choix :");
                        //on affiche toutes les villes en divisant en fonction de l'existance d'une zone de recharge ou pas
			 while (!sc.hasNextInt()) {
	             System.out.println("\u001B[31mVeuillez entrer un nombre valide.\u001B[0m");
	             sc.next(); // Consommer la saisie invalide
	         }// Bloc pour gerer l'erreur si l'utilisateur n'entre pas un int.
                        choix = sc.nextInt();
                        sc.nextLine();

                        switch (choix) {
                            case 1:
                                System.out.println("\nEntrez le nom de la ville où vous souhaitez ajouter une borne de recharge.");
                                String ajout = sc.nextLine().toUpperCase();//uniformisation du texte pour faciliter l'expérience utilisateur
                                ca.ajouterZoneDeRecharge(ajout);
                                break;
                            case 2:
                                System.out.println("Entrez le nom de la ville où vous souhaitez retirer une borne de recharge.");
                                String retirer = sc.nextLine().toUpperCase();
                                ca.retirerZoneDeRecharge(retirer);// la contrainte est toujours vérifié a travers cette méthode
                                break;
                            case 3:
                                break;//arrêt de la modification
                            default:
                                System.out.println("Erreur : l'option " + choix + " n'est pas valable.");
                        }
                    } while (choix != 3);

                    // Sortir de la boucle principale après le traitement de l'option 1
                    break;
                }
                case 2: {
                    // Traitement pour l'option 2
                	ca = traitementFichierTexte(filePath);//appel de la méthode qui va extraire les informations du fichier donné en argument
                	    if(!ca.verifConditionAcc()) {
                		    System.out.println("On repart de la solution naïve ( toutes les villes ont des zones de recharge )");
                		    ca.ajouterZonesDeRechargeDeToutesLesVilles();
                	    }
                	System.out.println("Cette communauté d'agglomération posséde "+ca.scoreCa()+" zones de recharges\n");
                	int choix;
                	System.out.println("Voulez vous appliquer l'algo2 du sujet (1), un nouvelle algo (2) (fonctionne à partir du nombres de voisins respectifs des villes, cet algorithme peut être vu comme un algorithme de Welsh-Powel modifié), ou revenir au menu principal (3) ?");
                	System.out.println("Votre choix :");
			while (!sc.hasNextInt()) {
                        System.out.println("\u001B[31mVeuillez entrer un nombre valide.\u001B[0m");
                        sc.next(); // Consommer la saisie invalide
                    }
           		 while (!sc.hasNextInt()) {
                     System.out.println("\u001B[31mVeuillez entrer un nombre valide.\u001B[0m");
                     sc.next(); // Consommer la saisie invalide
                 }// Bloc pour gerer l'erreur si l'utilisateur n'entre pas un int.
                	choix = sc.nextInt();//l'utilisateur choisi l'algorithme qu'il veut utiliser pour trouver une solution
                    sc.nextLine();
                    switch(choix) {
                    	case 1://appel et résolution par l'algo 2 du sujet
                    		System.out.println("Début de la résolution automatique\n");
                    		ca=resolutionAlgo2(ca,ca.getNbrVille());
                        	System.out.println("\nFin de la résolution automatique\n");
                        	System.out.println("Cette communauté d'agglomération posséde "+ca.scoreCa()+" zones de recharges");
                        	System.out.println("\nListe des villes possédant des zones de recharge :");
                            ca.afficheVillesAvecZone();
                            System.out.println("\nListe des villes ne possédant pas de zone de recharge :");
                            ca.afficheVillesSansZone();
                            break;

                            
                            //appel et résolution par l'algo de résolution automatique perso
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
			    case 3:
				    break;
                    	default:
                    		System.out.println("Votre choix ne correspond a aucune option, retour au menu");
                    }

                    // Sortir de la boucle principale après le traitement de l'option 2
                    break;
                }
                case 3: {//permet la sauvegarde de la solution
                    	if(ca == null) {
            // Lance une exception si la communauté d'agglomération n'est pas chargée
                    System.err.println("\u001B[31mVous devez d'abord choisir un moyen de solutionner le problème avec l'option 1 ou 2.\u001B[0m");
                    break;
            	}//pour fonctionner, l'utilisateur doit d'abord solutionner le probleme d'une maniere ou d'une autre
                else{
                    String save;
            		System.out.println("Entrez le chemin d'accès où vous voulez sauvegarder votre fichier :");
                	save=sc.next();
                    sauvegarde(save, ca);
                    System.out.println("\u001B[32mLa sauvegarde a réussi !\u001B[0m"); // Affiche en vert le succés et en rouge l'échec
                    break;}
                }
                case 4: {
                    if (ca == null){
                    System.err.println("\u001B[31mVous devez d'abord choisir un moyen de solutionner le problème avec l'option 1 ou 2.\u001B[0m");
                    }
                    else
                    	try {
                    launchJavaFX(ca);
                    }catch (IllegalStateException e) {
                        System.err.println("\u001B[31mLa représentation graphique ne peut être afficheé qu'une seule fois.\u001B[0m");
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
    //Menu pour la resolution manuelle.
    private static void menu1() {
        System.out.println("\n1 - Ajouter une zone de recharge;");
        System.out.println("2 - Retirer une zone de recharge;");
        System.out.println("3 - Fin.");
    }

    //Menu de sauvegarde si fichier existe deja 
    private static void menu2() {
        System.out.println("\n1 - Remplacer le fichier existant");
        System.out.println("2 - Faire une copie");
    }
    
    // Methode afin de traiter le fichier texte entré en argument.
    private static Ca traitementFichierTexte(String filePath) {
    	boolean aDejaRetireZonesDeRecharge = false;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));//crée un buffer 
            String line;
            int nbrVille = 0;
            Set<String> villesUniques = new HashSet<>();//va permettre de vérifier si une ville a deja été créé

            while ((line = reader.readLine()) != null) {//première boucle a travers le fichier pour connaitre le nombre de ville
                // Vérifier si la ligne commence par "ville"
                if (line.startsWith("ville")) {
                    // Ajouter à l'ensemble et vérifier si c'était déjà présent
                    if (!villesUniques.contains(line)) {
                        villesUniques.add(line);
                        nbrVille++;
                    }
                }
            }

            System.out.println("La communauté contient " + nbrVille + " villes :");
            Ca ca = new Ca(nbrVille);
            reader.close();

            BufferedReader reader2 = new BufferedReader(new FileReader(filePath));
            String line2;
	    ArrayList<Integer> ligneErr = new ArrayList<>();
	    int compteur = 0;
            while ((line2 = reader2.readLine()) != null) {
            	compteur++;
            	if(line2.length()<5) {
            		ligneErr.add(compteur);
            		System.err.println("\u001B[31mVous avez une erreur dans votre fichier ligne "+compteur+", néanmoins la Communauté d'agglomération a été chargé avec les lignes correctes (qui respectent le format donné).\u001B[0m");
            	}else
                if (line2.substring(0, 5).equals("ville")) {
                	int indexFin = line2.indexOf(')');
                    String ville = line2.substring(6,indexFin);
                    ca.ajouterVille(ville);
                } else if (line2.substring(0, 5).equals("route")) {
                	int indexSeparateur = line2.indexOf(',');
                    String depart = line2.substring(6,indexSeparateur);
                    int indexFin = line2.indexOf(')');
                    String arrivee = line2.substring(indexSeparateur+1,indexFin);
                    if (depart.equals(arrivee)) //permet d'empecher la création d'un chemin entre une ville et elle meme
                    {
                    ligneErr.add(compteur);
                    System.out.println("\n le départ ne peut pas être la même ville que l'arrivée, vérifier votre fichier."); 
                    }else{
                    System.out.println("\n" + depart + "---" + arrivee);
                    ca.ajouterRoute(depart, arrivee);
                    }
                } else if (line2.substring(0, 8).equals("recharge")) {
                    if (!aDejaRetireZonesDeRecharge) {
                        ca.retirerZonesDeRechargeDeToutesLesVilles();
                        aDejaRetireZonesDeRecharge = true;
                    }
                    int indexFin = line2.indexOf(')');
                    String recharge = line2.substring(9,indexFin);
                    ca.ajouterZoneDeRecharge(recharge);
                } else {
                	ligneErr.add(compteur);
                    System.err.println("\u001B[31mVous avez une erreur dans votre fichier ligne "+compteur+", néanmoins la Communauté d'agglomération a été chargé avec les lignes correctes (qui respectent le format donné).\u001B[0m");
                    break;
                }
            }
            reader2.close();
            if(ligneErr.size()!=0) {
            	if(ligneErr.size()==1) {
            		System.out.println("\nVoici la ligne ou il y a une erreur :");
            	}else {
            		System.out.println("\nVoici les lignes ou il y a une erreur :");
            	}
            	for(int i=0;i<ligneErr.size();i++) {
            		System.out.print(ligneErr.get(i)+" ");
            	}
            }
            System.out.println("\n\nLes villes avec recharge sont:");
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
    private static Ca resolutionAlgo2(Ca ca, int k) {
        int i = 0;
        int scoreCourant = ca.scoreCa();
        List<Ville> villes = ca.getVilles();
        // Boucle jusqu'à atteindre 'k' itérations ou aucune amélioration du score
        while (i < k) {
            Random random = new Random();
            int nombreAleatoire = random.nextInt(ca.getNbrVille());
    
            // Ajoute ou retire une zone de recharge à une ville choisie aléatoirement
            if (villes.get(nombreAleatoire).getZone()) {
                System.out.println("Retire la zone de " + villes.get(nombreAleatoire).getNom());
                ca.retirerZoneDeRecharge(villes.get(nombreAleatoire).getNom());
            } else {
                System.out.println("Ajoute la zone de " + villes.get(nombreAleatoire).getNom());
                ca.ajouterZoneDeRecharge(villes.get(nombreAleatoire).getNom());
            }
            // Réinitialise le compteur si le score s'améliore, sinon incrémente
            if (ca.scoreCa() < scoreCourant) {
                i = 0;
                scoreCourant = ca.scoreCa();
            } else {
                i++;
            }
        }
        return ca;
    }
       
    //Algo perso
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
        VilleRepresentation.setCa(ca); // Ajoutez cette ligne pour initialiser Ca dans VilleRepresentation.
        VilleRepresentation.launch(VilleRepresentation.class);//lance la class qui contient la representation graphique.
    }
    
    //Methode de Sauvegarde 
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
        	System.out.println("\nCe fichier existe déjà.");
        	Scanner sc = new Scanner(System.in);
        	int choix;
        	 
        	//Afichage du menu
        	do {
        		 menu2();
        		 System.out.println("\nVotre choix :");
        		 //Choix Utilisateur
        		 while (!sc.hasNextInt()) {
                     System.out.println("\u001B[31mVeuillez entrer un nombre valide.\u001B[0m");
                     sc.next(); // Consommer la saisie invalide
                 }// Bloc pour gerer l'erreur si l'utilisateur n'entre pas un int.
        		 choix = sc.nextInt();
        		 sc.nextLine();
        		 switch(choix) {
        	
        		 // remplacer le fichier existant.
        		 case 1:{
        			 System.out.println("Remplacement du fichier "+file.getName());
        			 ecrireCaDansFichier(fileName,ca);
        		 }break;
        	
        		 //Faire une copie du fichier existant et l'enregistrer dans ce dernier.
        		 case 2:{
        			 copieFichier(fileName,ca);

        		 }break;
        	
        		 default:
        			 System.out.println("Erreur : l'option " + choix + " n'est pas valable.");
        	
        		 }
        	}while(choix!=1&&choix!=2);
        }else {
        // Créer et enregistre le fichier.
        	System.out.println("Création du fichier "+file.getName());
        	ecrireCaDansFichier(fileName, ca);
        }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    
    //Méthode pour sauvegarder les données dans un fichier
    private static void ecrireCaDansFichier(String fileName, Ca ca) throws IOException {
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

    //Méthode pour créer une copie du fichier existant avec une date ajoutée au nom du fichier
    private static void copieFichier(String fileName, Ca ca) throws IOException {
        // Obtient la date actuelle sous forme de chaîne
        String dateString = new SimpleDateFormat("HH:mm_dd-MM-yyyy").format(new Date());
        // Crée un objet File pour la nouvelle copie du fichier
        String fileCopyName = fileName.replace(".txt", "") + "_Copy_" + dateString.replace(":", "h") + ".txt";
        File fileCopy = new File(fileCopyName);
        System.out.println("Création de la copie "+fileCopy.getName());
        ecrireCaDansFichier(fileCopyName,ca);
    }
}
