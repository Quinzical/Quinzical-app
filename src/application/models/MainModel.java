package application.models;

import application.models.api.Leaderboard;

/**
 * MainModel used for testing
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
//CHECKSTYLE:OFF
public class MainModel {
    /**
     * Run to test sql
     * @param args
     */
    public static void main(final String[] args) {
        Leaderboard _leaderboard = new Leaderboard();
        System.out.println(_leaderboard.getLeaderboard());

        System.out.println(_leaderboard.postLeaderboard("test3", "1,2,4,5,6", 200));
    }
}
//CHECKSTYLE:ON
