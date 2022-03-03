package edu.ksu.canvas.model.assignment;

import edu.ksu.canvas.annotation.CanvasField;
import edu.ksu.canvas.annotation.CanvasObject;
import edu.ksu.canvas.interfaces.Hashable;
import edu.ksu.canvas.model.BaseCanvasModel;

import java.io.Serializable;
import java.util.List;

@CanvasObject(postKey = "criteria")
public class RubricCriterion extends BaseCanvasModel implements Serializable, Hashable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String description;
    private String longDescription;
    private Double points;
    private Boolean criterionUseRange;
    private Boolean ignoreForScoring;
    private Double masteryPoints;
    private Long learningOutcomeId;
    private List<RubricRating> ratings;

		@CanvasField(postKey = "id", array = false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

		@CanvasField(postKey = "description", array = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

		@CanvasField(postKey = "long_description", array = false)
    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

		@CanvasField(postKey = "points", array = false)
    public Double getPoints() {
        return points;
    }

    public void setPoints(Double points) {
        this.points = points;
    }

		@CanvasField(postKey = "criterion_use_range", array = false)
    public Boolean getCriterionUseRange() {
        return criterionUseRange;
    }

    public void setCriterionUseRange(Boolean criterionUseRange) {
        this.criterionUseRange = criterionUseRange;
    }

		@CanvasField(postKey = "ignore_for_scoring", array = false)
    public Boolean getIgnoreForScoring() {
        return ignoreForScoring;
    }

    public void setIgnoreForScoring(Boolean ignoreForScoring) {
        this.ignoreForScoring = ignoreForScoring;
    }

		@CanvasField(postKey = "mastery_points", array = false)
    public Double getMasteryPoints() {
        return masteryPoints;
    }

    public void setMasteryPoints(Double masteryPoints) {
        this.masteryPoints = masteryPoints;
    }

		@CanvasField(postKey = "learning_outcome_id", array = false)
    public Long getLearningOutcomeId() {
        return learningOutcomeId;
    }

    public void setLearningOutcomeId(Long learningOutcomeId) {
        this.learningOutcomeId = learningOutcomeId;
    }

		@CanvasField(postKey = "ratings", array = false, hash = true)
    public List<RubricRating> getRatings() {
        return ratings;
    }

    public void setRatings(List<RubricRating> ratings) {
        this.ratings = ratings;
    }
}
