package example.test.springbootmultidb.contacts.model.enums;

public enum AddressDTOType {

    HOME("Home"),
    MAILING("Mailing"),
    WORK("Work"),
    OTHER("Other");

    private String value;

    private AddressDTOType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
