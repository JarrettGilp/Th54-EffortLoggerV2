package application;

import org.junit.Test;
import static org.junit.Assert.*;

public class UserProfileControllerTest {

    @Test
    public void testIsValidName() {
        UserProfileController controller = new UserProfileController();

        assertTrue(controller.isValidName("John"));
        assertFalse(controller.isValidName(""));
    }

    @Test
    public void testIsValidEmail() {
        UserProfileController controller = new UserProfileController();

        assertTrue(controller.isValidEmail("john@example.com"));
        assertFalse(controller.isValidEmail("invalid-email"));
    }

    @Test
    public void testIsValidAddress() {
        UserProfileController controller = new UserProfileController();

        assertTrue(controller.isValidAddress("123 Main St"));
        assertFalse(controller.isValidAddress(""));
    }

    @Test
    public void testIsValidPhone() {
        UserProfileController controller = new UserProfileController();

        assertTrue(controller.isValidPhone("1234567890"));
        assertFalse(controller.isValidPhone(""));
    }
}

