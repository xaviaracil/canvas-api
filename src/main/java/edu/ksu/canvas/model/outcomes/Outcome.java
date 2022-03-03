package edu.ksu.canvas.model.outcomes;

import edu.ksu.canvas.annotation.CanvasField;
import edu.ksu.canvas.annotation.CanvasObject;
import edu.ksu.canvas.model.BaseCanvasModel;

import java.io.Serializable;
import java.util.List;

/**
 * Universitat Oberta de Catalunya
 * Made for the project canvas-api
 */
@CanvasObject(postKey = "outcome")
public class Outcome extends BaseCanvasModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private long id;
	private String url;
	private long contextId;
	private String contextType;
	private String title;
	private String displayName;
	private String description;
	private String vendorGuid;
	private long pointsPossible;
	private long masteryPoints;
	private String calculationMethod;
	private long calculationInt;
	private List<Rating> ratings;
	private boolean canEdit;
	private boolean canUnlink;
	private boolean assessed;
	private boolean updateableRubrics;

	@CanvasField(postKey = "id")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@CanvasField(postKey = "url")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@CanvasField(postKey = "context_id")
	public long getContextId() {
		return contextId;
	}

	public void setContextId(long contextId) {
		this.contextId = contextId;
	}

	@CanvasField(postKey = "context_type")
	public String getContextType() {
		return contextType;
	}

	public void setContextType(String contextType) {
		this.contextType = contextType;
	}

	@CanvasField(postKey = "title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@CanvasField(postKey = "display_name")
	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@CanvasField(postKey = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@CanvasField(postKey = "vendor_guid")
	public String getVendorGuid() {
		return vendorGuid;
	}

	public void setVendorGuid(String vendorGuid) {
		this.vendorGuid = vendorGuid;
	}

	@CanvasField(postKey = "points_possible")
	public long getPointsPossible() {
		return pointsPossible;
	}

	public void setPointsPossible(long pointsPossible) {
		this.pointsPossible = pointsPossible;
	}

	@CanvasField(postKey = "mastery_points")
	public long getMasteryPoints() {
		return masteryPoints;
	}

	public void setMasteryPoints(long masteryPoints) {
		this.masteryPoints = masteryPoints;
	}

	@CanvasField(postKey = "calculation_method")
	public String getCalculationMethod() {
		return calculationMethod;
	}

	public void setCalculationMethod(String calculationMethod) {
		this.calculationMethod = calculationMethod;
	}

	@CanvasField(postKey = "calculation_int")
	public long getCalculationInt() {
		return calculationInt;
	}

	public void setCalculationInt(long calculationInt) {
		this.calculationInt = calculationInt;
	}

	@CanvasField(postKey = "ratings")
	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	@CanvasField(postKey = "can_edit")
	public boolean isCanEdit() {
		return canEdit;
	}

	public void setCanEdit(boolean canEdit) {
		this.canEdit = canEdit;
	}

	@CanvasField(postKey = "can_unlink")
	public boolean isCanUnlink() {
		return canUnlink;
	}

	public void setCanUnlink(boolean canUnlink) {
		this.canUnlink = canUnlink;
	}

	@CanvasField(postKey = "assessed")
	public boolean isAssessed() {
		return assessed;
	}

	public void setAssessed(boolean assessed) {
		this.assessed = assessed;
	}

	@CanvasField(postKey = "has_updateable_rubrics")
	public boolean isUpdateableRubrics() {
		return updateableRubrics;
	}

	public void setUpdateableRubrics(boolean updateableRubrics) {
		this.updateableRubrics = updateableRubrics;
	}
}
