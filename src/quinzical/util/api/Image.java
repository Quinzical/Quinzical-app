package quinzical.util.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;

import org.json.JSONException;
import org.json.JSONObject;

import quinzical.controllers.util.alerts.WarningAlert;
import quinzical.util.JWTStore;

/**
 * Leaderboard used to call REST API for Login Information
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class Image {
    private static final String ENDPOINT = "https://quinzical-api.herokuapp.com";
    private static final String IMAGE_URL = "/image";

    private static final int INTERNAL_ERROR = 500;

    private HttpClient _client = HttpClient.newHttpClient();

    /**
     * Used to get user image from the api
     * 
     * @param jwtToken
     * @return LoginEntry
     */
    public ImageEntry getImage(final String jwtToken) {
        JWTStore jwtStore = new JWTStore();

        try {
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(ENDPOINT + IMAGE_URL))
                    .header("Content-Type", "application/json").header("Authorization", "Bearer " + jwtStore.getJWT())
                    .GET().build();
            HttpResponse<String> response = _client.send(request, BodyHandlers.ofString());

            if (response.body().equals("null")) {
                return null;
            }

            JSONObject body = new JSONObject(response.body());
            return new ImageEntry(body.getString("imageUri"), body.getString("_id"), jwtToken);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Used to post a users image to the api
     * 
     * @param filename
     */
    public void postImage(final String filename) {
        JWTStore jwtStore = new JWTStore();

        try {
            JSONObject json = new JSONObject();
            json.put("imageUri", filename);

            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(ENDPOINT + IMAGE_URL))
                    .header("Content-Type", "application/json").header("Authorization", "Bearer " + jwtStore.getJWT())
                    .POST(BodyPublishers.ofString(json.toString())).build();
            HttpResponse<String> response = _client.send(request, BodyHandlers.ofString());

            if (response.statusCode() == INTERNAL_ERROR) {
                new WarningAlert("An internal error has occurred.");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
