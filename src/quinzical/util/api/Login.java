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
    private static final String SELF = "/self";

    private static final int INCORRECT_LOGIN = 401;
    private static final int SHORT_PASSWORD = 402;
    private static final int DUPLICATE_USERNAME = 403;
    private static final int INTERNAL_ERROR = 500;

    private HttpClient _client = HttpClient.newHttpClient();

    /**
     * Used to getSelf from api
     * 
     * @param jwtToken
     * @return LoginEntry
     */
    public LoginEntry getSelf(final String jwtToken) {
        try {
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(ENDPOINT + SELF))
                    .header("Authorization", "Bearer " + jwtToken).GET().build();
            HttpResponse<String> response = _client.send(request, BodyHandlers.ofString());
            JSONObject body = new JSONObject(response.body());
            return new LoginEntry(body.getString("username"), body.getString("_id"), jwtToken);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Used to post a login to api
     * 
     * @param username
     * @param password
     * @return LoginEntry
     */
    public LoginEntry postLogin(final String username, final String password) {
        try {
            JSONObject json = new JSONObject();
            json.put("username", username);
            json.put("password", password);

            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(ENDPOINT + LOGIN))
                    .header("Content-Type", "application/json").POST(BodyPublishers.ofString(json.toString())).build();
            HttpResponse<String> response = _client.send(request, BodyHandlers.ofString());

            if (response.statusCode() == INCORRECT_LOGIN) {
                new WarningAlert("Incorrect login details.");
                return null;
            } else if (response.statusCode() == INTERNAL_ERROR) {
                new WarningAlert("An internal error has occurred.");
                return null;
            }
            JSONObject data = new JSONObject(response.body());
            return new LoginEntry(data.getString("username"), data.getString("id"), data.getString("bearer"));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Used to register a new user
     * 
     * @param username
     * @param password
     * @return LoginEntry
     */
    public LoginEntry postRegister(final String username, final String password) {
        try {
            JSONObject json = new JSONObject();
            json.put("username", username);
            json.put("password", password);

            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(ENDPOINT + REGISTER))
                    .header("Content-Type", "application/json").POST(BodyPublishers.ofString(json.toString())).build();
            HttpResponse<String> response = _client.send(request, BodyHandlers.ofString());

            if (response.statusCode() == INCORRECT_LOGIN) {
                new WarningAlert("Username is empty.");
                return null;
            } else if (response.statusCode() == INTERNAL_ERROR) {
                new WarningAlert("An internal error has occurred.");
                return null;
            } else if (response.statusCode() == SHORT_PASSWORD) {
                new WarningAlert("Password is empty or too short.");
                return null;
            } else if (response.statusCode() == DUPLICATE_USERNAME) {
                new WarningAlert("This username is already registered.");
                return null;
            }
            JSONObject data = new JSONObject(response.body());
            return new LoginEntry(data.getString("username"), data.getString("id"), data.getString("bearer"));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
