package edu.ksu.canvas.impl;

import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.interfaces.AdminReader;
import edu.ksu.canvas.interfaces.AdminWriter;
import edu.ksu.canvas.model.AccountAdmin;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.net.auth.AuthorizationToken;
import edu.ksu.canvas.requestOptions.ListAccountAdminsOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class AdminImpl extends BaseImpl<AccountAdmin, AdminReader, AdminWriter> implements AdminReader, AdminWriter {
    private static final Logger LOG = LoggerFactory.getLogger(AdminImpl.class);

    public AdminImpl(String canvasBaseUrl, Integer apiVersion, AuthorizationToken authorizationToken, RestClient restClient,
										 int connectTimeout, int readTimeout, Integer paginationPageSize, Boolean serializeNulls) {
        super(canvasBaseUrl, apiVersion, authorizationToken, restClient, connectTimeout, readTimeout,
                paginationPageSize, serializeNulls);
    }

    @Override
    public List<AccountAdmin> listAccountAdmins(ListAccountAdminsOptions options) throws IOException {
        LOG.debug("Getting list of account admins");
        String url = buildCanvasUrl("accounts/" + options.getAccountId() + "/admins", options.getOptionsMap());
        return getListFromCanvas(url);
    }

    @Override
    protected Type listType() {
        return new TypeToken<List<AccountAdmin>>(){}.getType();
    }

    @Override
    protected Class<AccountAdmin> objectType() {
        return AccountAdmin.class;
    }
}
