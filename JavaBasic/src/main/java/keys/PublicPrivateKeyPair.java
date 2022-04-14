package keys;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PublicPrivateKeyPair {

	public static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
		
		SecureRandom rnd = new SecureRandom();
		
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
		keyGen.initialize(1024, rnd);
		
		return keyGen.generateKeyPair();
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
		
		KeyPair kp = generateKeyPair();
		
        System.out.println ("-----BEGIN PRIVATE KEY-----");
        System.out.println (Base64.getMimeEncoder().encodeToString( kp.getPrivate().getEncoded()));
        System.out.println ("-----END PRIVATE KEY-----");
        System.out.println ("-----BEGIN PUBLIC KEY-----");
        System.out.println (Base64.getMimeEncoder().encodeToString( kp.getPublic().getEncoded()));
        System.out.println ("-----END PUBLIC KEY-----");
        
	}
}
