package edu.ksu.canvas.model.assignment;

/**
 * Universitat Oberta de Catalunya
 * Made for the project canvas-api
 */
public class RubricWriterResponse {
	private Rubric rubric;
	private RubricAssociation rubricAssociation;

	public Rubric getRubric() {
		return rubric;
	}

	public void setRubric(Rubric rubric) {
		this.rubric = rubric;
	}

	public RubricAssociation getRubricAssociation() {
		return rubricAssociation;
	}

	public void setRubricAssociation(RubricAssociation rubricAssociation) {
		this.rubricAssociation = rubricAssociation;
	}
}
