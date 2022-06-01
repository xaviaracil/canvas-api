package edu.ksu.canvas.impl;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.interfaces.OutcomeReader;
import edu.ksu.canvas.interfaces.OutcomeWriter;
import edu.ksu.canvas.model.outcomes.Outcome;
import edu.ksu.canvas.model.outcomes.OutcomeLink;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.oauth.OauthToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Universitat Oberta de Catalunya
 * Made for the project canvas-api
 */
public class OutcomeImpl extends BaseImpl<OutcomeLink, OutcomeReader, OutcomeWriter> implements OutcomeReader, OutcomeWriter {
	private static final Logger LOG = LoggerFactory.getLogger(OutcomeLink.class);

	public OutcomeImpl(String canvasBaseUrl, Integer apiVersion, OauthToken oauthToken, RestClient restClient, int connectTimeout, int readTimeout, Integer paginationPageSize, Boolean serializeNulls) {
		super(canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimeout, paginationPageSize, serializeNulls);
	}


	@Override
	public List<OutcomeLink> getLinkedOutcomesInAccount(String accountId, String id) throws IOException {
		LOG.debug("getting linked outcomes for outcome group for account {} with id {}", accountId, id);
		String url = buildCanvasUrl("accounts/" + accountId + "/outcome_groups/" + id + "/outcomes", Collections.emptyMap());
		return getListFromCanvas(url);
	}

	@Override
	public List<OutcomeLink> getLinkedOutcomesInCourse(String courseId, String id) throws IOException {
		LOG.debug("getting linked outcomes from outcome group for course {} with id {}", courseId, id);
		String url = buildCanvasUrl("courses/" + courseId + "/outcome_groups/" + id + "/outcomes", Collections.emptyMap());
		return getListFromCanvas(url);
	}

	@Override
	public Optional<Outcome> getOutcome(String id) throws IOException {
		LOG.debug("getting outcome with id {}", id);
		String url = buildCanvasUrl("outcomes/" + id, Collections.emptyMap());

		Response response = canvasMessenger.getSingleResponseFromCanvas(oauthToken, url);
		if (response.getErrorHappened() || response.getResponseCode() != 200) {
			return Optional.empty();
		}
		return responseParser.parseToObject(Outcome.class, response);
	}

	@Override
	public Optional<OutcomeLink> createOutcome(String outcomeGroupId, Outcome outcome) throws IOException {
		LOG.debug("creating outcome in {}", outcomeGroupId);
		String url = buildCanvasUrl("global/outcome_groups/" + outcomeGroupId + "/outcomes", Collections.emptyMap());
		final JsonObject jsonObject = outcome.toJsonObject(serializeNulls);
		Response response = canvasMessenger.sendJsonPostToCanvas(oauthToken, url, jsonObject.getAsJsonObject("outcome"));
		return responseParser.parseToObject(OutcomeLink.class, response);
	}

	@Override
	public Optional<OutcomeLink> createOutcomeInAccount(String accountId, String outcomeGroupId, Outcome outcome) throws IOException {
		LOG.debug("creating outcome in account {} as child of {}", accountId, outcomeGroupId);
		String url = buildCanvasUrl("accounts/" + accountId + "/outcome_groups/" + outcomeGroupId + "/outcomes", Collections.emptyMap());
		final JsonObject jsonObject = outcome.toJsonObject(serializeNulls);
		Response response = canvasMessenger.sendJsonPostToCanvas(oauthToken, url, jsonObject.getAsJsonObject("outcome"));
		return responseParser.parseToObject(OutcomeLink.class, response);
	}

	@Override
	public Optional<OutcomeLink> createOutcomeInCourse(String courseId, String outcomeGroupId, Outcome outcome) throws IOException {
		LOG.debug("creating outcome group in course {} as child of {}", courseId, outcomeGroupId);
		String url = buildCanvasUrl("courses/" + courseId + "/outcome_groups/" + outcomeGroupId + "/outcomes", Collections.emptyMap());
		final JsonObject jsonObject = outcome.toJsonObject(serializeNulls);
		Response response = canvasMessenger.sendJsonPostToCanvas(oauthToken, url, jsonObject.getAsJsonObject("outcome"));
		return responseParser.parseToObject(OutcomeLink.class, response);
	}

	@Override
	public Optional<OutcomeLink> linkOutcomeInCourse(String courseId, String outcomeGroupId, String outcomeId) throws IOException {
		LOG.debug("link outcome {} in outcome group of course {}", outcomeId, courseId, outcomeGroupId);
		String url = buildCanvasUrl("courses/" + courseId + "/outcome_groups/" + outcomeGroupId + "/outcomes/" + outcomeId, Collections.emptyMap());
		Response response = canvasMessenger.sendJsonPutToCanvas(oauthToken, url, new JsonObject());
		return responseParser.parseToObject(OutcomeLink.class, response);
	}

	@Override
	public Optional<OutcomeLink> unlinkOutcomeFromAccount(String accountId, String outcomeGroupId, String outcomeId) throws IOException {
		LOG.debug("unlink outcome {} from outcome group {} of account {}", outcomeId, outcomeGroupId, accountId);
		String url = buildCanvasUrl("accounts/" + accountId + "/outcome_groups/" + outcomeGroupId + "/outcomes/" + outcomeId, Collections.emptyMap());
		Response response = canvasMessenger.deleteFromCanvas(oauthToken, url, Collections.emptyMap());
		return responseParser.parseToObject(OutcomeLink.class, response);
	}

	@Override
	public Optional<OutcomeLink> unlinkOutcomeFromCourse(String courseId, String outcomeGroupId, String outcomeId) throws IOException {
		LOG.debug("unlink outcome {} from outcome group {} of course {}", outcomeId, outcomeGroupId, courseId);
		String url = buildCanvasUrl("courses/" + courseId + "/outcome_groups/" + outcomeGroupId + "/outcomes/" + outcomeId, Collections.emptyMap());
		Response response = canvasMessenger.deleteFromCanvas(oauthToken, url, Collections.emptyMap());
		return responseParser.parseToObject(OutcomeLink.class, response);
	}
	@Override
	protected Type listType() {
		return new TypeToken<List<OutcomeLink>>(){}.getType();
	}

	@Override
	protected Class<OutcomeLink> objectType() {
		return OutcomeLink.class;
	}
}

