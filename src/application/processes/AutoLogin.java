
package application.processes;

import javafx.concurrent.Task;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import application.helper.SceneManager.Scenes;
import application.models.helper.JWTStore;
import application.models.helper.SplashModel;
import application.models.helper.SplashModel.Pages;
import application.models.login.LoginModel;

/**
 * This process is designed to AutoLogin user.
 *
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */

public class AutoLogin extends Task<Void> {

    private final LoginModel _loginModel = LoginModel.getInstance();

    private static final String ENDPOINT = "https://quinzical-api.herokuapp.com";
    private static final String SELF = "/self";

    private HttpClient _client = HttpClient.newHttpClient();

    /**
     * Run in process in background with cancelled option.
     */
    @Override
    protected Void call() throws IOException, InterruptedException, JSONException, SQLException {
        JWTStore jwtStore = new JWTStore();
        if (jwtStore.getJWT() == "") {
            return null;
        }

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(ENDPOINT + SELF))
                .header("Authorization", "Bearer " + jwtStore.getJWT()).GET().build();
        HttpResponse<String> response = _client.send(request, BodyHandlers.ofString());
        JSONObject body = new JSONObject(response.body());

        if (body.getString("_id") == "") {
            return null;
        }

        boolean userExists = _loginModel.checkUserExists(body.getString("username"));
        if (!userExists) {
            _loginModel.registerUser(body.getString("username"));
        }

        _loginModel.loginUser(body.getString("username"));

        _loginModel.setMongoID(body.getString("_id"));

        SplashModel.getInstance().setNextScene(Scenes.OPENING_MENU, Pages.SQL);

        return null;
    }
}
