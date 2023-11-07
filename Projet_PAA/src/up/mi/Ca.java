package up.mi;

import java.util.ArrayList;
import java.util.List;

public class Ca {
	int nbrVille;
	boolean [][] matriceAdj;
	private List<Ville> villes;
	
	public Ca(int nbrVille) {
		this.nbrVille = nbrVille;
		villes = new ArrayList<>();
		matriceAdj = new boolean[nbrVille][nbrVille];
	}
	
	public void ajouterVille(String nom){
		if (villes.size() < nbrVille) {
            villes.add(new Ville(nom));
            System.out.println("Ville ajoutée : " + nom);
		} else {
        System.out.println("Le nombre maximum de villes est atteint (" + nbrVille + "). Impossible d'ajouter : " + nom);
    	}
	}
	
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
	
	public void ajouterZoneDeRecharge(String A) {
		try {
			int i=-1;
			for (int k = 0; k<villes.size();k++) {
				if (villes.get(k).getNom().equals(A)){
					i = k;
					villes.get(k).ajouterZone();
				}
			}
			if (i==1) {
				throw new IllegalArgumentException("La ville "+A+" n'existe pas !");
			}
		}catch(IllegalArgumentException e ) {
			System.out.println("Erreur : "+e.getMessage());
		}
	}
	
	
	public void retirerZoneDeRecharge(String A) {
		
	}
}
