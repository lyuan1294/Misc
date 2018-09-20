package enumtest;

public enum TagEnum {
	ET ("ET"),
	ED ("ED"),
	GT ("GT"),
	GC ("GC");
	
	private String tag;
	
	TagEnum(String tag) {
		this.tag = tag;
	}
	
	public String tag() {
		return tag;
	}
}
