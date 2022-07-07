package edu.ksu.canvas.model.outcomes;

import edu.ksu.canvas.annotation.CanvasField;
import edu.ksu.canvas.annotation.CanvasObject;

import java.io.Serializable;
import java.util.List;

/**
 * Universitat Oberta de Catalunya
 * Made for the project canvas-api
 */
@CanvasObject(postKey = "root_outcome_group")
public class RootOutcomeGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	private OutcomeGroup rootOutcomeGroup;
	private List<OutcomeGroup> subgroups;

	@CanvasField(postKey = "root_outcome_group")
	public OutcomeGroup getRootOutcomeGroup() {
		return rootOutcomeGroup;
	}

	public void setRootOutcomeGroup(OutcomeGroup rootOutcomeGroup) {
		this.rootOutcomeGroup = rootOutcomeGroup;
	}

	@CanvasField(postKey = "subgroups")
	public List<OutcomeGroup> getSubgroups() {
		return subgroups;
	}

	public void setSubgroups(List<OutcomeGroup> subgroups) {
		this.subgroups = subgroups;
	}
}
