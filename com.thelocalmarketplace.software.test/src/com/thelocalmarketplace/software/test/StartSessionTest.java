import org.junit.Before;
import org.junit.Test;

import com.thelocalmarketplace.software.StartSession;

import static org.junit.Assert.*;

public class StartSessionTest {

    private StartSession selfCheckoutControl;

    @Before
    public void setUp() {
        StartSession startSession = new StartSession();
    }

    @Test
    public void testSessionNotStartedInitially() {
        // Assert that the session is not started initially
        assertFalse("Session should not be started initially", selfCheckoutControl.checkSessionStarted());
    }

    @Test
    public void testStartSession() {
        // Call the method to start the session
        selfCheckoutControl.startSession();
        // Assert that the session is started
        assertTrue("Session should be started after calling startSession", selfCheckoutControl.checkSessionStarted());
    }

    @Test
    public void testStartSessionWhenAlreadyStarted() {
        // Start the session for the first time
        selfCheckoutControl.startSession();
        // Try to start the session again
        selfCheckoutControl.startSession();
        // The session should remain started (the method does not change session state if it's already started)
        assertTrue("Session should still be started after calling startSession again", selfCheckoutControl.checkSessionStarted());
    }
}