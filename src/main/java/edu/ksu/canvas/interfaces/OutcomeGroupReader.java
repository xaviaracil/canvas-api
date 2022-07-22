package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.outcomes.Outcome;
import edu.ksu.canvas.model.outcomes.OutcomeGroup;
import edu.ksu.canvas.model.outcomes.OutcomeLink;
import edu.ksu.canvas.model.outcomes.RootOutcomeGroup;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Universitat Oberta de Catalunya
 * Made for the project canvas-api
 */
public interface OutcomeGroupReader extends CanvasReader<OutcomeGroup, OutcomeGroupReader> {

	/**
	 * Retrieve the root outcome group.
	 * See <a href="https://canvas.instructure.com/doc/api/outcome_groups.html#method.outcome_groups_api.redirect">Redirect to root outcome group for context</a>
	 * in the Canvas docs for details.
	 * @return The root outcome group from Canvas
	 * @throws IOException When there is an error communicating with Canvas
	 */
	Optional<OutcomeGroup> getRootOutcomeGroup() throws IOException;

	/**
	 * Retrieve the root outcome group.
	 * See <a href="https://canvas.instructure.com/doc/api/outcome_groups.html#method.outcome_groups_api.redirect">Redirect to root outcome group for context</a>
	 * in the Canvas docs for details.
	 * @param accountId The account to look for the root outcome group in.
	 * @return The root outcome group from Canvas
	 * @throws IOException When there is an error communicating with Canvas
	 */
	Optional<OutcomeGroup> getRootOutcomeGroupInAccount(String accountId) throws IOException;

	/**
	 * Retrieve the root outcome group.
	 * See <a href="https://canvas.instructure.com/doc/api/outcome_groups.html#method.outcome_groups_api.redirect">Redirect to root outcome group for context</a>
	 * in the Canvas docs for details.
	 * @param courseId The course to look for the root outcome group in.
	 * @return The root outcome group from Canvas
	 * @throws IOException When there is an error communicating with Canvas
	 */
	Optional<OutcomeGroup> getRootOutcomeGroupInCourse(String courseId) throws IOException;

	/**
	 * Retrieve all outcome groups for account.
	 * See <a href="https://canvas.instructure.com/doc/api/outcome_groups.html#method.outcome_groups_api.index">Get all outcome groups for context</a>
	 * in the Canvas docs for details.
	 * @param accountId The account to look for the outcome groups in.
	 * @return List of outcome groups of the account
	 * @throws IOException When there is an error communicating with Canvas
	 */
	List<OutcomeGroup> getOutcomeGroupsInAccount(String accountId) throws IOException;

	/**
	 * Retrieve all outcome groups for course
	 * See <a href="https://canvas.instructure.com/doc/api/outcome_groups.html#method.outcome_groups_api.index">Get all outcome groups for context</a>
	 * in the Canvas docs for details.
	 * @param courseId The course to look for the outcome groups in.
	 * @return List of outcome groups of the course
	 * @throws IOException When there is an error communicating with Canvas
	 */
	List<OutcomeGroup> getOutcomeGroupsInCourse(String courseId) throws IOException;

	/**
	 * Retrieve all outcome groups children of the root outcome group for account.
	 * ONLY MAKES SENSE in PLATIN
	 * @param accountId The account to look for the outcome groups in.
	 * @return List of outcome groups of the account
	 * @throws IOException When there is an error communicating with Canvas	 */
	Optional<RootOutcomeGroup> getOutcomeGroupsFromRootOutcomeGroupInAccount(String accountId) throws IOException;

	/**
	 * Retrieve all outcome groups children of the root outcome group for course
	 * ONLY MAKES SENSE in PLATIN
	 * @param courseId The course to look for the outcome groups in.
	 * @return object with root outcome group and a list of outcome groups of the course
	 * @throws IOException When there is an error communicating with Canvas
	 */
	Optional<RootOutcomeGroup> getOutcomeGroupsFromRootOutcomeGroupInCourse(String courseId) throws IOException;
	/**
	 * Retrieve an outcome group
	 * See <a href="https://canvas.instructure.com/doc/api/outcome_groups.html#method.outcome_groups_api.show">Show an outcome group</a>
	 * in the Canvas docs for details.
	 * @param id the outcome group to look in
	 * @return The outcome group from Canvas
	 * @throws IOException When there is an error communicating with Canvas
	 */
	Optional<OutcomeGroup> getOutcomeGroup(String id) throws IOException;

	/**
	 * Retrieve an outcome group
	 * See <a href="https://canvas.instructure.com/doc/api/outcome_groups.html#method.outcome_groups_api.show">Show an outcome group</a>
	 * in the Canvas docs for details.
	 * @param accountId The account to look for the outcome group in.
	 * @param id the outcome group to look in
	 * @return The outcome group from Canvas
	 * @throws IOException When there is an error communicating with Canvas
	 */
	Optional<OutcomeGroup> getOutcomeGroupInAccount(String accountId, String id) throws IOException;

	/**
	 * Retrieve an outcome group
	 * See <a href="https://canvas.instructure.com/doc/api/outcome_groups.html#method.outcome_groups_api.show">Show an outcome group</a>
	 * in the Canvas docs for details.
	 * @param courseId The course to look for the outcome group in.
	 * @param id the outcome group to look in
	 * @return The outcome group from Canvas
	 * @throws IOException When there is an error communicating with Canvas
	 */
	Optional<OutcomeGroup> getOutcomeGroupInCourse(String courseId, String id) throws IOException;
}
