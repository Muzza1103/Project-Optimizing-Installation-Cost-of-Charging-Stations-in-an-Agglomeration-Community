package up.mi;

public class Ville {
	private String nom;
	private boolean zoneDeRecharge;
	
	public Ville(String nom) {
		this.nom = nom;
		zoneDeRecharge = true;
	}
	
	public String getNom() {
		return nom;
	}
}
