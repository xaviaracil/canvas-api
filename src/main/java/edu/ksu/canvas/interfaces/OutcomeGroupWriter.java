package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.outcomes.Outcome;
import edu.ksu.canvas.model.outcomes.OutcomeGroup;
import edu.ksu.canvas.model.outcomes.OutcomeLink;
import edu.ksu.canvas.requestOptions.CreateOutcomeGroupOptions;
import edu.ksu.canvas.requestOptions.CreateOutcomeOptions;

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
	 * @param options An object containing the information needed to create a subgroup in Canvas
	 * @return the newly created Outcome Group
	 * @throws IOException When there is an error communicating with Canvas
	 */
	Optional<OutcomeGroup> createSubgroup(String parentOutcomeGroupId, CreateOutcomeGroupOptions options) throws IOException;

	/**
	 *
	 * Creates a new empty subgroup under the outcome group with the given title and description.
	 * See <a href="https://canvas.instructure.com/doc/api/outcome_groups.html#method.outcome_groups_api.create">Create a subgroup</a>
	 * in the Canvas docs for details.
	 * @param accountId the account ID of the account under which to place this subgroup
	 * @param parentOutcomeGroupId the outcome group ID of the outcome group under which to place this subgroup
	 * @param options An object containing the information needed to create a subgroup in Canvas
	 * @return the newly created Outcome Group
	 * @throws IOException When there is an error communicating with Canvas
	 */
	Optional<OutcomeGroup> createSubgroupInAccount(String accountId, String parentOutcomeGroupId, CreateOutcomeGroupOptions options) throws IOException;

	/**
	 * Creates a new empty subgroup under the outcome group with the given title and description.
	 * See <a href="https://canvas.instructure.com/doc/api/outcome_groups.html#method.outcome_groups_api.create">Create a subgroup</a>
	 * in the Canvas docs for details.
	 * @param courseId the course ID of the course under which to place this subgroup
	 * @param parentOutcomeGroupId the outcome group ID of the outcome group under which to place this subgroup
	 * @param options An object containing the information needed to create a subgroup in Canvas
	 * @return the newly created Outcome Group
	 * @throws IOException When there is an error communicating with Canvas
	 */
	Optional<OutcomeGroup> createSubgroupInCourse(String courseId, String parentOutcomeGroupId, CreateOutcomeGroupOptions options) throws IOException;

	/**
	 * Creates a new outcome linked to the outcome group
	 * See <a href="https://canvas.instructure.com/doc/api/outcome_groups.html#method.outcome_groups_api.link">Create/link an outcome</a>
	 * in the Canvas docs for details.
	 * @param outcomeGroupId the outcome group ID of the outcome group under which to place this outcome
	 * @param outcome A object containing the information needed to create an outcome in Canvas
	 * @return the newly created Outcome Link
	 * @throws IOException When there is an error communicating with Canvas
	 */
	Optional<OutcomeLink> createOutcome(String outcomeGroupId, Outcome outcome) throws IOException;

	/**
	 * Creates a new outcome linked to the outcome group
	 * See <a href="https://canvas.instructure.com/doc/api/outcome_groups.html#method.outcome_groups_api.link">Create/link an outcome</a>
	 * in the Canvas docs for details.
	 * @param accountId the account ID of the account under which to place this outcome
	 * @param outcomeGroupId the outcome group ID of the outcome group under which to place this outcome
	 * @param outcome A object containing the information needed to create an outcome in Canvas
	 * @return the newly created Outcome Link
	 * @throws IOException When there is an error communicating with Canvas
	 */
	Optional<OutcomeLink> createOutcomeInAccount(String accountId, String outcomeGroupId, Outcome outcome) throws IOException;

	/**
	 * Creates a new outcome linked to the outcome group
	 * See <a href="https://canvas.instructure.com/doc/api/outcome_groups.html#method.outcome_groups_api.link">Create/link an outcome</a>
	 * in the Canvas docs for details.
	 * @param courseId the course ID of the course under which to place this subgroup
	 * @param outcomeGroupId the outcome group ID of the outcome group under which to place this outcome
	 * @param outcome A object containing the information needed to create an outcome in Canvas
	 * @return the newly created Outcome Link
	 * @throws IOException When there is an error communicating with Canvas
	 */
	Optional<OutcomeLink> createOutcomeInCourse(String courseId, String outcomeGroupId, Outcome outcome) throws IOException;

	/**
	 * Links an existing outcome to the  group
	 * See <a href="https://canvas.instructure.com/doc/api/outcome_groups.html#method.outcome_groups_api.link">Create/link an outcome</a>
	 * in the Canvas docs for details.
	 * @param courseId the course ID of the course under which to place this subgroup
	 * @param outcomeGroupId the outcome group ID of the outcome group under which to place this outcome
	 * @param linkId the outcome ID of the outcome to be linked
	 * @return the newly created Outcome Link
	 * @throws IOException When there is an error communicating with Canvas
	 */
	Optional<OutcomeLink> linkOutcomeInCourse(String courseId, String outcomeGroupId, String linkId) throws IOException;

	/**
	 * Unlinks an existing outcome from the group
	 * See <a href="https://canvas.instructure.com/doc/api/outcome_groups.html#method.outcome_groups_api.unlink">Unlink an outcome</a>
	 * in the Canvas docs for details.
	 * @param accountId the account ID of the account containing the outcome group
	 * @param outcomeGroupId the outcome group ID of the outcome group under which the outcome is linked
	 * @param outcomeId the outcome ID of the outcome to be unlinked
	 * @return the deleted Outcome Link
	 * @throws IOException When there is an error communicating with Canvas
	 */
	Optional<OutcomeLink> unlinkOutcomeFromAccount(String accountId, String outcomeGroupId, String outcomeId) throws IOException;

	/**
	 * Unlinks an existing outcome from the group
	 * See <a href="https://canvas.instructure.com/doc/api/outcome_groups.html#method.outcome_groups_api.unlink">Unlink an outcome</a>
	 * in the Canvas docs for details.
	 * @param courseId the course ID of the course under which the outcome is linked
	 * @param outcomeGroupId the outcome group ID of the outcome group under which the outcome is linked
	 * @param outcomeId the outcome ID of the outcome to be unlinked
	 * @return the deleted Outcome Link
	 * @throws IOException When there is an error communicating with Canvas
	 */
	Optional<OutcomeLink> unlinkOutcomeFromCourse(String courseId, String outcomeGroupId, String outcomeId) throws IOException;

	/**
	 * Delete an outcome group from an account
	 * See <a href="https://canvas.instructure.com/doc/api/outcome_groups.html#method.outcome_groups_api.destroy">Delete an outcome group</a>
	 * in the Canvas docs for details.
	 * @param accountId the account ID of the account containing the outcome group
	 * @param outcomeGroupId the outcome group ID of the outcome group to delete
	 * @return the deleted Outcome Link
	 * @throws IOException When there is an error communicating with Canvas
	 */
	Optional<OutcomeGroup> deleteOutcomeGroupInAccount(String accountId, String outcomeGroupId) throws IOException;
	/**
	 * Delete an outcome group from a course
	 * See <a href="https://canvas.instructure.com/doc/api/outcome_groups.html#method.outcome_groups_api.destroy">Delete an outcome group</a>
	 * in the Canvas docs for details.
	 * @param courseId the course ID of the course under hte outcome group is placed
	 * @param outcomeGroupId the outcome group ID of the outcome group to delete
	 * @return the deleted Outcome Link
	 * @throws IOException When there is an error communicating with Canvas
	 */
	Optional<OutcomeGroup> deleteOutcomeGroupInCourse(String courseId, String outcomeGroupId) throws IOException;
}
