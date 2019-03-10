package bibliotheque.model.enumeration;

public enum Etat {
    NEUF("neuf"),
    BON("bon"),
    USE("usé"),
    TRES_USE("très usé"),
    DETRUIT("détruit");

    private String etat;

    Etat(String etat) {
        this.etat = etat;
    }

    public String getEtat() {
        return this.etat;
    }
}
