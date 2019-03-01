package bibliotheque.model.enumeration;

public enum StatutEmprunt {

    EN_COURS("en cours"),
    TERMINE("termmine");

    private String statut;

    StatutEmprunt(String statut) {
        this.statut = statut;
    }

    public String getStatut() {
        return this.statut;
    }

}
