package edu.ksu.canvas.model.outcomes;

import edu.ksu.canvas.annotation.CanvasField;
import edu.ksu.canvas.annotation.CanvasObject;
import edu.ksu.canvas.model.BaseCanvasModel;

import java.io.Serializable;

/**
 * Universitat Oberta de Catalunya
 * Made for the project canvas-api
 */
@CanvasObject(postKey = "outcome_group")
public class OutcomeGroup extends BaseCanvasModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private long id;
	private String url;
	private OutcomeGroup parentOutcomeGroup;
	private long contextId;
	private String contextType;
	private String title;
	private String description;
	private String vendorGuid;
	private String subgroupsUrl;
	private String outcomesUrl;
	private String importUrl;
	private boolean canEdit;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@CanvasField(postKey = "parent_outcome_group")
	public OutcomeGroup getParentOutcomeGroup() {
		return parentOutcomeGroup;
	}

	public void setParentOutcomeGroup(OutcomeGroup parentOutcomeGroup) {
		this.parentOutcomeGroup = parentOutcomeGroup;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

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

	@CanvasField(postKey = "subgroups_url")
	public String getSubgroupsUrl() {
		return subgroupsUrl;
	}

	public void setSubgroupsUrl(String subgroupsUrl) {
		this.subgroupsUrl = subgroupsUrl;
	}

	@CanvasField(postKey = "outcomes_url")
	public String getOutcomesUrl() {
		return outcomesUrl;
	}

	public void setOutcomesUrl(String outcomesUrl) {
		this.outcomesUrl = outcomesUrl;
	}

	@CanvasField(postKey = "import_url")
	public String getImportUrl() {
		return importUrl;
	}

	public void setImportUrl(String importUrl) {
		this.importUrl = importUrl;
	}

	@CanvasField(postKey = "can_edit")
	public boolean isCanEdit() {
		return canEdit;
	}

	public void setCanEdit(boolean canEdit) {
		this.canEdit = canEdit;
	}
}
