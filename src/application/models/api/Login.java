package application.models.api;

/**
 * Leaderboard used to call REST API for Login Information
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class Login {

    /**
     * Used to post a login to api
     * 
     * @return id (mongodb id)
     */
    public LoginEntry postLogin() {
        LoginEntry login = null;
        try {
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(ENDPOINT + LEADERBOARD)).GET().build();
            HttpResponse<String> response = _client.send(request, BodyHandlers.ofString());

            JSONArray array = new JSONArray(response.body());

            for (int i = 0; i < array.length(); i++) {
                JSONObject item = array.getJSONObject(i);
                entries.add(new LeaderboardEntry(item.getString("username"), item.getString("categories"),
                        item.getInt("score")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return entries;
    }

    /**
     * Used to post an entry to the leaderboard
     * 
     * @param username
     * @param categories
     * @param score
     * @return id (mongodb id)
     */
    public String postLeaderboard(final String username, final String categories, final int score) {
        try {
            JSONObject json = new JSONObject();
            json.put("score", score);
            json.put("categories", categories);
            json.put("username", username);

            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(ENDPOINT + LEADERBOARD))
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
