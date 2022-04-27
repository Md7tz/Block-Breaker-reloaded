package game.utils;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

// import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@SuppressWarnings("unchecked")
public final class FileManager
{
    private static final int EXIT_ON_CLOSE = 3;

    /**
     * @param fileName
     * @throws FileNotFoundException
     */
    public static void writeJson(String fileName) throws FileNotFoundException {
        // Create a JSONObject
        JSONObject json = new JSONObject();

        // Populate data
        json.put("version", 0.1);

        Map<String, Object> windowProps = new LinkedHashMap<String, Object>(6);
        Map<String, Object> playerInfo = new LinkedHashMap<String, Object>(3);

        windowProps.put("title", "Block Breaker Reloaded");
        windowProps.put("width", 800);
        windowProps.put("height", 600);
        windowProps.put("resizable", true);
        windowProps.put("visibility", true);
        windowProps.put("OnClose", EXIT_ON_CLOSE);
        
        playerInfo.put("name", "Guest");
        playerInfo.put("highestScore", 680);
        playerInfo.put("lastUpdated",  System.currentTimeMillis());

        json.put("WindowProperties", windowProps);
        json.put("PlayerInformation", playerInfo);

        // Write JSON to file "config.json"
        PrintWriter pw = new PrintWriter(fileName);
        pw.write(json.toJSONString());

        pw.flush();
        pw.close();
    } 
}
