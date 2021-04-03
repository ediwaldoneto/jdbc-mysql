package br.com.nt.model;

public class ModelApplication {

	private long id;
	private String description;
	private String details;
	private boolean done;

	public ModelApplication() {

	}

	public ModelApplication(long id, String description, String details, boolean done) {

		this.id = id;
		this.description = description;
		this.details = details;
		this.done = done;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	@Override
	public String toString() {
		return "DemoApplication [id=" + id + ", description=" + description + ", details=" + details + ", done=" + done
				+ "]";
	}

}
