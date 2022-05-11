package edu.ksu.canvas.net.auth;

import java.io.Serializable;

/**
 * Universitat Oberta de Catalunya
 * Made for the project canvas-api
 */
public interface AuthorizationToken extends Serializable {
	String getToken();
	void refresh();
}
