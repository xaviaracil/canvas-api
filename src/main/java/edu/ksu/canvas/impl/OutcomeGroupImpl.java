package edu.ksu.canvas.impl;

import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.interfaces.OutcomeGroupReader;
import edu.ksu.canvas.interfaces.OutcomeGroupWriter;
import edu.ksu.canvas.model.outcomes.OutcomeGroup;
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
public class OutcomeGroupImpl extends BaseImpl<OutcomeGroup, OutcomeGroupReader, OutcomeGroupWriter> implements OutcomeGroupReader, OutcomeGroupWriter {
	private static final Logger LOG = LoggerFactory.getLogger(OutcomeGroup.class);

	public OutcomeGroupImpl(String canvasBaseUrl, Integer apiVersion, OauthToken oauthToken, RestClient restClient, int connectTimeout, int readTimeout, Integer paginationPageSize, Boolean serializeNulls) {
		super(canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimeout, paginationPageSize, serializeNulls);
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

		Response response = canvasMessenger.getSingleResponseFromCanvas(oauthToken, url);
		if (response.getErrorHappened() || response.getResponseCode() != 200) {
			return Optional.empty();
		}
		return responseParser.parseToObject(OutcomeGroup.class, response);
	}

	@Override
	public Optional<OutcomeGroup> getOutcomeGroupInAccount(String accountId, String outcomeGroupId) throws IOException {
		LOG.debug("getting outcome group for account {} with id {}", accountId, outcomeGroupId);
		String url = buildCanvasUrl("accounts/" + accountId + "/outcome_groups/" + outcomeGroupId, Collections.emptyMap());

		Response response = canvasMessenger.getSingleResponseFromCanvas(oauthToken, url);
		if (response.getErrorHappened() || response.getResponseCode() != 200) {
			return Optional.empty();
		}
		return responseParser.parseToObject(OutcomeGroup.class, response);
	}

	@Override
	public Optional<OutcomeGroup> getOutcomeGroupInCourse(String courseId, String outcomeGroupId) throws IOException {
		LOG.debug("getting outcome group for course {} with id {}", courseId, outcomeGroupId);
		String url = buildCanvasUrl("courses/" + courseId + "/outcome_groups/" + outcomeGroupId, Collections.emptyMap());

		Response response = canvasMessenger.getSingleResponseFromCanvas(oauthToken, url);
		if (response.getErrorHappened() || response.getResponseCode() != 200) {
			return Optional.empty();
		}
		return responseParser.parseToObject(OutcomeGroup.class, response);
	}

	@Override
	public Optional<OutcomeGroup> createSubgroup(String parentOutcomeGroupId, OutcomeGroup outcomeGroup) throws IOException {
		LOG.debug("creating outcome group as subgroup of {}", parentOutcomeGroupId);
		String url = buildCanvasUrl("global/outcome_groups/" + parentOutcomeGroupId + "/subgroups", Collections.emptyMap());
		Response response = canvasMessenger.sendJsonPostToCanvas(oauthToken, url, outcomeGroup.toJsonObject(serializeNulls));
		return responseParser.parseToObject(OutcomeGroup.class, response);
	}

	@Override
	public Optional<OutcomeGroup> createSubgroupInAccount(String accountId, String parentOutcomeGroupId, OutcomeGroup outcomeGroup) throws IOException {
		LOG.debug("creating outcome group in account {} as subgroup of {}", accountId, parentOutcomeGroupId);
		String url = buildCanvasUrl("accounts/" + accountId + "/outcome_groups/" + parentOutcomeGroupId + "/subgroups", Collections.emptyMap());
		Response response = canvasMessenger.sendJsonPostToCanvas(oauthToken, url, outcomeGroup.toJsonObject(serializeNulls));
		return responseParser.parseToObject(OutcomeGroup.class, response);
	}

	@Override
	public Optional<OutcomeGroup> createSubgroupInCourse(String courseId, String parentOutcomeGroupId, OutcomeGroup outcomeGroup) throws IOException {
		LOG.debug("creating outcome group in course {} as subgroup of {}", courseId, parentOutcomeGroupId);
		String url = buildCanvasUrl("courses/" + courseId + "/outcome_groups/" + parentOutcomeGroupId + "/subgroups", Collections.emptyMap());
		Response response = canvasMessenger.sendJsonPostToCanvas(oauthToken, url, outcomeGroup.toJsonObject(serializeNulls));
		return responseParser.parseToObject(OutcomeGroup.class, response);
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

