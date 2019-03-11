package bibliotheque.model.enumeration;

public enum StatutReservation {

    EN_COURS("En cours"),
    TERMINEE("Terminée"),
    ANNULEE("Annulée");

    private String statut;

    StatutReservation(String statut) {
        this.statut = statut;
    }

    public String getStatut() {
        return this.statut;
    }

}