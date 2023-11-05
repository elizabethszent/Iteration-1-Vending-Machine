package com.thelocalmarketplace.software.test;

import org.junit.Before;
import org.junit.Test;

import com.thelocalmarketplace.software.StartSession;

import static org.junit.Assert.*;

/**
 * This class contains unit tests for the StartSession class.
 */
public class StartSessionTest {

    private StartSession startSession;

    @Before
    public void setUp() {
    	 /**
         * Sets up the test by creating an instance of the StartSession class.
         */
        startSession = new StartSession();
    }

    @Test
    public void testSessionNotStartedInitially() {
    	 // Assert that the session is not started initially
        /**
         * Tests that the session is not started initially and expects the result to be false.
         */
        assertFalse("Session should not be started initially", startSession.checkSessionStarted());
    }

    @Test
    public void testStartSession() {
        // Call the method to start the session
        startSession.startSession();
        // Assert that the session is started
        /**
         * Tests starting the session and expects the result to be true after calling startSession.
         */
        assertTrue("Session should be started after calling startSession", startSession.checkSessionStarted());
    }

    @Test
    public void testStartSessionWhenAlreadyStarted() {
        // Start the session for the first time
        startSession.startSession();
        // Try to start the session again
        startSession.startSession();
     // The session should remain started (the method does not change session state if it's already started)
        /**
         * Tests starting the session when it's already started. Expects the session state to remain started.
         */
        assertTrue("Session should still be started after calling startSession again", startSession.checkSessionStarted());
    }
}