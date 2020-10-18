
package application.processes;

import javafx.concurrent.Task;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

/**
 * This process is designed to load DB.
 *
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */

public class APILoader extends Task<Void> {
    private static final String ENDPOINT = "https://quinzical-api.herokuapp.com/";

    private HttpClient _client = HttpClient.newHttpClient();

    /**
     * Construct process for loading SQL database
     */
    public APILoader() {
    }

    /**
     * Run in process in background with cancelled option.
     */
    @Override
    protected Void call() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(ENDPOINT)).GET().build();
        HttpResponse<String> response = _client.send(request, BodyHandlers.ofString());
        System.out.println(response);
        return null;
    }
}
