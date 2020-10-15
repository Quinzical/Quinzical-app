package application.models.sql.data;

/**
 * QuestionData used to store Question data from SQL Database
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class QuestionData {
    private int _id;
    private int _categoryID;
    private String _question;
    private String _qualifier;

    /**
     * Creates Category to hold SQL data
     * 
     * @param id
     * @param categoryID
     * @param question
     * @param qualifier
     */
    public QuestionData(final int id, final int categoryID, final String question, final String qualifier) {
        _id = id;
        _categoryID = categoryID;
        _question = question;
        _qualifier = qualifier;
    }

    /**
     * @return int return the _id
     */
    public int getID() {
        return _id;
    }

    /**
     * @return int return the _categoryID
     */
    public int getCategoryID() {
        return _categoryID;
    }

    /**
     * @return String return the _question
     */
    public String getQuestion() {
        return _question;
    }

    /**
     * @return String return the _qualifier
     */
    public String getQualifier() {
        return _qualifier;
    }
}
