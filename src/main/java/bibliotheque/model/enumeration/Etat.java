package bibliotheque.model.enumeration;

public enum Etat {
    NEUF("neuf"),
    USE("usé"),
    TRES_USE("très usé"),
    DETRUIT("detruit");

    private String etat;

    Etat(String etat) {
        this.etat = etat;
    }

    public String getEtat() {
        return this.etat;
    }
}
