package dominskyi.springboot.rickandmortyapp.model;

public enum Status {
    ALIVE("Alive"),
    DEAD("Dead"),
    UNKNOWN("Unknown");
    private String value;

    Status(String value) {
        this.value = value;
    }
}
