package application.models.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import application.controllers.helper.WarningAlert;

/**
 * Leaderboard used to call REST API for Login Information
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class Login {
    private static final String ENDPOINT = "https://quinzical-api.herokuapp.com";
    private static final String LOGIN = "/login";
    private static final String REGISTER = "/register";

    private static final int INCORRECT_LOGIN = 401;
    private static final int INTERNAL_ERROR = 500;

    private HttpClient _client = HttpClient.newHttpClient();

    /**
     * Used to post a login to api
     * 
     * @return id (mongodb id)
     */
    public String postLogin(final String username, final String password) {
        try {
            JSONObject json = new JSONObject();
            json.put("username", username);
            json.put("password", password);

            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(ENDPOINT + LOGIN))
                    .header("Content-Type", "application/json").POST(BodyPublishers.ofString(json.toString())).build();
            HttpResponse<String> response = _client.send(request, BodyHandlers.ofString());

            if (response.statusCode() == INCORRECT_LOGIN) {
                new WarningAlert("Incorrect login details.");
                return "";
            } else if (response.statusCode() == INTERNAL_ERROR) {
                new WarningAlert("An internal error has occurred");
                return "";
            }
            JSONObject data = new JSONObject(response.body());
            return data.getString("id");
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Used to post an entry to the leaderboard
     * 
     * @param username
     * @param categories
     * @param score
     * @return id (mongodb id)
     */
    public String postLeaderboard(final String username, final String password) {
        try {
            JSONObject json = new JSONObject();
            json.put("username", username);
            json.put("password", password);

            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(ENDPOINT + REGISTER))
                    .header("Content-Type", "application/json").POST(BodyPublishers.ofString(json.toString())).build();
            HttpResponse<String> response = _client.send(request, BodyHandlers.ofString());

            System.out.println(response.body());
            System.out.println(json.toString());
            JSONObject data = new JSONObject(response.body());
            return data.getString("id");
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "";
    }
}
