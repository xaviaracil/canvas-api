package edu.ksu.canvas.net.auth;

/**
 * Universitat Oberta de Catalunya
 * Made for the project canvas-api
 */
public class BasicAuthorizationToken implements AuthorizationToken {
	private String token;

	public BasicAuthorizationToken(String token) {
		this.token = token;
	}

	@Override
	public String getHeaderMethod() {
		return "Basic";
	}

	@Override
	public String getToken() {
		return token;
	}

	@Override
	public void refresh() {
		// nothing here
	}
}
