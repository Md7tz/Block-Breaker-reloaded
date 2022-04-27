package game;

import java.io.FileNotFoundException;

import game.components.Input;
import game.components.Window;
import game.components.GameManager;
import game.utils.FileManager;

/**
 * Entrypoint 
 */
public final class Application 
{
    private static Window window;
    public static void main( String[] args ) throws FileNotFoundException 
    {   
        window = new Window("Brick Breaker v0.1");
        window.init();
        FileManager.writeJson("config.json");
    }
}
