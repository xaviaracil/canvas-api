package edu.ksu.canvas.net.auth;

import edu.ksu.canvas.oauth.OauthToken;
import org.apache.http.message.AbstractHttpMessage;

/**
 * Universitat Oberta de Catalunya
 * Made for the project canvas-api
 */
public class ClientHttpAuthHeaderHandler {
	public void addAuth(AbstractHttpMessage httpGet, AuthorizationToken token) {
		httpGet.setHeader("Authorization", token.getHeaderMethod() + " " + token.getToken());
	}
}
