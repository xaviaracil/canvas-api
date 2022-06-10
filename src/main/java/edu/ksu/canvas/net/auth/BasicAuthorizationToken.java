package edu.ksu.canvas.net.auth;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Universitat Oberta de Catalunya
 * Made for the project canvas-api
 */
public class BasicAuthorizationToken implements AuthorizationToken {
	private String user;
	private String password;

	public BasicAuthorizationToken(String user, String password) {
		this.user = user;
		this.password = password;
	}

	@Override
	public String getHeaderMethod() {
		return "Basic";
	}

	@Override
	public String getToken() {
		return new String(Base64.getEncoder().encode((user + ":" + password).getBytes(StandardCharsets.UTF_8)));
	}

	@Override
	public void refresh() {
		// nothing here
	}
}
