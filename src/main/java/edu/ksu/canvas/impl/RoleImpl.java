package edu.ksu.canvas.impl;

import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.interfaces.RoleReader;
import edu.ksu.canvas.interfaces.RoleWriter;
import edu.ksu.canvas.model.Role;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.net.auth.AuthorizationToken;
import edu.ksu.canvas.requestOptions.ListRolesOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class RoleImpl extends BaseImpl<Role, RoleReader, RoleWriter> implements RoleReader, RoleWriter {
    private static final Logger LOG = LoggerFactory.getLogger(RoleImpl.class);

    public RoleImpl(String canvasBaseUrl, Integer apiVersion, AuthorizationToken authorizationToken, RestClient restClient,
										int connectTimeout, int readTimeout, Integer paginationPageSize, Boolean serializeNulls) {
        super(canvasBaseUrl, apiVersion, authorizationToken, restClient, connectTimeout, readTimeout,
                paginationPageSize, serializeNulls);
    }

    @Override
    public List<Role> listRoles(ListRolesOptions options) throws IOException {
        LOG.debug("Retrieving roles for account {}", options.getAccountId());
        String url = buildCanvasUrl("accounts/" + options.getAccountId() + "/roles", options.getOptionsMap());
        return getListFromCanvas(url);
    }

    @Override
    protected Type listType() {
        return new TypeToken<List<Role>>(){}.getType();
    }

    @Override
    protected Class<Role> objectType() {
        return Role.class;
    }

}
