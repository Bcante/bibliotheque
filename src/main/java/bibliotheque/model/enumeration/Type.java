package bibliotheque.model.enumeration;

public enum Type {
    SPORT("Sport"),
    AUTOMOBILE("Automobile"),
    PEOPLE("People"),
    CULTURE("Culture"),
    CUISINE("Cuisine"),
    ACTUALITE("Actualite");

    private String type;

    Type(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}