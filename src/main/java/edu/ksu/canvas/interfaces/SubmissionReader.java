package edu.ksu.canvas.interfaces;

import java.io.IOException;
import java.util.List;

import edu.ksu.canvas.model.assignment.Submission;
import edu.ksu.canvas.model.assignment.SubmissionResponse;
import edu.ksu.canvas.requestOptions.GetSubmissionsOptions;

public interface SubmissionReader  extends CanvasReader<Submission, SubmissionReader> {
    /**
     * Retrieve a list of quiz submissions with user and quiz information optionally included
     *
     * @param options Options class containing required and optional parameters for this API call
     * @return List of quiz submissions in the course with the course ID
     * @throws IOException When there is an error communicating with Canvas
     */
    SubmissionResponse getSubmissions(GetSubmissionsOptions options) throws IOException;
}
