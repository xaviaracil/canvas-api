package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.assignment.Rubric;
import edu.ksu.canvas.model.assignment.RubricWriterResponse;
import edu.ksu.canvas.model.rubric.RubricCreationRequest;

import java.io.IOException;
import java.util.Optional;

public interface RubricWriter extends CanvasWriter<Rubric, RubricWriter> {
	/**
	 * Creates a new rubric in the course
	 * See <a href="https://canvas.instructure.com/doc/api/rubrics.html#method.rubric_associations.create">Create a single rubric</a>
	 * in the Canvas docs for details.
	 * @param courseId the course ID of the course under which to place this rubric
	 * @param rubric A rubric object containing the information needed to create an rubric in Canvas
	 * @return a RubricWriterResponse with the newly created Rubric and the RubricAssociation
	 * @throws IOException When there is an error communicating with Canvas
	 */
	Optional<RubricWriterResponse> createSingleRubricInCourse(String courseId, RubricCreationRequest rubric) throws IOException;
}
