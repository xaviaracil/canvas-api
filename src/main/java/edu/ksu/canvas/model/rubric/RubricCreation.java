package edu.ksu.canvas.model.rubric;

import edu.ksu.canvas.annotation.CanvasField;
import edu.ksu.canvas.model.assignment.RubricCriterion;

import java.util.List;

/**
 * Universitat Oberta de Catalunya
 * Made for the project canvas-api
 */
public class RubricCreation {
	private String title;
	private Boolean freeFormCriterionComments;
	private List<RubricCriterion> criteria;

	@CanvasField(postKey = "title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@CanvasField(postKey = "free_form_criterion_comments")
	public Boolean getFreeFormCriterionComments() {
		return freeFormCriterionComments;
	}

	public void setFreeFormCriterionComments(Boolean freeFormCriterionComments) {
		this.freeFormCriterionComments = freeFormCriterionComments;
	}

	@CanvasField(postKey = "criteria", hash = true)
	public List<RubricCriterion> getCriteria() {
		return criteria;
	}

	public void setCriteria(List<RubricCriterion> criteria) {
		this.criteria = criteria;
	}
}
