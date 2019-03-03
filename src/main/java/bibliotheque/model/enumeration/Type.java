package bibliotheque.model.enumeration;

public enum Type {
    SPORT("sport"),
    AUTOMOBILE("automobile"),
    PEOPLE("people"),
    CULTURE("culture"),
    CUISINE("cuisine"),
    ACTUALITE("actualite");

    private String type;

    Type(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}