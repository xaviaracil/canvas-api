package edu.ksu.canvas.oauth;

import edu.ksu.canvas.net.auth.AuthorizationToken;

import java.io.Serializable;

public interface OauthToken extends AuthorizationToken {

    String getAccessToken();
   
    void refresh();
}
