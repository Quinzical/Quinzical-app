package quinzical.util.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class is used to get rooms for online lobbies
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class Lobby {
    private static final String ENDPOINT = "https://quinzical-api.herokuapp.com";
    private static final String ROOMS = "/rooms";

    private HttpClient _client = HttpClient.newHttpClient();

    /**
     * Used to rooms from api
     * 
     * @return LoginEntry
     */
    public Iterator<String> getRooms() {
        try {
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(ENDPOINT + ROOMS)).GET().build();
            HttpResponse<String> response = _client.send(request, BodyHandlers.ofString());
            JSONObject body = new JSONObject(response.body());
            return body.keys();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
