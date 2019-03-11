package bibliotheque.model.enumeration;

public enum StatutEmprunt {

    EN_COURS("En cours"),
    TERMINE("Termine");

    private String statut;

    StatutEmprunt(String statut) {
        this.statut = statut;
    }

    public String getStatut() {
        return this.statut;
    }

}