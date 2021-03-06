package com.db.dbs.model;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.db.dbs.utilities.Utilities;

public class Tenant {

	private ModelContext modelContext;

	private String name;
	private String description;
	
	private List<Application> applications = new ArrayList<Application>();

	public Tenant(ModelContext modelContext, String name, String description) {
		this.modelContext = modelContext;
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Application> getApplications(){
		this.applications = modelContext.applicationRepository.listApplicationsByTenant(this.name);
		return this.applications;
	}

	@JsonIgnore
	public String toJson(){
		String jsonstring = Utilities.convertObjectToJSON(this);
		return "{\"tenant\": " + jsonstring + "}";
	}
}
