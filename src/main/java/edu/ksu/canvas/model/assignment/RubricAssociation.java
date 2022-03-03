package edu.ksu.canvas.model.assignment;

import edu.ksu.canvas.annotation.CanvasField;
import edu.ksu.canvas.annotation.CanvasObject;
import edu.ksu.canvas.model.BaseCanvasModel;

import java.io.Serializable;

@CanvasObject(postKey = "rubric_association")
public class RubricAssociation extends BaseCanvasModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long rubricId;
    private Long associationId;
    private String associationType;
    private Boolean useForGrading;
    private String purpose;
    private Boolean hideScoreTotal;
    private Boolean hidePoints;
    private Boolean hideOutcomeResults;
    // No clue what summary data is. The only place it is mentioned in docs seems to indicate it is a String
    // although I suspect this is not correct so I am leaving it out for now.
    //private String summaryData;

		@CanvasField(postKey = "id", array = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

		@CanvasField(postKey = "rubric_id", array = false)
    public Long getRubricId() {
        return rubricId;
    }

    public void setRubricId(Long rubricId) {
        this.rubricId = rubricId;
    }

		@CanvasField(postKey = "association_id", array = false)
    public Long getAssociationId() {
        return associationId;
    }

    public void setAssociationId(Long associationId) {
        this.associationId = associationId;
    }

		@CanvasField(postKey = "association_type", array = false)
    public String getAssociationType() {
        return associationType;
    }

    public void setAssociationType(String associationType) {
        this.associationType = associationType;
    }

		@CanvasField(postKey = "use_for_grading", array = false)
    public Boolean getUseForGrading() {
        return useForGrading;
    }

    public void setUseForGrading(Boolean useForGrading) {
        this.useForGrading = useForGrading;
    }

		@CanvasField(postKey = "purpose", array = false)
    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

		@CanvasField(postKey = "hide_score_total", array = false)
    public Boolean getHideScoreTotal() {
        return hideScoreTotal;
    }

    public void setHideScoreTotal(Boolean hideScoreTotal) {
        this.hideScoreTotal = hideScoreTotal;
    }

		@CanvasField(postKey = "hide_points", array = false)
    public Boolean getHidePoints() {
        return hidePoints;
    }

    public void setHidePoints(Boolean hidePoints) {
        this.hidePoints = hidePoints;
    }

		@CanvasField(postKey = "hide_outcome_results", array = false)
    public Boolean getHideOutcomeResults() {
        return hideOutcomeResults;
    }

    public void setHideOutcomeResults(Boolean hideOutcomeResults) {
        this.hideOutcomeResults = hideOutcomeResults;
    }
}
