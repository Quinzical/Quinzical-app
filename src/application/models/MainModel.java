package application.models;

import application.models.helper.FileHashCheck;

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
        FileHashCheck check = new FileHashCheck();
        System.out.println(check.checkChange());
        //SQLConnection.getInstance().reloadQuestions();
    }
}
//CHECKSTYLE:ON
