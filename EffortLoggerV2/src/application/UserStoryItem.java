package application;

public class UserStoryItem extends UserStory{

	private String title;
	private String description;
	private int score;
	
	public UserStoryItem(String title, String description, int score) {
		super();
		this.title = title;
		this.description = description;
		this.score = score;
	}
	
	// GETTERS
	public String getTitle() {
		return title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public int getScore() {
		return score;
	}
	
	//SETTERS
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
}
