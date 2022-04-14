package jwt;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.UUID;

import keys.PublicPrivateKeyPair;
import org.apache.commons.lang3.time.DateUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtToken {

	private final static KeyPair keyPair;
	
	static {
		try {
			keyPair = PublicPrivateKeyPair.generateKeyPair();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	public static String createJwtToken() {
		
		Date now = new Date();
		
		String token = Jwts.builder()
			.setId(UUID.randomUUID().toString()) //Generate identifier
			.setIssuer("issuer") //issuer
			.setSubject("Giacomo") //subject
			.setIssuedAt(now) //date
			.setExpiration(DateUtils.addHours(now, 1))
			.claim("scope", "myScope")
			.signWith(SignatureAlgorithm.RS512, keyPair.getPrivate())
			.compact();
		
		
		return token;
	}
	
	public static void parseJwtToken(String token) {
		
		Jws<Claims> jwtToken = Jwts.parser()
			.setSigningKey(keyPair.getPublic())
			.requireIssuer("issuer")
			.parseClaimsJws(token);
		
		System.out.println("Id: " + jwtToken.getBody().getId());
		System.out.println("Subject: " + jwtToken.getBody().getSubject());
		System.out.println("IssuedAt: " + jwtToken.getBody().getIssuedAt());
		System.out.println("Expiration: " + jwtToken.getBody().getExpiration());
		System.out.println("Scope " + jwtToken.getBody().get("scope"));
	}
	
	public static void main(String[] args) {
		
		String token = createJwtToken();
		
		System.out.println("Token: " + token);
		
		parseJwtToken(token);
	}
}
