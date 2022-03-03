package edu.ksu.canvas.model.rubric;

import edu.ksu.canvas.annotation.CanvasField;
import edu.ksu.canvas.model.BaseCanvasModel;
import edu.ksu.canvas.model.assignment.RubricCriterion;

import java.util.List;

/**
 * Universitat Oberta de Catalunya
 * Made for the project canvas-api
 */
public class RubricCreationRequest extends BaseCanvasModel {
	private long associationId;
	private String title;
	private Boolean freeFormCriterionComments;
	private List<RubricCriterion> criteria;

	private long associationAssociationId;
	private String associationType;
	private Boolean useForGrading;
	private Boolean hideScoreTotal;
	private String purpose;

	@CanvasField(postKey = "rubric_association_id", array = false)
	public long getAssociationId() {
		return associationId;
	}

	public void setAssociationId(long associationId) {
		this.associationId = associationId;
	}

	@CanvasField(postKey = "title", overrideObjectKey = "rubric")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@CanvasField(postKey = "free_form_criterion_comments", overrideObjectKey = "rubric")
	public Boolean getFreeFormCriterionComments() {
		return freeFormCriterionComments;
	}

	public void setFreeFormCriterionComments(Boolean freeFormCriterionComments) {
		this.freeFormCriterionComments = freeFormCriterionComments;
	}

	@CanvasField(postKey = "criteria", hash = true, overrideObjectKey = "rubric")
	public List<RubricCriterion> getCriteria() {
		return criteria;
	}

	public void setCriteria(List<RubricCriterion> criteria) {
		this.criteria = criteria;
	}


	@CanvasField(postKey = "association_id", overrideObjectKey = "rubric_association")
	public long getAssociationAssociationId() {
		return associationAssociationId;
	}

	public void setAssociationAssociationId(long associationAssociationId) {
		this.associationAssociationId = associationAssociationId;
	}

	@CanvasField(postKey = "association_type", overrideObjectKey = "rubric_association")
	public String getAssociationType() {
		return associationType;
	}

	public void setAssociationType(String associationType) {
		this.associationType = associationType;
	}

	@CanvasField(postKey = "use_for_grading", overrideObjectKey = "rubric_association")
	public Boolean getUseForGrading() {
		return useForGrading;
	}

	public void setUseForGrading(Boolean useForGrading) {
		this.useForGrading = useForGrading;
	}

	@CanvasField(postKey = "purpose", overrideObjectKey = "rubric_association")
	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	@CanvasField(postKey = "hide_score_total", overrideObjectKey = "rubric_association")
	public Boolean getHideScoreTotal() {
		return hideScoreTotal;
	}

	public void setHideScoreTotal(Boolean hideScoreTotal) {
		this.hideScoreTotal = hideScoreTotal;
	}

}
