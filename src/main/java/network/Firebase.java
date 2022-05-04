package network;

import game.Player;
import utils.Config;
import static utils.UtilLogging.LOGGER;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

import org.json.simple.parser.ParseException;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.WriteResult;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@SuppressWarnings("deprecation")
public class Firebase {
    static final String collection = "users";
    static String document;

    public static boolean initialize() throws IOException, UnsupportedOperationException {
        FileInputStream serviceAccount = new FileInputStream(Config.dotenv.get("BASE_DIR") + Config.dotenv.get("GOOGLE_APPLICATION_CREDENTIALS"));
        FirebaseOptions options = new FirebaseOptions.Builder().setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();

        FirebaseApp.initializeApp(options);
        LOGGER.log(Level.INFO, "Firebase Initialized");
        return true;
    }

    public static Firestore db() {  
        Firestore db = FirestoreClient.getFirestore();
        return db;
    }

    private static void genDocument() throws FileNotFoundException, IOException, ParseException {
        final boolean exists = Config.playerInfo() != null;
        if(exists) {
            Player.uuid = (String) Config.playerInfo().get("uuid");
            Config.updatePlayer(exists); 
            document = Player.uuid;
            LOGGER.log(Level.INFO, document);
            return;
        }

        Config.updatePlayer(exists); 
        Player.uuid = UUID.randomUUID().toString(); 
        document = Player.uuid;
        LOGGER.log(Level.INFO, document);
    }

    public static void uploadPlayerInfo() throws InterruptedException, ExecutionException, FileNotFoundException, IOException, ParseException {
        genDocument();
        DocumentReference ref = db().collection(collection).document(document);
        
        Map<String, Object> player = new HashMap<>();
        player.put("uuid", Player.uuid);
        player.put("name", Player.name);
        player.put("highestScore", Player.highestScore);
        player.put("lastUpdated", Player.lastUpdated);

        // Asynchronously write data
        ApiFuture<WriteResult> result = ref.set(player);

        // result.get() blocks on response
        LOGGER.log(Level.INFO, "Update time : " + result.get().getUpdateTime());
    }

    public static boolean mock(String document) throws InterruptedException, ExecutionException {        
        Firebase.document = document;
        DocumentReference ref = db().collection(collection).document(document);
        
        Map<String, Object> user = new HashMap<>();
        user.put("uuid", 123);
        user.put("name", "Mario");
        user.put("highestScore", 123);
        user.put("lastUpdated", 1651529406137L);

        // Asynchronously write data
        ApiFuture<WriteResult> result = ref.set(user);

        // result.get() blocks on response
        LOGGER.log(Level.INFO, "Update time : " + result.get().getUpdateTime());
        return true;
    }

    public static boolean undo() throws InterruptedException, ExecutionException {
        // Asynchronously delete last document
        ApiFuture<WriteResult> writeResult = db().collection(collection).document(document).delete();
        // ...
        LOGGER.log(Level.INFO, "Undo last operation : " + writeResult.get().getUpdateTime());

        return true;
    }
}
