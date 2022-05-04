package game;

import network.Firebase;

import org.json.simple.parser.ParseException;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Unit test for Blockbreaker game.
 */
public class Tester 
{
    /**
     * Test Firestore connection & adding player
     * @throws ExecutionException
     * @throws InterruptedException
     * @throws IOException
     * @throws UnsupportedOperationException
     * @throws ParseException
     */
    @Test
    public void testFirestore() throws InterruptedException, ExecutionException, UnsupportedOperationException, IOException, ParseException 
    {
        assertTrue( Firebase.initialize() );
        assertTrue( Firebase.mock("mock-UUID") ); TimeUnit.SECONDS.sleep(2);
        assertTrue( Firebase.undo() );
    }
}
