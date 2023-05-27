package example.test.springbootmultidb.contacts.model.enums;

public enum PhoneDTOType {

	HOME("Home"), WORK("Work"), CELL("Cell"), OTHER("Other");
	
	private String type;
	
	private PhoneDTOType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return this.type;
	}
}
