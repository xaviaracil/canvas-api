package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.outcomes.Outcome;
import edu.ksu.canvas.model.outcomes.OutcomeGroup;
import edu.ksu.canvas.model.outcomes.OutcomeLink;

import java.io.IOException;
import java.util.Optional;

/**
 * Universitat Oberta de Catalunya
 * Made for the project canvas-api
 */
public interface OutcomeWriter extends CanvasWriter<OutcomeLink, OutcomeWriter> {

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
}
