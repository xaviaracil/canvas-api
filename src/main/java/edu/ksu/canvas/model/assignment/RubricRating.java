package edu.ksu.canvas.model.assignment;

import edu.ksu.canvas.annotation.CanvasField;
import edu.ksu.canvas.annotation.CanvasObject;
import edu.ksu.canvas.interfaces.Hashable;
import edu.ksu.canvas.model.BaseCanvasModel;

import java.io.Serializable;

@CanvasObject(postKey = "rating")
public class RubricRating extends BaseCanvasModel implements Serializable, Hashable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String criterionId;
    private Double points;
    private String description;
    private String longDescription;

		@CanvasField(postKey = "id", array = false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

		@CanvasField(postKey = "criterion_id", array = false)
    public String getCriterionId() {
        return criterionId;
    }

    public void setCriterionId(String criterionId) {
        this.criterionId = criterionId;
    }

		@CanvasField(postKey = "points", array = false)
    public Double getPoints() {
        return points;
    }

    public void setPoints(Double points) {
        this.points = points;
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
}
