package application.models;

import application.models.sql.SQLConnection;

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
        SQLConnection.getInstance();
    }
}
//CHECKSTYLE:ON
