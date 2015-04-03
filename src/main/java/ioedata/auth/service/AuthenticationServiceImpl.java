package ioedata.auth.service;

import ioedata.token.util.TokenConverter;
import ioedata.token.util.TokenEntity;
import ioedata.token.util.TokenKeyGenerator;

import org.jose4j.keys.AesKey;
import org.jose4j.lang.JoseException;
import org.json.JSONException;
import org.springframework.stereotype.Service;

/**
 * This class implements AuthenticationService interface, for converting tokens and claims.
 * @author ajou
 *
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Override
	public String getAuthToken(String claim, int key) throws JoseException,
			JSONException {
		String authToken = null;
		byte[] tokenKey = TokenKeyGenerator.getTokenKeyGenerator().getTokenKey(
				key);
		TokenConverter converter = TokenConverter.getTokenConverter();
		converter.setClaim(claim);
		converter.setKey(new AesKey(tokenKey));
		String token = converter.getToken();
		if (token != null) {
			authToken = new TokenEntity(token, tokenKey).toJsonObjectString();
		}
		return authToken;
	}

	@Override
	public String getAuthClaim(String token, byte[] tokenKey)
			throws JoseException {
		TokenConverter converter = TokenConverter.getTokenConverter();
		converter.setKey(new AesKey(tokenKey));
		return converter.getClaim(token);
	}
}