package utility;

public enum UrlParameter {
	RESTUARANT_NAME("restName"),
	RESTUARANT_ADDRESS("restAdd"),
	RESTUARANT_CONTACT_INFO("restConatctInfo"),
	RESTUARANT_TABLES("restTables"),
	DEBUG("debug");
	
	private String data;
	
	private UrlParameter(String string) {
		data = string;
	}
	
	public String toString() {
		return data;
	}
}
