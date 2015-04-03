package ioedata.token.util;

import org.jose4j.lang.ByteUtil;

/**
 * This class creates byte array type token key using an integer key.
 * @author ajou
 *
 */
public class TokenKeyGenerator {
	private TokenKeyGenerator() {}
	public static TokenKeyGenerator generator = null;
	
	public static TokenKeyGenerator getTokenKeyGenerator() {
		return new TokenKeyGenerator();
	}
	
	public static byte[] getTokenKey(int key) {
		return ByteUtil.randomBytes(key);
	}
}
