package edu.ksu.canvas.impl;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.interfaces.OutcomeGroupReader;
import edu.ksu.canvas.interfaces.OutcomeGroupWriter;
import edu.ksu.canvas.model.outcomes.Outcome;
import edu.ksu.canvas.model.outcomes.OutcomeGroup;
import edu.ksu.canvas.model.outcomes.OutcomeLink;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.net.auth.AuthorizationToken;
import edu.ksu.canvas.requestOptions.CreateOutcomeGroupOptions;
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
public class OutcomeGroupImpl extends BaseImpl<OutcomeGroup, OutcomeGroupReader, OutcomeGroupWriter> implements OutcomeGroupReader, OutcomeGroupWriter {
	private static final Logger LOG = LoggerFactory.getLogger(OutcomeGroup.class);

	public OutcomeGroupImpl(String canvasBaseUrl, Integer apiVersion, AuthorizationToken authorizationToken, RestClient restClient, int connectTimeout, int readTimeout, Integer paginationPageSize, Boolean serializeNulls) {
		super(canvasBaseUrl, apiVersion, authorizationToken, restClient, connectTimeout, readTimeout, paginationPageSize, serializeNulls);
	}

	@Override
	public Optional<OutcomeGroup> getRootOutcomeGroup() throws IOException {
		LOG.debug("getting global root outcome group ");
		String url = buildCanvasUrl("global/root_outcome_group", Collections.emptyMap());

		Response response = canvasMessenger.getSingleResponseFromCanvas(authorizationToken, url);
		if (response.getErrorHappened() || response.getResponseCode() != 200) {
			return Optional.empty();
		}
		return responseParser.parseToObject(OutcomeGroup.class, response);
	}

	@Override
	public Optional<OutcomeGroup> getRootOutcomeGroupInAccount(String accountId) throws IOException {
		LOG.debug("getting root outcome group in account {}", accountId);
		String url = buildCanvasUrl("accounts/" + accountId + "/root_outcome_group", Collections.emptyMap());

		Response response = canvasMessenger.getSingleResponseFromCanvas(authorizationToken, url);
		if (response.getErrorHappened() || response.getResponseCode() != 200) {
			return Optional.empty();
		}
		return responseParser.parseToObject(OutcomeGroup.class, response);
	}

	@Override
	public Optional<OutcomeGroup> getRootOutcomeGroupInCourse(String courseId) throws IOException {
		LOG.debug("getting root outcome group in course {}", courseId);
		String url = buildCanvasUrl("courses/" + courseId + "/root_outcome_group", Collections.emptyMap());

		Response response = canvasMessenger.getSingleResponseFromCanvas(authorizationToken, url);
		if (response.getErrorHappened() || response.getResponseCode() != 200) {
			return Optional.empty();
		}
		return responseParser.parseToObject(OutcomeGroup.class, response);
	}

	@Override
	public List<OutcomeGroup> getOutcomeGroupsInAccount(String accountId) throws IOException {
		LOG.debug("Retrieving outcome groups for account {}", accountId);
		String url = buildCanvasUrl("accounts/" + accountId + "/outcome_groups", Collections.emptyMap());
		return getListFromCanvas(url);
	}

	@Override
	public List<OutcomeGroup> getOutcomeGroupsInCourse(String courseId) throws IOException {
		LOG.debug("Retrieving outcome groups for course {}", courseId);
		String url = buildCanvasUrl("courses/" + courseId + "/outcome_groups", Collections.emptyMap());
		return getListFromCanvas(url);
	}

	@Override
	public Optional<OutcomeGroup> getOutcomeGroup(String outcomeGroupId) throws IOException {
		LOG.debug("getting outcome group {}", outcomeGroupId);
		String url = buildCanvasUrl("global/outcome_groups/" + outcomeGroupId, Collections.emptyMap());

		Response response = canvasMessenger.getSingleResponseFromCanvas(authorizationToken, url);
		if (response.getErrorHappened() || response.getResponseCode() != 200) {
			return Optional.empty();
		}
		return responseParser.parseToObject(OutcomeGroup.class, response);
	}

