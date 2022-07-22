package edu.ksu.canvas.model.rubric;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import edu.ksu.canvas.annotation.CanvasField;
import edu.ksu.canvas.annotation.CanvasObject;
import edu.ksu.canvas.model.BaseCanvasModel;
import edu.ksu.canvas.model.assignment.Rubric;
import edu.ksu.canvas.model.assignment.RubricAssociation;
import edu.ksu.canvas.model.assignment.RubricCriterion;

import java.util.List;

/**
 * Universitat Oberta de Catalunya
 * Made for the project canvas-api
 */
@CanvasObject(postKey = "rubric")
public class RubricCreationJSONRequest extends BaseCanvasModel {
	private long id;

	private long rubric_association_id;

	private Rubric rubric;
	private RubricAssociation rubric_association;

	@CanvasField(postKey = "id", array = false)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getRubric_association_id() {
		return rubric_association_id;
	}

	public void setRubric_association_id(long rubric_association_id) {
		this.rubric_association_id = rubric_association_id;
	}

	public Rubric getRubric() {
		return rubric;
	}

	public void setRubric(Rubric rubric) {
		this.rubric = rubric;
	}

	public RubricAssociation getRubric_association() {
		return rubric_association;
	}

	public void setRubric_association(RubricAssociation rubric_association) {
		this.rubric_association = rubric_association;
	}

	public RubricCreationJSONRequest(RubricCreationRequest rubricCreationRequest) {
		super();
		this.setId(rubricCreationRequest.getId());
		this.setRubric_association_id(rubricCreationRequest.getAssociationId());
		Rubric rubric = new Rubric();
		rubric.setTitle(rubricCreationRequest.getTitle());
		rubric.setFreeFormCriterionComments(rubricCreationRequest.getFreeFormCriterionComments());
		rubric.setCriteria(rubricCreationRequest.getCriteria());
		this.setRubric(rubric);
		RubricAssociation rubricAssociation = new RubricAssociation();
		rubricAssociation.setAssociationId(rubricCreationRequest.getAssociationId());
		rubricAssociation.setAssociationType(rubricCreationRequest.getAssociationType());
		rubricAssociation.setUseForGrading(rubricCreationRequest.getUseForGrading());
		rubricAssociation.setHideScoreTotal(rubricCreationRequest.getHideScoreTotal());
		rubricAssociation.setHidePoints(rubricCreationRequest.getHidePoints());
		rubricAssociation.setPurpose(rubricCreationRequest.getPurpose());
		this.setRubric_association(rubricAssociation);
	}

	@Override
	public JsonObject toJsonObject(Boolean serializeNulls) {
		JsonObject jsonObject = super.toJsonObject(serializeNulls);
		// modify json object
		JsonObject rootObject = jsonObject.getAsJsonObject("rubric");
		JsonObject rubricJsonObject = rootObject.getAsJsonObject("rubric");
		JsonArray data = rubricJsonObject.getAsJsonArray("data");
		// convert data from array to hash
		JsonObject criteriaObject = new JsonObject();
		for (int i = 0; i < data.size(); i++) {
			JsonObject criteriaElementObject = data.get(i).getAsJsonObject();
			// convert ratings array to hash
			JsonArray ratings = criteriaElementObject.getAsJsonArray("ratings");
			JsonObject newRatings = new JsonObject();
			for (int j = 0; j < ratings.size(); j++) {
				JsonElement ratingElement = ratings.get(j);
				newRatings.add("" + j, ratingElement);
			}
			criteriaElementObject.remove("ratings");
			criteriaElementObject.add("ratings", newRatings);

			criteriaObject.add("" + i, criteriaElementObject);
		}
		rubricJsonObject.add("criteria", criteriaObject);
		rubricJsonObject.remove("data");
		return jsonObject;
	}
}
