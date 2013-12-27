package utility;


public class QRCodeGenerator {
	
	public static String createQRCode(String id) {
		return RestApi.QR_GENERATOR.toString() + id;
	}
	
}
