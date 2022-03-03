package edu.ksu.canvas.requestOptions;

import edu.ksu.canvas.model.outcomes.Rating;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Universitat Oberta de Catalunya
 * Made for the project canvas-api
 */
public class CreateOutcomeOptions extends BaseOptions {
	public CreateOutcomeOptions title(String title) {
		addSingleItem("title", title);
		return this;
	}

	public CreateOutcomeOptions description(String description) {
		addSingleItem("description", description);
		return this;
	}

	public CreateOutcomeOptions displayName(String displayName) {
		addSingleItem("display_name", displayName);
		return this;
	}

	public CreateOutcomeOptions masteryPoints(Long masteryPoints) {
		addSingleItem("matery_points", Long.toString(masteryPoints));
		return this;
	}

	public CreateOutcomeOptions ratings(List<Rating> ratings) {
		addStringList("ratings[][description]", ratings.stream().map(Rating::getDescription).collect(Collectors.toList()));
		addNumberList("ratings[][points]", ratings.stream().map(Rating::getPoints).collect(Collectors.toList()));
		return this;
	}



}
