package up.mi;

import java.util.ArrayList;
import java.util.List;

public class Ca {
	
    // Attributs de la classe
	int nbrVille; 
	boolean [][] matriceAdj; //matrice d'adjacence de boolean, avec true si il existe une route entre les deux villes et false sinon
	private List<Ville> villes; //liste contenant les différentes villes de la communauté d'agglomération
	
    // Constructeur
	public Ca(int nbrVille) {
		this.nbrVille = nbrVille;
		villes = new ArrayList<Ville>();
		matriceAdj = new boolean[nbrVille][nbrVille];
	}
	
	// Fonction qui permet d'ajouter une ville si elle n'existe pas déjà
	public void ajouterVille(String nom){
		try {
			for (Ville v : villes) {
				if(v.getNom().equals(nom)) {
					throw new IllegalArgumentException("La ville "+nom+" existe déjà !");
				}
			}
			if (villes.size() < nbrVille) {
				villes.add(new Ville(nom));
				System.out.print("  "+nom);
			} else {
				throw new IndexOutOfBoundsException("Le nombre maximum de villes est atteint (" + nbrVille + "). Impossible d'ajouter : " + nom);
			}
		}catch(IndexOutOfBoundsException e){
			System.out.println("Erreur : "+e.getMessage());
		}catch(IllegalArgumentException f) {
			System.out.println("Erreur : "+f.getMessage());
		}
	}
	
	// Permet d'ajouter une route entre deux villes qui existent
	public void ajouterRoute(String A, String B) {
		int i=-1;
		int j=-1;
		for (int k = 0; k<villes.size();k++) {
			if (villes.get(k).getNom().equals(A)){ //Permet d'avoir la position de A dans la liste villes, cette position correspond a la ligne/colonne correspondante a la ville A dans la matrice d'adjacence
				i = k;
			}
			if (villes.get(k).getNom().equals(B)){ //Permet d'avoir la position de B dans la liste villes, cette position correspond a la ligne/colonne correspondante a la ville B dans la matrice d'adjacence
				j = k;
			}
		}
		try {
			if (i!=-1 && j!=-1){
				if (matriceAdj[i][j]==true) {
					throw new IllegalArgumentException("Ces deux villes ont déjà une route qui les relie !");
				}else {
				matriceAdj[i][j]=matriceAdj[j][i]=true;
				}
			}else if (i==-1) {
				throw new IllegalArgumentException("La ville "+A+" n'existe pas !");
			}else if (j==-1){
				throw new IllegalArgumentException("La ville "+B+" n'existe pas !");
			}else if (i==j) {
				throw new IllegalArgumentException(A+" et "+B+" sont la même ville, vous ne pouvez pas créer de route entre eux !");
			}
		}catch(IllegalArgumentException e ) {
			System.out.println("Erreur : "+e.getMessage());
		}
	}
	// Permet d'ajouter une zone de recharge dans une ville si elle n'existe pas déjà
	public void ajouterZoneDeRecharge(String A) {
		try {
			int i=-1;
			for (int k = 0; k<villes.size();k++) { 
				if (villes.get(k).getNom().equals(A)){ //Permet d'avoir la position de A dans la liste villes, cette position correspond a la ligne/colonne correspondante a la ville A dans la matrice d'adjacence
					i = k;
					villes.get(i).ajouterZone(); 
					return;
				}
			}
			if (i==-1) {
				throw new IllegalArgumentException("La ville "+A+" n'existe pas !");
			}
		}catch(IllegalArgumentException e ) {
			System.out.println("Erreur : "+e.getMessage());
		}
	}
	
	// Suppression de la zone de recharge de la ville A en respectant la contrainte d'accessibilité
	public void retirerZoneDeRecharge(String A) {
		int i=-1;
		try {
			for (int k = 0; k<villes.size();k++) {
				if (villes.get(k).getNom().equals(A)){ //Permet d'avoir la position de A dans la liste villes, cette position correspond a la ligne/colonne correspondant a la ville A dans la matrice d'adjacence
					i = k;
					}
			}
			if (i==-1) {
				throw new IllegalArgumentException("La ville "+A+" n'existe pas !");
			}
			villes.get(i).retirerZone(); //On enléve temporairement la zone de recharge de la ville A pour vérifier que la contrainte d'accessibilté est respecté pour ses villes voisines
			if(verifVoisin(i)) { //Vérifie que A posséde une ville voisine qui posséde une zone de recharge
				List<Integer> villePbm = new ArrayList<>();
				for (int j=0;j<villes.size();j++) {
					if (matriceAdj[i][j]==true) {
						if (!verifVoisin(j)) { //Vérifie que les villes voisines de A, possédent toujours un voisin avec une zone de recharge malgré qu'on ait enlevé celle de A
							villePbm.add(j); //Permet d'accéder au nom de toutes les villes problématiques pour lesquelles la contrainte ne serait pas respecté
						}
					}
				}
				if (!villePbm.isEmpty()) { //Vérifie si il y a des villes problématiques et les renvoie dans l'exception
					StringBuilder sb = new StringBuilder();
					sb.append("Si vous enlever la zone de recharge de "+A+", ");
					for (int m=0;m<villePbm.size();m++) {
						sb.append(villes.get(villePbm.get(m)).getNom()+" ");
					}
					if(villePbm.size()==1) {
						sb.append("ne respectera plus la contrainte d'accessibilité !");
					}else {
						sb.append("ne respecteront plus la constrainte d'accessibilité !");
					}
					villes.get(i).ajouterZone(); //Rajoute la zone de recharge que l'on avait temporairement retiré car il est impossible de retirer la zone de A en respectant la contrainte d'accessibilité 
					throw new IllegalArgumentException(sb.toString());
				}else {
					return; //La contrainte d'accessibilité est respecté pour A et ses voisins, A n'a plus de zone de recharge
				}
			}else {
				villes.get(i).ajouterZone(); //Rajoute la zone de recharge que l'on avait temporairement retiré car il est impossible de retirer la zone de A en respectant la contrainte d'accessibilité 
				throw new IllegalArgumentException("Les villes voisines de "+A+" ne possédent pas de zone de recharge");
			}
		}catch(IllegalArgumentException e ) {
			System.out.println("Erreur : "+e.getMessage());
		}
	}
	
	
	// Permet de vérifier que la ville a une zone de recharge ou qu'au moins une de ses villes voisines en ait une
	private boolean verifVoisin(int i) {
		if(villes.get(i).getZone()==true) {
			return true;
		}else{
		for(int j=0;j<villes.size();j++) {
			if (matriceAdj[i][j]==true) {
				if(villes.get(j).getZone()==true) {
					return true;
				}
			}
		}
		return false;
		}
	}
	
	// Permet d'afficher les villes possédant une zone de recharge
	public void afficheVillesAvecZone() {
		StringBuilder sb = new StringBuilder();
		for (int i=0;i<villes.size();i++) {
			if(villes.get(i).getZone()) {
				sb.append(villes.get(i).getNom()+" ");
			}
		}
		System.out.println(sb.toString());
	}
	
	// Permet d'afficher les villes ne possédant pas de zone de recharge
	public void afficheVillesSansZone() {
		StringBuilder sb = new StringBuilder();
		for (int i=0;i<villes.size();i++) {
			if(!villes.get(i).getZone()) {
				sb.append(villes.get(i).getNom()+" ");
			}
		}
		System.out.println(sb.toString());
	}
	
}
