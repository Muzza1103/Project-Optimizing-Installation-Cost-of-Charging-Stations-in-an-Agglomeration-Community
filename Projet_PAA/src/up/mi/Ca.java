package up.mi;

import java.util.ArrayList;
import java.util.List;

public class Ca {
	
    // Attributs de la classe
	int nbrVille;
	boolean [][] matriceAdj;
	private List<Ville> villes;
	
    // Constructeur
	public Ca(int nbrVille) {
		try {
			if(nbrVille<=26) {
				this.nbrVille = nbrVille;
				villes = new ArrayList<Ville>();
				matriceAdj = new boolean[nbrVille][nbrVille];
				
				for (int i = 0; i < nbrVille; i++) { //Permet de créer 
		            Character nomCharVille = (char) ('A' + i);
		            String nomVille = String.valueOf(nomCharVille);
		            villes.add(new Ville(nomVille));
		        }
			}else {
				throw new IllegalArgumentException("Vous ne pouvez pas avoir plus de 26 villes !");
			}
		}catch(IllegalArgumentException f) {
			System.out.println("Erreur : "+f.getMessage());
		}
	}
	
	/* Pour la partie 2
	//Ajout d'une ville
	public void ajouterVille(String nom){
		try {
			for (Ville v : villes) {
				if(v.getNom().equals(nom)) {
					throw new IllegalArgumentException("La ville "+nom+" existe déjà !");
				}
			}
			if (villes.size() < nbrVille) {
				villes.add(new Ville(nom));
				System.out.println("Ville ajoutée : " + nom);
			} else {
				throw new IndexOutOfBoundsException("Le nombre maximum de villes est atteint (" + nbrVille + "). Impossible d'ajouter : " + nom);
			}
		}catch(IndexOutOfBoundsException e){
			System.out.println("Erreur : "+e.getMessage());
		}catch(IllegalArgumentException f) {
			System.out.println("Erreur : "+f.getMessage());
		}
	}
	*/
	
	//ajout d'une route
	public void ajouterRoute(String A, String B) {
		int i=-1;
		int j=-1;
		for (int k = 0; k<villes.size();k++) {
			if (villes.get(k).getNom().equals(A)){
				i = k;
			}
			if (villes.get(k).getNom().equals(B)){
				j = k;
			}
		}
		try {
			if (i!=-1 && j!=-1){
				matriceAdj[i][j]=matriceAdj[j][i]=true;
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
	// Ajout d'un zone de recharge 
	public void ajouterZoneDeRecharge(String A) {
		try {
			int i=-1;
			for (int k = 0; k<villes.size();k++) {
				if (villes.get(k).getNom().equals(A)){
					i = k;
					villes.get(k).ajouterZone();
				}
			}
			if (i==-1) {
				throw new IllegalArgumentException("La ville "+A+" n'existe pas !");
			}
		}catch(IllegalArgumentException e ) {
			System.out.println("Erreur : "+e.getMessage());
		}
	}
	
	// Suppression de la zone de recharge de la ville A
	public void retirerZoneDeRecharge(String A) {
		int i=-1;
		try {
			for (int k = 0; k<villes.size();k++) {
				if (villes.get(k).getNom().equals(A)){
					i = k;
					}
			}
			if (i==-1) {
				throw new IllegalArgumentException("La ville "+A+" n'existe pas !");
			}
			villes.get(i).retirerZone(); //On enléve temporairement la zone de recharge de la ville A pour vérifier que la contrainte d'accessibilté est respecté pour ses villes voisines
			if(verifVoisin(i)) { // Vérifie que A posséde une ville voisine qui posséde une zone de recharge
				List<Integer> villePbm = new ArrayList<>();
				for (int j=0;j<villes.size();j++) {
					if (matriceAdj[i][j]==true) {
						if (!verifVoisin(j)) {
							villePbm.add(j); //Permet d'accéder au nom de toutes les villes problématiques pour lesquelles la contrainte ne serait pas respecté
						}
					}
				}
				if (!villePbm.isEmpty()) {
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
					throw new IllegalArgumentException(sb.toString());
				}
			}else {
				villes.get(i).ajouterZone();//Rajoute la zone de recharge que l'on avait temporairement retiré car il est impossible de retirer la zone de A en respectant la contrainte d'accessibilité 
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
	
	public void afficheVillesAvecZone() {
		StringBuilder sb = new StringBuilder();
		for (int i=0;i<villes.size();i++) {
			if(villes.get(i).getZone()) {
				sb.append(villes.get(i).getNom()+" ");
			}
		}
		System.out.println(sb.toString());
	}
	
	public void afficheVillesSansZone() {
		StringBuilder sb = new StringBuilder();
		for (int i=0;i<villes.size();i++) {
			if(villes.get(i).getZoneSans()) {
				sb.append(villes.get(i).getNom()+" ");
			}
		}
		System.out.println(sb.toString());
	}
	
}
