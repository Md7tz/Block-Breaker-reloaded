package game;

public final class Player {
    public static String uuid;
    public static String name;
    public static int highestScore = 0;
    public static long lastUpdated = 0L;
    public static void setLastUpdated() { lastUpdated = System.currentTimeMillis(); };
}
