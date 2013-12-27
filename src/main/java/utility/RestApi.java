package utility;

public enum RestApi {
	QR_GENERATOR("https://chart.googleapis.com/chart?cht=qr&choe=UTF-8&chs=300x300&chl=");
	
	private String data;
	
	private RestApi(String string) {
		data = string;
	}
	
	public String toString(){
		return data;
	}
}
