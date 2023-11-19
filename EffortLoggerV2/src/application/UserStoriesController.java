package application;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserStoriesController {

	// USER STORIES VARIABLES
	private ObservableList<UserStory> storyList;
	private ArrayList<UserStoryItem> userStoryItems = new ArrayList<UserStoryItem>();
	
	// CONSTRUCTOR
	public UserStoriesController() {
		storyList = FXCollections.observableArrayList();
		storyList.add(new UserStory("", "", "", userStoryItems));
	}
	
	// GET LIST
	public ObservableList<UserStory> getList() {
		return storyList;
	}
	
	// SET LIST
	public void setList(ObservableList<UserStory> storyList) {
		this.storyList = storyList;
	}

}
