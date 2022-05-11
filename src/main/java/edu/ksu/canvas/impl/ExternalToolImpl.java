package edu.ksu.canvas.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.interfaces.ExternalToolReader;
import edu.ksu.canvas.interfaces.ExternalToolWriter;
import edu.ksu.canvas.model.ExternalTool;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.net.auth.AuthorizationToken;
import edu.ksu.canvas.requestOptions.ListExternalToolsOptions;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ExternalToolImpl extends BaseImpl<ExternalTool, ExternalToolReader, ExternalToolWriter> implements ExternalToolReader, ExternalToolWriter {
    private static final Logger LOG = LoggerFactory.getLogger(ExternalToolImpl.class);

    public ExternalToolImpl(String canvasBaseUrl, Integer apiVersion, AuthorizationToken authorizationToken, RestClient restClient,
														int connectTimeout, int readTimeout, Integer paginationPageSize, Boolean serializeNulls) {
        super(canvasBaseUrl, apiVersion, authorizationToken, restClient, connectTimeout, readTimeout,
                paginationPageSize, serializeNulls);
    }

    @Override
    public Optional<ExternalTool> getExternalToolInCourse(String courseId, Long toolId) throws IOException {
        return getExternalTool("courses", courseId, toolId);
    }

    @Override
    public Optional<ExternalTool> getExternalToolInAccount(String accountId, Long toolId) throws IOException {
        return getExternalTool("accounts", accountId, toolId);
    }

    private Optional<ExternalTool> getExternalTool(String objectType, String objectId, Long toolId) throws IOException {
        LOG.debug("Getting external tool {} from {} {}", toolId, objectType, objectId);
        if(StringUtils.isBlank(objectId) || toolId == null) {
            throw new IllegalArgumentException("course/account ID and tool ID cannot be blank");
        }
        String url = buildCanvasUrl(objectType + "/" + objectId + "/external_tools/" + toolId, Collections.emptyMap());
        return getFromCanvas(url);
    }

    @Override
    public List<ExternalTool> listExternalToolsInAccount(ListExternalToolsOptions options) throws IOException {
        return listExternalTools("accounts", options.getId(), options.getOptionsMap());
    }

    @Override
    public List<ExternalTool> listExternalToolsInCourse(ListExternalToolsOptions options) throws IOException {
        return listExternalTools("courses", options.getId(), options.getOptionsMap());
    }

    @Override
    public List<ExternalTool> listExternalToolsInGroup(ListExternalToolsOptions options) throws IOException {
        return listExternalTools("groups", options.getId(), options.getOptionsMap());
    }

    private List<ExternalTool> listExternalTools(String objectType, String objectId, Map<String, List<String>> optionsMap) throws IOException {
        LOG.debug("Getting list of external tools from {}: {}", objectType, objectId);
        String url = buildCanvasUrl(objectType + "/" + objectId + "/external_tools", optionsMap);
        return getListFromCanvas(url);
    }

    @Override
    public Optional<ExternalTool> createExternalToolInCourse(String courseId, ExternalTool tool) throws IOException {
        LOG.debug("Creating external tool \"{}\" in course {}", tool.getName(), courseId);
        String url = buildCanvasUrl("courses/" + courseId + "/external_tools", Collections.emptyMap());
        return createExternalTool(url, tool);
    }

    @Override
    public Optional<ExternalTool> createExternalToolInAccount(String accountId, ExternalTool tool) throws IOException {
        LOG.debug("Creating external tool \"{} \" in account {}", tool.getName(), accountId);
        String url = buildCanvasUrl("accounts/" + accountId + "/external_tools", Collections.emptyMap());
        return createExternalTool(url, tool);
    }

    private Optional<ExternalTool> createExternalTool(String url, ExternalTool tool) throws IOException {
        ensureToolValidForCreation(tool);
        Gson gson = GsonResponseParser.getDefaultGsonParser(serializeNulls);
        JsonObject toolJson = gson.toJsonTree(tool).getAsJsonObject();
        Response response = canvasMessenger.sendJsonPostToCanvas(authorizationToken, url, toolJson);
        return responseParser.parseToObject(ExternalTool.class, response);
    }

    @Override
    public Optional<ExternalTool> editExternalToolInCourse(String courseId, ExternalTool tool) throws IOException {
        LOG.debug("Editing external tool \"{}\" in course {}", tool.getName(), courseId);
        String url = buildCanvasUrl("courses/" + courseId + "/external_tools/" + tool.getId(), Collections.emptyMap());
        return editExternalTool(url, tool);
    }

    @Override
    public Optional<ExternalTool> editExternalToolInAccount(String accountId, ExternalTool tool) throws IOException {
        LOG.debug("Editing external tool \"{}\" in course {}", tool.getName(), accountId);
        String url = buildCanvasUrl("accounts/" + accountId + "/external_tools/" + tool.getId(), Collections.emptyMap());
        return editExternalTool(url, tool);
    }

    private Optional<ExternalTool> editExternalTool(String url, ExternalTool tool) throws IOException {
        if(tool.getId() == null) {
            throw new IllegalArgumentException("Tool being edited must have a tool ID");
        }
        Gson gson = GsonResponseParser.getDefaultGsonParser(serializeNulls);
        JsonObject toolJson = gson.toJsonTree(tool).getAsJsonObject();
        Response response = canvasMessenger.sendJsonPutToCanvas(authorizationToken, url, toolJson);
        return responseParser.parseToObject(ExternalTool.class, response);
    }

    public Optional<ExternalTool> deleteExternalToolInCourse(String courseId, Long toolId) throws IOException {
        LOG.debug("Deleting external tool {} from course {}", toolId, courseId);
        String url = buildCanvasUrl("courses/" + courseId + "/external_tools/" + toolId, Collections.emptyMap());
        Response response = canvasMessenger.deleteFromCanvas(authorizationToken, url, Collections.emptyMap());
        return responseParser.parseToObject(ExternalTool.class, response);
    }

    public Optional<ExternalTool> deleteExternalToolInAccount(String accountId, Long toolId) throws IOException {
        LOG.debug("Deleting external tool {} from account {}", toolId, accountId);
        String url = buildCanvasUrl("accounts/" + accountId + "/external_tools/" + toolId, Collections.emptyMap());
        Response response = canvasMessenger.deleteFromCanvas(authorizationToken, url, Collections.emptyMap());
        return responseParser.parseToObject(ExternalTool.class, response);
    }

    /**
     * Ensure that a tool object is valid for creation. The API requires certain fields to be filled out.
     * Throws an IllegalArgumentException if the conditions are not met.
     * @param tool The external tool object we are trying to create
     */
    private void ensureToolValidForCreation(ExternalTool tool) {
        //check for the unconditionally required fields
        if(StringUtils.isAnyBlank(tool.getName(), tool.getPrivacyLevel(), tool.getConsumerKey(), tool.getSharedSecret())) {
            throw new IllegalArgumentException("External tool requires all of the following for creation: name, privacy level, consumer key, shared secret");
        }
        //check that there is either a URL or a domain. One or the other is required
        if(StringUtils.isBlank(tool.getUrl()) && StringUtils.isBlank(tool.getDomain())) {
            throw new IllegalArgumentException("External tool requires either a URL or domain for creation");
        }
    }

    @Override
    protected Type listType() {
        return new TypeToken<List<ExternalTool>>(){}.getType();
    }

    @Override
    protected Class<ExternalTool> objectType() {
        return ExternalTool.class;
    }
}
