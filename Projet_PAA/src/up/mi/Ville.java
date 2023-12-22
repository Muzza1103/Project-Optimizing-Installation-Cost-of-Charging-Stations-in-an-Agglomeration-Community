package up.mi;

public class Ville {
    private String nom; // Nom de la ville
    private boolean zoneDeRecharge; // Indique si la ville a une zone de recharge ou non

    // Constructeur prenant le nom de la ville en paramètre
    public Ville(String nom) {
        this.nom = nom;
        zoneDeRecharge = true; // Par défaut, chaque ville a une zone de recharge
    }

    // Méthode pour obtenir le nom de la ville
    public String getNom() {
        return nom;
    }

    // Méthode pour obtenir l'état de la zone de recharge pour cette ville
    public boolean getZone() {
        return zoneDeRecharge;
    }

    // Méthode pour ajouter une zone de recharge à la ville
    public void ajouterZone() {
        if (zoneDeRecharge) {
            System.out.println("Il existe déjà une zone de recharge dans la ville " + nom);
        } else {
            zoneDeRecharge = true; // Active la zone de recharge pour cette ville
        }
    }

    // Méthode pour retirer la zone de recharge de la ville
    public void retirerZone() {
        if (!zoneDeRecharge) {
            System.out.println("Il n'existe pas de zone de recharge dans la ville : " + nom);
        } else {
            zoneDeRecharge = false; // Désactive la zone de recharge pour cette ville
        }
    }

    // Méthode pour représenter la ville sous forme de chaîne de caractères
    public String toString() {
        // Utilisation de StringBuffer pour construire la chaîne
        StringBuffer sb = new StringBuffer();
        sb.append("ville(" + nom + ")."); // Ajoute le nom de la ville au format "ville(nom)."
        return sb.toString(); // Retourne la chaîne générée
    }
}
