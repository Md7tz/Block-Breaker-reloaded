package game;

import javax.swing.JFrame;

import GUI.Window;
import GUI.Launcher;

/**
 * Entrypoint
 */
public final class Application {
    public static void main(String[] args) {
        new Launcher(200, 150);
    }

    public static void start() {
        gameWindow = new Window("Brick Breaker v0.1", 800, 600, true, true, JFrame.EXIT_ON_CLOSE);
        gameWindow.initManager();
    }

    private static Window gameWindow;
}
