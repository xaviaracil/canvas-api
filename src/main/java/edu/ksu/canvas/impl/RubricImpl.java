package edu.ksu.canvas.impl;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.interfaces.RubricReader;
import edu.ksu.canvas.interfaces.RubricWriter;
import edu.ksu.canvas.model.assignment.Rubric;
import edu.ksu.canvas.model.assignment.RubricWriterResponse;
import edu.ksu.canvas.model.rubric.RubricCreationJSONRequest;
import edu.ksu.canvas.model.rubric.RubricCreationRequest;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.net.auth.AuthorizationToken;
import edu.ksu.canvas.net.auth.BasicAuthorizationToken;
import edu.ksu.canvas.requestOptions.GetRubricOptions;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class RubricImpl extends BaseImpl<Rubric, RubricReader, RubricWriter> implements RubricReader, RubricWriter{
    private static final Logger LOG = LoggerFactory.getLogger(RubricImpl.class);

    public RubricImpl(String canvasBaseUrl, Integer apiVersion, AuthorizationToken authorizationToken, RestClient restClient,
											int connectTimeout, int readTimeout, Integer paginationPageSize, Boolean serializeNulls) {
        super(canvasBaseUrl, apiVersion, authorizationToken, restClient, connectTimeout, readTimeout, paginationPageSize, serializeNulls);
    }

    @Override
    public Optional<Rubric> getRubricInAccount(GetRubricOptions options) throws IOException {
        if(StringUtils.isBlank(options.getCanvasId()) || options.getRubricId() == null) {
            throw new IllegalArgumentException(("Account and rubric IDs must be supplied"));
        }
        LOG.debug("Retrieving rubric {} in account {}", options.getRubricId(), options.getCanvasId());
        String url = buildCanvasUrl(String.format("accounts/%s/rubrics/%d", options.getCanvasId(), options.getRubricId()), options.getOptionsMap());
        Response response = canvasMessenger.getSingleResponseFromCanvas(authorizationToken, url);
        return responseParser.parseToObject(Rubric.class, response);
    }

    @Override
    public Optional<Rubric> getRubricInCourse(GetRubricOptions options) throws IOException {
        if(StringUtils.isBlank(options.getCanvasId()) || options.getRubricId() == null) {
            throw new IllegalArgumentException(("Course and rubric IDs must be supplied"));
        }
        LOG.debug("Retrieving rubric {} in course {}", options.getRubricId(), options.getCanvasId());
        String url = buildCanvasUrl(String.format("courses/%s/rubrics/%d", options.getCanvasId(), options.getRubricId()), options.getOptionsMap());
        Response response = canvasMessenger.getSingleResponseFromCanvas(authorizationToken, url);
        return responseParser.parseToObject(Rubric.class, response);
    }

	@Override
	public List<Rubric> getRubricsInCourse(String courseId) throws IOException {
		LOG.debug("Retrieving rubrics in course {}", courseId);
		String url = buildCanvasUrl(String.format("courses/%s/rubrics", courseId), Collections.emptyMap());
		return getListFromCanvas(url);
	}

	@Override
	public Optional<RubricWriterResponse> createSingleRubricInCourse(String courseId, RubricCreationRequest rubric) throws IOException {
		LOG.debug("creating rubric in course {}", courseId);
		String url = buildCanvasUrl("courses/" + courseId + "/rubrics", Collections.emptyMap());
        if (authorizationToken instanceof BasicAuthorizationToken) {
            RubricCreationJSONRequest rubricCreationJSONRequest = new RubricCreationJSONRequest(rubric);
            JsonObject jsonObject = rubricCreationJSONRequest.toJsonObject(serializeNulls);
            Response response = canvasMessenger.sendJsonPostToCanvas(authorizationToken, url, jsonObject.getAsJsonObject("rubric"));
            return responseParser.parseToObject(RubricWriterResponse.class, response);
        }
		Response response = canvasMessenger.sendToCanvas(authorizationToken, url, rubric.toPostMap(serializeNulls));
		return responseParser.parseToObject(RubricWriterResponse.class, response);
	}

	@Override
	public Optional<RubricWriterResponse> updateSingleRubricInCourse(String courseId, String rubricId, RubricCreationRequest rubric) throws IOException {
		LOG.debug("updating rubric {} in course {}", rubricId, courseId);
		String url = buildCanvasUrl("courses/" + courseId + "/rubrics/" + rubricId, Collections.emptyMap());
        if (authorizationToken instanceof BasicAuthorizationToken) {
            RubricCreationJSONRequest rubricCreationJSONRequest = new RubricCreationJSONRequest(rubric);
            JsonObject jsonObject = rubricCreationJSONRequest.toJsonObject(serializeNulls);
            Response response = canvasMessenger.sendJsonPutToCanvas(authorizationToken, url, jsonObject.getAsJsonObject("rubric"));
            return responseParser.parseToObject(RubricWriterResponse.class, response);
        }
		Response response = canvasMessenger.putToCanvas(authorizationToken, url, rubric.toPostMap(serializeNulls));
		return responseParser.parseToObject(RubricWriterResponse.class, response);
	}

	@Override
	public Optional<Rubric> deleteRubric(String courseId, String id) throws IOException {
		LOG.debug("deleting rubric {} in course {}", id, courseId);
		String url = buildCanvasUrl("courses/" + courseId + "/rubrics/" + id, Collections.emptyMap());
		Response response = canvasMessenger.deleteFromCanvas(authorizationToken, url, Collections.emptyMap());
		return responseParser.parseToObject(Rubric.class, response);
	}

	@Override
    protected Type listType() {
        return new TypeToken<List<Rubric>>(){}.getType();
    }

    @Override
    protected Class<Rubric> objectType() {
        return Rubric.class;
    }
}
