package ioedata.token.util;

import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.lang.JoseException;

/**
 * This class provides methods for converting token from/to claims.
 * @author ajou
 *
 */
public class TokenConverter extends JsonWebEncryption {
	private TokenConverter() {}
	private static TokenConverter converter = null;
	
	public static TokenConverter getTokenConverter() {
		if(converter == null) {
			converter = new TokenConverter();
			converter.setAlgorithmHeaderValue(TokenUtils.KEY_MANAGEMENT_ALGORITHM_ID);
			converter.setEncryptionMethodHeaderParameter(TokenUtils.CONTENT_ENCRYPTION_ALGORITHM_ID);
		}
		return converter;
	}
	
	public static void setClaim(String claim) {
		converter.setPayload(claim);
	}
	
	public static String getClaim(String token) throws JoseException {
		converter.setCompactSerialization(token);
		return converter.getPayload();
	}
	
	public static String getToken() throws JoseException {
		return converter.getCompactSerialization();
	}
}
