package edu.ksu.canvas.tests.outcomeGroups;

import edu.ksu.canvas.CanvasTestBase;
import edu.ksu.canvas.impl.OutcomeGroupImpl;
import edu.ksu.canvas.impl.RoleImpl;
import edu.ksu.canvas.interfaces.OutcomeGroupReader;
import edu.ksu.canvas.interfaces.RoleReader;
import edu.ksu.canvas.model.Role;
import edu.ksu.canvas.model.outcomes.OutcomeGroup;
import edu.ksu.canvas.net.FakeRestClient;
import edu.ksu.canvas.requestOptions.ListRolesOptions;
import edu.ksu.canvas.util.CanvasURLBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

/**
 * Universitat Oberta de Catalunya
 * Made for the project canvas-api
 */
public class OutcomeGroupsUTest extends CanvasTestBase {
	@Autowired
	private FakeRestClient fakeRestClient;
	private OutcomeGroupReader outcomeGroupReader;

	private static final String ROOT_ACCOUNT_ID = "1";

	@Before
	public void setupReader() {
		outcomeGroupReader = new OutcomeGroupImpl(baseUrl, apiVersion, SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT,
						SOME_READ_TIMEOUT, DEFAULT_PAGINATION_PAGE_SIZE, false);
	}

	@Test
	public void testListRoles() throws Exception {
		String url = CanvasURLBuilder.buildCanvasUrl(baseUrl, apiVersion,"accounts/" + ROOT_ACCOUNT_ID + "/outcome_groups", Collections.emptyMap());
		fakeRestClient.addSuccessResponse(url, "SampleJson/role/Role.json");
		final List<OutcomeGroup> outcomeGroupList = outcomeGroupReader.getOutcomeGroupsInAccount(ROOT_ACCOUNT_ID);
		Assert.assertNotNull(outcomeGroupList);
		Assert.assertEquals(1, outcomeGroupList.size());
	}
}
