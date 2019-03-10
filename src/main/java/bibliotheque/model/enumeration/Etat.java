package bibliotheque.model.enumeration;

public enum Etat {
    NEUF("Neuf"),
    BON("Bon"),
    USE("Usé"),
    TRES_USE("Très usé"),
    DETRUIT("Détruit");

    private String etat;

    Etat(String etat) {
        this.etat = etat;
    }

    public String getEtat() {
        return this.etat;
    }
}
