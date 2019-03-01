package bibliotheque.model.enumeration;

public enum Type {
    SPORT("sport"),
    AUTOMOBILE("automobile"),
    PEOPLE("people"),
    ASTRONOMIE("astronomie"),
    CUISINE("cuisine"),
    ECONOMIE("économie"),
    POLITIQUE("politique");

    private String type;

    Type(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}