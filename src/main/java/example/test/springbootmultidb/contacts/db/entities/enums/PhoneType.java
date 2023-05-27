package example.test.springbootmultidb.contacts.db.entities.enums;

public enum PhoneType {

	HOME("Home"), WORK("Work"), CELL("Cell"), OTHER("Other");
	
	private String type;
	
	private PhoneType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return this.type;
	}
}
