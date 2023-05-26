package dominskyi.springboot.rickandmortyapp.model;

public enum Gender {
    FEMALE("Female"),
    MALE("Male"),
    GENDERLESS("Genderless"),
    UNKNOWN("Unknown");
    private String value;

    Gender(String value) {
        this.value = value;
    }
}
