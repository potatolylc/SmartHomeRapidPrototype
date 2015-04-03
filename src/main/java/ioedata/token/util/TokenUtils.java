package ioedata.token.util;

import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers;
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers;

/**
 * This interface holds constant values for token works.
 * @author ajou
 *
 */
public interface TokenUtils {
	public String KEY_MANAGEMENT_ALGORITHM_ID = KeyManagementAlgorithmIdentifiers.A128KW;
	public String CONTENT_ENCRYPTION_ALGORITHM_ID = ContentEncryptionAlgorithmIdentifiers.AES_128_CBC_HMAC_SHA_256;
}
