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
	
	public boolean getZone() {
		return zoneDeRecharge;
	}
	
	public void ajouterZone() {
		if(zoneDeRecharge) {
			System.out.println("Il existe déjà une zone de recharge dans la ville "+nom);
		}else {
			zoneDeRecharge = true;
		}
	}
	
	public void retirerZone() {
		if(!zoneDeRecharge) {
			System.out.println("Il n'existe pas de zone de recharge dans la ville : "+nom);
		}else {
			zoneDeRecharge = false;
		}
	}
}
