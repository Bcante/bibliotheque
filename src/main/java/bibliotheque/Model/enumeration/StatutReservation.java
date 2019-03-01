package bibliotheque.model.enumeration;

public enum StatutReservation {

    EN_COURS("en cours"),
    TERMINEE("terminée"),
    ANNULEE("annulée");

    private String statut;

    StatutReservation(String statut) {
        this.statut = statut;
    }

    public String getStatut() {
        return this.statut;
    }

}
