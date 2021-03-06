package game;


import utils.Config;
import graphics.Launcher;
import graphics.Window;

import javax.swing.JFrame;
import java.io.IOException;
import java.awt.Dimension;

import org.json.simple.parser.ParseException;

/**
 * Entrypoint
 */
public final class Application {
    public static void main(String[] args) throws IOException, ParseException {
        String env = (String) Config.dotenv.get("APP_ENV");
        if (env.equalsIgnoreCase("production")) {
            disableLogging();
            new Launcher(new Dimension(425, 110));
            return;
        }
        gameWindow = new Window();
        gameWindow.fitPanels();
    }

    public static void start() throws IOException, ParseException {
        gameWindow = new Window(Config.title() + " v" + Config.getVersion(), Config.getWindowDimension(), Config.resizable(), true, JFrame.EXIT_ON_CLOSE);
        gameWindow.fitPanels();
    }
    
    private final static void disableLogging() {
        System.setOut(new java.io.PrintStream(new java.io.OutputStream() {
            @Override public void write(int b) {}
        }) {
            @Override public void flush() {}
            @Override public void close() {}
            @Override public void write(int b) {}
            @Override public void write(byte[] b) {}
            @Override public void write(byte[] buf, int off, int len) {}
            @Override public void print(boolean b) {}
            @Override public void print(char c) {}
            @Override public void print(int i) {}
            @Override public void print(long l) {}
            @Override public void print(float f) {}
            @Override public void print(double d) {}
            @Override public void print(char[] s) {}
            @Override public void print(String s) {}
            @Override public void print(Object obj) {}
            @Override public void println() {}
            @Override public void println(boolean x) {}
            @Override public void println(char x) {}
            @Override public void println(int x) {}
            @Override public void println(long x) {}
            @Override public void println(float x) {}
            @Override public void println(double x) {}
            @Override public void println(char[] x) {}
            @Override public void println(String x) {}
            @Override public void println(Object x) {}
            @Override public java.io.PrintStream printf(String format, Object... args) { return this; }
            @Override public java.io.PrintStream printf(java.util.Locale l, String format, Object... args) { return this; }
            @Override public java.io.PrintStream format(String format, Object... args) { return this; }
            @Override public java.io.PrintStream format(java.util.Locale l, String format, Object... args) { return this; }
            @Override public java.io.PrintStream append(CharSequence csq) { return this; }
            @Override public java.io.PrintStream append(CharSequence csq, int start, int end) { return this; }
            @Override public java.io.PrintStream append(char c) { return this; }
        });
    }

    private static Window gameWindow;
}
