package example.test.springbootmultidb.contacts.db.entities.enums;

public enum AddressType {

	HOME("Home"),
	MAILING("Mailing"),
	WORK("Work"),
	OTHER("Other");
	
	private String value;
	
	private AddressType(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
