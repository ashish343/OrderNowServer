package com.parse;

public enum ParseApplicationData {
	APLICATION_ID("vMFTELLhOo9RDRql9HpV9lKRot5xQTCCD63wkYdQ"),
	REST_API_KEY("UcvbyxFdRP9aGUHnNJ5TUyO3QPNoL6M1oq9zRlBx");

	private String applicationData;
	
	private ParseApplicationData(String s) {
		applicationData = s;
	}
	
	public String toString(){
		return applicationData;
	}
}
