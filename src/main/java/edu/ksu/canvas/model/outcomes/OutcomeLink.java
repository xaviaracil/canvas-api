package edu.ksu.canvas.model.outcomes;

import edu.ksu.canvas.annotation.CanvasField;
import edu.ksu.canvas.annotation.CanvasObject;
import edu.ksu.canvas.model.BaseCanvasModel;

import java.io.Serializable;

/**
 * Universitat Oberta de Catalunya
 * Made for the project canvas-api
 */
@CanvasObject(postKey = "outcome_link")
public class OutcomeLink extends BaseCanvasModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private String url;
	private long contextId;
	private String contextType;
	private OutcomeGroup outcomeGroup;
	private Outcome outcome;
	private boolean assessed;
	private boolean canUnlink;

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

	@CanvasField(postKey = "outcome_group")
	public OutcomeGroup getOutcomeGroup() {
		return outcomeGroup;
	}

	public void setOutcomeGroup(OutcomeGroup outcomeGroup) {
		this.outcomeGroup = outcomeGroup;
	}

	public Outcome getOutcome() {
		return outcome;
	}

	public void setOutcome(Outcome outcome) {
		this.outcome = outcome;
	}

	public boolean isAssessed() {
		return assessed;
	}

	public void setAssessed(boolean assessed) {
		this.assessed = assessed;
	}

	@CanvasField(postKey = "can_unlink")
	public boolean isCanUnlink() {
		return canUnlink;
	}

	public void setCanUnlink(boolean canUnlink) {
		this.canUnlink = canUnlink;
	}
}
