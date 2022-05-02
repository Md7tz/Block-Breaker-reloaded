package game;

import utils.Config;

import javax.swing.JFrame;
import java.io.IOException;
import java.awt.Dimension;

import org.json.simple.parser.ParseException;

import graphics.Launcher;
import graphics.Window;

/**
 * Entrypoint
 */
public final class Application {
    public static void main(String[] args) { new Launcher(new Dimension(200, 150)); }

    public static void start() throws IOException, ParseException {
        gameWindow = new Window(Config.title() + " v" + Config.getVersion(), Config.getWindowDimension(), Config.resizable(), true, JFrame.EXIT_ON_CLOSE);
        gameWindow.initManager();
    }

    private static Window gameWindow;
}
