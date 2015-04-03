package ioedata.token.util;

import java.util.Arrays;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class is used for binding token and token key.
 */
public class TokenEntity {
	private String token;
	private byte[] tokenKey;
	
	public TokenEntity() {
		super();
	}

	public TokenEntity(String token, byte[] tokenKey) {
		super();
		this.token = token;
		this.tokenKey = tokenKey;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public byte[] getTokenKey() {
		return tokenKey;
	}

	public void setTokenKey(byte[] tokenKey) {
		this.tokenKey = tokenKey;
	}

	@Override
	public String toString() {
		return "TokenEntity [token=" + token + ", tokenKey="
				+ Arrays.toString(tokenKey) + "]";
	}

	public String toJsonObjectString() throws JSONException {
		return new JSONObject().put("token", token).put("tokenKey", this.convertTokenKeyToString()).toString();
	}
	
	public JSONObject toJsonObject(String tokenEntityString) throws JSONException {
		return new JSONObject(tokenEntityString);
	}
	
	public String convertTokenKeyToString() {
		return Arrays.toString(tokenKey);
	}
}
