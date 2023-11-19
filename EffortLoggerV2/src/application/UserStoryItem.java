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
	
	public String getStoryTitle() {
		return title;
	}
	
	public String getStoryDescription() {
		return description;
	}
	
	public int getStoryScore() {
		return score;
	}
	
	//SETTERS
	
	public void setStoryTitle(String title) {
		this.title = title;
	}
	
	public void setStoryDescription(String description) {
		this.description = description;
	}
	
	public void setStoryScore(int score) {
		this.score = score;
	}
}
