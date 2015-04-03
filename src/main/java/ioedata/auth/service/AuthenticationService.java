package ioedata.auth.service;

import org.jose4j.lang.JoseException;
import org.json.JSONException;

/**
 * This interface provides abstract methods for dealing with authentication
 * works, and it will be implemented by AuthenticationServiceImpl class.
 * 
 * @author ajou
 * 
 */
public interface AuthenticationService {
	/*
	 * Get an encrypted token held by a Json object, and the Json object is
	 * returned with the key.
	 */
	public String getAuthToken(String claim, int key) throws JoseException,
			JSONException;

	/*
	 * Get an decrypted claim by a token.
	 */
	public String getAuthClaim(String token, byte[] tokenKey)
			throws JoseException;
}