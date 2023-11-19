package application;

import java.util.ArrayList;

public class UserStory extends SceneController{

	private String date;
	private String title;
	private String description;
	public ArrayList<UserStoryItem> userStoryItems = new ArrayList<UserStoryItem>();
	
	// DEFAULT CONSTRUCTOR
	public UserStory() {}
	
	// USERSTORY CONSTRUCTOR
	public UserStory(String date, String title, String description, ArrayList<UserStoryItem> userStoryItems) {
		this.date = date;
		this.title = title;
		this.description = description;
		this.userStoryItems = userStoryItems;
	}
	
	// GETTERS
	
	public String getTitle() {
		return title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getDate() {	
		return date;
	}
	
	public ArrayList<UserStoryItem> getItemList() {
		return userStoryItems;
	}
	
	// SETTERS
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	
}
