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
		//nombre de ville
		this.nbrVille = nbrVille;
		villes = new ArrayList<>();
		matriceAdj = new boolean[nbrVille][nbrVille];
	}
	//Ajout d'une ville
	public void ajouterVille(String nom){
		if (villes.size() < nbrVille) {
            villes.add(new Ville(nom));
            System.out.println("Ville ajoutée : " + nom);
		} else {
        System.out.println("Le nombre maximum de villes est atteint (" + nbrVille + "). Impossible d'ajouter : " + nom);
    	}
	}
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
			}else {
				throw new IllegalArgumentException("La ville "+B+" n'existe pas !");
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
	
	// Suppression d'une zone de recharge
	public void retirerZoneDeRecharge(String A) {
		// Tente de retirer une zone de recharge de la ville A
		try {
			int i=-1;
			for (int k = 0; k<villes.size();k++) {
				if (villes.get(k).getNom().equals(A)){
					i = k;
					villes.get(k).retirerZone();
				}
			}
			//affiche un message d'erreur si la ville n'exsite pas 
			if (i==-1) {
				throw new IllegalArgumentException("La ville "+A+" n'existe pas !");
			}
		}catch(IllegalArgumentException e ) {
			System.out.println("Erreur : "+e.getMessage());
		}
	}
}