	@Override
	public Optional<OutcomeGroup> getOutcomeGroupInAccount(String accountId, String outcomeGroupId) throws IOException {
		LOG.debug("getting outcome group for account {} with id {}", accountId, outcomeGroupId);
		String url = buildCanvasUrl("accounts/" + accountId + "/outcome_groups/" + outcomeGroupId, Collections.emptyMap());

		Response response = canvasMessenger.getSingleResponseFromCanvas(authorizationToken, url);
		if (response.getErrorHappened() || response.getResponseCode() != 200) {
			return Optional.empty();
		}
		return responseParser.parseToObject(OutcomeGroup.class, response);
	}

	@Override
	public Optional<OutcomeGroup> getOutcomeGroupInCourse(String courseId, String outcomeGroupId) throws IOException {
		LOG.debug("getting outcome group for course {} with id {}", courseId, outcomeGroupId);
		String url = buildCanvasUrl("courses/" + courseId + "/outcome_groups/" + outcomeGroupId, Collections.emptyMap());

		Response response = canvasMessenger.getSingleResponseFromCanvas(authorizationToken, url);
		if (response.getErrorHappened() || response.getResponseCode() != 200) {
			return Optional.empty();
		}
		return responseParser.parseToObject(OutcomeGroup.class, response);
	}

	@Override
	public Optional<Outcome> getOutcome(String id) throws IOException {
		LOG.debug("getting outcome with id {}", id);
		String url = buildCanvasUrl("outcomes/" + id, Collections.emptyMap());

		Response response = canvasMessenger.getSingleResponseFromCanvas(authorizationToken, url);
		if (response.getErrorHappened() || response.getResponseCode() != 200) {
			return Optional.empty();
		}
		return responseParser.parseToObject(Outcome.class, response);
	}

	@Override
	public Optional<OutcomeGroup> createSubgroup(String parentOutcomeGroupId, CreateOutcomeGroupOptions options) throws IOException {
		LOG.debug("creating outcome group as subgroup of {}", parentOutcomeGroupId);
		String url = buildCanvasUrl("global/outcome_groups/" + parentOutcomeGroupId + "/subgroups", Collections.emptyMap());
		Response response = canvasMessenger.sendToCanvas(authorizationToken, url, options.getOptionsMap());
		return responseParser.parseToObject(OutcomeGroup.class, response);
	}

	@Override
	public Optional<OutcomeGroup> createSubgroupInAccount(String accountId, String parentOutcomeGroupId, CreateOutcomeGroupOptions options) throws IOException {
		LOG.debug("creating outcome group in account {} as subgroup of {}", accountId, parentOutcomeGroupId);
		String url = buildCanvasUrl("accounts/" + accountId + "/outcome_groups/" + parentOutcomeGroupId + "/subgroups", Collections.emptyMap());
		Response response = canvasMessenger.sendToCanvas(authorizationToken, url, options.getOptionsMap());
		return responseParser.parseToObject(OutcomeGroup.class, response);
	}

	@Override
	public Optional<OutcomeGroup> createSubgroupInCourse(String courseId, String parentOutcomeGroupId, CreateOutcomeGroupOptions options) throws IOException {
		LOG.debug("creating outcome group in course {} as subgroup of {}", courseId, parentOutcomeGroupId);
		String url = buildCanvasUrl("courses/" + courseId + "/outcome_groups/" + parentOutcomeGroupId + "/subgroups", Collections.emptyMap());
		Response response = canvasMessenger.sendToCanvas(authorizationToken, url, options.getOptionsMap());
		return responseParser.parseToObject(OutcomeGroup.class, response);
	}

