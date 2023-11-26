package application;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import javafx.event.ActionEvent;

class SceneControllerTest {

	private static ArrayList<UserStory> stories = new ArrayList<UserStory>();
	public ActionEvent event;
	
	@Test
	void test() throws IOException {
		SceneController obj = new SceneController();
		
		obj.createUserStory(event);
		
		assertNotNull(obj);
	}

}
