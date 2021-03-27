package change.domai.common.converter;
import java.security.MessageDigest;


public class Encrypt {
	
	public static String generateHash(String val) {
		String text = "";
        byte[] cipher_byte;
        try{
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                md.update(val.getBytes());
                cipher_byte = md.digest();
                StringBuilder sb = new StringBuilder(2 * cipher_byte.length);
                for(byte b: cipher_byte) {
                        sb.append(String.format("%02x", b&0xff) );
                }
                System.out.println( sb );
                text = sb.toString();
        } catch (Exception e) {
                e.printStackTrace();
        }
		return text;
	}

}