	@Override
	public Optional<OutcomeLink> createOutcome(String outcomeGroupId, Outcome outcome) throws IOException {
		LOG.debug("creating outcome in {}", outcomeGroupId);
		String url = buildCanvasUrl("global/outcome_groups/" + outcomeGroupId + "/outcomes", Collections.emptyMap());
		final JsonObject jsonObject = outcome.toJsonObject(serializeNulls);
		Response response = canvasMessenger.sendJsonPostToCanvas(authorizationToken, url, jsonObject.getAsJsonObject("outcome"));
		return responseParser.parseToObject(OutcomeLink.class, response);
	}

	@Override
	public Optional<OutcomeLink> createOutcomeInAccount(String accountId, String outcomeGroupId, Outcome outcome) throws IOException {
		LOG.debug("creating outcome in account {} as child of {}", accountId, outcomeGroupId);
		String url = buildCanvasUrl("accounts/" + accountId + "/outcome_groups/" + outcomeGroupId + "/outcomes", Collections.emptyMap());
		final JsonObject jsonObject = outcome.toJsonObject(serializeNulls);
		Response response = canvasMessenger.sendJsonPostToCanvas(authorizationToken, url, jsonObject.getAsJsonObject("outcome"));
		return responseParser.parseToObject(OutcomeLink.class, response);
	}

	@Override
	public Optional<OutcomeLink> createOutcomeInCourse(String courseId, String outcomeGroupId, Outcome outcome) throws IOException {
		LOG.debug("creating outcome group in course {} as child of {}", courseId, outcomeGroupId);
		String url = buildCanvasUrl("courses/" + courseId + "/outcome_groups/" + outcomeGroupId + "/outcomes", Collections.emptyMap());
		final JsonObject jsonObject = outcome.toJsonObject(serializeNulls);
		Response response = canvasMessenger.sendJsonPostToCanvas(authorizationToken, url, jsonObject.getAsJsonObject("outcome"));
		return responseParser.parseToObject(OutcomeLink.class, response);
	}

	@Override
	public Optional<OutcomeLink> linkOutcomeInCourse(String courseId, String outcomeGroupId, String outcomeId) throws IOException {
		LOG.debug("link outcome {} in outcome group of course {}", outcomeId, courseId, outcomeGroupId);
		String url = buildCanvasUrl("courses/" + courseId + "/outcome_groups/" + outcomeGroupId + "/outcomes/" + outcomeId, Collections.emptyMap());
		Response response = canvasMessenger.sendJsonPutToCanvas(authorizationToken, url, new JsonObject());
		return responseParser.parseToObject(OutcomeLink.class, response);
	}

	@Override
	public Optional<OutcomeGroup> deleteOutcomeGroupInAccount(String accountId, String outcomeGroupId) throws IOException {
		LOG.debug("Deleting outcome group {} from account {}", outcomeGroupId, accountId);
		String url = buildCanvasUrl("accounts/" + accountId + "/outcome_groups/" + outcomeGroupId, Collections.emptyMap());
		Response response = canvasMessenger.deleteFromCanvas(oauthToken, url, Collections.emptyMap());
		return responseParser.parseToObject(OutcomeGroup.class, response);
	}

	@Override
	public Optional<OutcomeGroup> deleteOutcomeGroupInCourse(String courseId, String outcomeGroupId) throws IOException {
		LOG.debug("Deleting outcome group {} from course {}", outcomeGroupId, courseId);
		String url = buildCanvasUrl("courses/" + courseId + "/outcome_groups/" + outcomeGroupId, Collections.emptyMap());
		Response response = canvasMessenger.deleteFromCanvas(oauthToken, url, Collections.emptyMap());
		return responseParser.parseToObject(OutcomeGroup.class, response);
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
		return new TypeToken<List<OutcomeGroup>>(){}.getType();
	}

	@Override
	protected Class<OutcomeGroup> objectType() {
		return OutcomeGroup.class;
	}
}

