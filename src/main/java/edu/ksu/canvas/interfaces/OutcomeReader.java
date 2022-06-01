package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.outcomes.Outcome;
import edu.ksu.canvas.model.outcomes.OutcomeGroup;
import edu.ksu.canvas.model.outcomes.OutcomeLink;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Universitat Oberta de Catalunya
 * Made for the project canvas-api
 */
public interface OutcomeReader extends CanvasReader<OutcomeLink, OutcomeReader> {
	/**
	 * list linked outcomes in an account
	 * See <a href="https://aula.uoc.edu/doc/api/outcome_groups.html#method.outcome_groups_api.outcomes">List linked outcome</a>
	 * in the Canvas docs for details.
	 * @param accountId The account to look for the outcome group in.
	 * @param id the outcome group to look in
	 * @return The linked outcomes from Canvas
	 * @throws IOException When there is an error communicating with Canvas
	 */
	List<OutcomeLink> getLinkedOutcomesInAccount(String accountId, String id) throws IOException;

	/**
	 * list linked outcomes in a course
	 * See <a href="https://aula.uoc.edu/doc/api/outcome_groups.html#method.outcome_groups_api.outcomes">List linked outcome</a>
	 * in the Canvas docs for details.
	 * @param courseId The course to look for the outcome group in.
	 * @param id the outcome group to look in
	 * @return The linked outcomes from Canvas
	 * @throws IOException When there is an error communicating with Canvas
	 */
	List<OutcomeLink> getLinkedOutcomesInCourse(String courseId, String id) throws IOException;
	
	Optional<Outcome> getOutcome(String id) throws IOException;
}
