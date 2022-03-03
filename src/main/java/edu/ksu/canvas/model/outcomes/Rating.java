package edu.ksu.canvas.model.outcomes;

import edu.ksu.canvas.annotation.CanvasField;
import edu.ksu.canvas.annotation.CanvasObject;
import edu.ksu.canvas.model.BaseCanvasModel;

import java.io.Serializable;

/**
 * Universitat Oberta de Catalunya
 * Made for the project canvas-api
 */
@CanvasObject(postKey = "rating")
public class Rating  extends BaseCanvasModel implements Serializable {
	private long points;
	private String description;

	@CanvasField(postKey = "points")
	public long getPoints() {
		return points;
	}

	public void setPoints(long points) {
		this.points = points;
	}

	@CanvasField(postKey = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
