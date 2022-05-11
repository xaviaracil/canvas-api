package edu.ksu.canvas.impl;

import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.interfaces.LoginReader;
import edu.ksu.canvas.interfaces.LoginWriter;
import edu.ksu.canvas.model.Login;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.net.auth.AuthorizationToken;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyMap;

public class LoginImpl extends BaseImpl<Login, LoginReader, LoginWriter> implements LoginReader, LoginWriter {
    private static final Logger LOG = LoggerFactory.getLogger(LoginImpl.class);

    public LoginImpl(String canvasBaseUrl, Integer apiVersion, AuthorizationToken authorizationToken, RestClient restClient,
										 int connectTimeout, int readTimeout, Integer paginationPageSize, Boolean serializeNulls) {
        super(canvasBaseUrl, apiVersion, authorizationToken, restClient, connectTimeout, readTimeout,
                paginationPageSize, serializeNulls);
    }

    @Override
    public List<Login> getLoginForUser(String userId) throws IOException {
        LOG.debug("Retrieving logins for user id {}", userId);
        String url = buildCanvasUrl(String.format("users/%s/logins", userId), emptyMap());

        return getListFromCanvas(url);
    }

    @Override
    public Optional<Login> updateLogin(Login login) throws IOException {
        LOG.debug("Updating login {} on account {}", login.getId(), login.getAccountId());
        if(StringUtils.isAnyBlank(login.getAccountId(), login.getId())) {
            throw new IllegalArgumentException("Account ID and Login ID are required to update a login");
        }

        String url = buildCanvasUrl(String.format("accounts/%s/logins/%s", login.getAccountId(), login.getId()), emptyMap());
        Response response = canvasMessenger.sendJsonPutToCanvas(authorizationToken, url, login.toJsonObject(serializeNulls));
        return responseParser.parseToObject(Login.class, response);
    }

    @Override
    public Optional<Login> deleteLogin(Login login) throws IOException {
        LOG.debug("Deleting login {} for user {}", login.getId(), login.getUserId());
        if(StringUtils.isAnyBlank(login.getUserId(), login.getId())) {
            throw new IllegalArgumentException("User ID and Login ID are required to delete a login");
        }

        String url = buildCanvasUrl(String.format("users/%s/logins/%s", login.getUserId(), login.getId()), emptyMap());
        Response response = canvasMessenger.deleteFromCanvas(authorizationToken, url, emptyMap());
        return responseParser.parseToObject(Login.class, response);
    }

    @Override
    protected Type listType() {
        return new TypeToken<List<Login>>() {
        }.getType();
    }

    @Override
    protected Class<Login> objectType() {
        return Login.class;
    }
}
