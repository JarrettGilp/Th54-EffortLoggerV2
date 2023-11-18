package application;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserStoriesController {

	private ObservableList<UserStory> storyList;
	private ArrayList<UserStoryItem> userStoryItems = new ArrayList<UserStoryItem>();
	
	public UserStoriesController() {
		storyList = FXCollections.observableArrayList();
		storyList.add(new UserStory("", "", "", userStoryItems));
	}
	
	public ObservableList<UserStory> getList() {
		return storyList;
	}
	
	public void setList(ObservableList<UserStory> storyList) {
		this.storyList = storyList;
	}

}
