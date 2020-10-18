
package application.processes;

import javafx.concurrent.Task;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import application.models.helper.JWTStore;

/**
 * This process is designed to AutoLogin user.
 *
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */

public class AutoLogin extends Task<Void> {
    private static final String ENDPOINT = "https://quinzical-api.herokuapp.com";

    private static final String SELF = "/self";

    private HttpClient _client = HttpClient.newHttpClient();

    /**
     * Run in process in background with cancelled option.
     */
    @Override
    protected Void call() throws IOException, InterruptedException {
        JWTStore jwtStore = new JWTStore();
        if (jwtStore.getJWT() == "") {
            return null;
        }

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(ENDPOINT + SELF))
                .header("Authorization", "Bearer " + jwtStore.getJWT()).GET().build();
        HttpResponse<String> response = _client.send(request, BodyHandlers.ofString());
        System.out.println(response.body());
        return null;
    }
}
