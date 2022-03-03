package edu.ksu.canvas.requestOptions;

/**
 * Universitat Oberta de Catalunya
 * Made for the project canvas-api
 */
public class CreateOutcomeGroupOptions extends BaseOptions {
	public CreateOutcomeGroupOptions title(String title) {
		addSingleItem("title", title);
		return this;
	}

	public CreateOutcomeGroupOptions description(String description) {
		addSingleItem("description", description);
		return this;
	}

	public CreateOutcomeGroupOptions vendorGuid(String vendorGuid) {
		addSingleItem("vendor_guid", vendorGuid);
		return this;
	}

}
