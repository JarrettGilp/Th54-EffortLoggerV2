package application;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

public class UserStory extends SceneController{

	private String date;
	private String title;
	private String description;
	public ArrayList<UserStoryItem> userStoryItems = new ArrayList<UserStoryItem>();
	public int id;
	
	public static ArrayList<String> obsItemArrList = new ArrayList<String>();	
	
	// DEFAULT CONSTRUCTOR
	public UserStory() {}
	
	// USERSTORY CONSTRUCTOR
	public UserStory(String date, String title, String description, ArrayList<UserStoryItem> userStoryItems, int id) {
		this.date = date;
		this.title = title;
		this.description = description;
		this.userStoryItems = userStoryItems;
		this.id = id;
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
	
	public int getID() {
		return id;
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
	
	public void setID(int id) {
		this.id = id;
	}
	
}
