package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.outcomes.OutcomeGroup;

import java.io.IOException;
import java.util.Optional;

/**
 * Universitat Oberta de Catalunya
 * Made for the project canvas-api
 */
public interface OutcomeGroupWriter extends CanvasWriter<OutcomeGroup, OutcomeGroupWriter> {

	/**
	 * Creates a new empty subgroup under the outcome group with the given title and description.
	 * See <a href="https://canvas.instructure.com/doc/api/outcome_groups.html#method.outcome_groups_api.create">Create a subgroup</a>
	 * in the Canvas docs for details.
	 * @param parentOutcomeGroupId the outcome group ID of the outcome group under which to place this subgroup
	 * @param outcomeGroup A outcome group object containing the information needed to create a subgroup in Canvas
	 * @return the newly created Outcome Group
	 * @throws IOException When there is an error communicating with Canvas
	 */
	Optional<OutcomeGroup> createSubgroup(String parentOutcomeGroupId, OutcomeGroup outcomeGroup) throws IOException;
	/**
	 *
	 * Creates a new empty subgroup under the outcome group with the given title and description.
	 * See <a href="https://canvas.instructure.com/doc/api/outcome_groups.html#method.outcome_groups_api.create">Create a subgroup</a>
	 * in the Canvas docs for details.
	 * @param accountId the account ID of the account under which to place this subgroup
	 * @param parentOutcomeGroupId the outcome group ID of the outcome group under which to place this subgroup
	 * @param outcomeGroup A outcome group object containing the information needed to create a subgroup in Canvas
	 * @return the newly created Outcome Group
	 * @throws IOException When there is an error communicating with Canvas
	 */
	Optional<OutcomeGroup> createSubgroupInAccount(String accountId, String parentOutcomeGroupId, OutcomeGroup outcomeGroup) throws IOException;
	/**
	 * Creates a new empty subgroup under the outcome group with the given title and description.
	 * See <a href="https://canvas.instructure.com/doc/api/outcome_groups.html#method.outcome_groups_api.create">Create a subgroup</a>
	 * in the Canvas docs for details.
	 * @param courseId the course ID of the course under which to place this subgroup
	 * @param parentOutcomeGroupId the outcome group ID of the outcome group under which to place this account
	 * @param outcomeGroup A outcome group object containing the information needed to create a subgroup in Canvas
	 * @return the newly created Outcome Group
	 * @throws IOException When there is an error communicating with Canvas
	 */
	Optional<OutcomeGroup> createSubgroupInCourse(String courseId, String parentOutcomeGroupId, OutcomeGroup outcomeGroup) throws IOException;
}
