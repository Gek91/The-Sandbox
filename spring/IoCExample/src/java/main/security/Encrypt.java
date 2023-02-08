package security;

import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;

import javax.crypto.KeyGenerator;

public class Encrypt {

	public static void main(String[] args) {

		String value = "value";

		//8byte salt
		String salt = KeyGenerators.string().generateKey();

		//byte array encryptor
		BytesEncryptor encryptor = Encryptors.stronger("password", salt);
		byte[] encryptedValue = encryptor.encrypt(value.getBytes());
		byte[] decryptedValue = encryptor.decrypt(encryptedValue);

		System.out.println("salt:" + salt);
		System.out.println("encrypted:" + new String(encryptedValue));
		System.out.println("decrypted:" + new String(decryptedValue));

		//text encryptor
		value = "76616c7565"; //hex encoded
		TextEncryptor textEncryptor = Encryptors.text("password", salt);
		String encryptedTextValue = textEncryptor.encrypt(value);
		String decryptedTextValue = textEncryptor.decrypt(encryptedTextValue);

		System.out.println("salt:" + salt);
		System.out.println("encrypted:" + encryptedTextValue);
		System.out.println("decrypted:" + decryptedTextValue);

		//key generator
		byte[] key = KeyGenerators.secureRandom().generateKey();
		System.out.println(new String(key));
		String stringKey = KeyGenerators.string().generateKey();
		System.out.println(stringKey);
	}
}
