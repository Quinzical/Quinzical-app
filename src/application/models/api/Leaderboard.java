package application.models.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Leaderboard used to call REST API for Global Leaderboard Information
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public final class Leaderboard {
    private static final String ENDPOINT = "https://quinzical-api.herokuapp.com";
    private static final String LEADERBOARD = "/leaderboard";

    private HttpClient _client = HttpClient.newHttpClient();

    /**
     * Used to retrieve the leaderboard
     * 
     * @return List of entries from api
     */
    public List<LeaderboardEntry> getLeaderboard() {
        List<LeaderboardEntry> entries = new ArrayList<LeaderboardEntry>();
        try {
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(ENDPOINT + LEADERBOARD)).GET().build();
            HttpResponse<String> response = _client.send(request, BodyHandlers.ofString());

            JSONArray array = new JSONArray(response.body());

            for (int i = 0; i < array.length(); i++) {
                JSONObject item = array.getJSONObject(i);
                JSONObject user = item.getJSONObject("user_id");
                entries.add(new LeaderboardEntry(user.getString("username"), item.getString("categories"),
                        item.getInt("score")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return entries;
    }

    /**
     * Used to post an entry to the leaderboard
     * 
     * @param mongoID
     * @param categories
     * @param score
     * @return id (mongodb id)
     */
    public String postLeaderboard(final String mongoID, final String categories, final int score) {
        try {
            JSONObject json = new JSONObject();
            json.put("score", score);
            json.put("categories", categories);
            json.put("user_id", mongoID);

            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(ENDPOINT + LEADERBOARD))
                    .header("Content-Type", "application/json").POST(BodyPublishers.ofString(json.toString())).build();
            HttpResponse<String> response = _client.send(request, BodyHandlers.ofString());

            JSONObject data = new JSONObject(response.body());
            System.out.println(response.body());
            return data.getString("id");
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return "";
    }
}
