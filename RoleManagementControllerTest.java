package application;

import org.junit.Test;
import static org.junit.Assert.*;

public class RoleManagementControllerTest {

    @Test
    public void testIsValidUserID() {
        RoleManagementController controller = new RoleManagementController();

        assertTrue(controller.isValidUserID("user123"));
        assertFalse(controller.isValidUserID(""));
    }

    @Test
    public void testIsValidRole() {
        RoleManagementController controller = new RoleManagementController();

        assertTrue(controller.isValidRole("Admin"));
        assertFalse(controller.isValidRole(""));
    }
}
